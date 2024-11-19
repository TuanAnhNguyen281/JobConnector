<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chi tiết công việc</title>
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                font-size: 14px;
                line-height: 1.6;
            }


            .job-details {
                border: 1px solid #ccc;
                padding: 20px;
                margin-bottom: 20px;
                font-size: 16px;
            }



            .job-info {
                display: flex;
                justify-content: space-between;
            }

            .job-info-item {
                display: flex;
                align-items: center;
            }

            .icon {
                width: 40px;
                height: 40px;
            }

            .job-info-item div {
                line-height: 1.5;
            }

            strong {
                font-weight: bold;
            }

            .actions {
                margin-top: 20px;
                display: flex;
                justify-content: center;
                align-items: center;
                gap: 15px;
            }

            .apply-btn, .save-btn {
                display: flex;
                align-items: center;
                padding: 8px 16px;
                border: none;
                border-radius: 4px;
                text-decoration: none;
                font-size: 14px;
                color: #fff;
                gap: 8px;
            }

            .apply-btn {
                background-color: #004A95;
                flex: 10;
            }

            .save-btn {
                background-color: #ccc;
                color: #333;
                flex: 2;
            }

            .apply-btn img, .save-btn img {
                width: 30px;
                height: 30px;
                object-fit: contain;
            }

            .company-info {
                display: flex;
                align-items: center;
                border: 1px solid #ccc;
                padding: 20px;
                gap: 20px;
            }

            .company-info .logo img {
                max-width: 80px;
                height: auto;
                object-fit: contain;
            }


            .application-details {
                border: 1px solid #ccc;
                padding: 20px;
                margin-bottom: 20px;
                font-size: 15px;
            }


            .general-info {
                border: 1px solid #ccc;
                padding: 20px;
                font-size: 15px;
            }


            h1, h2 {
                font-size: 24px;
                color: #333;
                margin-bottom: 15px;
            }


            .info-item p {
                margin: 5px 0;
                font-size: 14px;
            }

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
                        <a href="JobseekerServlet">
                        <img src="images/lOGO2.png" alt="Logo">
                        </a>
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
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <section class="job-details">
                        <h1>${job.jobTitle}</h1>
                        <div class="job-info">
                            <div class="job-info-item">
                                <img src="images/tien.png" alt="salary" class="icon" />
                                <div>
                                    <strong>Mức lương:</strong><br />
                                    ${job.salaryRangeTitle}
                                </div>
                            </div>
                            <div class="job-info-item">
                                <img src="images/location.png" alt="location" class="icon" />
                                <div>
                                    <strong>Địa điểm:</strong><br />
                                    ${job.locationTitle}
                                </div>
                            </div>
                            <div class="job-info-item">
                                <img src="images/expp.png" alt="exp" class="icon" />
                                <div>
                                    <strong>Kinh nghiệm:</strong><br />
                                    ${job.experienceTitle}
                                </div>
                            </div>
                        </div>
                        <div>
                            <strong>Hạn nộp hồ sơ: ${job.endAt}</strong>
                        </div>

                        <div class="actions">
                            <button class="apply-btn">
                                <img src="images/fly.png" alt="Apply Icon">
                                Ứng tuyển ngay
                            </button>
                        </div>
                    </section>
                </div>

                <div class="col-md-4">
                    <section class="company-info">
                        <div class="logo">
                            <img src="userSaving/companysave/${Eprofile.logo}" alt="Company Logo" />
                        </div>
                        <div class="info">
                            <p><strong>${Eprofile.companyName}</strong></p>
                            <p>Quy mô: ${Eprofile.companySize}</p>
                            <p>Lĩnh vực: ${Eprofile.companyIndustry}</p>
                            <p>Địa chỉ: ${Eprofile.address}</p>
                        </div>
                    </section>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <section class="application-details">
                        <h2>Chi tiết tin tuyển dụng</h2>
                        <p><strong>Mô tả công việc</strong></p>
                        <p>${job.jobDescription}</p>
                        <p><strong>Yêu cầu ứng viên</strong></p>
                        <p>${job.jobRequirement}</p>
                        <p><strong>Số lượng</strong></p>
                        <p>${job.noNeed} người</p>
                        <p><strong>Quyền lợi</strong></p>
                        <p>${job.jobBenefits}</p>
                        <p><strong>Địa điểm làm việc</strong></p>
                        <p>${job.locationTitle}</p>
                        <p><strong>Cách thức ứng tuyển</strong></p>
                        <p>${job.wayTitle}</p>
                        <p><strong>Hạn nộp hồ sơ</strong></p>
                        <p> ${job.endAt}</p>
                        <strong>Ứng viên nộp hồ sơ trực tuyến bằng cách chọn CV đã lưu trữ và ấn nút "Ứng tuyển ngay"</strong> 

                        <form action="ApplicationServlet?action=apply" method="post">
                            <input type="hidden" name="jobid" value="${job.jobID}">
                            <div class="form-group">
                                <select class="form-control" id="cvid" name="cvid" required>
                                    <c:choose>
                                        <c:when test="${not empty cvList}">
                                            <c:forEach var="cv" items="${cvList}">
                                                <option value="${cv.cvID}">${cv.filePath}</option>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="">No CVs available for this user.</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                            <button type="submit" class="apply-btn">
                                <img src="images/fly.png" alt="Apply Icon" />
                                Ứng tuyển ngay
                            </button>
                        </form>

                    </section>
                </div>

                <div class="col-md-4">
                    <section class="general-info">
                        <h2>Thông tin chung</h2>
                        <p><strong>Lĩnh Vực:</strong> ${job.industryTitle}</p>
                        <p><strong>Kinh nghiệm:</strong> ${job.experienceTitle}</p>
                        <p><strong>Số lượng tuyển:</strong> ${job.noNeed} người</p>
                        <p><strong>Hình thức làm việc:</strong> ${job.jobtypeTitle}</p>
                    </section>
                </div>
            </div>
        </div>
    </body>
    <script>
        document.getElementById('apply-btn').addEventListener('click', function () {
            document.getElementById('application-details').scrollIntoView({
                behavior: 'smooth'
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
</html>
