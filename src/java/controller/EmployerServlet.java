/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.EmployerProfileDAO;
import dal.JobDAO;
import dal.dataDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.EmployerProfile;
import model.Job;
import model.JobStatus;

/**
 *
 * @author tuananh
 */
public class EmployerServlet extends HttpServlet {

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
        Integer accountid = (Integer) session.getAttribute("UserID");
        String action = request.getParameter("action");

        if (accountid == null) {
            // Redirect to login if accountid is not in session
            response.sendRedirect("login.jsp");
            return;
        }

        EmployerProfileDAO dao = new EmployerProfileDAO();
        boolean profileExists = dao.existsEmployerProfile(accountid);
        EmployerProfile profile = dao.getEprofileByUserID(accountid);
        dataDAO data = new dataDAO();
        List<JobStatus> statu = data.getAllJobStatus();
        JobDAO jdao = new JobDAO();
        List<Job> job = new ArrayList<>();

        if ("filter".equals(action)) {
            String status = request.getParameter("status");
            if (status == null || status.isEmpty()) {

                job = jdao.getAllJobsByUserId(accountid);
            } else {

                int statusID = Integer.parseInt(status);
                job = jdao.filterJobByStatus(statusID); 

            }
        } else {

            job = jdao.getAllJobsByUserId(accountid);
        }
        request.setAttribute("status", statu);
        request.setAttribute("job", job);

        request.setAttribute("Ename", profile);
        request.setAttribute("profileExists", profileExists);

        request.getRequestDispatcher("employerHome.jsp").forward(request, response);
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
