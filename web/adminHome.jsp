<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="model.EmployerProfile"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
        <title>Trang quản trị viên</title><link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/custom.css">
        <link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="css/admin.css">
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
                        <a href="AdminDashboardServlet" class="dashboard"><span>Bảng tin</span></a>
                        <a href="PendingCompanyServlet"><span>Công ty chờ kiểm duyệt</span></a>
                        <a href="AcceptedCompanyServlet"><span>Công ty đã kiểm duyệt</span></a>
                        <a href="AccountListServlet"><span>Quản lý tài khoản</span></a>
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
                            <li class="breadcrumb-item active" aria-current="page">Quản Trị Viên</li>
                        </ol>                
                    </div>
                </div>


                <div class="main-content">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-wrapper">
                                <div class="table-title">
                                    <div class="row">
                                        <div class="col-sm-6 p-0 d-flex justify-content-lg-start justify-content-center"> 
                                            <a>Bảng Tin</a>
                                        </div>

                                    </div>
                                </div>
                                <div class="main-content">
                                    <div class="breadcrumb">
                                        <span>Trang / Quản trị viên</span>
                                        <br>
                                    </div>

                                    <div class="cards">
                                      
                                        <div class="card orange">
                                            <div class="card-icon">🏢</div>
                                            <div class="card-info">
                                                <h2></h2> 
                                                <p>Công ty đang đợi kiểm duyệt</p>
                                            </div>
                                        </div>

                                      
                                        <div class="card dark">
                                            <div class="card-icon">🏢</div>
                                            <div class="card-info">
                                                <h2></h2> 
                                                <p>Công ty đã thông qua kiểm duyệt</p>
                                            </div>
                                        </div>

                                        <div class="card dark">
                                            <div class="card-icon">📜</div>
                                            <div class="card-info">
                                                <h2></h2>
                                                <p>Công việc đang đợi kiểm duyệt</p>
                                            </div>
                                        </div>
                                        <div class="card orange">
                                            <div class="card-icon">📜</div>
                                            <div class="card-info">
                                                <h2></h2>
                                                <p>Công việc đã thông qua kiểm duyệt</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-container">
                                    <h3>Danh sách toàn bộ các công ty trong hệ thống</h3>

                                    <c:if test="${not empty employerProfileList}">
                                        <table>
                                            <thead>
                                                <tr>
                                                    <th>Tên công ty</th>
                                                    <th>Mã số thuế</th>
                                                    <th>Chuyên ngành</th>
                                                    <th>Quy mô công ty</th>
                                                    <th>Trạng thái</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="company" items="${employerProfileList}">
                                                    <tr>
                                                        <td>${company.companyName}</td>
                                                        <td>${company.taxNumber}</td>
                                                        <td>${company.companyIndustry}</td>
                                                        <td>${company.companySize}</td>
                                                        <td>${company.companystatus}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>

                                    <c:if test="${empty employerProfileList}">
                                        <p>No company information found.</p>
                                    </c:if>
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
