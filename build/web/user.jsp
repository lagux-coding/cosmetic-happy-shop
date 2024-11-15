<%-- 
    Document   : user.jsp
    Created on : Jul 3, 2024, 7:40:10 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/userStyle.css">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                padding: 20px;
            }
            .user-info {
                margin-bottom: 20px;
                background-color: #f8f9fa;
                padding: 15px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            .email-warning {
                margin-top: 10px;
                color: #dc3545;
            }
        </style>
        <title>User Page</title>
    </head>
    <body>
        <div class="container">
            <div class="user-info">
                <h1>Welcome ${sessionScope.LOGIN_USER.fullName}</h1>
                <p><strong>User ID:</strong> ${sessionScope.LOGIN_USER.userID}</p>
                <p><strong>Full Name:</strong> ${sessionScope.LOGIN_USER.fullName}</p>
                <p><strong>Role ID:</strong> ${sessionScope.LOGIN_USER.roleID}</p>
                <p><strong>Password:</strong> ${sessionScope.LOGIN_USER.password}</p>
                <p><strong>Email:</strong> <c:if test="${sessionScope.LOGIN_USER.email != null}">${sessionScope.LOGIN_USER.email}</c:if></p>
            </div>
            <div class="mt-3">
                <form action="MainController" method="post">
                    <button type="submit" name="action" value="Shopping" class="btn btn-success mr-2" title="Shopping">Go Shopping</button>
                    <a href="MainController?action=View_Order&id=${sessionScope.LOGIN_USER.userID}" class="btn btn-info mr-2" title="View Order">Show Orders</a>
                <a href="MainController?action=Logout" class="btn btn-primary ml-2" title="Logout">Logout</a>
                </form>
                    
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
