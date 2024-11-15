<%-- 
    Document   : admin.jsp
    Created on : Jul 3, 2024, 7:40:02 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <title>Administrator</title>
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="admin.jsp">Admin Panel</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#userManagement">User Management</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="MainController?action=Product_Page">Product Management</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <form action="MainController" method="POST" class="d-inline">
                                <input type="hidden" name="action" value="Logout">
                                <button type="submit" class="nav-link btn btn-link">Logout</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Content -->
        <div class="container mt-4">
            <!-- Welcome Message -->
            <div class="alert alert-success" role="alert">
                Welcome: ${sessionScope.LOGIN_USER.fullName}
            </div>

            <!-- User Management Section -->
            <section id="userManagement">
                <h2>User Management</h2>
                <!-- Search Form -->
                <form class="mb-4" action="MainController" method="POST">
                    <div class="input-group">
                        <input type="text" class="form-control" name="search" value="${param.search}" placeholder="Search for users...">
                        <button class="btn btn-outline-secondary" type="submit" name="action" value="Search" title="Search">
                            <i class="fas fa-search"></i> Search
                        </button>
                    </div>
                </form>
                <c:if test="${sessionScope.LIST_USER != null}">
                    <c:if test="${not empty sessionScope.LIST_USER}">    
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>User ID</th>
                                    <th>Full Name</th>
                                    <th>Role ID</th>
                                    <th>Password</th>
                                    <th>Email</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" varStatus="counter" items="${sessionScope.LIST_USER}">
                                <form action="MainController" method="POST" class="d-inline">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${user.userID}</td>
                                        <td>
                                            <input type="text" name="fullName" value="${user.fullName}" class="form-control">
                                        </td>
                                        <td>
                                            <select name="roleID" class="form-control">
                                                <option value="AD" ${user.roleID == 'AD' ? 'selected' : ''}>AD</option>
                                                <option value="US" ${user.roleID == 'US' ? 'selected' : ''}>US</option>
                                            </select>
                                        </td>
                                        <td>${user.password}</td>
                                        <td>
                                            <input type="email" name="email" value="${user.email}" class="form-control">
                                        </td>
                                        <td>
                                            <input type="hidden" name="userID" value="${user.userID}">
                                            <input type="hidden" name="search" value="${param.search}">
                                            <button type="submit" name="action" value="Update" class="btn btn-outline-primary btn-sm" title="Edit">
                                                <i class="fas fa-edit"></i>
                                            </button>
                                            <a href="MainController?action=Delete&userID=${user.userID}&search=${param.search}" class="btn btn-outline-danger btn-sm" title="Delete">
                                                <i class="fas fa-trash"></i>
                                            </a>
                                            <a href="MainController?action=View_Order&id=${user.userID}" class="btn btn-outline-success btn-sm" title="View Order">
                                                <i class="fas fa-eye"></i>
                                            </a>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/js/all.min.js"></script>
    </body>
</html>
