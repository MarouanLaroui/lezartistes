package com.lezartistes.database;

import com.lezartistes.dao.*;
import com.lezartistes.dao.feedback.FeedbackDAO;
import com.lezartistes.dao.feedback.FeedbackDAOPostgres;
import java.sql.Connection;

public class PostgresFactory implements AbstractFactory {

    /*attributes*/
    private static PostgresFactory postgresFactory;
    private static Connection connection;

    //Ajouter le connecteur
    private PostgresFactory() {}

    /**
     * @return an instance of PostgresFactory
     */
    public static PostgresFactory getInstance(){
        if(postgresFactory == null){
            postgresFactory = new PostgresFactory();
            connection = ConnectPostgresSQL.getInstance();
        }
        return postgresFactory;
    }

    public UserDAO createUserDAO() {
        return UserDAOPostgres.getInstance(connection);
    }
    public FeedbackDAO createFeedbackDAO() {
        return FeedbackDAOPostgres.getInstance(connection);
    }

    public ClientDAO createClientDAO(){ return ClientDAOPostgres.getInstance(connection);}

    public ReportDAO createReportDAO(){return ReportDAOPostgres.getInstance(connection);}

}
