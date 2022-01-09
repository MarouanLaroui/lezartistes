package com.lezartistes.controllers.client;

import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.facades.ClientFacade;
import com.lezartistes.models.Client;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.lezartistes.App;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ClientListController implements Initializable {

    @FXML
    private Label errorMessage;
    @FXML
    private ListView<Client> clientsList;
    @FXML
    private TextField searchInput;

    private List<Client> clients;

    private ClientFacade clientFacade;

    /*Constructor*/
    public ClientListController(){
        this.clientFacade = ClientFacade.getInstance();
    }



    //TODO : gérer affichage UserNotFoundException
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            this.clients = clientFacade.getAllClients();
            this.clientsList.setItems(new FilteredList<>(FXCollections.observableList(clients)));
        }
        catch (ClientNotFoundException e) {
            e.printStackTrace();
            this.errorMessage.setText("ALERT : no user found");
        }

    }

    private List<Client> filterByName(List<Client> clients, String name){

        this.errorMessage.setText("");

        if(name.equals("")){
            return clients;
        }
        else{
            List<Client> filteredClientList = new ArrayList<>();

            for(Client client : clients){
                if(client.getName().contains(name)){
                    filteredClientList.add(client);
                }
            }
            if(filteredClientList.isEmpty()){
                this.errorMessage.setText("No such client found");
            }
            return filteredClientList;
        }
    }

    // Show a Information Alert with header Text
    private void noUserFoundAlert() {
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No client found");
        alert.setHeaderText("Error");
        alert.setContentText("ALERT : no client to open");
        alert.showAndWait();
    }

    /*If someone click on a client*/
    public void showClientDetail(javafx.scene.input.MouseEvent mouseEvent){

        Client selectedClient = clientsList.getSelectionModel().getSelectedItem();

        if(selectedClient!=null){
            //Create new stage to show client information
            Stage stage = new Stage();
            stage.setHeight(480);
            stage.setWidth(640);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/client/ClientProfile.fxml"));

            try{
                ClientProfileController cpc = new ClientProfileController(selectedClient, stage);
                loader.setController(cpc);
                Scene scene = new Scene(loader.load(), stage.getWidth(),stage.getHeight());
                stage.setScene(scene);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            stage.show();
        }
        else{
            this.noUserFoundAlert();
        }
    }

    public void onType(javafx.scene.input.KeyEvent event){
        String searchedName = this.searchInput.getText();
        this.clientsList.setItems(new FilteredList<>(FXCollections.observableList(this.filterByName(this.clients,searchedName))));
    }

    //TODO : rediriger vers bonne page en fonction du type
    public void redirectToHome(MouseEvent mouseEvent) {
        try {
            App.setRoot("views/accueilExpert");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
