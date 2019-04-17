/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Domain;
import entity.Subscriber;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author ihebc_000
 */
public class SubscriberService implements InterfaceService<Subscriber> {
    
    private static SubscriberService instance;

    public static SubscriberService getInstance() {
        if (instance == null) {
            instance = new SubscriberService();
        }
        return instance;
    }

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

    public ObservableList<Subscriber> DisplayByName(String email, Domain d) {
        DomainService ds = new DomainService();
        ObservableList<Subscriber> subs = FXCollections.observableArrayList();
        String req = "SELECT * FROM subscriber where email_subscriber like '%" + email + "%'";
        if (d != null) {
            req = req + " and domain_id="+d.getIdDomain();
        }
        try {
            PreparedStatement s = DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                subs.add(new Subscriber(rs.getInt("id_subscriber"), rs.getString("email_subscriber"), ds.DisplayById(rs.getInt("domain_id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subs;
    }
    
    
    
    public ObservableList<Subscriber> DisplayByName(String email) {
        DomainService ds = new DomainService();
        ObservableList<Subscriber> subs = FXCollections.observableArrayList();
        String req = "SELECT * FROM subscriber where email_subscriber = " + email ;
        try {
            PreparedStatement s = DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                subs.add(new Subscriber(rs.getInt("id_subscriber"), rs.getString("email_subscriber"), ds.DisplayById(rs.getInt("domain_id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subs;
    }
    
    
    public boolean isSubsribed(String email) {
        int nb = 0;
        String req = "SELECT * FROM subscriber where email_subscriber = ? ";
        try {
            PreparedStatement s = DataSource.getInstance().getCnx().prepareStatement(req);
            s.setString(1, email);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                nb++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nb != 0;
    }
    
    
     public int create(Subscriber o) {
        int id=-1;
        String req = "insert into subscriber(email_subscriber, 	domain_id) values('"+o.getEmail()+"','"+o.getDomain().getIdDomain()+"')";
        try {
            Statement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.executeUpdate(req, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.getGeneratedKeys();
            while(rs != null && rs.next()) {
                id=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return id;
    }
}
