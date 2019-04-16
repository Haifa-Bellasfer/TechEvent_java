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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.DomainService;

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

    public void showInterface() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                Logger.getLogger(DomainController.class.getName()).log(Level.SEVERE, null, ex);
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
                        ds.delete(d);
                        Parent page1 = FXMLLoader.load(getClass().getResource("/view/Domain.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
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
