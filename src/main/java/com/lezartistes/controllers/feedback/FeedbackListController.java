package com.lezartistes.controllers.feedback;

import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.exceptions.FeedbackNotFoundException;
import com.lezartistes.facades.CompanyFacade;
import com.lezartistes.facades.FeedbackFacade;
import com.lezartistes.models.Company;
import com.lezartistes.models.Feedback;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

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


    private Company company;
    private final FeedbackFacade feedbackFacade;
    List<Feedback> feedbacks;
    private Stage stage;

    public FeedbackListController(Company company, Stage stage) {
        this.company = company;
        this.feedbackFacade = FeedbackFacade.getInstance();
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.feedbacks = feedbackFacade.getAllFeedbackByCompany(this.company.getName());
            //if there is feedbacks to display
            if(!this.feedbacks.isEmpty()){
                this.feedbacksList.setItems(new FilteredList<>(FXCollections.observableList(this.feedbacks)));
            }
            //todo: gérer l'affichage quand il n'y a pas de feedbacks (on affiche l'alerte et PAS la liste vide)
            this.noFeedbackFoundAlert();

        }catch (FeedbackNotFoundException | CompanyNotFoundException e){
            this.noFeedbackFoundAlert();
            e.printStackTrace();
        }
    }

    // Show a Information Alert with header Text
    private void noFeedbackFoundAlert() {
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No feedback found for "+this.company.getName());
        alert.setHeaderText("Error");
        alert.setContentText("ALERT : no feedback to display");
        alert.showAndWait();
    }

}
