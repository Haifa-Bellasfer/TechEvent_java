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
public class user_categorie {
private int id;
private int category_id;
private int user_id;

    public user_categorie(int id, int category_id, int user_id) {
        this.id = id;
        this.category_id = category_id;
        this.user_id = user_id;
    }

    public user_categorie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "user_categorie{" + "id=" + id + ", category_id=" + category_id + ", user_id=" + user_id + '}';
    }

    
}
