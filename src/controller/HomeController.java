/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Domain;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.DomainService;
import utils.Mail;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author ihebc_000
 */
public class HomeController implements Initializable {

    @FXML
    private Label label_news;
    @FXML
    private Label label_visitor;
    @FXML
    private Label label_user;
    @FXML
    private VBox vbox;
    @FXML
    private Label label_domain;
    @FXML
    private Label label_article;
    @FXML
    private Label label_subscriber;
    @FXML
    private Label label_newsletter;

    /**
     * Initializes the controller class.
     */
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
                Logger.getLogger(DomainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        label_visitor.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/News.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DomainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        label_user.setOnMouseClicked((MouseEvent e) -> {
            try {
                Session.current_user = new User(1);
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/News.fxml"));
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

        //chart      
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Domains");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("No.");
        BarChart barChartViewsByDomain = new BarChart(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();
        series.setName("No. views by domain");
        for (Domain dom : DomainService.getInstance().DisplayAll()) {
            for (Map.Entry m : DomainService.getInstance().statDomainByViewNumber(dom).entrySet()) {
                series.getData().add(new XYChart.Data(m.getValue(), m.getKey()));
            }
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("No. Saved by Domain");
        for (Domain dom : DomainService.getInstance().DisplayAll()) {
            for (Map.Entry m : DomainService.getInstance().statDomainBySaved(dom).entrySet()) {
                series2.getData().add(new XYChart.Data(m.getValue(), m.getKey()));
            }
        }

        barChartViewsByDomain.getData().addAll(series, series2);
        barChartViewsByDomain.setAnimated(true);
        barChartViewsByDomain.setBarGap(10);
        vbox.getChildren().addAll(barChartViewsByDomain);

    }

}
