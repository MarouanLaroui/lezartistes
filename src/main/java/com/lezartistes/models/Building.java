package com.lezartistes.models;

import java.util.Date;

public class Building {
    private int id;
    private String name;
    private String region;
    private double budget;
    private Date construction_date;
    private String master_builder;
    private String design_office;
    private int client;

    public Building(String name, String region, double budget, Date construction_date, String master_builder, String design_office, int client) {
        this.name = name;
        this.region = region;
        this.budget = budget;
        this.construction_date = construction_date;
        this.master_builder = master_builder;
        this.design_office = design_office;
        this.client=client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Date getConstruction_date() {
        return construction_date;
    }

    public void setConstruction_date(Date construction_date) {
        this.construction_date = construction_date;
    }

    public String getMaster_builder() {
        return master_builder;
    }

    public void setMaster_builder(String master_builder) {
        this.master_builder = master_builder;
    }

    public String getDesign_office() {
        return design_office;
    }

    public void setDesign_office(String design_office) {
        this.design_office = design_office;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }
}
