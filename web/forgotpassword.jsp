<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quên Mật Khẩu</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: linear-gradient(135deg, #a3d8f4, #b2e4f9);
                margin: 0;
                padding: 20px;
            }

            .container {
                max-width: 800px;
                margin: 50px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: left;
            }

            h2 {
                color: #333;
                margin-bottom: 15px;
            }

            .message {
                color: green;
                margin-bottom: 10px;
                font-size: 14px;
                display: inline;
            }

            label {
                display: block;
                margin: 10px;
                font-weight: bold;
            }

            input[type="email"] {
                width: 50%;
                padding: 10px;
                margin-bottom: 20px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            button {
                background-color: #675fdb;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                justify-content: center;
            }

            button:hover {
                background-color: #b2c1d1;
            }

            p.error {
                color: red;
                margin-top: 20px;
                font-size: 14px;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <h2>Quên Mật Khẩu</h2>
            <form action="forgotpassword" method="post">
                <input type="hidden" name="action" value="sendCode" />
                <label for="email">Nhập địa chỉ email của bạn:</label>
                <input type="email" id="email" name="email" required />
                <button type="submit">Gửi Mã Xác Nhận</button>
            </form>
            <c:if test="${not empty message}">
                <p class="message">${message}</p>
            </c:if>
        </div>
    </body>
</html>