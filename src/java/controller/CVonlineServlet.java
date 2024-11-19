/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CvDAO;
import dal.dataDAO;
import dal.jobseekerProfileDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.*;

/**
 *
 * @author tuananh
 */
public class CVonlineServlet extends HttpServlet {

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
            // Nếu accountid chưa tồn tại trong session, chuyển hướng về trang đăng nhập
            response.sendRedirect("login.jsp");
            return;
        }

        jobseekerProfileDAO jsprofileDAO = new jobseekerProfileDAO();

        if ("add".equals(action)) {
            dataDAO datadao = new dataDAO();

            List<Position> positions = datadao.getAllPositions();
            List<Gender> genders = datadao.getAllGenders();

            request.setAttribute("positions", positions);
            request.setAttribute("genders", genders);

            request.getRequestDispatcher("JobseekerProfile.jsp").forward(request, response);

        } else if ("submit".equals(action)) {
            try {
                Part photoPart = request.getPart("photo");
                String photoFileName = photoPart.getSubmittedFileName();

                // Kiểm tra định dạng file
                String fileExtension = photoFileName.substring(photoFileName.lastIndexOf(".")).toLowerCase();
                if (!fileExtension.equals(".img") && !fileExtension.equals(".png") && !fileExtension.equals(".jpg")) {
                    session.setAttribute("message", "Định dạng file không hợp lệ! Vui lòng chọn file .img, .png hoặc .jpg");
                    response.sendRedirect("createEmployerProfile.jsp");
                    return;
                }

                String uploadPath = "E:/FA24/SWP/inter2/WebApplication2/web/userSaving/avatarsave/" + photoFileName;
                try (FileOutputStream fos = new FileOutputStream(uploadPath); InputStream is = photoPart.getInputStream()) {
                    byte[] data = new byte[is.available()];
                    is.read(data);
                    fos.write(data);

                    int positionID = Integer.parseInt(request.getParameter("position"));
                    int genderID = Integer.parseInt(request.getParameter("gender"));
                    int phone = Integer.parseInt(request.getParameter("phone"));
                    String description = request.getParameter("description");
                    String professionalSummary = request.getParameter("professionalSummary");
                    String skills = request.getParameter("skills");
                    String education = request.getParameter("education");
                    String experience = request.getParameter("experience");
                    String address = request.getParameter("address");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date dob = sdf.parse(request.getParameter("dob"));

                    JobSeekerProfile profile = new JobSeekerProfile(accountid, dob, genderID, phone, positionID, description, professionalSummary, skills, experience, education, photoFileName, 2, address);
                    jsprofileDAO.addJobSeekerProfile(profile);
                    request.setAttribute("message", "Tạo hồ sơ thành công!");
                    response.sendRedirect("JobseekerServlet");
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
                }
            } catch (Exception e) {
                request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            }
        } else if ("view".equals(action)) {
            JobSeekerProfile profile = jsprofileDAO.getJobSeekerProfileByUserID(accountid);
            dataDAO datadao = new dataDAO();
            request.setAttribute("profile", profile);
            request.setAttribute("positions", datadao.getAllPositions());
            request.setAttribute("genders", datadao.getAllGenders());
            request.getRequestDispatcher("viewJobSeekerProfile.jsp").forward(request, response);
        } else if ("update".equals(action)) {
            try {
                Part photoPart = request.getPart("photo");
                String photoFileName = photoPart.getSubmittedFileName();

                // Kiểm tra định dạng file
                String fileExtension = photoFileName.substring(photoFileName.lastIndexOf(".")).toLowerCase();
                if (!fileExtension.equals(".img") && !fileExtension.equals(".png") && !fileExtension.equals(".jpg")) {
                    session.setAttribute("message", "Định dạng file không hợp lệ! Vui lòng chọn file .img, .png hoặc .jpg");
                    response.sendRedirect("createEmployerProfile.jsp");
                    return;
                }

                String uploadPath = "E:/FA24/SWP/inter2/WebApplication2/web/userSaving/avatarsave/" + photoFileName;
                try (FileOutputStream fos = new FileOutputStream(uploadPath); InputStream is = photoPart.getInputStream()) {
                    byte[] data = new byte[is.available()];
                    is.read(data);
                    fos.write(data);

                    int positionID = Integer.parseInt(request.getParameter("position"));
                    int genderID = Integer.parseInt(request.getParameter("gender"));
                    int phone = Integer.parseInt(request.getParameter("phone"));
                    String description = request.getParameter("description");
                    String professionalSummary = request.getParameter("professionalSummary");
                    String skills = request.getParameter("skills");
                    String education = request.getParameter("education");
                    String experience = request.getParameter("experience");
                    String address = request.getParameter("address");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date dob = sdf.parse(request.getParameter("dob"));

                    JobSeekerProfile updateProfile = new JobSeekerProfile(accountid, dob, genderID, phone, positionID, description, professionalSummary, skills, experience, education, photoFileName, 2, address);
                    jsprofileDAO.updateByUserID(updateProfile);
                    request.setAttribute("message", "Cập nhật hồ sơ thành công!");
                    response.sendRedirect("CVonlineServlet?action=view");
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
                }
            } catch (Exception e) {
                request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
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
