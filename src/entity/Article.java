/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;


/**
 *
 * @author ihebc_000
 */
public class Article {
    private int idArticle;
    private String titreArticle;
    private String contentArticle;
    private int viewsNumber;
    private Date dateOfPublish;
    private String image;
    private Domain domain;
    private Newsletter newsletter;

    public Article() {
    }
    
    public Article(int idArticle, String titreArticle, String contentArticle, int viewsNumber, Date dateOfPublish, String image, Domain domain, Newsletter newsletter) {
        this.idArticle = idArticle;
        this.titreArticle = titreArticle;
        this.contentArticle = contentArticle;
        this.viewsNumber = viewsNumber;
        this.dateOfPublish = dateOfPublish;
        this.image = image;
        this.domain = domain;
        this.newsletter = newsletter;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitreArticle() {
        return titreArticle;
    }

    public void setTitreArticle(String titreArticle) {
        this.titreArticle = titreArticle;
    }

    public String getContentArticle() {
        return contentArticle;
    }

    public void setContentArticle(String contentArticle) {
        this.contentArticle = contentArticle;
    }

    public int getViewsNumber() {
        return viewsNumber;
    }

    public void setViewsNumber(int viewsNumber) {
        this.viewsNumber = viewsNumber;
    }

    public Date getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(Date dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public Newsletter getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Newsletter newsletter) {
        this.newsletter = newsletter;
    }

    @Override
    public String toString() {
        return "Article{" + "idArticle=" + idArticle + ", titreArticle=" + titreArticle + ", contentArticle=" + contentArticle + ", viewsNumber=" + viewsNumber + ", dateOfPublish=" + dateOfPublish + ", image=" + image + ", domain=" + domain + ", newsletter=" + newsletter + '}';
    }
    
    
    
}
