/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.deploy.util.BlackList;
import entity.Blacklist;
import entity.Comment;
import entity.Report;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import static java.time.Instant.now;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.Stage;
import service.CommentService;
import service.ReportService;
import utils.Session;

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
    @FXML
    private Button btncom;

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
                ReportService rs = ReportService.getInstance();
                Report rep = rs.DisplayById(com.getId_comment());
                lab.setText(com.getContent());

                if (rep.getUserid() == Session.current_user.getId()) {

                    btn.setDisable(true);

                } else {
                    btn.setDisable(false);

                }

                setGraphic(box);
                

                btn.setOnAction((event) -> {

                    LocalDateTime now = LocalDateTime.now();
                    Date sqlDate = java.sql.Date.valueOf(now.toLocalDate());
                    CommentService cs = CommentService.getInstance();

                    if (rep.getUserid() == Session.current_user.getId()) {

                        btn.setDisable(true);

                    } else {
                        btn.setDisable(false);

                    }

                    if (rep.getIdreport() == 0) {
                        Report r = new Report(1, com.getId_comment(), 1, 0, sqlDate, Session.current_user.getId());
                        rs.insert(r);

                    } else {
                        System.out.println(rep);
                        rep.setNbreportcomment(rep.getNbreportcomment() + 1);
                        System.out.println(rep.getNbreportcomment());
                        rs.update(rep);

                        if (rep.getNbreportcomment() == 3) {
                            rep.setNbreportcomment(0);
                            rep.setNbreportuser(rep.getNbreportuser() + 1);

                            rs.update(rep);

                        }
                    }

                    btn.setDisable(true);

                });
                

            }
            

        }
        
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommentService cs = CommentService.getInstance();
        
      
        
        list = cs.DisplayByIdEvent(Session.current_event.getId_event());
        allComent.setItems(list);

        add.setOnAction(event -> {

            CommentService cd = CommentService.getInstance();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(now.toLocalDate());
            Comment o = new Comment(Session.user, Session.current_event.getId_event(), Session.current_user.getId(), myComment.getText(), sqlDate, 0);
            System.out.println(o);
            cd.insert(o);
            allComent.<String>setItems(null);
            list=cs.DisplayByIdEvent(1);
            allComent.setItems(list);
            myComment.setText("");
            System.out.println("9999999999999999999");
            
        });
        
        
        
        btncom.setOnAction(event ->{
              try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Comment.fxml"));
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
