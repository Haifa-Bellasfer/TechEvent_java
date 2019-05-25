/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import service.StoryService;
import service.UserService;
import service.UserStoryService;
import entity.Story;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.Session;

/**
 *
 * @author Dalli
 */
public class ProfileController implements Initializable {

    PreparedStatement preparedStatement;
    Connection connection;

    @FXML
    private TextArea txtPublish;
    @FXML
    private Button btnPublish;
    @FXML
    private Button btnUpdate;

    @FXML
    private ListView<String> listView;
    @FXML
    private ListView<String> listProfile;
    @FXML
    private JFXTextField txtSearch;
    int ids;
    @FXML
    private Label lblError;
    @FXML
    private JFXListView<String> listShared;
    @FXML
    private JFXButton btnhome;

    @FXML
    private void HandleEvents(MouseEvent event) {
        if (!txtPublish.getText().isEmpty()) {
            String ymd = "2019-01-01";
            Date date = Date.valueOf(ymd);
            Story str = new Story(Session.current_user.getId_user(), txtPublish.getText(), date);
            StoryService strdao = StoryService.getInstance();
            strdao.insert(str);
            txtPublish.clear();
            listView.<String>setItems(null);
            listView.<String>setItems(strdao.DisplayAllById(Session.current_user.getId_user()));
        }

        if (event.getSource() == btnUpdate) {

            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Update.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        btnhome.setOnAction(e -> {
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

        StoryService strdao = StoryService.getInstance();
        listView.setItems(strdao.DisplayAllById(Session.current_user.getId_user()));

        Session.searched_user = new User();
        Session.searched_user.setId_user(0);
        UserService udao = UserService.getInstance();

        User user = new User();

        user = udao.DisplayById(Session.current_user.getId_user());
        ObservableList<String> list = FXCollections.observableArrayList();

        list.add(user.getUsername());
        list.add(user.getFirst_name());
        list.add(user.getLast_name());
        list.add(user.getEmail());
        list.add(user.getAddress());
        list.add(String.valueOf(user.getPhone()));

        listProfile.setItems(list);
        UserStoryService usdao = UserStoryService.getInstance();
        listShared.setItems(strdao.DisplayByIds(usdao.DisplayByIdUser(Session.current_user.getId_user())));
        ids = 0;

        txtSearch.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {

                ids = udao.getIdByUsername(txtSearch.getText());
                if (ids != 0 && ids != Session.current_user.getId_user()) {

                    Session.searched_user.setId_user(ids);

                    try {
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/ElseProfile.fxml")));
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    lblError.setTextFill(Color.TOMATO);
                    lblError.setText("enter valid username");
                }
            }

        });

    }
}
