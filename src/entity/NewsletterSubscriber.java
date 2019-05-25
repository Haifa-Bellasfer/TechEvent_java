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
public class NewsletterSubscriber {
    private int idNewsletterSubscriber;
    private Newsletter newsletter;
    private Subscriber subscriber;

    public NewsletterSubscriber() {
    }

    public NewsletterSubscriber(int idNewsletterSubscriber, Newsletter newsletter, Subscriber subscriber) {
        this.idNewsletterSubscriber = idNewsletterSubscriber;
        this.newsletter = newsletter;
        this.subscriber = subscriber;
    }

    public int getIdNewsletterSubscriber() {
        return idNewsletterSubscriber;
    }

    public void setIdNewsletterSubscriber(int idNewsletterSubscriber) {
        this.idNewsletterSubscriber = idNewsletterSubscriber;
    }

    public Newsletter getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Newsletter newsletter) {
        this.newsletter = newsletter;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
    
    
}
