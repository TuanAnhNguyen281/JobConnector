package controller;

import dal.*;
import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.UUID;

public class forgotPassServlet extends HttpServlet {

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
        String action = request.getParameter("action");
        ForgotDAO dao = new ForgotDAO();
        // Chia hoạt động gửi mã và đổi mật khẩu
        if ("sendCode".equals(action)) {
            String email = request.getParameter("email");
            
            userAccount user = dao.getByEmail(email);
            if (user != null) {
                // Tạo mã xác nhận
                String confirmationCode = UUID.randomUUID().toString().substring(0, 6);
                // Lưu thông tin người dùng vào session
                HttpSession session = request.getSession();
                session.setAttribute("confirmationCode", confirmationCode);
                session.setAttribute("userId", user.getUserid());
                //Gửi mã xác nhận đến người dùng
                String subject = "Reset password";
                String content = "Chào " + user.getFullname() + ",\n\nMã thay đổi mật khẩu của bạn là: " + confirmationCode
                        + "\nVui lòng nhập mã này để thay đổi mật khẩu của bạn.";
                Email.SendEmails(email, subject, content);

                // Gửi thông báo đã gửi mail
                request.setAttribute("message", "Mã xác nhận đã được gửi đến email của bạn.");
                request.getRequestDispatcher("enternewPassword.jsp").forward(request, response);
            } else {
                // Nếu mail không tồn tại
                request.setAttribute("message", "Email của bạn chưa được đăng ký hoặc nhập sai. Nếu chưa có Hãy <a href='Register.jsp'>đăng ký</a> ngay.");
                request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
            }
        } else if ("resetPassword".equals(action)) {
            String inputCode = request.getParameter("activationCode");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            HttpSession session = request.getSession();
            String storedCode = (String) session.getAttribute("confirmationCode");
            int userId = (int) session.getAttribute("userId");

            if (storedCode != null && storedCode.equals(inputCode)) {
                // Kiểm tra các điều kiện mật khẩu
                if (newPassword.equals(confirmPassword)) {
                    // Cập nhật mật khẩu mới vào database
                    if (dao.updatePassword(userId, newPassword)) {
                        request.setAttribute("message", "Mật khẩu đã được thay đổi thành công! Hãy <a href='login.jsp'>đăng nhập</a> ngay");
                        request.getRequestDispatcher("enternewPassword.jsp").forward(request, response);
                    } else {
                        request.setAttribute("message", "Lỗi khi cập nhật mật khẩu!");
                        request.getRequestDispatcher("enternewPassword.jsp").forward(request, response);
                    }
                } else if (!newPassword.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                    request.setAttribute("message", "Mật khẩu phải chứa ít nhất một ký tự đặc biệt.");
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                } else if (newPassword.length() < 8) {
                    request.setAttribute("message", "Mật khẩu phải có ít nhất 8 ký tự.");
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Mật khẩu xác nhận không trùng khớp!");
                    request.getRequestDispatcher("enternewPassword.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "Mã xác nhận không hợp lệ!");
                request.getRequestDispatcher("enternewPassword.jsp").forward(request, response);
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
