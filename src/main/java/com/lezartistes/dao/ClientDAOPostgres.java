package com.lezartistes.dao;

import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.exceptions.UserNotFoundException;
import com.lezartistes.models.Client;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOPostgres extends ClientDAO{

    /*attributes*/
    private static ClientDAOPostgres clientDAOPostgres;
    private final Connection coToDB;

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

        return new Client(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("street"),
                rs.getString("complement"),
                rs.getString("city"),
                rs.getInt("postal_code"),
                rs.getInt("phone_number"),
                false);
        //TODO : On part du principe qu'on appel cette fonction que si on récupère depuis la base de données donc pas d'encryptage de mot de passe
    }

    @Override
    public List<Client> getAllClients() throws ClientNotFoundException{

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
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       if(clients.isEmpty()){
           throw new ClientNotFoundException();
       }
       return clients;
    }

    @Override
    public Client getClientById(int id) throws ClientNotFoundException{

        String sqlSelect = "SELECT * FROM clients WHERE id_clients=?";
        try {
            /*Requête select sur la base de donnée*/
            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1, id);
            ResultSet rs = pstatement.executeQuery();

            /*Renvoie le client si trouvé, exception sinon*/
            if(rs.next()){
                return this.resultSetToClient(rs);
            }
            else{
                throw new ClientNotFoundException(id);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        //TODO : Mieux gérer ça (si on a le temps sinon ballec)
        return null;
    }

    @Override
    public Client getClientByEmail(String email) {
        System.out.println("Mail reçu en entrée : " + email);
        String sqlSelect = "SELECT * FROM clients WHERE username=?";
        Client c;
        try {
            /*Requête select sur la base de donnée*/

            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setString(1, email);

            ResultSet rs = pstatement.executeQuery();

            /*Renvoie le client si trouvé, exception sinon*/
            if(rs.next())
                c = this.resultSetToClient(rs);
            else
                throw new UserNotFoundException(email);
        } catch (SQLException | UserNotFoundException e){
            e.printStackTrace();
            c = null;
        }
        return c;
    }

    //TODO : Remplacer par des autoincrémentales keys, et enlever le paramètre id
    public int createClient(Client c) {
        int affectRows = 0;
        String sqlInsert = "INSERT INTO clients(username, password, name, surname, street, complement, city, postal_code, phone_number) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = this.coToDB.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, c.getMail());
            pstmt.setString(2, c.getPassword());
            pstmt.setString(3,c.getName());
            pstmt.setString(4,c.getSurname());
            pstmt.setString(5,c.getStreet());
            pstmt.setString(6,c.getComplement());
            pstmt.setString(7, c.getCity());
            pstmt.setInt(8, c.getPostal_code());
            pstmt.setInt(9, c.getPhone_number());

            affectRows = pstmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    }
}
