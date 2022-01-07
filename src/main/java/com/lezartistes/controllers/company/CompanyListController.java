package com.lezartistes.controllers.company;

import com.lezartistes.App;
import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.facades.CompanyFacade;
import com.lezartistes.models.Client;
import com.lezartistes.models.Company;
import com.lezartistes.models.User;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CompanyListController implements Initializable {
    /*attributes*/
    CompanyFacade companyFacade;
    List<Company> companies;
    private Stage stage;
    private User user;

    @FXML
    private ListView<Company> companiesList;
    @FXML
    private TextField searchInput;

    public CompanyListController(){
        this.companyFacade = CompanyFacade.getInstance();
    }

    /*methods*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: retirer une fois les tests finis
        /*User u = new User("ophelie@gmail.com", "mdp", true);
        this.user = UserInformation.setUser(new Client(u));*/

        try {
            this.companies = this.companyFacade.getAllCompanies();
            this.companiesList.setItems(new FilteredList<>(FXCollections.observableList(this.companies)));
        } catch (CompanyNotFoundException e) {
            e.printStackTrace();
        }
    }
    private List<Company> filterByName(List<Company> companies, String name){

        if(name.equals("")){
            return companies;
        }
        else{
            List<Company> filteredCompaniesList = new ArrayList<>();

            for(Company company : companies){
                if(company.getName().contains(name)){
                    filteredCompaniesList.add(company);
                }
            }
            return filteredCompaniesList;
        }
    }

    public void onType(javafx.scene.input.KeyEvent event){
        String searchedName = this.searchInput.getText();
        this.companiesList.setItems(new FilteredList<>(FXCollections.observableList(this.filterByName(this.companies,searchedName))));
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

    /*If click on a company*/
    public void displayCompanyProfile(MouseEvent mouseEvent) {
        Company selectedCompany = companiesList.getSelectionModel().getSelectedItem();

        if(selectedCompany != null){
            //Create new stage to show company information
            Stage stage = new Stage();
            stage.setHeight(480);
            stage.setWidth(640);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/company/companyProfile.fxml"));

            try{
                CompanyProfileController cpc = new CompanyProfileController(selectedCompany, stage);
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
            this.noCompanyFoundAlert();
        }

    }
}
