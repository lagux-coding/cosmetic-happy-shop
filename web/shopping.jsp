<%-- 
    Document   : shopping.jsp
    Created on : Jul 4, 2024, 3:30:03 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                padding: 20px;
            }
            .table-container {
                margin-top: 20px;
            }
        </style>
        <title>Cosmetic Happy Shop</title>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Welcome to Cosmetic Happy Shop</h1>

            <!-- Form to view cart -->
            <div class="text-center mb-2">
                <form action="MainController">
                    <button type="submit" name="action" value="View_Cart" class="btn btn-primary" title="View Cart">View Cart</button>
                </form>
            </div>

            <!-- Back link -->
            <div class="text-center mb-3">
                <a href="user.jsp" class="btn btn-secondary">Back</a>
            </div>
            <!-- Message -->
            <c:if test="${not empty requestScope.MESSAGE}">
                <div class="alert alert-success mt-3">
                    ${requestScope.MESSAGE}
                </div>
            </c:if>
            <!-- Display products -->
            <div class="table-container">
                <c:if test="${sessionScope.LIST_COSMETIC != null}">
                    <c:if test="${not empty sessionScope.LIST_COSMETIC}">
                        <table class="table table-bordered">
                            <thead class="thead-dark">
                                <tr>
                                    <th>NO</th>
                                    <th>Product ID</th>
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Add</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cosmetic" varStatus="counter" items="${sessionScope.LIST_COSMETIC}">
                                <form action="MainController">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${cosmetic.productID}
                                            <input type="hidden" name="id" value="${cosmetic.productID}">
                                        </td>
                                        <td>${cosmetic.productName}
                                            <input type="hidden" name="name" value="${cosmetic.productName}">
                                        </td>
                                        <td>${cosmetic.productPrice}
                                            <input type="hidden" name="price" value="${cosmetic.productPrice}">
                                        </td>
                                        <td>
                                            <select name="cmbQuantity" class="form-control">
                                                <option value="1">1 item</option>
                                                <option value="2">2 items</option>
                                                <option value="3">3 items</option>
                                                <option value="4">4 items</option>
                                                <option value="5">5 items</option>
                                                <option value="10">10 items</option>
                                                <option value="50">50 items</option>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="submit" name="action" value="Add_To_Cart" class="btn btn-success" title="Add">Add</button>
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty sessionScope.LIST_COSMETIC}">
                        <div class="alert alert-info" role="alert">
                            No products available.
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
