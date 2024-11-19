<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Trang chủ</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/homepage.css">
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
            
            .search-container {
                background-color: white;
                display: flex;
                align-items: center;
                border: 1px solid #ccc;
                border-radius: 11px;
                padding: 5px;
                width: 530px;
                font-family: 'Montserrat', sans-serif;
                margin: 0 auto;
                margin-bottom: 15px;
                gap: 10px;
                flex-wrap: wrap;


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
                                <a href="logout" class="dropdown-content">Đăng xuất</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <div class="container-fluid">
            <div class="bone">
                <div>
                    <h2>Việc làm cho bạn</h2>
                    <div class="search-container">
                        <form action="JobseekerServlet?action=search" method="POST">
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

                    <div class="container">
                        <div class="row">
                            <c:forEach items="${joblist}" var="job">
                                <div class="col-lg-3 col-md-6">
                                    <div class="job-card">
                                        <div class="company-logo">
                                            <img src="userSaving/companysave/${job.logo}" alt="Company logo" class="logo-img" >
                                        </div>
                                        <div class="card-body">
                                            <h5 class="job-title">${job.jobTitle}</h5>
                                            <div class="job-details">
                                                <p><i class="fas fa-building"></i>Công ty: ${job.companyName}</p>
                                                <p><i class="fas fa-map-marker-alt"></i>Địa điểm: ${job.locationTitle}</p>
                                                <p><i class="fas fa-money-bill-wave"></i>Mức lương: ${job.salaryRangeTitle}</p>
                                            </div>
                                            <a href="JobseekerServlet?action=viewJobDetails&jobID=${job.jobID}" class="btn btn-primary btn-block mt-3">Xem chi tiết</a>
                                        </div>
                                    </div>
                                </div> 
                            </c:forEach>
                        </div>
                    </div>

                    <!-- Phân trang -->
                    <div class="pagination">
                        <c:if test="${currentPage > 1}">
                            <a class="page-link" href="JobseekerServlet?page=${currentPage - 1}">Previous</a>
                        </c:if>

                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <c:choose>
                                <c:when test="${i == currentPage}">
                                    <span class="page-number current">${i}</span> <!-- Trang hiện tại -->
                                </c:when>
                                <c:otherwise>
                                    <a class="page-link" href="JobseekerServlet?page=${i}">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <a class="page-link" href="JobseekerServlet?page=${currentPage + 1}">Next</a>
                        </c:if>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div class="popular-job">
        <div class="container">
            <h2>Các ngành nghề nổi bật</h2>

            <div class="career-grid">
                <c:forEach items="${industries}" var="industry">
                    <a href="JobseekerServlet?action=filter&industry=${industry.industryID}" class="career-card-link">
                        <div class="career-card">
                            <div class="career-icon">
                                <img src="images/Icon/${industry.icon}" >
                            </div>
                            <div class="career-title">${industry.industryName}</div>
                        </div>
                    </c:forEach>
                </a>
            </div>
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
                </div>
            </div>

            <div class="footer-section">
                <div class="footer-title">JobConnector</div>
                <ul class="footer-links">
                    <li><a href="aboutus.jsp">Về chúng tôi</a></li>
                    <li><a href="bussinessRule.jsp">Điều khoản và điều kiện</a></li>
                    <li><a href="UserSuppotServlet?action=jlistTicket">Giải quyết khiếu nại</a></li>
                </ul>
            </div>

            <div class="footer-section">
                <div class="footer-title">Liên hệ với đội ngũ phát triển tại:</div>
                <div class="contact-info">
                    <span> <img src="images/logophone.png" alt="Phone"> (+84) 94 728 1124</span>
                    <span> <img src="images/logoemail.png" alt="Email"> Email: group4.jobconnector@gmail.com</span>
                </div>
            </div>

            <div class="copyright">
                Copyright © 2024 GROUP4 - SWP391
            </div>
        </footer>
    </div>
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
