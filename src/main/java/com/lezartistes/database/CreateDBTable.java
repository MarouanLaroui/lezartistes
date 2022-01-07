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

    /* --------------- CREATE TABLE INTO DATABASE METHODS --------------- */

    //TODO mettre des autoincremental key partout
    public void createUserTable(){
        Connection connection = ConnectPostgresSQL.getInstance();

        try{
            Statement stmt = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS users;"+
                    "CREATE TABLE users " +
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

            String sql = "DROP TABLE IF EXISTS reports;" +
                    "CREATE TABLE reports " +
                    "(id SERIAL PRIMARY KEY," +
                    " title VARCHAR(50)," +
                    " general_description VARCHAR(150)," +
                    "is_posted BOOLEAN,"+
                    "visit_date DATE,"+
                    "inspection_team VARCHAR(150),"+
                    "necessary_means VARCHAR(150)," +
                    "meteo VARCHAR(50)," +
                    "ambient_temperature DOUBLE PRECISION," +
                    "location VARCHAR(50)," +
                    "observation VARCHAR(150)," +
                    "file1 bytea,"+
                    "file2 bytea,"+
                    "file3 bytea)";
            stmt.execute(sql);
            System.out.println("Created table in given database...");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createBuildingTable(){
        try{
            Statement stmt = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS buildings;" +
                    "CREATE TABLE buildings " +
                    "(id_building SERIAL PRIMARY KEY," +
                    " name VARCHAR(50)," +
                    " region VARCHAR(50)," +
                    " budget double precision," +
                    " construction_date DATE ," +
                    " master_building VARCHAR(50)," +
                    " construction_company VARCHAR(50),"+
                    " design_office VARCHAR(50)) ";
            stmt.execute(sql);
            System.out.println("Table Building created ");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createQuotationTable(){
        try{
            Statement stmt = connection.createStatement();


            String sql = "CREATE TABLE quotations "+
                    "(idQuotation serial primary key, "+
                    "idCompany int, "+
                    "capital float,"+
                    "siret_number varchar(50),"+
                    "number_business_register varchar(50), "+
                    "NAF varchar(50),"+
                    "total_price_ttc float, "+
                    "callforproposal int,"+
                    "constraint idCompany foreign key(idCompany) references companies(idCompany))";
            stmt.execute(sql);
            System.out.println("Table Quotation created");
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public void createClientTable(){
        try{
            Statement stmt = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS clients;" +
                    "CREATE TABLE clients " +
                    "(id_clients SERIAL PRIMARY KEY," +
                    " username VARCHAR(50), " +
                    " password VARCHAR(300), " +
                    " name VARCHAR(50)," +
                    " surname VARCHAR(50)," +
                    " street VARCHAR(50)," +
                    " complement VARCHAR(150)," +
                    " city VARCHAR(50)," +
                    " postal_code INT,"+
                    " phone_number INT)";
            stmt.execute(sql);
            System.out.println("Created table in given database...");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createServiceProvider() {
        try {
            Statement stmt = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS serviceProviders;" +
                    "CREATE TABLE serviceProviders" +
                    "(id_sp SERIAL PRIMARY KEY, " +
                    " username VARCHAR(50), " +
                    " password VARCHAR(300) " +
                    " )";

            stmt.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createFeedbackTable(){
        try{
            Statement stmt = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS feedbacks;"+
                    "CREATE TABLE feedbacks " +
                    "(idFeedback SERIAL PRIMARY KEY," +
                    " rating INT,"+
                    " comment VARCHAR(50)," +
                    " companyFeedback INT,"+
                    " constraint id_company foreign key(companyFeedback) references companies(idCompany));";
            stmt.execute(sql);
            System.out.println("Created feedbacks table in given database...");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createCompanyTable(){
        try{
            Statement stmt = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS companies;"+
                    "CREATE TABLE companies " +
                    "(idCompany SERIAL PRIMARY KEY," +
                    " companyName VARCHAR(50),"+
                    " companyDepartement VARCHAR(50)," +
                    " companyCity VARCHAR(30),"+
                    " companyStreet VARCHAR(30),"+
                    " companyComplement VARCHAR(30),"+
                    " companyPostalCode INT)";
            stmt.execute(sql);
            System.out.println("Created companies table in given database...");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createCallForProposalTable(){
        try{
            Statement stmt = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS callForProposals;"+
                    "CREATE TABLE callForProposals " +
                    "(idCFP SERIAL PRIMARY KEY," +
                    " title VARCHAR(100),"+
                    " general_description VARCHAR(300)," +
                    " imgSignature bytea," +
                    " report INT," +
                    " author INT, " +
                    " status VARCHAR(30)," +
                    " building INT," +
                    " FOREIGN KEY (report) REFERENCES reports(id)," +
                    " FOREIGN KEY (author) REFERENCES clients(id_clients)," +
                    " FOREIGN KEY (building) REFERENCES buildings(id_building))";
            stmt.execute(sql);
            System.out.println("Created callForProposals table in given database...");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void creteHistoryTable() {
        try{
            Statement stmt = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS histories;" +
                    "CREATE TABLE histories " +
                    "(idHistory SERIAL PRIMARY KEY," +
                    "date DATE, " +
                    "description VARCHAR(1000)," +
                    "relatedBuilding INT," +
                    "FOREIGN KEY (relatedBuilding) REFERENCES buildings(id_building));";
            stmt.execute(sql);
            System.out.println("Created table in given database...");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /* --------------- INSERTION INTO DATABASE METHODS --------------- */
    public void insertIntoClientTable(){

        try{

            Statement stmt = this.connection.createStatement();
            String sqlInsert = "INSERT INTO clients(username, password, name, surname, street, complement, city, postal_code, phone_number) VALUES ('marouanLaouri@gmail.com', '123456', 'marouan','laroui','6 rue de la palissade','Batiment A','Montpellier',34000,0658003255)";
            int affectRows = stmt.executeUpdate(sqlInsert);
            sqlInsert = "INSERT INTO clients(username, password, name, surname, street, complement, city, postal_code, phone_number) VALUES ('marouanLaouri@gmail.com', '123456', 'Surement pas marouan','laroui','6 rue de la palissade','Batiment A','Montpellier',34000,0658003255)";
            stmt.executeUpdate(sqlInsert);
            sqlInsert = "INSERT INTO clients(username, password, name, surname, street, complement, city, postal_code, phone_number) VALUES ('marouanLaouri@gmail.com', '123456', 'Julien','laroui','6 rue de la palissade','Batiment A','Montpellier',34000,0658003255)";
            stmt.executeUpdate(sqlInsert);
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

    public void insertIntoCompanyTable(){
        try{

            Statement stmt = this.connection.createStatement();
            String sqlInsert = "INSERT INTO companies(companyname, companydepartement, companycity, companystreet, companycomplement, companypostalcode) " +
                    "VALUES ('Alpes Contrôle', 'Ouvrages d`art', 'Perpignan','Rue de la Palissade','pouet',34000)";
            int affectRows = stmt.executeUpdate(sqlInsert);
            sqlInsert = "INSERT INTO companies(companyname, companydepartement, companycity, companystreet, companycomplement, companypostalcode) " +
                    "VALUES ('Polytech', 'IG', 'Montpellier','Rue du Truel','Ingéniérie',34000)";
            stmt.executeUpdate(sqlInsert);
            /*sqlInsert = "INSERT INTO companies(companyname, companydepartement, companycity, companystreet, companycomplement, companypostalcode) " +
                    "VALUES ('Alpes Contrôle', 'Ouvrages d`art', 'Perpignan','Rue de la Palissade','pouet',34000)";
            stmt.executeUpdate(sqlInsert);*/

            System.out.println("finish");
            System.out.println(affectRows);


        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertIntoFeedbackTable(){
        try{

            Statement stmt = this.connection.createStatement();
            String sqlInsert = "INSERT INTO feedbacks(rating, comment, companyfeedback) " +
                    "VALUES (5, 'Très bien accueilli, très professionnel', 1)";
            int affectRows = stmt.executeUpdate(sqlInsert);
            sqlInsert = "INSERT INTO feedbacks(rating, comment, companyfeedback) " +
                    "VALUES (4, 'Bien, ravi du travail réalisé', 1)";
            stmt.executeUpdate(sqlInsert);

            System.out.println("finish");
            System.out.println(affectRows);


        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {

        CreateDBTable cTable = new CreateDBTable();
        //cTable.insertIntoFeedbackTable();
        //cTable.createCompanyTable();
        //cTable.insertIntoCompanyTable();
        //cTable.createFeedbackTable();
        //cTable.createReportTable();
        //cTable.createUserTable();
        //cTable.createClientTable();
        cTable.createCallForProposalTable();

        //cTable.insertIntoClientTable();
        //cTable.createQuotationTable();
        //cTable.createBuildingTable();

        //cTable.createServiceProvider();
        //cTable.creteHistoryTable();


    }
}
