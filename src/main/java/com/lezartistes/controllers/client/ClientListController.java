package com.lezartistes.controllers.client;

import com.lezartistes.facades.ClientFacade;
import com.lezartistes.models.Client;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.lezartistes.App;
import javafx.stage.Stage;

public class ClientListController implements Initializable {

    @FXML
    private ListView<Client> clientsList;
    private ClientFacade clientFacade;
    public ClientListController(){
        this.clientFacade = ClientFacade.getInstance();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Client> clients = clientFacade.getAllClients();
        System.out.println(clients.get(0).getMail());
        this.clientsList.setItems(new FilteredList<>(FXCollections.observableList(clients)));
    }
    /*If someone click on a client*/
    public void showClientDetail(javafx.scene.input.MouseEvent mouseEvent){

        Client selectedClient = clientsList.getSelectionModel().getSelectedItem();
        //Create new stage to show client information
        Stage stage = new Stage();
        stage.setHeight(280);
        stage.setWidth(610);
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


        //loader.setController(cpc);
    }
}
