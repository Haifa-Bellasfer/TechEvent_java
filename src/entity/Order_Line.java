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
public class Order_Line {
    private int id_line;
    private int ticket_id;
    private int cart_id;
    private int quantity;
    private double price;

    public Order_Line() {
    }

    public Order_Line(int id_line, int ticket_id, int cart_id, int quantity, double price) {
        this.id_line = id_line;
        this.ticket_id = ticket_id;
        this.cart_id = cart_id;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId_line() {
        return id_line;
    }

    public void setId_line(int id_line) {
        this.id_line = id_line;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "order_line{" + "id_line=" + id_line + ", ticket_id=" + ticket_id + ", cart_id=" + cart_id + ", quantity=" + quantity + ", price=" + price + '}';
    }
    
    
    
    
}
