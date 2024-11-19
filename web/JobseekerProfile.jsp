<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thông tin của bạn</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <style>
            :root {
                --primary-color: #2563EB;
                --secondary-color: #4CAF50;
                --text-color: #333;
                --background-color: #f0f0f0;
                --white: #fff;
                --gray-light: #e0e0e0;
                --gray: #555;
            }

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: 'Roboto', Arial, sans-serif;
                background-color: var(--background-color);
                color: var(--text-color);
                line-height: 1.6;
            }

            .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 0 20px;
            }

            .header {
                background-color: var(--white);
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                padding: 15px 0;
            }

            .header-content {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .logo {
                display: flex;
                align-items: center;
                font-weight: bold;
                font-size: 24px;
                color: var(--primary-color);
            }

            .logo img {
                height: 40px;
                margin-right: 10px;
            }

            .nav-items {
                display: flex;
                gap: 30px;
            }

            .nav-item {
                position: relative;
            }

            .nav-item > a {
                text-decoration: none;
                color: var(--text-color);
                font-weight: 500;
                transition: color 0.3s ease;
            }

            .nav-item > a:hover {
                color: var(--primary-color);
            }

            .dropdown {
                position: absolute;
                top: 100%;
                left: 0;
                background-color: var(--white);
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                border-radius: 4px;
                padding: 10px 0;
                display: none;
                z-index: 1;
                min-width: 200px;
            }

            .dropdown a {
                display: block;
                padding: 10px 20px;
                text-decoration: none;
                color: var(--text-color);
                transition: background-color 0.3s ease;
            }

            .dropdown a:hover {
                background-color: var(--background-color);
            }

            .user-actions {
                display: flex;
                align-items: center;
                gap: 15px;
            }

            .user-profile {
                display: flex;
                align-items: center;
                cursor: pointer;
            }

            .user-profile img {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                object-fit: cover;
                margin-right: 10px;
            }

            .cv-container {
                max-width: 800px;
                margin: 0 auto;
                background-color: white;
                padding: 40px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }

            .header {
                display: flex;
                gap: 30px;
                margin-bottom: 30px;
            }

            .photo-container {
                width: 150px;
                height: 150px;
                position: relative;
            }

            .photo-preview {
                width: 150px;
                height: 150px;
                border-radius: 50%;
                background-color: #ddd;
                overflow: hidden;
            }

            #preview {
                width: 100%;
                height: 100%;
                object-fit: cover;
            }

            #photo-upload {
                display: none;
            }

            .photo-label {
                cursor: pointer;
                position: absolute;
                bottom: 0;
                right: 0;
                background: #007bff;
                color: white;
                padding: 5px 10px;
                border-radius: 4px;
                font-size: 12px;
            }

            .info {
                flex: 1;
            }

            input[type="text"],
            input[type="email"],
            input[type="tel"],
            input[type="date"] {
                width: 100%;
                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 14px;
            }

            .personal-info {
                display: flex;
                gap: 10px;
            }

            .personal-info input {
                flex: 1;
            }

            .section {
                margin-bottom: 25px;
            }

            .section h2 {
                font-size: 18px;
                color: #333;
                margin-bottom: 10px;
                padding-bottom: 5px;
                border-bottom: 2px solid #333;
            }

            textarea {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                resize: vertical;
                min-height: 100px;
                max-height: 200px;
                font-size: 14px;
                line-height: 1.5;
                overflow-y: auto;
                word-wrap: break-word;
            }

            button {
                background-color: #007bff;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }

            button:hover {
                background-color: #0056b3;
            }
            /* Thêm CSS cho các trường Position và Gender */
            .personal-info {
                display: flex;
                align-items: center;
                gap: 10px;
                margin-bottom: 10px;
            }

            .personal-info select {
                flex: 1;
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 14px;
            }

            .personal-info select option {
                background-color: #f0f0f0;
            }

            .personal-info select:focus {
                outline: none;
                border-color: #007bff;
            }
        </style>
    </head>
    <body>
        <header class="header">
            <div class="container">
                <div class="header-content">
                    <a class="logo" href="JobseekerServlet" >
                        <img src="images/lOGO2.png" alt="Logo">
                    </a>
                    <nav class="nav-items">
                        <div class="nav-item">
                            <a href="#">Việc làm</a>
                            <div class="dropdown">
                                <a href="#">Tìm việc làm</a>
                                <a href="#">Việc làm Bán Hàng</a>
                                <a href="#">Việc làm IT</a>
                            </div>
                        </div>
                        <div class="nav-item">
                            <a href="#">CV / Hồ sơ</a>
                            <div class="dropdown">
                                <a href="TemplateServlet">Tạo CV</a>
                                <a href="CvManageServlet?action=list">Quản lý CV</a>
                            </div>
                        </div>
                        <div class="nav-item">
                            <a href="#">Cẩm nang nghề nghiệp</a>
                            <div class="dropdown">
                                <a href="#">Khóa học online</a>
                                <a href="#">Tin tức nghề nghiệp</a>
                            </div>
                        </div>
                    </nav>
                    <div class="user-actions">
                        <div class="user-profile">
                            <img src="<%= session.getAttribute("Avatar") != null ? session.getAttribute("Avatar") : "images/OIP.jpg" %>" alt="User Avatar">
                            <span><%= session.getAttribute("fullname") %></span>
                            <div class="dropdown">
                                <a href="userProfile.jsp" class="dropdown-content">Profile</a>
                                <a href="logout" class="dropdown-content">Đăng xuất</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <div class="cv-container">
            <h4 class="text-center">Tạo CV online cho riêng bạn</h4>
            <form action="CVonlineServlet?action=submit" method="POST" enctype="multipart/form-data">
                <div class="header">
                    <div class="photo-container">
                        <div class="photo-preview">
                            <img id="preview" src="/api/placeholder/150/150" alt="Preview">
                        </div>
                        <input type="file" id="photo-upload" name="photo" accept="image/*">
                        <label for="photo-upload" class="photo-label">Chọn ảnh</label>
                    </div>
                    <div class="info">
                        <input type="text" name="name" placeholder="${account.fullname}" disabled="">
                        <div class="personal-info">
                            <select name="position" required>
                                <option value="">Chọn vị trí mong muốn</option>
                                <c:forEach items="${positions}" var="position">
                                    <option value="${position.positionID}">${position.positionTitle}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="personal-info">
                            <input type="date" name="dob" required>
                            <select name="gender" required>
                                <option value="">Giới tính của bạn là</option>
                                <c:forEach items="${genders}" var="gender">
                                    <option value="${gender.genderID}">${gender.genderTitle}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="personal-info">
                            <input type="tel" name="phone" placeholder="Số điện thoại" required>
                            <input type="email" name="email" placeholder="${account.email}" disabled="">
                        </div>
                        <div class="personal-info">
                            <input type="text" name="address" placeholder="Địa chỉ" required>
                        </div>
                    </div>
                </div>

                <div class="section">
                    <h2>Giới thiệu</h2>
                    <textarea name="description" placeholder="Giới thiệu và mô tả bản thân..."></textarea>
                </div>

                <div class="section">
                    <h2>Tóm tắt chuyên môn</h2>
                    <textarea name="professionalSummary" placeholder="Tóm tắt chuyên môn cá nhân..."></textarea>
                </div>

                <div class="section">
                    <h2>Kỹ năng</h2>
                    <textarea name="skills" placeholder="Các kỹ năng..."></textarea>
                </div>

                <div class="section">
                    <h2>Học vấn</h2>
                    <textarea name="education" placeholder="Thông tin học vấn..."></textarea>
                </div>

                <div class="section">
                    <h2>Kinh nghiệm làm việc</h2>
                    <textarea name="experience" placeholder="Kinh nghiệm làm việc..."></textarea>
                </div>

                <button type="submit">Lưu thông tin</button>
            </form>
        </div>

        <script>
            // Xử lý preview ảnh
            document.getElementById('photo-upload').addEventListener('change', function (e) {
                const file = e.target.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        document.getElementById('preview').src = e.target.result;
                    }
                    reader.readAsDataURL(file);
                }
            });

            // Xử lý auto-resize cho textarea
            document.querySelectorAll('textarea').forEach(textarea => {
                textarea.addEventListener('input', function () {
                    this.style.height = 'auto';
                    this.style.height = Math.min(this.scrollHeight, 200) + 'px';
                });
            });

            document.querySelectorAll('.nav-item').forEach(item => {
                item.addEventListener('mouseenter', () => {
                    item.querySelector('.dropdown').style.display = 'block';
                });
                item.addEventListener('mouseleave', () => {
                    item.querySelector('.dropdown').style.display = 'none';
                });
            });
        </script>
    </body>
</html>