/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import entity.Comment;
import entity.Report;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.CommentService;
import service.ReportService;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author khaled
 */
public class AdminDashborad implements Initializable {

    @FXML
    private Label stat;
    @FXML
    private Pane paneBtn;
    @FXML
    private Label txtContent;
    @FXML
    private Button btnstat;
    @FXML
    private TableView<Comment> tablereport;
    @FXML
    private TableColumn<Comment, String> contenucomm;
    @FXML
    private Text reportcontenu;

    /**
     * Initializes the controller class.
     */
    ObservableList<Comment> listc = FXCollections.observableArrayList();
    CommentService cs = new CommentService();
    @FXML
    private AnchorPane detailscom;
    @FXML
    private Button del;
    @FXML
    private Label label_news;
    @FXML
    private Label label_users;
    @FXML
    private Label label_clubs;
    @FXML
    private Label event;
    @FXML
    private Label Report;
    @FXML
    private JFXButton btnlogout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
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
        //navbar
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

        
        

        listc = cs.ShowAllRep();
        System.out.println(listc);

        contenucomm.setCellValueFactory(new PropertyValueFactory<>("content"));

        tablereport.setItems(listc);

        tablereport.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                Comment cselect = (Comment) newValue;

                reportcontenu.setText(cselect.getContent());
                detailscom.setVisible(true);
            }
        });

        del.setOnAction(event -> {

            CommentService cd = CommentService.getInstance();
            ReportService rep = ReportService.getInstance();
            listc = FXCollections.observableArrayList();
            ObservableList<Report> li = FXCollections.observableArrayList();

            reportcontenu.setText("");
            System.out.println(tablereport.getSelectionModel().getSelectedItem());
            li = rep.Display(tablereport.getSelectionModel().getSelectedItem().getId_comment());
            for (int i = 0; i < li.size(); i++) {
                rep.delete(li.get(i));
            }

            cd.delete(tablereport.getSelectionModel().getSelectedItem());

        });

        btnstat.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/PieChartView.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AdminDashborad.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
}
