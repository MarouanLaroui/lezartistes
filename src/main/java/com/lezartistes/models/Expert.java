package com.lezartistes.models;

public class Expert extends User {

    private Company company;

    /**
     * Constructor
     * @param mail
     * @param password
     * @param company
     * @param needEncrypt On veut savoir s'il faut encrypter notre mot de passe ou pas
     */
    public Expert(String mail, String password, Company company, boolean needEncrypt) {
        super(mail, password, needEncrypt);
        this.company = company;
    }

    /**
     * Getter and setter
     */
    public Company getCompany() {
        return company;
    }

    public int getCompanyId() {
        return this.company.getId_company();
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
