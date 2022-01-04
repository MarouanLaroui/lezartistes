package com.lezartistes;

import com.lezartistes.controllers.client.ClientProfileController;
import com.lezartistes.controllers.report.SpReportListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.lezartistes.App;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {


        //scene = new Scene(loadFXML("views/user/login"), 640, 480);
        //scene = new Scene(loadFXML("views/client/ClientProfile"), 640, 480);
        //scene = new Scene(loadFXML("views/client/ClientList"), 640, 480);
        //scene = new Scene(loadFXML("views/report/ViewReport"), 640, 480);
        //scene = new Scene(loadFXML("views/report/ReportForm"), 640, 480);
        //stage.setScene(scene);
        //stage.show();

        /*
        stage.setHeight(480);
        stage.setWidth(640);
        FXMLLoader loader = new FXMLLoader(App.class.getResource("views/report/ReportList.fxml"));

        try{
            SpReportListController cpc = new SpReportListController(2, stage);
            loader.setController(cpc);
            Scene scene = new Scene(loader.load(), stage.getWidth(),stage.getHeight());
            stage.setScene(scene);
        }
        catch (IOException e){
            e.printStackTrace();
        }
         */
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
