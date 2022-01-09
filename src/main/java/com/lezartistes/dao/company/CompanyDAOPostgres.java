package com.lezartistes.dao.company;

import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.models.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOPostgres extends CompanyDAO {
    /**
     * feedbackDaoPostgres for the Singleton Pattern
     */
    private static CompanyDAOPostgres companyDaoPostgres;

    /**
     * Connection to the database
     */
    private final Connection connection;

    /**
     * Constructor : create a CompanyDAOPostgres
     * @param connection connection to the DB
     */
    public CompanyDAOPostgres(Connection connection){
        super();
        this.connection = connection;
    }

    /**
     * Method for the singleton pattern
     * @param connection connection to the DB
     * @return an only instance of CompanyDAOPostgres
     */
    public static CompanyDAO getInstance(Connection connection){
        if (companyDaoPostgres == null){
            companyDaoPostgres = new CompanyDAOPostgres(connection);
        }
        return companyDaoPostgres;
    }

    /*methods*/
    public Company resultSetToCompany(ResultSet rs) throws SQLException {
        return new Company(
                rs.getInt("idcompany"),
                rs.getString("companyname"),
                rs.getString("companydepartement"),
                rs.getString("companycity"),
                rs.getString("companystreet"),
                rs.getString("companycomplement"),
                rs.getInt("companypostalcode")
        );
    }

    @Override
    public Company getCompanyById(int idCompany) throws CompanyNotFoundException {
        String sqlSelect = "SELECT * FROM companies WHERE idcompany=?";

        PreparedStatement pstatement = null;
        try {
            pstatement = this.connection.prepareStatement(sqlSelect);
            pstatement.setInt(1,idCompany);
            ResultSet resultSet = pstatement.executeQuery();

            if(resultSet.next()){
                return this.resultSetToCompany(resultSet);
            }
            else{
                throw new CompanyNotFoundException(idCompany);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public int getCompanyIdByName(String companyName) throws CompanyNotFoundException {
        String sqlSelect = "SELECT idcompany FROM companies WHERE companyname=?";
        int idCompany = -1;

        try{
            PreparedStatement pstatement = this.connection.prepareStatement(sqlSelect);
            pstatement.setString(1, companyName);
            ResultSet resultSet = pstatement.executeQuery();

            if(resultSet.next())
                idCompany = resultSet.getInt("idcompany");
            else
                throw new CompanyNotFoundException(companyName);
        }
        catch (SQLException | CompanyNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return idCompany;
    }

    @Override
    public List<Company> getAllCompanies() throws CompanyNotFoundException {
        String sqlSelect = "SELECT * FROM companies";
        List<Company> companies = new ArrayList<>();

        try{
            PreparedStatement pstatement = this.connection.prepareStatement(sqlSelect);
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en company*/
            while(resultSet.next()){
                Company company = resultSetToCompany(resultSet);
                companies.add(company);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(companies.isEmpty()){
            throw new CompanyNotFoundException();
        }
        return companies;
    }

    @Override
    public int addCompany(Company company) {
        //TODO: Remplacer par des autoincrémentales keys
        int affectRows = 0;
        String sqlInsert = "INSERT INTO companies(companyname, companydepartement, companycity, " +
                "companystreet, companycomplement, companypostalcode) " +
                "VALUES (?,?,?,?,?,?)";
        try{
            PreparedStatement pstmt = this.connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,company.getName());
            pstmt.setString(2,company.getDepartement());
            pstmt.setString(3,company.getCity());
            pstmt.setString(4,company.getStreet());
            pstmt.setString(5,company.getComplement());
            pstmt.setInt(6,company.getPostal_code());

            affectRows = pstmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    }

    @Override
    public int modifyCompany(int idCompany, Company company) {
        int affectRows = 0;
        String sqlUpdate = "UPDATE companies SET " +
                "companyname = ?, " +
                "companydepartement = ?, " +
                "companycity = ?, " +
                "companystreet = ?, " +
                "companycomplement = ?, " +
                "companypostalcode = ? "+
                "WHERE idcompany = ?";
        try{
            PreparedStatement pstmt = this.connection.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,company.getName());
            pstmt.setString(2,company.getDepartement());
            pstmt.setString(3,company.getCity());
            pstmt.setString(4,company.getStreet());
            pstmt.setString(5,company.getComplement());
            pstmt.setInt(6,company.getPostal_code());
            pstmt.setInt(7, idCompany);


            affectRows = pstmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    }

    @Override
    public int deleteCompany(int idCompany) {
        //TODO: vérifier qu'une company n'est pas affectée à des utilisateurs
        int affectRows = 0;
        String sqlDelete = "DELETE FROM companies WHERE idcompany=?";
        try{
            PreparedStatement pstmt = this.connection.prepareStatement(sqlDelete, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, idCompany);

            affectRows = pstmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    }
}
