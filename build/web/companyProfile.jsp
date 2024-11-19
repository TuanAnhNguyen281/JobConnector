<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin công ty</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/profile.css">
    </head>
    <body>
        <!-- Header -->
        <header class="header">
            <div class="header-logo">
                <a href="EmployerServlet"><img src="images/lOGO2.png" alt="Logo"></a>
            </div>
            <div class="header-signout">
                <a href="logout" class="btn btn-light">Đăng xuất</a>
            </div>
        </header>

        <div class="flex-container">
            <!-- Main Content -->
            <main class="flex-grow-1"> 
                <div class="profile-card">
                    <div class="profile-card-header">

                        <c:if test="${not empty Eprofile}">
                            <img src="userSaving/companysave/${Eprofile.logo}" alt="Company Logo" class="profile-avatar">          
                        </div>
                        <div class="profile-card-body">
                            <div class="info-container">
                                <div class="info-text">
                                    <!-- Company Information -->
                                    <div class="row">

                                        <div class="col-md-12 info-item">
                                            <i class="fas fa-building"></i> Công ty: ${Eprofile.companyName}
                                        </div>
                                        <div class="col-md-12 info-item">
                                            <i class="fas fa-users"></i> Quy mô: ${Eprofile.companySize}
                                        </div>
                                        <div class="col-md-12 info-item">
                                            <i class="fas fa-industry"></i> Ngành nghề: ${Eprofile.companyIndustry}
                                        </div>
                                        <div class="col-md-12 info-item">
                                            <i class="fas fa-map-marker-alt"></i> Địa chỉ: ${Eprofile.address}
                                        </div>
                                        <div class="col-md-12 info-item">
                                            Trạng thái: 
                                            <span style="<c:choose>
                                                      <c:when test='${Eprofile.checkID == 1}'>color: orange;</c:when>
                                                      <c:when test='${Eprofile.checkID == 2}'>color: green;</c:when>
                                                      <c:when test='${Eprofile.checkID == 3}'>color: red;</c:when>
                                                      <c:otherwise></c:otherwise>
                                                  </c:choose>">
                                                ${Eprofile.companystatus}
                                            </span>
                                        </div>

                                    </c:if>
                                    <c:if test="${empty Eprofile}">
                                        <div class="col-md-12 info-item">
                                            <i class="fas fa-info-circle"></i> Bạn chưa có hồ sơ. Vui lòng tạo hồ sơ mới để được tạo thông tin tuyển dụng
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <!-- Buttons -->
                        <div class="button-container mt-3">
                            <c:choose>
                                <c:when test="${not empty Eprofile}">
                                    <a href="employeeprofileservlet?action=viewupdate" class="btn btn-secondary">
                                        <i class="fas fa-edit"></i> Cập nhật hồ sơ công ty
                                    </a>
                                </c:when>                              
                                <c:otherwise>
                                    <a class="btn btn-secondary disabled">
                                        <i class="fas fa-edit"></i> Cập nhật hồ sơ công ty
                                    </a>
                                    <a href="createCompanyProfile.jsp" class="btn btn-success">
                                        <i class="fas fa-plus"></i> Bổ sung thông tin công ty
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
        </div>
  

    <!-- Footer -->
    <footer class="footer">
        <div class="footer-section">
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
                <span><img src="images/logophone.png" alt="Phone"> (+84) 36 874 1192</span>
                <span><img src="images/logophone.png" alt="Phone"> (+84) 90 322 5211</span>
                <span><img src="images/logoemail.png" alt="Email"> Email: JobConnector@gmail.com</span>
            </div>
        </div>
        <div class="copyright">
            Copyright © 2024 GROUP4 - SWP391
        </div>
    </footer>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
</body>
</html>
