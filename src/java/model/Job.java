package model;

import java.util.Date;
import java.text.NumberFormat;
import java.util.Locale;

public class Job {

    int jobID;
    int userID;
    String jobTitle;
    String jobDescription;
    String jobRequirement;
    String jobBenefits;
    int noNeed;
    int experienceID;
    int salaryRangeID;
    int locationID;
    int industryID;
    int jobTypeID;
    Date createdAt;
    Date endAt;
    int wayID;
    int statusID;
    String commentCheck;
    
    String experienceTitle;
    String salaryRangeTitle;
    String locationTitle;
    String industryTitle;
    String jobtypeTitle;
    String wayTitle;
    String statusTitle;
    
    String companyName;
    String logo;

    public Job() {
    }

    public Job(int userID, String jobTitle, String jobDescription, String jobRequirement, String jobBenefits, int noNeed, int experienceID, int salaryRangeID, int locationID, int industryID, int jobTypeID, Date createdAt, Date endAt, int wayID, int statusID, String commentCheck) {
        this.userID = userID;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobRequirement = jobRequirement;
        this.jobBenefits = jobBenefits;
        this.noNeed = noNeed;
        this.experienceID = experienceID;
        this.salaryRangeID = salaryRangeID;
        this.locationID = locationID;
        this.industryID = industryID;
        this.jobTypeID = jobTypeID;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.wayID = wayID;
        this.statusID = statusID;
        this.commentCheck = commentCheck;
    }

    public Job(int jobID, int userID, String jobTitle, String jobDescription, String jobRequirement, String jobBenefits, int noNeed, int experienceID, int salaryRangeID, int locationID, int industryID, int jobTypeID, Date createdAt, Date endAt, int wayID, int statusID, String commentCheck) {
        this.jobID = jobID;
        this.userID = userID;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobRequirement = jobRequirement;
        this.jobBenefits = jobBenefits;
        this.noNeed = noNeed;
        this.experienceID = experienceID;
        this.salaryRangeID = salaryRangeID;
        this.locationID = locationID;
        this.industryID = industryID;
        this.jobTypeID = jobTypeID;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.wayID = wayID;
        this.statusID = statusID;
        this.commentCheck = commentCheck;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobRequirement() {
        return jobRequirement;
    }

    public void setJobRequirement(String jobRequirement) {
        this.jobRequirement = jobRequirement;
    }

    public String getJobBenefits() {
        return jobBenefits;
    }

    public void setJobBenefits(String jobBenefits) {
        this.jobBenefits = jobBenefits;
    }

    public int getNoNeed() {
        return noNeed;
    }

    public void setNoNeed(int noNeed) {
        this.noNeed = noNeed;
    }

    public int getExperienceID() {
        return experienceID;
    }

    public void setExperienceID(int experienceID) {
        this.experienceID = experienceID;
    }

    public int getSalaryRangeID() {
        return salaryRangeID;
    }

    public void setSalaryRangeID(int salaryRangeID) {
        this.salaryRangeID = salaryRangeID;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public int getIndustryID() {
        return industryID;
    }

    public void setIndustryID(int industryID) {
        this.industryID = industryID;
    }

    public int getJobTypeID() {
        return jobTypeID;
    }

    public void setJobTypeID(int jobTypeID) {
        this.jobTypeID = jobTypeID;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public int getWayID() {
        return wayID;
    }

    public void setWayID(int wayID) {
        this.wayID = wayID;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getCommentCheck() {
        return commentCheck;
    }

    public void setCommentCheck(String commentCheck) {
        this.commentCheck = commentCheck;
    }

    public String getExperienceTitle() {
        return experienceTitle;
    }

    public void setExperienceTitle(String experienceTitle) {
        this.experienceTitle = experienceTitle;
    }

    public String getSalaryRangeTitle() {
        return salaryRangeTitle;
    }

    public void setSalaryRangeTitle(String salaryRangeTitle) {
        this.salaryRangeTitle = salaryRangeTitle;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public void setLocationTitle(String locationTitle) {
        this.locationTitle = locationTitle;
    }

    public String getIndustryTitle() {
        return industryTitle;
    }

    public void setIndustryTitle(String industryTitle) {
        this.industryTitle = industryTitle;
    }

    public String getJobtypeTitle() {
        return jobtypeTitle;
    }

    public void setJobtypeTitle(String jobtypeTitle) {
        this.jobtypeTitle = jobtypeTitle;
    }

    public String getWayTitle() {
        return wayTitle;
    }

    public void setWayTitle(String wayTitle) {
        this.wayTitle = wayTitle;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
      
    

    
}
