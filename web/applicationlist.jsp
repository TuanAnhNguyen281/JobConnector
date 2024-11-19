<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh sách ứng tuyển</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="StyleIndex.css">
        <style>
            .navbar-text {
                display: inline-block;
                padding-bottom: .7rem;
            }
            .dropdown-menu-right {
                right: 0;
                left: auto;
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
        </style>
    </head>
    <body>

        <header>
            <div id="signin" class="container-fluid bg-light">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <div class="logo">
                        <a class="navbar-brand" href="JobseekerServlet?action=viewAllJobs">
                            <img src="images/lOGO2.png" alt="Logo">
                        </a>
                    </div>
                    <div class="ml-auto">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                Xin chào,
                                <button id="btnFullName" class="btn btn-link navbar-text">
                                    <%= session.getAttribute("fullname") %>
                                </button>
                                <div id="logoutDropdown" class="dropdown-menu dropdown-menu-right" aria-labelledby="btnFullName">
                                    <a class="dropdown-item" href="logout">Đăng xuất</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </header>

        <section id="application-dashboard" class="py-4">
            <div class="container">
                <h2 class="text-center">Danh Sách Ứng Tuyển</h2>

                <!-- Hiển thị thông báo thành công -->
                <c:if test="${not empty message}">
                    <div id="successAlert" class="alert alert-success alert-dismissible fade show" role="alert">
                        ${message}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>

                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Công ty ứng tuyển</th>
                                    <th>Tên công việc</th>
                                    <th>CV đã nộp</th>
                                    <th>Trạng thái</th>
                                    <th>Ngày ứng tuyển</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${applicationList}" var="application">
                                    <tr>

                                        <td>${application.companyName}</td> 
                                        <td>${application.jobTitle}</td>
                                        <td><a href="userSaving/CVsave/${application.cvPath}" target="_blank">${application.cvPath}</a></td>
                                        <td> 
                                            <c:choose>   
                                                <c:when test="${application.statusID == 1}">
                                                    <span style="color: orange;">${application.statusTitle}</span>
                                                </c:when>
                                                <c:when test="${application.statusID == 2}">
                                                    <span style="color: green;">${application.statusTitle}</span>
                                                </c:when>
                                                <c:when test="${application.statusID == 3}">
                                                    <span style="color: red;">${application.statusTitle}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span>${application.statusTitle}</span> 
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${application.appliedAt}</td> 
                                        <td>
                                            <a href="ApplicationServlet?action=delete&applicationID=${application.applicationID}" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa ứng tuyển này không?');">Xóa</a>
                                             <a class="btn btn-sm btn-warning" href="ApplicationServlet?action=viewresult&applicationID=${application.applicationID}">Kết quả</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>


        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
                                                document.addEventListener("DOMContentLoaded", function () {
                                                    var btnFullName = document.getElementById("btnFullName");
                                                    var logoutDropdown = document.getElementById("logoutDropdown");

                                                    btnFullName.addEventListener("click", function () {
                                                        logoutDropdown.classList.toggle("show");
                                                    });

                                                    document.addEventListener("click", function (event) {
                                                        if (!logoutDropdown.contains(event.target) && !btnFullName.contains(event.target)) {
                                                            logoutDropdown.classList.remove("show");
                                                        }
                                                    });


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
