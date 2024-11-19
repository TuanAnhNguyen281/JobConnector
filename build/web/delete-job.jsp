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
        <h1>Xóa công việc</h1>
        <form action="JobManageServlet" method="post">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="jobid" value="${job.jobID}">

            <label for="jobTitle">Tên công việc:</label>
            <input type="text" id="jobTitle" name="jobTitle" value="${job.jobTitle}" disabled="">

            <label for="jobDescription">Mô tả công việc:</label>
            <textarea id="jobDescription" name="jobDescription" rows="4" disabled="">${job.jobDescription}</textarea>

            <label for="jobRequirement">Yêu cầu ứng viên:</label>
            <textarea id="jobRequirement" name="jobRequirement" rows="4" disabled="">${job.jobRequirement}</textarea>

            <label for="jobBenefit">Quyền lợi:</label>
            <textarea id="jobBenefit" name="jobBenefit" rows="4" disabled="">${job.jobBenefits}</textarea>

            <label for="count">Số lượng cần tuyển (người):</label>
            <input type="number" id="count" name="count" min="1" value="${job.noNeed}" disabled="">

            <label for="experience">Kinh nghiệm yêu cầu:</label>
            <input type="text" id="jobTitle" name="jobTitle" value="${job.experienceTitle}" disabled="">


            <label for="salaryRange">Mức lương:</label>
            <input type="text" id="jobTitle" name="jobTitle" value="${job.salaryRangeTitle}" disabled="">

            <label for="industry">Lĩnh vực:</label>
            <input type="text" id="jobTitle" name="jobTitle" value="${job.industryTitle}"   disabled="">

            <label for="jobType">Kiểu công việc:</label>
            <input type="text" id="jobTitle" name="jobTitle" value="${job.jobtypeTitle}" disabled="">

            <label for="location">Địa điểm:</label>
            <input type="text" id="jobTitle" name="jobTitle" value="${job.locationTitle}" disabled="">

            <label for="endDate">Hạn công việc:</label>
            <input type="text" id="jobTitle" name="jobTitle" value="${job.endAt}" disabled="">

            <label for="applyWay">Phương thức nộp hồ sơ:</label>
            <input type="text" id="jobTitle" name="jobTitle" value="${job.wayTitle}" disabled="">

            <div style="display: flex; gap: 10px;">
                <input type="submit" value="xóa công việc"  onclick="return confirm('Bạn có chắc chắn muốn xóa công việc này không?');">
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
