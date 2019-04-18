/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Dorsaf
 */
public class Event {

    
    private int id_event;   
    private int category_id;
    private int organizer_id;
    private String event_name;
    private String description;
    private int nb_participant;
    private String photo;
    private String status;
    private Date start_date;
    private Date end_date;
    private int archive;
    private double price_ticket;
    private int nb_like;
    private String address;

    public Event(int id_event, int category_id, int organizer_id, String event_name, String description, int nb_participant, String photo, String status, Date start_date, Date end_date, int archive, double price_ticket, int nb_like, String address) {
        this.id_event = id_event;
        this.category_id = category_id;
        this.organizer_id = organizer_id;
        this.event_name = event_name;
        this.description = description;
        this.nb_participant = nb_participant;
        this.photo = photo;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
        this.archive = archive;
        this.price_ticket = price_ticket;
        this.nb_like = nb_like;
        this.address = address;
    }

    public Event() {
       
    }


   



    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getOrganizer_id() {
        return organizer_id;
    }

    public void setOrganizer_id(int organizer_id) {
        this.organizer_id = organizer_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNb_participant() {
        return nb_participant;
    }

    public void setNb_participant(int nb_participant) {
        this.nb_participant = nb_participant;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    public double getPrice_ticket() {
        return price_ticket;
    }

    public void setPrice_ticket(double price_ticket) {
        this.price_ticket = price_ticket;
    }

    public int getNb_like() {
        return nb_like;
    }

    public void setNb_like(int nb_like) {
        this.nb_like = nb_like;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "event{" + "id_event=" + id_event + ", category_id=" + category_id + ", organizer_id=" + organizer_id + ", event_name=" + event_name + ", description=" + description + ", nb_participant=" + nb_participant + ", photo=" + photo + ", status=" + status + ", start_date=" + start_date + ", end_date=" + end_date + ", archive=" + archive + ", price_ticket=" + price_ticket + ", nb_like=" + nb_like + ", address=" + address + '}';
    }
    
    
    
}
