/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author mbare
 */
public class Workshop {
    
    private int id_workshop;
   
    private String title;
    
    private int nbr_places;
    
    private String workshop_description;
    
    private Date start_date;

    private String location;
    
    private int club_id;

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    
    
    public Workshop() {
    }

    public Workshop( String title, int nbr_places, String workshop_description, Date start_date, String location, int club_id) {
        
        this.title = title;
        this.nbr_places = nbr_places;
        this.workshop_description = workshop_description;
        this.start_date = start_date;
        this.location = location;
        this.club_id = club_id;
    }



    public int getId_workshop() {
        return id_workshop;
    }

    public void setId_workshop(int id_workshop) {
        this.id_workshop = id_workshop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNbr_places() {
        return nbr_places;
    }

    public void setNbr_places(int nbr_places) {
        this.nbr_places = nbr_places;
    }

    public String getWorkshop_description() {
        return workshop_description;
    }

    public void setWorkshop_description(String workshop_description) {
        this.workshop_description = workshop_description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Workshop{" + "id_workshop=" + id_workshop + ", title=" + title + ", nbr_places=" + nbr_places + ", workshop_description=" + workshop_description + ", start_date=" + start_date + ", location=" + location + '}';
    }
    
    
    
}
