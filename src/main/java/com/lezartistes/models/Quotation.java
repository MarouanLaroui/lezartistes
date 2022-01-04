package com.lezartistes.models;

public class Quotation {
    private Company company;
    private String title;
    private Expert expert;
    private double capital;
    private int siret_number;
    private int number_business_register;
    private int NAF;
    private double total_price_TTC;

    public Quotation(Company company, String title, Expert expert, double capital, int siret_number, int number_business_register, int NAF, double total_price_TTC) {
        this.company = company;
        this.title = title;
        this.expert = expert;
        this.capital = capital;
        this.siret_number = siret_number;
        this.number_business_register = number_business_register;
        this.NAF = NAF;
        this.total_price_TTC = total_price_TTC;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public int getSiret_number() {
        return siret_number;
    }

    public void setSiret_number(int siret_number) {
        this.siret_number = siret_number;
    }

    public int getNumber_business_register() {
        return number_business_register;
    }

    public void setNumber_business_register(int number_business_register) {
        this.number_business_register = number_business_register;
    }

    public int getNAF() {
        return NAF;
    }

    public void setNAF(int NAF) {
        this.NAF = NAF;
    }

    public double getTotal_price_TTC() {
        return total_price_TTC;
    }

    public void setTotal_price_TTC(double total_price_TTC) {
        this.total_price_TTC = total_price_TTC;
    }
}
