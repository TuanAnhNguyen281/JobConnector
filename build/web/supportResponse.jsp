<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Trả lời câu hỏi</title>
        <style>
            table {
                width: 50%;
                margin-bottom: 20px;
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid #ddd;
                padding: 8px;
            }
            th {
                background-color: #f2f2f2;
            }
            textarea {
                width: 100%;
                height: 100px;
                resize: none;
            }
        </style>
    </head>
    <body>

        <h2>Mô tả vấn đề</h2>

        <c:if test="${not empty ticket}">
            <table>
                <tr>
                    <th>Mã phiếu</th>
                    <td>${ticket.ticketID}</td>
                </tr>
                <tr>
                    <th>Tên người dùng</th>
                    <td>${ticket.fullname}</td>
                </tr>
                <tr>
                    <th>Vai trò</th>
                    <td>${ticket.userRole}</td>
                </tr>
                <tr>
                    <th>Mô tả vấn đề</th>
                    <td>${ticket.message}</td>
                </tr>
                <tr>
                    <th>Trạng thái</th>
                    <td>${ticket.statusTitle}</td>
                </tr>
                <tr>
                    <th>Ngày tạo</th>
                    <td>${ticket.createdAt}</td>
                </tr>
            </table>

            <h3>Trả lời câu hỏi</h3>
            <form action="SupportServlet" method="post">
                <input type="hidden" name="action" value="ticketdetail">
                <input type="hidden" name="responseAction" value="response">
                <input type="hidden" name="ticketID" value="${ticket.ticketID}">
                <textarea name="message" placeholder="Trả lời ở đây"></textarea><br><br>
                <button type="submit">trả lời</button>
            </form>

        </c:if>

        <c:if test="${empty ticket}">
            <p>No ticket information found.</p>
        </c:if>

    </body>
</html>
