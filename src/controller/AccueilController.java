/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import entity.Cart;
import entity.Event;
import entity.Order_Line;
import entity.Tickett;
import service.EventService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.CartService;
import service.Order_LineService;
import service.TicketService;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Dorsaf
 */
public class AccueilController implements Initializable {

    @FXML
    private ImageView addevent;
    @FXML
    private ImageView myevents;
    @FXML
    private ImageView Favorit;

    private ObservableList<Event> Allevent;

    @FXML
    private ListView<Event> allevent;

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

    public void affiche(MouseEvent event) {
        if (event.getClickCount() == 2) {

            try {
                Session.current_event = allevent.getSelectionModel().getSelectedItem();

                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Description.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    static class CustumCell extends ListCell<Event> {

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

        public CustumCell() {
            super();
            box.getChildren().addAll(eventpicc, lab, lab4, lab5, lab2, lab3, p, btn3);
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

                btn3.setOnAction(ev -> {
                    CartService cs = CartService.getInstance();
                    Cart c = new Cart(1, Session.current_user.getId_user(), 0);
                    cs.insert(c);

                    try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("/view/Cart.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    EventService e = EventService.getInstance();
                    Event myevent = e.DisplayById(event.getId_event());
                    System.out.println(myevent);

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY/MM/DD HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    java.sql.Date sqDate = Date.valueOf(now.toLocalDate());

                    TicketService td = TicketService.getInstance();
                    Tickett t = new Tickett(1, Session.current_user.getId_user(), myevent.getId_event(), sqDate, 1, "qr", myevent.getNb_participant());

                    td.insert(t);
                    Tickett t1 = td.DisplayByIdEventTicket(myevent.getId_event());

                    Order_LineService od = Order_LineService.getInstance();
                    System.out.println(t1.getId_ticket());
                    Order_Line l = new Order_Line(1, t1.getId_ticket(), c.getId_cart(), myevent.getNb_participant(), myevent.getPrice_ticket());
                    System.out.println(l);
                    od.insert(l);

                });

                setGraphic(box);

            }

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
                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        EventService ed = EventService.getInstance();
        Allevent = FXCollections.observableArrayList();
        ObservableList<Event> Local = FXCollections.observableArrayList();
        Allevent = ed.DisplayAll();
        for (int i = 0; i < Allevent.size(); i++) {
            if (Allevent.get(i).getStatus().equalsIgnoreCase("ACCEPTED")) {
                Local.add(Allevent.get(i));
            }

        }

        allevent.setItems(Local);
        allevent.setCellFactory(s -> new CustumCell());

        addevent.setPickOnBounds(true);
        addevent.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AddEvent.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        myevents.setPickOnBounds(true);
        myevents.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/MyEventList.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        Favorit.setPickOnBounds(true);
        Favorit.setOnMouseClicked((MouseEvent e) -> {
            try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Myevent.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }


}
