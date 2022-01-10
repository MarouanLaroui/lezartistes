package com.lezartistes.tests;

import com.lezartistes.dao.history.HistoryDAO;
import com.lezartistes.dao.history.HistoryDAOPostgres;
import com.lezartistes.facades.HistoryFacade;
import org.junit.Test;

public class HistoryTest {

    @Test
    public void safeDAOSingleton(){
        HistoryDAO historyDAO = HistoryDAOPostgres.getInstance(null);
        assert(HistoryDAOPostgres.getInstance(null) == historyDAO);
    }

    @Test
    public void safeFacadeSingleton(){
        HistoryFacade facade = HistoryFacade.getInstance();
        assert(HistoryFacade.getInstance() == facade);
    }

    @Test
    public void throwsHistoryNotFoundException(){
        HistoryFacade facade = HistoryFacade.getInstance();
        facade.getAllHistoryByBuildingId(-1);
    }
}
