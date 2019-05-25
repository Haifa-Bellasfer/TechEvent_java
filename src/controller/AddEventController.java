/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXComboBox;
import entity.Category;
import entity.Event;
import entity.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import service.EventService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import service.CategoryService;
import service.UserService;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Dorsaf
 */
public class AddEventController implements Initializable {

    @FXML
    private JFXComboBox<Category> Category;
    @FXML
    private DatePicker EndDate;
    @FXML
    private DatePicker StartDate;
    @FXML
    private TextField Name;
    @FXML
    private TextArea Description;
    @FXML
    private TextField Nbp;
    @FXML
    private TextField Price;
    @FXML
    private TextField Address;
    @FXML
    private Button AddButton;
    @FXML
    private ImageView img;
    private ObservableList<Category> catlist = FXCollections.observableArrayList();
    private String st;
    @FXML
    private Label addphoto;
    private String nameImage1;
    private int nbp;
    private Double price;
    @FXML
    private ImageView back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategoryService c = CategoryService.getInstance();
        catlist = c.DisplayAll();
        Category.getItems().addAll(catlist);

        addphoto.setOnMouseClicked((event) -> {
            addimage(event);
        });

        AddButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if ("".equals(Name.getText()) || "".equals(Description.getText()) || "".equals(Nbp.getText()) || "".equals(Price.getText())) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText("fildes must be not empty");
                    alert.show();

                }

                try {
                    price = Double.parseDouble(Price.getText());
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText("the price must be Double");
                    alert.show();
                }

                try {
                    nbp = Integer.parseInt(Nbp.getText());
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText("the participant must be a number");
                    alert.show();
                }

                int cId = catlist.get(Category.getSelectionModel().getSelectedIndex()).getId_category();
                User u = UserService.getInstance().DisplayById(Session.current_user.getId_user());
                System.out.println(u);
                if (u.getStatus().compareTo("VIP") != 0) {
                    st = "WAITING";
                } else {
                    st = "ACCEPTED";
                }

                Image image1 = img.getImage();

                try {

                    LocalDate start = StartDate.getValue();
                    Date startdate = Date.valueOf(start);

                    LocalDate end = EndDate.getValue();
                    Date enddate = Date.valueOf(end);

                    Event ev = new Event(1, cId, Session.current_user.getId_user(), Name.getText(), Description.getText(), nbp, ConvertFileImage(image1), st, startdate, enddate, 0, price, 0, Address.getText());
                    EventService ed = EventService.getInstance();
                    System.out.println(ev);
                    ed.insert(ev);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("event");
                    alert.setHeaderText(null);
                    alert.setContentText("your event is added!");
                    alert.show();

                } catch (SQLException ex) {
                    Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
                }
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

    }

    @FXML
    private void addimage(MouseEvent event) {

        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            img.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String ConvertFileImage(Image image) throws SQLException, IOException {

        String ext = "jpg";
        File dir = new File("D:/Study/EasyPHP/data/localweb/TechEvent/web/img");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(24), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(bImage, "png", outputFile);
        return name;
    }

}
