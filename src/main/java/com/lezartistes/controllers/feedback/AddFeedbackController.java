package com.lezartistes.controllers.feedback;

import com.lezartistes.App;
import com.lezartistes.dao.feedback.FeedbackDAO;
import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.facades.CompanyFacade;
import com.lezartistes.facades.FeedbackFacade;
import com.lezartistes.models.Company;
import com.lezartistes.models.Feedback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddFeedbackController{

    @FXML
    private TextArea comment;
    @FXML
    private TextField rating;
    @FXML
    private Label error;

    private Feedback feedback;
    private Company company;
    private Stage stage;
    private final FeedbackFacade feedbackFacade;
    private final CompanyFacade companyFacade;

    public AddFeedbackController(Feedback feedback, Company company, Stage stage){
        this.feedback = feedback;
        this.company = company;
        this.stage = stage;
        this.feedbackFacade = FeedbackFacade.getInstance();
        this.companyFacade = CompanyFacade.getInstance();
    }

    @FXML
    public void addFeedback() throws CompanyNotFoundException, IOException {
        if(comment.getText().equals("") || rating.getText().equals("")){
            this.error.setText("Please fill all the fields.");
        }
        else{
            Feedback fb = new Feedback(Integer.parseInt(rating.getText()),
                    comment.getText().trim(),
                    this.companyFacade.getCompanyIdByName(this.company.getName()));
            int retour = this.feedbackFacade.addFeedback(fb);
            if (retour != 0){
                this.closeStage();
            }
        }
    }

    public void closeStage() throws IOException {
        //todo: refresh la page FeedbackList quand on ferme ce stage
        this.stage.close();
    }
}
