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
import java.util.ArrayList;
import java.util.List;
import model.EmployerProfile;

public class EmployerProfileDAO extends DBContext {

    public void addEmployerProfile(EmployerProfile newEprofile) {
        String getMaxEProfileIdSql = "SELECT MAX(EmployerID) FROM EmployerProfile";
        String sql = "INSERT INTO EmployerProfile (EmployerID, UserID, TaxNumber, CompanyName, Address, IndustryID, "
                + "SizeID, CompanyDescription, Logo, CheckID, Comment,EmployerEmail,EmployerPhone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

        try (Connection conn = getConnection()) {
            int newProfileId = 1;
            try (PreparedStatement psMax = conn.prepareStatement(getMaxEProfileIdSql); ResultSet rs = psMax.executeQuery()) {
                if (rs.next()) {
                    newProfileId = rs.getInt(1) + 1;
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, newProfileId);
                ps.setInt(2, newEprofile.getUserID());
                ps.setInt(3, newEprofile.getTaxNumber());
                ps.setString(4, newEprofile.getCompanyName());
                ps.setString(5, newEprofile.getAddress());
                ps.setInt(6, newEprofile.getIndustryID());
                ps.setInt(7, newEprofile.getSizeID());
                ps.setString(8, newEprofile.getCompanyDescription());
                ps.setString(9, newEprofile.getLogo());
                ps.setInt(10, newEprofile.getCheckID());
                ps.setString(11, newEprofile.getComment());
                ps.setString(12, newEprofile.getEmployerEmail());
                ps.setString(13, newEprofile.getEmployerPhone());

                ps.executeUpdate();
                System.out.println("Employer profile added successfully!"); // Success message

            } catch (SQLException e) {
                System.err.println("Error adding employer profile: " + e.getMessage()); // Print error message
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean existsEmployerProfile(int userID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            conn = getConnection();
            String sql = "SELECT COUNT(*) FROM EmployerProfile WHERE UserID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();

            if (rs.next()) {
                exists = (rs.getInt(1) > 0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public EmployerProfile getEprofileByUserID(int userID) {
        String sql = "SELECT ep.EmployerID, ep.UserID, ep.TaxNumber, ep.CompanyName, ep.Address, ep.IndustryID, "
                + "ep.SizeID, ep.CompanyDescription, ep.Logo, ep.CheckID, ep.Comment, ep.EmployerEmail, ep.EmployerPhone, "
                + "i.IndustryName, cs.SizeDescription, cep.StatusDescription "
                + "FROM EmployerProfile ep "
                + "JOIN Industry i ON ep.IndustryID = i.IndustryID "
                + "JOIN CompanySize cs ON ep.SizeID = cs.SizeID "
                + "JOIN CheckEmployerProfile cep ON ep.CheckID = cep.CheckID "
                + "WHERE ep.UserID = ?";

        EmployerProfile eProfile = null;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    eProfile = new EmployerProfile();
                    eProfile.setEmployerID(rs.getInt("EmployerID"));
                    eProfile.setUserID(rs.getInt("UserID"));
                    eProfile.setTaxNumber(rs.getInt("TaxNumber"));
                    eProfile.setCompanyName(rs.getString("CompanyName"));
                    eProfile.setAddress(rs.getString("Address"));
                    eProfile.setIndustryID(rs.getInt("IndustryID"));
                    eProfile.setSizeID(rs.getInt("SizeID"));
                    eProfile.setCompanyDescription(rs.getString("CompanyDescription"));
                    eProfile.setLogo(rs.getString("Logo"));
                    eProfile.setCheckID(rs.getInt("CheckID"));
                    eProfile.setComment(rs.getString("Comment"));
                    eProfile.setEmployerEmail(rs.getString("EmployerEmail"));
                    eProfile.setEmployerPhone(rs.getString("EmployerPhone"));

                    // Set the additional description fields
                    eProfile.setCompanyIndustry(rs.getString("IndustryName"));
                    eProfile.setCompanySize(rs.getString("SizeDescription"));
                    eProfile.setCompanystatus(rs.getString("StatusDescription"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving employer profile: " + e.getMessage());
        }
        return eProfile;
    }

    public void updateEprofileByUserID(EmployerProfile updatedEprofile) {
        String sql = "UPDATE EmployerProfile SET TaxNumber = ?, CompanyName = ?, Address = ?, IndustryID = ?, "
                + "SizeID = ?, CompanyDescription = ?, Logo = ?, CheckID = ?, Comment = ?, "
                + "EmployerEmail = ?, EmployerPhone = ? WHERE UserID = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, updatedEprofile.getTaxNumber());
            ps.setString(2, updatedEprofile.getCompanyName());
            ps.setString(3, updatedEprofile.getAddress());
            ps.setInt(4, updatedEprofile.getIndustryID());
            ps.setInt(5, updatedEprofile.getSizeID());
            ps.setString(6, updatedEprofile.getCompanyDescription());
            ps.setString(7, updatedEprofile.getLogo());
            ps.setInt(8, updatedEprofile.getCheckID());
            ps.setString(9, updatedEprofile.getComment());
            ps.setString(10, updatedEprofile.getEmployerEmail());
            ps.setString(11, updatedEprofile.getEmployerPhone());
            ps.setInt(12, updatedEprofile.getUserID());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employer profile updated successfully!");
            } else {
                System.out.println("No employer profile found with the given UserID.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating employer profile: " + e.getMessage());
        }
    }

    public EmployerProfile getEprofileByJobID(int jobID) {
        
        String sql = "SELECT ep.EmployerID, ep.UserID, ep.TaxNumber, ep.CompanyName, ep.Address, ep.IndustryID, "
                + "ep.SizeID, ep.CompanyDescription, ep.Logo, ep.CheckID, ep.Comment, ep.EmployerEmail, ep.EmployerPhone, "
                + "i.IndustryName, cs.SizeDescription, cep.StatusDescription "
                + "FROM EmployerProfile ep "
                + "JOIN Industry i ON ep.IndustryID = i.IndustryID "
                + "JOIN CompanySize cs ON ep.SizeID = cs.SizeID "
                + "JOIN CheckEmployerProfile cep ON ep.CheckID = cep.CheckID "
                + "JOIN Job j ON ep.UserID = j.UserID " 
                + "WHERE j.JobID = ?";

        EmployerProfile eProfile = null;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jobID);  // Set the JobID to the query
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    eProfile = new EmployerProfile();
                    eProfile.setEmployerID(rs.getInt("EmployerID"));
                    eProfile.setUserID(rs.getInt("UserID"));
                    eProfile.setTaxNumber(rs.getInt("TaxNumber"));
                    eProfile.setCompanyName(rs.getString("CompanyName"));
                    eProfile.setAddress(rs.getString("Address"));
                    eProfile.setIndustryID(rs.getInt("IndustryID"));
                    eProfile.setSizeID(rs.getInt("SizeID"));
                    eProfile.setCompanyDescription(rs.getString("CompanyDescription"));
                    eProfile.setLogo(rs.getString("Logo"));
                    eProfile.setCheckID(rs.getInt("CheckID"));
                    eProfile.setComment(rs.getString("Comment"));
                    eProfile.setEmployerEmail(rs.getString("EmployerEmail"));
                    eProfile.setEmployerPhone(rs.getString("EmployerPhone"));

                    // Set the additional description fields
                    eProfile.setCompanyIndustry(rs.getString("IndustryName"));
                    eProfile.setCompanySize(rs.getString("SizeDescription"));
                    eProfile.setCompanystatus(rs.getString("StatusDescription"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving employer profile by JobID: " + e.getMessage());
        }
        return eProfile;
    }

    public static void main(String[] args) {
//        EmployerProfile newEprofile = new EmployerProfile(1, 12345678, "companyName", "address", "employerEmail", 12345678, 1, 1, "companyDescription", "logo", 1, "comment");
//
//        // Create an instance of your class that contains the addEmployerProfile method
//        EmployerProfileDAO DAO = new EmployerProfileDAO();
//        DAO.addEmployerProfile(newEprofile);

//        int userID = 3;
//
//        // Create an instance of EmployerProfileDAO
//        EmployerProfileDAO dao = new EmployerProfileDAO();
//
//        // Check if the EmployerProfile exists
//        boolean exists = dao.existsEmployerProfile(userID);
//
//        // Output the result
//        if (exists) {
//            System.out.println("Employer profile exists for UserID: " + userID);
//        } else {
//            System.out.println("No employer profile found for UserID: " + userID);
//        }
//        EmployerProfileDAO dao = new EmployerProfileDAO();
//
//        // Test getEprofileByUserID
//        int testUserID = 1; // Replace with an actual UserID from your database
//        EmployerProfile profile = dao.getEprofileByUserID(testUserID);
//        if (profile != null) {
//            System.out.println("Retrieved Employer Profile:");
//            System.out.println("EmployerID: " + profile.getEmployerID());
//            System.out.println("UserID: " + profile.getUserID());
//            System.out.println("TaxNumber: " + profile.getTaxNumber());
//            System.out.println("CompanyName: " + profile.getCompanyName());
//            System.out.println("Address: " + profile.getAddress());
//            System.out.println("IndustryID: " + profile.getIndustryID());
//            System.out.println("SizeID: " + profile.getSizeID());
//            System.out.println("CompanyDescription: " + profile.getCompanyDescription());
//            System.out.println("Logo: " + profile.getLogo());
//            System.out.println("CheckID: " + profile.getCheckID());
//            System.out.println("Comment: " + profile.getComment());
//            System.out.println("EmployerEmail: " + profile.getEmployerEmail());
//            System.out.println("EmployerPhone: " + profile.getEmployerPhone());
//        } else {
//            System.out.println("No Employer Profile found for UserID: " + testUserID);
//        }

  // Khởi tạo đối tượng của lớp chứa phương thức getEprofileByJobID
        EmployerProfileDAO employerProfileDAO = new EmployerProfileDAO();
        
        // Giả sử chúng ta muốn kiểm tra EmployerProfile của công việc với JobID = 1
        int jobID = 3;
        
        // Gọi phương thức getEprofileByJobID để lấy thông tin nhà tuyển dụng
        EmployerProfile eProfile = employerProfileDAO.getEprofileByJobID(jobID);
        
        // Kiểm tra nếu đối tượng EmployerProfile không phải là null và in thông tin
        if (eProfile != null) {
            System.out.println("Employer ID: " + eProfile.getEmployerID());
            System.out.println("User ID: " + eProfile.getUserID());
            System.out.println("Tax Number: " + eProfile.getTaxNumber());
            System.out.println("Company Name: " + eProfile.getCompanyName());
            System.out.println("Address: " + eProfile.getAddress());
            System.out.println("Industry: " + eProfile.getCompanyIndustry());
            System.out.println("Size: " + eProfile.getCompanySize());
            System.out.println("Company Description: " + eProfile.getCompanyDescription());
            System.out.println("Employer Email: " + eProfile.getEmployerEmail());
            System.out.println("Employer Phone: " + eProfile.getEmployerPhone());
            System.out.println("Status: " + eProfile.getCompanystatus());
        } else {
            System.out.println("No employer profile found for JobID: " + jobID);
        }
    

    }

}
