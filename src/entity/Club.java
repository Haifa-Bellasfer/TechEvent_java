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
public class Club {
    private int id_club;
  
    private String club_name;
    
    private String logo;
   
    private String club_description;
   
    private String email;
   
    private String facebook;
   
    private String club_status="Waiting";
    
    private int theme;
    
    private int owner;
    
    

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public Club( String club_name, String club_description, String email, String facebook,int theme,int owner) {
        
        this.club_name = club_name;
        this.club_description = club_description;
        this.email = email;
        this.facebook = facebook;
        this.club_status="Waiting";
        this.theme = theme;
        this.owner = owner;
        
    }
    
    

    
    
    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }


    public int getId_club() {
        return id_club;
    }

    public void setId_club(int id_club) {
        this.id_club = id_club;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getClub_description() {
        return club_description;
    }

    public void setClub_description(String club_description) {
        this.club_description = club_description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getClub_status() {
        return club_status;
    }

    public void setClub_status(String club_status) {
        this.club_status = club_status;
    }


    public Club(String club_name,  String club_description, String email, String facebook) {
        
        this.club_name = club_name;
        
        this.club_description = club_description;
        this.email = email;
        this.facebook = facebook;
        
    }

    public Club(int id_club, String club_name, String logo, String club_description, String email, String facebook, int theme) {
        this.id_club = id_club;
        this.club_name = club_name;
        this.logo = logo;
        this.club_description = club_description;
        this.email = email;
        this.facebook = facebook;
        this.theme = theme;
    }

    public Club() {
    }
    

    @Override
    public String toString() {
        return   club_name ;
    }
    
     public String affiche() {
         StringBuilder sb = new StringBuilder();
         sb.append("Club name:    ");
         sb.append(club_name).append(System.getProperty("line.separator")); 
         sb.append("        ").append(System.getProperty("line.separator")); 
         sb.append("Club description:    ");
         sb.append(club_description).append(System.getProperty("line.separator")); 
         sb.append("Email:    ");
         sb.append(email).append(System.getProperty("line.separator"));
         sb.append("Facebook:    ");
         sb.append(facebook).append(System.getProperty("line.separator"));
        return   sb.toString();
   
    }
    
}
