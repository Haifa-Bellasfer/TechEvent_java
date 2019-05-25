/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import entity.Event;
import service.EventService;
import com.jfoenix.controls.JFXTextField;
import entity.Order_Line;
import entity.Tickett;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import service.Order_LineService;
import service.TicketService;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author PC ASUS
 */
public class CartController implements Initializable {

    ObservableList<Order_Line> list = FXCollections.observableArrayList();
    @FXML
    private ListView<Order_Line> listView;
    @FXML
    private JFXButton del;
    @FXML
    private JFXButton submit;
    @FXML
    private JFXTextField quantity;
    @FXML
    private ImageView plus;
    @FXML
    private ImageView min;
    @FXML
    private JFXButton add;
    double qt = 0;
    @FXML
    private Label total;
    @FXML
    private Label tot_cart;
    static Event evt;
    static int cartT;
    @FXML
    private ImageView home;

    @FXML
    private void eventHome(MouseEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/view/AddTicket.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static class cell extends ListCell<Order_Line> {

        Tickett t;
        EventService es = EventService.getInstance();
        int id;
        HBox box = new HBox();

        Label lab = new Label("hhhh");
        Label lab2 = new Label("58");

        Pane p = new Pane();
        private Object listview;

        public cell() {
            super();

            box.getChildren().addAll(lab, lab2, p);
            box.setHgrow(p, Priority.ALWAYS);
            box.setSpacing(50);

        }

        @Override
        public void updateItem(Order_Line od, boolean empty) {
            CartController c = null;
            super.updateItem(od, empty);
            setText(null);
            setGraphic(null);

            if (od != null && !empty) {

                TicketService ts = TicketService.getInstance();
                Tickett ticket = ts.DisplayById(od.getTicket_id());

                Event event = es.DisplayById(ticket.getEvent_id());
                CartController.evt = event;

                lab.setText(event.getEvent_name());
                lab2.setText(Integer.toString((int) event.getPrice_ticket()));
                setGraphic(box);

                /*
                 box.getChildren().removeAll();
                 int q=Integer.parseInt(quantity.getText()); 
                 ObservableList<Tickett> eventTicket=FXCollections.observableArrayList(); 
                 eventTicket=ts.DisplayByEventId(e.getId_event());
                 for(int i=0;i<q;i++){
                 e.setNb_participant(e.getOrganizer_id()+1);
                 es.update(e);
                 eventTicket.get(i).setStatus(0);
                 ts.update(eventTicket.get(i));
              
    
                 }*/
            }
        }

    }

    private List<Integer> quantityList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Order_LineService orderS = Order_LineService.getInstance();
        list = orderS.DisplayAll();
        cartT = list.size();
        tot_cart.setText(Integer.toString(cartT));
        System.out.println(list);
        list.forEach((t) -> {
            quantityList.add(0);
        });
        listView.setItems(list);
        listView.setCellFactory(s -> new cell()
        );

        quantity.setEditable(false);

        plus.setPickOnBounds(true);
        plus.setOnMouseClicked(ev -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            quantityList.set(selectedIndex, quantityList.get(selectedIndex) + 1);
            quantity.setText(Integer.toString(quantityList.get(selectedIndex)));

            Order_Line evn = list.get(selectedIndex);

            int currentTotal = 0;
            for (int i = 0; i < list.size(); i++) {
                int qty = quantityList.get(i);
                Order_Line event = list.get(i);
                currentTotal += qty * evt.getPrice_ticket();
            }
            total.setText(Double.toString(currentTotal));

            qt += qt + evt.getPrice_ticket();
            //total.setText(Double.toString(qt));

        });
        min.setPickOnBounds(true);
        min.setOnMouseClicked(ev -> {

            int selectedIndex = listView.getSelectionModel().getSelectedIndex();

            if (quantityList.get(selectedIndex) > 0) {
                quantityList.set(selectedIndex, quantityList.get(selectedIndex) - 1);
                quantity.setText(Integer.toString(quantityList.get(selectedIndex)));
                Order_Line evn = list.get(listView.getSelectionModel().getSelectedIndex());
                qt = qt - evt.getPrice_ticket();
                // TODO TOTAL Travers Quantity list

                int currentTotal = 0;
                for (int i = 0; i < list.size(); i++) {
                    int qty = quantityList.get(i);
                    Order_Line event = list.get(i);
                    currentTotal += qty * evt.getPrice_ticket();
                }
                total.setText(Double.toString(currentTotal));
            } else {
                quantity.setText("0");

            }
        });

        add.setOnAction((event) -> {
            int q = Integer.parseInt(quantity.getText());

            java.util.Date uDate = new java.util.Date();
            java.sql.Date sqDate = new java.sql.Date(uDate.getTime());
            TicketService td = TicketService.getInstance();
            EventService esv = EventService.getInstance();
            Order_Line evn = list.get(listView.getSelectionModel().getSelectedIndex());

            if (evt.getNb_participant() >= q) {
                for (int i = 0; i < q; i++) {
                    Tickett t = new Tickett(1, Session.current_user.getId_user(), evt.getId_event(), sqDate, 1, "****", 444444);
                    td.insert(t);
                    System.out.println(q);

                    evt.setNb_participant(evt.getNb_participant() - 1);

                    esv.updateNbParticipant(evt);

                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Number of Ticket Left :" + evt.getNb_participant());
                alert.show();

            }

        });

        del.setOnAction(ev -> {

            int selectedIdx = listView.getSelectionModel().getSelectedIndex();
            /* if (selectedIdx != -1) {
             Order_Line itemToRemove = listView.getSelectionModel().getSelectedItem();

             final int newSelectedIdx
             = (selectedIdx == listView.getItems().size() - 1)
             ? selectedIdx - 1
             : selectedIdx;
             listView.getItems().remove(selectedIdx);*/
            Order_LineService os = Order_LineService.getInstance();
            os.delete(list.get(selectedIdx));
            TicketService ts = TicketService.getInstance();
            Tickett t1 = ts.DisplayById(list.get(selectedIdx).getTicket_id());
            System.out.println(t1);
            ts.update(t1);
            list.remove(selectedIdx);
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Cart.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        home.setPickOnBounds(true);
        home.setOnMouseClicked((MouseEvent e) -> {
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

        submit.setOnAction((t) -> {

            Document document = new Document(PageSize.A7);
            document.addTitle("Your Ticket Praticipation");
            EventService esv = EventService.getInstance();
            Order_Line evn = list.get(listView.getSelectionModel().getSelectedIndex());
            try {

                PdfWriter.getInstance(document, new FileOutputStream("ticket.pdf"));
                document.open();
                String entete = " Your event:  ";
                String pdf = "";

                pdf = pdf + " " + evt.getEvent_name() + "           " + "Ticekt Price  " + evt.getPrice_ticket() + "       ";

                BarcodeQRCode barcodeQRCode = new BarcodeQRCode("https://memorynotfound.com", 1000, 1000, null);
                Image codeQrImage = barcodeQRCode.getImage();
                codeQrImage.scaleAbsolute(100, 100);

                Paragraph contenu = new Paragraph(entete + pdf);
                document.add(contenu);
                document.add(codeQrImage);

            } catch (FileNotFoundException | DocumentException ex)  {
                Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
            } 

            document.close();

        });

        listView.setOnMouseClicked((event) -> {
            int selectedList = listView.getSelectionModel().getSelectedIndex();
            int currentValue = quantityList.get(selectedList);
            quantity.setText(String.valueOf(currentValue));
        });
    }
}
