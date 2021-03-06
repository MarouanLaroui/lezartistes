package com.lezartistes.controllers.building;

import com.lezartistes.App;
import com.lezartistes.controllers.GeneralController;
import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.exceptions.BuildingNotFoundException;
import com.lezartistes.exceptions.ClientNotFoundException;
import com.lezartistes.facades.BuildingFacade;
import com.lezartistes.facades.ClientFacade;
import com.lezartistes.models.Building;
import com.lezartistes.models.User;
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
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListBuildingController extends GeneralController implements Initializable {

    @FXML
    private ListView<Building> buildingList;

    private final BuildingFacade buildingFacade;
    private User connectedUser;
    private ClientFacade clientFacade;

    public ListBuildingController(){
        this.buildingFacade = BuildingFacade.getInstance();
        this.clientFacade = ClientFacade.getInstance();
        this.connectedUser = UserInformation.getUser();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Building> building = null;
        if (!UserInformation.isServiceProvider()){
            int idAuthor=-1;
                User user = this.clientFacade.getClientByEmail(UserInformation.getUser().getMail());
                try {
                    idAuthor = this.clientFacade.getClientIdByMail(user.getMail());
                } catch (ClientNotFoundException e) {
                    e.printStackTrace();
                }
                if (idAuthor==-1){}
                else{
                        building = new ArrayList<>(this.buildingFacade.getBuildingByMailClient(user.getMail()));
                        this.buildingList.setItems(new FilteredList<>(FXCollections.observableList(building)));

                    this.buildingList.setItems(new FilteredList<>(FXCollections.observableList(building)));
                }

        }
    }

    @FXML
    protected void addNewBuilding () throws IOException {
        App.setRoot("views/building/createBuilding");
    }

    @FXML
    protected void clickOnBuilding(MouseEvent mouseEvent) throws IOException {

        Stage stage = new Stage();
        stage.setHeight(600);
        stage.setWidth(600);

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
