/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.JobSeekerProfile;

public class jobseekerProfileDAO extends DBContext {

    public void addJobSeekerProfile(JobSeekerProfile profile) {
        String getMaxJsIdSql = "SELECT MAX(JobSeekerID) FROM JobSeekerProfile";
        String sql = "INSERT INTO JobSeekerProfile (JobSeekerID, UserID, DoB, GenderID, PhoneNumber, "
                + "PositionID, Description, ProfessionalSummary, Skills, Experience, Education, "
                + "ProfilePicture, StatusID, Address) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        try (Connection conn = getConnection()) {
            int newJsId = 1;
            try (PreparedStatement psMax = conn.prepareStatement(getMaxJsIdSql); ResultSet rs = psMax.executeQuery()) {
                if (rs.next()) {
                    newJsId = rs.getInt(1) + 1;
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, newJsId);
                ps.setInt(2, profile.getUserID());
                ps.setDate(3, new Date(profile.getDob().getTime())); // Convert java.util.Date to java.sql.Date
                ps.setInt(4, profile.getGenderID());
                ps.setInt(5, profile.getPhoneNumber());
                ps.setInt(6, profile.getPositionID());
                ps.setString(7, profile.getDescription());
                ps.setString(8, profile.getProfessionalSummary());
                ps.setString(9, profile.getSkills());
                ps.setString(10, profile.getExperience());
                ps.setString(11, profile.getEducation());
                ps.setString(12, profile.getProfilePicture());
                ps.setInt(13, profile.getStatusID());
                ps.setString(14, profile.getAddress());

                ps.executeUpdate();
                System.out.println("Job Seeker Profile added successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean isProfileExists(int userID) {
    String sql = "SELECT COUNT(*) FROM JobSeekerProfile WHERE UserID = ?";
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, userID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    
    public JobSeekerProfile getJobSeekerProfileByUserID(int userID) {
    String sql = "SELECT JobSeekerID, UserID, DoB, GenderID, PhoneNumber, PositionID, Description, "
               + "ProfessionalSummary, Skills, Experience, Education, ProfilePicture, StatusID, Address "
               + "FROM JobSeekerProfile WHERE UserID = ?";
    JobSeekerProfile profile = null;
    
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, userID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                profile = new JobSeekerProfile();
                profile.setJobSeekerID(rs.getInt("JobSeekerID"));
                profile.setUserID(rs.getInt("UserID"));
                profile.setDob(rs.getDate("DoB")); // java.sql.Date to java.util.Date
                profile.setGenderID(rs.getInt("GenderID"));
                profile.setPhoneNumber(rs.getInt("PhoneNumber"));
                profile.setPositionID(rs.getInt("PositionID"));
                profile.setDescription(rs.getString("Description"));
                profile.setProfessionalSummary(rs.getString("ProfessionalSummary"));
                profile.setSkills(rs.getString("Skills"));
                profile.setExperience(rs.getString("Experience"));
                profile.setEducation(rs.getString("Education"));
                profile.setProfilePicture(rs.getString("ProfilePicture"));
                profile.setStatusID(rs.getInt("StatusID"));
                profile.setAddress(rs.getString("Address"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return profile;
}
    
    public void updateByUserID(JobSeekerProfile profile) {
    String sql = "UPDATE JobSeekerProfile SET "
            + "DoB = ?, GenderID = ?, PhoneNumber = ?, PositionID = ?, Description = ?, "
            + "ProfessionalSummary = ?, Skills = ?, Experience = ?, Education = ?, "
            + "ProfilePicture = ?, StatusID = ?, Address = ? "
            + "WHERE UserID = ?";

    try (Connection conn = getConnection(); 
         PreparedStatement ps = conn.prepareStatement(sql)) {


        ps.setDate(1, new Date(profile.getDob().getTime())); 
        ps.setInt(2, profile.getGenderID());
        ps.setInt(3, profile.getPhoneNumber());
        ps.setInt(4, profile.getPositionID());
        ps.setString(5, profile.getDescription());
        ps.setString(6, profile.getProfessionalSummary());
        ps.setString(7, profile.getSkills());
        ps.setString(8, profile.getExperience());
        ps.setString(9, profile.getEducation());
        ps.setString(10, profile.getProfilePicture());
        ps.setInt(11, profile.getStatusID());
        ps.setString(12, profile.getAddress());
        ps.setInt(13, profile.getUserID());

        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Job Seeker Profile updated successfully!");
        } else {
            System.out.println("No profile found for the given UserID.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    



    public static void main(String[] args) {
        // Create a JobSeekerProfile object with some test data
        JobSeekerProfile profile = new JobSeekerProfile();
        profile.setUserID(2);  // Assume UserID 1 exists in the database
        profile.setDob(new java.util.Date());  // Set current date as Date of Birth
        profile.setGenderID(1);  // Assuming 1 = Male
        profile.setPhoneNumber(123456789);  // Example phone number
        profile.setPositionID(2);  // Example PositionID
        profile.setDescription("Experienced Java Developer");
        profile.setProfessionalSummary("Strong background in Java development with expertise in Spring Boot and microservices.");
        profile.setSkills("Java, Spring Boot, SQL, REST APIs");
        profile.setExperience("5+ years of experience in software development.");
        profile.setEducation("Bachelor's in Computer Science");
        profile.setProfilePicture("OIP.jpg");  // Example profile picture filename
        profile.setStatusID(1);  // Assuming 1 = Active status
        profile.setAddress("123 Main St, City, Country");

      
        jobseekerProfileDAO jobSeekerDAO = new jobseekerProfileDAO();

 
        jobSeekerDAO.updateByUserID(profile);
    }
}
