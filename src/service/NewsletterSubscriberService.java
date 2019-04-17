/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.NewsletterSubscriber;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author ihebc_000
 */
public class NewsletterSubscriberService implements InterfaceService<NewsletterSubscriber>{

    private static NewsletterSubscriberService instance;

    public static NewsletterSubscriberService getInstance() {
        if (instance == null) {
            instance = new NewsletterSubscriberService();
        }
        return instance;
    }
    
    
    @Override
    public void insert(NewsletterSubscriber o) {
        String req = "insert into newsletter_subscriber(newsletter_id, subscriber_id) values(?,?)";
        try {
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, o.getNewsletter().getIdNewsletter());
            ps.setInt(2, o.getSubscriber().getIdSubscriber());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DomainService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(NewsletterSubscriber o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(NewsletterSubscriber os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<NewsletterSubscriber> DisplayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NewsletterSubscriber DisplayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
