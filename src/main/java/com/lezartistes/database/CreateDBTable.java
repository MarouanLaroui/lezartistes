package com.lezartistes.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDBTable {
    /**
     * Used to generate the table in the database
     */
    public static void main(String[] args) {
        Connection connection = ConnectPostgresSQL.getInstance();
        /*
        try{
            Statement stmt = connection.createStatement();

            String sql = "CREATE TABLE users " +
                    "(id SERIAL PRIMARY KEY," +
                    " mail VARCHAR(50)," +
                    " password VARCHAR(50))";
            stmt.execute(sql);
            System.out.println("Created table in given database...");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
         */

        try{
            Statement stmt = connection.createStatement();

            String sql = "Select * from users";
            String sqlInsert = "INSERT INTO users VALUES (3,'marouanlaroui2000@gmail.com','pasword')";
            //stmt.executeUpdate(sqlInsert);

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }
            System.out.println("finish");


        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
