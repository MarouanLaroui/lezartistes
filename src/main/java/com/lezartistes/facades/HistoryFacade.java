package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.history.HistoryDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.models.History;

import java.util.List;

public class HistoryFacade {

    private static HistoryFacade facade;
    private HistoryDAO dao;

    private HistoryFacade() {
        AbstractFactory factory = PostgresFactory.getInstance();
        this.dao = factory.createHistoryDAO();
    }

    public static HistoryFacade getInstance() {
        if (facade == null)
            facade = new HistoryFacade();
        return facade;
    }

    public List<History> getAllQuotationByBuildingId (int idBuilding) {
        return this.dao.getAllQuotationByBuildingId(idBuilding);
    }

    public static void main(String[] args) {
        System.out.println("coucou");
        HistoryFacade fa = HistoryFacade.getInstance();
        fa.getAllQuotationByBuildingId(1);
    }
}
