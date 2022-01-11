package com.lezartistes.tests;

import com.lezartistes.dao.history.HistoryDAO;
import com.lezartistes.dao.history.HistoryDAOPostgres;
import com.lezartistes.facades.HistoryFacade;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class HistoryTest {

    @Test
    public void safeDAOSingleton(){
        Assertions.assertNull(HistoryDAOPostgres.getInstance(null));
    }

    @Test
    public void safeFacadeSingleton(){
        HistoryFacade facade = HistoryFacade.getInstance();
        assert(HistoryFacade.getInstance() == facade);
    }
}
