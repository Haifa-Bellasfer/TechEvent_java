/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Dorsaf
 */
public class event_likes {
private int id_like;
private int user_id;
private int event_id;

    public event_likes(int id_like, int user_id, int event_id) {
        this.id_like = id_like;
        this.user_id = user_id;
        this.event_id = event_id;
    }

    public event_likes() {
     
    }

    public int getId_like() {
        return id_like;
    }

    public void setId_like(int id_like) {
        this.id_like = id_like;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    @Override
    public String toString() {
        return "event_likes{" + "id_like=" + id_like + ", user_id=" + user_id + ", event_id=" + event_id + '}';
    }


}
