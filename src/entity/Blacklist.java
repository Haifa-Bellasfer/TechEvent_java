/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author khaled
 */
public class Blacklist {
    
    
    private int id_blacklist;

    public Blacklist() {
    }

    public Blacklist(int id_blacklist) {
        this.id_blacklist = id_blacklist;
    }

    public int getId_blacklist() {
        return id_blacklist;
    }

    public void setId_blacklist(int id_blacklist) {
        this.id_blacklist = id_blacklist;
    }

    @Override
    public String toString() {
        return "Blacklist{" + "id_blacklist=" + id_blacklist + '}';
    }
    
    
}
