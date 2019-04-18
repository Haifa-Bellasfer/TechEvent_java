/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Comment;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import service.CommentService;

/**
 * FXML Controller class
 *
 * @author khaled
 */
public class CommentController implements Initializable {

    @FXML
    private TextArea comment;

    @FXML
    private Button Delete;

    @FXML
    private Button Update;

    private ObservableList<Comment> comments;
    @FXML
    private ListView<Comment> listComment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        CommentService cs = CommentService.getInstance();
   ObservableList<Comment> list = FXCollections.observableArrayList();
   list=(cs.DisplayByIdEventUser(1,1));
        listComment.setItems(list);

       
        
        
           Delete.setOnAction(event -> {
             String content = listComment.getSelectionModel().getSelectedItem().getContent().toString();
             cs.deleteByContent(content);
             listComment.<String>setItems(null);
        listComment.setItems(cs.DisplayByIdEventUser(1,1));
            

          
     
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Comment");
            alert.setHeaderText(null);
            alert.setContentText("your Comment is Deleted");
            alert.show();
            comment.setText("");

            });
        
        
        
        
        
        Update.setOnAction(event -> {

            CommentService com1 = CommentService.getInstance();
            int i = comments.get(listComment.getSelectionModel().getSelectedIndex()).getId_comment();
            Comment c = com1.DisplayById(i);
            c.setContent(comment.getText());
            com1.update(c);

        });

    }

}
