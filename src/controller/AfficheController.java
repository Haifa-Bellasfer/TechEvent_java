/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Comment;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import service.CommentService;

/**
 * FXML Controller class
 *
 * @author khaled
 */
public class AfficheController implements Initializable {

    @FXML
    private ListView<Comment> allComent;
    @FXML
    private TextArea myComment;
    @FXML
    private Button add;

    private ObservableList<Comment> list = FXCollections.observableArrayList();

    static class cell extends ListCell<Comment> {

        HBox box = new HBox();

        Label lab = new Label();
        Button btn = new Button("Report");
        Pane p = new Pane();

        public cell() {
            super();
            box.getChildren().addAll(lab, p, btn);
            box.setHgrow(p, Priority.ALWAYS);
        }

        @Override
        public void updateItem(Comment com, boolean empty) {

            super.updateItem(com, empty);
            setText(null);
            setGraphic(null);
            if (com != null && !empty) {
                lab.setText(com.getContent());
                setGraphic(box);

                btn.setOnAction((event) -> {
                    

                });

            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommentService cs = CommentService.getInstance();
        list = cs.DisplayAll();
        allComent.setItems(list);
        allComent.setCellFactory(s -> new cell());

        add.setOnAction(event -> {

            CommentService cd = CommentService.getInstance();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(now.toLocalDate());
            Comment o = new Comment(1, 1, 1, myComment.getText(), sqlDate, 0);
            cd.insert(o);
            allComent.getItems().clear();
            list = cs.DisplayAll();
            allComent.setItems(list);
            myComment.setText("");
        });
        
        
       
    }
             

}
