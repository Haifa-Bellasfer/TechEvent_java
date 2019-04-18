/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Event;
import entity.Order_Line;
import entity.Tickett;
import service.EventService;
import service.Order_LineService;
import service.TicketService;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author PC ASUS
 */
public class AddTicketController implements Initializable {

    @FXML
    private Button add;
   
    private ObservableList<Event> Allevent;
    int id;
    @FXML
    private TableView<Event> event_list;
    @FXML
    private TableColumn<Event, String> event_name;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EventService ed= EventService.getInstance();
       Allevent=FXCollections.observableArrayList();  
       Allevent=ed.DisplayAll();
       event_list.setItems(Allevent);
       event_name.setCellValueFactory(new PropertyValueFactory<>("event_name"));
       
       
       
       
      
        add.setOnAction(ev -> {
                try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Cart.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AddTicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
        id=Allevent.get(event_list.getSelectionModel().getSelectedIndex()).getId_event();
                 EventService e= EventService.getInstance();
        Event myevent=e.DisplayById(id);
        System.out.println(myevent);
        
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("YYYY/MM/DD HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        java.sql.Date sqDate=Date.valueOf(now.toLocalDate());
        
        
        TicketService td=TicketService.getInstance();
        Tickett t=new Tickett(1,Session.user, myevent.getId_event(), sqDate, 1, "qr", myevent.getNb_participant());
            
        td.insert(t);
        Tickett t1=td.DisplayByIdEventTicket(myevent.getId_event());
        
           
        Order_LineService od= Order_LineService.getInstance();
        System.out.println(t1.getId_ticket());
        Order_Line l=new Order_Line(1, t1.getId_ticket(),5, myevent.getNb_participant(), myevent.getPrice_ticket());
        System.out.println(l);
        od.insert(l);
        
          
        });
        
       
        
        
        
    }    
    
}
