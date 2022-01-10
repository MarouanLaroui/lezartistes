package com.lezartistes.tests;

import com.lezartistes.dao.expert.ExpertDAO;
import com.lezartistes.dao.expert.ExpertDAOPostgres;
import com.lezartistes.dao.history.HistoryDAO;
import com.lezartistes.dao.history.HistoryDAOPostgres;
import com.lezartistes.exceptions.ExpertNotFoundException;
import com.lezartistes.facades.ExpertFacade;
import com.lezartistes.facades.HistoryFacade;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ExpertTest {
    @Test
    public void safeDAOSingleton(){
        ExpertDAO expertDAO = ExpertDAOPostgres.getInstance(null);
        assert(ExpertDAOPostgres.getInstance(null) == expertDAO);
    }

    @Test
    public void safeFacadeSingleton(){
        ExpertFacade facade = ExpertFacade.getInstance();
        assert(ExpertFacade.getInstance() == facade);
    }

    @Test
    public void throwsExpertNotFoundException(){
        ExpertFacade facade = ExpertFacade.getInstance();
        Exception ex = null;
        try {
            facade.getExpert(-17);
        }
        catch (ExpertNotFoundException e) {
            e.printStackTrace();
            System.out.println("error");
            ex = e;
        }
        finally {
            assert(ex!=null);
        }
    }
}
