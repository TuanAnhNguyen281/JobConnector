<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hồ sơ & CV</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .navbar-text {
                display: inline-block;
                padding-bottom: .7rem;
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
                position: relative;
                display: inline-block;
            }


            .user-profile img {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                object-fit: cover;
                margin-right: 10px;
            }

            .user-profile {
                position: relative;
                display: inline-block;
            }
            .dropdown {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
                z-index: 1;
            }
            .user-profile:hover .dropdown {
                display: block;
            }
            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }
            .dropdown-content a:hover {
                background-color: #f1f1f1;
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
                                <a href="#">Việc làm gấp</a>
                                <a href="#">Việc làm IT</a>
                            </div>
                        </div>
                        <div class="nav-item">
                            <a href="#">CV / Hồ sơ</a>
                            <div class="dropdown">
                                <a href="TemplateServlet">Tạo CV</a>
                                <a href="CvManageServlet?action=list">Quản lý CV</a>
                                <a href="">Đánh giá CV</a>
                            </div>
                        </div>
                        <div class="nav-item">
                            <a href="ApplicationServlet?action=jobseekerview">Danh sách ứng tuyển</a>
                            <div class="">
                             
                            </div>
                        </div>
                    </nav>
                    <div class="user-actions">
                        <div class="user-profile">
                             <img src="<%= session.getAttribute("Avatar") != null ? session.getAttribute("Avatar") : "images/OIP.jpg" %>" alt="User Avatar">
                            <span><%= session.getAttribute("fullname") %></span>
                             <div class="dropdown">
                                <a href="userProfile.jsp" class="dropdown-content" target="_blank">Profile</a>
                                <a href="logout" class="dropdown-content">Đăng xuất</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <section id="profile" class="py-4">
            <div class="container">
                <h2 class="text-center">Hồ sơ cá nhân</h2>
                <div class="row">
                    <div class="col-md-6 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Thông tin cá nhân</h5>
                                <p class="card-text">Họ và tên: ${account.fullname}</p>
                                <p class="card-text">Email: ${account.email}</p>
                                <form action="CvManageServlet?action=add" method="post" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label for="cvPath">Tải lên CV của bạn( Tải lên các file có định dạng .pdf, .doc, .docx )</label>
                                        <input type="file" class="form-control" id="cvPath" name="cvPath" required>
                                    </div>
                                    <input type="hidden" name="accountid" value="${account.userid}">
                                    <button type="submit" class="btn btn-primary">Thêm CV</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Các CV đã tải lên</h5>

                                <!-- Hiển thị thông báo thành công hoặc lỗi khi xóa CV -->
                                <c:if test="${not empty message}">
                                    <div id="successAlert" class="alert alert-success alert-dismissible fade show" role="alert">
                                        ${message}
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                </c:if>

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>CV Của bạn</th>
                                            <th>Ngày tải lên</th>
                                            <th>Xóa CV</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${cvlist}" var="cv" varStatus="status">
                                            <tr>
                                                <td>${status.index + 1}</td> 
                                                <td><a href="userSaving/CVsave/${cv.filePath}" target="_blank">${cv.filePath}</a></td>
                                                <td>${cv.dateCreated}</td>
                                                <td>
                                                    <a href="CvManageServlet?action=delete&cvid=${cv.cvID}" 
                                                       class="btn btn-sm btn-danger" 
                                                       onclick="return confirm('Bạn có chắc chắn muốn xóa CV này không?');">
                                                        Xóa
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
                                                           document.querySelectorAll('.nav-item').forEach(item => {
                                                               item.addEventListener('mouseenter', () => {
                                                                   item.querySelector('.dropdown').style.display = 'block';
                                                               });
                                                               item.addEventListener('mouseleave', () => {
                                                                   item.querySelector('.dropdown').style.display = 'none';
                                                               });
                                                           });
                                                           // Đóng dropdown nếu người dùng nhấp bên ngoài
                                                           window.onclick = function (event) {
                                                               if (!event.target.matches('.dropbtn')) {
                                                                   dropdownContent.style.display = 'none';
                                                               }
                                                           };
                                                           document.addEventListener("DOMContentLoaded", function () {
                                                               const successAlert = document.getElementById("successAlert");
                                                               if (successAlert) {
                                                                   const autoDismiss = setTimeout(function () {
                                                                       successAlert.classList.remove('show');
                                                                       successAlert.classList.add('fade');
                                                                   }, 5000);

                                                                   const closeButton = successAlert.querySelector('.close');
                                                                   closeButton.addEventListener('click', function () {
                                                                       clearTimeout(autoDismiss);
                                                                       successAlert.classList.remove('show');
                                                                       successAlert.classList.add('fade');
                                                                   });
                                                               }
                                                           });
        </script>
    </body>
</html>
