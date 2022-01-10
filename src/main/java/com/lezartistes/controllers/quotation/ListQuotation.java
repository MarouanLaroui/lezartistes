package com.lezartistes.controllers.quotation;

import com.lezartistes.App;
import com.lezartistes.controllers.GeneralController;
import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.exceptions.QuotationNotFoundException;
import com.lezartistes.facades.ClientFacade;
import com.lezartistes.facades.ExpertFacade;
import com.lezartistes.facades.QuotationFacade;
import com.lezartistes.models.Quotation;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListQuotation extends GeneralController implements Initializable {

    @FXML
    private ListView<Quotation> quotationList;
    private ClientFacade clientFacade;
    private final QuotationFacade quotationFacade;
    private User connectedUser;


    public ListQuotation(){
        this.quotationFacade = QuotationFacade.getInstance();
        this.connectedUser = UserInformation.getUser();
        this.clientFacade = ClientFacade.getInstance();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Quotation> quotations = null;
        User user = this.clientFacade.getClientByEmail(UserInformation.getUser().getMail());
        if (!UserInformation.isServiceProvider()) {
            try {
                quotations = new ArrayList<>(this.quotationFacade.getAllQuotation());
                this.quotationList.setItems(new FilteredList<>(FXCollections.observableList(quotations)));
            } catch (QuotationNotFoundException e) {
                e.printStackTrace();
            }
        }
            else{
                try {
                    quotations = new ArrayList<>(this.quotationFacade.getQuotationByCompany(ExpertFacade.getInstance().getExpertByEmail(UserInformation.getUser().getMail()).getCompanyId()));
                    this.quotationList.setItems(new FilteredList<>(FXCollections.observableList(quotations)));
                } catch (QuotationNotFoundException e) {
                    e.printStackTrace();
            }

        }
    }

    @FXML
    protected void addNewQuotation () throws IOException {
        App.setRoot("views/quotation/createQuotation");
    }

    @FXML
    protected void clickOnQuotation(MouseEvent mouseEvent) throws IOException {

        Stage stage = new Stage();
        stage.setHeight(600);
        stage.setWidth(600);

        Quotation selectedQuot = this.quotationList.getSelectionModel().getSelectedItem();
        if (selectedQuot != null) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/quotation/readQuotation.fxml"));

            ReadQuotationController readQuotationController = new ReadQuotationController(selectedQuot);
            loader.setController(readQuotationController);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        }
    }
}
