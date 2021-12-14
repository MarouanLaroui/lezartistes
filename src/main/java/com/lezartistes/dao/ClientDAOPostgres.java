package com.lezartistes.dao;

import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.models.Client;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOPostgres extends ClientDAO{

    /*attributes*/
    private static ClientDAOPostgres clientDAOPostgres;
    private Connection coToDB;

    /*constructor*/
    private ClientDAOPostgres(Connection connection){
        this.coToDB = connection;
    }

    /*methods*/
    public static ClientDAOPostgres getInstance(Connection connection){
        if(clientDAOPostgres == null){
            clientDAOPostgres = new ClientDAOPostgres(connection);
        }
        return clientDAOPostgres;
    }

    private Client resultSetToClient(ResultSet rs) throws SQLException {
        Client c = new Client(
                rs.getString("mail"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("street"),
                rs.getString("complement"),
                rs.getString("city"),
                rs.getInt("postal_code"),
                rs.getInt("phone_numer"));
        return c;
    }

    @Override
    public List<Client> getAllClients() {

       String sqlSelect = "SELECT * FROM clients";
       List<Client> clients = new ArrayList<>();

       try{
           PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
           ResultSet rs = pstatement.executeQuery();

           /*Transforme toutes les lignes en clients*/
           while(rs.next()){
               Client client = this.resultSetToClient(rs);
               clients.add(client);
           }
       }
       catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       return clients;
    }

    @Override
    public Client getClientById(int id) throws ClientNotFoundException{

        String sqlSelect = "SELECT * FROM clients WHERE id=?";
        try{
            /*Requête select sur la base de donnée*/
            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1,id);
            ResultSet rs = pstatement.executeQuery(sqlSelect);

            /*Renvoie le client si trouvé, exception sinon*/
            if(rs.next()){
                return this.resultSetToClient(rs);
            }
            else{
                throw new ClientNotFoundException(id);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        //TODO : Mieux gérer ça
        return null;
    }
    //TODO : Remplacer par des autoincrémentales keys, et enlever le paramètre id
    public Client createClient(Client c) throws SQLException {

        String sqlInsert = "INSERT INTO clients VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement pstmt = this.coToDB.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,c.getName());
            pstmt.setString(2,c.getSurname());
            pstmt.setString(3,c.getStreet());
            pstmt.setString(4,c.getComplement());
            pstmt.setString(5, c.getCity());
            pstmt.setInt(6, c.getPostal_code());
            pstmt.setInt(7, c.getPhone_number());

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
