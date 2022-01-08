package com.lezartistes.controllers.building;

import com.lezartistes.App;
import com.lezartistes.exceptions.BuildingNotFoundException;
import com.lezartistes.models.Building;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListBuildingController extends BuildingController implements Serializable {

    @FXML
    private ListView<Building> buildingList;

    public void initialize(URL url, ResourceBundle resourceBundle) throws BuildingNotFoundException {
        ArrayList<Building> building = new ArrayList<>(this.buildingFacade.getAllBuilding());
        this.buildingList.setItems(new FilteredList<>(FXCollections.observableList(building)));
    }

    @FXML
    protected void addNewBuilding () throws IOException {
        App.setRoot("views/building/createBuilding");
    }

    @FXML
    protected void clickOnBuilding(MouseEvent mouseEvent) throws IOException {

        Stage stage = new Stage();
        stage.setHeight(280);
        stage.setWidth(610);

        Building selectedBuild = this.buildingList.getSelectionModel().getSelectedItem();
        if (selectedBuild != null) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/building/readBuilding.fxml"));

            ReadBuildingController readBuildingController = new ReadBuildingController(selectedBuild);
            loader.setController(readBuildingController);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        }
    }
}
