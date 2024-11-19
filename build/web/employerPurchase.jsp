<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
        <title>Bảng nhà tuyển dụng</title><link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/custom.css">
        <link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">
        <style>

            .container {
                max-width: 1200px;
                margin: 0 auto;
            }

            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 30px;
            }

            .plans-container {
                display: flex;
                justify-content: center;
                gap: 30px;
                flex-wrap: wrap;
            }

            .plan-card {
                background: white;
                border-radius: 10px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                width: 350px;
                position: relative;
                overflow: hidden;
            }

            .featured-badge {
                position: absolute;
                top: 20px;
                right: -35px;
                background: #2196F3;
                color: white;
                padding: 5px 40px;
                transform: rotate(45deg);
                font-size: 14px;
            }

            .plan-icon {
                font-size: 48px;
                text-align: center;
                margin: 20px 0;
            }

            .plan-name {
                text-align: center;
                font-size: 24px;
                font-weight: bold;
                color: #333;
                margin-bottom: 20px;
            }

            .feature-list {
                padding: 0 30px;
                margin-bottom: 30px;
            }

            .feature-item {
                display: flex;
                align-items: center;
                margin-bottom: 15px;
                color: #666;
            }

            .checkmark {
                color: #2196F3;
                margin-right: 10px;
            }

            .price-section {
                text-align: center;
                padding: 20px;
                background: #f8f9fa;
            }

            .price {
                font-size: 28px;
                font-weight: bold;
                color: #333;
                margin-bottom: 10px;
            }

            .duration {
                color: #666;
                margin-bottom: 20px;
            }

            .buy-button {
                background: #00bcd4;
                color: white;
                border: none;
                padding: 12px 40px;
                border-radius: 5px;
                font-weight: bold;
                cursor: pointer;
                transition: background 0.3s;
                width: 100%;
            }

            .buy-button:hover {
                background: #00a5bb;
            }

            @media (max-width: 768px) {
                .plan-card {
                    width: 100%;
                    max-width: 350px;
                }
            }

        </style>
    </head>
    <body>
        <div class="wrapper">
            <div class="body-overlay"></div>
            <nav id="sidebar" class="active">
                <div class="sidebar-header">

                    <a class="text-decoration-none ms-2">
                        <c:if test="${not empty Ename}">
                            <h6><b> Công ty  ${Ename.companyName}</b></h6>
                            Trạng thái: 
                            <span style="<c:choose>
                                      <c:when test='${Ename.checkID == 1}'>color: orange;</c:when>
                                      <c:when test='${Ename.checkID == 2}'>color: green;</c:when>
                                      <c:when test='${Ename.checkID == 3}'>color: red;</c:when>
                                      <c:otherwise></c:otherwise>
                                  </c:choose>">
                                ${Ename.companystatus}
                            </span>

                        </c:if>
                    </a>

                </div>
                <ul class="list-unstyled components">
                    <li>
                        <a href="EmployerServlet" class="dashboard"><span>Quản lý công việc</span></a>
                        <a href="employeeprofileservlet?action=view" class="dashboard"><span>Thông tin công ty</span></a>
                        <a href="ApplyManagerServlet?action=view"><span>Quản lý Đơn ứng tuyển</span></a>
                        <a href="ApplicationServlet?action=employerview"><span>Danh sách CV Trực tuyến</span></a>
                        <a href="UserSuppotServlet?action=elistTicket"><span>Liên hệ khiếu nại</span></a>
                        <a href="employerPurchase.jsp"><span>Nâng cấp kho lưu trữ</span></a>
                    </li>
                </ul>
            </nav>

            <!--------page-content---------------->

            <div id="content" class="active">
                <div class="top-navbar">
                    <div class="xp-topbar">

                        <!-- Start XP Row -->
                        <div class="row"> 
                            <!-- Start XP Col -->
                            <div class="col-2 col-md-1 col-lg-1 order-2 order-md-1 align-self-center">
                                <div class="xp-menubar">
                                    <span class="material-icons text-white">signal_cellular_alt
                                    </span>
                                </div>
                            </div> 
                            <!-- End XP Col -->

                            <!-- Start XP Col -->
                            <div class="col-md-5 col-lg-3 order-3 order-md-2">
                                <div class="xp-searchbar">
                                    <form>
                                        <div class="input-group">

                                            <div class="input-group-append">

                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <!-- End XP Col -->

                            <!-- Start XP Col -->
                            <div class="col-10 col-md-6 col-lg-8 order-1 order-md-3">
                                <div class="xp-profilebar text-right">
                                    <nav class="navbar p-0">
                                        <ul class="nav navbar-nav flex-row ml-auto">   
                                            <li class="dropdown nav-item active">

                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="#">


                                                </a>
                                            </li>
                                            <li class="nav-item dropdown">
                                                <a class="nav-link" href="#" data-toggle="dropdown">
                                                    <%= session.getAttribute("fullname") %>
                                                    <img src="<%= session.getAttribute("Avatar") != null ? session.getAttribute("Avatar") : "images/OIP.jpg" %>" style="width:40px; border-radius:50%;">
                                                    <span class="xp-user-live"></span>
                                                </a>
                                                <ul class="dropdown-menu small-menu">
                                                    <li>
                                                        <a href="userProfile.jsp">
                                                            <span class="material-icons">
                                                                person_outline
                                                            </span>Profile
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="logout"><span class="material-icons">
                                                                logout</span>Đăng xuất</a>
                                                    </li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div> 
                    </div>
                    <div class="xp-breadcrumbbar text-center">
                        <h4 class="page-title">Chào Mừng bạn đến với Job Connector</h4>  
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item active" aria-current="page">Quản lý công việc</li>
                        </ol>                
                    </div>
                </div>


                <div class="main-content">
                    <div class="row">
                        <div class="container">
                            <h1>Chọn gói nâng cấp kho lưu trữ</h1>
                            <div class="plans-container">
                                <div class="plan-card">
                                    <div class="plan-icon">⭐</div>
                                    <h2 class="plan-name">VIP 1</h2>
                                    <div class="feature-list">
                                        <div class="feature-item">
                                            <span class="checkmark">✓</span>
                                            <span>Lưu trữ và quản lí 10 Công việc</span>
                                        </div>
                                    </div>
                                    <div class="price-section">
                                        <div class="price">20,000đ</div>
                                        <button class="buy-button">MUA</button>
                                    </div>
                                </div>

                                <div class="plan-card">
                                    <div class="plan-icon">❤️</div>
                                    <h2 class="plan-name">Vip 2</h2>
                                    <div class="feature-list">
                                        <div class="feature-item">
                                            <span class="checkmark">✓</span>
                                            <span>Lưu trữ không giới hạn</span>
                                        </div>
                                    </div>
                                    <div class="price-section">
                                        <div class="price">200,000đ</div>
                                        <button class="buy-button">MUA</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script>
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
        <script type="text/javascript">

            $(document).ready(function () {
                $(".xp-menubar").on('click', function () {
                    $('#sidebar').toggleClass('active');
                    $('#content').toggleClass('active');
                });

                $(".xp-menubar,.body-overlay").on('click', function () {
                    $('#sidebar,.body-overlay').toggleClass('show-nav');
                });

            });

        </script>

        <script>
            function openResultWindow(event, jobId) {
                event.preventDefault(); // Ngăn chặn việc chuyển hướng
                var resultWindow = window.open('result.jsp?jobId=' + jobId, 'Kết quả', 'width=600,height=400');
                resultWindow.focus(); // Tập trung vào cửa sổ mới
            }
        </script>



        <script>
            if ('WebSocket' in window) {
                (function () {
                    function refreshCSS() {
                        var sheets = [].slice.call(document.getElementsByTagName("link"));
                        var head = document.getElementsByTagName("head")[0];
                        for (var i = 0; i < sheets.length; ++i) {
                            var elem = sheets[i];
                            var parent = elem.parentElement || head;
                            parent.removeChild(elem);
                            var rel = elem.rel;
                            if (elem.href && typeof rel != "string" || rel.length == 0 || rel.toLowerCase() == "stylesheet") {
                                var url = elem.href.replace(/(&|\?)_cacheOverride=\d+/, '');
                                elem.href = url + (url.indexOf('?') >= 0 ? '&' : '?') + '_cacheOverride=' + (new Date().valueOf());
                            }
                            parent.appendChild(elem);
                        }
                    }
                    var protocol = window.location.protocol === 'http:' ? 'ws://' : 'wss://';
                    var address = protocol + window.location.host + window.location.pathname + '/ws';
                    var socket = new WebSocket(address);
                    socket.onmessage = function (msg) {
                        if (msg.data == 'reload')
                            window.location.reload();
                        else if (msg.data == 'refreshcss')
                            refreshCSS();
                    };
                    if (sessionStorage && !sessionStorage.getItem('IsThisFirstTime_Log_From_LiveServer')) {
                        console.log('Live reload enabled.');
                        sessionStorage.setItem('IsThisFirstTime_Log_From_LiveServer', true);
                    }
                })();
            } else {
                console.error('Upgrade your browser. This Browser is NOT supported WebSocket for Live-Reloading.');
            }
        </script>


        <script>
            if ('WebSocket' in window) {
                (function () {
                    function refreshCSS() {
                        var sheets = [].slice.call(document.getElementsByTagName("link"));
                        var head = document.getElementsByTagName("head")[0];
                        for (var i = 0; i < sheets.length; ++i) {
                            var elem = sheets[i];
                            var parent = elem.parentElement || head;
                            parent.removeChild(elem);
                            var rel = elem.rel;
                            if (elem.href && typeof rel != "string" || rel.length == 0 || rel.toLowerCase() == "stylesheet") {
                                var url = elem.href.replace(/(&|\?)_cacheOverride=\d+/, '');
                                elem.href = url + (url.indexOf('?') >= 0 ? '&' : '?') + '_cacheOverride=' + (new Date().valueOf());
                            }
                            parent.appendChild(elem);
                        }
                    }
                    var protocol = window.location.protocol === 'http:' ? 'ws://' : 'wss://';
                    var address = protocol + window.location.host + window.location.pathname + '/ws';
                    var socket = new WebSocket(address);
                    socket.onmessage = function (msg) {
                        if (msg.data == 'reload')
                            window.location.reload();
                        else if (msg.data == 'refreshcss')
                            refreshCSS();
                    };
                    if (sessionStorage && !sessionStorage.getItem('IsThisFirstTime_Log_From_LiveServer')) {
                        console.log('Live reload enabled.');
                        sessionStorage.setItem('IsThisFirstTime_Log_From_LiveServer', true);
                    }
                })();
            } else {
                console.error('Upgrade your browser. This Browser is NOT supported WebSocket for Live-Reloading.');
            }

        </script>

        <script>
            if ('WebSocket' in window) {
                (function () {
                    function refreshCSS() {
                        var sheets = [].slice.call(document.getElementsByTagName("link"));
                        var head = document.getElementsByTagName("head")[0];
                        for (var i = 0; i < sheets.length; ++i) {
                            var elem = sheets[i];
                            var parent = elem.parentElement || head;
                            parent.removeChild(elem);
                            var rel = elem.rel;
                            if (elem.href && typeof rel != "string" || rel.length == 0 || rel.toLowerCase() == "stylesheet") {
                                var url = elem.href.replace(/(&|\?)_cacheOverride=\d+/, '');
                                elem.href = url + (url.indexOf('?') >= 0 ? '&' : '?') + '_cacheOverride=' + (new Date().valueOf());
                            }
                            parent.appendChild(elem);
                        }
                    }
                    var protocol = window.location.protocol === 'http:' ? 'ws://' : 'wss://';
                    var address = protocol + window.location.host + window.location.pathname + '/ws';
                    var socket = new WebSocket(address);
                    socket.onmessage = function (msg) {
                        if (msg.data == 'reload')
                            window.location.reload();
                        else if (msg.data == 'refreshcss')
                            refreshCSS();
                    };
                    if (sessionStorage && !sessionStorage.getItem('IsThisFirstTime_Log_From_LiveServer')) {
                        console.log('Live reload enabled.');
                        sessionStorage.setItem('IsThisFirstTime_Log_From_LiveServer', true);
                    }
                })();
            } else {
                console.error('Upgrade your browser. This Browser is NOT supported WebSocket for Live-Reloading.');
            }
        </script>
    </body>
</html>
