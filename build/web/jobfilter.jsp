<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Job Filter Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/homepage.css">
        <style>
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
            .content {
                padding: 20px;
                margin-top: 20px;
            }
            .filter-date {
                text-align: center;
                margin-bottom: 20px;
            }
            .job-card {
                background-color: #fff;
                border: 1px solid #e0e0e0;
                border-radius: 8px;
                padding: 15px;
                margin-bottom: 20px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                transition: box-shadow 0.3s ease;
                display: flex;
                align-items: center;
            }
            .job-card img {
                height: 50px; /* Height of the company logo */
                margin-right: 15px; /* Space between logo and text */
            }
            .job-card:hover {
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }
            .job-title {
                font-size: 18px;
                font-weight: bold;
                margin: 0 0 10px 0;
            }
            .job-description {
                font-size: 14px;
                color: #666;
                margin-bottom: 10px;
            }
            .job-location {
                font-size: 12px;
                color: #999;
                margin-bottom: 5px;
            }
            .job-salary {
                font-size: 16px;
                font-weight: bold;
                color: #2563EB;
            }
            .sidebar {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                margin-right: 20px;
                margin-top: 20px;
            }
            .search-body-container{
                display: flex;

            }

            select {
                padding: 8px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .search-container {
                background-color: white;
                display: flex;
                align-items: center;
                border: 1px solid #ccc;
                border-radius: 11px;
                padding: 5px;
                width: 550px;
                font-family: 'Montserrat', sans-serif;
                margin: 0 auto;
                margin-bottom: 15px;
                gap: 10px;
                flex-wrap: wrap;


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
        <div class="container">
            <div class="sidebar">
                <div class="search-container">
                    <form action="JobseekerServlet?action=searchfilter" method="POST">
                        <div class="search-body-container">
                            <div class="search-icon">
                                <img src="images/searchlogo.png" alt="searchlogo" class="icon">
                            </div>
                            <div>
                                <input type="text" placeholder="Tìm kiếm công việc" name="title" class="search-input" value="${title}" >
                            </div>
                            <div class="location-icon">
                                <img src="images/locationlogo.png" alt="locationlogo" class="icon">
                            </div>
                            <div>
                                <select class="form-control" id="jobLocation" name="location" inline>
                                    <option value="">Chọn địa điểm</option>
                                    <c:forEach items="${location}" var="l">
                                        <option value="${l.locationID}">${l.locationName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <button type="submit" class="search-button">Tìm kiếm</button>   
                            </div>
                        </div>
                    </form>
                </div>
                <form action="JobseekerServlet?action=filter" method="post">
                    <select id="industry" name="industry" required>
                        <option value="">Chọn lĩnh vực</option>
                        <c:forEach items="${industries}" var="ind">
                            <option value="${ind.industryID}">${ind.industryName}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" class="btn btn-primary" value="Lọc">
                </form>
            </div>
        </div>
        <div class="content">
            <div class="filter-date">
                <c:set var="lastIndustry" value="" /> 
                <c:if test="${param.action == 'searchfilter'}">
                    <h2><b>Công việc</b></h2>
                </c:if>


                <c:if test="${param.action != 'searchfilter'}">
                    <c:forEach var="job" items="${joblist}">
                        <c:if test="${lastIndustry != job.industryTitle}">
                            <h2><b>Công việc ${job.industryTitle}</b></h2>
                            <c:set var="lastIndustry" value="${job.industryTitle}" />
                        </c:if>
                    </c:forEach>
                </c:if>

            </div>
            <div class="job-list">
                <c:forEach var="job" items="${joblist}">
                    <div class="job-card">
                        <img src="userSaving/companysave/${job.logo}" alt="${job.companyName} Logo"/> 
                        <div>
                            <h4 class="job-title">${job.jobTitle}</h4>
                            <p class="job-description">${job.companyName}</p>
                            <p class="job-location">Địa điểm: ${job.locationTitle}</p>
                            <p class="job-salary">Mức lương: ${job.salaryRangeTitle}</p>
                            <a href="JobseekerServlet?action=viewJobDetails&jobID=${job.jobID}" class="btn btn-primary">Ứng tuyển ngay</a>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${empty joblist}">
                    <div class="alert alert-warning">Không có công việc bạn tìm kiếm.</div>
                </c:if>
            </div>
        </div>
        <div class="navbar">
            <footer class="footer">
                <div class="footer-section">
                    <div class="footer-section"></div>
                    <div class="footer-logo-image">
                        <img src="images/lOGO2.png" alt="JobConnector">
                    </div>
                    <div class="job-connect">Kết nối công việc của bạn</div>
                    <div class="social-icons">
                        <a href="#"><img src="images/facebook.png" alt="Facebook"></a>
                        <a href="#"><img src="images/instagram.jpg" alt="Instagram"></a>
                        <a href="#"><img src="images/twitter.png" alt="Twitter"></a>
                    </div>
                </div>

                <div class="footer-section">
                    <div class="footer-title">Về JobConnector</div>
                    <ul class="footer-links">
                        <li><a href="#">Trang chủ</a></li>
                        <li><a href="#">Giới thiệu về chúng tôi</a></li>
                        <li><a href="#">Câu hỏi thường gặp</a></li>
                    </ul>
                </div>

                <div class="footer-section">
                    <div class="footer-title">Điều khoản chung</div>
                    <ul class="footer-links">
                        <li><a href="#">Điều khoản và điều kiện</a></li>
                        <li><a href="#">Chính sách bảo mật</a></li>
                        <li><a href="#">Giải quyết khiếu nại</a></li>
                    </ul>
                </div>

                <div class="footer-section">
                    <div class="footer-title">Liên hệ với đội ngũ phát triển tại:</div>
                    <div class="contact-info">
                        <span> <img src="images/logophone.png" alt="Phone"> (+84) 36 874 1192</span>
                        <span> <img src="images/logophone.png" alt="Phone"> (+84) 90 322 5211</span>
                        <span> <img src="images/logoemail.png" alt="Email"> Email: group4.jobconnector@gmail.com</span>
                    </div>
                </div>

                <div class="copyright">
                    Copyright © 2024 GROUP4 - SWP391
                </div>
            </footer>
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
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    </body>
</html>
