<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng ký</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                background: linear-gradient(135deg, #6e8efb, #a777e3);
                padding: 20px;
            }
            .container {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 100%;
            }
            .register-container {
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 500px;
                padding: 30px;
            }
            .register-form {
                width: 100%;
            }
            .form-group {
                margin-bottom: 1.5rem;
            }
            .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
            }
            .btn-primary:hover {
                background-color: #0056b3;
                border-color: #0056b3;
            }
            .error {
                color: #dc3545;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="register-container">
                <div class="register-form">
                    <h2 class="text-center">Đăng ký</h2>
                    <form action="register" method="post">
                        <div class="form-group">
                            <label for="fullName">Họ và tên:</label>
                            <input type="text" class="form-control" id="fullName" name="fullname" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                            <span id="emailError" style="color:red;"></span>
                        </div>
                        <div class="form-group">
                            <label for="username">Tên đăng nhập:</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Mật khẩu:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                            <span>Mật khẩu cần: Lớn hơn 8 chữ cái, chứa số và ký tự đặc biệt</span>
                            <span id="passwordError" style="color:red;"></span>
                        </div>
                        <div class="form-group">
                            <label for="password">Nhập lại Mật khẩu:</label>
                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                            <span id="confirmPasswordError" style="color:red;"></span>
                        </div>
                        <div class="form-group">
                            <label for="role">Bạn là:</label><br>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" id="employer" value="1" required>
                                <label class="form-check-label" for="employer">Nhà tuyển dụng</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" id="jobseeker" value="2" required>
                                <label class="form-check-label" for="jobseeker">Người tìm việc</label> 
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Đăng ký</button>
                        <c:if test="${not empty message}">
                            <div class="error" role="alert">
                                ${message}
                            </div>
                        </c:if>
                    </form>
                    <p class="mt-3 text-center">Nếu đã có tài khoản, vui lòng <a href="login.jsp">đăng nhập</a>.</p>
                </div>
            </div>
        </div>

        <script>
            // Định nghĩa các hàm kiểm tra riêng lẻ
            function validateEmail(email) {
                return email.endsWith('@gmail.com');
            }

            function validatePassword(password) {
                return password.length >= 8 && /[!@#$%^&*(),.?":{}|<>]/.test(password);
            }

            function validateConfirmPassword(password, confirmPassword) {
                return password === confirmPassword;
            }

            // Hàm hiển thị lỗi
            function showError(inputElement, errorMessage) {
                const errorElement = document.getElementById(inputElement.id + 'Error');
                if (errorElement) {
                    errorElement.textContent = errorMessage;
                }
            }

            // Hàm xóa thông báo lỗi
            function clearError(inputElement) {
                const errorElement = document.getElementById(inputElement.id + 'Error');
                if (errorElement) {
                    errorElement.textContent = '';
                }
            }

            // Hàm kiểm tra từng trường
            function validateField(inputElement) {
                const value = inputElement.value.trim();
                switch (inputElement.id) {
                    case 'email':
                        if (!validateEmail(value)) {
                            showError(inputElement, "Email của bạn không đúng định dạng (@gmail.com)");
                        } else {
                            clearError(inputElement);
                        }
                        break;
                    case 'password':
                        if (!validatePassword(value)) {
                            showError(inputElement, "Mật khẩu phải có ít nhất 8 ký tự và chứa ít nhất một ký tự đặc biệt.");
                        } else {
                            clearError(inputElement);
                        }
                        break;
                    case 'confirmPassword':
                        const password = document.getElementById('password').value;
                        if (!validateConfirmPassword(password, value)) {
                            showError(inputElement, "Mật khẩu không khớp.");
                        } else {
                            clearError(inputElement);
                        }
                        break;
                }
            }

            // Hàm kiểm tra toàn bộ form
            function validateForm() {
                let isValid = true;
                const fields = ['email', 'password', 'confirmPassword'];

                fields.forEach(fieldId => {
                    const field = document.getElementById(fieldId);
                    validateField(field);
                    if (document.getElementById(fieldId + 'Error').textContent !== '') {
                        isValid = false;
                    }
                });

                return isValid;
            }

            // Thêm sự kiện cho form khi trang được tải
            window.onload = function () {
                const form = document.querySelector('form');
                const fields = ['email', 'password', 'confirmPassword'];

                fields.forEach(fieldId => {
                    const field = document.getElementById(fieldId);
                    field.addEventListener('blur', function () {
                        validateField(this);
                    });
                });

                form.addEventListener('submit', function (event) {
                    if (!validateForm()) {
                        event.preventDefault();
                    }
                });
            };
        </script>

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
