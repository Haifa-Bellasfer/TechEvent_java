/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Dalli
 */
public class UserStory {
    
    private int id_user_story ;
    private int id_user;
    private int id_story;
    private Date creation_date;

    public UserStory() {
    }

    public UserStory(int id_user, int id_story, Date creation_date) {
        this.id_user = id_user;
        this.id_story = id_story;
        this.creation_date = creation_date;
    }

   

    public UserStory(int id_user, int id_story) {
        this.id_user = id_user;
        this.id_story = id_story;
    }

    public int getId_user_story() {
        return id_user_story;
    }

    public void setId_user_story(int id_user_story) {
        this.id_user_story = id_user_story;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_story() {
        return id_story;
    }

    public void setId_story(int id_story) {
        this.id_story = id_story;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    @Override
    public String toString() {
        return "userstory{" + "id_user_story=" + id_user_story + ", id_user=" + id_user + ", id_story=" + id_story + ", creation_date=" + creation_date + '}';
    }
}
