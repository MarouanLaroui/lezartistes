package com.lezartistes.controllers.callForProposal;

import com.lezartistes.controllers.GeneralController;
import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.exceptions.BuildingNotFoundException;
import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.exceptions.ReportNotFoundException;
import com.lezartistes.facades.BuildingFacade;
import com.lezartistes.facades.CallForProposalFacade;
import com.lezartistes.facades.ClientFacade;
import com.lezartistes.facades.ReportFacade;
import com.lezartistes.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddCallForProposalController extends GeneralController implements Initializable {
    @FXML
    private TextArea general_description;
    @FXML
    private TextField title;
    @FXML
    private Button signature;
    @FXML
    private Label error;
    @FXML
    private ComboBox<String> building;
    @FXML
    private ChoiceBox<String> report;

    private File imageSignature;

    private List<Building> buildings;
    private List<Report> reports;
    private CallForProposal callForProposal;
    private Stage stage;
    private final CallForProposalFacade callForProposalFacade;
    private final BuildingFacade buildingFacade;
    private final ClientFacade clientFacade;

    public AddCallForProposalController(Stage stage) {
        this.stage = stage;
        this.callForProposalFacade = CallForProposalFacade.getInstance();
        this.buildingFacade = BuildingFacade.getInstance();
        this.clientFacade = ClientFacade.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            //initilisation des buildings
            List<Building> buildingsToDisplay = this.buildingFacade.getAllBuilding();
            List<String> buildingsNames = new ArrayList<>();
            for (Building b : buildingsToDisplay) {
                buildingsNames.add(b.getName());
            }

            ObservableList<String> optionsB = FXCollections.observableArrayList(buildingsNames);
            this.building.getItems().addAll(optionsB);

        } catch (BuildingNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void addCallForProposal() throws IOException, ClientNotFoundException, BuildingNotFoundException {
        if(title.getText().equals("") || general_description.getText().equals("")){
            this.error.setText("Please give this call for proposal a title and a description.");
        }
        else{
            User user = this.clientFacade.getClientByEmail(UserInformation.getUser().getMail());
            int idAuthor = this.clientFacade.getClientIdByMail(user.getMail());

            String buildingChosen = this.building.getValue();
            int idBuilding = this.buildingFacade.getBuildingIdByName(buildingChosen);


            CallForProposal cfp = new CallForProposal(
                    this.title.getText(),
                    this.general_description.getText(),
                    Files.readAllBytes(imageSignature.toPath()),
                    idAuthor,
                    idBuilding);

            int retour = this.callForProposalFacade.createCallForProposal(cfp);
            if (retour != 0){
                this.closeStage();
            }
        }
    }

    public void addSignatureImage(ActionEvent actionEvent) {
        /*Create a new Stage*/
        Stage stage = new Stage();
        stage.setHeight(280);
        stage.setWidth(610);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        this.imageSignature = fileChooser.showOpenDialog(stage);
        signature.setText("uploaded");

    }

    public void closeStage() throws IOException {
        //todo: refresh la page CallForProposalList quand on ferme ce stage
        this.stage.close();
    }
}
