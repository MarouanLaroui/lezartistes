package com.lezartistes.dao.serviceProvider;

import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.exceptions.UserNotFoundException;
import com.lezartistes.models.Client;
import com.lezartistes.models.Expert;

import java.sql.*;

public class ServiceProviderDAOPostgres extends ServiceProviderDAO {

    /*Attributes*/
    private static ServiceProviderDAOPostgres spDAOpg = null;
    private final Connection coToDB;

    /*constructor*/
    private ServiceProviderDAOPostgres(Connection c) {this.coToDB = c;}

    /*methods*/
    public static ServiceProviderDAOPostgres getInstance(Connection connection) {
        if (spDAOpg == null)
            spDAOpg = new ServiceProviderDAOPostgres(connection);
        return spDAOpg;
    }

    @Override
    public int createServiceProvider(Expert e) {
        int affectRows = 0;
        try {
            String sqlInsert = "INSERT INTO serviceProviders(username, password, id_company) VALUES (?, ?, ?)";

            PreparedStatement stmtprep = this.coToDB.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stmtprep.setString(1, e.getMail());
            stmtprep.setString(2, e.getPassword());
            stmtprep.setInt(3, e.getCompanyId());

            affectRows = stmtprep.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return affectRows;
    }

    @Override
    public Expert getServiceProviderByEmail(String email) {
        String sqlSelect = "SELECT * FROM serviceproviders WHERE username=?";
        Expert expert;
        try {
            /*Requête select sur la base de donnée*/

            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setString(1, email);

            ResultSet rs = pstatement.executeQuery();

            /*Renvoie le client si trouvé, exception sinon*/
            if(rs.next())
                expert = this.resultSetToExpert(rs);
            else
                throw new UserNotFoundException(email);
        }
        catch (SQLException | UserNotFoundException e) {
            e.printStackTrace();
            expert = null;
        }
        return expert;
    }

    private Expert resultSetToExpert(ResultSet rs) throws SQLException {
        return null;
        //return new Expert(rs.getString("username"), rs.getString("password"), null, false);
        //TODO : On se dit qu'on ne l'appel que si les données viennent depuis la base de données donc pas d'encryptage de mot de passe
    }
}
