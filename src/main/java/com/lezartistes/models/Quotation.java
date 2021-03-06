package com.lezartistes.models;

public class Quotation {
    private int id;
    private int company;
    private String title;
    private String expert;
    private double capital;
    private int siret_number;
    private int number_business_register;
    private int NAF;
    private double total_price_TTC;
    private int callforproposal;
    private StatusQuotation statusQuotation;

    public Quotation(int company, String title, String expert, double capital, int siret_number, int number_business_register, int NAF, double total_price_TTC, int callforproposal) {
        this.company = company;
        this.title = title;
        this.expert = expert;
        this.capital = capital;
        this.siret_number = siret_number;
        this.number_business_register = number_business_register;
        this.NAF = NAF;
        this.total_price_TTC = total_price_TTC;
        this.callforproposal=callforproposal;
        this.statusQuotation=StatusQuotation.PROPOSE;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }

    public int getcallforproposal() {
        return callforproposal;
    }

    public void setcallforproposal(int cap) {
        this.callforproposal = cap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
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

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getStatusQuotation(){return this.statusQuotation.name();}
    public void setStatusQuotation(String status){this.statusQuotation = StatusQuotation.valueOf(status.toUpperCase().trim());}

}
