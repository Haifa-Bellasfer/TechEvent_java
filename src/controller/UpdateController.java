/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.UserService;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.Session;

/**
 *
 * @author Dalli
 */
public class UpdateController implements Initializable {

    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPhone;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtPassword2;
    @FXML
    private Label lblStatus;

    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private Button btnChange;
    @FXML
    private Label lblNew;
    @FXML
    private Label lblConfirm;
    boolean change = false;
    String pw_hash;

    @FXML
    private void HandleEvents(MouseEvent event) {

        UserService udao = UserService.getInstance();
        User u = new User();
        if (event.getSource() == btnChange) {

            lblNew.setVisible(true);
            lblConfirm.setVisible(true);
            txtPassword.setVisible(true);
            txtPassword2.setVisible(true);
            btnChange.setVisible(false);
            change = true;

        }

        if (event.getSource() == btnSave) {
            if (!txtEmail.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                lblStatus.setTextFill(Color.TOMATO);
                lblStatus.setText("Enter valid Email");
                txtEmail.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
                txtPhone.setStyle("");
                txtPassword.setStyle("");
                txtPassword2.setStyle("");
                txtUsername.setStyle("");
            } else if (!txtPhone.getText().matches("[0-9]+")) {
                lblStatus.setTextFill(Color.TOMATO);
                lblStatus.setText("Enter valid phone number");
                txtPhone.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
                txtEmail.setStyle("");
                txtPassword.setStyle("");
                txtPassword2.setStyle("");
                txtUsername.setStyle("");
            } else { 
                

                if (change == true) {

                    if (!txtPassword.getText().equals(txtPassword2.getText())) {
                        lblStatus.setTextFill(Color.TOMATO);
                        lblStatus.setText("passwords don't match");
                        txtPassword.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
                        txtPassword2.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
                        txtPhone.setStyle("");
                        txtEmail.setStyle("");
                        txtUsername.setStyle("");
                    } else if (txtPassword.getText() == "" || txtPassword2.getText() == null) {
                        lblStatus.setTextFill(Color.TOMATO);
                        lblStatus.setText("Enter valid passwords");
                        txtPassword.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
                        txtPassword2.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
                        txtPhone.setStyle("");
                        txtEmail.setStyle("");
                        txtUsername.setStyle("");
                    } else {
                        pw_hash = MyBCrypt.hashpw(txtPassword.getText(), MyBCrypt.gensalt());

                        udao.UpdatePassword(pw_hash, Session.current_user.getId_user());
                    }
                }
                User user = new User();
                user.setAddress(txtAddress.getText());
                user.setFirst_name(txtFirstname.getText());
                user.setLast_name(txtLastname.getText());
                user.setUsername(txtUsername.getText());
                user.setPhone(txtPhone.getText());
                user.setEmail(txtEmail.getText());

                udao.UpdateUser(user, Session.current_user.getId_user());
                lblStatus.setTextFill(Color.GREEN);
                lblStatus.setText("Updated Successfully");
                lblNew.setVisible(false);
                lblConfirm.setVisible(false);
                txtPassword.setVisible(false);
                txtPassword.clear();
                txtPassword2.setVisible(false);
                txtPassword2.clear();
                btnChange.setVisible(true);
                change = false;

            }
        }

        if (event.getSource() == btnCancel) {

            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Profile.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        UserService udao = UserService.getInstance();
        User user = new User();

        user = udao.DisplayById(Session.current_user.getId_user());

        txtUsername.setText(user.getUsername());
        txtFirstname.setText(user.getFirst_name());
        txtLastname.setText(user.getLast_name());
        txtEmail.setText(user.getEmail());
        txtAddress.setText(user.getAddress());
        txtPhone.setText(user.getPhone());

    }

}
