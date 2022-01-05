package com.lezartistes.controllers.history;

import com.lezartistes.App;
import com.lezartistes.controllers.GeneralController;
import com.lezartistes.controllers.report.ReadReportController;
import com.lezartistes.facades.HistoryFacade;
import com.lezartistes.models.History;
import com.lezartistes.models.Report;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListHistoryController extends HistoryController implements Initializable {

    @FXML
    private ListView<History> historyList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<History> hist = new ArrayList<>(this.historyFacade.getAllHistory());
        this.historyList.setItems(new FilteredList<>(FXCollections.observableList(hist)));
    }

    @FXML
    protected void addNewHistory () throws IOException {
        App.setRoot("views/history/AddHistory");
    }

    @FXML
    protected void clickOnHistory(MouseEvent mouseEvent) throws IOException {

        Stage stage = new Stage();
        stage.setHeight(280);
        stage.setWidth(610);

        History selectedHisto = this.historyList.getSelectionModel().getSelectedItem();
        if (selectedHisto != null) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/history/ReadHistory.fxml"));

            ReadHistoryController cpc = new ReadHistoryController(selectedHisto);
            loader.setController(cpc);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        }
    }
}