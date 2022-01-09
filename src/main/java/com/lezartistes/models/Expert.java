package com.lezartistes.models;

public class Expert extends User {
    private String name;
    private String surname;
    private Company company;

    /**
     * Constructor
     * @param mail
     * @param password
     * @param company
     * @param needEncrypt On veut savoir s'il faut encrypter notre mot de passe ou pas
     */
    public Expert(String mail, String password, String name, String surname, Company company, boolean needEncrypt) {
        super(mail, password, needEncrypt);
        this.company = company;
        this.name = name;
        this.surname = surname;
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
}
