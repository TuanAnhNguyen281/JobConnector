/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.JobDAO;
import dal.dataDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author tuananh
 */
public class JobManageServlet extends HttpServlet {

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
            response.sendRedirect("login.jsp");
            return;
        }

        if ("add".equals(action)) {
            dataDAO datadao = new dataDAO();

            List<Industry> industries = datadao.getAllIndustries();
            List<ExperimentRequirement> experiments = datadao.getAllExperimentRequirement();
            List<SalaryRange> salary = datadao.getAllSalaryRanges();
            List<JobType> type = datadao.getAllJobType();
            List<ApplyWay> way = datadao.getAllApplyWay();
            List<Location> location = datadao.getAllLocations();

            request.setAttribute("industries", industries);
            request.setAttribute("experiments", experiments);
            request.setAttribute("salary", salary);
            request.setAttribute("type", type);
            request.setAttribute("way", way);
            request.setAttribute("location", location);

            request.getRequestDispatcher("add-job.jsp").forward(request, response);

        } else if ("submit".equals(action)) {
            try {

                String jobTitle = request.getParameter("jobTitle");
                String jobDescription = request.getParameter("jobDescription");
                String jobRequirement = request.getParameter("jobRequirement");
                String jobBenefits = request.getParameter("jobBenefit");
                int noNeed = Integer.parseInt(request.getParameter("count"));
                int experienceID = Integer.parseInt(request.getParameter("experience"));
                int salaryRangeID = Integer.parseInt(request.getParameter("salaryRange"));
                int locationID = Integer.parseInt(request.getParameter("location"));
                int industryID = Integer.parseInt(request.getParameter("industry"));
                int jobTypeID = Integer.parseInt(request.getParameter("jobType"));
                int wayID = Integer.parseInt(request.getParameter("applyWay"));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date endAt = sdf.parse(request.getParameter("endDate"));
                Date createdAt = new Date();

                // Create Job object
                Job job = new Job(accountid, jobTitle, jobDescription, jobRequirement, jobBenefits, noNeed, experienceID, salaryRangeID, locationID, industryID, jobTypeID, createdAt, endAt, wayID, 1, "null");

                // Save job
                JobDAO jobDAO = new JobDAO();
                jobDAO.addJob(job);

                request.setAttribute("successMessage", "Công việc đã được thêm thành công!");
                request.getRequestDispatcher("add-job.jsp").forward(request, response);

            } catch (ParseException e) {
                // If there's an error, get the data again and return to form
                dataDAO datadao = new dataDAO();
                request.setAttribute("industries", datadao.getAllIndustries());
                request.setAttribute("experiments", datadao.getAllExperimentRequirement());
                request.setAttribute("salary", datadao.getAllSalaryRanges());
                request.setAttribute("type", datadao.getAllJobType());
                request.setAttribute("way", datadao.getAllApplyWay());

                request.setAttribute("error", "Invalid date format");
                request.getRequestDispatcher("add-job.jsp").forward(request, response);
            }
        } else if ("edit".equals(action)) {
            int jobid = Integer.parseInt(request.getParameter("jobid"));
            JobDAO jobDAO = new JobDAO();
            Job job = jobDAO.getJobByJobId(jobid);

            dataDAO datadao = new dataDAO();

            List<Industry> industries = datadao.getAllIndustries();
            List<ExperimentRequirement> experiments = datadao.getAllExperimentRequirement();
            List<SalaryRange> salary = datadao.getAllSalaryRanges();
            List<JobType> type = datadao.getAllJobType();
            List<ApplyWay> way = datadao.getAllApplyWay();
            List<Location> location = datadao.getAllLocations();

            request.setAttribute("job", job);
            request.setAttribute("industries", industries);
            request.setAttribute("experiments", experiments);
            request.setAttribute("salary", salary);
            request.setAttribute("type", type);
            request.setAttribute("way", way);
            request.setAttribute("location", location);

            request.getRequestDispatcher("edit-job.jsp").forward(request, response);
        } else if ("update".equals(action)) {
            try {
                int jobid = Integer.parseInt(request.getParameter("jobid"));
                String jobTitle = request.getParameter("jobTitle");
                String jobDescription = request.getParameter("jobDescription");
                String jobRequirement = request.getParameter("jobRequirement");
                String jobBenefits = request.getParameter("jobBenefit");
                int noNeed = Integer.parseInt(request.getParameter("count"));
                int experienceID = Integer.parseInt(request.getParameter("experience"));
                int salaryRangeID = Integer.parseInt(request.getParameter("salaryRange"));
                int locationID = Integer.parseInt(request.getParameter("location"));
                int industryID = Integer.parseInt(request.getParameter("industry"));
                int jobTypeID = Integer.parseInt(request.getParameter("jobType"));
                int wayID = Integer.parseInt(request.getParameter("applyWay"));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date endAt = sdf.parse(request.getParameter("endDate"));
                Date createdAt = new Date();

                // update Job object
                Job job = new Job(jobid, accountid, jobTitle, jobDescription, jobRequirement, jobBenefits, noNeed, experienceID, salaryRangeID, locationID, industryID, jobTypeID, createdAt, endAt, wayID, 1, "null");

                // Save job
                JobDAO jobDAO = new JobDAO();
                jobDAO.updateJob(job);

                response.sendRedirect("EmployerServlet");
            } catch (ParseException e) {
                // If there's an error, get the data again and return to form
                dataDAO datadao = new dataDAO();
                request.setAttribute("industries", datadao.getAllIndustries());
                request.setAttribute("experiments", datadao.getAllExperimentRequirement());
                request.setAttribute("salary", datadao.getAllSalaryRanges());
                request.setAttribute("type", datadao.getAllJobType());
                request.setAttribute("way", datadao.getAllApplyWay());

                request.setAttribute("error", "Invalid date format");
                request.getRequestDispatcher("edit-job.jsp").forward(request, response);
            }
        } else if ("viewdelete".equals(action)) {
            int jobid = Integer.parseInt(request.getParameter("jobid"));
            JobDAO jobDAO = new JobDAO();
            Job job = jobDAO.getJobByJobId(jobid);

            request.setAttribute("job", job);
            request.getRequestDispatcher("delete-job.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int jobid = Integer.parseInt(request.getParameter("jobid"));
            JobDAO jobDAO = new JobDAO();
            jobDAO.deleteJob(jobid);

            request.getRequestDispatcher("EmployerServlet").forward(request, response);
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
