package com.lezartistes.controllers.callForProposal;

import com.lezartistes.controllers.GeneralController;
import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.exceptions.CFPIllegalChangeOfStateException;
import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.facades.CallForProposalFacade;
import com.lezartistes.facades.ClientFacade;
import com.lezartistes.models.CallForProposal;
import com.lezartistes.models.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowCallForProposalController extends GeneralController implements Initializable {

    @FXML
    private Label title;

    @FXML
    private Label general_description;

    @FXML
    private Label status;

    @FXML
    private Label author;

    @FXML
    private ChoiceBox<String> newStatus;

    @FXML
    private Button save;

    @FXML
    private Label error;

    @FXML
    private Label labelNewStatus;

    private CallForProposal callForProposal;
    private CallForProposalFacade callForProposalFacade;
    private Stage stage;
    private final ClientFacade clientFacade;

    public ShowCallForProposalController(CallForProposal cfp, Stage stage){
        this.callForProposal = cfp;
        this.stage = stage;
        this.clientFacade = ClientFacade.getInstance();
        this.callForProposalFacade = CallForProposalFacade.getInstance();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //todo: ajouter un bouton qui redirige vers le rapport associé

        try {
            this.title.setText(this.callForProposal.getTitle());
            this.general_description.setText(this.callForProposal.getGeneral_description());
            Client client = clientFacade.getClientById(this.callForProposal.getIdClientAuthor());
            this.author.setText(client.getName() +" "+client.getSurname());
            this.status.setText(this.callForProposal.getStatus());

            this.newStatus.getItems().add("DRAFT");
            this.newStatus.getItems().add("POSTED");
            this.newStatus.getItems().add("OVER");
            this.newStatus.getItems().add("ARCHIVED");
            //si c'est un client
            if (!UserInformation.isServiceProvider()){
                this.labelNewStatus.setVisible(true);
                this.newStatus.setVisible(true);
            }
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void showSaveButton(MouseEvent event){
        this.save.setVisible(true);
    }

    @FXML
    public void saveNewStatus(MouseEvent event){
        String selectedStatus = this.newStatus.getValue();

        try {
            switch (selectedStatus){
                case "DRAFT":
                    this.callForProposalFacade.setStatusToDraft(this.callForProposal);
                    break;
                case "POSTED":
                    this.callForProposalFacade.setStatusToPosted(this.callForProposal);
                    break;
                case "OVER":
                    this.callForProposalFacade.setStatusToOver(this.callForProposal);
                    break;
                case "ARCHIVED":
                    this.callForProposalFacade.setStatusToArchived(this.callForProposal);
                    break;
            }
        }
        catch (CFPIllegalChangeOfStateException e) {
            this.error.setText("You cannot change this call for proposal from "+this.callForProposal.getStatus()+" to "+ selectedStatus);
            //e.printStackTrace();
        }

    }

    public void closeStage() throws IOException {
        //todo: refresh la page CallForProposalList quand on ferme ce stage
        this.stage.close();
    }
}
