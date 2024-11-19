package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.userAccount;

public class ForgotDAO extends DBContext {

    //Lấy thông tin người dùng thông qua email
    public userAccount getByEmail(String email) {
        String query = "SELECT * FROM UserAccount WHERE Email = ?";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    userAccount account = new userAccount();
                    account.setUserid(rs.getInt("UserID"));
                    account.setEmail(rs.getString("Email"));
                    account.setUsername(rs.getString("Username"));
                    account.setPassword(rs.getString("Password")); // Store the password securely!
                    account.setFullname(rs.getString("Fullname"));
                    account.setRoleid(rs.getInt("Roleid"));
                    return account;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Chỉnh sửa mật khẩu Database
    public boolean updatePassword(int userId, String newPassword) {
        String query = "UPDATE UserAccount SET Password = ? WHERE UserID = ?";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, newPassword);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
