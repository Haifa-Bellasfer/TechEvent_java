/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entity.Article;
import entity.Domain;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import service.ArticleService;
import service.DomainService;
import utils.Mail;
import utils.UploadImage;

/**
 * FXML Controller class
 *
 * @author ihebc_000
 */
public class ArticleAddController implements Initializable {

    @FXML
    private Label label_news;
    @FXML
    private Label label_domain;
    @FXML
    private Label label_article;
    @FXML
    private Label label_subscriber;
    @FXML
    private JFXTextField txt_title;
    @FXML
    private JFXButton btn_add;
    @FXML
    private JFXButton btn_cancel;
    @FXML
    private JFXButton btn_browse;
    @FXML
    private JFXTextArea txt_content;
    @FXML
    private Label errorTitle;
    @FXML
    private Label errorContent;
    @FXML
    private Label errorPicture;
    @FXML
    private ChoiceBox<Domain> ch_domain;
    @FXML
    private Label errorDomain;
    @FXML
    private ImageView imageV;
    @FXML
    private Label label_newsletter;
    //added
    @FXML
    private Label path;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        path.setVisible(false);

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
                Logger.getLogger(ArticleAddController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ArticleAddController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ArticleAddController.class.getName()).log(Level.SEVERE, null, ex);
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
        //domain
        DomainService ds = new DomainService();
        ch_domain.getItems().addAll(ds.DisplayAll());
        ch_domain.getSelectionModel().selectFirst();

//choose file
        //add
        btn_add.setOnAction(e -> {
            errorTitle.setText("");
            errorContent.setText("");
            errorPicture.setText("");
            errorDomain.setText("");
            if ((txt_title.getText().isEmpty()) || (txt_content.getText().isEmpty()) || (btn_browse.getText().compareTo("Choose file ...") == 0) || (ch_domain.getSelectionModel().isEmpty())) {
                if (txt_title.getText().isEmpty()) {
                    errorTitle.setText("Title can not be empty !!");
                }
                if (txt_content.getText().isEmpty()) {
                    errorContent.setText("Content can not be empty !!");
                }
                if (btn_browse.getText().compareTo("Choose file ...") == 0) {
                    errorPicture.setText("You have to choose a picture !!");
                }
                if (ch_domain.getSelectionModel().isEmpty()) {
                    errorDomain.setText("You have to choose a domain !!");
                }
            } else {
                try {
                    Article a = new Article();
                    a.setTitreArticle(txt_title.getText());
                    a.setContentArticle(txt_content.getText());
                    Date date = new Date(System.currentTimeMillis());
                    a.setDateOfPublish(date);
                    a.setViewsNumber(0);
                    a.setDomain(ch_domain.getSelectionModel().getSelectedItem());
                    String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(32), "jpg");
                    UploadImage.getInstance().upload(path.getText(), name);
                    a.setImage(name);
                    ArticleService as = new ArticleService();
                    as.insert(a);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("add article");
                    alert.setHeaderText(null);
                    alert.setContentText("Article add : Success");
                    alert.show();

                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/Article.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        btn_cancel.setOnAction(e -> {
            try {
                Parent page = FXMLLoader.load(getClass().getResource("/view/Article.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ArticleAddController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    public Image Browse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pictures", "*.png", "*.jpeg", "*.jpg"));
        File file = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            btn_browse.setText(file.getName());
        }
        Image image = new Image(file.toURI().toString());
        imageV.setImage(image);
        path.setText(file.getAbsolutePath());
        return image;
    }

}
