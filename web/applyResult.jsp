<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Kết quả ứng tuyển</title>

        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f7f7f7;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                height: 100vh;
            }

            h1 {
                font-size: 32px;
                color: #2c3e50;
                margin-bottom: 20px;
            }


            .application-result {
                background-color: #ffffff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 700px;
                margin-top: 20px;
                font-size: 16px;
            }

            .application-result p {
                margin: 10px 0;
                color: #555;
            }

            .application-result strong {
                color: #2ecc71;
            }


            a {
                display: inline-block;
                margin-top: 20px;
                padding: 12px 25px;
                background-color: #2ecc71;
                color: white;
                text-decoration: none;
                border-radius: 6px;
                text-align: center;
                font-weight: bold;
                transition: background-color 0.3s;
            }

            a:hover {
                background-color: #27ae60;
            }


            @media (max-width: 600px) {
                h1 {
                    font-size: 28px;
                }

                .application-result {
                    padding: 15px;
                    width: 90%;
                }

                a {
                    padding: 10px 20px;
                }
            }
        </style>
    </head>

    <body>
        <h1>Kết quả ứng tuyển</h1>

        <c:choose>
            <c:when test="${not empty result}">
                <div class="application-result">
                    <p><strong>Trạng thái:</strong> ${result.statusTitle}</p>
                    <p><strong>Lời nhắn nhà tuyển dụng:</strong> ${result.comment}</p>
                    <a href="ApplicationServlet?action=jobseekerview">Quay lại danh sách ứng tuyển</a>
                </div>
            </c:when>
            <c:otherwise>
                <p>Không có kết quả ứng tuyển nào.</p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
