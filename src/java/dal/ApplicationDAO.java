/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Application;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.JobSummary;

public class ApplicationDAO extends DBContext {

    public void addApplication(Application application) {
        String getMaxAppidSql = "SELECT MAX(ApplicationID) FROM Application";
        String sql = "INSERT INTO Application (ApplicationID, JobID,CVID,StatusID, AppliedAt, Comment,UserID) VALUES (?, ?, ?, ?, ?,?,?)";

        try (PreparedStatement psGetMaxId = connection.prepareStatement(getMaxAppidSql); ResultSet rs = psGetMaxId.executeQuery()) {
            int newAppId = 1;
            if (rs.next()) {
                newAppId = rs.getInt(1) + 1;
            }

            try (PreparedStatement psInsert = connection.prepareStatement(sql)) {
                psInsert.setInt(1, newAppId);
                psInsert.setInt(2, application.getJobID());
                psInsert.setInt(3, application.getCVID());
                psInsert.setInt(4, application.getStatusID());
                psInsert.setDate(5, new Date(application.getAppliedAt().getTime()));
                psInsert.setString(6, application.getComment());
                psInsert.setInt(7, application.getUserID());

                psInsert.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isJobAlreadyAppliedByUser(int jobId, int userId) {
        String sql = "SELECT COUNT(*) FROM Application WHERE JobID = ? AND UserID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, jobId);
            ps.setInt(2, userId);

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

    public List<Application> getAppLyByUserId(int userId) {
        String sql = "SELECT a.ApplicationID, a.JobID, a.UserID, a.CVID, a.StatusID, a.AppliedAt, a.Comment,  "
                + "j.JobTitle, c.FilePath, s.StatusDescription, ep.CompanyName "
                + "FROM Application a "
                + "JOIN Job j ON a.JobID = j.JobID "
                + "JOIN CV c ON a.CVID = c.CVID "
                + "JOIN ApplicationStatus s ON a.StatusID = s.StatusID "
                + "JOIN EmployerProfile ep ON j.UserID = ep.UserID "
                + "WHERE a.UserID = ?";

        List<Application> applications = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Application app = new Application();
                    app.setApplicationID(rs.getInt("ApplicationID"));
                    app.setJobID(rs.getInt("JobID"));
                    app.setCVID(rs.getInt("CVID"));
                    app.setStatusID(rs.getInt("StatusID"));
                    app.setAppliedAt(rs.getDate("AppliedAt"));
                    app.setComment(rs.getString("Comment"));
                    app.setUserID(rs.getInt("UserID"));

                    app.setJobTitle(rs.getString("JobTitle"));
                    app.setCompanyName(rs.getString("CompanyName"));
                    app.setCvPath(rs.getString("FilePath"));
                    app.setStatusTitle(rs.getString("StatusDescription"));

                    // Adding the employer's company name from EmployerProfile
                    app.setCompanyName(rs.getString("CompanyName"));

                    applications.add(app);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    public List<Application> getApplicationsByJobId(int jobId) {
        String sql = "SELECT a.ApplicationID, a.JobID, a.UserID, a.CVID, a.StatusID, a.AppliedAt, a.Comment, "
                + "acc.FullName, cv.FilePath, s.StatusDescription "
                + "FROM Application a "
                + "JOIN Account acc ON a.UserID = acc.UserID "
                + "JOIN CV cv ON a.CVID = cv.CVID "
                + "JOIN ApplicationStatus s ON a.StatusID = s.StatusID "
                + "WHERE a.JobID = ?";
        List<Application> applications = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, jobId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Application app = new Application();
                    app.setApplicationID(rs.getInt("ApplicationID"));
                    app.setJobID(rs.getInt("JobID"));
                    app.setUserID(rs.getInt("UserID"));
                    app.setCVID(rs.getInt("CVID"));
                    app.setStatusID(rs.getInt("StatusID"));
                    app.setAppliedAt(rs.getDate("AppliedAt"));
                    app.setComment(rs.getString("Comment"));

                    app.setFullName(rs.getString("FullName"));
                    app.setCvPath(rs.getString("FilePath"));
                    app.setStatusTitle(rs.getString("StatusDescription"));

                    applications.add(app);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    public void deleteApplication(int applicationId) {
        String sql = "DELETE FROM Application WHERE ApplicationID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, applicationId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted application with ID: " + applicationId);
            } else {
                System.out.println("No application found with ID: " + applicationId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JobSummary> getJobSummaryByEmployerId(int employerId) {
        String sql = "SELECT j.JobID, j.JobTitle, j.EndAt, "
                + "COUNT(a.ApplicationID) AS TotalApplications, "
                + "SUM(CASE WHEN a.StatusID = 1 THEN 1 ELSE 0 END) AS Status1Count, "
                + "SUM(CASE WHEN a.StatusID = 2 THEN 1 ELSE 0 END) AS Status2Count, "
                + "SUM(CASE WHEN a.StatusID = 3 THEN 1 ELSE 0 END) AS Status3Count "
                + "FROM Job j "
                + "LEFT JOIN Application a ON j.JobID = a.JobID "
                + "WHERE j.UserID = ? "
                + "GROUP BY j.JobID, j.JobTitle, j.EndAt";

        List<JobSummary> jobSummaries = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, employerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    JobSummary summary = new JobSummary();
                    summary.setJobID(rs.getInt("JobID"));
                    summary.setJobTitle(rs.getString("JobTitle"));
                    summary.setEndAt(rs.getDate("EndAt"));
                    summary.setTotalApplications(rs.getInt("TotalApplications"));
                    summary.setStatus1Count(rs.getInt("Status1Count"));
                    summary.setStatus2Count(rs.getInt("Status2Count"));
                    summary.setStatus3Count(rs.getInt("Status3Count"));

                    jobSummaries.add(summary);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobSummaries;
    }

    public Application getApplicationByApplicationID(int applicationID) {
        String sql = "SELECT a.ApplicationID, a.JobID, a.UserID, a.CVID, a.StatusID, a.AppliedAt, a.Comment, "
                + "acc.FullName, acc.Email, cv.FilePath, jsp.Description, jsp.ProfessionalSummary,jsp.ProfilePicture, "
                + "s.StatusDescription "
                + "FROM Application a "
                + "JOIN Account acc ON a.UserID = acc.UserID "
                + "JOIN JobSeekerProfile jsp ON a.UserID = jsp.UserID "
                + "JOIN CV cv ON a.CVID = cv.CVID "
                + "JOIN ApplicationStatus s ON a.StatusID = s.StatusID "
                + "WHERE a.ApplicationID = ?";

        Application application = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, applicationID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    application = new Application();
                    application.setApplicationID(rs.getInt("ApplicationID"));
                    application.setJobID(rs.getInt("JobID"));
                    application.setUserID(rs.getInt("UserID"));
                    application.setCVID(rs.getInt("CVID"));
                    application.setStatusID(rs.getInt("StatusID"));
                    application.setAppliedAt(rs.getDate("AppliedAt"));
                    application.setComment(rs.getString("Comment"));

                    application.setFullName(rs.getString("FullName"));
                    application.setEmail(rs.getString("Email"));
                    application.setCvPath(rs.getString("FilePath"));
                    application.setUserDescription(rs.getString("Description"));
                    application.setUserProfessionallSummary(rs.getString("ProfessionalSummary"));
                    application.setStatusTitle(rs.getString("StatusDescription"));
                    application.setUserProfilePicture("ProfilePicture");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return application;
    }

    public void updateApplication(int applicationID, String newComment, int newStatusID) {
        String sql = "UPDATE Application SET Comment = ?, StatusID = ? WHERE ApplicationID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newComment);
            ps.setInt(2, newStatusID);
            ps.setInt(3, applicationID);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Application updated successfully.");
            } else {
                System.out.println("No application found with the given ApplicationID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ApplicationDAO applicationDAO = new ApplicationDAO();


        // Test the update method with ApplicationID = 2, Comment = "Updated comment", and StatusID = 2
        applicationDAO.updateApplication(2, "Updated comment", 2);
    }
}
