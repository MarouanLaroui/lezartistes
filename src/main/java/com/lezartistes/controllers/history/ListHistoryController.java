package com.lezartistes.controllers.history;

import com.lezartistes.App;
import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.models.History;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    @FXML
    private Button addnewHistorybtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<History> hist;
        if ( ! UserInformation.isServiceProvider()) {
            hist = new ArrayList<>(this.historyFacade.getHistoryByClientId(UserInformation.getUser().getMail()));
        } else {
            hist = new ArrayList<>(this.historyFacade.getHistoryByExpertPMail(UserInformation.getUser().getMail()));
            this.addnewHistorybtn.setVisible(false);
        }
        this.historyList.setItems(new FilteredList<>(FXCollections.observableList(hist)));
    }

    @FXML
    protected void addNewHistory () throws IOException {
        App.setRoot("views/history/AddHistory");
    }

    @FXML
    protected void clickOnHistory(MouseEvent mouseEvent) throws IOException {

        Stage stage = new Stage();
        stage.setHeight(600);
        stage.setWidth(600);

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