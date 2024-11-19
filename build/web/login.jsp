<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng nhập</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background: linear-gradient(135deg, #a8bbfa, #c09bee);
                overflow-y: hidden;
            }
            .container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100%;
            }
            .login-container {
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: white;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 80%;
            }
            .login-form {
                flex: 1;
                padding-left: 40px;
                margin: 90px 0;
                margin-right: 50px;
            }
            .info-container {
                flex: 1;
                padding: 9rem;
                border-radius: 5px;
                background: linear-gradient(135deg, #6e8efb, #a777e3);
                color: white;
                text-align: center;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .info-container h2 {
                font-size: 1.5rem;
                margin-bottom: 1rem;
            }
            .info-container a {
                color: white;
                text-decoration: none;
            }
            .error {
                color: #dc3545;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="login-container">
                <div class="login-form">
                    <h2 class="text-center">Đăng nhập</h2>
                    <form action="login" method="post">
                        <div class="form-group">
                            <label for="username">Tên Đăng Nhập:</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Mật khẩu:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Đăng nhập</button>
                        <c:if test="${not empty message}">
                            <div class="error" role="alert">
                                ${message}
                            </div>
                        </c:if>
                    </form>
                    <div><a href="forgotpassword.jsp">Quên mật khẩu ?</a></div>
                </div>
                <div class="info-container">
                    <h2>Tìm công việc của bạn ngay hôm nay</h2>
                    <p>Đăng ký ngay để bắt đầu tìm kiếm cơ hội nghề nghiệp.</p>
                    <a href="Register.jsp" class="btn btn-light btn-block" style="color: #333">Đăng ký</a>
                </div>
            </div>
        </div>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>

</html>

