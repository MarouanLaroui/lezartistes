package com.lezartistes.dao.history;

import com.lezartistes.models.History;

import java.io.Serializable;
import java.util.List;

public abstract class HistoryDAO implements Serializable {
    public HistoryDAO() {
        super();
    }

    public abstract List<History> getAllHistoryByBuildingId(int idBuilding);
    public abstract List<History> getHistoryByClientId(String mailClient);
    public abstract List<History> getHistoryByExpertPMail(String mailSP);
    public abstract List<History> getAllHistory();
    public abstract int createHistory(History h);
}
