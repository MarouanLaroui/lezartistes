package com.lezartistes.controllers.feedback;

import com.lezartistes.App;
import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.exceptions.FeedbackNotFoundException;
import com.lezartistes.facades.CompanyFacade;
import com.lezartistes.facades.FeedbackFacade;
import com.lezartistes.models.Company;
import com.lezartistes.models.Feedback;
import com.lezartistes.models.User;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FeedbackListController implements Initializable {

    /*attributes*/

    /**
     * Linked to the TextField who's fx:id is his rating
     */
    @FXML
    private ListView<Feedback> feedbacksList;
    @FXML
    private Label error;
    @FXML
    private Button deleteButton;


    private Company company;
    private Feedback feedback;
    private final FeedbackFacade feedbackFacade;
    List<Feedback> feedbacks;
    private Stage stage;
    private boolean isSP;

    public FeedbackListController(Company company, Stage stage) {
        this.company = company;
        this.feedbackFacade = FeedbackFacade.getInstance();
        this.stage = stage;
        this.isSP = UserInformation.getIsSP();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.feedbacks = feedbackFacade.getAllFeedbackByCompany(this.company.getName());
            //if there is feedbacks to display
            if(!this.feedbacks.isEmpty()){
                this.feedbacksList.setItems(new FilteredList<>(FXCollections.observableList(this.feedbacks)));

                this.feedbacksList.setOnMouseClicked(event -> System.out.println("clicked on " + feedbacksList.getSelectionModel().getSelectedItem()));
            }
            //else
                //todo: gérer mieux l'affichage quand il n'y a pas de feedbacks
                //this.noFeedbackFoundAlert();

        }catch (FeedbackNotFoundException | CompanyNotFoundException e){
            this.noFeedbackFoundAlert();
            //e.printStackTrace();
        }
    }


    @FXML
    protected void addFeedback(MouseEvent mouseEvent) throws IOException {

        //todo: tester si ça marche avec la connexion
        //If the connected user is a client
        if (!this.isSP){
            Stage stage = new Stage();
            stage.setHeight(480);
            stage.setWidth(640);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/feedback/addFeedback.fxml"));

            try{
                AddFeedbackController afc = new AddFeedbackController(this.feedback, this.company, stage);
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
            //if the user is a ServiceProvider
            this.error.setText("You can't add a new feedback since you are not a client.");
        }
    }

    @FXML
    protected void deleteFeedback(MouseEvent mouseEvent) throws IOException{
        //get selected item
        Feedback selectedFeedback = this.feedbacksList.getSelectionModel().getSelectedItem();
        if (selectedFeedback!=null){
            this.deleteButton.setCancelButton(false);
            //todo: print message quand on delete
            int retour = this.feedbackFacade.deleteFeedback(selectedFeedback);
            if (retour == 0){
                this.error.setText("Delete failed");
            }
        }
        else{
            this.error.setText("Choose a feedback to delete.");
        }
    }

    // Show a Information Alert with header Text
    private void noFeedbackFoundAlert() {
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No feedback found for "+this.company.getName());
        alert.setHeaderText("Warning");
        alert.setContentText("ALERT : no feedback found for "+this.company.getName()+" but you can add a new one!");
        alert.showAndWait();
    }



}
