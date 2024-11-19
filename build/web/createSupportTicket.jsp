<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Phiếu Hỗ Trợ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
        }

        .container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
        }

        h1 {
            color: #00A8FF;
            font-size: 32px;
            margin-bottom: 20px;
        }

        p {
            color: #333;
            font-size: 18px;
            margin-bottom: 20px;
        }

        .ticket-form {
            display: flex;
            flex-direction: column;
        }

        .ticket-form input,
        .ticket-form textarea {
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            width: 100%;
        }

        .ticket-form button {
            background-color: #00A8FF;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .ticket-form button:hover {
            background-color: #45a049;
        }

        .message {
            color: green;
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .error {
            color: red;
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Chúng tôi luôn sẵn sàng hỗ trợ bạn!</h1>
        <p>Bạn đang gặp khó khăn gì? Hãy nói với tôi tại đây, chúng tôi sẽ giúp bạn!</p>

        <!-- Hiển thị thông báo thành công hoặc lỗi -->
        <c:if test="${not empty message}">
            <div class="message">
                <c:out value="${message}" />
            </div>
        </c:if>


        <form class="ticket-form" method="post" action="UserSuppotServlet">
            <input type="hidden" name="action" value="createTicket">
            <textarea name="message" placeholder="Mô tả vấn đề của bạn" rows="5" required></textarea>
            <button type="submit">Gửi Phiếu Hỗ Trợ</button>
        </form>
    </div>
</body>
</html>
