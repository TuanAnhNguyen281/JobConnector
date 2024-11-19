/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tuananh
 */
public class JobType {
    int jobtypeID;
    String jobTypeDescription;

    public JobType(int jobtypeID, String jobTypeDescription) {
        this.jobtypeID = jobtypeID;
        this.jobTypeDescription = jobTypeDescription;
    }

    public int getJobtypeID() {
        return jobtypeID;
    }

    public void setJobtypeID(int jobtypeID) {
        this.jobtypeID = jobtypeID;
    }


    public String getJobTypeDescription() {
        return jobTypeDescription;
    }

    public void setJobTypeDescription(String jobTypeDescription) {
        this.jobTypeDescription = jobTypeDescription;
    }
    
}
