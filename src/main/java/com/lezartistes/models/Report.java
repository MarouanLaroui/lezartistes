package com.lezartistes.models;

import java.io.File;
import java.util.Date;

public class Report {

    private String title;
    private String general_description;
    private boolean isPosted;
    private Date visit_date;
    private String[] inspection_team;
    private String necessary_means;
    private String meteo;
    private double ambient_temperature;
    /*Pas trop sûr de ça ???*/
    private File file;

    public Report(String title,
                  String general_description,
                  boolean isPosted,
                  Date visit_date,
                  String[] inspection_team,
                  String necessary_means,
                  String meteo,
                  double ambient_temperature){
        this.title = title;
        this.general_description = general_description;
        this.isPosted = isPosted;
        this.visit_date = visit_date;
        this.inspection_team = inspection_team;
        this.necessary_means = necessary_means;
        this.meteo = meteo;
        this.ambient_temperature = ambient_temperature;
    }
}
