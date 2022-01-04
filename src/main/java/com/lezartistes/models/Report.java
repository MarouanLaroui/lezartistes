package com.lezartistes.models;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class Report {

    private int id;
    private String title;
    private String general_description;
    private boolean isPosted;
    private Date visit_date;
    private String inspection_team;
    private String necessary_means;
    private String meteo;
    private double ambient_temperature;
    private String location;
    private String observation;
    private byte[] img1;
    private byte[] img2;
    private byte[] img3;

    public Report(String title,
                  String general_description,
                  boolean isPosted,
                  Date visit_date,
                  String inspection_team,
                  String necessary_means,
                  String meteo,
                  double ambient_temperature,
                  String location,
                  String observation,
                  byte[] img1,
                  byte[] img2,
                  byte[] img3){
        this.title = title;
        this.general_description = general_description;
        this.isPosted = isPosted;
        this.visit_date = visit_date;
        this.inspection_team = inspection_team;
        this.necessary_means = necessary_means;
        this.meteo = meteo;
        this.ambient_temperature = ambient_temperature;
        this.observation = observation;
        this.location = location;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
    }

    public static byte[] fileToBytes(File file) throws IOException {
        // Creating an object of FileInputStream to
        // read from a file
        FileInputStream fl = new FileInputStream(file);

        // Now creating byte array of same length as file
        byte[] arr = new byte[(int)file.length()];

        // Reading file content to byte array
        // using standard read() method
        fl.read(arr);

        // lastly closing an instance of file input stream
        // to avoid memory leakage
        fl.close();

        // Returning above byte array
        return arr;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getGeneral_description() {
        return general_description;
    }

    public void setGeneral_description(String general_description) {
        this.general_description = general_description;
    }

    public boolean isPosted() {
        return isPosted;
    }

    public void setPosted(boolean posted) {
        isPosted = posted;
    }

    public Date getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(Date visit_date) {
        this.visit_date = visit_date;
    }

    public String getInspection_team() {
        return inspection_team;
    }

    public void setInspection_team(String inspection_team) {
        this.inspection_team = inspection_team;
    }

    public String getNecessary_means() {
        return necessary_means;
    }

    public void setNecessary_means(String necessary_means) {
        this.necessary_means = necessary_means;
    }

    public String getMeteo() {
        return meteo;
    }

    public void setMeteo(String meteo) {
        this.meteo = meteo;
    }

    public double getAmbient_temperature() {
        return ambient_temperature;
    }

    public void setAmbient_temperature(double ambient_temperature) {
        this.ambient_temperature = ambient_temperature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public byte[] getImg1() {
        return img1;
    }

    public void setImg1(byte[] img1) {
        this.img1 = img1;
    }

    public byte[] getImg2() {
        return img2;
    }

    public void setImg2(byte[] img1) {
        this.img2 = img2;
    }

    public byte[] getImg3() {
        return img3;
    }

    public void setImg3(byte[] img1) {
        this.img3 = img3;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }


}
