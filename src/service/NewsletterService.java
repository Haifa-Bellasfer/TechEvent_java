/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Newsletter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author ihebc_000
 */
public class NewsletterService implements InterfaceService<Newsletter>{

    private static NewsletterService instance;

    public static NewsletterService getInstance() {
        if (instance == null) {
            instance = new NewsletterService();
        }
        return instance;
    }
    
    @Override
    public void insert(Newsletter o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Newsletter o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Newsletter os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Newsletter> DisplayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Newsletter DisplayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int create(Newsletter o) {
        int id=-1;
        String req = "insert into newsletter(creation_date) values('"+o.getCreationDate()+"')";
        try {
            Statement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.executeUpdate(req, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.getGeneratedKeys();
            while(rs != null && rs.next()) {
                id=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsletterService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return id;
    }
    
}
