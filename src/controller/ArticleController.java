/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entity.Article;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ArticleService;
import service.DomainService;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author ihebc_000
 */
public class ArticleController implements Initializable {

    @FXML
    private Label label_news;
    @FXML
    private Label label_domain;
    @FXML
    private Label label_article;
    @FXML
    private Label label_subscriber;
    @FXML
    private JFXListView lv_article;
    @FXML
    private JFXButton btn_add;
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

    static class CustomCell extends JFXListCell<Article> {

        HBox hbox = new HBox();
        VBox vbox = new VBox();
        Label label = new Label("Title");
        Label domain = new Label("Domain");
        Label nbV = new Label("View number");
        Label dateP = new Label("Publish date");
        ImageView iv = new ImageView();
        Pane pane = new Pane();

        public CustomCell() {
            super();
            label.setStyle("-fx-font-weight: bold;-fx-font-size: 18px;");
            vbox.getChildren().addAll(label, domain, nbV, dateP);
            hbox.getChildren().addAll(iv, vbox, pane);
            HBox.setHgrow(pane, Priority.ALWAYS);
            hbox.setSpacing(10);
        }

        @Override
        public void updateItem(Article a, boolean empty) {
            super.updateItem(a, empty);
            setText(null);
            setGraphic(null);

            if (a != null && !empty) {
                label.setText(a.getTitreArticle());
                domain.setText("Domain : " + a.getDomain().getNameDomain());
                nbV.setText("View Number : " + a.getViewsNumber());
                dateP.setText("Publish date : " + a.getDateOfPublish().toString());
                iv.setImage(new Image("http://localhost/TechEvent/web/uploads/images/" + a.getImage()));
                iv.setFitHeight(70);
                iv.setFitWidth(70);
                setGraphic(hbox);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
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

        ArticleService as = new ArticleService();
        DomainService ds = new DomainService();

        //search
        chDomain.getItems().addAll(ds.DisplayAll());
        chOrderBy.getItems().addAll("View number", "Publish date");
        btnSearch.setOnAction(e -> {
            Domain domain = chDomain.getValue();
            String orderBy = chOrderBy.getValue();
            String keyWord = txtKeyword.getText();
            lv_article.getItems().clear();
            lv_article.getItems().addAll(as.FindByDomainKeywordAndOrderBy(domain, keyWord, orderBy));
        });

        //reset
        btnReset.setOnAction(e -> {
            lv_article.getItems().clear();
            lv_article.getItems().addAll(as.DisplayAll());
        });

//listview
        lv_article.getItems().addAll(as.DisplayAll());
        GridPane pane = new GridPane();
        Label title = new Label("Title");
        Label domain = new Label("Domain");
        JFXButton btn = new JFXButton("Show");
        pane.add(title, 0, 0);
        pane.add(domain, 1, 0);
        pane.add(btn, 2, 0);
        lv_article.setCellFactory(p -> new CustomCell());

        lv_article.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Session.selected_article = (Article) lv_article.getSelectionModel().getSelectedItem();
                try {
                    Parent page = FXMLLoader.load(getClass().getResource("/view/ArticleShow.fxml"));
                    Scene scene = new Scene(page);
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        //add 
        btn_add.setOnAction(e -> {
            try {
                Parent page = FXMLLoader.load(getClass().getResource("/view/ArticleAdd.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
