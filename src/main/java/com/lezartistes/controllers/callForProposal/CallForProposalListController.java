package com.lezartistes.controllers.callForProposal;

import com.lezartistes.controllers.GeneralController;
import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.dao.ClientDAOPostgres;
import com.lezartistes.dao.UserDAOPostgres;
import com.lezartistes.exceptions.CallForProposalNotFoundException;
import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.facades.CallForProposalFacade;
import com.lezartistes.facades.ClientFacade;
import com.lezartistes.facades.ServiceProviderFacade;
import com.lezartistes.models.CallForProposal;
import com.lezartistes.models.Client;
import com.lezartistes.models.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CallForProposalListController extends GeneralController implements Initializable {

    @FXML
    private Label error;
    @FXML
    private Label info;
    @FXML
    private Button deleteButton;

    @FXML private TableView<CallForProposal> cfpTable;
    @FXML private TableColumn<CallForProposal, String> title;
    @FXML private TableColumn<CallForProposal, String> status;
    @FXML private TableColumn<CallForProposal, String> author;

    private final CallForProposalFacade callForProposalFacade;
    List<CallForProposal> callForProposals;
    List<CallForProposal> callForProposalsByAuthor;
    private User connectedUser;
    private boolean isServiceProvider = UserInformation.isServiceProvider();
    private ClientFacade clientFacade;
    private ServiceProviderFacade serviceProviderFacade;

    public CallForProposalListController() throws ClientNotFoundException {
        this.callForProposalFacade = CallForProposalFacade.getInstance();
        this.serviceProviderFacade = ServiceProviderFacade.getInstance();
        this.clientFacade = ClientFacade.getInstance();
        //test service provider
        //this.connectedUser = serviceProviderFacade.getServiceProviderByEmail("ophelie");
        //test client
        //this.connectedUser = clientFacade.getClientById(1);
        this.connectedUser = UserInformation.getUser();
        this.isServiceProvider = UserInformation.isServiceProvider();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("isServiceProvider ? : "+ this.isServiceProvider);

        if (!this.isServiceProvider){
            //si c'est un client, on affiche ses CFP peu importe l'état
            displayListForClient();
        }
        else {
            //sinon on affiche uniquement les CFP postés
            displayListForServiceProvider();
        }
    }


    private void displayListForServiceProvider() {
        try{
            this.callForProposals = callForProposalFacade.getAllPostedCallForProposal();
            //s'il y a des cfp à afficher
            if (!this.callForProposals.isEmpty()){
                title.setCellValueFactory(new PropertyValueFactory<CallForProposal, String>("title"));
                status.setCellValueFactory(new PropertyValueFactory<CallForProposal, String>("status"));
                cfpTable.getItems().setAll(this.callForProposals);

                this.cfpTable.setOnMouseClicked(event -> System.out.println("clicked on " + cfpTable.getSelectionModel().getSelectedItem()));
            }
        }
        catch (CallForProposalNotFoundException c){
            this.info.setText("No call for proposal has been posted yet.");
            c.printStackTrace();
        }
    }

    //Un client voit tous les CFP postés ainsi que les siens
    private void displayListForClient() {
        try{
            //on récupère les CFP du client connecté
            Client connectedClient = this.clientFacade.getClientByEmail(this.connectedUser.getMail());
            this.callForProposalsByAuthor = callForProposalFacade.getCallForProposalByAuthor(connectedClient.getMail());

            //s'il y a des cfp à afficher
            if (!this.callForProposalsByAuthor.isEmpty()){
                title.setCellValueFactory(new PropertyValueFactory<CallForProposal, String>("title"));
                status.setCellValueFactory(new PropertyValueFactory<CallForProposal, String>("status"));
                cfpTable.getItems().setAll(this.callForProposalsByAuthor);

                this.cfpTable.setOnMouseClicked(event -> System.out.println("clicked on " + cfpTable.getSelectionModel().getSelectedItem()));
            }
        }
        catch (CallForProposalNotFoundException c){
            System.out.println("No call for proposal posted by "+ this.connectedUser.getMail());
            //this.info.setText("You haven't written any call for proposal yet!");
            //c.printStackTrace();
        }
    }


    //addCFP -> createCallForProposal
    //updateCFP -> updateCallForProposal
    //deleteCFP -> deleteCallForProposal
    //archiveCFP, postCFP, draftCFP, endCFP -> setStatusTo....
}
