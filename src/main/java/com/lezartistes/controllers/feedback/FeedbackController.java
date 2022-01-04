package com.lezartistes.controllers.feedback;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FeedbackController {

    /**
     * Linked to the TextField who's fx:id is his rating
     */
    @FXML
    private Label rating;

    @FXML
    private Label comment;
}
