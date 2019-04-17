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
    private TableView<Comment> allComment;
    @FXML
    private Button Add;
    
    @FXML
    private Button Delete ;
    
    @FXML
    private Button Update ;

    private ObservableList<Comment> comments;
    @FXML
    private TableColumn<Comment, String> com;

    /**
     * Initializes the controller class.
     */
    
    
    
    public void show(){
    
      CommentService ed= CommentService.getInstance();
       comments=FXCollections.observableArrayList();  
       comments=ed.DisplayAll();
       allComment.setItems(comments);
       com.setCellValueFactory(new PropertyValueFactory<>("content"));
       
    
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        show();
       com.setCellValueFactory(new PropertyValueFactory<>("content"));
       
        
       allComment.setOnMouseClicked(event ->{
        comment.setText(comments.get(allComment.getSelectionModel().getSelectedIndex()).getContent());
        
         
       });
           
        
         Add.setOnAction(event -> {
             
            CommentService cd=CommentService.getInstance();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            java.sql.Date sqlDate = java.sql.Date.valueOf(now.toLocalDate());
            Comment o=new Comment(1, 1, 1, comment.getText(),sqlDate , 0);
            cd.insert(o);
            show();
            
            
            
                 
   CommentService ed= CommentService.getInstance();
       comments=FXCollections.observableArrayList();  
       comments=ed.DisplayAll();
       allComment.setItems(comments);
       com.setCellValueFactory(new PropertyValueFactory<>("content"));
       
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Comment");
            alert.setHeaderText(null);
            alert.setContentText("your Comment is added!");
            alert.show();

            });
       Delete.setOnAction(event -> {
           CommentService cd=CommentService.getInstance();
           int i=comments.get(allComment.getSelectionModel().getSelectedIndex()).getId_comment();
           Comment com=cd.DisplayById(i);
           cd.delete(com);
           show();

          
            
            
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Comment");
            alert.setHeaderText(null);
            alert.setContentText("your Comment is Deleted");
            alert.show();

            });
       
       
          
        
       
       Update.setOnAction(event -> {
    
          CommentService com1=CommentService.getInstance();
           int i=comments.get(allComment.getSelectionModel().getSelectedIndex()).getId_comment();
           Comment c=com1.DisplayById(i);
           c.setContent(comment.getText());
           com1.update(c);
           show();
           
     
          
    
    });
        
       
       
       
       
       
       
      
           
    
           
           
       }
               
      
    }



