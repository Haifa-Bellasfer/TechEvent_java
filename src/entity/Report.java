/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author khaled
 */
public class Report {
     
    private int idreport;
    private int commentid;
    private int nbreportcomment; 
    private int nbreportuser; 
    private Date dateofreport ;
    private int userid; 

    public Report(int idreport, int commentid, int nbreportcomment, int nbreportuser, Date dateofreport, int userid) {
        this.idreport = idreport;
        this.commentid = commentid;
        this.nbreportcomment = nbreportcomment;
        this.nbreportuser = nbreportuser;
        this.dateofreport = dateofreport;
        this.userid = userid;
    }

   
    
    public Report() {
       
    }

    public int getIdreport() {
        return idreport;
    }

    public void setIdreport(int idreport) {
        this.idreport = idreport;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public int getNbreportcomment() {
        return nbreportcomment;
    }

    public void setNbreportcomment(int nbreportcomment) {
        this.nbreportcomment = nbreportcomment;
    }

    public int getNbreportuser() {
        return nbreportuser;
    }

    public void setNbreportuser(int nbreportuser) {
        this.nbreportuser = nbreportuser;
    }

    public Date getDateofreport() {
        return dateofreport;
    }

    public void setDateofreport(Date dateofreport) {
        this.dateofreport = dateofreport;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Report{" + "idreport=" + idreport + ", commentid=" + commentid + ", nbreportcomment=" + nbreportcomment + ", nbreportuser=" + nbreportuser + ", dateofreport=" + dateofreport + ", userid=" + userid + '}';
    }

  
    
    
    
}
