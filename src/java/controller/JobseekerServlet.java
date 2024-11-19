/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CV;
import model.EmployerProfile;
import model.Industry;
import model.Job;
import model.Location;

/**
 *
 * @author ntanh
 */
public class JobseekerServlet extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Integer accountid = (Integer) session.getAttribute("UserID");
        String action = request.getParameter("action");

        if (accountid == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        jobseekerProfileDAO profileDAO = new jobseekerProfileDAO();
        boolean profileExists = profileDAO.isProfileExists(accountid);

        request.setAttribute("profileExists", profileExists);

        if ("filter".equals(action)) {
            int industry = Integer.parseInt(request.getParameter("industry"));
            FilterJobDAO DAO = new FilterJobDAO();

            dataDAO datadao = new dataDAO();
            List<Industry> industries = datadao.getAllIndustries();
            request.setAttribute("industries", industries);

            List<Job> jobfilter = DAO.getJobsByIndustry(industry);
            request.setAttribute("joblist", jobfilter); // sử dụng "joblist" thay vì "jobfilter"
            request.getRequestDispatcher("jobfilter.jsp").forward(request, response);

        } else if ("search".equals(action)) {
            String title = request.getParameter("title");
            String location = request.getParameter("location");
            int locationID = -1; // Default value for locationID

            // Check if location is not empty and can be parsed
            if (location != null && !location.isEmpty()) {
                try {
                    locationID = Integer.parseInt(location);
                } catch (NumberFormatException e) {
                    // Handle the case where location is not a valid number, if needed
                    locationID = -1; // Or set a default/fallback value
                }
            }
            dataDAO datadao = new dataDAO();
            List<Location> locations = datadao.getAllLocations();
            List<Industry> industries = datadao.getAllIndustries();

            FilterJobDAO DAO = new FilterJobDAO();
            JobDAO jDAO = new JobDAO();
            request.setAttribute("industries", industries);
            request.setAttribute("location", locations);
            request.setAttribute("title", title);

            int pageSize = 8;
            int pageNumber = 1;
            String pageParam = request.getParameter("page");
            if (pageParam != null) {
                pageNumber = Integer.parseInt(pageParam);
            }

            List<Job> jobSearchResults;
            if (title != null && !title.isEmpty() && location != null && !location.isEmpty()) {
                jobSearchResults = DAO.searchAndFilter(title, locationID);
            } else if (title != null && !title.isEmpty()) {
                jobSearchResults = DAO.searchByTitle(title);
            } else if (location != null && !location.isEmpty()) {
                jobSearchResults = DAO.filterByLocation(locationID);
            } else {
                jobSearchResults = jDAO.getAllJobsAfterCheck();
            }

            int totalJobs = jobSearchResults.size();
            int totalPages = (int) Math.ceil((double) totalJobs / pageSize);
            int startIndex = (pageNumber - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, totalJobs);

            // Get the sublist for the current page
            List<Job> jobsForCurrentPage = jobSearchResults.subList(startIndex, endIndex);

            // Set attributes for the JSP
            request.setAttribute("joblist", jobsForCurrentPage);
            request.setAttribute("currentPage", pageNumber);
            request.setAttribute("totalPages", totalPages);

            // Forward to the jobseekerhome.jsp page
            request.getRequestDispatcher("jobseekerhome.jsp").forward(request, response);

        } else if ("searchfilter".equals(action)) {
            String title = request.getParameter("title");
            String location = request.getParameter("location");
            int locationID = -1; 

         
            if (location != null && !location.isEmpty()) {
                try {
                    locationID = Integer.parseInt(location);
                } catch (NumberFormatException e) {
                    locationID = -1; 
                }
            }
            dataDAO datadao = new dataDAO();
            List<Location> locations = datadao.getAllLocations();
            List<Industry> industries = datadao.getAllIndustries();

            FilterJobDAO DAO = new FilterJobDAO();
            JobDAO jDAO = new JobDAO();
            request.setAttribute("industries", industries);
            request.setAttribute("location", locations);
            request.setAttribute("title", title);


            List<Job> jobSearchResults;
            if (title != null && !title.isEmpty() && location != null && !location.isEmpty()) {
                jobSearchResults = DAO.searchAndFilter(title, locationID);
            } else if (title != null && !title.isEmpty()) {
                jobSearchResults = DAO.searchByTitle(title);
            } else if (location != null && !location.isEmpty()) {
                jobSearchResults = DAO.filterByLocation(locationID);
            } else {
                jobSearchResults = jDAO.getAllJobsAfterCheck();
            }

            request.setAttribute("joblist", jobSearchResults);
            request.getRequestDispatcher("jobfilter.jsp").forward(request, response);
        } else if ("viewJobDetails".equals(action)) {

            String jobidstr = request.getParameter("jobID");
            int jobid = Integer.parseInt(jobidstr);

            JobDAO DAO = new JobDAO();
            Job job = DAO.getJobByJobId(jobid);
            request.setAttribute("job", job);
            EmployerProfileDAO pDAO = new EmployerProfileDAO();
            EmployerProfile ep = pDAO.getEprofileByJobID(jobid);
            request.setAttribute("Eprofile", ep);
            CvDAO cvDAO = new CvDAO();
            try {
                List<CV> cvList = cvDAO.showAllCvByAccountId(accountid);
                request.setAttribute("cvList", cvList);
                request.getRequestDispatcher("jobDetail.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(JobseekerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("jobDetail.jsp").forward(request, response);
        } else {
            int pageSize = 8;
            int pageNumber = 1;

            try {
                // Lấy số trang từ request, nếu không có thì giữ nguyên 1
                String pageParam = request.getParameter("page");
                if (pageParam != null) {
                    pageNumber = Integer.parseInt(pageParam);
                }

                JobDAO DAO = new JobDAO();
                List<Job> approvedJobs = new ArrayList<>(); // Giả sử phương thức này trả về danh sách tất cả công việc
                approvedJobs = DAO.getAllJobsAfterCheck();

                // Tính toán chỉ số bắt đầu và kết thúc
                int totalJobs = approvedJobs.size();
                int totalPages = (int) Math.ceil((double) totalJobs / pageSize);
                int startIndex = (pageNumber - 1) * pageSize;
                int endIndex = Math.min(startIndex + pageSize, totalJobs);

                // Lấy danh sách công việc cho trang hiện tại
                List<Job> jobsForCurrentPage = approvedJobs.subList(startIndex, endIndex);
                dataDAO datadao = new dataDAO();
                List<Location> location = datadao.getAllLocations();
                List<Industry> industries = datadao.getAllIndustries();

                request.setAttribute("location", location);
                request.setAttribute("industries", industries);

                // Đưa danh sách công việc và các thông tin cần thiết vào request
                request.setAttribute("joblist", jobsForCurrentPage);
                request.setAttribute("currentPage", pageNumber);
                request.setAttribute("totalPages", totalPages);

                // Chuyển tiếp đến trang JSP để hiển thị
                request.getRequestDispatcher("jobseekerhome.jsp").forward(request, response);

            } catch (NumberFormatException e) {

                request.setAttribute("errorMessage", "Trang không hợp lệ.");

                request.getRequestDispatcher("jobseekerhome.jsp").forward(request, response);

            } catch (IndexOutOfBoundsException e) {
                // Xử lý ngoại lệ nếu chỉ số truy cập ra ngoài giới hạn
                request.setAttribute("errorMessage", "Không có công việc nào để hiển thị.");
                request.getRequestDispatcher("jobseekerhome.jsp").forward(request, response);

            } catch (Exception e) {

                e.printStackTrace();
                request.setAttribute("errorMessage", "Đã xảy ra lỗi không xác định.");
                request.getRequestDispatcher("jobseekerhome.jsp").forward(request, response);
            }
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
