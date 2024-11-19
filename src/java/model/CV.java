/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class CV {

  int cvID;
  int userID;
  String filePath;
  Date dateCreated;

    public CV() {
    }

    public CV(int userID, String filePath, Date dateCreated) {
        this.userID = userID;
        this.filePath = filePath;
        this.dateCreated = dateCreated;
    }

    public CV(int cvID, int userID, String filePath, Date dateCreated) {
        this.cvID = cvID;
        this.userID = userID;
        this.filePath = filePath;
        this.dateCreated = dateCreated;
    }

    public int getCvID() {
        return cvID;
    }

    public void setCvID(int cvID) {
        this.cvID = cvID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }





  

   
  
  

}
