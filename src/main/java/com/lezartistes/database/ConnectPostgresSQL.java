package com.lezartistes.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectPostgresSQL {

    /*attributes*/
    /**
     * Information used to connect with the database
     */
    private static final String url = "jdbc:postgresql://ec2-52-211-233-176.eu-west-1.compute.amazonaws.com:5432/d7948nm67t2sok";
    /**
     * Information used to connect with the database
     */
    private static final String user = "vpwizczpoibmoh";
    /**
     * Information used to connect with the database
     */
    private static final String password = "859ff41408e49832141041d08bc1c931f5436f7e37fe9a8f8b6ebcbccdb97688";
    /**
     * Information used to connect with the database
     */
    private static Connection connect = null;

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public static Connection getInstance() {
        if(connect == null){
            try {
                connect = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the PostgreSQL server successfully.");
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return connect;
    }
}
