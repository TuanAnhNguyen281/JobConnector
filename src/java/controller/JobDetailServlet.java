package controller;

import dal.AdminDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Job;

public class JobDetailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Phương thức này xử lý request và gửi dữ liệu tới JSP
    protected void loadJobDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Lấy jobID từ tham số URL
            int jobID = Integer.parseInt(request.getParameter("jobID"));
            
            AdminDAO adminDAO = new AdminDAO();
            // Gọi phương thức getJobByJobId để lấy thông tin công việc
            Job job = adminDAO.getJobByJobId(jobID);

            if (job != null) {
                // Đặt các giá trị vào request để chuyển tiếp đến JSP
                request.setAttribute("jobTitle", job.getJobTitle());
                request.setAttribute("jobDescription", job.getJobDescription());
                request.setAttribute("jobRequirement", job.getJobRequirement());
                request.setAttribute("jobBenefit", job.getJobBenefits());
                request.setAttribute("count", job.getNoNeed());
                request.setAttribute("experience", job.getExperienceTitle());
                request.setAttribute("salaryRange", job.getSalaryRangeTitle());
                request.setAttribute("industry", job.getIndustryTitle());
                request.setAttribute("jobType", job.getJobtypeTitle());
                request.setAttribute("location", job.getLocationTitle());
                request.setAttribute("endDate", job.getEndAt().toString());
                request.setAttribute("applyWay", job.getWayTitle());
                request.setAttribute("status", job.getStatusTitle());

                // Chuyển tiếp dữ liệu đến JSP
                RequestDispatcher dispatcher = request.getRequestDispatcher("jobViewDetails.jsp");
                dispatcher.forward(request, response);
            } else {
                // Nếu không tìm thấy công việc, chuyển hướng tới trang lỗi
                response.sendRedirect("errorPage.jsp");
            }
        } catch (NumberFormatException e) {
            // Nếu jobID không hợp lệ (không phải là số), chuyển hướng đến trang lỗi
            response.sendRedirect("errorPage.jsp");
        }
    }

    // Phương thức xử lý HTTP GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadJobDetails(request, response);
    }

    // Phương thức xử lý HTTP POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadJobDetails(request, response);
    }

    // Phương thức trả về thông tin về Servlet
    @Override
    public String getServletInfo() {
        return "JobDetailServlet for displaying job details";
    }
}
