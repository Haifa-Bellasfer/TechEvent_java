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
import java.sql.Date;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
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
    @FXML
    private Label label_article;
    @FXML
    private Label label_event;
    @FXML
    private Label label_club;
    @FXML
    private Label label_subscriber;
    @FXML
    private Label label_profile;
    @FXML
    private JFXButton btnlogout;
    @FXML
    private JFXButton btnlogin;
    @FXML
    private ImageView back;

    static class CustomCell extends ListCell<Comment> {

        HBox box = new HBox();

        Label lab = new Label();
        Button btn = new Button("Report");
        Pane p = new Pane();

        public CustomCell() {
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
                if (Session.current_user.getId_user() == com.getUser_id()) {

                    btn.setVisible(false);
                }
                setGraphic(box);

                btn.setOnAction((event) -> {

                    LocalDateTime now = LocalDateTime.now();
                    Date sqlDate = java.sql.Date.valueOf(now.toLocalDate());
                    CommentService cs = CommentService.getInstance();

                    if (rep.getUserid() == Session.current_user.getId_user()) {

                        btn.setDisable(true);

                    } else {
                        btn.setDisable(false);

                    }

                    if (rep.getIdreport() == 0) {
                        Report r = new Report(1, com.getId_comment(), 1, 0, sqlDate, Session.current_user.getId_user());
                        rs.insert(r);

                    } else {
                        System.out.println(rep);
                        rep.setNbreportcomment(rep.getNbreportcomment() + 1);
                        System.out.println(rep.getNbreportcomment());
                        rs.update(rep);

                        if (rep.getNbreportcomment() == 3) {
                            Comment ccccc = CommentService.getInstance().DisplayById(rep.getCommentid());
                            ccccc.setNbrep(ccccc.getNbrep() + 1);
                            CommentService.getInstance().update(ccccc);

                        }
                    }

                    btn.setDisable(true);

                });

            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        label_event.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        label_club.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ClubAcc.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.out.println("news controller, label club action " + ex.getMessage());
            }
        });

        label_article.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/News.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.out.println("news controller, label artilce action " + ex.getMessage());
            }
        });
        label_subscriber.setOnMouseClicked(e -> {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/Subs.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Subscribe to our newsletter");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node) e.getSource()).getScene().getWindow());
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.out.println("news controller, label artilce action " + ex.getMessage());
            }
        });

        label_profile.setVisible(false); //1
        if (Session.current_user != null) {
            label_profile.setVisible(true); //1
            btnlogin.setVisible(false);
            btnlogout.setVisible(true);
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
            label_profile.setOnMouseClicked(e -> {
                try {
                    Parent page = FXMLLoader.load(getClass().getResource("/view/Profile.fxml"));
                    Scene scene = new Scene(page);
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        back.setOnMouseClicked(e -> {
            try {
                Parent page = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnlogout.setVisible(false);
        btnlogin.setOnAction(e -> {
            try {
                Parent page = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        label_profile.setVisible(false); //1
        if (Session.current_user != null) {
            label_profile.setVisible(true); //1

            btnlogin.setVisible(false);
            btnlogout.setVisible(true);
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
            label_profile.setOnMouseClicked(e -> {
                try {
                    Parent page = FXMLLoader.load(getClass().getResource("/view/Profile.fxml"));
                    Scene scene = new Scene(page);
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        CommentService cs = CommentService.getInstance();
        list = cs.DisplayByIdEvent(Session.current_event.getId_event());
        allComent.setCellFactory(e -> new CustomCell());
        allComent.setItems(list);
        add.setOnAction(event -> {

            CommentService cd = CommentService.getInstance();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(now.toLocalDate());
            System.out.println(Session.current_user.getId_user());
            Comment o = new Comment(1, Session.current_event.getId_event(), Session.current_user.getId_user(), myComment.getText(), sqlDate, 0);
            cd.insert(o);
            allComent.<String>setItems(null);
            list = cs.DisplayByIdEvent(Session.current_event.getId_event());
            allComent.setItems(list);
            myComment.setText("");

        });

        btncom.setOnAction(event -> {
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
