package entity;

import java.util.Date;

/**
 *
 * @author ihebc_000
 */
public class Saved {
    private int idSaved;
    private Date dateSave;
    private Article article;
    private User user;

    public Saved() {
    }

    public Saved(int idSaved, Date dateSave, Article article, User user) {
        this.idSaved = idSaved;
        this.dateSave = dateSave;
        this.article = article;
        this.user = user;
    }

    public int getIdSaved() {
        return idSaved;
    }

    public void setIdSaved(int idSaved) {
        this.idSaved = idSaved;
    }

    public Date getDateSave() {
        return dateSave;
    }

    public void setDateSave(Date dateSave) {
        this.dateSave = dateSave;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
