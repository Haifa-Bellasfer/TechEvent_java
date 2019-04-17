/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Article;
import entity.Domain;
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
public class ArticleService implements InterfaceService<Article> {
    
    private static ArticleService instance;

    public static ArticleService getInstance() {
        if (instance == null) {
            instance = new ArticleService();
        }
        return instance;
    }
    
    @Override
    public void insert(Article o) {
        String req = "insert into article(title_article, content_article, views_number, date_of_publish, image, domain_id) "
                + "values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, o.getTitreArticle());
            ps.setString(2, o.getContentArticle());
            ps.setInt(3, o.getViewsNumber());
            ps.setDate(4, o.getDateOfPublish());
            ps.setString(5, o.getImage());
            ps.setInt(6, o.getDomain().getIdDomain());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DomainService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void delete(Article o) {
        try {
            String req = "Delete from article where id_article=?  ";
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, o.getIdArticle());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DomainService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean update(Article os) {
        String req = "update article set domain_id = ? , title_article = ? , content_article = ?, views_number = ? , date_of_publish=?,  image = ? where id_article = ?";
        int i = 0;
        try {
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, os.getDomain().getIdDomain());
            ps.setString(2, os.getTitreArticle());
            ps.setString(3, os.getContentArticle());
            ps.setInt(4, os.getViewsNumber());
            ps.setDate(5, os.getDateOfPublish());
            ps.setString(6, os.getImage());
            ps.setInt(7, os.getIdArticle());
            i = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DomainService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i != 0;
    }
    
    public boolean updateNewsletter(Article os) {
        String req = "update article set newsletter_id = ? where id_article = ?";
        int i = 0;
        try {
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, os.getNewsletter().getIdNewsletter());
            ps.setInt(2, os.getIdArticle());
            i = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DomainService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i != 0;
    }
    
    public boolean updateViewNumber(Article os) {
        String req = "update article set views_number = ? where id_article = ?";
        int i = 0;
        try {
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, os.getViewsNumber());
            ps.setInt(2, os.getIdArticle());
            i = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DomainService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i != 0;
    }
    
    @Override
    public ObservableList<Article> DisplayAll() {
        ObservableList<Article> articles = FXCollections.observableArrayList();
        String req = "SELECT * FROM article ORDER BY date_of_publish  ";
        try {
            PreparedStatement s = DataSource.getInstance().getCnx().prepareStatement(req);
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
    
    @Override
    public Article DisplayById(int id) {
        String req = "SELECT * FROM article where id_article = ?";
        Article a = new Article();
        try {
            PreparedStatement s = DataSource.getInstance().getCnx().prepareStatement(req);
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                a.setIdArticle(rs.getInt("id_article"));
                a.setTitreArticle(rs.getString("title_article"));
                a.setContentArticle(rs.getString("content_article"));
                a.setDateOfPublish(rs.getDate("date_of_publish"));
                DomainService ds = new DomainService();
                a.setDomain(ds.DisplayById(rs.getInt("domain_id")));
                a.setImage(rs.getString("image"));
                a.setViewsNumber(rs.getInt("views_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
    
    public ObservableList<Article> FindByDomainKeywordAndOrderBy(Domain domain, String keyword, String orderBy) {
        ObservableList<Article> articles = FXCollections.observableArrayList();
        
        String sql = "";
        if (domain != null) {
            sql = sql + " AND domain_id=" + domain.getIdDomain() + " ";
        }
        
        if (orderBy != null) {
            if ("View number".equals(orderBy)) {
                sql = sql + " ORDER BY views_number DESC ";
            }
            if ("Publish date".equals(orderBy)) {
                sql = sql + " ORDER BY date_of_publish DESC ";
            }
        }
        
        sql = "SELECT * FROM article where title_article like '%"+keyword+"%' "+sql;
        try {
            PreparedStatement s = DataSource.getInstance().getCnx().prepareStatement(sql);
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
    
    
    public ObservableList<Article> getArticleByDomain(Domain d) {
        ObservableList<Article> articles = FXCollections.observableArrayList();
        String req = "SELECT * FROM article where domain_id='"+d.getIdDomain()+"'";
        try {
            PreparedStatement s = DataSource.getInstance().getCnx().prepareStatement(req);
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
