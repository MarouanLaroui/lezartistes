package com.lezartistes.dao.history;

import com.lezartistes.models.History;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAOPostgres extends HistoryDAO {
    private static HistoryDAOPostgres historyDAOPostgres;
    private final Connection connection;

    public HistoryDAOPostgres (Connection c) {
        super();
        this.connection = c;
    }

    public static HistoryDAO getInstance(Connection connection) {
        if(historyDAOPostgres == null) {
            historyDAOPostgres = new HistoryDAOPostgres(connection);
        }
        return historyDAOPostgres;
    }

    public List<History> getAllHistoryByBuildingId(int idBuilding) {
        System.out.println("id de notre building : " + idBuilding);
        String sqlSelect = "SELECT * FROM histories WHERE relatedBuilding = ?";
        List<History> histories = new ArrayList<>();

        try {
            PreparedStatement pstatement = this.connection.prepareStatement(sqlSelect);
            pstatement.setInt(1, idBuilding);
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en feedback*/
            while (resultSet.next()) {
                histories.add(this.resultSetToHistory(resultSet));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return histories;
    }

    @Override
    public List<History> getHistoryByClientId(String mailClient) {
        String sqlSelect = "" +
                "SELECT H.date, H.description, H.idhistory, C.username " +
                "FROM histories H " +
                "JOIN buildings B ON B.id_building = H.relatedbuilding " +
                "JOIN clients C ON C.id_clients = B.client " +
                "WHERE C.username = ?;";
        List<History> histories = new ArrayList<>();

        try {
            PreparedStatement pstatement = this.connection.prepareStatement(sqlSelect);
            pstatement.setString(1, mailClient);
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en feedback*/
            System.out.println("-- ca commence --");
            while (resultSet.next()) {
                System.out.println("On ajoute un building en plus de notre réponse");
                System.out.println(resultSet.getString(4));
                histories.add(this.resultSetToHistory(resultSet));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return histories;
    }

    @Override
    public List<History> getHistoryBySPId(String mailSP) {
        String sqlSelect = "" +
                "SELECT H.date, H.description, H.idhistory " +
                "FROM histories H " +
                "JOIN buildings B ON B.id_building = H.relatedbuilding " +
                "JOIN callforproposals C ON B.id_building = c.building " +
                "JOIN quotations Q ON Q.callforproposal = C.idcfp " +
                "JOIN companies c2 ON Q.idcompany = c2.idcompany " +
                "JOIN serviceproviders sp ON sp.id_company = sp.id_company " +
                "WHERE sp.username = ?;";
        List<History> histories = new ArrayList<>();

        try {
            PreparedStatement pstatement = this.connection.prepareStatement(sqlSelect);
            pstatement.setString(1, mailSP);
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en feedback*/
            System.out.println("-- ca commence --");
            while (resultSet.next()) {
                histories.add(this.resultSetToHistory(resultSet));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return histories;
    }

    @Override
    public List<History> getAllHistory() {
        String sqlSelect = "SELECT * FROM histories";
        List<History> histories = new ArrayList<>();

        try {
            PreparedStatement pstatement = this.connection.prepareStatement(sqlSelect);
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en feedback*/
            while (resultSet.next()) {
                histories.add(this.resultSetToHistory(resultSet));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return histories;
    }

    @Override
    public int createHistory(History h) {
        int affectRows = 0;
        try {
            String sqlInsert = "INSERT INTO histories(date, description, relatedBuilding) VALUES (?, ?, ?)";

            PreparedStatement stmtprep = this.connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stmtprep.setDate(1, h.getDate());
            stmtprep.setString(2, h.getDescription());
            stmtprep.setInt(3, h.getIdBuilding());

            affectRows = stmtprep.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return affectRows;
    }

    private History resultSetToHistory (ResultSet rs) throws SQLException {
        return new History(rs.getInt(3), rs.getDate(1), rs.getString(2));
    }
}
