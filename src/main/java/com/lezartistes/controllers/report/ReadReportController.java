package com.lezartistes.controllers.report;

import com.lezartistes.exceptions.ReportNotFoundException;
import com.lezartistes.facades.ClientFacade;
import com.lezartistes.facades.ReportFacade;
import com.lezartistes.models.Client;
import com.lezartistes.models.Report;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ReadReportController implements Initializable {

    private ReportFacade reportFacade;
    private Report report;
    private Stage stage;

    @FXML
    Label title;
    @FXML
    Label description;
    @FXML
    Label temperature;
    @FXML
    Label inspection_team;
    @FXML
    Label necessary_means;
    @FXML
    Label meteo;
    @FXML
    Label localisation;
    @FXML
    Label date;
    @FXML
    Label observation;
    @FXML
    ImageView img1;
    @FXML
    ImageView img2;
    @FXML
    ImageView img3;


    public ReadReportController(Report report, Stage stage){
        this.report = report;
        this.reportFacade = ReportFacade.getInstance();
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*Initialisation of all fields*/

        this.title.setText(this.report.getTitle());
        this.description.setText(this.report.getGeneral_description());
        this.temperature.setText(String.valueOf(this.report.getAmbient_temperature()));
        this.inspection_team.setText(this.report.getInspection_team());
        this.necessary_means.setText(this.report.getNecessary_means());
        this.meteo.setText(this.report.getMeteo());
        this.localisation.setText(this.report.getLocation());
        this.observation.setText(this.report.getObservation());
        this.date.setText(this.report.getVisit_date().toString());

        this.img1.setImage(new Image(new ByteArrayInputStream(this.report.getImg1())));
        this.img2.setImage(new Image(new ByteArrayInputStream(this.report.getImg2())));
        this.img3.setImage(new Image(new ByteArrayInputStream(this.report.getImg3())));
    }
}
