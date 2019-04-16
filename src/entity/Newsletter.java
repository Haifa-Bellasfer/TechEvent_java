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
public class Newsletter {

    private int idNewsletter;
    private Date creationDate;

    public Newsletter() {
    }

    public Newsletter(int idNewsletter, Date creationDate) {
        this.idNewsletter = idNewsletter;
        this.creationDate = creationDate;
    }

    public int getIdNewsletter() {
        return idNewsletter;
    }

    public void setIdNewsletter(int idNewsletter) {
        this.idNewsletter = idNewsletter;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
