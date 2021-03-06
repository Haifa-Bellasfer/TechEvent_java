package controller;

import com.jfoenix.controls.JFXListView;
import entity.Event;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import service.EventService;

/**
 * FXML Controller class
 *
 * @author Dorsaf
 */
public class ArchiveController implements Initializable {

    @FXML
    public JFXListView<Event> archiveList;

    ObservableList<Event> list1=FXCollections.observableArrayList();
    EventService es= EventService.getInstance();
    @FXML
    private ImageView back;
    
    static class cell extends ListCell<Event>{
        HBox box= new HBox(50);
        Button btn =new Button("Archive");
        Label lab=new Label();
        Label lab1=new Label();
        Label lab2=new Label();
        Pane p= new Pane();

        public cell() {
            super();
            box.setPrefWidth(400);
            box.getChildren().addAll(lab,lab1,lab2, p,btn);
            box.setHgrow(p, Priority.ALWAYS);
        }
        
         @Override
        public void updateItem(Event event  , boolean empty){
        
            super.updateItem(event, empty);
            setText(null);
            setGraphic(null);
            if(event != null && !empty){
             lab1.setText(event.getStart_date().toString());
              lab2.setText(event.getEnd_date().toString());  
                
                
                
            DateTimeFormatter dtf=DateTimeFormatter.ofPattern("YYYY/MM/DD HH:mm:ss");
            LocalDateTime now=LocalDateTime.now();
         

            DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date1 = event.getEnd_date().toLocalDate();
            LocalDate date2 = now.toLocalDate();

            System.out.println("event : " + sdf.format(date1));
            System.out.println("tody : " + sdf.format(date2));

            System.out.println("Is...");
            if (date1.isAfter(date2)){
                
               lab.setText(event.getEvent_name());
               
               btn.setDisable(true);
                
                
            }

            if (date1.isBefore(date2)) {
                
                if( event.getArchive()==0){
                btn.setStyle("-fx-background-color : #FF0000");
                p.setDisable(false);
                lab.setText(event.getEvent_name());
                box.setStyle("-fx-background-color : #8bcbc8");
                btn.setDisable(false); 
                btn.setOnAction((ev) -> {
                   
                  EventService es=EventService.getInstance();
                  es.updateArchive(event);
                  
                lab.setText(event.getEvent_name());
                box.setStyle("-fx-background-color : #f6d55c");
                btn.setDisable(true);
               
                }
                );
                
                
                
                
                }else {
                lab.setText(event.getEvent_name());
                box.setStyle("-fx-background-color : #f6d55c");
                
                btn.setDisable(true);
                }
                
            }

            if (date1.isEqual(date2)) {
                 
                lab.setText(event.getEvent_name());
                box.setStyle("-fx-background-color : #f6d55c");
                
                btn.setDisable(true); 
            }

          
            
            setGraphic(box);
            
            }
            
         }   
            
        
        }
        
        
    
    
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        list1=es.DisplayAll();
        archiveList.setItems(list1);
        archiveList.setCellFactory(s->new cell());
       
        
        
               
        back.setPickOnBounds(true); 
        back.setOnMouseClicked((MouseEvent e) -> {
                try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Chart.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ArchiveController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
        
        
        
        
        
        
    }    
    
}

