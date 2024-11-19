<%@page import="model.UserProfile"%>
<%@page import="dal.ProfileDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    Integer userId = (Integer) session.getAttribute("UserID");
    ProfileDAO profileDAO = new ProfileDAO();
    UserProfile Profile = profileDAO.getUserProfileByUserID(userId);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập Nhật Thông Tin Người Dùng</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script>
            function Validate() {
                var phoneNumber = document.getElementById("phoneNumber").value.trim();
                phoneNumber = phoneNumber.replace(/\s+/g, ''); // Loại bỏ tất cả khoảng trắng trong số điện thoại
                var dob = new Date(document.getElementById("dob").value);
                var today = new Date();

                if (phoneNumber === "") {
                    alert("Vui lòng nhập số điện thoại.");
                    return false;
                }

                // Kiểm tra nếu số điện thoại không bắt đầu bằng số 0
                if (phoneNumber.charAt(0) !== '0') {
                    alert("Số điện thoại phải bắt đầu bằng số 0.");
                    return false;
                }

                if (phoneNumber.length > 1 && phoneNumber.charAt(1) === '0') {
                    alert("Chữ số thứ hai sau số 0 phải khác 0.");
                    return false;
                }


                // Kiểm tra nếu số lượng chữ số trong số điện thoại không nằm trong khoảng 10 đến 11
                if (phoneNumber.length < 10 || phoneNumber.length > 11) {
                    alert("Số điện thoại phải có từ 10 đến 11 chữ số.");
                    return false;
                }

                // Kiểm tra nếu số điện thoại chứa ký tự không phải là chữ số
                var phonePattern = /^[0-9]+$/;
                if (!phonePattern.test(phoneNumber)) {
                    alert("Số điện thoại chỉ được chứa các chữ số.");
                    return false;
                }

                // Kiểm tra ngày sinh
                if (dob > today) {
                    alert("Ngày sinh không được lớn hơn ngày hiện tại!");
                    return false;
                }

                return true;
            }
        </script>
    </head>
    <body>
        <div class="container mt-5">
            <h1>Cập Nhật Thông Tin Người Dùng</h1>
            <form action="userprofileservlet" method="POST" onsubmit="return Validate();">
                <input type="hidden" name="action" value="update"> 

                <!-- Họ và Tên -->
                <div class="mb-3">
<label for="fullName" class="form-label">Họ và Tên</label>
                    <input type="text" class="form-control" id="fullName" name="fullName" 
                           value="<c:out value='${sessionScope.fullname}'/>" disabled readonly>
                </div>

                <!-- Số điện thoại -->
                <div class="mb-3">
                    <label for="phoneNumber" class="form-label">Số điện thoại</label>
                    <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" 
                           value="<%= Profile.getPhoneNumber()%>" required>
                </div>

                <!-- Email -->
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="text" class="form-control" id="email" name="email" 
                           value="<c:out value='${sessionScope.email}'/>" disabled readonly>
                </div>

                <!-- Ngày sinh -->
                <div class="mb-3">
                    <label for="dob" class="form-label">Ngày sinh</label>
                    <input type="date" class="form-control" id="dob" name="dob" 
                           value="<%= Profile.getDob()%>" required>
                </div>

                <!-- Địa chỉ -->
                <div class="mb-3">
                    <label for="address" class="form-label">Tỉnh/Thành phố</label>
                    <input type="text" class="form-control" id="address" name="address" 
                           value="<%= Profile.getAddress()%>" required>
                </div>

                <!-- Nút Cập nhật -->
                <button type="submit" class="btn btn-primary">Cập nhật hồ sơ</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>