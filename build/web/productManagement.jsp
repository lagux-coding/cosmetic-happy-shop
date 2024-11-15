<%-- 
    Document   : productManagement
    Created on : Jul 4, 2024, 2:30:17 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <title>Product Management</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="admin.jsp">Admin Panel</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="admin.jsp">User Management</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Product Management</a>
                        </li>
                        <!-- Add more navigation items as needed -->
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <form action="MainController" method="POST" class="d-inline">
                                <input type="hidden" name="action" value="Logout">
                                <button type="submit" class="nav-link btn btn-link">Logout</button>
                            </form>
                        </li>
                </div>
            </div>
        </nav>

        <div class="container mt-4">
            <h1 class="mb-4">Cosmetic Happy Shop</h1>

            <!-- Search form -->
            <form action="MainController" class="mb-4">
                <div class="input-group">
                    <input type="text" name="search" class="form-control" placeholder="Enter name of cosmetic" value="${param.search}">
                    <button type="submit" name="action" value="Search_Product" class="btn btn-primary" title="Search">Search</button>
                </div>
            </form>

            <!-- Add product button -->
            <div class="mb-4">
                <form action="MainController" method="POST">
                    <button type="submit" name="action" value="Add_Product_Page" class="btn btn-success" title="Add Product">Add more product</button>
                </form>
            </div>

            ${requestScope.MESSAGE}

            <c:if test="${sessionScope.LIST_COSMETIC != null}">
                <c:if test="${not empty sessionScope.LIST_COSMETIC}">
                    <table class="table table-bordered table-hover mt-4">
                        <thead class="table-dark">
                            <tr>
                                <th>NO</th>
                                <th>Product ID</th>
                                <th>Product Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cosmetic" varStatus="counter" items="${sessionScope.LIST_COSMETIC}">
                            <form action="MainController" method="POST">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${cosmetic.productID}
                                        <input type="hidden" name="id" value="${cosmetic.productID}">
                                    </td>
                                    <td>
                                        <input type="text" name="name" value="${cosmetic.productName}" class="form-control">
                                    </td>
                                    <td>
                                        <input type="number" name="price" value="${cosmetic.productPrice}" min="0" class="form-control">
                                    </td>
                                    <td>
                                        <input type="number" name="quantity" value="${cosmetic.productQuantity}" min="0" class="form-control">
                                    </td>
                                    <td>
                                        <input type="hidden" name="search" value="${param.search}">
                                        <button type="submit" name="action" value="Update_Product" class="btn btn-warning btn-sm" title="Update">Update</button>
                                        <a href="MainController?action=Remove_Product&id=${cosmetic.productID}&search=${param.search}" class="btn btn-danger btn-sm" title="Remove">Remove</a>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </c:if>
        </div>
        <script src="https://unpkg.com/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
