<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thêm công việc</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: linear-gradient(135deg, #a3d8f4, #b2e4f9);
                margin: 0;
                padding: 20px;
            }
            h1 {
                color: #333;
            }
            form {
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                margin-bottom: 20px;
            }
            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            input[type="text"],
            input[type="number"],
            input[type="date"],
            textarea,
            select {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            input[type="submit"] {
                background-color: #5cb85c;
                color: white;
                border: none;
                padding: 10px 15px;
                border-radius: 4px;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #4cae4c;
            }
            .cancel-btn {
                background-color: #d9534f;
                color: white;
                border: none;
                padding: 10px 15px;
                border-radius: 4px;
                cursor: pointer;
            }
            .cancel-btn:hover {
                background-color: #c9302c;
            }
           
            .success-message {
                background-color: #28a745;
                color: white; 
                padding: 15px;
                border-radius: 8px;
                font-size: 16px;
                font-weight: bold;
                text-align: center;
                margin: 20px 0;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); 
                animation: fadeIn 1s ease-out;
            }

            
            .success-message span#countdown {
                font-size: 20px;
                color: #ffc107;
                font-weight: bold;
            }

            
            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: translateY(-20px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }

            
            .success-message:hover {
                background-color: #218838; 
                cursor: pointer;
            }

            
            @keyframes pulse {
                0% {
                    transform: scale(1);
                }
                50% {
                    transform: scale(1.1);
                }
                100% {
                    transform: scale(1);
                }
            }

            
            .success-message span#countdown {
                animation: pulse 1s infinite;
            }

            .error-message {
                color: red;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <h1>Thêm công việc mới</h1>

        <c:if test="${not empty successMessage}">
            <div id="successMessage" class="success-message">
                ${successMessage} Bạn sẽ được chuyển đến trang chủ trong <span id="countdown">5</span> giây.
            </div>
        </c:if>


        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <form action="JobManageServlet" method="post">
            <input type="hidden" name="action" value="submit">

            <label for="jobTitle">Tên công việc:</label>
            <input type="text" id="jobTitle" name="jobTitle" required>

            <label for="jobDescription">Mô tả công việc:</label>
            <textarea id="jobDescription" name="jobDescription" rows="4" required></textarea>

            <label for="jobRequirement">Yêu cầu ứng viên:</label>
            <textarea id="jobRequirement" name="jobRequirement" rows="4" required></textarea>

            <label for="jobBenefit">Quyền lợi:</label>
            <textarea id="jobBenefit" name="jobBenefit" rows="4" required></textarea>

            <label for="count">Số lượng cần tuyển (người):</label>
            <input type="number" id="count" name="count" min="1" required>

            <label for="experience">Kinh nghiệm yêu cầu:</label>
            <select id="experience" name="experience" required>
                <option value="">Chọn kinh nghiệm</option>
                <c:forEach items="${experiments}" var="exp">
                    <option value="${exp.experienceID}">${exp.experienceDescription}</option>
                </c:forEach>
            </select>

            <label for="salaryRange">Mức lương:</label>
            <select id="salaryRange" name="salaryRange" required>
                <option value="">Chọn mức lương</option>
                <c:forEach items="${salary}" var="sal">
                    <option value="${sal.salaryRangeID}">${sal.salaryDescription}</option>
                </c:forEach>
            </select>

            <label for="industry">Lĩnh vực:</label>
            <select id="industry" name="industry" required>
                <option value="">Chọn lĩnh vực</option>
                <c:forEach items="${industries}" var="ind">
                    <option value="${ind.industryID}">${ind.industryName}</option>
                </c:forEach>
            </select>

            <label for="jobType">Kiểu công việc:</label>
            <select id="jobType" name="jobType" required>
                <option value="">Chọn kiểu công việc</option>
                <c:forEach items="${type}" var="t">
                    <option value="${t.jobtypeID}">${t.jobTypeDescription}</option>
                </c:forEach>
            </select>

            <label for="location">Địa điểm:</label>
            <select id="location" name="location" required>
                <option value="">Chọn địa điểm</option>
                <c:forEach items="${location}" var="l">
                    <option value="${l.locationID}">${l.locationName}</option>
                </c:forEach>
            </select>

            <label for="endDate">Hạn công việc:</label>
            <input type="date" id="endDate" name="endDate" required>


            <label for="applyWay">Phương thức nộp hồ sơ:</label>
            <select id="applyWay" name="applyWay" required>
                <option value="">Chọn phương thức nộp</option>
                <c:forEach items="${way}" var="w">
                    <option value="${w.wayID}">${w.wayTitle}</option>
                </c:forEach>
            </select>

            <div style="display: flex; gap: 10px;">
                <input type="submit" value="Thêm công việc" >
                <button type="button" class="cancel-btn" onclick="window.location.href = 'EmployerServlet'">Hủy</button>
            </div>
        </form>

        <script>
            // Scroll to the success message if it exists
            window.onload = function () {
                const successMessage = document.getElementById('successMessage');
                if (successMessage) {
                    successMessage.scrollIntoView({behavior: 'smooth'});

                    // Countdown and redirect
                    let countdown = 5;
                    const countdownElement = document.getElementById('countdown');
                    const interval = setInterval(() => {
                        countdown--;
                        countdownElement.textContent = countdown;

                        if (countdown <= 0) {
                            clearInterval(interval);
                            window.location.href = 'EmployerServlet';
                        }
                    }, 1000);
                }
            };
            // Set minimum date to today
            const today = new Date().toISOString().split('T')[0];
            document.getElementById('endDate').setAttribute('min', today);

            // Prevent form submission if end date is before today
            document.querySelector('form').addEventListener('submit', function (e) {
                const endDate = document.getElementById('endDate').value;
                if (endDate < today) {
                    e.preventDefault();
                    alert('Ngày kết thúc không thể nhỏ hơn ngày hiện tại');
                }
            });
        </script>
    </body>
</html>