/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author PC ASUS
 */
public class Cart {
    private int id_cart;
    private int user_id;
    private double total;

    public Cart() {
    }

    
    public Cart(int id_cart, int user_id, double total) {
        this.id_cart = id_cart;
        this.user_id = user_id;
        this.total = total;
    }

    public int getId_cart() {
        return id_cart;
    }

    public void setId_cart(int id_cart) {
        this.id_cart = id_cart;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "cart{" + "id_cart=" + id_cart + ", user_id=" + user_id + ", total=" + total + '}';
    }
    
    
    
}
