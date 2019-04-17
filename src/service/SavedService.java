/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Article;
import entity.Saved;
import entity.User;
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
public class SavedService implements InterfaceService<Saved> {

    private static SavedService instance;

    public static SavedService getInstance() {

        if (instance == null) {
            instance = new SavedService();
        }
        return instance;
    }

    @Override
    public void insert(Saved o) {
        String req = "insert into saved(date_save, article_id, user_id) values(?,?,?)";
        try {
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setDate(1, o.getDateSave());
            ps.setInt(2, o.getArticle().getIdArticle());
            ps.setInt(3, o.getUser().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SavedService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Saved o) {
        try {
            String req = "Delete from saved where id_saved = ?";
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, o.getIdSaved());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SavedService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean update(Saved os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Saved> DisplayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Saved DisplayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isSaved(Article article, User user) {
        boolean isSaved = false;
        String req = "SELECT * FROM saved where article_id = ? and user_id = ?";
        try {
            PreparedStatement s = DataSource.getInstance().getCnx().prepareStatement(req);
            s.setInt(1, article.getIdArticle());
            s.setInt(2, user.getId());
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                isSaved = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSaved;
    }

    public Saved geSavedByArticleAndUser(Article article, User user) {
        String req = "SELECT * FROM saved where article_id = ? and user_id = ?";
        Saved saved = new Saved(article, user);
        try {
            PreparedStatement s = DataSource.getInstance().getCnx().prepareStatement(req);
            s.setInt(1, article.getIdArticle());
            s.setInt(2, user.getId());
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                saved.setIdSaved(rs.getInt("id_saved"));
                saved.setDateSave(rs.getDate("date_save"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saved;
    }
    
    
    
     
    public ObservableList<Article> DisplayByIdUser(int id) {
        String req = "select * from article a join saved s where a.id_article=s.article_id and s.user_id=?";
        ObservableList<Article> articles = FXCollections.observableArrayList();
        try {
            PreparedStatement s = DataSource.getInstance().getCnx().prepareStatement(req);
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                Article a = new Article();
                a.setIdArticle(rs.getInt("id_article"));
                a.setTitreArticle(rs.getString("title_article"));
                a.setContentArticle(rs.getString("content_article"));
                a.setDateOfPublish(rs.getDate("date_of_publish"));
                DomainService ds = new DomainService();
                a.setDomain(ds.DisplayById(rs.getInt("domain_id")));
                a.setImage(rs.getString("image"));
                a.setViewsNumber(rs.getInt("views_number"));
                articles.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }
}
