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
public class UpdateController implements Initializable{

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


        ObservableList<String> list=FXCollections.observableArrayList();
    @FXML
    private Button btnChange;
    @FXML
    private Label lblNew;
    @FXML
    private Label lblConfirm;
             boolean change=false;
             String pw_hash ;

    @FXML
    private void HandleEvents(MouseEvent event) {
        
        
        
          if (event.getSource() == btnChange){
         
         lblNew.setVisible(true);         
         lblConfirm.setVisible(true);         
         txtPassword.setVisible(true);         
         txtPassword2.setVisible(true);
         btnChange.setVisible(false);
         change=true;
     

     }
        
             if (event.getSource() == btnSave) {
            
           User user = new User();
            UserService udao=UserService.getInstance();
            user.setAddress(txtAddress.getText());
            user.setFirst_name(txtFirstname.getText());
            user.setLast_name(txtLastname.getText());
            user.setUsername(txtUsername.getText());
            user.setPhone(txtPhone.getText());
            user.setEmail(txtEmail.getText());
     if (change==true){
         
     
 pw_hash = MyBCrypt.hashpw(txtPassword.getText(), MyBCrypt.gensalt());
            
     udao.UpdatePassword(pw_hash,Session.current_user);
     }
     
     
   
           
           
            
     udao.UpdateUser(user,Session.current_user);
       lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Updated Successfully");
            lblNew.setVisible(false);         
         lblConfirm.setVisible(false);         
         txtPassword.setVisible(false); 
         txtPassword.clear();
         txtPassword2.setVisible(false);
         txtPassword2.clear();
         btnChange.setVisible(true);
         change=false;
     
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
        
    
        
        ObservableList<String> list=FXCollections.observableArrayList();
        
     UserService udao=UserService.getInstance();
     list=udao.DisplayById(Session.current_user);
         
     list=udao.DisplayById(Session.current_user);
     txtUsername.setText(list.get(0));
     txtFirstname.setText(list.get(1));
     txtLastname.setText(list.get(2));
     txtEmail.setText(list.get(3));
     txtAddress.setText(list.get(4));
     txtPhone.setText(list.get(5));
     
     
     
     
    }
    
}
