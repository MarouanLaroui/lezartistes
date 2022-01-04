package com.lezartistes.controllers.report;

import com.lezartistes.App;
import com.lezartistes.exceptions.ReportNotFoundException;
import com.lezartistes.facades.ReportFacade;
import com.lezartistes.models.CallForProposal;
import com.lezartistes.models.Report;
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

public class SpReportListController implements Initializable {

    int serviceProviderId;
    CallForProposal callForProposal;
    ReportFacade reportFacade;
    List<Report> reports;
    private Stage stage;

    @FXML
    private ListView<Report> reportsList;
    @FXML
    private TextField searchInput;


    public SpReportListController(CallForProposal cfp, Stage stage){
        this.serviceProviderId = -1;
        this.callForProposal = cfp;
        this.reportFacade = ReportFacade.getInstance();
        this.stage = stage;
    }

    public SpReportListController(int serviceProviderId, Stage stage){
        this.callForProposal = null;
        this.serviceProviderId = serviceProviderId;
        this.reportFacade = ReportFacade.getInstance();
        this.stage = stage;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            /*Initialization depends on if it's a SP looking for his reports or a client looking for reports on a call for proposal*/
            if(this.callForProposal != null){
                this.reports = reportFacade.getAllReports();
                //this.reports = reportFacade.getReportsForProposal(callForProposal.getId());
            }
            else{
                if(this.serviceProviderId != -1){
                    //this.reports = reportFacade.getReportsByAuthor(2);
                    this.reports = reportFacade.getAllReports();
                }
            }
            this.reports = reportFacade.getAllReports();
            this.reportsList.setItems(new FilteredList<>(FXCollections.observableList(this.reports)));
        }
        catch (ReportNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<Report> filterByTitle(List<Report> clients, String title){

        if(title.equals("")){
            return this.reports;
        }
        else{
            List<Report> filteredReportList = new ArrayList<>();

            for(Report report : clients){
                if(report.getTitle().contains(title)){
                    filteredReportList.add(report);
                }
            }
            return filteredReportList;
        }
    }
    /*If someone type in search bar*/
    public void onType(javafx.scene.input.KeyEvent event){
        String searchedName = this.searchInput.getText();
        this.reportsList.setItems(new FilteredList<>(FXCollections.observableList(this.filterByTitle(this.reports,searchedName))));
    }

    /*If someone click on a client*/
    public void showReportDetail(javafx.scene.input.MouseEvent mouseEvent){

        Report selectedReport = this.reportsList.getSelectionModel().getSelectedItem();

        if(selectedReport!=null){
            //Create new stage to show client information
            Stage stage = new Stage();
            stage.setHeight(280);
            stage.setWidth(610);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/report/ViewReport.fxml"));

            try{
                ReadReportController cpc = new ReadReportController(selectedReport, stage);
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
            this.noReportFoundAlert();
        }
    }

    // Show a Information Alert with header Text
    private void noReportFoundAlert() {
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No report found");
        alert.setHeaderText("Error");
        alert.setContentText("ALERT : no report to open");
        alert.showAndWait();
    }

    //TODO : rediriger vers bonne page
    public void redirectToHome(MouseEvent mouseEvent) {
        try {
            App.setRoot("");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
