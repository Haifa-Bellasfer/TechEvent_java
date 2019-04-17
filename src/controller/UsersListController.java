/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.UserService;
import entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Dalli
 */
public class UsersListController implements Initializable {

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> colUsername;
    @FXML
    private TableColumn<User, String> colFirstname;
    @FXML
    private TableColumn<User, String> colLastname;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colAddress;
    @FXML
    private TableColumn<User, String> colPhone;
    @FXML
    private TableColumn<User, String> colStatus;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        colLastname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        
     UserService ud=UserService.getInstance();
     
        ObservableList<User> list=FXCollections.observableArrayList();
        list =ud.DisplayAllUsers();
        table.setItems(list);

    
    }
    
}
