package com.lezartistes.models;

public class Expert extends User{

    private Company company;

    /**
     * Constructor
     * @param mail
     * @param password
     * @param company
     */
    public Expert(String mail, String password, Company company) {
        super(mail, password);
        this.company = company;
    }

    /**
     * Getter and setter
     */
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
