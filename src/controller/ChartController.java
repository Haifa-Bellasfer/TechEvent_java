/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.exceptions.MySQLDataException;
import entity.Category;
import entity.Event;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.CategoryService;
import service.EventService;
import service.User_CategoryService;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class ChartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PieChart pieChart;
    ObservableList<Data> list = FXCollections.
            observableArrayList();
    @FXML
    private JFXComboBox<Category> category;
    private ObservableList<Category> catlist = FXCollections.observableArrayList();
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton update;
    @FXML
    private JFXTextField text;
    @FXML
    private Label Archive;
    @FXML
    private Label events;
    @FXML
    private Label Home;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Archive.setOnMouseClicked((MouseEvent e) -> {
            try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Archive.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        events.setOnMouseClicked((MouseEvent e) -> {
            try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/view/UserEvents.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        Home.setOnMouseClicked((MouseEvent e) -> {
            try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        EventService pdao = EventService.getInstance();
        List<Event> pers = pdao.DisplayAll();
        for (Event p : pers) {
            list.addAll(
                    new Data(p.getEvent_name() + " Likes :" + p.getNb_like(), p.getNb_like())
            );
        }
        pieChart.setAnimated(true);
        pieChart.setData(list);

        CategoryService c = CategoryService.getInstance();
        catlist = c.DisplayAll();
        category.getItems().addAll(catlist);

        //text.setText(this.category.getSelectionModel().getSelectedItem().toString());
        System.out.println(this.category.getSelectionModel().getSelectedItem());

        add.setOnAction(event -> {
            Category cat = new Category(1, text.getText());
            c.insert(cat);
            category.setItems(null);
            catlist = c.DisplayAll();
            category.setItems(catlist);

        });

        update.setOnAction(event -> {

            int cId = catlist.get(category.getSelectionModel().getSelectedIndex()).getId_category();
            Category c1 = c.DisplayById(cId);
            c1.setCategory_name(text.getText());
            c.update(c1);
            category.setItems(null);
            catlist = c.DisplayAll();
            category.setItems(catlist);

        });

        delete.setOnAction((ActionEvent event) -> {
            EventService ed = EventService.getInstance();
            ObservableList<Event> Local = FXCollections.observableArrayList();
            User_CategoryService uc=User_CategoryService.getInstance();
            
            ObservableList<Event> LocalCat = FXCollections.observableArrayList();
            int cId = catlist.get(category.getSelectionModel().getSelectedIndex()).getId_category();
            Local = ed.DisplayAllEvent(cId);
            
            Category c1 = c.DisplayById(cId);
   
            category.setItems(null);
            catlist = c.DisplayAll();
            category.setItems(catlist);
            System.out.println(Local.size());
            
                if(Local.size() == 0){
                c.delete(c1);
                }else{

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("First You need to delete All the event Categories");
                alert.show();
                }
           

        });

        events.setOnMouseClicked(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/UserEvents.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ObservableList<Event> ev = FXCollections.observableArrayList();
            EventService es = EventService.getInstance();

            int nb = es.DisplayCountrep();
            Notifications NotifBuild = Notifications.create().title("Notify").text("Hello Manager you have " + nb + " Events Suggestion ")
                    .graphic(null).hideAfter(javafx.util.Duration.seconds(10)).position(Pos.TOP_RIGHT)
                    .onAction((ActionEvent event1) -> {
                    });
            NotifBuild.showConfirm();
        });

    }

    @FXML
    private void archive(MouseEvent event) {

        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/view/Archive.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
