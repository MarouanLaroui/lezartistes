package com.lezartistes.models;

import java.sql.Date;

public class History {

    private int idBuilding;
    private Date date;
    private String description;

    public History(int idBuilding, Date date, String description) {
        this.idBuilding = idBuilding;
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return "History{" +
                "idBuilding=" + idBuilding +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }

    public int getIdBuilding() {
        return idBuilding;
    }

    public void setIdBuilding(int idBuilding) {
        this.idBuilding = idBuilding;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
