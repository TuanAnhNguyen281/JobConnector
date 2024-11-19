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
            .error-message {
                color: red;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <h1>Chi tiết công việc</h1>

        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <label for="jobTitle">Tên công việc:</label>
        <input type="text" id="jobTitle" value="${jobTitle}" readonly>

        <label for="jobDescription">Mô tả công việc:</label>
        <textarea id="jobDescription" rows="4" readonly>${jobDescription}</textarea>

        <label for="jobRequirement">Yêu cầu ứng viên:</label>
        <textarea id="jobRequirement" rows="4" readonly>${jobRequirement}</textarea>

        <label for="jobBenefit">Quyền lợi:</label>
        <textarea id="jobBenefit" rows="4" readonly>${jobBenefit}</textarea>

        <label for="count">Số lượng cần tuyển (người):</label>
        <input type="number" id="count" value="${count}" readonly min="1">

        <label for="experience">Kinh nghiệm yêu cầu:</label>
        <input type="text" id="experience" value="${experience}" readonly>

        <label for="salaryRange">Mức lương:</label>
        <input type="text" id="salaryRange" value="${salaryRange}" readonly>

        <label for="industry">Lĩnh vực:</label>
        <input type="text" id="industry" value="${industry}" readonly>

        <label for="jobType">Kiểu công việc:</label>
        <input type="text" id="jobType" value="${jobType}" readonly>

        <label for="location">Địa điểm:</label>
        <input type="text" id="location" value="${location}" readonly>

        <label for="endDate">Hạn công việc:</label>
        <input type="date" id="endDate" value="${endDate}" readonly>

        <label for="applyWay">Phương thức nộp hồ sơ:</label>
        <input type="text" id="applyWay" value="${applyWay}" readonly>
        <div style="display: flex; gap: 10px;">
            <button type="button" class="cancel-btn" onclick="goBack()">Quay lại</button>
        </div>

    </form>

    <script>
        function goBack() {
            if (document.referrer) {
                // Nếu có trang trước đó, quay lại trang đó
                window.history.back();
            } else {
                // Nếu không có trang trước đó (trang này được truy cập trực tiếp), quay lại trang chủ hoặc trang mặc định
                window.location.href = 'EmployerServlet';  // Thay đổi URL này nếu cần thiết
            }
        }
    </script>
    <script>
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