package com.lezartistes.models;

public class User {

    /*attributes*/
    private String mail;
    private String password;

    /*constructor*/
    public User(String mail, String password){
        this.mail = mail;
        this.password = password;
    }
    /*getters et setters*/
    public String getMail(){
        return this.mail;
    }

    public String getPassword(){
        return this.password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
