/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import entity.event;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import service.CommentService;
import service.EventService;


/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class PieChartViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PieChart pieChart;
    ObservableList<Data> list=FXCollections.
            observableArrayList();
    
    
    
    public int calcul(int x){
        CommentService c=CommentService.getInstance();
        
       return c.DisplayCount(x);
        
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        EventService pdao=EventService.getInstance();
        List<event> ev=pdao.DisplayAll();
        for(event e:ev) {
            list.addAll(
                new Data(e.getEvent_name(), calcul(e.getCategory_id()))   
                    
        );
        }
        pieChart.setAnimated(true);
        pieChart.setData(list);
        
    }
    
    

}
