/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entity.Club;
import entity.Workshop;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import service.WorkshopServices;

/**
 * FXML Controller class
 *
 * @author mbare
 */
public class WorkshopListController implements Initializable {

    @FXML
    private JFXListView<String> list;
    
    JFXButton Update = new JFXButton("Update");
    JFXButton Cancel = new JFXButton("Cancel");
    VBox pbox = new VBox(Update,Cancel);
    JFXPopup pop = new JFXPopup(pbox);

    
    WorkshopServices ws = WorkshopServices.getInstance();
    ObservableList<Workshop> listW=FXCollections.observableArrayList();
    ObservableList<String> listS=FXCollections.observableArrayList();
    String s;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField nbr;
    @FXML
    private JFXTextArea desc;
    @FXML
    private JFXTextField loc;
    @FXML
    private DatePicker date;
    
    @FXML
    private JFXButton up;
    public void aff() {
        listW=ws.DisplayAll();
        for (int i = 0; i < listW.size(); i++) {
            s = listW.get(i).getTitle();
            listS.add(s);
            list.setItems(listS);
        }
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        title.setVisible(false);
        nbr.setVisible(false);
        desc.setVisible(false);
        loc.setVisible(false);
        date.setVisible(false);
        
        //Workshop w = ws.DisplayById(listW.get(list.getSelectionModel().getSelectedIndex()).getId_workshop());
        up.setVisible(false);
        aff();
        Update.setPadding(new Insets(10));
        Cancel.setPadding(new Insets(10));
        pop.setPopupContent(pbox);
        
        Cancel.setOnAction(event -> {
            System.out.println(listW.get(list.getSelectionModel().getSelectedIndex()).getId_workshop());
            ws.delete(ws.DisplayById(listW.get(list.getSelectionModel().getSelectedIndex()).getId_workshop()));
            pop.hide();
            list.getItems().clear();
            aff();
        });
        
        Update.setOnAction(event3 -> {
           
      
           
            title.setVisible(true);
            nbr.setVisible(true);
            desc.setVisible(true);
            loc.setVisible(true);
            date.setVisible(true);
            
            up.setVisible(true);
            
            title.setText(listW.get(list.getSelectionModel().getSelectedIndex()).getTitle());
            desc.setText(listW.get(list.getSelectionModel().getSelectedIndex()).getWorkshop_description());
            nbr.setText(String.valueOf(listW.get(list.getSelectionModel().getSelectedIndex()).getNbr_places()));
            
            loc.setText(listW.get(list.getSelectionModel().getSelectedIndex()).getLocation());
            date.setValue(LocalDate.parse(listW.get(list.getSelectionModel().getSelectedIndex()).getStart_date().toString()));
            pop.hide();
            System.out.println(ws.DisplayById(listW.get(list.getSelectionModel().getSelectedIndex()).getId_workshop()));
            Workshop w = ws.DisplayById(listW.get(list.getSelectionModel().getSelectedIndex()).getId_workshop());
            up.setOnAction(event2 -> {
                
                
                w.setTitle(title.getText());
                w.setWorkshop_description(desc.getText());
                w.setLocation(loc.getText());
                w.setStart_date(Date.valueOf(date.getValue()));
                w.setNbr_places(Integer.parseInt(nbr.getText()));
                
                ws.update(w);
                
                list.getItems().clear();
                aff();
                });
                title.setText("");
                desc.setText("");
                loc.setText("");
                date.setValue(LocalDate.now());
                nbr.setText("");
                pop.hide();
                list.getItems().clear();
                aff();
              });
                  
    }    

    @FXML
    private void showP(MouseEvent event) {
        pop.show(list,JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
    }
    
}
