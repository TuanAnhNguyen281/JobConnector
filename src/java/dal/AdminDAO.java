package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Account.Role;
import model.EmployerProfile;
import model.Job;

public class AdminDAO extends DBContext {

    public List<EmployerProfile> getAllCompanies() {
        List<EmployerProfile> employerProfileList = new ArrayList<>();
        String sql = "SELECT e.CompanyName, e.Address, e.TaxNumber, i.IndustryName, s.SizeDescription, c.StatusDescription "
                + "FROM EmployerProfile e "
                + "JOIN Industry i ON e.IndustryID = i.IndustryID "
                + "JOIN CompanySize s ON e.SizeID = s.SizeID "
                + "JOIN CheckEmployerProfile c ON e.CheckID = c.CheckID";

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                EmployerProfile employerProfile = new EmployerProfile();

                employerProfile.setCompanyName(rs.getString("CompanyName"));
                employerProfile.setAddress(rs.getString("Address"));
                employerProfile.setTaxNumber(rs.getInt("TaxNumber"));
                employerProfile.setCompanyIndustry(rs.getString("IndustryName"));
                employerProfile.setCompanySize(rs.getString("SizeDescription"));
                employerProfile.setCompanystatus(rs.getString("StatusDescription"));
                employerProfileList.add(employerProfile);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching employer profile list.", e);
        }

