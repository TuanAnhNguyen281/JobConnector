package model;

import java.util.Date;

public class JobSeekerProfile {

    int jobSeekerID;
    int userID;
    Date Dob;
    int genderID;
    int phoneNumber;
    int positionID;
    String description;
    String professionalSummary;
    String skills;
    String experience;
    String education;
    String profilePicture;
    int statusID;
    String address;
    
    String positionTitle;
    String genderTitle;
    String StatusTitle;

    public JobSeekerProfile() {
    }

    public JobSeekerProfile(int userID, Date Dob, int genderID, int phoneNumber, int positionID, String description, String professionalSummary, String skills, String experience, String education, String profilePicture, int statusID, String address) {
        this.userID = userID;
        this.Dob = Dob;
        this.genderID = genderID;
        this.phoneNumber = phoneNumber;
        this.positionID = positionID;
        this.description = description;
        this.professionalSummary = professionalSummary;
        this.skills = skills;
        this.experience = experience;
        this.education = education;
        this.profilePicture = profilePicture;
        this.statusID = statusID;
        this.address = address;
    }



    public int getJobSeekerID() {
        return jobSeekerID;
    }

    public void setJobSeekerID(int jobSeekerID) {
        this.jobSeekerID = jobSeekerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date Dob) {
        this.Dob = Dob;
    }

    public int getGenderID() {
        return genderID;
    }

    public void setGenderID(int genderID) {
        this.genderID = genderID;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPositionID() {
        return positionID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfessionalSummary() {
        return professionalSummary;
    }

    public void setProfessionalSummary(String professionalSummary) {
        this.professionalSummary = professionalSummary;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getGenderTitle() {
        return genderTitle;
    }

    public void setGenderTitle(String genderTitle) {
        this.genderTitle = genderTitle;
    }

    public String getStatusTitle() {
        return StatusTitle;
    }

    public void setStatusTitle(String StatusTitle) {
        this.StatusTitle = StatusTitle;
    }
    
    

}
