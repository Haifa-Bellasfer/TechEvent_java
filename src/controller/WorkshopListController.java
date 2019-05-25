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
import entity.User;
import entity.Workshop;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.ClubService;
import service.WorkshopServices;
import utils.Session;


/**
 * FXML Controller class
 *
 * @author mbare
 */
public class WorkshopListController implements Initializable {

    @FXML
    private JFXListView<String> list;
    
    JFXButton Update = new JFXButton("Update");
    JFXButton Cancel = new JFXButton("Cancel");
    VBox pbox = new VBox(Update,Cancel);
    JFXPopup pop = new JFXPopup(pbox);

    
    WorkshopServices ws = WorkshopServices.getInstance();
    ObservableList<Workshop> listW=FXCollections.observableArrayList();
    ObservableList<String> listS=FXCollections.observableArrayList();
    String s;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField nbr;
    @FXML
    private JFXTextArea desc;
    @FXML
    private JFXTextField loc;
    @FXML
    private DatePicker date;
    
    @FXML
    private JFXButton up;
    @FXML
    private JFXButton myWork;
    @FXML
    private JFXButton createWork;
    @FXML
    private JFXButton home;
    User u = new User();
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
    
    public void aff() {
        ClubService CS = ClubService.getInstance();
        ObservableList<Club> ls = FXCollections.observableArrayList();
        
        ls=CS.DisplayAll(Session.current_user.getId_user());
        for (int i = 0; i < ls.size(); i++) {
            
          listW.addAll(ws.DisplayWork(ls.get(i).getId_club()));
        }
        for (int i = 0; i < listW.size(); i++) {
            s = listW.get(i).getTitle();
            listS.add(s);
            list.setItems(listS);
        }
    } 
    
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

        
        
        
        myWork.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/workshopList.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(WorkshopListController.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        
        createWork.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/workshopView.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(WorkshopListController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(WorkshopListController.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        
        
        title.setVisible(false);
        nbr.setVisible(false);
        desc.setVisible(false);
        loc.setVisible(false);
        date.setVisible(false);
        
        up.setVisible(false);
        aff();
        Update.setPadding(new Insets(10));
        Cancel.setPadding(new Insets(10));
        pop.setPopupContent(pbox);
        
        Cancel.setOnAction(event -> {
            System.out.println(listW.get(list.getSelectionModel().getSelectedIndex()).getId_workshop());
            ws.delete(ws.DisplayById(listW.get(list.getSelectionModel().getSelectedIndex()).getId_workshop()));
            pop.hide();
            list.getItems().clear();
            aff();
        });
        
        Update.setOnAction(event3 -> {
           
      
           
            title.setVisible(true);
            nbr.setVisible(true);
            desc.setVisible(true);
            loc.setVisible(true);
            date.setVisible(true);
            
            up.setVisible(true);
            System.out.println(list);
            title.setText(listW.get(list.getSelectionModel().getSelectedIndex()).getTitle());
            desc.setText(listW.get(list.getSelectionModel().getSelectedIndex()).getWorkshop_description());
            nbr.setText(String.valueOf(listW.get(list.getSelectionModel().getSelectedIndex()).getNbr_places()));
            
            loc.setText(listW.get(list.getSelectionModel().getSelectedIndex()).getLocation());
            date.setValue(LocalDate.parse(listW.get(list.getSelectionModel().getSelectedIndex()).getStart_date().toString()));
            pop.hide();
            System.out.println(ws.DisplayById(listW.get(list.getSelectionModel().getSelectedIndex()).getId_workshop()));
            Workshop w = ws.DisplayById(listW.get(list.getSelectionModel().getSelectedIndex()).getId_workshop());
            up.setOnAction(event2 -> {
                
                
                w.setTitle(title.getText());
                w.setWorkshop_description(desc.getText());
                w.setLocation(loc.getText());
                w.setStart_date(Date.valueOf(date.getValue()));
                if (!nbr.getText().isEmpty()) {
                    w.setNbr_places(Integer.parseInt(nbr.getText()));
                }
                
                
                ws.update(w);
                
                list.getItems().clear();
                listS.clear();
                listW.clear();
                aff();
                });
                
                title.setText("");
                desc.setText("");
                loc.setText("");
                date.setValue(LocalDate.now());
                nbr.setText("");
                pop.hide();

              });
                  
    }    

    @FXML
    private void showP(MouseEvent event) {
        pop.show(list,JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
    }
    
}
