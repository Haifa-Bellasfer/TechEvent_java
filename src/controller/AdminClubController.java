/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import entity.Club;
import entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.ClubService;
import service.ThemeServices;
import service.UserService;
import utils.Session;


/**
 * FXML Controller class
 *
 * @author mbare
 */
public class AdminClubController implements Initializable {

    @FXML
    private TableView<Club> clubs;
    @FXML
    private TableColumn<Club, String> desc;
    @FXML
    private TableColumn<Club, String> mail;
    @FXML
    private TableColumn<Club, String> st;
    @FXML
    private TableColumn<Club, String> name;
    JFXButton acc = new JFXButton("Change status");
    JFXButton fire = new JFXButton("delete");
    VBox pbox = new VBox(acc,fire) ;
    JFXPopup pop = new JFXPopup(pbox);
    /**
     * Initializes the controller class.
     */
    ClubService cs = ClubService.getInstance();
    @FXML
    private Label label_news;
    @FXML
    private Label themes;
    @FXML
    private Label req;
    @FXML
    private Label label_users;
    @FXML
    private Label label_clubs;
    @FXML
    private JFXButton btnlogout;
    @FXML
    private Label event;
    @FXML
    private Label Report;
    public void aff(){
            
        ThemeServices ts = ThemeServices.getInstance();
        ObservableList<Club> ls = FXCollections.observableArrayList();
        ls=cs.DisplayAll();
        
        name.setCellValueFactory(new PropertyValueFactory<>("club_name"));
        desc.setCellValueFactory(new PropertyValueFactory<>("club_description"));
                
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        st.setCellValueFactory(new PropertyValueFactory<>("club_status"));
        clubs.setItems(ls);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         Report.setOnMouseClicked(e -> {
            try {
                Parent page = FXMLLoader.load(getClass().getResource("/view/AdminDashborad.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        event.setOnMouseClicked(e -> {
            try {
                Parent page = FXMLLoader.load(getClass().getResource("/view/Chart.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        label_clubs.setOnMouseClicked(e -> {
            try {
                Parent page = FXMLLoader.load(getClass().getResource("/view/adminClub.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnlogout.setOnAction(e -> {
            Session.current_user = null;
            try {
                Parent page = FXMLLoader.load(getClass().getResource("/view/News.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //navbar
        label_news.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Domain.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DomainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        label_users.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/UsersList.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DomainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        themes.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/clubView.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(AdminClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        req.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/adminClub.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(AdminClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        
        aff();
        acc.setOnAction(event ->{
            Club cu = new Club();
            UserService us = UserService.getInstance();
             cu=clubs.getSelectionModel().getSelectedItem();
             
            String num= us.DisplayById(clubs.getSelectionModel().getSelectedItem().getOwner()).getPhone();
             if ("Accepted".equals(cu.getClub_status())) {
                cu.setClub_status("Refused");
                try {
			// Construct data
			String apiKey = "apikey=" + "MVLOno+0VZM-y2GuYuU0QFsk10BeBFvXBoI9vyhI0P";
			String message = "&message=" + "TechEvent wants to inform you that your club "+cu.getClub_name()+" has been refused";
			String sender = "&sender=" + "Admin";
			String numbers = "&numbers=" + "+216"+num;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                JOptionPane.showMessageDialog(null, message+line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			//System.out.println("Error SMS "+e);
                        JOptionPane.showMessageDialog(null, e);
			//return "Error "+e;
		}
            }else if ("Refused".equals(cu.getClub_status())){
             cu.setClub_status("Accepted");
             try {
			// Construct data
			String apiKey = "apikey=" + "MVLOno+0VZM-y2GuYuU0QFsk10BeBFvXBoI9vyhI0P";
			String message = "&message=" + "TechEvent wants to inform you that your club "+cu.getClub_name()+" has been accepted";
			String sender = "&sender=" + "Admin";
			String numbers = "&numbers=" + "+21626424863";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                JOptionPane.showMessageDialog(null, message+line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			//System.out.println("Error SMS "+e);
                        JOptionPane.showMessageDialog(null, e);
			//return "Error "+e;
		}
             }
            cs.update(cu);
            pop.hide();
            aff();
            pop.setPopupContent(pbox);
            
        });
        
        fire.setOnAction(event ->{
            Club cu = new Club();
            cu=clubs.getSelectionModel().getSelectedItem();
            cs.delete(cu);
            pop.hide();
            aff();
            pop.setPopupContent(pbox);
        });
    }    

    @FXML
    private void popShow(MouseEvent event) {
        pop.show(clubs,JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
    }
    
}
