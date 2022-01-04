package com.lezartistes.models;

public class Client extends User {
    /*attributes*/
    private String name;
    private String surname;
    private String street;
    private String complement;
    private String city;
    private int postal_code;
    private int phone_number;

    /**
     * Constructors
     * */
    public Client(String mail, String password, boolean needEncrypt){
        super(mail, password, needEncrypt);
    }

    public Client(String mail, String password, String name, String surname, String street,
                  String complement, String city, int postal_code, int phone_number, boolean needEncrypt){
        this(mail, password, needEncrypt);
        this.name = name;
        this.surname = surname;
        this.street = street;
        this.complement = complement;
        this.city = city;
        this.postal_code = postal_code;
        this.phone_number = phone_number;
    }

    /**
     * Getters and setters
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }
}
