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
    private Label txtContent;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXButton btnAddBook;
    @FXML
    private JFXButton btnRemoveBook;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label_article.setOnMouseClicked((MouseEvent e) -> {
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
        btnAddBook.setVisible(false);
        btnRemoveBook.setVisible(false);
        ArticleService as = new ArticleService();

        //showArticle
        txtTitle.setText(Session.selected_article.getTitreArticle());
        txtContent.setText(Session.selected_article.getContentArticle());
        txtContent.setWrapText(true);
        imageV.setImage(new Image("http://localhost/TechEvent/web/uploads/images/" + Session.selected_article.getImage()));

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

        if (Session.current_user != null) {
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
            }

        }
    }

}
