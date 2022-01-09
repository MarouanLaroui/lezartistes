package com.lezartistes.dao.expert;

import com.lezartistes.dao.company.CompanyDAO;
import com.lezartistes.dao.company.CompanyDAOPostgres;
import com.lezartistes.exceptions.CompanyNotFoundException;
import com.lezartistes.exceptions.ExpertNotFoundException;
import com.lezartistes.models.Company;
import com.lezartistes.models.Expert;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpertDAOPostgres extends ExpertDAO{

    /*attributes*/
    private static ExpertDAOPostgres expertDAOPostgres;
    private final Connection coToDB;

    private ExpertDAOPostgres(Connection connection){
        this.coToDB = connection;
    }


    public static ExpertDAOPostgres getInstance(Connection connection){
        if(expertDAOPostgres == null){
            expertDAOPostgres = new ExpertDAOPostgres(connection);
        }
        return expertDAOPostgres;
    }

    private Expert resultSetToExpert(ResultSet rs) throws SQLException {

        CompanyDAO companyDAO = CompanyDAOPostgres.getInstance(this.coToDB);
        Expert expert = null;
        try {
            Company expertCompany = companyDAO.getCompanyById(rs.getInt("id_company"));
            expert = new Expert(
                    rs.getString("mail"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    expertCompany,
                    false);
        }
        catch (CompanyNotFoundException e) {
            e.printStackTrace();
        }
        return expert;

    }


    @Override
    public List<Expert> getAllExperts() throws ExpertNotFoundException {
        String sqlSelect = "SELECT * FROM experts";
        List<Expert> experts = new ArrayList<>();

        try{
            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            ResultSet rs = pstatement.executeQuery();

            /*Transforme toutes les lignes en clients*/
            while(rs.next()){
                Expert expert = this.resultSetToExpert(rs);
                experts.add(expert);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(experts.isEmpty()){
            throw new ExpertNotFoundException();
        }
        return experts;

    }

    @Override
    public Expert getExpertById(int id) {
        return null;
    }

    @Override
    public int createExpert(Expert expert) {
        int affectRow = 0;
        try{
            String sqlInsert = "INSERT INTO clients(mail, password,name,surname,id_company) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = this.coToDB.prepareStatement(sqlInsert);
            pstmt.setString(1,expert.getMail());
            pstmt.setString(2,expert.getPassword());
            pstmt.setString(3,expert.getName());
            pstmt.setString(4,expert.getSurname());
            pstmt.setInt(5,expert.getCompanyId());
            int affectRows = pstmt.executeUpdate(sqlInsert);
            System.out.println(affectRows);


        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return affectRow;
    }

}
