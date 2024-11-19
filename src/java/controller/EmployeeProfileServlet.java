/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.EmployerProfileDAO;
import dal.dataDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import model.EmployerProfile;
import model.*;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class EmployeeProfileServlet extends HttpServlet {

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
            request.setAttribute("message", "Vui lòng đăng nhập để tiếp tục");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String action = request.getParameter("action");
        EmployerProfileDAO employerDAO = new EmployerProfileDAO();
        dataDAO datadao = new dataDAO();

        // Lấy danh sách industries và sizes cho form
        List<Industry> industries = datadao.getAllIndustries();
        List<CompanySize> sizes = datadao.getAllCompanySizes();

        // Đặt attributes để jsp có thể sử dụng
        request.setAttribute("industries", industries);
        request.setAttribute("sizes", sizes);

        if ("add".equals(action)) {
            try {
                // Lấy thông tin từ form
                Part logoPart = request.getPart("logo");
                String logoFileName = logoPart.getSubmittedFileName();

                // Kiểm tra định dạng file
                String fileExtension = logoFileName.substring(logoFileName.lastIndexOf(".")).toLowerCase();
                if (!fileExtension.equals(".img") && !fileExtension.equals(".png") && !fileExtension.equals(".jpg")) {
                    session.setAttribute("message", "Định dạng file không hợp lệ! Vui lòng chọn file .img, .png hoặc .jpg");
                    response.sendRedirect("createEmployerProfile.jsp");
                    return;
                }

                String uploadPath = "E:/FA24/SWP/interation3/WebApplication2/web/userSaving/companysave/" + logoFileName;
                try (FileOutputStream fos = new FileOutputStream(uploadPath); InputStream is = logoPart.getInputStream()) {
                    byte[] data = new byte[is.available()];
                    is.read(data);
                    fos.write(data);

                    int taxNumber = Integer.parseInt(request.getParameter("taxNumber"));
                    String companyName = request.getParameter("companyName");
                    String address = request.getParameter("address");
                    int industryId = Integer.parseInt(request.getParameter("industry"));
                    int sizeId = Integer.parseInt(request.getParameter("size"));
                    String description = request.getParameter("description");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");

                    // Tạo đối tượng EmployerProfile
                    EmployerProfile profile = new EmployerProfile(userId, taxNumber, companyName, address, email, phone, industryId, sizeId, description, logoFileName, 1, "null");

                    // Lưu profile vào database
                    employerDAO.addEmployerProfile(profile);

                    request.setAttribute("message", "Tạo hồ sơ thành công!");
                    response.sendRedirect("EmployerServlet"); // Chuyển đến trang dashboard
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
                }
            } catch (Exception e) {
                request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            }
        } else if ("view".equals(action)) {
            EmployerProfile viewprofile = employerDAO.getEprofileByUserID(userId);
            request.setAttribute("Eprofile", viewprofile);
            RequestDispatcher dispatcher = request.getRequestDispatcher("companyProfile.jsp");
            dispatcher.forward(request, response);
        } else if ("viewupdate".equals(action)) {
            EmployerProfile viewprofile = employerDAO.getEprofileByUserID(userId);
            request.setAttribute("Eprofile", viewprofile);
            request.setAttribute("industries", industries);
            request.setAttribute("sizes", sizes);
            request.getRequestDispatcher("updateCompany.jsp").forward(request, response);
        } else if ("update".equals(action)) {
            try {
                // Lấy thông tin từ form
                Part logoPart = request.getPart("logo");
                String logoFileName = logoPart.getSubmittedFileName();

                // Kiểm tra định dạng file
                String fileExtension = logoFileName.substring(logoFileName.lastIndexOf(".")).toLowerCase();
                if (!fileExtension.equals(".img") && !fileExtension.equals(".png") && !fileExtension.equals(".jpg")) {
                    session.setAttribute("message", "Định dạng file không hợp lệ! Vui lòng chọn file .img, .png hoặc .jpg");
                    response.sendRedirect("createEmployerProfile.jsp");
                    return;
                }

                String uploadPath = "E:/FA24/SWP/interation3/WebApplication2/web/userSaving/companysave/" + logoFileName;
                try (FileOutputStream fos = new FileOutputStream(uploadPath); InputStream is = logoPart.getInputStream()) {
                    byte[] data = new byte[is.available()];
                    is.read(data);
                    fos.write(data);

                    int taxNumber = Integer.parseInt(request.getParameter("taxNumber"));
                    String companyName = request.getParameter("companyName");
                    String address = request.getParameter("address");
                    int industryId = Integer.parseInt(request.getParameter("industry"));
                    int sizeId = Integer.parseInt(request.getParameter("size"));
                    String description = request.getParameter("description");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");

                    // Tạo đối tượng EmployerProfile
                    EmployerProfile profile = new EmployerProfile(userId, taxNumber, companyName, address, email, phone, industryId, sizeId, description, logoFileName, 1, "null");

                    // Lưu profile vào database
                    employerDAO.updateEprofileByUserID(profile);

                    request.setAttribute("message", "Cập nhật hồ sơ thành công!");
                    response.sendRedirect("EmployerServlet"); // Chuyển đến trang dashboard
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
                }
            } catch (Exception e) {
                request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            }
        }

        request.getRequestDispatcher("createEmployerProfile.jsp").forward(request, response);
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
