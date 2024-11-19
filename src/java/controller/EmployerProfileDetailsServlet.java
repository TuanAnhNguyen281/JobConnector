/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EmployerProfile;

/**
 *
 * @author My PC
 */
public class EmployerProfileDetailsServlet extends HttpServlet {

     private static final long serialVersionUID = 1L;
    private AdminDAO employerProfileDAO = new AdminDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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

        request.getRequestDispatcher("companyViewDetail.jsp").forward(request, response);
   
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
