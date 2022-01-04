module com.lezartistes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires junit;
    requires org.junit.jupiter.api;

    opens com.lezartistes to javafx.fxml;
    exports com.lezartistes;

    opens com.lezartistes.dao to javafx.fxml;
    exports com.lezartistes.dao;

    opens com.lezartistes.dao.serviceProvider to javafx.fxml;
    exports com.lezartistes.dao.serviceProvider;

    opens com.lezartistes.controllers.user to javafx.fxml;
    exports com.lezartistes.controllers.user;

    opens com.lezartistes.controllers.client to javafx.fxml;
    exports com.lezartistes.controllers.client;

    opens com.lezartistes.controllers to javafx.fxml;
    exports com.lezartistes.controllers;

    opens com.lezartistes.database to javafx.fxml;
    exports com.lezartistes.database;

    opens com.lezartistes.exceptions to javafx.fxml;
    exports com.lezartistes.exceptions;

    opens com.lezartistes.facades to javafx.fxml;
    exports com.lezartistes.facades;

    opens com.lezartistes.models to javafx.fxml;
    exports com.lezartistes.models;
    exports com.lezartistes.dao.feedback;
    opens com.lezartistes.dao.feedback to javafx.fxml;
}
