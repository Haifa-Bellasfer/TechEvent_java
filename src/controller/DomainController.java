/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entity.Domain;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.DomainService;
import utils.Mail;
import utils.Session;

/**
 *
 * @author ihebc_000
 */
public class DomainController implements Initializable {

    @FXML
    private Label label_news;
    @FXML
    private Label label_domain;
    @FXML
    private Label label_article;
    @FXML
    private Label label_subscriber;
    @FXML
    private JFXListView<Domain> lv_domain;
    @FXML
    private Label error;
    @FXML
    private JFXTextField txt_domain;
    @FXML
    private JFXButton btn_Add;
    @FXML
    private JFXButton btn_update;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private JFXTextField txt_search;
    @FXML
    private JFXButton btn_search;
    @FXML
    private Label search_error;
    @FXML
    private JFXButton btn_reset;
    @FXML
    private Label label_newsletter;
    @FXML
    private Label label_users;
    @FXML
    private JFXButton btnlogout;
    @FXML
    private Label label_clubs;
    @FXML
    private Label event;
    @FXML
    private Label Report;

    public void showInterface() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                Session.current_user=null;
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
        label_news.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DomainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //header
        label_domain.setOnMouseClicked((MouseEvent e) -> {
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
        label_article.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Article.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.out.println("article label" + ex.getMessage() + " ");
                ex.printStackTrace();
            }
        });
        label_subscriber.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Subscriber.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DomainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        label_newsletter.setOnMouseClicked(e -> {
            if (Mail.getInstance().SendNewsletter()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Newsletter.");
                alert.setHeaderText(null);
                alert.setContentText("All new newsletter has been sent.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Newsletter.");
                alert.setHeaderText(null);
                alert.setContentText("There is no new newsletter to send.");
                alert.show();
            }
        });

        DomainService ds = new DomainService();
        btn_update.setVisible(false);
        btn_delete.setVisible(false);
        //listView
        lv_domain.getItems().addAll(ds.DisplayAll());
        lv_domain.setOnMouseClicked((MouseEvent event) -> {
            Domain d = lv_domain.getSelectionModel().getSelectedItem();
            txt_domain.setText(d.getNameDomain());
            btn_Add.setVisible(false);
            btn_update.setVisible(true);
            btn_delete.setVisible(true);
            //update
            btn_update.setOnAction(e -> {
                try {
                    d.setNameDomain(txt_domain.getText());
                    boolean i = ds.update(d);
                    if (i) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("update domain");
                        alert.setHeaderText(null);
                        alert.setContentText("Domain update : Success");
                        alert.show();
                        Parent page1 = FXMLLoader.load(getClass().getResource("/view/Domain.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("update domain");
                        alert.setHeaderText(null);
                        alert.setContentText("Domain update : fail");
                        alert.show();
                    }

                } catch (IOException ex) {
                    Logger.getLogger(DomainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            //delete
            btn_delete.setOnAction(e -> {
                try {
                    if (ds.hasArticles(d)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("This domain has articles, it can not be deleted ! Delete his articles before.");
                        alert.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("");
                        alert.setTitle("Delete ?");
                        alert.setContentText("Are you sure you want to delete this?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            ds.delete(d);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/view/Domain.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(DomainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        });
        //add
        btn_Add.setOnAction(e -> {
            if (txt_domain.getText().isEmpty()) {
                error.setText("Domain name can not be empty !!");
            } else {
                Domain domain = new Domain();
                domain.setNameDomain(txt_domain.getText());
                ds.insert(domain);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Domain added");
                alert.setHeaderText(null);
                alert.setContentText("Domain added !!");
                alert.show();

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
            }
        });
        //Search
        btn_search.setOnAction(e -> {
            if (txt_search.getText().isEmpty()) {
                search_error.setText("Domain name can not be empty !!");
            } else {
                lv_domain.getItems().clear();
                lv_domain.getItems().addAll(ds.DisplayByName(txt_search.getText()));
            }
        });
        //Reset
        btn_reset.setOnAction(e -> {
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

    }

}
