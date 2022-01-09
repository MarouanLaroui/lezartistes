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

    public List<History> getAllHistoryByBuildingId (int idBuilding) {
        return this.dao.getAllHistoryByBuildingId(idBuilding);
    }

    public List<History> getAllHistory () {
        return this.dao.getAllHistory();
    }

    public List<History> getHistoryByClientId (String mailClient) {
        return this.dao.getHistoryByClientId(mailClient);
    }

    public List<History> getHistoryByExpertPMail (String mailSP) {
        return this.dao.getHistoryByExpertPMail(mailSP);
    }

    public int createHistory (History h) {
        return this.dao.createHistory(h);
    }
}
