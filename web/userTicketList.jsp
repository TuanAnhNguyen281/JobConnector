<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Trang chủ</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/custom.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }
            .header {
                background-color: #fff;
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
                background-color: #fff;
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

            .post-job-btn {
                background-color: #2563EB;
                color: #fff;
                padding: 8px 16px;
                border-radius: 4px;
                text-decoration: none;
                font-weight: bold;
            }


            .job-card {
                border: 1px solid #e0e0e0;
                border-radius: 8px;
                transition: transform 0.2s, box-shadow 0.2s;
                margin-bottom: 20px;
                background-color: white;
                height: 100%;
                display: flex;
                flex-direction: column;
            }

            .job-card:hover {
                transform: translateY(-5px);
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            }

            .company-logo {
                background-color: #f8f9fa;
                height: 100px;
                display: flex;
                align-items: center;
                justify-content: center;
                border-top-left-radius: 8px;
                border-top-right-radius: 8px;
                border-bottom: 1px solid #e0e0e0;
                overflow: hidden;
            }

            .company-logo img.logo-img {
                max-width: 100%;
                max-height: 100%;
                width: auto;
                height: auto;
            }


            .card-body {
                padding: 15px;
                flex-grow: 1;
                display: flex;
                flex-direction: column;
            }

            .job-title {
                color: #2d3436;
                font-size: 16px;
                font-weight: 600;
                margin-bottom: 10px;
                line-height: 1.4;
            }

            .job-details {
                margin-bottom: 15px;
                flex-grow: 1;
            }

            .job-details p {
                margin: 5px 0;
                color: #636e72;
                font-size: 14px;
                display: flex;
                align-items: center;
            }

            .apply-btn {
                width: 100%;
                padding: 8px 16px;
                background-color: #2563EB;
                color: white;
                border: none;
                border-radius: 4px;
                font-weight: 500;
                transition: background-color 0.2s;
            }

            .apply-btn:hover {
                background-color: #1d4ed8;
            }


            .row {
                display: flex;
                flex-wrap: wrap;
            }

            .col-lg-3 {
                margin-bottom: 20px;
            }
            .search-body-container{
                display: flex;

            }


            @media (max-width: 992px) {
                .col-lg-3 {
                    flex: 0 0 50%;
                    max-width: 50%;
                }
            }

            @media (max-width: 576px) {
                .col-lg-3 {
                    flex: 0 0 100%;
                    max-width: 100%;
                }
            }
            .pagination {
                display: flex;
                justify-content: center;
                padding: 10px;
            }

            .page-link {
                padding: 8px 12px;
                margin: 0 5px;
                border: 1px solid #007bff;
                border-radius: 4px;
                text-decoration: none;
                color: #007bff;
                transition: background-color 0.3s, color 0.3s;
            }

            .page-link:hover {
                background-color: #007bff;
                color: white;
            }

            .page-number {
                padding: 8px 12px;
                margin: 0 5px;
                border: 1px solid #ddd;
                border-radius: 4px;
                background-color: #f8f9fa;
            }

            .current {
                font-weight: bold;
                background-color: #007bff;
                color: white;
            }
            .container-fluid{
                padding-right: 0px;
                padding-left: 0px;
                margin-right: auto;
                margin-left: auto;
            }
            .icon {
                width: 20px;
                height: 20px;
                vertical-align: middle;
                margin-top: 6px;
            }
        </style>
    </head>
    <body>
          <header class="header">
            <div class="container">
                <div class="header-content">
                    <div class="logo">
                        <img src="images/lOGO2.png" alt="Logo">
                    </div>
                    <nav class="nav-items">
                        <div class="nav-item">
                            <a href="#">Việc làm</a>
                            <div class="dropdown">
                                <a href="jobfilter.jsp">Tìm việc làm</a>
                            </div>
                        </div>
                        <div class="nav-item">
                            <a href="#">CV / Hồ sơ</a>
                            <div class="dropdown">
                                <a href="<c:choose>
                                       <c:when test="${profileExists}">
                                           CVonlineServlet?action=view
                                       </c:when>
                                       <c:otherwise>
                                           CVonlineServlet?action=add
                                       </c:otherwise>
                                   </c:choose>">CV online của bạn</a>
                                <a href="CvManageServlet?action=list">Quản lý CV</a>
                                <a href="">Đánh giá CV</a>
                            </div>
                        </div>
                        <div class="nav-item">
                            <a class="nav-link" href="ApplicationServlet?action=jobseekerview">Danh sách ứng tuyển</a>
                        </div>
                    </nav>
                    <div class="user-actions">
                        <div class="user-profile">
                            <img src= "images/OIP.jpg" alt="User Avatar">
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

        <div class="container-fluid">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6 p-0 d-flex justify-content-lg-start justify-content-center"> 
                            <h2>Danh sách phiếu hỗ trợ</h2>
                        </div>
                        <div class="col-sm-6 p-0 d-flex justify-content-lg-end justify-content-center">
                            <a href="createSupportTicket.jsp" class="btn btn-success" target="_blank">
                               <span>Viết phiếu hỗ trợ</span></a>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th></th>
                            <th class="text-center">Mã phiếu</th>
                            <th class="text-center">ngày tạo</th>
                            <th class="text-center">Trạng thái</th>
                            <th class="text-center">Kết quả</th>
                            <th></th>
                        </tr>
                    </thead>
                    <c:forEach items="${ticketList}" var="tl">
                        <tbody>

                        <td></td>
                        <td class="text-center">${tl.ticketID}</td>
                        <td class="text-center">${tl.createdAt}</td>
                        <td class="text-center">
                           
                                <span style="<c:choose>
                                          <c:when test='${tl.statusID == 1}'>color: orange;</c:when>
                                          <c:when test='${tl.statusID == 2}'>color: green;</c:when>
                                          <c:when test='${tl.statusID == 3}'>color: red;</c:when>
                                          <c:otherwise></c:otherwise>
                                      </c:choose>">
                                    ${tl.statusTitle}
                       
                        </td>
                        <td class="action-links text-center">
                            <a class="btn" href="SupportServlet?action=viewTicket&ticketID=${tl.ticketID}" target="_blank">xem kết quả</a>
                        </td>
                        <td></td>
                        </tbody>
                    </c:forEach>
                </table>
            </div>
        </div>
        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script>
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
