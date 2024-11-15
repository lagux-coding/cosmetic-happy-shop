<%-- 
    Document   : viewOrder
    Created on : Jul 5, 2024, 1:31:49 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <title>Orders List</title>
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="mb-4">Order List of ${param.id}</h1>
            <c:if test="${requestScope.LIST_ORDER == null}">
                <div class="alert alert-warning">${requestScope.MESSAGE}</div>
            </c:if>
            <!-- Back link -->
            <div class="text-center mb-3">
                <a href="javascript:history.back()" class="btn btn-secondary">Back</a>
            </div>
            <c:if test="${requestScope.LIST_ORDER != null}">
                <c:if test="${not empty requestScope.LIST_ORDER}">
                    <table class="table table-bordered table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">NO</th>
                                <th scope="col">Order ID</th>
                                <th scope="col">User ID</th>
                                <th scope="col">Order Date</th>
                                <th scope="col">Total</th>
                                <th scope="col">Details</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" varStatus="counter" items="${requestScope.LIST_ORDER}">
                                <tr>
                                    <th scope="row">${counter.count}</th>
                                    <td>${order.orderID}</td>
                                    <td>${order.userID}</td>
                                    <td>${order.orderDate}</td>
                                    <td>${order.total}</td>
                                    <td>
                                        <a class="btn btn-info btn-sm" href="MainController?action=View_Order_Detail&id=${order.orderID}&userID=${order.userID}">Details</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </c:if>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
