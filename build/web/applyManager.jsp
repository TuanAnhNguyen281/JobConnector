<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
        <title>Trang Quản Lý CV</title><link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/custom.css">
        <link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">
        <style>

            select {

                padding: 8px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .container-fluid {
                display: flex;
                justify-content: center;

                height: 100vh;
                margin: 0;
            }


            table {
                width: 80%;
                border-collapse: collapse;
                margin-top: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            th, td {
                text-align: center;
                padding: 12px;
                font-size: 16px;
                border: 2px solid #007bb5;
                border-radius: 8px;
                background-color: #f2f2f2;
            }

            th {
                background-color: #0099cc;
                color: white;
            }

            td {
                color: #333;
            }


            tr:hover {
                background-color: #e0f7fa;
                color: #000;
            }


            tbody tr:hover td {
                border-top: 2px solid #007bb5;
                border-bottom: 2px solid #007bb5;
            }


            tbody tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            tbody tr:nth-child(odd) {
                background-color: #ffffff;
            }

            button {
                padding: 10px 15px;
                background-color: #007bb5;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            button:hover {
                background-color: #005f87;
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
                                                    <%= session.getAttribute("fullname")%>
                                                    <img src="<%= session.getAttribute("Avatar") != null ? session.getAttribute("Avatar") : "images/OIP.jpg"%>" style="width:40px; border-radius:50%;">
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
                <c:if test="${not empty message}">
                    <div id="successAlert" class="alert alert-success alert-dismissible fade show" role="alert">
                        ${message}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>




                <div class="container-fluid">
                    <table>
                        <thead>
                            <tr>
                                <th>Việc làm</th>
                                <th>Hạn</th>
                                <th>Tổng số người ứng tuyển</th>
                                <th>Đồng ý xét tuyển</th>
                                <th>Chưa xét tuyển</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>             
                            <c:forEach var="job" items="${jobSummaries}">
                                <tr>
                                    <td>${job.jobTitle}</td>
                                    <td>${job.endAt}</td>
                                    <td>${job.totalApplications}</td>
                                    <td>${job.status2Count}</td>
                                    <td>${job.status1Count}</td>
                                    <td>
                                        <a href="ApplyManagerServlet?action=viewDetail&jobId=${job.jobID}">
                                            <button type="button">Xem thông tin</button>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

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






