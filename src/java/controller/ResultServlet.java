/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 *
 * @author tuananh
 */
@WebServlet(name="ResultServlet", urlPatterns={"/ResultServlet"})
public class ResultServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResultServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResultServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String jobIdStr = request.getParameter("jobId");
        response.setContentType("text/html; charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
        
            out.println("<link rel='stylesheet' href='css/result.css'>");

            if (jobIdStr == null) {
                out.println("<div class='notification-box'>Không có ID công việc để hiển thị kết quả.</div>");
                return;
            }

            int jobId = Integer.parseInt(jobIdStr);
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                DBContext dbContext = new DBContext();
                connection = dbContext.getConnection();

                if (connection != null) {
                    String selectSql = "SELECT [Check], RejectReason, RejectMs FROM Job WHERE JobID = ?";
                    preparedStatement = connection.prepareStatement(selectSql);
                    preparedStatement.setInt(1, jobId);
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        boolean isApproved = resultSet.getBoolean("Check");
                        String rejectReason = resultSet.getString("RejectReason");
                        String rejectMessage = resultSet.getString("RejectMs");

                        if (rejectReason == null) {
                            rejectReason = "Đang chờ xét duyệt";
                        }
                        if (rejectMessage == null) {
                            rejectMessage = "Đang chờ xét duyệt";
                        }

                        if (isApproved) {
                            out.println("<div class='notification-box approved'>");
                            out.println("<div class='message'>Công việc của bạn đã được chấp nhận.</div>");
                        } else {
                            out.println("<div class='notification-box rejected'>");
                            out.println("<div class='message'>Công việc của bạn đã bị từ chối.</div>");
                            out.println("<div class='reason'>Lý do: " + rejectReason + "</div>");
                            out.println("<div class='additional-message'>Ghi chú: " + rejectMessage + "</div>");
                        }
                        out.println("</div>");
                    } else {
                        out.println("<div class='notification-box'>Không tìm thấy thông tin công việc.</div>");
                    }
                } else {
                    out.println("<div class='notification-box'>Không thể kết nối tới cơ sở dữ liệu.</div>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("<div class='notification-box'>Lỗi xảy ra khi truy vấn dữ liệu: " + e.getMessage() + "</div>");
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
