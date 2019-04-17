/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import entity.Club;
import entity.Workshop;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import service.ThemeServices;
import service.WorkshopServices;

/**
 * FXML Controller class
 *
 * @author mbare
 */
public class WorkshopAffichController implements Initializable {

    @FXML
    private JFXListView<String> listW;
    WorkshopServices WS = WorkshopServices.getInstance();
    ObservableList<Workshop> ls = FXCollections.observableArrayList();
    ObservableList<String> ss = FXCollections.observableArrayList();
    @FXML
    private JFXButton part;
    @FXML
    private JFXButton cancel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        part.setVisible(false);
        cancel.setVisible(false);
        ls=WS.DisplayAll();
        for (int i = 0; i < ls.size(); i++) {
            String s =ls.get(i).affiche();
            ss.add(s);
            listW.setItems(ss);
        }
        part.setOnAction(e->{
            Workshop w = WS.DisplayById(ls.get(listW.getSelectionModel().getSelectedIndex()).getId_workshop());
            w.setNbr_places(w.getNbr_places()-1);
            WS.update(w);
            ls=WS.DisplayAll();
            listW.getItems().clear();
            for (int i = 0; i < ls.size(); i++) {
            String s =ls.get(i).affiche();
            ss.add(s);
            listW.setItems(ss);
            part.setVisible(false);
            cancel.setVisible(true);
        }
        });
        cancel.setOnAction(e->{
            Workshop w = WS.DisplayById(ls.get(listW.getSelectionModel().getSelectedIndex()).getId_workshop());
            w.setNbr_places(w.getNbr_places()+1);
            WS.update(w);
            ls=WS.DisplayAll();
            listW.getItems().clear();
            for (int i = 0; i < ls.size(); i++) {
            String s =ls.get(i).affiche();
            ss.add(s);
            listW.setItems(ss);
            part.setVisible(true);
            cancel.setVisible(false);
        }
        });
        
        
    }    

    @FXML
    private void showP(MouseEvent event) {
        if (event.getClickCount()==2) {
            part.setVisible(true);
        }
        
        
    }
    
}
