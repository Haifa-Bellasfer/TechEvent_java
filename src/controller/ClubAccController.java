/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import entity.Club;
import entity.ClubUser;
import entity.Theme;

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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.ClubService;
import service.MemberService;
import service.ThemeServices;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author mbare
 */
public class ClubAccController implements Initializable {

    @FXML
    private JFXListView<String> list;

    ObservableList<Club> ls = FXCollections.observableArrayList();
    ObservableList<String> ss = FXCollections.observableArrayList();
    ClubService cs = ClubService.getInstance();
    MemberService cc = MemberService.getInstance();
    @FXML
    private JFXButton bt;
    @FXML
    private JFXTextArea why;
    @FXML
    private JFXTextArea skills;
    @FXML
    private JFXComboBox<String> you;
    @FXML
    private JFXButton submit;
    private TextField search;
    private JFXButton ser;
    private ComboBox<Theme> seC;
    @FXML
    private JFXButton create;
    @FXML
    private JFXButton My;
    @FXML
    private JFXButton home;
    //private ComboBox<Theme> searchT;
    @FXML
    private JFXButton subS;
    @FXML
    private ComboBox<Theme> searchTheme;
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

    @FXML
    private void join(MouseEvent event) {
        if (Session.current_user == null) {
            bt.setVisible(false);
        } else {
            bt.setVisible(true);
        }
    }
    ThemeServices TS = ThemeServices.getInstance();

    ObservableList<Theme> th = FXCollections.observableArrayList();

    ObservableList<String> y = FXCollections.observableArrayList("Student", "Organization", "pro");

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        label_event.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Home2.fxml"));
                if (Session.current_user != null) {
                    page1 = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
                }
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

        wrk.setVisible(false);
        My.setVisible(false);
        create.setVisible(false);
        if (Session.current_user != null) {
            wrk.setVisible(true);
            home.setVisible(true);
            My.setVisible(true);
            create.setVisible(true);
        }

        wrk.setOnMouseClicked(ev -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/WorkshopAffich.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(ClubAccController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        create.setOnMouseClicked(ev -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Club2.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(ClubAccController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        My.setOnMouseClicked(ev -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ListClub.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(ClubAccController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        home.setOnMouseClicked(ev -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ClubAcc.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(ClubAccController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        th = TS.DisplayAll();
        searchTheme.getItems().addAll(th);

        bt.setVisible(false);
        why.setVisible(false);
        skills.setVisible(false);
        you.setVisible(false);
        submit.setVisible(false);

        ls = cs.DisplayAll();
        for (int i = 0; i < ls.size(); i++) {
            String s = ls.get(i).affiche();
            ss.add(s);
            list.setItems(ss);
        }

        bt.setOnAction(e -> {
            bt.setVisible(false);
            list.setVisible(false);
            why.setVisible(true);
            skills.setVisible(true);
            you.setVisible(true);
            you.setItems(y);
            submit.setVisible(true);

            submit.setOnAction(ev -> {

                if ((why.getText().isEmpty())
                        || (you.getValue().isEmpty())
                        || (skills.getText().isEmpty())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Info");
                    alert.setHeaderText(null);
                    alert.setContentText("Data invalid");
                    alert.show();
                } else {

                    ClubUser cu = new ClubUser(why.getText(), you.getValue(), skills.getText(), Session.current_user.getId_user(), "Waiting", ls.get(list.getSelectionModel().getSelectedIndex()).getId_club());
                    cc.insert(cu);
                    bt.setVisible(false);
                    list.setVisible(true);
                    why.setVisible(false);
                    skills.setVisible(false);
                    you.setVisible(false);
                    submit.setVisible(false);

                    ls = cs.DisplayAll();
                    for (int i = 0; i < ls.size(); i++) {
                        String s = ls.get(i).affiche();
                        ss.add(s);
                        list.setItems(ss);
                    }
                }
            });

        });
        subS.setOnAction(e -> {

            ObservableList<Club> searchT = FXCollections.observableArrayList();
            searchT = cs.searchByTheme(searchTheme.getValue());
            //System.out.println(searchT);
            list.getItems().clear();
            for (int i = 0; i < searchT.size(); i++) {
                String s = searchT.get(i).affiche();
                ss.add(s);

                list.setItems(ss);
            }
        });

    }

}
