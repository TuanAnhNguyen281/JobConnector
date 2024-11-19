
package model;

import java.util.Date;


public class Application {
   int applicationID;
   int jobID;
   int userID;
   int CVID;
   int statusID;
   Date appliedAt;
   String Comment;
   
   String jobTitle;
   String companyName;
   String cvPath;
   String statusTitle;
   String fullName;
   String email;
   String userDescription;
   String userProfessionallSummary;
   String userProfilePicture;
   

    public Application() {
    }

    public Application(int jobID, int userID, int CVID, int statusID, Date appliedAt, String Comment) {
        this.jobID = jobID;
        this.userID = userID;
        this.CVID = CVID;
        this.statusID = statusID;
        this.appliedAt = appliedAt;
        this.Comment = Comment;
    }

    public Application(int applicationID, int jobID, int userID, int CVID, int statusID, Date appliedAt, String Comment) {
        this.applicationID = applicationID;
        this.jobID = jobID;
        this.userID = userID;
        this.CVID = CVID;
        this.statusID = statusID;
        this.appliedAt = appliedAt;
        this.Comment = Comment;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCVID() {
        return CVID;
    }

    public void setCVID(int CVID) {
        this.CVID = CVID;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public Date getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(Date appliedAt) {
        this.appliedAt = appliedAt;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCvPath() {
        return cvPath;
    }

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getUserProfessionallSummary() {
        return userProfessionallSummary;
    }

    public void setUserProfessionallSummary(String userProfessionallSummary) {
        this.userProfessionallSummary = userProfessionallSummary;
    }

    public String getUserProfilePicture() {
        return userProfilePicture;
    }

    public void setUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }
   
   
    
}
