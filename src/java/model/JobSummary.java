/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class JobSummary {

    int jobID;
    String jobTitle;
    Date endAt;
    int totalApplications;
    int status1Count;
    int status2Count;
    int status3Count;

    public JobSummary() {
    }

    
    public JobSummary(int jobID, String jobTitle, Date endAt, int totalApplications, int status1Count, int status2Count, int status3Count) {
        this.jobID = jobID;
        this.jobTitle = jobTitle;
        this.endAt = endAt;
        this.totalApplications = totalApplications;
        this.status1Count = status1Count;
        this.status2Count = status2Count;
        this.status3Count = status3Count;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public int getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(int totalApplications) {
        this.totalApplications = totalApplications;
    }

    public int getStatus1Count() {
        return status1Count;
    }

    public void setStatus1Count(int status1Count) {
        this.status1Count = status1Count;
    }

    public int getStatus2Count() {
        return status2Count;
    }

    public void setStatus2Count(int status2Count) {
        this.status2Count = status2Count;
    }

    public int getStatus3Count() {
        return status3Count;
    }

    public void setStatus3Count(int status3Count) {
        this.status3Count = status3Count;
    }
    
    

}
