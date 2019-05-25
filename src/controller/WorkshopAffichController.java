/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import entity.Club;
import entity.Workshop;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.ClubService;
import service.MemberService;
import service.ThemeServices;
import service.WorkshopServices;
import utils.Session;


/**
 * FXML Controller class
 *
 * @author mbare
 */
public class WorkshopAffichController implements Initializable {

    @FXML
    private JFXListView<String> listW;
    WorkshopServices WS = WorkshopServices.getInstance();
    ClubService cs = ClubService.getInstance();
    MemberService uc = MemberService.getInstance();
    ObservableList<Workshop> ls = FXCollections.observableArrayList();
    ObservableList<Workshop> work = FXCollections.observableArrayList();
    ObservableList<String> ss = FXCollections.observableArrayList();
    @FXML
    private JFXButton part;
    @FXML
    private JFXButton cancel;
    private Label myWork;
    private Label createWork;
    @FXML
    private JFXButton home;
    @FXML
    private JFXButton create;
    @FXML
    private JFXButton My;
    @FXML
    private JFXButton wrk;
    @FXML
    private Label label_article;
    @FXML
    private Label label_event;
    @FXML
    private Label label_club;
    @FXML
    private Label label_subscriber;
    @FXML
    private Label label_profile;
    @FXML
    private JFXButton btnlogout;
    @FXML
    private JFXButton btnlogin;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label_event.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        
        
        label_club.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ClubAcc.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.out.println("news controller, label club action " + ex.getMessage());
            }
        });

        label_article.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/News.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.out.println("news controller, label artilce action " + ex.getMessage());
            }
        });
        label_subscriber.setOnMouseClicked(e -> {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/Subs.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Subscribe to our newsletter");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node) e.getSource()).getScene().getWindow());
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.out.println("news controller, label artilce action " + ex.getMessage());
            }
        });

        btnlogout.setVisible(false);
        btnlogin.setOnAction(e -> {
            try {
                Parent page = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        label_profile.setVisible(false); //1
        if (Session.current_user != null) {
            label_profile.setVisible(true); //1
            btnlogin.setVisible(false);
            btnlogout.setVisible(true);
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
            label_profile.setOnMouseClicked(e -> {
                try {
                    Parent page = FXMLLoader.load(getClass().getResource("/view/Profile.fxml"));
                    Scene scene = new Scene(page);
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        
        
        wrk.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/WorkshopAffich.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(WorkshopAffichController.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        
        
        create.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/Club2.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(WorkshopAffichController.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        
        My.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/ListClub.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(WorkshopAffichController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(WorkshopAffichController.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        
        part.setVisible(false);
        cancel.setVisible(false);
        int connectedUser=Session.current_user.getId_user();
        ls=WS.DisplayAll();
        //System.out.println(ls.size());
        System.out.println(uc.DisplayMember(connectedUser)); 
        for (int i = 0; i < uc.DisplayMember(connectedUser).size(); i++) {
            
           work.addAll(WS.DisplayWork(uc.DisplayMember(connectedUser).get(i).getClub_id()));
            System.out.println(uc.DisplayMember(connectedUser).get(i).getClub_id());
        }
        //cs.DisplayById(uc.DisplayMember(connectedUser).get(1).getClub_id());
        
        
        for (int i = 0; i < work.size(); i++) {
            
            String s =work.get(i).affiche();
            ss.add(s);
            listW.setItems(ss);
        }
        part.setOnAction(e->{
            Workshop w = WS.DisplayById(work.get(listW.getSelectionModel().getSelectedIndex()).getId_workshop());
            if (w.getNbr_places()>0) {
                w.setNbr_places(w.getNbr_places()-1);
                WS.update(w);
                listW.getItems().clear();
                work.clear();
                for (int i = 0; i < uc.DisplayMember(connectedUser).size(); i++) {

                work.addAll(WS.DisplayWork(uc.DisplayMember(connectedUser).get(i).getClub_id()));
                System.out.println(uc.DisplayMember(connectedUser).get(i).getClub_id());
                }
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Info");
                    alert.setHeaderText(null);
                    alert.setContentText("No more available places  ");
                    alert.show();       
                        }
        //cs.DisplayById(uc.DisplayMember(connectedUser).get(1).getClub_id());
        
        
        for (int i = 0; i < work.size(); i++) {
            String s =work.get(i).affiche();
            ss.add(s);
            listW.setItems(ss);
            part.setVisible(false);
            cancel.setVisible(true);
        }
  
        });
        cancel.setOnAction(e->{
            Workshop w = WS.DisplayById(work.get(listW.getSelectionModel().getSelectedIndex()).getId_workshop());
            w.setNbr_places(w.getNbr_places()+1);
            WS.update(w);
            //work=WS.DisplayAll();
            listW.getItems().clear();
            work.clear();
            for (int i = 0; i < uc.DisplayMember(connectedUser).size(); i++) {
            
           work.addAll(WS.DisplayWork(uc.DisplayMember(connectedUser).get(i).getClub_id()));
            System.out.println(uc.DisplayMember(connectedUser).get(i).getClub_id());
        }
        //cs.DisplayById(uc.DisplayMember(connectedUser).get(1).getClub_id());
        
        
        for (int i = 0; i < work.size(); i++) {
            String s =work.get(i).affiche();
            ss.add(s);
            listW.setItems(ss);
            part.setVisible(true);
            cancel.setVisible(false);
        }
        
        });
        
        
    }    

    @FXML
    private void showP(MouseEvent event) {
        if (event.getClickCount()==2) {
            part.setVisible(true);
        }
        
        
    }
    
}
