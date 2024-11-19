<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kiểm duyệt hồ sơ Công Ty</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
                background: linear-gradient(135deg, #87CEEB, #90EE90);
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                margin: 0;
                flex-direction: column;
            }

            .container {
                background-color: white;
                padding: 2rem;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                max-width: 1400px;
                width: 100%;
            }

            .form-box, .terms-box {
                padding: 1.5rem;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            .terms-box {
                background-color: #f9f9f9;
                border: 1px solid #ddd;
            }

            h2 {
                text-align: center;
                color: #333;
                margin-bottom: 1.5rem;
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

            .required::after {
                content: " *";
                color: red;
            }

            .error {
                color: red;
                font-weight: bold;
                margin-top: 1rem;
            }

            button[type="submit"] {
                width: 30%;
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

            .form-group {
                margin-bottom: 1rem;
            }

            .page-title {
                text-align: center;
                font-size: 2rem;
                font-weight: bold;
                color: #333;
                margin-top: 2rem;
                margin-bottom: 1rem;
            }

            .mess {
                margin-top: 8px;
                width: 100%;
            }
        </style>
    </head>
    <body>
        <!-- Tiêu đề ở góc trên của trang -->
        <div class="page-title">
            Kiểm duyệt thông tin công ty
        </div>

        <div class="container mt-5">
            <div class="row">
                <!-- Form Column -->
                <div class="col-lg-8">
                    <div class="form-box">
                        <form action="employeeprofileservlet" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="action" value="add">
                            <div class="text-center mb-4">
                                <div class="photo-container">
                                    <div class="photo-preview">
                                        <img id="preview" src="/api/placeholder/200/200" alt="Preview">
                                    </div>
                                    <input type="file" id="photo-upload" name="logo" accept="image/*">
                                    <label for="photo-upload" class="photo-label">Chọn ảnh</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-6">
                                    <div class="mb-3">
                                        <label for="taxId" class="form-label">Mã số thuế</label>
                                        <input type="text" class="form-control" id="taxNumber" name="taxNumber" value="${employerProfile.taxNumber}" readonly>
                                    </div>
                                    <div class="mb-3">
                                        <label for="size" class="form-label">Quy Mô</label>
                                        <input type="text" class="form-control" id="size" name="size" value="${employerProfile.companySize}" readonly>
                                    </div>
                                    <div class="mb-3">
                                        <label for="email" class="form-label">Email Công Ty</label>
                                        <input type="email" class="form-control" id="email" name="email" value="${employerProfile.employerEmail}" readonly>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="mb-3">
                                        <label for="companyName" class="form-label">Tên Công Ty</label>
                                        <input type="text" class="form-control" id="companyName" name="companyName" value="${employerProfile.companyName}" readonly>
                                    </div>
                                    <div class="mb-3">
                                        <label for="industry" class="form-label">Lĩnh Vực</label>
                                        <input type="text" class="form-control" id="industry" name="industry" value="${employerProfile.companyIndustry}" readonly>
                                    </div>
                                    <div class="mb-3">
                                        <label for="address" class="form-label">Địa chỉ</label>
                                        <input type="text" class="form-control" id="address" name="address" value="${employerProfile.address}" readonly>
                                    </div>
                                    <div class="mb-3">
                                        <label for="phone" class="form-label">Số Điện Thoại</label>
                                        <input type="tel" class="form-control" id="phone" name="phone" value="${employerProfile.employerPhone}" readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="mb-3">
                                        <label for="description" class="form-label">Mô tả</label>
                                        <textarea class="form-control" id="description" name="description" rows="3" readonly>${employerProfile.companyDescription}</textarea>
                                    </div>
                                </div>
                            </div>

                            <c:if test="${not empty message}">
                                <div class="error" role="alert">
                                    ${message}
                                </div>
                            </c:if>
                        </form>
                    </div>
                </div>

                <!-- Terms and Conditions Box Column -->
                <div class="col-lg-4">
                    <div class="terms-box">
                        <h4>Điều kiện kiểm tra thông tin</h4>
                        <ul>
                            <li><strong>Điều kiện 1:</strong> Ảnh đại diện phải liên quan tới công ty, rõ ràng và không chứa thông tin nhạy cảm</li>
                            <li><strong>Điều kiện 2:</strong> Mã số thuế phải chính xác và hợp lệ theo quy định của cơ quan thuế.
                                Kiểm tra mã số thuế theo đường dẫn: <br> <a href="https://tracuunnt.gdt.gov.vn/tcnnt/mstdn.jsp" target="_blank">Tra cứu mã số thuế</a>
                            </li>
                            <li><strong>Điều kiện 3:</strong> Tên công ty phải đầy đủ, chính xác và có ý nghĩa</li>
                            <li><strong>Điều kiện 4:</strong> Email công ty phải hợp lệ và có khả năng nhận thông báo từ hệ thống</li>
                            <li><strong>Điều kiện 5:</strong> Địa chỉ công ty phải đầy đủ và chính xác, bao gồm số nhà, tên đường, quận/huyện và thành phố</li>
                            <li><strong>Điều kiện 6:</strong> Số điện thoại công ty phải là số điện thoại đúng định dạng, chính thức và có thể liên lạc được</li>
                        </ul>
                    </div>
                    <form method="post" action="CompanyCheckingServlet">
                        <input type="hidden" name="employerID" value="${employerProfile.employerID}" />
                        <div class="mb-3">
                            <textarea class="form-control" placeholder="Lời nhắn" name="comment" id="comment" rows="4"></textarea>
                        </div>

                        <div class="mt-3">
                            <button type="submit" name="action" value="approve" class="btn btn-success">Chấp nhận</button>
                            <button type="submit" name="action" value="reject" class="btn btn-danger">Từ chối</button>
                            <button type="submit" name="action" value="cancel" class="btn btn-warning">Hủy</button>
                        </div>
                    </form>

                </div>
            </div>

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
