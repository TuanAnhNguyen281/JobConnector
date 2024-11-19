<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thêm tài khoản</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: linear-gradient(135deg, #a3d8f4, #b2e4f9);
                margin: 0;
                padding: 20px;
            }
            h1 {
                color: #333;
            }
            form {
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                margin-bottom: 20px;
            }
            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            input[type="text"],
            input[type="number"],
            input[type="date"],
            textarea,
            select {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            input[type="submit"] {
                background-color: #5cb85c;
                color: white;
                border: none;
                padding: 10px 15px;
                border-radius: 4px;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #4cae4c;
            }
            .cancel-btn {
                background-color: #d9534f;
                color: white;
                border: none;
                padding: 10px 15px;
                border-radius: 4px;
                cursor: pointer;
            }
            .cancel-btn:hover {
                background-color: #c9302c;
            }
            .error-message {
                color: red;
                margin-bottom: 10px;
            }
           
            form input[type="text"],
            form input[type="email"],
            form input[type="password"],
            form select {
                width: 100%;
                max-width: 300px; 
                padding: 10px;
                box-sizing: border-box; 
                margin-bottom: 10px;
            }
    
    </style>
</head>
<body>
    <h1>Thêm tài khoản mới</h1>

    <c:if test="${not empty error}">
        <div class="error-message">${error}</div>
    </c:if>

        <form action="AddAcountServlet" method="post">
            <label for="userName">User Name:</label>
            <input type="text" id="userName" name="userName" required><br>

            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" required><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>

            <label for="role">Role:</label>
            <select id="role" name="roleID" required>
                <option value="3">Quản trị viên</option>
                <option value="4">Hỗ trợ</option>
            </select><br>

            <button type="submit">Thêm Tài Khoản</button>
        </form>

        <script>
            // Set minimum date to today
            const today = new Date().toISOString().split('T')[0];
            document.getElementById('endDate').setAttribute('min', today);

            // Prevent form submission if end date is before today
            document.querySelector('form').addEventListener('submit', function (e) {
                const endDate = document.getElementById('endDate').value;
                if (endDate < today) {
                    e.preventDefault();
                    alert('Ngày kết thúc không thể nhỏ hơn ngày hiện tại');
                }
            });
        </script>
</body>
</html>