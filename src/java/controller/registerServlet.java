package controller;

import dal.*;
import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.UUID;

@WebServlet(name = "registerServlet", urlPatterns = {"/register"})
public class registerServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        // Kiểm tra xem người dùng đang đăng ký hay xác nhận mã kích hoạt
        String action = request.getParameter("action");

        if (action == null || "register".equals(action)) {
            // Xử lý đăng ký và gửi email mã kích hoạt
            String email = request.getParameter("email");
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String roleIdStr = request.getParameter("role");

            RegisterDAO registerDAO = new RegisterDAO();
            int roleId = Integer.parseInt(roleIdStr);

            if (registerDAO.checkUserExists(email)) {
                request.setAttribute("message", "Email đã tồn tại! Vui lòng sử dụng email khác.");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else if (!password.equals(confirmPassword)) {
                request.setAttribute("message", "Mật khẩu không khớp.");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                request.setAttribute("message", "Mật khẩu phải chứa ít nhất một ký tự đặc biệt.");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else if (password.length() < 8) {
                request.setAttribute("message", "Mật khẩu phải có ít nhất 8 ký tự.");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else if (!email.contains("@gmail.com")) {
                request.setAttribute("message", "Email của bạn không đúng định dạng");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else {
                // Tạo mã kích hoạt
                String activationCode = UUID.randomUUID().toString().substring(0, 6);

                // Lưu thông tin đăng ký vào session
                request.getSession().setAttribute("email", email);
                request.getSession().setAttribute("fullname", fullname);
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("password", password);
                request.getSession().setAttribute("role", roleId);
                request.getSession().setAttribute("activationCode", activationCode);

                // Gửi mã kích hoạt qua email
                String subject = "Acctivate your account";
                String content = "Chào " + fullname + ",\n\nMã kích hoạt của bạn là: " + activationCode
                        + "\nVui lòng nhập mã này để hoàn tất quá trình đăng ký.";
                Email.SendEmails(email, subject, content);

                // Chuyển đến trang nhập mã kích hoạt
                request.getRequestDispatcher("activateAccount.jsp").forward(request, response);
            }

        } else if ("activate".equals(action)) {
            // Xử lý kích hoạt tài khoản
            String enteredCode = request.getParameter("activationCode");
            String sessionCode = (String) request.getSession().getAttribute("activationCode");

            if (enteredCode.equals(sessionCode)) {
                // Lấy thông tin đăng ký từ session
                String email = (String) request.getSession().getAttribute("email");
                String fullname = (String) request.getSession().getAttribute("fullname");
                String username = (String) request.getSession().getAttribute("username");
                String password = (String) request.getSession().getAttribute("password");
                int roleId = (int) request.getSession().getAttribute("role");

                // Tạo tài khoản và lưu vào cơ sở dữ liệu
                userAccount newUser = new userAccount();
                newUser.setEmail(email);
                newUser.setFullname(fullname);
                newUser.setUsername(username);
                newUser.setPassword(password);
                newUser.setRoleid(roleId);
                newUser.setPackageID(1);


                RegisterDAO registerDAO = new RegisterDAO();
                registerDAO.registerUser(newUser);
                // Xóa session sau khi tạo tài khoản
                request.getSession().invalidate();

                // Chuyển đến trang đăng nhập hoặc xác nhận thành công
                request.setAttribute("message", "Kích hoạt tài khoản thành công !! Chào mừng bạn tham gia cùng chúng tôi. Hãy <a href='login.jsp'>đăng nhập</a> ngay.");
                request.getRequestDispatcher("activateAccount.jsp").forward(request, response);
            } else {
                // Gửi thông báo khi mã kích hoạt sai
                request.setAttribute("message", "Mã kích hoạt không hợp lệ.");
                request.getRequestDispatcher("activateAccount.jsp").forward(request, response);
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
        return "register Servlet";
    }// </editor-fold>

}
