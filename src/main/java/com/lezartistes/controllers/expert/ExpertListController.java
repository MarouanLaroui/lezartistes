package com.lezartistes.controllers.expert;

import com.lezartistes.App;
import com.lezartistes.exceptions.ExpertNotFoundException;
import com.lezartistes.facades.ExpertFacade;
import com.lezartistes.models.Expert;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ExpertListController implements Initializable {
    @FXML
    private Label errorMessage;
    @FXML
    private ListView<Expert> expertsList;
    @FXML
    private TextField searchInput;

    private List<Expert> experts;

    private ExpertFacade expertFacade;

    /*Constructor*/
    public ExpertListController(){
        this.expertFacade = ExpertFacade.getInstance();
    }



    //TODO : gérer affichage UserNotFoundException
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            this.experts = expertFacade.getAllExperts();
            this.expertsList.setItems(new FilteredList<Expert>(FXCollections.observableList(experts)));
        }
        catch (ExpertNotFoundException e) {
            e.printStackTrace();
            this.errorMessage.setText("ALERT : no expert found");
        }

    }

    private List<Expert> filterByName(List<Expert> experts, String name){

        if(name.equals("")){
            return experts;
        }
        else{
            List<Expert> filteredExpertList = new ArrayList<>();

            for(Expert expert : experts){
                if(expert.getMail().contains(name)){
                    filteredExpertList.add(expert);
                }
            }
            return filteredExpertList;
        }
    }

    // Show a Information Alert with header Text
    private void noExpertFoundAlert() {
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No expert found");
        alert.setHeaderText("Error");
        alert.setContentText("ALERT : no expert to open");
        alert.showAndWait();
    }

    /*If someone click on a client*/
    public void showExpertDetail(javafx.scene.input.MouseEvent mouseEvent){

        Expert selectedExpert = expertsList.getSelectionModel().getSelectedItem();

        if(selectedExpert!=null){
            //Create new stage to show client information
            Stage stage = new Stage();
            stage.setHeight(480);
            stage.setWidth(640);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/client/ClientProfile.fxml"));

            try{
                ExpertProfileController cpc = new ExpertProfileController(selectedExpert, stage);
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
            this.noExpertFoundAlert();
        }
    }

    public void onType(javafx.scene.input.KeyEvent event){
        String searchedName = this.searchInput.getText();
        this.expertsList.setItems(new FilteredList<Expert>(FXCollections.observableList(this.filterByName(this.experts,searchedName))));
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
