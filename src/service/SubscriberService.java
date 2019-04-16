/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Subscriber;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author ihebc_000
 */
public class SubscriberService implements InterfaceService<Subscriber>{

    @Override
    public void insert(Subscriber o) {
        String req = "insert into subscriber(email_subscriber, 	domain_id) values(?,?)";
        try {
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, o.getEmail());
            ps.setInt(2, o.getDomain().getIdDomain());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DomainService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Subscriber o) {
         try {
            String req = "Delete from subscriber where id_subscriber=?  ";
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, o.getIdSubscriber());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DomainService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean update(Subscriber os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public ObservableList<Subscriber> DisplayAll() {
        ObservableList<Subscriber> subscribers = FXCollections.observableArrayList();
        String req = "SELECT * FROM subscriber ";
        try {
            DomainService ds = new DomainService();
            PreparedStatement s = DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                subscribers.add(new Subscriber(rs.getInt("id_subscriber"), rs.getString("email_subscriber"), ds.DisplayById(rs.getInt("domain_id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscribers;
    }

    @Override
    public Subscriber DisplayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
