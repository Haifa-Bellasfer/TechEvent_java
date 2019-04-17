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
import java.sql.SQLException;
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
import controller.MyBCrypt;
import service.UserService;
import entity.User;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterController  {
  

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
    Connection con ;

    public RegisterController() {
        con = (Connection) DataSource.getInstance().getCnx();
    }

    
    @FXML
    private void HandleEvents(MouseEvent event) {
        if (txtEmail.getText().isEmpty() || txtFirstname.getText().isEmpty() || txtLastname.getText().isEmpty() || txtUsername.getText().isEmpty()|| txtPhone.getText().isEmpty()|| txtAddress.getText().isEmpty()|| txtPassword.getText().isEmpty()) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Enter all details");
        } 
        else if (txtPassword.getText() == null ? txtPassword2.getText() != null : !txtPassword.getText().equals(txtPassword2.getText())){
             lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("passwords don't match");
        }
        else {
            
     UserService udao=UserService.getInstance();
     User u = new User();
     u.setFirst_name(txtFirstname.getText());
     u.setLast_name(txtLastname.getText());
     u.setEmail(txtEmail.getText());
     u.setUsername(txtUsername.getText());
     u.setAddress(txtAddress.getText());
     u.setPhone(txtPhone.getText());
     u.setPassword(txtPassword.getText());
     udao.insert(u);
            
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
