/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import entity.Event;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.EventService;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author ihebc_000
 */
public class Home2Controller implements Initializable {

    private Label label_news;
    @FXML
    private JFXListView<Event> AllEvent;
    private ObservableList<Event> list;
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

    static class cell extends ListCell<Event> {

        HBox box = new HBox(70);

        Label lab = new Label();
        Label lab1 = new Label();
        Label lab2 = new Label();
        Label lab3 = new Label();
        Label lab4 = new Label();
        Label lab5 = new Label();
        JFXButton btn3 = new JFXButton("Participate");
        Pane p = new Pane();

        ImageView eventpicc = new ImageView();

        public cell() {
            super();
            box.getChildren().addAll(eventpicc, lab, lab4, lab5, lab2, lab3, p);
            box.setHgrow(p, Priority.ALWAYS);
        }

        @Override
        public void updateItem(Event event, boolean empty) {

            super.updateItem(event, empty);
            setText(null);
            setGraphic(null);
            if (event != null && !empty) {
                Image im = new Image("http://localhost/TechEvent/web/img/uploads/" + event.getPhoto());
                eventpicc.setImage(im);
                eventpicc.setFitWidth(60);
                eventpicc.setFitHeight(60);
                lab.setText(event.getEvent_name());
                lab5.setText(event.getAddress());
                lab3.setText(event.getStart_date().toString());

                setGraphic(box);

            }

        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         
        
        label_event.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Home2.fxml"));
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
                System.out.println( ex.getMessage());
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
        
        
        

        EventService ed = EventService.getInstance();
        list = FXCollections.observableArrayList();
        ObservableList<Event> Local = FXCollections.observableArrayList();
        list = ed.DisplayAll();
        for (int i =0; i < list.size(); i++) {
            if (list.get(i).getStatus().equalsIgnoreCase("ACCEPTED")) {
                Local.add(list.get(i));
            }

        }

        AllEvent.setItems(Local);
        AllEvent.setCellFactory(s -> new Home2Controller.cell());

    }

}
