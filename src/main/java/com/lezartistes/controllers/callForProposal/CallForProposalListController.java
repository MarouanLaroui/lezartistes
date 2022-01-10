package com.lezartistes.controllers.callForProposal;

import com.lezartistes.App;
import com.lezartistes.controllers.GeneralController;
import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.exceptions.CallForProposalDeleteImpossibleException;
import com.lezartistes.exceptions.CallForProposalNotFoundException;
import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.facades.CallForProposalFacade;
import com.lezartistes.facades.ClientFacade;
import com.lezartistes.facades.ServiceProviderFacade;
import com.lezartistes.models.CallForProposal;
import com.lezartistes.models.Client;
import com.lezartistes.models.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CallForProposalListController extends GeneralController implements Initializable {

    @FXML
    private Label error;
    @FXML
    private Label info;
    @FXML
    private Button addButton;

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
    private CallForProposal callForProposal;

    public CallForProposalListController() throws ClientNotFoundException {
        this.callForProposalFacade = CallForProposalFacade.getInstance();
        this.serviceProviderFacade = ServiceProviderFacade.getInstance();
        this.clientFacade = ClientFacade.getInstance();
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
            this.callForProposals = callForProposalFacade.getAllPostedAndOverCallForProposal();
            //s'il y a des cfp à afficher
            if (!this.callForProposals.isEmpty()){
                title.setCellValueFactory(new PropertyValueFactory<CallForProposal, String>("title"));
                status.setCellValueFactory(new PropertyValueFactory<CallForProposal, String>("status"));
                cfpTable.getItems().setAll(this.callForProposals);
            }
        }
        catch (CallForProposalNotFoundException c){
            this.info.setText("No call for proposal has been posted yet.");
            //c.printStackTrace();
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
            }
        }
        catch (CallForProposalNotFoundException c){
            System.out.println("No call for proposal posted by "+ this.connectedUser.getMail());
        }
    }

    @FXML
    public void addCallForProposal(){
        //todo: tester si ça marche avec la connexion
        //Si c'est un client, il peut ajouter un CFP
        if (!this.isServiceProvider){
            Stage stage = new Stage();
            stage.setHeight(480);
            stage.setWidth(640);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/callForProposal/addCallForProposal.fxml"));

            try{
                AddCallForProposalController afc = new AddCallForProposalController(stage);
                loader.setController(afc);
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            stage.show();
        }
        else{
            //sinon si c'est un service provider, il ne peut pas ajouter de CFP
            this.error.setText("You can't add a new call for proposal since you are not a client.");
        }
    }

    public void showDetails(MouseEvent mouseEvent) {
        CallForProposal selectedCFP = cfpTable.getSelectionModel().getSelectedItem();
        //s'il a sélectionné un item de la liste de CFP
        if (selectedCFP != null){
            Stage stage = new Stage();
            stage.setHeight(480);
            stage.setWidth(640);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/callForProposal/showCallForProposal.fxml"));

            try{
                ShowCallForProposalController afc = new ShowCallForProposalController(selectedCFP, stage);
                loader.setController(afc);
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            stage.show();
        }
        else{
            this.error.setText("Please select a call for proposal to display.");
        }
    }

    @FXML
    public void archiveCallForProposal(MouseEvent event){
        CallForProposal selectedCFP = cfpTable.getSelectionModel().getSelectedItem();
        //s'il n'y a pas de rapport associé, on peut supprimer le CFP
        try {
            callForProposalFacade.deleteCallForProposal(selectedCFP);
        } catch (CallForProposalDeleteImpossibleException e) {
            this.error.setText("You cannot delete a call for proposal related to an existant report.");
        }

    }
}
