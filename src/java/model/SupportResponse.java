/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author tuananh
 */
public class SupportResponse {
    int responseID;
    int userID;
    int ticketID;
    String response;
    Date responseAt;

    public SupportResponse() {
    }

    public SupportResponse(int userID, int ticketID, String response, Date responseAt) {
        this.userID = userID;
        this.ticketID = ticketID;
        this.response = response;
        this.responseAt = responseAt;
    }
    
    

    public SupportResponse(int responseID, int userID, int ticketID, String response, Date responseAt) {
        this.responseID = responseID;
        this.userID = userID;
        this.ticketID = ticketID;
        this.response = response;
        this.responseAt = responseAt;
    }

    public int getResponseID() {
        return responseID;
    }

    public void setResponseID(int responseID) {
        this.responseID = responseID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getResponseAt() {
        return responseAt;
    }

    public void setResponseAt(Date responseAt) {
        this.responseAt = responseAt;
    }
    
    
}
