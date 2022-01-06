package com.lezartistes.controllers.history;

import com.lezartistes.models.History;

public class ReadHistoryController extends HistoryController {

    History historyToShow;

    public ReadHistoryController (History h) {
        super();
        this.historyToShow = h;
    }
}
