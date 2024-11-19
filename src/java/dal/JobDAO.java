package dal;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import model.Job;

public class JobDAO extends DBContext {

    public void addJob(Job job) {
        String getMaxJobIdSql = "SELECT MAX(JobID) FROM Job";
        String sql = "INSERT INTO Job (JobID, UserID, JobTitle, JobDescription, JobRequirement, JobBenefits, "
                + "NoNeed, ExperienceID, SalaryRangeID, LocationID, IndustryID, JobTypeID, "
                + "CreatedAt, EndAt, WayID, CommentCheck) "
                + "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection()) {
            int newJobId = 1;
            try (PreparedStatement psMax = conn.prepareStatement(getMaxJobIdSql); ResultSet rs = psMax.executeQuery()) {
                if (rs.next()) {
                    newJobId = rs.getInt(1) + 1;
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, newJobId);
                ps.setInt(2, job.getUserID());
                ps.setString(3, job.getJobTitle());
                ps.setString(4, job.getJobDescription());
                ps.setString(5, job.getJobRequirement());
                ps.setString(6, job.getJobBenefits());
                ps.setInt(7, job.getNoNeed());
                ps.setInt(8, job.getExperienceID());
                ps.setInt(9, job.getSalaryRangeID());
                ps.setInt(10, job.getLocationID());
                ps.setInt(11, job.getIndustryID());
                ps.setInt(12, job.getJobTypeID());
                ps.setDate(13, new java.sql.Date(job.getCreatedAt().getTime()));
                ps.setDate(14, new java.sql.Date(job.getEndAt().getTime()));
                ps.setInt(15, job.getWayID());
                ps.setString(16, job.getCommentCheck());

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Job> getAllJobsByUserId(int userId) {
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
                + "st.StatusDescription AS statusTitle "
                + "FROM Job j "
                + "JOIN ExperienceRequirement e ON j.ExperienceID = e.ExperienceID "
                + "JOIN SalaryRange s ON j.SalaryRangeID = s.SalaryRangeID "
                + "JOIN Location l ON j.LocationID = l.LocationID "
                + "JOIN Industry i ON j.IndustryID = i.IndustryID "
                + "JOIN JobType t ON j.JobTypeID = t.JobTypeID "
                + "JOIN ApplyWay w ON j.WayID = w.WayID "
                + "JOIN JobStatus st ON j.StatusID = st.StatusID "
                + "WHERE j.UserID = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);

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

                    // Set các thông tin từ các bảng liên kết
                    job.setExperienceTitle(rs.getString("experienceTitle"));
                    job.setSalaryRangeTitle(rs.getString("salaryRangeTitle"));
                    job.setLocationTitle(rs.getString("locationTitle"));
                    job.setIndustryTitle(rs.getString("industryTitle"));
                    job.setJobtypeTitle(rs.getString("jobTypeTitle"));
                    job.setWayTitle(rs.getString("wayTitle"));
                    job.setStatusTitle(rs.getString("statusTitle"));

                    jobList.add(job);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    public Job getJobByJobId(int jobId) {
        String sql = "SELECT j.JobID, j.UserID, j.JobTitle, j.JobDescription, j.JobRequirement, j.JobBenefits, "
                + "j.NoNeed, j.ExperienceID, j.SalaryRangeID, j.LocationID, j.IndustryID, j.JobTypeID, "
                + "j.CreatedAt, j.EndAt, j.WayID, j.StatusID, j.CommentCheck, "
                + "e.ExperienceDescription AS experienceTitle, "
                + "s.SalaryDescription AS salaryRangeTitle, "
                + "l.LocationName AS locationTitle, "
                + "i.IndustryName AS industryTitle, "
                + "t.JobTypeDescription AS jobTypeTitle, "
                + "w.WayTitle AS wayTitle, "
                + "st.StatusDescription AS statusTitle "
                + "FROM Job j "
                + "JOIN ExperienceRequirement e ON j.ExperienceID = e.ExperienceID "
                + "JOIN SalaryRange s ON j.SalaryRangeID = s.SalaryRangeID "
                + "JOIN Location l ON j.LocationID = l.LocationID "
                + "JOIN Industry i ON j.IndustryID = i.IndustryID "
                + "JOIN JobType t ON j.JobTypeID = t.JobTypeID "
                + "JOIN ApplyWay w ON j.WayID = w.WayID "
                + "JOIN JobStatus st ON j.StatusID = st.StatusID "
                + "WHERE j.JobID = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jobId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
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

                    // Set the additional information from the joined tables
                    job.setExperienceTitle(rs.getString("experienceTitle"));
                    job.setSalaryRangeTitle(rs.getString("salaryRangeTitle"));
                    job.setLocationTitle(rs.getString("locationTitle"));
                    job.setIndustryTitle(rs.getString("industryTitle"));
                    job.setJobtypeTitle(rs.getString("jobTypeTitle"));
                    job.setWayTitle(rs.getString("wayTitle"));
                    job.setStatusTitle(rs.getString("statusTitle"));

                    return job;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateJob(Job job) {
        String sql = "UPDATE Job SET UserID = ?, JobTitle = ?, JobDescription = ?, JobRequirement = ?, JobBenefits = ?, "
                + "NoNeed = ?, ExperienceID = ?, SalaryRangeID = ?, LocationID = ?, IndustryID = ?, "
                + "JobTypeID = ?, CreatedAt = ?, EndAt = ?, WayID = ?,StatusID = ?, CommentCheck = ? "
                + "WHERE JobID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, job.getUserID());
            ps.setString(2, job.getJobTitle());
            ps.setString(3, job.getJobDescription());
            ps.setString(4, job.getJobRequirement());
            ps.setString(5, job.getJobBenefits());
            ps.setInt(6, job.getNoNeed());
            ps.setInt(7, job.getExperienceID());
            ps.setInt(8, job.getSalaryRangeID());
            ps.setInt(9, job.getLocationID());
            ps.setInt(10, job.getIndustryID());
            ps.setInt(11, job.getJobTypeID());
            ps.setDate(12, new java.sql.Date(job.getCreatedAt().getTime()));
            ps.setDate(13, new java.sql.Date(job.getEndAt().getTime()));
            ps.setInt(14, job.getWayID());
            ps.setInt(15, job.getStatusID());
            ps.setString(16, job.getCommentCheck());
            ps.setInt(17, job.getJobID());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteJob(int jobId) {
        String sql = "DELETE FROM Job WHERE JobID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jobId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Job> filterJobByStatus(int statusID) {
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
                + "st.StatusDescription AS statusTitle "
                + "FROM Job j "
                + "JOIN ExperienceRequirement e ON j.ExperienceID = e.ExperienceID "
                + "JOIN SalaryRange s ON j.SalaryRangeID = s.SalaryRangeID "
                + "JOIN Location l ON j.LocationID = l.LocationID "
                + "JOIN Industry i ON j.IndustryID = i.IndustryID "
                + "JOIN JobType t ON j.JobTypeID = t.JobTypeID "
                + "JOIN ApplyWay w ON j.WayID = w.WayID "
                + "JOIN JobStatus st ON j.StatusID = st.StatusID "
                + "WHERE j.StatusID = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, statusID);

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

                    // Set các thông tin từ các bảng liên kết
                    job.setExperienceTitle(rs.getString("experienceTitle"));
                    job.setSalaryRangeTitle(rs.getString("salaryRangeTitle"));
                    job.setLocationTitle(rs.getString("locationTitle"));
                    job.setIndustryTitle(rs.getString("industryTitle"));
                    job.setJobtypeTitle(rs.getString("jobTypeTitle"));
                    job.setWayTitle(rs.getString("wayTitle"));
                    job.setStatusTitle(rs.getString("statusTitle"));

                    jobList.add(job);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    public List<Job> getAllJobsAfterCheck() {
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
                + "WHERE st.StatusID = 2 AND ep.CheckID = 2";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

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

                    // Set các thông tin từ các bảng liên kết
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

    public static void main(String[] args) {

    }

}
