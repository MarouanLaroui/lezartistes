package com.lezartistes.controllers.report;

import com.lezartistes.facades.ReportFacade;
import com.lezartistes.models.Report;
import com.lezartistes.validation.InputControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class WriteReportController {

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

    public void createReport(ActionEvent actionEvent) {
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
                        this.title.getText(), this.general_description.getText(), true, date, this.inspection_team.getText(), this.necessary_means.getText(), this.meteo.getText(), Double.valueOf(this.ambient_temperature.getText()), this.localisation.getText(), this.observation.getText(),Report.fileToBytes(this.file1), Report.fileToBytes(this.file2),Report.fileToBytes(this.file3));
            } catch (IOException e) {
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
}
