/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import controller.AccueilController;

import entity.Category;
import entity.event;
import entity.user_categorie;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.CategoryService;
import service.EventService;
import service.User_CategoryService;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Dorsaf
 */
public class MyeventController implements Initializable {
    
    
    private  ObservableList<Category> catlist=FXCollections.observableArrayList(); 
    private  ObservableList<Category> Mylist=FXCollections.observableArrayList();  
    private  ObservableList<event> eventlist=FXCollections.observableArrayList();  
    private  ObservableList<user_categorie> userlist=FXCollections.observableArrayList();  
    private  ObservableList<user_categorie> list=FXCollections.observableArrayList();
    @FXML
    private TableView<event> EventList;
    @FXML
    private TableColumn<event, String> eventName;
    @FXML
    private JFXButton show;
    @FXML
    private TableView<Category> listCategory;
    @FXML
    private TableColumn<Category, String> allCategoriesName;
    @FXML
    private TableView<Category> myCategory;
    @FXML
    private TableColumn<Category, String> myCategoryNames;
    
    private   User_CategoryService uc=User_CategoryService.getInstance();
    int  idCat,idDel;
    @FXML
    private JFXButton Add;
    private JFXButton delete;
    @FXML
    private ImageView back;
   
    /**
     * Initializes the controller class.
     */
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       CategoryService c= CategoryService.getInstance();
       catlist=c.DisplayAll();
       listCategory.setItems(catlist);
       allCategoriesName.setCellValueFactory(new PropertyValueFactory<>("category_name")); 
    
       userlist=uc.DisplayAllUserCategory(Session.user);
       EventService ev=EventService.getInstance();
       for(int i=0;i<userlist.size();i++){
           int idcategory=userlist.get(i).getCategory_id();
           Mylist.add(c.DisplayById(idcategory));
           myCategory.setItems(Mylist);
           eventlist.addAll(ev.DisplayAllEvent(idcategory));
           
           
       }
        EventList.setItems(eventlist);
        myCategoryNames.setCellValueFactory(new PropertyValueFactory<>("category_name")); 
        eventName.setCellValueFactory(new PropertyValueFactory<>("event_name")); 
        
         
        myCategory.setOnMouseClicked(event->{
        idCat =Mylist.get(myCategory.getSelectionModel().getSelectedIndex()).getId_category();
         
        });
        listCategory.setOnMouseClicked(event->{
         idDel=catlist.get(listCategory.getSelectionModel().getSelectedIndex()).getId_category();
          });
         
         Add.setOnMouseClicked((MouseEvent e) -> {
         list =uc.DisplayAllUserCategory(Session.user);
          for(int i=0;i<list.size();i++){
           if(list.get(i).getCategory_id() == idCat){
               uc.delete(list.get(i));
               
           }}
           
          uc.insert(new user_categorie(1, idDel, Session.user));
           
         
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Myevent.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
       
            
            
            
            });
         
         
     
         
         
         
         
         
                
        back.setPickOnBounds(true); 
        back.setOnMouseClicked((MouseEvent e) -> {
                try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
        
         
         
         
         
         
         
         
         
        
        
        show.setOnMouseClicked((MouseEvent e) -> {
                try {
                   
                Session.current_event =eventlist.get(EventList.getSelectionModel().getSelectedIndex());
                 
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Description.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });
        
        
        
        
          
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }    
    
}
