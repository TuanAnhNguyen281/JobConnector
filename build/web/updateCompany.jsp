<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chỉnh sửa hồ sơ Công ty</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
                background: linear-gradient(135deg, #87CEEB, #90EE90);
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            .container {
                background-color: white;
                padding: 2rem;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 1000px;
            }

            h2 {
                text-align: center;
                color: #333;
                margin-bottom: 1.5rem;
            }

            .form-group {
                margin-bottom: 1rem;
            }

            .photo-container {
                width: 150px;
                height: 150px;
                position: relative;
            }

            .photo-preview {
                width: 150px;
                height: 150px;
                border-radius: 50%;
                background-color: #ddd;
                overflow: hidden;
            }

            #preview {
                width: 100%;
                height: 100%;
                object-fit: cover;
            }

            #photo-upload {
                display: none;
            }

            .photo-label {
                cursor: pointer;
                position: absolute;
                bottom: 0;
                right: 0;
                background: #007bff;
                color: white;
                padding: 5px 10px;
                border-radius: 4px;
                font-size: 12px;
            }

            label {
                display: block;
                margin-bottom: 0.5rem;
                color: #666;
            }

            input[type="text"],
            input[type="email"],
            input[type="tel"],
            select,
            textarea {
                width: 100%;
                padding: 0.5rem;
                border: 1px solid #ddd;
                border-radius: 4px;
                box-sizing: border-box;
            }

            textarea {
                height: 100px;
                resize: vertical;
            }

            button[type="submit"] {
                width: 100%;
                padding: 0.75rem;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 1rem;
            }

            button[type="submit"]:hover {
                background-color: #0056b3;
            }

            .required::after {
                content: " *";
                color: red;
            }

            .error {
                color: red;
                font-weight: bold;
                margin-top: 1rem;
            }

            .description-label {
                font-size: 1.2rem;
                font-weight: bold;
            }

            .description-input {
                font-size: 1.2rem;
                font-weight: normal;
                font-style: normal;
            }

        </style>
    </head>
    <body>
        <div class="container mt-5">
            <form action="employeeprofileservlet" method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="update">
                <div class="row">
                    <div class="col-12 text-center mb-4">
                        <div class="photo-container text-center">
                            <div class="photo-preview">
                                <c:choose>
                                    <c:when test="${not empty Eprofile.logo}">
                                        <img id="preview" src="userSaving/companysave/${Eprofile.logo}" alt="Company Logo">
                                    </c:when>
                                    <c:otherwise>
                                        <img id="preview" src="/api/placeholder/200/200" alt="Preview">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <input type="file" id="photo-upload" name="logo" accept="image/*">
                            <label for="photo-upload" class="photo-label">Chọn ảnh</label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <div class="mb-3">
                            <label for="taxId" class="form-label">Mã số thuế</label>
                            <input type="text" class="form-control" id="taxNumber" name="taxNumber" value="${Eprofile.taxNumber}" required>
                        </div>
                        <div class="mb-3">
                            <label for="size" class="form-label">Quy Mô</label>
                            <select class="form-select" id="size" name="size" required>
                                <option value="">Chọn quy mô</option>
                                <c:forEach items="${sizes}" var="size">
                                    <option value="${size.sizeID}" ${size.sizeID == Eprofile.sizeID ? 'selected' : ''}>
                                        ${size.sizeDescription}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email Công Ty</label>
                            <input type="email" class="form-control" id="email" name="email" value="${Eprofile.employerEmail}" required>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="mb-3">
                            <label for="companyName" class="form-label">Tên Công Ty</label>
                            <input type="text" class="form-control" id="companyName" name="companyName" value="${Eprofile.companyName}" required>
                        </div>
                        <div class="mb-3">
                            <label for="industry" class="form-label">Lĩnh Vực</label>
                            <select class="form-select" id="industry" name="industry" required>
                                <option value="">Chọn lĩnh vực</option>
                                <c:forEach items="${industries}" var="industry">
                                    <option value="${industry.industryID}" ${industry.industryID == Eprofile.industryID ? 'selected' : ''}>
                                        ${industry.industryName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Địa chỉ</label>
                            <input type="text" class="form-control" id="address" name="address" value="${Eprofile.address}" required>
                        </div>
                        <div class="mb-3">
                            <label for="phone" class="form-label">Số Điện Thoại</label>
                            <input type="tel" class="form-control" id="phone" name="phone" value="${Eprofile.employerPhone}" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="mb-3">
                            <label for="description" class="form-label description-label">Mô tả</label>
                            <textarea class="form-control description-input" id="description" name="description" rows="3" required>${Eprofile.description}</textarea>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Tạo Hồ Sơ Công Ty</button>
                <c:if test="${not empty message}">
                    <div class="error" role="alert">
                        ${message}
                    </div>
                </c:if>
            </form>
        </div>
        <script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            document.getElementById('photo-upload').addEventListener('change', function (e) {
                const file = e.target.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        document.getElementById('preview').src = e.target.result;
                    }
                    reader.readAsDataURL(file);
                }
            });
        </script>
    </body>
</html>