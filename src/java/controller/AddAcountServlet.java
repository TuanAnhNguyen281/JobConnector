/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AdminDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author My PC
 */
public class AddAcountServlet extends HttpServlet {

    private AdminDAO accountDAO = new AdminDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Kiểm tra nếu roleID là null hoặc không hợp lệ
        String roleIDString = request.getParameter("roleID");
        if (roleIDString == null || roleIDString.isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng chọn vai trò.");
            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            return;
        }

        // Kiểm tra các trường thông tin không được để trống
        if (userName == null || userName.isEmpty() || fullName == null || fullName.isEmpty()
                || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("errorMessage", "Tất cả các trường đều phải được điền.");
            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            return;
        }

        try {
            int roleID = Integer.parseInt(roleIDString);
            // Kiểm tra giá trị roleID hợp lệ
            if (roleID != 3 && roleID != 4) {
                request.setAttribute("errorMessage", "Vai trò không hợp lệ.");
                request.getRequestDispatcher("addAccount.jsp").forward(request, response);
                return;
            }

            // Tạo đối tượng Role
            String roleName = (roleID == 3) ? "Quản trị viên" : "Hỗ trợ";
            Account.Role role = new Account.Role(roleID, roleName);

            // Tạo đối tượng Account và gọi hàm thêm tài khoản
            Account account = new Account(0, userName, fullName, email, password, role);
            boolean isAdded = accountDAO.addAccount(account);

            if (isAdded) {
                response.sendRedirect("AccountListServlet"); // Chuyển hướng sau khi thêm thành công
            } else {
                request.setAttribute("errorMessage", "Thêm tài khoản thất bại.");
                request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            // Xử lý khi roleID không phải là một số hợp lệ
            request.setAttribute("errorMessage", "Vai trò không hợp lệ.");
            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
