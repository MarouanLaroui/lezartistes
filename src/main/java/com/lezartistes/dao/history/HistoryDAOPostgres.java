package com.lezartistes.dao.history;

import com.lezartistes.models.History;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public List<History> getAllQuotationByBuildingId(int idBuilding) {
        System.out.println("id de notre building : " + idBuilding);
        String sqlSelect = "SELECT * FROM histories WHERE relatedBuilding = ?";
        List<History> histories = new ArrayList<>();

        try {
            PreparedStatement pstatement = this.connection.prepareStatement(sqlSelect);
            pstatement.setInt(1, idBuilding);
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en feedback*/
            while (resultSet.next()) {
                histories.add(new History(resultSet.getInt(4), resultSet.getDate(2), resultSet.getString(3)));

            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return histories;
    }
}
