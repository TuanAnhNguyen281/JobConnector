package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class dataDAO extends DBContext {

    // Lấy các ngành nghề
    public List<Industry> getAllIndustries() {
        List<Industry> industries = new ArrayList<>();
        String sql = "SELECT IndustryID, IndustryName,Icon FROM Industry";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Industry industry = new Industry(
                        rs.getInt("IndustryID"),
                        rs.getString("IndustryName"),
                        rs.getString("Icon")
                );
                industries.add(industry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return industries;
    }

    public List<CompanySize> getAllCompanySizes() {
        List<CompanySize> companySizes = new ArrayList<>();
        String sql = "SELECT SizeID, SizeDescription FROM CompanySize";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                CompanySize companySize = new CompanySize(
                        rs.getInt("SizeID"),
                        rs.getString("SizeDescription")
                );
                companySizes.add(companySize);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companySizes;
    }

    public List<ExperimentRequirement> getAllExperimentRequirement() {
        List<ExperimentRequirement> ers = new ArrayList<>();
        String sql = "SELECT  ExperienceID, ExperienceDescription FROM ExperienceRequirement";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ExperimentRequirement exp = new ExperimentRequirement(
                        rs.getInt("ExperienceID"),
                        rs.getString("ExperienceDescription")
                );
                ers.add(exp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ers;
    }

    public List<SalaryRange> getAllSalaryRanges() {
        List<SalaryRange> salaryRanges = new ArrayList<>();
        String sql = "SELECT   SalaryRangeID, SalaryDescription FROM SalaryRange";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                SalaryRange salary = new SalaryRange(
                        rs.getInt("SalaryRangeID"),
                        rs.getString("SalaryDescription")
                );
                salaryRanges.add(salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salaryRanges;
    }

    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT LocationID, LocationName FROM Location";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Location location = new Location(
                        rs.getInt("LocationID"),
                        rs.getString("LocationName")
                );
                locations.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    public List<JobType> getAllJobType() {
        List<JobType> types = new ArrayList<>();
        String sql = "SELECT JobTypeID, JobTypeDescription FROM JobType";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                JobType type = new JobType(
                        rs.getInt("JobTypeID"),
                        rs.getString("JobTypeDescription")
                );
                types.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    public List<ApplyWay> getAllApplyWay() {
        List<ApplyWay> ways = new ArrayList<>();
        String sql = "SELECT WayID, WayTitle FROM ApplyWay";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ApplyWay way = new ApplyWay(
                        rs.getInt("WayID"),
                        rs.getString("WayTitle")
                );
                ways.add(way);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ways;
    }

    public List<JobStatus> getAllJobStatus() {
        List<JobStatus> status = new ArrayList<>();
        String sql = "SELECT StatusID, StatusDescription FROM JobStatus";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                JobStatus stat = new JobStatus(
                        rs.getInt("StatusID"),
                        rs.getString("StatusDescription")
                );
                status.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        String sql = "SELECT PositionID, PositionTitle FROM Position";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Position pos = new Position(
                        rs.getInt("PositionID"),
                        rs.getString("PositionTitle")
                );
                positions.add(pos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positions;
    }

    public List<Gender> getAllGenders() {
        List<Gender> genders = new ArrayList<>();
        String sql = "SELECT GenderID, GenderTitle FROM Gender";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Gender gen = new Gender(
                        rs.getInt("GenderID"),
                        rs.getString("GenderTitle")
                );
                genders.add(gen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genders;
    }

    public static void main(String[] args) {
        // Tạo một đối tượng dataDAO
        dataDAO dao = new dataDAO();

        // Kiểm tra các phương thức lấy dữ liệu
        System.out.println("All Industries:");
        List<Industry> industries = dao.getAllIndustries();
        for (Industry industry : industries) {
            System.out.println("IndustryID: " + industry.getIndustryID() + ", IndustryName: " + industry.getIndustryName());
        }

        System.out.println("\nAll Company Sizes:");
        List<CompanySize> companySizes = dao.getAllCompanySizes();
        for (CompanySize companySize : companySizes) {
            System.out.println("SizeID: " + companySize.getSizeID() + ", SizeDescription: " + companySize.getSizeDescription());
        }

        System.out.println("\nAll Experiment Requirements:");
        List<ExperimentRequirement> experimentRequirements = dao.getAllExperimentRequirement();
        for (ExperimentRequirement exp : experimentRequirements) {
            System.out.println("ExperienceID: " + exp.getExperienceID() + ", ExperienceDescription: " + exp.getExperienceDescription());
        }

        System.out.println("\nAll Salary Ranges:");
        List<SalaryRange> salaryRanges = dao.getAllSalaryRanges();
        for (SalaryRange salaryRange : salaryRanges) {
            System.out.println("SalaryRangeID: " + salaryRange.getSalaryRangeID() + ", SalaryDescription: " + salaryRange.getSalaryDescription());
        }

        System.out.println("\nAll Locations:");
        List<Location> locations = dao.getAllLocations();
        for (Location location : locations) {
            System.out.println("LocationID: " + location.getLocationID() + ", LocationName: " + location.getLocationName());
        }

        System.out.println("\nAll Job Types:");
        List<JobType> jobTypes = dao.getAllJobType();
        for (JobType jobType : jobTypes) {
            System.out.println("JobTypeID: " + jobType.getJobtypeID() + ", JobTypeDescription: " + jobType.getJobTypeDescription());
        }

        System.out.println("\nAll Apply Ways:");
        List<ApplyWay> applyWays = dao.getAllApplyWay();
        for (ApplyWay applyWay : applyWays) {
            System.out.println("WayID: " + applyWay.getWayID() + ", WayTitle: " + applyWay.getWayTitle());
        }

        System.out.println("\nAll position :");
        List<Position> positions = dao.getAllPositions();
        for (Position position : positions) {
            System.out.println("PosID: " + position.getPositionID() + ", PosTitle: " + position.getPositionTitle());
        }
    }

}
