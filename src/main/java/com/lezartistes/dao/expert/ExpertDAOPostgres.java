package com.lezartistes.dao.expert;

import com.lezartistes.dao.ClientDAOPostgres;
import com.lezartistes.models.Client;
import com.lezartistes.models.Company;
import com.lezartistes.models.Expert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private Expert resultSetToClient(ResultSet rs) throws SQLException {

        return new Expert(
                rs.getString("mail"),
                rs.getString("password"),
                null,
                false);
    }


    @Override
    public List<Expert> getAllExperts() {
        return null;
    }

    @Override
    public Expert getExpertById(int id) {
        return null;
    }

    @Override
    public int createExpert(Expert expert) {
        return 0;
    }
}
