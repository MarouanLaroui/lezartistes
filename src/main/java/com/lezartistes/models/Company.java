package com.lezartistes.models;

public class Company {
    private String name;
    private String departement;
    private String street;
    private String city;
    private String complement;
    private int postal_code;

    /**
     * Constructor
     * @param name
     * @param departement
     * @param street
     * @param city
     * @param complement
     * @param postal_code
     */
    public Company(String name, String departement, String street, String city, String complement, int postal_code) {
        this.name = name;
        this.departement = departement;
        this.street = street;
        this.city = city;
        this.complement = complement;
        this.postal_code = postal_code;
    }

    /**
     * Getter and setter
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }
}
