package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.userAccount;

public class LoginDAO extends DBContext {
    //Lấy thông tin người dùng thông qua username và password - Đăng nhập
    public userAccount getByUsernamePassword(String username, String password) {
        String query = "SELECT * FROM Account WHERE UserName = ? AND Password = ?";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    userAccount account = new userAccount();
                    account.setUserid(rs.getInt("UserID"));
                    account.setEmail(rs.getString("Email"));
                    account.setUsername(rs.getString("UserName"));
                    account.setPassword(rs.getString("Password")); 
                    account.setFullname(rs.getString("FullName"));
                    account.setRoleid(rs.getInt("RoleID"));
                    return account;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
