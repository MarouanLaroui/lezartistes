package com.lezartistes;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField input;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
        System.out.println(input.getText());
    }
}
