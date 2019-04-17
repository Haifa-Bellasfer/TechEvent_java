/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entity.Article;
import entity.Domain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.ArticleService;
import service.DomainService;
import service.SavedService;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author ihebc_000
 */
public class NewsController implements Initializable {

    @FXML
    private Label label_article;
    @FXML
    private Label label_subscriber;
    @FXML
    private JFXTextField txtKeyword;
    @FXML
    private ChoiceBox<Domain> chDomain;
    @FXML
    private ChoiceBox<String> chOrderBy;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnReset;
    @FXML
    private JFXListView<Article> lv_article;
    @FXML
    private JFXButton btnBookmarks;
    @FXML
    private JFXButton btnlogout;
    @FXML
    private JFXButton btnlogin;

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
        ArticleService as = new ArticleService();
        DomainService ds = new DomainService();

        lv_article.getItems().addAll(as.DisplayAll());
        GridPane pane = new GridPane();
        Label title = new Label("Title");
        Label domain = new Label("Domain");
        pane.add(title, 0, 0);
        pane.add(domain, 1, 0);
        lv_article.setCellFactory(p -> new ArticleController.CustomCell());

        chDomain.getItems().addAll(ds.DisplayAll());
        chOrderBy.getItems().addAll("View number", "Publish date");

        btnSearch.setOnAction(e -> {
            Domain dom = chDomain.getValue();
            String orderBy = chOrderBy.getValue();
            String keyWord = txtKeyword.getText();
            lv_article.getItems().clear();
            lv_article.getItems().addAll(as.FindByDomainKeywordAndOrderBy(dom, keyWord, orderBy));
        });

        btnReset.setOnAction(e -> {
            lv_article.getItems().clear();
            lv_article.getItems().addAll(as.DisplayAll());
            txtKeyword.setText("");
            chDomain.setValue(null);
            chOrderBy.setValue(null);
        });

        lv_article.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Session.selected_article = (Article) lv_article.getSelectionModel().getSelectedItem();
                int nb = Session.selected_article.getViewsNumber() + 1;
                Session.selected_article.setViewsNumber(nb);
                as.updateViewNumber(Session.selected_article);
                try {
                    Parent page = FXMLLoader.load(getClass().getResource("/view/NewsShow.fxml"));
                    Scene scene = new Scene(page);
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println("news controller, double click action " + ex.getMessage());
                }
            }
        });
        btnlogout.setVisible(false);
        btnlogin.setOnAction(e -> {
            
        });
        btnBookmarks.setVisible(false);
        if (Session.current_user != null) {
            btnBookmarks.setVisible(true);
            btnBookmarks.setOnAction(e -> {
                lv_article.getItems().clear();
                lv_article.setItems(SavedService.getInstance().DisplayByIdUser(Session.current_user.getId_user()));

            });
            btnlogin.setVisible(false);
            btnlogout.setVisible(true);
            btnlogout.setOnAction(e -> {
            
            });
        }
    }

}
