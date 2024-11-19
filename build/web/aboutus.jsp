<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <title>Giới thiệu về Job Link</title>
    </head>
    <style>
        /* General reset and styling */
        body, html {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
        }

        /* Logo and Header Section */
        .header {
            text-align: center;
            padding: 40px 15px;
            background-color: #3c8dbc;
            color: white;
        }

        .header h1 {
            font-size: 2.5em;
            margin-bottom: 10px;
        }

        .header p {
            font-size: 1.2em;
            margin-top: 10px;
        }

        /* Footer Styling */
        .footer {
            background-color: #045c76;
            color: #EFD6D6;
            padding: 30px;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
        }

        .footer-section {
            margin-bottom: 20px;
        }

        .footer-logo-image img {
            max-width: 150px;
            height: auto;
        }

        .job-connect {
            font-size: 1.2em;
            font-weight: bold;
            margin-top: 15px;
            text-align: center;
        }

        .social-icons {
            display: flex;
            justify-content: center;
            margin-top: 10px;
            gap: 20px;
        }

        .social-icons img {
            width: 35px;
            transition: transform 0.3s;
        }

        .social-icons img:hover {
            transform: scale(1.1);
        }

        .footer-title {
            font-weight: bold;
            margin-bottom: 10px;
        }

        .footer-links {
            list-style: none;
            padding: 0;
        }

        .footer-links li {
            margin-bottom: 8px;
        }

        .footer-links a {
            color: #EFD6D6;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .footer-links a:hover {
            color: #ffffff;
        }

        .contact-info {
            color: #EFD6D6;
        }

        .contact-info img {
            width: 20px;
            vertical-align: middle;
            margin-right: 5px;
        }

        .copyright {
            width: 100%;
            text-align: center;
            margin-top: 20px;
            font-size: 0.9em;
            color: #EFD6D6;
        }

        /* Introduction Section */
        .intro-section {
            max-width: 800px;
            margin: 40px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .intro-section h2 {
            color: #3c8dbc;
            font-size: 2em;
            margin-bottom: 15px;
        }

        .intro-section p {
            font-size: 1.1em;
            line-height: 1.6;
            color: #333;
        }

        .container {
            display: flex;
            width: 100%;
        }

        .left-section {
            flex: 1;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .left-section img {
            width: 100%;
            height: auto;
            object-fit: cover;
        }

        .right-section {
            flex: 1;
            padding: 20px;
        }

        .right-section .intro-section h3,
        .right-section .intro-section h3 + p, /* Targets paragraphs directly under h3 */
        .right-section .intro-section h3 + p + ul + li /* Targets subsequent paragraphs under the same section */
        {
            text-align: left;
            margin-left: 0;
            padding-left: 0;
        }

    </style>
    <body>

        <!-- Introduction Section -->
        <div class="header">
            <h1>Giới thiệu về Job Connector</h1>
            <p>Job Connector là nền tảng tuyển dụng trung gian, tạo ra cầu nối mạnh mẽ giữa ứng viên tiềm năng và các nhà tuyển dụng hàng đầu. Với mục tiêu thúc đẩy sự phát triển của thị trường lao động, chúng tôi mang đến giải pháp kết nối hiệu quả, minh bạch và dễ dàng cho cả người tìm việc và nhà tuyển dụng.</p>
        </div>
        <div class="container">
            <div class="left-section">
                <img src="images/d9c99c3f3b6e8030d97f.jpg" alt="Job Connector Introduction Image" />
            </div>

            <div class="right-section">


                <div class="intro-section">
                    <h2>Chào mừng đến với Job Connector</h2>
                    <ul>
                    <li>Được xây dựng từ niềm đam mê kết nối và hỗ trợ tìm kiếm việc làm, Job Connector giúp ứng viên và nhà tuyển dụng tìm thấy nhau một cách dễ dàng và nhanh chóng.</li>
                    <li>Với mục tiêu trở thành nền tảng tuyển dụng hàng đầu, chúng tôi không chỉ cung cấp thông tin việc làm mà còn là nơi ứng viên có thể khám phá những cơ hội phát triển cá nhân và nghề nghiệp trong tương lai.</li>
                    </ul>
                    
                    <h3>1.Đối với các ứng viên tìm việc</h3>
                    <ul>
                    <li>Ứng viên có thể dễ dàng tìm kiếm những công việc phù hợp với trình độ, kinh nghiệm và mục tiêu sự nghiệp của mình.</li> 
                    <li>Hệ thống của chúng tôi luôn cập nhật liên tục các thông tin tuyển dụng từ những doanh nghiệp uy tín, tạo điều kiện thuận lợi cho người tìm việc có thể tiếp cận và nắm bắt cơ hội một cách nhanh chóng. Bên cạnh đó, Job Connector cung cấp các công cụ hỗ trợ, giúp ứng viên có thể gửi lên thắc mắc của mình với hệ thống để góp ý về chất lượng và cải thiện hệ thống một cách tốt nhất</li>
                    </ul>
                    
                    <h3>2.Đối với các nhà tuyển dụng</h3>
                    <ul>
                    <li>Đối với nhà tuyển dụng, Job Connector là một giải pháp toàn diện để tìm kiếm, lựa chọn và tuyển dụng những ứng viên chất lượng.</li> 
                    <li>Chúng tôi hiểu rằng việc xây dựng đội ngũ nhân sự xuất sắc là yếu tố then chốt để doanh nghiệp phát triển bền vững, do đó, nền tảng của chúng tôi được thiết kế để hỗ trợ tối đa cho các quy trình tuyển dụng, giúp nhà tuyển dụng tiết kiệm thời gian và chi phí, đồng thời tiếp cận những ứng viên phù hợp nhất.</li>
                    </ul>
                    
                    <h3>3.Cam kết cho người dùng</h3>
                    <ul>
                    <li>Chúng tôi cam kết cung cấp trải nghiệm người dùng tốt nhất, liên tục cải tiến và nhận những phản hồi từ người dùng để đem lại hiệu quả cao trong từng bước của quy trình tuyển dụng.</li> 
                    <li>Tại Job Connector, chúng tôi không chỉ kết nối mà còn đồng hành cùng người dùng, từ việc giúp ứng viên hỗ trợ giải đáp các thắc mắc khi làm quen với hệ thống, đến việc giúp nhà tuyển dụng kiểm duyệt thông tin công ty một cách chính xác và hợp lệ để không vướng vào cái quy định pháp lý của pháp luật trong quy trình tạo công ty và việc làm. Hãy tham gia cùng chúng tôi và trải nghiệm một cách thức kết nối tuyển dụng hoàn toàn mới, hướng tới thành công và phát triển bền vững.</li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Footer Section -->
        <footer class="footer">
            <div class="footer-section">
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
                    <li><a href="AboutUS.jsp">Về chúng tôi</a></li>
                    <li><a href="BussinessRule.jsp">Điều khoản và điều kiện</a></li>
                    <li><a href="UserSuppotServlet?action=jlistTicket">Giải quyết khiếu nại</a></li>
                </ul>
            </div>

            <div class="footer-section">
                <div class="footer-title">Liên hệ với đội ngũ phát triển tại:</div>
                <div class="contact-info">
                    <span><img src="images/logophone.png" alt="Phone"> (+84) 94 728 1124</span><br>
                    <span><img src="images/logoemail.png" alt="Email"> group4.jobconnector@gmail.com</span>
                </div>
            </div>

            <div class="copyright">
                © 2024 GROUP4 - SWP391. All rights reserved.
            </div>
        </footer>

    </body>
</html>
