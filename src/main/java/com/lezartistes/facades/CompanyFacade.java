package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.company.CompanyDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.models.Company;

import java.util.List;

public class CompanyFacade {
    /*attributes*/
    private static CompanyFacade companyFacade;
    private final CompanyDAO companyDAO;

    /*constructeur*/
    private CompanyFacade(){
        AbstractFactory factory = PostgresFactory.getInstance();
        this.companyDAO = factory.createCompanyDAO();
    }

    /*methods*/
    public static CompanyFacade getInstance(){
        if(companyFacade == null){
            companyFacade = new CompanyFacade();
        }
        return companyFacade;
    }

    public Company getCompanyById(int idCompany) throws CompanyNotFoundException{
        return this.companyDAO.getCompanyById(idCompany);
    }

    public int getCompanyIdByName(String companyName) throws CompanyNotFoundException{
        return this.companyDAO.getCompanyIdByName(companyName);
    }

    public List<Company> getAllCompanies() throws CompanyNotFoundException{
        return this.companyDAO.getAllCompanies();
    }

    public int addCompany(Company company){
        return this.companyDAO.addCompany(company);
    }

    public int modifyCompany(int idCompany, Company company){
        return this.companyDAO.modifyCompany(idCompany, company);
    }

    public int deleteCompany(int idCompany){
        return this.companyDAO.deleteCompany(idCompany);
    }

}
