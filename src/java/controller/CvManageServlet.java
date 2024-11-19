/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CvDAO;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import model.CV;

@MultipartConfig
public class CvManageServlet extends HttpServlet {

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

        CvDAO cvDao = new CvDAO();

        if (action == null) {
            session.setAttribute("message", "Hành động không hợp lệ!");
            response.sendRedirect("cvManager.jsp");
            return;
        }

        try {
            switch (action) {
                case "add":
                    Part file = request.getPart("cvPath");
                    String imagefile = file.getSubmittedFileName();

                    // Kiểm tra định dạng file
                    String fileExtension = imagefile.substring(imagefile.lastIndexOf(".")).toLowerCase();
                    if (!fileExtension.equals(".docx") && !fileExtension.equals(".doc") && !fileExtension.equals(".pdf")) {
                        session.setAttribute("message", "Định dạng file không hợp lệ! Vui lòng chọn file .pdf, .doc hoặc .docx");
                        response.sendRedirect("cvManager.jsp");
                        return;
                    }

                    String uploadPath = "E:/FA24/SWP/interation3/WebApplication2/web/userSaving/CVsave/" + imagefile;
                    try (FileOutputStream fos = new FileOutputStream(uploadPath); InputStream is = file.getInputStream()) {
                        byte[] data = new byte[is.available()];
                        is.read(data);
                        fos.write(data);

                        // Thêm CV mới vào cơ sở dữ liệu     
                        CV newCV = new CV(accountid, imagefile,  new java.util.Date());
                        cvDao.addCv(newCV);
                        session.setAttribute("message", "Thêm CV thành công!");
                        response.sendRedirect("CvManageServlet?action=list");
                    } catch (Exception e) {
                        e.printStackTrace();
                        session.setAttribute("message", "Có lỗi xảy ra trong quá trình thêm CV!");
                        response.sendRedirect("cvManager.jsp");
                    }
                    break;

                case "delete":
                    int cvidToDelete = Integer.parseInt(request.getParameter("cvid"));
                    try {
                        CV cvToDelete = cvDao.getCvById(cvidToDelete);
                        if (cvToDelete != null) {
                            String filePath = "E:/FA24/SWP/interation3/WebApplication2/web/userSaving/CVsave/" + cvToDelete.getFilePath();
                            File deletefile = new File(filePath);
                            if (deletefile.exists()) {
                                if (deletefile.delete()) {

                                    cvDao.deleteCv(cvidToDelete);
                                    session.setAttribute("message", "Xóa CV thành công!");
                                } else {
                                    session.setAttribute("message", "Không thể xóa file trên hệ thống!");
                                }
                            } else {
                                cvDao.deleteCv(cvidToDelete);
                                session.setAttribute("message", "File không tồn tại, chỉ xóa dữ liệu CV trong database.");
                            }
                        } else {
                            session.setAttribute("message", "CV không tồn tại!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        session.setAttribute("message", "Có lỗi xảy ra trong quá trình xóa CV!");
                    }
                    response.sendRedirect("CvManageServlet?action=list");
                    break;

                case "displaycv":
                    int cvid = Integer.parseInt(request.getParameter("cvid"));
                    CV cv = null;

                    try {
                        cv = cvDao.getCvById(cvid);
                        if (cv == null) {
                            session.setAttribute("message", "CV không tồn tại!");
                            response.sendRedirect("error.jsp");
                            return;
                        }

                        request.setAttribute("cv", cv);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("cvView.jsp");
                        dispatcher.forward(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                        session.setAttribute("message", "Có lỗi xảy ra trong quá trình hiển thị CV!");
                        response.sendRedirect("cvView.jsp");
                    }
                    break;
                case "list":
                try {
                    List<CV> cvList = cvDao.showAllCvByAccountId(accountid);
                    session.setAttribute("cvlist", cvList);
                    request.getRequestDispatcher("cvManager.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    session.setAttribute("message", "Có lỗi xảy ra trong quá trình tải danh sách CV!");
                    response.sendRedirect("cvManager.jsp");
                }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "Có lỗi xảy ra trong quá trình xử lý yêu cầu!");
            response.sendRedirect("cvManager.jsp");
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
