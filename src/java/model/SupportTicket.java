/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class SupportTicket {
    int ticketID;
    int userID;
    String message;
    int statusID;
    Date createdAt;
    
    String fullname;
    String userRole;
    String statusTitle;

    public SupportTicket() {
    }

    public SupportTicket(int ticketID, int userID, String message, int statusID, Date createdAt, String fullname, String userRole, String statusTitle) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.message = message;
        this.statusID = statusID;
        this.createdAt = createdAt;
        this.fullname = fullname;
        this.userRole = userRole;
        this.statusTitle = statusTitle;
    }    
    


    public SupportTicket(int userID, String message, int statusID, Date createdAt) {
        this.userID = userID;
        this.message = message;
        this.statusID = statusID;
        this.createdAt = createdAt;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }
    
    
}
