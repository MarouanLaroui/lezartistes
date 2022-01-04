package com.lezartistes.dao.company;

import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.models.Company;

import java.io.Serializable;
import java.util.List;

public abstract class CompanyDAO implements Serializable {
    //constructor
    public CompanyDAO(){super();}

    //methods
    public abstract Company getCompanyById(int idCompany) throws CompanyNotFoundException;
    public abstract int getCompanyIdByName(String companyName) throws CompanyNotFoundException;
    public abstract List<Company> getAllCompanies() throws CompanyNotFoundException;
    public abstract int addCompany(Company company);
    public abstract int modifyCompany(int idCompany, Company company);
    public abstract int deleteCompany(int idCompany);
}
