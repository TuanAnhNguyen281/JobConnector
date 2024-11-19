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
import model.*;

public class SupportDAO extends DBContext {

    public void createTicket(SupportTicket ticket) {
        String getMaxTicketIdSql = "SELECT MAX(TicketID) FROM SupportTicket";
        String sql = "INSERT INTO SupportTicket (TicketID, UserID, Message, StatusID, CreatedAt) VALUES (?,?, ?, ?, ?)";

        try (Connection conn = getConnection()) {
            int newTicketId = 1;
            try (PreparedStatement psMax = conn.prepareStatement(getMaxTicketIdSql); ResultSet rs = psMax.executeQuery()) {
                if (rs.next()) {
                    newTicketId = rs.getInt(1) + 1;
                }
            }
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, newTicketId);
                stmt.setInt(2, ticket.getUserID());
                stmt.setString(3, ticket.getMessage());
                stmt.setInt(4, ticket.getStatusID());
                stmt.setDate(5, new java.sql.Date(ticket.getCreatedAt().getTime()));

                stmt.executeUpdate();
                System.out.println("Ticket created successfully!");
            } catch (SQLException e) {
                System.err.println("Error creating ticket: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Error creating ticket: " + e.getMessage());
        }
    }

    public List<SupportTicket> getAllSupportTickets() {
        List<SupportTicket> tickets = new ArrayList<>();
        String sql = "SELECT t.TicketID, t.UserID, t.Message, t.StatusID, t.CreatedAt, "
                + "a.FullName, r.RoleName, s.StatusDescription "
                + "FROM SupportTicket t "
                + "JOIN Account a ON t.UserID = a.UserID "
                + "JOIN Role r ON a.RoleID = r.RoleID "
                + "JOIN SupportStatus s ON t.StatusID = s.StatusID";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int ticketID = rs.getInt("TicketID");
                int userID = rs.getInt("UserID");
                String message = rs.getString("Message");
                int statusID = rs.getInt("StatusID");
                Date createdAt = rs.getDate("CreatedAt");
                String fullName = rs.getString("FullName");
                String roleName = rs.getString("RoleName");
                String statusTitle = rs.getString("StatusDescription");

                SupportTicket ticket = new SupportTicket(ticketID, userID, message, statusID, createdAt, fullName, roleName, statusTitle);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving tickets: " + e.getMessage());
        }

        return tickets;
    }

    public List<SupportTicket> getAllInProgressTickets() {
        List<SupportTicket> tickets = new ArrayList<>();
        String sql = "SELECT t.TicketID, t.UserID, t.Message, t.StatusID, t.CreatedAt, "
                + "a.FullName, r.RoleName, s.StatusDescription "
                + "FROM SupportTicket t "
                + "JOIN Account a ON t.UserID = a.UserID "
                + "JOIN Role r ON a.RoleID = r.RoleID "
                + "JOIN SupportStatus s ON t.StatusID = s.StatusID "
                + "WHERE t.StatusID = 1"; // Chỉ lấy vé có StatusID = 1

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int ticketID = rs.getInt("TicketID");
                int userID = rs.getInt("UserID");
                String message = rs.getString("Message");
                int statusID = rs.getInt("StatusID");
                Date createdAt = rs.getDate("CreatedAt");
                String fullName = rs.getString("FullName");
                String roleName = rs.getString("RoleName");
                String statusTitle = rs.getString("StatusDescription");

                SupportTicket ticket = new SupportTicket(ticketID, userID, message, statusID, createdAt, fullName, roleName, statusTitle);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving in-progress tickets: " + e.getMessage());
        }

        return tickets;
    }

    public List<SupportTicket> getAllClosedTickets() {
        List<SupportTicket> tickets = new ArrayList<>();
        String sql = "SELECT t.TicketID, t.UserID, t.Message, t.StatusID, t.CreatedAt, "
                + "a.FullName, r.RoleName, s.StatusDescription "
                + "FROM SupportTicket t "
                + "JOIN Account a ON t.UserID = a.UserID "
                + "JOIN Role r ON a.RoleID = r.RoleID "
                + "JOIN SupportStatus s ON t.StatusID = s.StatusID "
                + "WHERE t.StatusID = 2";
        

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int ticketID = rs.getInt("TicketID");
                int userID = rs.getInt("UserID");
                String message = rs.getString("Message");
                int statusID = rs.getInt("StatusID");
                Date createdAt = rs.getDate("CreatedAt");
                String fullName = rs.getString("FullName");
                String roleName = rs.getString("RoleName");
                String statusTitle = rs.getString("StatusDescription");

                SupportTicket ticket = new SupportTicket(ticketID, userID, message, statusID, createdAt, fullName, roleName, statusTitle);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving closed tickets: " + e.getMessage());
        }

        return tickets;
    }

    public SupportTicket getTicketByTicketID(int ticketID) {
        String sql = "SELECT t.TicketID, t.UserID, t.Message, t.StatusID, t.CreatedAt, "
                + "a.FullName, r.RoleName, s.StatusDescription "
                + "FROM SupportTicket t "
                + "JOIN Account a ON t.UserID = a.UserID "
                + "JOIN Role r ON a.RoleID = r.RoleID "
                + "JOIN SupportStatus s ON t.StatusID = s.StatusID "
                + "WHERE t.TicketID = ?";
        SupportTicket ticket = null;

        try (Connection conn = new DBContext().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ticketID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String message = rs.getString("Message");
                    int statusID = rs.getInt("StatusID");
                    Date createdAt = rs.getDate("CreatedAt");
                    String fullName = rs.getString("FullName");
                    String roleName = rs.getString("RoleName");
                    String statusTitle = rs.getString("StatusDescription");

                    ticket = new SupportTicket(ticketID, userID, message, statusID, createdAt, fullName, roleName, statusTitle);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving ticket by TicketID: " + e.getMessage());
        }

        return ticket;
    }
    
      public List<SupportTicket> getAllByUserID(int userID) {
        List<SupportTicket> tickets = new ArrayList<>();
        String sql = "SELECT t.TicketID, t.UserID, t.Message, t.StatusID, t.CreatedAt, "
                + "a.FullName, r.RoleName, s.StatusDescription "
                + "FROM SupportTicket t "
                + "JOIN Account a ON t.UserID = a.UserID "
                + "JOIN Role r ON a.RoleID = r.RoleID "
                + "JOIN SupportStatus s ON t.StatusID = s.StatusID "
                + "WHERE t.UserID = ?";  

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, userID);  
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int ticketID = rs.getInt("TicketID");
                    String message = rs.getString("Message");
                    int statusID = rs.getInt("StatusID");
                    Date createdAt = rs.getDate("CreatedAt");
                    String fullName = rs.getString("FullName");
                    String roleName = rs.getString("RoleName");
                    String statusTitle = rs.getString("StatusDescription");

                    SupportTicket ticket = new SupportTicket(ticketID, userID, message, statusID, createdAt, fullName, roleName, statusTitle);
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving tickets for UserID " + userID + ": " + e.getMessage());
        }

        return tickets;
    }

    public void updateTicketStatus(int ticketId, int newStatusId) {
        String sql = "UPDATE SupportTicket SET StatusID = ? WHERE TicketID = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newStatusId);
            stmt.setInt(2, ticketId);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Ticket status updated successfully!");
            } else {
                System.out.println("No ticket found with the specified TicketID.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating ticket status: " + e.getMessage());
        }
    }

    public void createResponse(SupportResponse response) {
        String getMaxResponseIdSql = "SELECT MAX(ResponseID) FROM SupportResponse";
        String sql = "INSERT INTO SupportResponse (ResponseID,SupporterID,TicketID, Response, RespondedAt) VALUES (?,?, ?, ?, ?)";

        try (Connection conn = getConnection()) {
            int newResponseId = 1;
            try (PreparedStatement psMax = conn.prepareStatement(getMaxResponseIdSql); ResultSet rs = psMax.executeQuery()) {
                if (rs.next()) {
                    newResponseId = rs.getInt(1) + 1;
                }
            }
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, newResponseId);
                stmt.setInt(2, response.getUserID());
                stmt.setInt(3, response.getTicketID());
                stmt.setString(4, response.getResponse());
                stmt.setDate(5, new java.sql.Date(response.getResponseAt().getTime()));

                stmt.executeUpdate();
                System.out.println("response created successfully!");
            } catch (SQLException e) {
                System.err.println("Error creating response: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Error creating response: " + e.getMessage());
        }
    }

    public List<SupportResponse> getResponsesByTicketID(int ticketID) {
        List<SupportResponse> responses = new ArrayList<>();
        String sql = "SELECT r.ResponseID, r.SupporterID, r.TicketID, r.Response, r.RespondedAt "
                + "FROM SupportResponse r "
                + "JOIN SupportTicket t ON r.TicketID = t.TicketID "
                + "WHERE t.TicketID = ?";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ticketID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int responseID = rs.getInt("ResponseID");
                    int supporterID = rs.getInt("SupporterID");
                    String responseText = rs.getString("Response");
                    Date respondedAt = rs.getDate("RespondedAt");

                    SupportResponse response = new SupportResponse(responseID, supporterID, ticketID, responseText, respondedAt);
                    responses.add(response);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving responses for TicketID " + ticketID + ": " + e.getMessage());
        }

        return responses;
    }

    public static void main(String[] args) {
        SupportDAO DAO = new SupportDAO();

        // Tạo đối tượng SupportTicket mới
//        SupportTicket newTicket = new SupportTicket(1, "This is a test message.", 1,new java.util.Date());
        //     SupportResponse Responese = new SupportResponse(6, 1, "siuuuuuuuuuuu", new java.util.Date());
        // Gọi hàm createTicket để thêm ticket vào cơ sở dữ liệu
//        DAO.createTicket(newTicket);
        //    DAO.createResponse(Responese);
//        List<SupportTicket> tickets = DAO.getAllSupportTickets();
//
//        // Kiểm tra và in ra danh sách ticket nếu có
//        if (tickets.isEmpty()) {
//            System.out.println("No support tickets found.");
//        } else {
//            System.out.println("List of Support Tickets:");
//            for (SupportTicket ticket : tickets) {
//                System.out.println("TicketID: " + ticket.getTicketID());
//                System.out.println("UserID: " + ticket.getUserID());
//                System.out.println("Message: " + ticket.getMessage());
//                System.out.println("StatusID: " + ticket.getStatusID());
//                System.out.println("CreatedAt: " + ticket.getCreatedAt());
//                System.out.println("---------------");
//            }
//        }
//        System.out.println("Test completed.");
//        List<SupportResponse> responses = DAO.getResponsesByTicketID(1);
//
//        if (responses.isEmpty()) {
//            System.out.println("No responses found for TicketID: " + 1);
//        } else {
//            System.out.println("Responses for TicketID " + 1 + ":");
//            for (SupportResponse response : responses) {
//                System.out.println("ResponseID: " + response.getResponseID());
//                System.out.println("SupporterID: " + response.getUserID());
//                System.out.println("Response: " + response.getResponse());
//                System.out.println("RespondedAt: " + response.getResponseAt());
//                System.out.println("---------------");
//            }
//        }
//        SupportTicket ticket = DAO.getTicketByTicketID(1);
//
//        if (ticket != null) {
//            System.out.println("Ticket Found:");
//            System.out.println("TicketID: " + ticket.getTicketID());
//            System.out.println("UserID: " + ticket.getUserID());
//            System.out.println("Message: " + ticket.getMessage());
//            System.out.println("StatusID: " + ticket.getStatusID());
//            System.out.println("CreatedAt: " + ticket.getCreatedAt());
//            System.out.println("Fullname" + ticket.getFullname());
//            System.out.println("roleName" + ticket.getUserRole());
//        } else {
//            System.out.println("No ticket found with TicketID: " + 1);
//        }
 int ticketID = 1; // Set the ticket ID to test
        

        // Test getTicketByTicketID
        SupportTicket ticket = DAO.getTicketByTicketID(ticketID);
        if (ticket != null) {
            System.out.println("Ticket Information:");
            System.out.println("Ticket ID: " + ticket.getTicketID());
            System.out.println("User ID: " + ticket.getUserID());
            System.out.println("Message: " + ticket.getMessage());
            System.out.println("Status ID: " + ticket.getStatusID());
            System.out.println("Created At: " + ticket.getCreatedAt());
            System.out.println("Full Name: " + ticket.getFullname());
            System.out.println("Role Name: " + ticket.getUserRole());
            System.out.println("Status Title: " + ticket.getStatusTitle());
        } else {
            System.out.println("No ticket found with TicketID = " + ticketID);
        }

        // Test getResponsesByTicketID
        List<SupportResponse> responseList = DAO.getResponsesByTicketID(ticketID);
        if (!responseList.isEmpty()) {
            System.out.println("\nResponse List:");
            for (SupportResponse response : responseList) {
                System.out.println("Response ID: " + response.getResponseID());
                System.out.println("Supporter ID: " + response.getUserID());
                System.out.println("Response: " + response.getResponse());
                System.out.println("Responded At: " + response.getResponseAt());
                System.out.println("----------");
            }
        } else {
            System.out.println("No responses found for TicketID = " + ticketID);
        }
    }
}
