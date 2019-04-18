/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entity.Club;
import entity.ClubUser;
import entity.Theme;

import java.awt.Panel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.ClubService;
import service.MemberService;
import service.ThemeServices;
import service.UserService;
import utils.Session;
import view.mainFx;

/**
 * FXML Controller class
 *
 * @author mbare
 */
public class ClubListViewController implements Initializable {

    @FXML
    private TableView<Club> listV;

    /**
     * Initializes the controller class.
     */
    ClubService CS = ClubService.getInstance();
    Club c;
    int a;
    
    @FXML
    private TableColumn<Club, String> nameCol;
    //private Pane upPan = new Pane();
    @FXML
    private Pane upPop= new Pane();
    @FXML
    private JFXTextField text;
    @FXML
    private JFXButton btnUP;
    @FXML
    private JFXTextArea desc;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField fb;
    @FXML
    private JFXComboBox<Theme> th = new JFXComboBox<>();
    @FXML
    private TableView<ClubUser> members;
    @FXML
    private TableColumn<ClubUser,String > skills;
    @FXML
    private TableColumn<ClubUser, String> why;
    @FXML
    private TableColumn<ClubUser, String> status;
    @FXML
    private TableColumn<ClubUser, String> he;
    private Label create;
    private Label My;
    @FXML
    private Label home;
    @FXML
    private Label myWork;
    @FXML
    private Label createWork;
    @FXML
    private Label Clubs;
    
    
    public void affC(){
            ObservableList<Club> ls = FXCollections.observableArrayList();
            int owner=1;
        ls=CS.DisplayAll(owner);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("club_name"));
        listV.setItems(ls);
        
    }
    
    JFXButton b1 = new JFXButton("Update                  ");
    JFXButton b2 = new JFXButton("Close                     ");
    JFXButton b3 = new JFXButton("Show members list");
    
    JFXButton acc = new JFXButton("Change status");
    JFXButton fire = new JFXButton("Fire");
    MemberService m = MemberService.getInstance();
    VBox pbox = new VBox(b1,b2,b3);
    VBox pbox2 = new VBox(acc,fire) ;
    JFXPopup pop = new JFXPopup(pbox);
    JFXPopup pop2 = new JFXPopup(pbox2);
    public void initPopup(){

    b1.setPadding(new Insets(10));
    b2.setPadding(new Insets(10));
    b3.setPadding(new Insets(10));
    pop.setPopupContent(pbox);
    pop2.setPopupContent(pbox2);
    
    
    }
    public void pop2(){
        
        acc.setOnAction(event ->{
            upPop.setVisible(true);
            ClubUser cu = new ClubUser();
            UserService us = UserService.getInstance();
             cu=members.getSelectionModel().getSelectedItem();
             
             
             String num="+216"+us.DisplayById(members.getSelectionModel().getSelectedItem().getMember_id()).getPhone();
             String msg =CS.DisplayById(cu.getClub_id()).getClub_name();
             if ("Accepted".equals(cu.getClub_user_status())) {
                cu.setClub_user_status("Refused");
                try {
			// Construct data
			String apiKey = "apikey=" + "MVLOno+0VZM-jpaVhscxc0tBilKDFJXZa6jVxGuoEd";
			String message = "&message=" + msg+" wants to inform you that your membership request has been refused";
			String sender = "&sender=" + "Admin";
			String numbers = "&numbers=" +num;
			
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
            }else if ("Refused".equals(cu.getClub_user_status())){
             cu.setClub_user_status("Accepted");
             try {
			// Construct data
			String apiKey = "apikey=" + "MVLOno+0VZM-y2GuYuU0QFsk10BeBFvXBoI9vyhI0P";
			String message = "&message=" + msg+" wants to inform you that your membership request has been accepted !! welcome to our team";
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
             
             members.setVisible(true);
            upPop.setVisible(false);
            
            m.update(cu);
            ObservableList<ClubUser> ls = FXCollections.observableArrayList();
           
            ls=m.DisplayAll(listV.getSelectionModel().getSelectedItem().getId_club());
            pop2.hide();

            members.setItems(ls);
            
            pop2.setPopupContent(pbox2);
        });
        fire.setOnAction(event2 ->{
            ClubUser cu = new ClubUser();
             cu=members.getSelectionModel().getSelectedItem();
             m.delete(cu);
            members.setVisible(true);
            upPop.setVisible(true);
            ObservableList<ClubUser> ls = FXCollections.observableArrayList();
            
            ls=m.DisplayAll(listV.getSelectionModel().getSelectedItem().getId_club());
            pop2.hide();
            members.setItems(ls);
            pbox2 = new VBox(acc);
            pop2.setPopupContent(pbox2);
        });
    }
    
    
    public void memberList(){
       

        b3.setOnAction(event ->{
            upPop.setVisible(false);
            members.setVisible(true);
            
            
            
            ObservableList<ClubUser> ls = FXCollections.observableArrayList();

            ls=m.DisplayAll(listV.getSelectionModel().getSelectedItem().getId_club());
            pop.hide();
            
            why.setCellValueFactory(new PropertyValueFactory<>("why"));
            status.setCellValueFactory(new PropertyValueFactory<>("club_user_status"));
            he.setCellValueFactory(new PropertyValueFactory<>("you_are"));
            skills.setCellValueFactory(new PropertyValueFactory<>("skills"));
            members.setItems(ls);
            pop2();
        });
        
    }
    ThemeServices TS = ThemeServices.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        myWork.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/WorkshopList.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(mainFx.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        
        createWork.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/WorkshopView.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(mainFx.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        home.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/ClubAcc.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(mainFx.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        ObservableList<Theme> ls = FXCollections.observableArrayList();
        ls=TS.DisplayAll();
        th.getItems().addAll(ls);
        members.setVisible(false);
        upPop.setVisible(false);
        affC();
        upC();
        memberList();
        pop2();
        initPopup();
        b2.setOnAction(event -> {
          
                int cc = listV.getSelectionModel().getSelectedItem().getId_club();
                c= CS.DisplayById(cc);
                if (CS.Exist(cc)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Be carefull");
                    alert.setHeaderText(null);
                    alert.setContentText("You have to delete the members related to this club first");
                    alert.show();
             }else {
                CS.delete(c);
             } 
               
                affC();
                pop.hide();
            
        });
    }    

    @FXML
    private void showPop(MouseEvent event) {
        pop.show(listV,JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
    }


    public void upC(){
        
      b1.setOnAction(event -> {
           
           int i;
           Club d;
           d = listV.getSelectionModel().getSelectedItem();
           members.setVisible(false);
        upPop.setVisible(true);
        text.setVisible(true);
        desc.setVisible(true);
        upPop.setVisible(true);
        mail.setVisible(true);
        btnUP.setVisible(true);
        fb.setVisible(true);
        text.setText(listV.getSelectionModel().getSelectedItem().getClub_name());
        desc.setText(listV.getSelectionModel().getSelectedItem().getClub_description());
        mail.setText(listV.getSelectionModel().getSelectedItem().getEmail());
        fb.setText(listV.getSelectionModel().getSelectedItem().getFacebook());
        //i =listV.getSelectionModel().getSelectedItem().getTheme();
          //System.out.println(i);
        //th.setValue(TS.DisplayById(i));
        btnUP.setOnAction(event2 -> {

        d.setClub_name(text.getText());
        d.setClub_description(desc.getText());
        d.setEmail(mail.getText());
        d.setFacebook(fb.getText());
            if (!th.getSelectionModel().isEmpty()) {
                d.setTheme(th.getSelectionModel().getSelectedItem().getId_theme());
            }else{
            d.setTheme(d.getTheme());
            }
        
        CS.update(d);
        //TS.update(os);
        affC();
        });
        
        pop.hide();
        affC();
      });
    }

    @FXML
   private void pop2Show(MouseEvent event) {
       
        pop2.show(members,JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
    }}
    