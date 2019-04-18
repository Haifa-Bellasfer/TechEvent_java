/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.CommentService;
import service.ReportService;

/**
 * FXML Controller class
 *
 * @author khaled
 */
public class AdminDashborad implements Initializable {

    @FXML
    private Label txt;
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
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listc=cs.ShowAllRep();
                System.out.println(listc);
                
   
         contenucomm.setCellValueFactory(new PropertyValueFactory<>("content"));

        tablereport.setItems(listc);
 
        
          tablereport.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() { 
    
             

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                Comment cselect = (Comment)newValue;
             
          
             reportcontenu.setText(cselect.getContent());
             detailscom.setVisible(true);
            }
      });
    

        
        del.setOnAction(event -> {
            
              CommentService cd=CommentService.getInstance();
              ReportService rep= ReportService.getInstance();
              listc = FXCollections.observableArrayList();
              ObservableList<Report> li = FXCollections.observableArrayList();
             
             
               reportcontenu.setText(""); 
               System.out.println(tablereport.getSelectionModel().getSelectedItem());
               li=rep.Display(tablereport.getSelectionModel().getSelectedItem().getId_comment());
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

