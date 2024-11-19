/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import model.Job;

public class FilterJobDAO extends DBContext {

    public List<Job> getJobsByIndustry(int industryID) {
        List<Job> jobList = new ArrayList<>();
        String sql = "SELECT j.JobID, j.UserID, j.JobTitle, j.JobDescription, j.JobRequirement, j.JobBenefits, "
                + "j.NoNeed, j.ExperienceID, j.SalaryRangeID, j.LocationID, j.IndustryID, j.JobTypeID, "
                + "j.CreatedAt, j.EndAt, j.WayID, j.StatusID, j.CommentCheck, "
                + "e.ExperienceDescription AS experienceTitle, "
                + "s.SalaryDescription AS salaryRangeTitle, "
                + "l.LocationName AS locationTitle, "
                + "i.IndustryName AS industryTitle, "
                + "t.JobTypeDescription AS jobTypeTitle, "
                + "w.WayTitle AS wayTitle, "
                + "st.StatusDescription AS statusTitle, "
                + "ep.CompanyName AS CompanyName, "
                + "ep.Logo AS Logo "
                + "FROM Job j "
                + "JOIN ExperienceRequirement e ON j.ExperienceID = e.ExperienceID "
                + "JOIN SalaryRange s ON j.SalaryRangeID = s.SalaryRangeID "
                + "JOIN Location l ON j.LocationID = l.LocationID "
                + "JOIN Industry i ON j.IndustryID = i.IndustryID "
                + "JOIN JobType t ON j.JobTypeID = t.JobTypeID "
                + "JOIN ApplyWay w ON j.WayID = w.WayID "
                + "JOIN JobStatus st ON j.StatusID = st.StatusID "
                + "JOIN EmployerProfile ep ON j.UserID = ep.UserID "
                + "WHERE st.StatusID = 2 AND j.IndustryID = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, industryID);  // Set the industryID parameter

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Job job = new Job();
                    job.setJobID(rs.getInt("JobID"));
                    job.setUserID(rs.getInt("UserID"));
                    job.setJobTitle(rs.getString("JobTitle"));
                    job.setJobDescription(rs.getString("JobDescription"));
                    job.setJobRequirement(rs.getString("JobRequirement"));
                    job.setJobBenefits(rs.getString("JobBenefits"));
                    job.setNoNeed(rs.getInt("NoNeed"));
                    job.setExperienceID(rs.getInt("ExperienceID"));
                    job.setSalaryRangeID(rs.getInt("SalaryRangeID"));
                    job.setLocationID(rs.getInt("LocationID"));
                    job.setIndustryID(rs.getInt("IndustryID"));
                    job.setJobTypeID(rs.getInt("JobTypeID"));
                    job.setCreatedAt(rs.getDate("CreatedAt"));
                    job.setEndAt(rs.getDate("EndAt"));
                    job.setWayID(rs.getInt("WayID"));
                    job.setStatusID(rs.getInt("StatusID"));
                    job.setCommentCheck(rs.getString("CommentCheck"));

                    // Set related information from joined tables
                    job.setExperienceTitle(rs.getString("experienceTitle"));
                    job.setSalaryRangeTitle(rs.getString("salaryRangeTitle"));
                    job.setLocationTitle(rs.getString("locationTitle"));
                    job.setIndustryTitle(rs.getString("industryTitle"));
                    job.setJobtypeTitle(rs.getString("jobTypeTitle"));
                    job.setWayTitle(rs.getString("wayTitle"));
                    job.setStatusTitle(rs.getString("statusTitle"));
                    job.setCompanyName(rs.getString("CompanyName"));
                    job.setLogo(rs.getString("Logo"));

                    jobList.add(job);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    public List<Job> searchByTitle(String jobTitle) {
        List<Job> jobList = new ArrayList<>();
        String sql = "SELECT j.JobID, j.UserID, j.JobTitle, j.JobDescription, j.JobRequirement, j.JobBenefits, "
                + "j.NoNeed, j.ExperienceID, j.SalaryRangeID, j.LocationID, j.IndustryID, j.JobTypeID, "
                + "j.CreatedAt, j.EndAt, j.WayID, j.StatusID, j.CommentCheck, "
                + "e.ExperienceDescription AS experienceTitle, "
                + "s.SalaryDescription AS salaryRangeTitle, "
                + "l.LocationName AS locationTitle, "
                + "i.IndustryName AS industryTitle, "
                + "t.JobTypeDescription AS jobTypeTitle, "
                + "w.WayTitle AS wayTitle, "
                + "st.StatusDescription AS statusTitle, "
                + "ep.CompanyName AS CompanyName, "
                + "ep.Logo AS Logo "
                + "FROM Job j "
                + "JOIN ExperienceRequirement e ON j.ExperienceID = e.ExperienceID "
                + "JOIN SalaryRange s ON j.SalaryRangeID = s.SalaryRangeID "
                + "JOIN Location l ON j.LocationID = l.LocationID "
                + "JOIN Industry i ON j.IndustryID = i.IndustryID "
                + "JOIN JobType t ON j.JobTypeID = t.JobTypeID "
                + "JOIN ApplyWay w ON j.WayID = w.WayID "
                + "JOIN JobStatus st ON j.StatusID = st.StatusID "
                + "JOIN EmployerProfile ep ON j.UserID = ep.UserID "
                + "WHERE st.StatusID = 2 AND j.JobTitle LIKE ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + jobTitle + "%");  // Search for job titles containing the given string

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Job job = new Job();
                    job.setJobID(rs.getInt("JobID"));
                    job.setUserID(rs.getInt("UserID"));
                    job.setJobTitle(rs.getString("JobTitle"));
                    job.setJobDescription(rs.getString("JobDescription"));
                    job.setJobRequirement(rs.getString("JobRequirement"));
                    job.setJobBenefits(rs.getString("JobBenefits"));
                    job.setNoNeed(rs.getInt("NoNeed"));
                    job.setExperienceID(rs.getInt("ExperienceID"));
                    job.setSalaryRangeID(rs.getInt("SalaryRangeID"));
                    job.setLocationID(rs.getInt("LocationID"));
                    job.setIndustryID(rs.getInt("IndustryID"));
                    job.setJobTypeID(rs.getInt("JobTypeID"));
                    job.setCreatedAt(rs.getDate("CreatedAt"));
                    job.setEndAt(rs.getDate("EndAt"));
                    job.setWayID(rs.getInt("WayID"));
                    job.setStatusID(rs.getInt("StatusID"));
                    job.setCommentCheck(rs.getString("CommentCheck"));

                    // Set related information from joined tables
                    job.setExperienceTitle(rs.getString("experienceTitle"));
                    job.setSalaryRangeTitle(rs.getString("salaryRangeTitle"));
                    job.setLocationTitle(rs.getString("locationTitle"));
                    job.setIndustryTitle(rs.getString("industryTitle"));
                    job.setJobtypeTitle(rs.getString("jobTypeTitle"));
                    job.setWayTitle(rs.getString("wayTitle"));
                    job.setStatusTitle(rs.getString("statusTitle"));
                    job.setCompanyName(rs.getString("CompanyName"));
                    job.setLogo(rs.getString("Logo"));

                    jobList.add(job);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    public List<Job> filterByLocation(int locationID) {
        List<Job> jobList = new ArrayList<>();
        String sql = "SELECT j.JobID, j.UserID, j.JobTitle, j.JobDescription, j.JobRequirement, j.JobBenefits, "
                + "j.NoNeed, j.ExperienceID, j.SalaryRangeID, j.LocationID, j.IndustryID, j.JobTypeID, "
                + "j.CreatedAt, j.EndAt, j.WayID, j.StatusID, j.CommentCheck, "
                + "e.ExperienceDescription AS experienceTitle, "
                + "s.SalaryDescription AS salaryRangeTitle, "
                + "l.LocationName AS locationTitle, "
                + "i.IndustryName AS industryTitle, "
                + "t.JobTypeDescription AS jobTypeTitle, "
                + "w.WayTitle AS wayTitle, "
                + "st.StatusDescription AS statusTitle, "
                + "ep.CompanyName AS CompanyName, "
                + "ep.Logo AS Logo "
                + "FROM Job j "
                + "JOIN ExperienceRequirement e ON j.ExperienceID = e.ExperienceID "
                + "JOIN SalaryRange s ON j.SalaryRangeID = s.SalaryRangeID "
                + "JOIN Location l ON j.LocationID = l.LocationID "
                + "JOIN Industry i ON j.IndustryID = i.IndustryID "
                + "JOIN JobType t ON j.JobTypeID = t.JobTypeID "
                + "JOIN ApplyWay w ON j.WayID = w.WayID "
                + "JOIN JobStatus st ON j.StatusID = st.StatusID "
                + "JOIN EmployerProfile ep ON j.UserID = ep.UserID "
                + "WHERE st.StatusID = 2 AND j.LocationID = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, locationID);  // Set locationID parameter

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Job job = new Job();
                    job.setJobID(rs.getInt("JobID"));
                    job.setUserID(rs.getInt("UserID"));
                    job.setJobTitle(rs.getString("JobTitle"));
                    job.setJobDescription(rs.getString("JobDescription"));
                    job.setJobRequirement(rs.getString("JobRequirement"));
                    job.setJobBenefits(rs.getString("JobBenefits"));
                    job.setNoNeed(rs.getInt("NoNeed"));
                    job.setExperienceID(rs.getInt("ExperienceID"));
                    job.setSalaryRangeID(rs.getInt("SalaryRangeID"));
                    job.setLocationID(rs.getInt("LocationID"));
                    job.setIndustryID(rs.getInt("IndustryID"));
                    job.setJobTypeID(rs.getInt("JobTypeID"));
                    job.setCreatedAt(rs.getDate("CreatedAt"));
                    job.setEndAt(rs.getDate("EndAt"));
                    job.setWayID(rs.getInt("WayID"));
                    job.setStatusID(rs.getInt("StatusID"));
                    job.setCommentCheck(rs.getString("CommentCheck"));

                    // Set related information from joined tables
                    job.setExperienceTitle(rs.getString("experienceTitle"));
                    job.setSalaryRangeTitle(rs.getString("salaryRangeTitle"));
                    job.setLocationTitle(rs.getString("locationTitle"));
                    job.setIndustryTitle(rs.getString("industryTitle"));
                    job.setJobtypeTitle(rs.getString("jobTypeTitle"));
                    job.setWayTitle(rs.getString("wayTitle"));
                    job.setStatusTitle(rs.getString("statusTitle"));
                    job.setCompanyName(rs.getString("CompanyName"));
                    job.setLogo(rs.getString("Logo"));

                    jobList.add(job);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    public List<Job> searchAndFilter(String jobTitle, int locationID) {
        List<Job> jobList = new ArrayList<>();
        String sql = "SELECT j.JobID, j.UserID, j.JobTitle, j.JobDescription, j.JobRequirement, j.JobBenefits, "
                + "j.NoNeed, j.ExperienceID, j.SalaryRangeID, j.LocationID, j.IndustryID, j.JobTypeID, "
                + "j.CreatedAt, j.EndAt, j.WayID, j.StatusID, j.CommentCheck, "
                + "e.ExperienceDescription AS experienceTitle, "
                + "s.SalaryDescription AS salaryRangeTitle, "
                + "l.LocationName AS locationTitle, "
                + "i.IndustryName AS industryTitle, "
                + "t.JobTypeDescription AS jobTypeTitle, "
                + "w.WayTitle AS wayTitle, "
                + "st.StatusDescription AS statusTitle, "
                + "ep.CompanyName AS CompanyName, "
                + "ep.Logo AS Logo "
                + "FROM Job j "
                + "JOIN ExperienceRequirement e ON j.ExperienceID = e.ExperienceID "
                + "JOIN SalaryRange s ON j.SalaryRangeID = s.SalaryRangeID "
                + "JOIN Location l ON j.LocationID = l.LocationID "
                + "JOIN Industry i ON j.IndustryID = i.IndustryID "
                + "JOIN JobType t ON j.JobTypeID = t.JobTypeID "
                + "JOIN ApplyWay w ON j.WayID = w.WayID "
                + "JOIN JobStatus st ON j.StatusID = st.StatusID "
                + "JOIN EmployerProfile ep ON j.UserID = ep.UserID "
                + "WHERE st.StatusID = 2 AND j.JobTitle LIKE ? AND j.LocationID = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + jobTitle + "%");
            ps.setInt(2, locationID);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Job job = new Job();
                    job.setJobID(rs.getInt("JobID"));
                    job.setUserID(rs.getInt("UserID"));
                    job.setJobTitle(rs.getString("JobTitle"));
                    job.setJobDescription(rs.getString("JobDescription"));
                    job.setJobRequirement(rs.getString("JobRequirement"));
                    job.setJobBenefits(rs.getString("JobBenefits"));
                    job.setNoNeed(rs.getInt("NoNeed"));
                    job.setExperienceID(rs.getInt("ExperienceID"));
                    job.setSalaryRangeID(rs.getInt("SalaryRangeID"));
                    job.setLocationID(rs.getInt("LocationID"));
                    job.setIndustryID(rs.getInt("IndustryID"));
                    job.setJobTypeID(rs.getInt("JobTypeID"));
                    job.setCreatedAt(rs.getDate("CreatedAt"));
                    job.setEndAt(rs.getDate("EndAt"));
                    job.setWayID(rs.getInt("WayID"));
                    job.setStatusID(rs.getInt("StatusID"));
                    job.setCommentCheck(rs.getString("CommentCheck"));

                    // Set related information from joined tables
                    job.setExperienceTitle(rs.getString("experienceTitle"));
                    job.setSalaryRangeTitle(rs.getString("salaryRangeTitle"));
                    job.setLocationTitle(rs.getString("locationTitle"));
                    job.setIndustryTitle(rs.getString("industryTitle"));
                    job.setJobtypeTitle(rs.getString("jobTypeTitle"));
                    job.setWayTitle(rs.getString("wayTitle"));
                    job.setStatusTitle(rs.getString("statusTitle"));
                    job.setCompanyName(rs.getString("CompanyName"));
                    job.setLogo(rs.getString("Logo"));

                    jobList.add(job);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobList;
    }
}
