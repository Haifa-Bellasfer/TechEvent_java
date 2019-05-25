/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import entity.Saved;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.ArticleService;
import service.SavedService;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author ihebc_000
 */
public class NewsShowController implements Initializable {

    @FXML
    private Label label_article;
    @FXML
    private Label label_subscriber;
    @FXML
    private Label txtTitle;
    @FXML
    private ImageView imageV;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXButton btnAddBook;
    @FXML
    private JFXButton btnRemoveBook;
    @FXML
    private Label txtContent;
    @FXML
    private Label label_event;
    @FXML
    private Label label_club;
    @FXML
    private Label label_profile;
    @FXML
    private JFXButton btnlogout;
    @FXML
    private JFXButton btnlogin;

    /**
     * Initializes the controller class.
     */
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

        btnAddBook.setVisible(false);
        btnRemoveBook.setVisible(false);
        ArticleService as = new ArticleService();

        //showArticle
        txtTitle.setText(Session.selected_article.getTitreArticle());
        txtContent.setText(Session.selected_article.getContentArticle());
        txtContent.setWrapText(true);
        imageV.setImage(new Image("http://127.0.0.1/TechEvent/web/uploads/images/" + Session.selected_article.getImage()));

        btnBack.setOnAction(e -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/News.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        label_profile.setVisible(false); //1
        if (Session.current_user != null) {
            label_profile.setVisible(true); //1
            if (!SavedService.getInstance().isSaved(Session.selected_article, Session.current_user)) {
                btnAddBook.setVisible(true);
                btnAddBook.setOnAction(e -> {
                    Saved s = new Saved();
                    s.setDateSave(new Date(System.currentTimeMillis()));
                    s.setArticle(Session.selected_article);
                    s.setUser(Session.current_user);
                    SavedService.getInstance().insert(s);
                    try {
                        Parent page = FXMLLoader.load(getClass().getResource("/view/NewsShow.fxml"));
                        Scene scene = new Scene(page);
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                });
            } else {
                btnRemoveBook.setVisible(true);
                btnRemoveBook.setOnAction(e -> {
                    Saved s = SavedService.getInstance().geSavedByArticleAndUser(Session.selected_article, Session.current_user);
                    SavedService.getInstance().delete(s);
                    try {
                        Parent page = FXMLLoader.load(getClass().getResource("/view/NewsShow.fxml"));
                        Scene scene = new Scene(page);
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                });
            }
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
    }

}
