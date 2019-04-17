/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import entity.event;
import service.CategoryService;
import service.EventService;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Dorsaf
 */
public class CategoryController implements Initializable {

    @FXML
    private TableView<Category> categories;
    @FXML
    private TableColumn<Category,String> categoryName;
    @FXML
    private Button add;
    @FXML
    private Button update;
    
     private ObservableList<Category> Allcategories;
    @FXML
    private TextField name;
    int id;
    @FXML
    private Button delete;
    @FXML
    private JFXButton hi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       CategoryService ed= CategoryService.getInstance();
       Allcategories=FXCollections.observableArrayList();  
       Allcategories=ed.DisplayAll();
       categories.setItems(Allcategories);
       categoryName.setCellValueFactory(new PropertyValueFactory<>("category_name"));
     
       
       categories.setOnMouseClicked(event->{
        name.setText((Allcategories.get(categories.getSelectionModel().getSelectedIndex())
                .getCategory_name()));
        
       
       id=Allcategories.get(categories.getSelectionModel().getSelectedIndex())
                .getId_category();
       
       });
       
     
            
      add.setOnAction(event -> {
          
           CategoryService cdao=CategoryService.getInstance();
           Category c=new Category(1,name.getText());
           cdao.insert(c);
           categories.setItems(null);
           Allcategories=ed.DisplayAll();
           categories.setItems(Allcategories);
            
           
          
        });
       
        
      update.setOnAction(event -> {
           CategoryService cdao=CategoryService.getInstance();
           Category c=cdao.DisplayById(id);
           c.setCategory_name(name.getText());
           cdao.update(c);
           categories.setItems(null);
           Allcategories=ed.DisplayAll();
           categories.setItems(Allcategories);
           
        });
      
     delete.setOnAction(event -> {
           CategoryService cdao=CategoryService.getInstance();
           Category c=cdao.DisplayById(id);
           cdao.delete(c);
           categories.setItems(null);
           Allcategories=ed.DisplayAll();
           categories.setItems(Allcategories);
           
           
        });
    }    

 
    
}
