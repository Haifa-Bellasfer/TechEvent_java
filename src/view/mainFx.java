/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author mbare
 */
public class mainFx extends Application {

    @Override
    public void start(Stage Stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ListClub.fxml"));
            Scene scene = new Scene(root);
            Stage.setScene(scene);
            Stage.setResizable(false);
            Stage.show();

        } catch (IOException ex) {
            Logger.getLogger(mainFx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
