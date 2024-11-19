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
import java.util.List;
import model.Application;
import model.JobSummary;

/**
 *
 * @author tuananh
 */
public class ApplyManagerServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Integer accountid = (Integer) session.getAttribute("UserID");
        String action = request.getParameter("action");

        if (accountid == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if ("view".equals(action)) {
            ApplicationDAO applicationDAO = new ApplicationDAO();
            List<JobSummary> jobSummaries = applicationDAO.getJobSummaryByEmployerId(accountid);


            request.setAttribute("jobSummaries", jobSummaries);
            request.getRequestDispatcher("applyManager.jsp").forward(request, response);
        } else if ("viewDetail".equals(action)) {

            String jobIdParam = request.getParameter("jobId");
            if (jobIdParam != null) {
                try {
                    int jobId = Integer.parseInt(jobIdParam);

                    ApplicationDAO applicationDAO = new ApplicationDAO();
                    List<Application> applications = applicationDAO.getApplicationsByJobId(jobId);

                    request.setAttribute("applications", applications);

                    request.getRequestDispatcher("detailJobApply.jsp").forward(request, response);

                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Invalid job ID format.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
        } else if ("applyDetail".equals(action)) {
            String applicationIdStr = request.getParameter("applicationID");
            if (applicationIdStr != null && !applicationIdStr.isEmpty()) {
                try {
                    int applicationID = Integer.parseInt(applicationIdStr);
                    ApplicationDAO applicationDAO = new ApplicationDAO();

                    Application application = applicationDAO.getApplicationByApplicationID(applicationID);

                    if (application != null) {
                        request.setAttribute("application", application);

                        request.getRequestDispatcher("updateApplyStatus.jsp").forward(request, response);
                    } else {

                        request.setAttribute("errorMessage", "Application not found.");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Invalid application ID.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
        } else if ("updateApply".equals(action)) {
            int applicationID = Integer.parseInt(request.getParameter("applicationID"));
            String comment = request.getParameter("Comment");
            int statusID = Integer.parseInt(request.getParameter("statusID"));
            ApplicationDAO applicationDAO = new ApplicationDAO();
            applicationDAO.updateApplication(applicationID, comment, statusID);

            request.setAttribute("message", "Xét duyệt thành công");
            request.getRequestDispatcher("ApplyManagerServlet?action=applyDetail&applicationID=" + applicationID ).forward(request, response);

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
