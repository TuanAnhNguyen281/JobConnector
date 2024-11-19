/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SupportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.SupportResponse;
import model.SupportTicket;

/**
 *
 * @author tuananh
 */
public class SupportServlet extends HttpServlet {

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
            // Redirect to login if accountid is not in session
            response.sendRedirect("login.jsp");
            return;
        }
        SupportDAO DAO = new SupportDAO();
        if ("ticketlist".equals(action)) {

            List<SupportTicket> ticketList = DAO.getAllSupportTickets();

            request.setAttribute("ticketList", ticketList);
            request.getRequestDispatcher("supporterHome.jsp").forward(request, response);

        } else if ("ticketdetail".equals(action)) {
            int ticketID = Integer.parseInt(request.getParameter("ticketID"));
            SupportTicket ticket = DAO.getTicketByTicketID(ticketID);
            request.setAttribute("ticket", ticket);

            String responseAction = request.getParameter("responseAction");
            if ("response".equals(responseAction)) {
                String responsed = request.getParameter("message");
                SupportResponse responseTicket = new SupportResponse(accountid, ticketID, responsed, new java.util.Date());
                DAO.createResponse(responseTicket);

                request.setAttribute("message", "Trả lời hoàn tất");
                request.getRequestDispatcher("SupportServlet?action=ticketlist").forward(request, response);
                return;
            }
            request.getRequestDispatcher("supportResponse.jsp").forward(request, response);
        } else if ("close".equals(action)) {
            int ticketID = Integer.parseInt(request.getParameter("ticketID"));
            int statusID = Integer.parseInt(request.getParameter("statusID"));

            DAO.updateTicketStatus(ticketID, statusID);
            request.getRequestDispatcher("SupportServlet?action=ticketlist").forward(request, response);
        } else if ("viewInProgressTicket".equals(action)) {
            List<SupportTicket> ticketList = DAO.getAllInProgressTickets();

            request.setAttribute("ticketList", ticketList);
            request.getRequestDispatcher("inprogressTicket.jsp").forward(request, response);

        } else if ("viewCloseTicket".equals(action)) {
            List<SupportTicket> ticketList = DAO.getAllClosedTickets();

            request.setAttribute("ticketList", ticketList);
            request.getRequestDispatcher("closeTicket.jsp").forward(request, response);
        } else if ("viewTicket".equals(action)) {
            int ticketID = Integer.parseInt(request.getParameter("ticketID"));
            List<SupportResponse> responseList = DAO.getResponsesByTicketID(ticketID);
            SupportTicket ticket = DAO.getTicketByTicketID(ticketID);
            request.setAttribute("ticket", ticket);
            request.setAttribute("responseList", responseList);
            request.getRequestDispatcher("viewTicket.jsp").forward(request, response);
            System.out.println("ticketID: " + ticketID);
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
