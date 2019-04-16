/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ihebc_000
 */
public class Subscriber {
    private int idSubscriber;
    private String email;
    private Domain domain;

    public Subscriber() {
    }

    public Subscriber(int idSubscriber, String email, Domain domain) {
        this.idSubscriber = idSubscriber;
        this.email = email;
        this.domain = domain;
    }

    public int getIdSubscriber() {
        return idSubscriber;
    }

    public void setIdSubscriber(int idSubscriber) {
        this.idSubscriber = idSubscriber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }
    
    
}
