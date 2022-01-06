package com.lezartistes.controllers.report;

import com.lezartistes.exceptions.ReportNotFoundException;
import com.lezartistes.facades.ReportFacade;
import com.lezartistes.models.Report;
import com.lezartistes.validation.InputControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class WriteReportController implements Initializable {

    @FXML
    TextField title;
    @FXML
    TextField general_description;

    @FXML
    TextField inspection_team;
    @FXML
    TextField necessary_means;
    @FXML
    TextField meteo;
    @FXML
    TextField ambient_temperature;
    @FXML
    DatePicker visit_date;
    @FXML
    TextField observation;
    @FXML
    TextField localisation;

    @FXML
    Button button1;
    @FXML
    Button button2;
    @FXML
    Button button3;

    private File file1;
    private File file2;
    private File file3;

    private ReportFacade reportFacade;
    private Report fromReport;

    public WriteReportController(int reportId){
        this.reportFacade = ReportFacade.getInstance();

        try {
            this.fromReport = this.reportFacade.getReportById(reportId);
            System.out.printf(this.fromReport.getVisit_date().toString());
        }
        catch (ReportNotFoundException e) {
            e.printStackTrace();
        }

    }

    public WriteReportController(Report report){
        this.reportFacade = ReportFacade.getInstance();
        this.fromReport = report;
        System.out.printf("init from report");
    }


    public  WriteReportController(){
        this.reportFacade = ReportFacade.getInstance();
    }

    public File chooseImage(){

        /*Create a new Stage*/
        Stage stage = new Stage();
        stage.setHeight(280);
        stage.setWidth(610);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);

        return file;
    }

    public void chooseImage1(ActionEvent actionEvent) {
        this.file1 = chooseImage();
        button1.setText("uploaded");

    }

    public void chooseImage2(ActionEvent actionEvent) {
        this.file2 = chooseImage();
        button2.setText("uploaded");
    }

    public void chooseImage3(ActionEvent actionEvent) {
        this.file3 = chooseImage();
        button3.setText("uploaded");
        System.out.println(this.file3.getName());
    }

    public void onSubmit(ActionEvent actionEvent){
        if(this.fromReport != null){
            System.out.printf("Update");
            this.updateReport();
        }
        else{
            System.out.printf("Create");
            this.createReport();
        }
    }
    public void updateReport() {
        boolean isValid = true;
        /*All the verification*/
        isValid = InputControl.isValidDouble(Double.valueOf(this.ambient_temperature.getText()), 0, 50);

        /*Create*/

        if (isValid) {
            /*
            LocalDate localDate = this.visit_date.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
             */

            this.fromReport.setTitle(this.title.getText());
            this.fromReport.setGeneral_description(this.general_description.getText());
            this.fromReport.setInspection_team(this.inspection_team.getText());
            this.fromReport.setNecessary_means(this.necessary_means.getText());
            this.fromReport.setMeteo(this.meteo.getText());
            this.fromReport.setObservation(this.observation.getText());
            this.fromReport.setLocation(this.localisation.getText());

            try {
                this.reportFacade.updateReport(this.fromReport);
            }
            catch (ReportNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void createReport(){
        boolean isValid = true;
        /*All the verification*/
        isValid = InputControl.isValidDouble(Double.valueOf(this.ambient_temperature.getText()),0,50);

        /*Create*/

        if(isValid){

            LocalDate localDate = this.visit_date.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);

            Report newReport = null;
            try {
                newReport = new Report(
                        this.title.getText(),
                        this.general_description.getText(),
                        true,
                        date,
                        this.inspection_team.getText(),
                        this.necessary_means.getText(),
                        this.meteo.getText(),
                        Double.valueOf(this.ambient_temperature.getText()),
                        this.localisation.getText(),
                        this.observation.getText(),
                        Report.fileToBytes(this.file1),
                        Report.fileToBytes(this.file2),
                        Report.fileToBytes(this.file3));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            try {
                this.reportFacade.createReport(newReport);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(this.fromReport != null){
            this.initializeFieldsFromReport();
        }
    }

    public void initializeFieldsFromReport(){

        this.title.setText(this.fromReport.getTitle());
        this.general_description.setText(this.fromReport.getGeneral_description());
        this.ambient_temperature.setText(String.valueOf(this.fromReport.getAmbient_temperature()));
        this.inspection_team.setText(this.fromReport.getInspection_team());
        this.necessary_means.setText(this.fromReport.getNecessary_means());
        this.meteo.setText(this.fromReport.getMeteo());
        this.localisation.setText(this.fromReport.getLocation());
        LocalDate localDate = Instant.ofEpochMilli((this.fromReport.getVisit_date()).getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        this.visit_date.setValue(localDate);
        this.observation.setText(this.fromReport.getObservation());
    }
}
