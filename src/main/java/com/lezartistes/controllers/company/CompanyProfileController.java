package com.lezartistes.controllers.company;

import com.lezartistes.App;
import com.lezartistes.controllers.feedback.FeedbackListController;
import com.lezartistes.facades.CompanyFacade;
import com.lezartistes.models.Company;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CompanyProfileController implements Initializable {

    /**
     * Linked to the TextField who's fx:id is his name
     */
    @FXML
    private Label name;

    @FXML
    private Label departement;

    @FXML
    private Label street;

    @FXML
    private Label city;

    @FXML
    private Label postalCode;

    @FXML
    private Label complement;

    private Company company;
    private final CompanyFacade companyFacade;
    private Stage stage;

    public CompanyProfileController(Company company, Stage stage){
        this.company = company;
        this.companyFacade = CompanyFacade.getInstance();
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*Displaying the retrieved informations*/
        name.setText(this.company.getName());
        departement.setText(this.company.getDepartement());
        street.setText(this.company.getStreet());
        city.setText(this.company.getCity());
        postalCode.setText(String.valueOf(this.company.getPostal_code()));
        complement.setText(this.company.getComplement());

    }

    @FXML
    protected void displayFeedback(MouseEvent mouseEvent) throws IOException {
        if(this.company != null){
            //System.out.println("dans displayFeedback");
            //Create new stage to show company information
            Stage stage = new Stage();
            stage.setHeight(480);
            stage.setWidth(640);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/feedback/feedbackList.fxml"));

            try{
                FeedbackListController cpc = new FeedbackListController(this.company, stage);
                loader.setController(cpc);
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            stage.show();
        }
        else{
            this.noCompanyFoundAlert();
        }
    }

    // Show a Information Alert with header Text
    private void noCompanyFoundAlert() {
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No company found");
        alert.setHeaderText("Error");
        alert.setContentText("ALERT : no company to open");
        alert.showAndWait();
    }


    /*
    public void redirectToCompanyList(MouseEvent mouseEvent) {
        try {
            App.setRoot("views/company/companyList");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
