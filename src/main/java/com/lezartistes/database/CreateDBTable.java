package com.lezartistes.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDBTable {
    /**
     * Used to generate the table in the database
     */
    private Connection connection;

    public CreateDBTable(){
        this.connection = ConnectPostgresSQL.getInstance();
    }
    //TODO mettre des autoincremental key partout
    public void createUserTable(){
        Connection connection = ConnectPostgresSQL.getInstance();

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

    }

    public void createReportTable(){
        try{
            Statement stmt = connection.createStatement();

            String sql = "CREATE TABLE reports " +
                    "(id SERIAL PRIMARY KEY," +
                    " title VARCHAR(50)," +
                    " general_description VARCHAR(150)," +
                    " mail VARCHAR(50)," +
                    " necessary_means VARCHAR(150)," +
                    " meteo VARCHAR(50)," +
                    " ambient_temperature VARCHAR(50))";
            stmt.execute(sql);
            System.out.println("Created table in given database...");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createClientTable(){
        try{
            Statement stmt = connection.createStatement();

            String sql = "CREATE TABLE clients " +
                    "(id SERIAL PRIMARY KEY," +
                    " name VARCHAR(50)," +
                    " surname VARCHAR(50)," +
                    " street VARCHAR(50)," +
                    " complement VARCHAR(150)," +
                    " city VARCHAR(50)," +
                    " postal_code INT,"+
                    " phone_number INT)";
            stmt.execute(sql);
            System.out.println("Created table in given database...");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertIntoClientTable(){
        try{
            Statement stmt = this.connection.createStatement();

            String sqlInsert = "INSERT INTO clients VALUES ('marouan','laroui','6 rue de la palissade','Batiment A','Montpellier',34000,0658003255)";
            int affectRows = stmt.executeUpdate(sqlInsert);
            /*
            while(rs.next()){
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }
             */
            System.out.println("finish");
            System.out.println(affectRows);


        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void main(String[] args) {

        CreateDBTable cTable = new CreateDBTable();
        //cTable.createUserTable();
        //cTable.createClientTable();
        cTable.insertIntoClientTable();

    }
}
