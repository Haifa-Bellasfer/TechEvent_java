/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Domain;
import entity.Subscriber;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import service.DomainService;
import service.SubscriberService;
import utils.Mail;

/**
 * FXML Controller class
 *
 * @author ihebc_000
 */
public class SubsController implements Initializable {

    @FXML
    private JFXTextField txtEmail;
    @FXML
    private ChoiceBox<Domain> chDomain;
    @FXML
    private JFXButton btnSubscribe;
    @FXML
    private Label error;
    @FXML
    private Label errorD;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chDomain.setItems(DomainService.getInstance().DisplayAll());
        btnSubscribe.setOnAction(e -> {
            error.setText("");
            errorD.setText("");
            String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

            if ((txtEmail.getText().isEmpty())
                    || (!txtEmail.getText().matches(regex))
                    || (chDomain.getSelectionModel().getSelectedItem() == null)
                    || (SubscriberService.getInstance().isSubsribed(txtEmail.getText()))) {
                if (txtEmail.getText().isEmpty()) {
                    error.setText("Email can not be empty.");
                } else {
                    if (!txtEmail.getText().matches(regex)) {
                        error.setText("email is not valid.");
                    }
                }
                if (chDomain.getSelectionModel().getSelectedItem() == null) {
                    errorD.setText("Domain  can not be empty.");
                }
                if (SubscriberService.getInstance().isSubsribed(txtEmail.getText())) {
                    error.setText("You are already subscribed.");
                }
            } else {
                Subscriber s = new Subscriber(txtEmail.getText(), chDomain.getSelectionModel().getSelectedItem());
                s.setIdSubscriber(SubscriberService.getInstance().create(s));
                Mail.getInstance().SendWelcomeMail(s);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Subscriber ");
                alert.setHeaderText(null);
                alert.setContentText("You are now subscribed to our newsletter.");
                alert.show();
            }

        });
    }

}
