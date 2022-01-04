package com.lezartistes.models;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class User {

    /*attributes*/
    private String mail;
    private String password;

    private static final String key = "SE";

    /*constructor*/
    public User(String mail, String password, boolean needEncrypt) {
        this.mail = mail;
        if (needEncrypt)
            this.password = this.encrypt(password);
        else
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

    public String encrypt(String password){
        try {
            Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"),"Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, clef);
            return new String(cipher.doFinal(password.getBytes()));
        } catch (Exception e) {
            return null;
        }
    }
}
