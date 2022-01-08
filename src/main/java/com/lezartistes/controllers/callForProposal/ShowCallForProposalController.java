package com.lezartistes.controllers.callForProposal;

import com.lezartistes.controllers.GeneralController;
import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.facades.ClientFacade;
import com.lezartistes.models.CallForProposal;
import com.lezartistes.models.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    private CallForProposal callForProposal;
    private Stage stage;
    private final ClientFacade clientFacade;

    public ShowCallForProposalController(CallForProposal cfp, Stage stage){
        this.callForProposal = cfp;
        this.stage = stage;
        this.clientFacade = ClientFacade.getInstance();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //todo: ajouter un bouton qui redirige vers le rapport associé
        //todo: afficher la signature ?

        try {
            this.title.setText(this.callForProposal.getTitle());
            this.general_description.setText(this.callForProposal.getGeneral_description());
            Client client = clientFacade.getClientById(this.callForProposal.getIdClientAuthor());
            this.author.setText(client.getName() +" "+client.getSurname());
            this.status.setText(this.callForProposal.getStatus());
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void closeStage() throws IOException {
        //todo: refresh la page CallForProposalList quand on ferme ce stage
        this.stage.close();
    }
}