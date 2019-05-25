/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import utils.DataSource;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.UserService;
import entity.User;

public class RegisterController {

    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtPassword2;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPhone;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnSignin;
    @FXML
    Label lblStatus;

    PreparedStatement preparedStatement;
    Connection con;

    public RegisterController() {
        con = (Connection) DataSource.getInstance().getCnx();
    }
    UserService udao = UserService.getInstance();
    User u = new User();

    @FXML
    private void HandleEvents(MouseEvent event) {

        if (txtEmail.getText().isEmpty() || txtFirstname.getText().isEmpty() || txtLastname.getText().isEmpty() || txtUsername.getText().isEmpty() || txtPhone.getText().isEmpty() || txtAddress.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Enter all details");
        } else if (udao.getEmail(txtEmail.getText()) != null) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Email already exists");
            txtEmail.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
            txtPhone.setStyle("");
            txtPassword.setStyle("");
            txtPassword2.setStyle("");
            txtUsername.setStyle("");
        } else if (!txtEmail.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Enter valid Email");
            txtEmail.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
            txtPhone.setStyle("");
            txtPassword.setStyle("");
            txtPassword2.setStyle("");
            txtUsername.setStyle("");
        } else if (udao.getId(txtUsername.getText()) != 0) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Username already exists");
            txtUsername.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
            txtPhone.setStyle("");
            txtEmail.setStyle("");
            txtPassword.setStyle("");
            txtPassword2.setStyle("");
        } else if (!txtPhone.getText().matches("[0-9]+")) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Enter valid phone number");
            txtPhone.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
            txtEmail.setStyle("");
            txtPassword.setStyle("");
            txtPassword2.setStyle("");
            txtUsername.setStyle("");
        } else if (txtPassword.getText() == null ? txtPassword2.getText() != null : !txtPassword.getText().equals(txtPassword2.getText())) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("passwords don't match");
            txtPassword.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
            txtPassword2.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
            txtPhone.setStyle("");
            txtEmail.setStyle("");
            txtUsername.setStyle("");
        } else {

            u.setFirst_name(txtFirstname.getText());
            u.setLast_name(txtLastname.getText());
            u.setEmail(txtEmail.getText());
            u.setUsername(txtUsername.getText());
            u.setAddress(txtAddress.getText());
            u.setPhone(txtPhone.getText());
            u.setPassword(txtPassword.getText());
            udao.insert(u);
            txtPassword.setStyle("");
            txtPassword2.setStyle("");
            txtEmail.setStyle("");
            txtUsername.setStyle("");
            txtPhone.setStyle("");
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("registration complete");
        }
        if (event.getSource() == btnSignin) {

            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene;
                scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Login.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void clearFields() {
        txtFirstname.clear();
        txtLastname.clear();
        txtEmail.clear();
        txtUsername.clear();
        txtAddress.clear();
        txtPhone.clear();
        txtPassword.clear();
        txtPassword2.clear();

    }

}
