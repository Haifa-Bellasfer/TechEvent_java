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
public class Story {
    private int id_story;
    private int id_user;
    private String content_story;
    private Date creation_date;

    public Story() {
    }

    public Story( int id_user, String content_story, Date creation_date) {
        this.id_user = id_user;
        this.content_story = content_story;
        this.creation_date = creation_date;
    }

    public int getId_story() {
        return id_story;
    }

    public void setId_story(int id_story) {
        this.id_story = id_story;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getContent_story() {
        return content_story;
    }

    public void setContent_story(String content_story) {
        this.content_story = content_story;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    @Override
    public String toString() {
        return "story{" + "id_story=" + id_story + ", id_user=" + id_user + ", content_story=" + content_story + ", creation_date=" + creation_date + '}';
    }

    public void getContent_storyProprety() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