        return employerProfileList;
    }

    public List<EmployerProfile> getAllCompaniesPending() {
        List<EmployerProfile> employerProfileList = new ArrayList<>();

        String sql = "SELECT e.EmployerID, e.UserID, e.TaxNumber, e.CompanyName, e.Address, e.IndustryID, "
                + "e.SizeID, e.CompanyDescription, e.Logo, e.CheckID, e.Comment, e.EmployerEmail, e.EmployerPhone, "
                + "i.IndustryName, s.SizeDescription, c.StatusDescription "
                + "FROM EmployerProfile e "
                + "JOIN Industry i ON e.IndustryID = i.IndustryID "
                + "JOIN CompanySize s ON e.SizeID = s.SizeID "
                + "JOIN CheckEmployerProfile c ON e.CheckID = c.CheckID "
                + "WHERE e.CheckID = 1";  // Điều kiện lọc: chỉ lấy các công ty có CheckID = 2

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            // Lặp qua các kết quả trả về từ câu lệnh SQL
            while (rs.next()) {
                EmployerProfile employerProfile = new EmployerProfile();

                // Cập nhật các trường trong đối tượng EmployerProfile từ kết quả truy vấn
                employerProfile.setEmployerID(rs.getInt("EmployerID"));
                employerProfile.setUserID(rs.getInt("UserID"));
                employerProfile.setTaxNumber(rs.getInt("TaxNumber"));
                employerProfile.setCompanyName(rs.getString("CompanyName"));
                employerProfile.setAddress(rs.getString("Address"));
                employerProfile.setIndustryID(rs.getInt("IndustryID"));
                employerProfile.setSizeID(rs.getInt("SizeID"));
                employerProfile.setCompanyDescription(rs.getString("CompanyDescription"));
                employerProfile.setLogo(rs.getString("Logo"));
                employerProfile.setCheckID(rs.getInt("CheckID"));
                employerProfile.setComment(rs.getString("Comment"));
                employerProfile.setEmployerEmail(rs.getString("EmployerEmail"));
                employerProfile.setEmployerPhone(rs.getString("EmployerPhone"));

                // Thêm các mô tả ngành và kích thước công ty từ các bảng liên kết
                employerProfile.setCompanyIndustry(rs.getString("IndustryName"));
                employerProfile.setCompanySize(rs.getString("SizeDescription"));
                employerProfile.setCompanystatus(rs.getString("StatusDescription"));

                // Thêm đối tượng EmployerProfile vào danh sách
                employerProfileList.add(employerProfile);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching employer profile list.", e);
        }

        return employerProfileList;
    }

    public List<EmployerProfile> getAllCompaniesAccepted() {
        List<EmployerProfile> employerProfileList = new ArrayList<>();

        // Câu lệnh SQL lấy tất cả thông tin của công ty có CheckID = 2
        String sql = "SELECT e.EmployerID, e.UserID, e.TaxNumber, e.CompanyName, e.Address, e.IndustryID, "
                + "e.SizeID, e.CompanyDescription, e.Logo, e.CheckID, e.Comment, e.EmployerEmail, e.EmployerPhone, "
                + "i.IndustryName, s.SizeDescription, c.StatusDescription "
                + "FROM EmployerProfile e "
                + "JOIN Industry i ON e.IndustryID = i.IndustryID "
                + "JOIN CompanySize s ON e.SizeID = s.SizeID "
                + "JOIN CheckEmployerProfile c ON e.CheckID = c.CheckID "
                + "WHERE e.CheckID = 2";  // Điều kiện lọc: chỉ lấy các công ty có CheckID = 2

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            // Lặp qua các kết quả trả về từ câu lệnh SQL
            while (rs.next()) {
                EmployerProfile employerProfile = new EmployerProfile();

                // Cập nhật các trường trong đối tượng EmployerProfile từ kết quả truy vấn
                employerProfile.setEmployerID(rs.getInt("EmployerID"));
                employerProfile.setUserID(rs.getInt("UserID"));
                employerProfile.setTaxNumber(rs.getInt("TaxNumber"));
                employerProfile.setCompanyName(rs.getString("CompanyName"));
                employerProfile.setAddress(rs.getString("Address"));
                employerProfile.setIndustryID(rs.getInt("IndustryID"));
                employerProfile.setSizeID(rs.getInt("SizeID"));
                employerProfile.setCompanyDescription(rs.getString("CompanyDescription"));
                employerProfile.setLogo(rs.getString("Logo"));
                employerProfile.setCheckID(rs.getInt("CheckID"));
                employerProfile.setComment(rs.getString("Comment"));
                employerProfile.setEmployerEmail(rs.getString("EmployerEmail"));
                employerProfile.setEmployerPhone(rs.getString("EmployerPhone"));

                // Thêm các mô tả ngành và kích thước công ty từ các bảng liên kết
                employerProfile.setCompanyIndustry(rs.getString("IndustryName"));
                employerProfile.setCompanySize(rs.getString("SizeDescription"));
                employerProfile.setCompanystatus(rs.getString("StatusDescription"));

                // Thêm đối tượng EmployerProfile vào danh sách
                employerProfileList.add(employerProfile);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching employer profile list.", e);
        }

        return employerProfileList;
    }

    public EmployerProfile getEprofileByEmployerID(int employerID) {
        String sql = "SELECT ep.EmployerID, ep.UserID, ep.TaxNumber, ep.CompanyName, ep.Address, ep.IndustryID, "
                + "ep.SizeID, ep.CompanyDescription, ep.Logo, ep.CheckID, ep.Comment, ep.EmployerEmail, ep.EmployerPhone, "
                + "i.IndustryName, cs.SizeDescription, cep.StatusDescription "
                + "FROM EmployerProfile ep "
                + "JOIN Industry i ON ep.IndustryID = i.IndustryID "
                + "JOIN CompanySize cs ON ep.SizeID = cs.SizeID "
                + "JOIN CheckEmployerProfile cep ON ep.CheckID = cep.CheckID "
                + "WHERE ep.EmployerID = ?";

        EmployerProfile eProfile = null;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employerID);

            // Kiểm tra kết nối trước khi thực hiện truy vấn
            if (conn != null && !conn.isClosed()) {
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
            } else {
                System.err.println("Connection is closed or null.");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving employer profile: " + e.getMessage());
        }
        return eProfile;
    }

    public void updateEmployerProfileStatus(int employerID, int newCheckID, String comment) {
        String sql = "UPDATE EmployerProfile SET CheckID = ?, Comment = ? WHERE EmployerID = ?";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newCheckID); // Cập nhật CheckID
            ps.setString(2, comment); // Cập nhật Comment
            ps.setInt(3, employerID); // Điều kiện WHERE (EmployerID)

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employer profile updated successfully.");
            } else {
                System.out.println("No record found for EmployerID: " + employerID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating employer profile.");
        }
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

    public List<Job> getJobPendingDetails(int userID) {
        String sql = "SELECT j.jobID, j.userID, j.jobTitle, j.jobDescription, j.jobRequirement, j.jobBenefits, "
                + "j.noNeed, j.experienceID, j.salaryRangeID, j.locationID, j.industryID, j.jobTypeID, j.createdAt, "
                + "j.endAt, j.wayID, j.statusID, j.commentCheck, "
                + "e.EmployerID, e.CompanyName, e.Address, e.TaxNumber, i.IndustryName AS companyIndustry, "
                + "s.SizeDescription AS companySize, c.StatusDescription AS companyStatus "
                + "FROM Job j "
                + "JOIN EmployerProfile e ON j.userID = e.UserID "
                + "JOIN Industry i ON j.industryID = i.IndustryID "
                + "JOIN CompanySize s ON e.SizeID = s.SizeID "
                + "JOIN CheckEmployerProfile c ON e.CheckID = c.CheckID "
                + "WHERE j.statusID = 1 AND j.userID = ?";  // Thêm điều kiện UserID vào

        List<Job> jobList = new ArrayList<>();

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            // Set giá trị cho ? (userID)
            ps.setInt(1, userID);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Job job = new Job();
                    job.setJobID(rs.getInt("jobID"));
                    job.setUserID(rs.getInt("userID"));
                    job.setJobTitle(rs.getString("jobTitle"));
                    job.setJobDescription(rs.getString("jobDescription"));
                    job.setJobRequirement(rs.getString("jobRequirement"));
                    job.setJobBenefits(rs.getString("jobBenefits"));
                    job.setNoNeed(rs.getInt("noNeed"));
                    job.setExperienceID(rs.getInt("experienceID"));
                    job.setSalaryRangeID(rs.getInt("salaryRangeID"));
                    job.setLocationID(rs.getInt("locationID"));
                    job.setIndustryID(rs.getInt("industryID"));
                    job.setJobTypeID(rs.getInt("jobTypeID"));
                    job.setCreatedAt(rs.getDate("createdAt"));
                    job.setEndAt(rs.getDate("endAt"));
                    job.setWayID(rs.getInt("wayID"));
                    job.setStatusID(rs.getInt("statusID"));
                    job.setCommentCheck(rs.getString("commentCheck"));

                    // Tạo đối tượng EmployerProfile và thiết lập thông tin công ty
                    EmployerProfile employerProfile = new EmployerProfile();

                    employerProfile.setCompanyName(rs.getString("CompanyName"));

                    // Thêm job vào danh sách
                    jobList.add(job);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobList;
    }

    public List<Job> getJobAcceptedDetails(int userID) {
        String sql = "SELECT j.jobID, j.userID, j.jobTitle, j.jobDescription, j.jobRequirement, j.jobBenefits, "
                + "j.noNeed, j.experienceID, j.salaryRangeID, j.locationID, j.industryID, j.jobTypeID, j.createdAt, "
                + "j.endAt, j.wayID, j.statusID, j.commentCheck, "
                + "e.EmployerID, e.CompanyName, e.Address, e.TaxNumber, i.IndustryName AS companyIndustry, "
                + "s.SizeDescription AS companySize, c.StatusDescription AS companyStatus "
                + "FROM Job j "
                + "JOIN EmployerProfile e ON j.userID = e.UserID "
                + "JOIN Industry i ON j.industryID = i.IndustryID "
                + "JOIN CompanySize s ON e.SizeID = s.SizeID "
                + "JOIN CheckEmployerProfile c ON e.CheckID = c.CheckID "
                + "WHERE j.statusID = 2 AND j.userID = ?";  // Thêm điều kiện UserID vào

        List<Job> jobList = new ArrayList<>();

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            // Set giá trị cho ? (userID)
            ps.setInt(1, userID);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Job job = new Job();
                    job.setJobID(rs.getInt("jobID"));
                    job.setUserID(rs.getInt("userID"));
                    job.setJobTitle(rs.getString("jobTitle"));
                    job.setJobDescription(rs.getString("jobDescription"));
                    job.setJobRequirement(rs.getString("jobRequirement"));
                    job.setJobBenefits(rs.getString("jobBenefits"));
                    job.setNoNeed(rs.getInt("noNeed"));
                    job.setExperienceID(rs.getInt("experienceID"));
                    job.setSalaryRangeID(rs.getInt("salaryRangeID"));
                    job.setLocationID(rs.getInt("locationID"));
                    job.setIndustryID(rs.getInt("industryID"));
                    job.setJobTypeID(rs.getInt("jobTypeID"));
                    job.setCreatedAt(rs.getDate("createdAt"));
                    job.setEndAt(rs.getDate("endAt"));
                    job.setWayID(rs.getInt("wayID"));
                    job.setStatusID(rs.getInt("statusID"));
                    job.setCommentCheck(rs.getString("commentCheck"));

                    // Tạo đối tượng EmployerProfile và thiết lập thông tin công ty
                    EmployerProfile employerProfile = new EmployerProfile();
                    employerProfile.setCompanyName(rs.getString("CompanyName"));

                    // Thêm job vào danh sách
                    jobList.add(job);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobList;
    }

    public void updateJobStatus(int jobID, int newstatusId, String commentCheck) {
        String sql = "UPDATE Job SET StatusID = ?, CommentCheck = ? WHERE JobID = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            // Cập nhật StatusID và CommentCheck
            ps.setInt(1, newstatusId);
            ps.setString(2, commentCheck);
            ps.setInt(3, jobID);

            // Thực hiện cập nhật và kiểm tra số dòng bị ảnh hưởng
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Job status updated successfully. JobID: " + jobID);  // Thông báo thành công với JobID
            } else {
                System.out.println("No record found for JobID: " + jobID);  // Khi không tìm thấy công việc với JobID
            }
        } catch (SQLException e) {
            // In chi tiết lỗi để giúp debug dễ dàng hơn
            e.printStackTrace();
            System.out.println("Error updating job status. JobID: " + jobID + ", Error: " + e.getMessage());
        }
    }

    public boolean addAccount(Account account) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String getMaxUseridSql = "SELECT MAX(UserID) FROM Account";
        String sql = "INSERT INTO Account(UserID, UserName, FullName, Email, Password, RoleID) VALUES (?, ?, ?, ?, ?, ?)";

        int newUserid = 1;

        try (PreparedStatement psMaxId = connection.prepareStatement(getMaxUseridSql)) {
            rs = psMaxId.executeQuery();
            if (rs.next()) {
                newUserid = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            System.err.println("Error fetching max UserID: " + e.getMessage());
            return false;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        try (PreparedStatement stmtInsert = connection.prepareStatement(sql)) {
            stmtInsert.setInt(1, newUserid);
            stmtInsert.setString(2, account.getUserName());
            stmtInsert.setString(3, account.getFullName());
            stmtInsert.setString(4, account.getEmail());
            stmtInsert.setString(5, account.getPassword());
            stmtInsert.setInt(6, account.getRole().getRoleID());
            // Thực hiện câu lệnh INSERT
            int rowsInserted = stmtInsert.executeUpdate();
            return rowsInserted > 0;  // Nếu số bản ghi được chèn vào lớn hơn 0 thì thành công
        } catch (SQLException e) {
            System.err.println("Error inserting account: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT RoleID, RoleName FROM Roles";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int roleID = rs.getInt("RoleID");
                String roleName = rs.getString("RoleName");
                roles.add(new Role(roleID, roleName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        // Cập nhật truy vấn để chỉ lấy các tài khoản có RoleID = 3 hoặc 4
        String sql = "SELECT UserID, UserName, FullName, Email, RoleID FROM Account WHERE RoleID IN (3, 4)";

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int userID = rs.getInt("UserID");
                String userName = rs.getString("UserName");
                String fullName = rs.getString("FullName");
                String email = rs.getString("Email");
                int roleID = rs.getInt("RoleID");
                String roleName = (roleID == 3) ? "Quản trị viên" : (roleID == 4) ? "Hỗ trợ" : "Unknown";
                Account account = new Account(userID, userName, fullName, email, "", new Account.Role(roleID, roleName));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public static void main(String[] args) {

    }

}
