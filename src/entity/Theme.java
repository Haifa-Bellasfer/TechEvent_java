/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javafx.scene.control.Button;

/**
 *
 * @author mbare
 */
public class Theme {
    private int id_theme;
 
    private String theme_name;
     
    private Button del;

    public Button getDel() {
        return del;
    }

    public void setDel(Button del) {
        this.del = del;
    }
    
    
    public Theme() {
    }

    public Theme(String theme_name) {
        
        this.theme_name = theme_name;
        this.del = new Button("Delete");
    }

    public int getId_theme() {
        return id_theme;
    }

    public void setId_theme(int id_theme) {
        this.id_theme = id_theme;
    }

    public String getTheme_name() {
        return theme_name;
    }

    public void setTheme_name(String theme_name) {
        this.theme_name = theme_name;
    }

    @Override
    public String toString() {
        return theme_name ;
    }
    
    
    
}
