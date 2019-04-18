/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author PC ASUS
 */
public class Tickett {
    private int id_ticket;
    private int user_id;
    private int event_id;
    private Date time_boocked;
    private int status;
    private String qr_code;
    private int code;

    public Tickett() {
    }

    public Tickett(int id_ticket, int user_id, int event_id, Date time_boocked, int status, String qr_code, int code) {
        this.id_ticket = id_ticket;
        this.user_id = user_id;
        this.event_id = event_id;
        this.time_boocked = time_boocked;
        this.status = status;
        this.qr_code = qr_code;
        this.code = code;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public Date getTime_boocked() {
        return time_boocked;
    }

    public void setTime_boocked(Date time_boocked) {
        this.time_boocked = time_boocked;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ticket{" + "id_ticket=" + id_ticket + ", user_id=" + user_id + ", event_id=" + event_id + ", time_boocked=" + time_boocked + ", status=" + status + ", qr_code=" + qr_code + ", code=" + code + '}';
    }
    
    
    
    
}
