<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cập nhật công việc</title>
        <link rel="stylesheet" type="text/css" href="style.css"> 
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
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }
            label {
                display: block;
                margin-bottom: 8px;
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
        <h1>Cập nhật công việc</h1>
        <form action="JobManageServlet" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="jobid" value="${job.jobID}">

            <label for="jobTitle">Tên công việc:</label>
            <input type="text" id="jobTitle" name="jobTitle" value="${job.jobTitle}" required>

            <label for="jobDescription">Mô tả công việc:</label>
            <textarea id="jobDescription" name="jobDescription" rows="4" required>${job.jobDescription}</textarea>

            <label for="jobRequirement">Yêu cầu ứng viên:</label>
            <textarea id="jobRequirement" name="jobRequirement" rows="4" required>${job.jobRequirement}</textarea>

            <label for="jobBenefit">Quyền lợi:</label>
            <textarea id="jobBenefit" name="jobBenefit" rows="4" required>${job.jobBenefits}</textarea>

            <label for="count">Số lượng cần tuyển (người):</label>
            <input type="number" id="count" name="count" min="1" value="${job.noNeed}" required>

            <label for="experience">Kinh nghiệm yêu cầu:</label>
            <select id="experience" name="experience" required>
                <option value="">Chọn kinh nghiệm</option>
                <c:forEach items="${experiments}" var="exp">
                    <option value="${exp.experienceID}" ${job.experienceID == exp.experienceID ? 'selected' : ''}>${exp.experienceDescription}</option>
                </c:forEach>
            </select>

            <label for="salaryRange">Mức lương:</label>
            <select id="salaryRange" name="salaryRange" required>
                <option value="">Chọn mức lương</option>
                <c:forEach items="${salary}" var="sal">
                    <option value="${sal.salaryRangeID}" ${job.salaryRangeID == sal.salaryRangeID ? 'selected' : ''}>${sal.salaryDescription}</option>
                </c:forEach>
            </select>

            <label for="industry">Lĩnh vực:</label>
            <select id="industry" name="industry" required>
                <option value="">Chọn lĩnh vực</option>
                <c:forEach items="${industries}" var="ind">
                    <option value="${ind.industryID}" ${job.industryID == ind.industryID ? 'selected' : ''}>${ind.industryName}</option>
                </c:forEach>
            </select>

            <label for="jobType">Kiểu công việc:</label>
            <select id="jobType" name="jobType" required>
                <option value="">Chọn kiểu công việc</option>
                <c:forEach items="${type}" var="t">
                    <option value="${t.jobtypeID}" ${job.jobTypeID == t.jobtypeID ? 'selected' : ''}>${t.jobTypeDescription}</option>
                </c:forEach>
            </select>

            <label for="location">Địa điểm:</label>
            <select id="location" name="location" required>
                <option value="">Chọn địa điểm</option>
                <c:forEach items="${location}" var="l">
                    <option value="${l.locationID}" ${job.locationID == l.locationID ? 'selected' : ''}>${l.locationName}</option>
                </c:forEach>
            </select>

            <label for="endDate">Hạn công việc:</label>
            <input type="date" id="endDate" name="endDate" value="${job.endAt}" required>

            <label for="applyWay">Phương thức nộp hồ sơ:</label>
            <select id="applyWay" name="applyWay" required>
                <option value="">Chọn phương thức nộp</option>
                <c:forEach items="${way}" var="w">
                    <option value="${w.wayID}" ${job.wayID == w.wayID ? 'selected' : ''}>${w.wayTitle}</option>
                </c:forEach>
            </select>

            <div style="display: flex; gap: 10px;">
                <input type="submit" value="Chỉnh sửa công việc" onclick="return confirm('Bạn có chắc chắn muốn chỉnh sửa công việc này không?');">
                <button type="button" class="cancel-btn" onclick="window.location.href = 'EmployerServlet'">Hủy</button>
            </div>
        </form>


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
