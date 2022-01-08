package com.lezartistes.controllers.callForProposal;

import com.lezartistes.controllers.GeneralController;
import com.lezartistes.exceptions.BuildingNotFoundException;
import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.facades.CallForProposalFacade;
import com.lezartistes.models.CallForProposal;
import com.lezartistes.models.Feedback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AddCallForProposalController extends GeneralController {
    @FXML
    private TextArea general_description;
    @FXML
    private TextField title;
    @FXML
    private Button signature;
    @FXML
    private Label error;

    private File imageSignature;

    private CallForProposal callForProposal;
    private Stage stage;
    private final CallForProposalFacade callForProposalFacade;

    public AddCallForProposalController(CallForProposal callForProposal, Stage stage) {
        this.callForProposal = callForProposal;
        this.stage = stage;
        this.callForProposalFacade = CallForProposalFacade.getInstance();
    }

    @FXML
    public void addCallForProposal() throws BuildingNotFoundException, IOException {
        if(title.getText().equals("") || general_description.getText().equals("")){
            this.error.setText("Please give this call for proposal a title and a description.");
        }
        else{
            /*CallForProposal cfp = new CallForProposal();

            int retour = this.callForProposalFacade.createCallForProposal(cfp);
            if (retour != 0){
                this.closeStage();
            }*/
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
