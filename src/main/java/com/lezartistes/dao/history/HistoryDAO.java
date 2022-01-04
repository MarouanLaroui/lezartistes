package com.lezartistes.dao.history;

import com.lezartistes.models.History;

import java.io.Serializable;
import java.util.List;

public abstract class HistoryDAO implements Serializable {
    public HistoryDAO() {
        super();
    }

    public abstract List<History> getAllQuotationByBuildingId(int idBuilding);
}
