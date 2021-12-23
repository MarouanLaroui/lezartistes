package com.lezartistes.dao;
import com.lezartistes.models.User;

import java.sql.*;

/*Singleton pattern*/
public class UserDAOPostgres extends UserDAO {

    /*attributs*/
    /**
     * userDaoPostgres is used for Singleton Pattern
     */
    private static UserDAOPostgres userDaoPostgres;

    /**
     * coToDB is the connection to the database
     */
    private Connection coToDB;

    /*constructor*/
    private UserDAOPostgres(Connection connection) {
        super();
        this.coToDB = connection;
    }

    /*methods*/

    /**
     * Check if already instanciated, if not create an instance. Then return instance
     * @param connection is the connection with the database (Postgres)
     * @return the instance of UserDAOPostgres
     */
    public static UserDAOPostgres getInstance(Connection connection){
        if(userDaoPostgres == null){
            userDaoPostgres = new UserDAOPostgres(connection);
        }
        return userDaoPostgres;
    }

    /**
     * Query the DB for a user with the mail, returns null if not found
     * @param mail: String is the email of the searched user
     * @return the user with the corresponding email
     */
    public User getUserByMail(String mail){

        try {
            PreparedStatement pstatement = this.coToDB.prepareStatement("SELECT * FROM users WHERE mail = ?");
            pstatement.setString(1,mail);
            ResultSet resultSet = pstatement.executeQuery();

            //Return info the info back
            if(resultSet.next()) {
                return new User(resultSet.getString(2),resultSet.getString(3), false);
            }
            else {
                return null;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}