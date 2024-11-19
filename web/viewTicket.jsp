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

            <h3>Danh sách câu trả lời</h3>
            <c:if test="${not empty responseList}">
                <table>
                    <thead>
                        <tr>
                            <th>Nội dung trả lời</th>
                            <th>Ngày trả lời</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="response" items="${responseList}">
                            <tr>
                                <td>${response.response}</td>
                                <td>${response.responseAt}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${empty responseList}">
                <p>Chưa có câu trả lời nào cho phiếu này.</p>
            </c:if>

        </c:if>

        <c:if test="${empty ticket}">
            <p>Không tìm thấy thông tin phiếu hỗ trợ.</p>
        </c:if>

    </body>
</html>
