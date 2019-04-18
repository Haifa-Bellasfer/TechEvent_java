package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.DataSource;
import service.UserService;
import entity.User;
import javafx.scene.layout.StackPane;
import org.mindrot.jbcrypt.BCrypt;
import utils.Session;


public class LoginController implements Initializable {

    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnSignin;
    @FXML
    private Button btnSignup;
 public int id ;
    /// -- 
    Connection con = null;
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement2 = null;
    ResultSet resultSet = null;
    ResultSet resultSet2 = null;
    ResultSet resultSet3 = null;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }
    }

    public LoginController() {
        con = DataSource.getInstance().getCnx();

    }

    public boolean logIn() {

        String username = txtUsername.getText();
        String password = txtPassword.getText();
        boolean bool=false;
      

        UserService ud = UserService.getInstance();
       String passw=ud.getPass(username);
        
    
if(passw!=null){
            if 
            (MyBCrypt.checkpw(password, passw.replaceFirst("2y", "2a")))
            {
                System.out.println("It matches");
      
              id= ud.getId(username);
              Session.current_user = new User();
                Session.current_user.setId_user(id);
                bool=true;
            }     
            
            
            else {
               lblErrors.setTextFill(Color.TOMATO);
                lblErrors.setText("Enter Correct Email/Password");
                System.err.println("Wrong Logins --///");}}
            else {
               lblErrors.setTextFill(Color.TOMATO);
                lblErrors.setText("Enter Correct Email/Password");
                System.err.println("Wrong Logins --///");}

      
        return bool;

    }
    
    @FXML
    public void handleButtonAction(MouseEvent event) {

     UserService udao=UserService.getInstance();
        if (event.getSource() == btnSignin) {
            if (logIn()==true) {
                try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    if (udao.isAdmin(Session.current_user.getId_user())==true){
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/UsersList.fxml")));
                      stage.setScene(scene);
                    stage.show();}
                    else {
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Home.fxml")));
                      stage.setScene(scene);
                    stage.show();}
                  

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
        
        
        
        
        
        
           if (event.getSource() == btnSignup) {
            
                

            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Register.fxml")));
                
                stage.setTitle("Register");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

                

            
        }
        
        
        
        
        
    }

}
