package com.lezartistes.models;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

public class CallForProposal {
    private int idCFP;
    private String title;
    private String general_description;
    private byte[] signature;

    //l'idReport vaut -1 s'il n'y a pas encore de rapport associé
    private int idReport;
    private int idClientAuthor;
    private Status status;
    private int building;

    /**
     * Constructor
     * @param title
     * @param general_description
     * @param imgSignature
     * @param idReport
     * @param idClient
     */
    public CallForProposal(String title, String general_description, byte[] imgSignature, int idReport, int idClient, int building){
        this.title = title;
        this.general_description = general_description;
        this.signature = imgSignature;
        this.status = Status.DRAFT;
        this.idReport = -1;
        this.idClientAuthor = idClient;
        this.building = building;
    }

    /*toString*/
    //todo: voir si on a besoin de rajouter des choses dans le toString
    public String toString(){
        return this.title + " (" + this.status + ") ";
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

    /*Getter et setters*/
    public int getId(){
        return this.idCFP;
    }

    public void setId(int id){
        this.idCFP = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGeneral_description() {
        return general_description;
    }

    public void setGeneral_description(String general_description) {
        this.general_description = general_description;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public int getIdClientAuthor() {
        return idClientAuthor;
    }

    public void setIdClientAuthor(int idClientAuthor) {
        this.idClientAuthor = idClientAuthor;
    }

    public String getStatus() {
        return status.name();
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status.toUpperCase().trim());
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }
}
