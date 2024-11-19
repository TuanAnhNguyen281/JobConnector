/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AdminDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.EmployerProfile;


public class CompanyCheckingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AdminDAO employerProfileDAO = new AdminDAO();
    private AdminDAO adminDAO = new AdminDAO();

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String employerIDParam = request.getParameter("employerID");

        if (employerIDParam != null) {
            try {
                int employerID = Integer.parseInt(employerIDParam);
                EmployerProfile employerProfile = employerProfileDAO.getEprofileByEmployerID(employerID);

                if (employerProfile != null) {
                    request.setAttribute("employerProfile", employerProfile);
                } else {
                    request.setAttribute("message", "Không tìm thấy thông tin công ty.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("message", "ID không hợp lệ.");
            }
        } else {
            request.setAttribute("message", "Không tìm thấy ID công ty.");
        }

        request.getRequestDispatcher("companyChecking.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action"); 
        int employerID = Integer.parseInt(request.getParameter("employerID")); 
        String comment = request.getParameter("comment"); 

        // Kiểm tra action và thực hiện thay đổi trạng thái hồ sơ công ty
        if ("approve".equals(action)) {
            adminDAO.updateEmployerProfileStatus(employerID, 2, "Công ty đã thông qua kiểm duyệt"); 
        } else if ("reject".equals(action)) {
            adminDAO.updateEmployerProfileStatus(employerID, 3, comment); 
        }

        // Sau khi duyệt hoặc từ chối, lấy lại thông tin chi tiết của công ty
        EmployerProfile employerProfile = adminDAO.getEprofileByEmployerID(employerID);

        // Nếu tìm thấy thông tin công ty, lưu vào request, nếu không có thì thông báo lỗi
        if (employerProfile != null) {
            request.setAttribute("employerProfile", employerProfile);
        } else {
            request.setAttribute("message", "Không tìm thấy thông tin công ty.");
        }

        // Chuyển hướng lại trang chi tiết công ty (hoặc trang khác nếu cần)
        RequestDispatcher dispatcher = request.getRequestDispatcher("PendingCompanyServlet");
        dispatcher.forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
