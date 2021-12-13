package com.lezartistes.database;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.UserDAO;
import com.lezartistes.dao.UserDAOPostgres;

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

}
