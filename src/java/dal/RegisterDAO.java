package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.*;
import java.sql.ResultSet;

public class RegisterDAO extends DBContext {

    //tạo tài khoản mới
    public void registerUser(userAccount user) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String getMaxUseridSql = "SELECT MAX(UserID) FROM Account";
            String query = "INSERT INTO Account(UserID, UserName, RoleID, Email, Password, FullName, PackageID) VALUES (?, ?, ?, ?, ?, ?,?)";

            int newUserid = 1;
            try (PreparedStatement psMaxId = connection.prepareStatement(getMaxUseridSql)) {
                rs = psMaxId.executeQuery();
                if (rs.next()) {
                    newUserid = rs.getInt(1) + 1;
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

            stm = connection.prepareStatement(query);
            stm.setInt(1, newUserid);
            stm.setString(2, user.getUsername());
            stm.setInt(3, user.getRoleid());
            stm.setString(4, user.getEmail());
            stm.setString(5, user.getPassword());
            stm.setString(6, user.getFullname());
            stm.setInt(7, user.getPackageID());

            int result = stm.executeUpdate();

            if (result > 0) {
                System.out.println("User registered successfully with ID: " + newUserid);
                user.setUserid(newUserid);
            } else {
                System.out.println("Failed to register user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Kiểm tra xem người dùng đã tồn tại bằng email hay chưa
    public boolean checkUserExists(String email) {
        String query = "SELECT * FROM Account WHERE Email = ?";
        try (
                Connection conn = connection; PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        userAccount newUser = new userAccount();

        newUser.setEmail("testemail@gmail.com");
        newUser.setFullname("nametesst");
        newUser.setPassword("testpass");
        newUser.setRoleid(2);
        newUser.setUsername("testjobseeker");
        RegisterDAO dao = new RegisterDAO();

        dao.registerUser(newUser);

    }

}
