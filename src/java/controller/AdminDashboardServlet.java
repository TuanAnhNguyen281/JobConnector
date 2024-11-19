package controller;

import dal.AdminDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.EmployerProfile;

/**
 * Servlet for displaying a list of employer profiles
 */
public class AdminDashboardServlet extends HttpServlet {

    private AdminDAO adminDAO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            AdminDAO adminDAO = new AdminDAO();
            List<EmployerProfile> employerProfileList = adminDAO.getAllCompanies();
            
            //int pendingCount = adminDAO.countPendingCompanies();
            //int acceptedCount = adminDAO.countAcceptedCompanies();
            // Set the list of employer profiles and counts as request attributes
            if (employerProfileList != null && !employerProfileList.isEmpty()) {
                request.setAttribute("employerProfileList", employerProfileList);
            } else {
                request.setAttribute("message", "No employer profiles found.");
            }

            // Set the pending and accepted counts as request attributes
            //request.setAttribute("pendingCount", pendingCount);
            // request.setAttribute("acceptedCount", acceptedCount);
            // Forward to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminHome.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error occurred while fetching employer profiles.");
            request.getRequestDispatcher("adminHome.jsp").forward(request, response);
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
        return "Servlet to list all employer profiles";
    }

}
