/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import entity.Club;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import service.ClubService;
import service.ThemeServices;

/**
 * FXML Controller class
 *
 * @author mbare
 */
public class AdminClubController implements Initializable {

    @FXML
    private TableView<Club> clubs;
    @FXML
    private TableColumn<Club, String> desc;
    @FXML
    private TableColumn<Club, String> mail;
    @FXML
    private TableColumn<Club, String> st;
    @FXML
    private TableColumn<Club, String> name;
    JFXButton acc = new JFXButton("Change status");
    JFXButton fire = new JFXButton("delete");
    VBox pbox = new VBox(acc,fire) ;
    JFXPopup pop = new JFXPopup(pbox);
    /**
     * Initializes the controller class.
     */
    ClubService cs = ClubService.getInstance();
    public void aff(){
            
        ThemeServices ts = ThemeServices.getInstance();
        ObservableList<Club> ls = FXCollections.observableArrayList();
        ls=cs.DisplayAll();
        
        name.setCellValueFactory(new PropertyValueFactory<>("club_name"));
        desc.setCellValueFactory(new PropertyValueFactory<>("club_description"));
                
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        st.setCellValueFactory(new PropertyValueFactory<>("club_status"));
        clubs.setItems(ls);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        aff();
        acc.setOnAction(event ->{
            Club cu = new Club();
             cu=clubs.getSelectionModel().getSelectedItem();
             if ("Accepted".equals(cu.getClub_status())) {
                cu.setClub_status("Refused");
                try {
			// Construct data
			String apiKey = "apikey=" + "MVLOno+0VZM-y2GuYuU0QFsk10BeBFvXBoI9vyhI0P";
			String message = "&message=" + "TechEvent wants to inform you that your club "+cu.getClub_name()+" has been refused";
			String sender = "&sender=" + "Admin";
			String numbers = "&numbers=" + "+21626424863";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                JOptionPane.showMessageDialog(null, message+line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			//System.out.println("Error SMS "+e);
                        JOptionPane.showMessageDialog(null, e);
			//return "Error "+e;
		}
            }else if ("Refused".equals(cu.getClub_status())){
             cu.setClub_status("Accepted");
             try {
			// Construct data
			String apiKey = "apikey=" + "MVLOno+0VZM-y2GuYuU0QFsk10BeBFvXBoI9vyhI0P";
			String message = "&message=" + "TechEvent wants to inform you that your club "+cu.getClub_name()+" has been accepted";
			String sender = "&sender=" + "Admin";
			String numbers = "&numbers=" + "+21626424863";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                JOptionPane.showMessageDialog(null, message+line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			//System.out.println("Error SMS "+e);
                        JOptionPane.showMessageDialog(null, e);
			//return "Error "+e;
		}
             }
            cs.update(cu);
            pop.hide();
            aff();
            pop.setPopupContent(pbox);
            
        });
        
        fire.setOnAction(event ->{
            Club cu = new Club();
            cu=clubs.getSelectionModel().getSelectedItem();
            cs.delete(cu);
            pop.hide();
            aff();
            pop.setPopupContent(pbox);
        });
    }    

    @FXML
    private void popShow(MouseEvent event) {
        pop.show(clubs,JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
    }
    
}