/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author mbare
 */
public class ClubUser {
    
     private int id_club_user;
    
     private String club_user_status="Waiting";
    
     private String why;
    
     private String you_are;
    
     private String skills;
     
     private int member_id;
     
     private int club_id;

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public ClubUser(int id_club_user, String why, String you_are, String skills, int member_id) {
        this.id_club_user = id_club_user;
        this.why = why;
        this.you_are = you_are;
        this.skills = skills;
        this.member_id = member_id;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }
     
     

    public ClubUser() {
    }

    public ClubUser(int id_club_user, String why, String you_are, String skills) {
        this.id_club_user = id_club_user;
        this.why = why;
        this.you_are = you_are;
        this.skills = skills;
    }

    public int getId_club_user() {
        return id_club_user;
    }

    public void setId_club_user(int id_club_user) {
        this.id_club_user = id_club_user;
    }

    public String getClub_user_status() {
        return club_user_status;
    }

    public void setClub_user_status(String club_user_status) {
        this.club_user_status = club_user_status;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    public String getYou_are() {
        return you_are;
    }

    public void setYou_are(String you_are) {
        this.you_are = you_are;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "ClubUser{" + "id_club_user=" + id_club_user + ", club_user_status=" + club_user_status + ", why=" + why + ", you_are=" + you_are + ", skills=" + skills + ", member_id=" + member_id + '}';
    }


    
    
     
     
    
}
