package controller;

import dal.AdminDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Job;

public class JobListServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private AdminDAO adminDAO;
    
    public JobListServlet() {
        super();
        adminDAO = new AdminDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

         try {
            int userID = Integer.parseInt(request.getParameter("userID")); // Lấy userID từ request

            // Lấy danh sách công việc chờ xử lý và công việc đã chấp nhận
            List<Job> pendingJobs = adminDAO.getJobPendingDetails(userID);
            List<Job> acceptedJobs = adminDAO.getJobAcceptedDetails(userID);

            // Đưa danh sách công việc vào trong scope của request
           request.setAttribute("jobListPending", pendingJobs); 
           request.setAttribute("jobListApproved", acceptedJobs); 

            // Chuyển tiếp yêu cầu đến trang JSP để hiển thị dữ liệu
            request.getRequestDispatcher("companyJob.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Xử lý nếu userID không hợp lệ
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid userID");
        }
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
        return "Job list servlet for displaying pending and approved jobs.";
    }
}
