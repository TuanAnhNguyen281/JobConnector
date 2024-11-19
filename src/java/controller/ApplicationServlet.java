/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ApplicationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import model.Application;

/**
 *
 * @author tuananh
 */
public class ApplicationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("UserID");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String action = request.getParameter("action");

        if ("apply".equals(action)) {
            String cvidStr = request.getParameter("cvid");
            String jobidStr = request.getParameter("jobid");

            if (cvidStr != null && jobidStr != null) {
                int cvid = Integer.parseInt(cvidStr);
                int jobid = Integer.parseInt(jobidStr);
                ApplicationDAO applicationDAO = new ApplicationDAO();
                boolean alreadyApplied = applicationDAO.isJobAlreadyAppliedByUser(jobid, userId);

                if (alreadyApplied) {
                    session.setAttribute("message", "Bạn đã ứng tuyển vào công việc này rồi!");
                    response.sendRedirect("ApplicationServlet?action=jobseekerview");
                } else {
                    Application application = new Application(jobid, userId, cvid, 1, new java.util.Date(), "null");
                    applicationDAO.addApplication(application);

                    session.setAttribute("message", "Ứng tuyển thành công!");
                    response.sendRedirect("ApplicationServlet?action=jobseekerview");
                }
            } else {
                session.setAttribute("message", "Có lỗi xảy ra! Vui lòng kiểm tra lại.");
                response.sendRedirect("ApplicationServlet?action=jobseekerview");
            }
        } else if ("delete".equals(action)) {
            String applicationIdStr = request.getParameter("applicationID");
            int applicationId = Integer.parseInt(applicationIdStr);
            System.out.println("Attempting to delete application with ID: " + applicationId);
            ApplicationDAO applicationDAO = new ApplicationDAO();
            try {
                applicationDAO.deleteApplication(applicationId);
                session.setAttribute("message", "Xóa ứng tuyển thành công");
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("message", "Có lỗi xảy ra trong quá trình xóa ứng tuyển!");
            }
            response.sendRedirect("ApplicationServlet?action=jobseekerview");
        } else if ("jobseekerview".equals(action)) {
            ApplicationDAO applicationDAO = new ApplicationDAO();
            List<Application> applications = applicationDAO.getAppLyByUserId(userId);
            session.setAttribute("applicationList", applications);
            request.getRequestDispatcher("applicationlist.jsp").forward(request, response);
        } else if ("viewresult".equals(action)) {
            int application = Integer.parseInt(request.getParameter("applicationID"));
            ApplicationDAO applicationDAO = new ApplicationDAO();
            Application result = applicationDAO.getApplicationByApplicationID(application);
            request.setAttribute("result", result);
            request.getRequestDispatcher("applyResult.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
