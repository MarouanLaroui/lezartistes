package com.lezartistes.controllers.history;

import com.lezartistes.App;
import com.lezartistes.controllers.GeneralController;
import com.lezartistes.facades.HistoryFacade;

import java.io.IOException;

public abstract class HistoryController extends GeneralController {
    protected final HistoryFacade historyFacade = HistoryFacade.getInstance();

    public void redirectToHistoryList () throws IOException {
        App.setRoot("views/history/HistoryList");
    }
}
