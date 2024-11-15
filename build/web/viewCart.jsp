<%-- 
    Document   : viewCart.jsp
    Created on : Jul 4, 2024, 7:16:17 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center">Your Cosmetics Cart</h1>
            <c:if test="${requestScope.MESSAGE != null}">
                <div class="alert alert-info mt-3">
                    ${requestScope.MESSAGE}
                </div>
            </c:if>
            <c:if test="${requestScope.EMAIL_MESSAGE != null}">
                <div class="alert alert-info mt-3">                  
                    ${requestScope.EMAIL_MESSAGE}
                </div>
            </c:if>
            <c:if test="${sessionScope.CART == null}">
                <div class="alert alert-info mt-3">                  
                    No item
                </div>
            </c:if>
            <c:if test="${sessionScope.CART != null}">
                <c:if test="${not empty sessionScope.CART.cart}">
                    <table class="table table-bordered table-hover mt-4">
                        <thead class="thead-dark">
                            <tr>
                                <th>NO</th>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Total</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="total" value="0"></c:set>
                            <c:forEach var="cosmeticEntry" varStatus="counter" items="${sessionScope.CART.cart}">
                                <c:set var="cosmetic" value="${cosmeticEntry.value}"></c:set>
                                <c:set var="total" value="${total + cosmetic.productPrice * cosmetic.productQuantity}"></c:set>
                                <form action="MainController" method="POST">
                                    <tr>
                                        <td>${counter.count}</td>
                                    <td>${cosmetic.productID}
                                        <input type="hidden" name="id" value="${cosmetic.productID}">
                                    </td>
                                    <td>${cosmetic.productName}</td>
                                    <td>
                                        <input type="number" name="quantity" value="${cosmetic.productQuantity}" min="1" class="form-control">
                                    </td>
                                    <td>${cosmetic.productPrice}$</td>
                                    <td>${cosmetic.productPrice * cosmetic.productQuantity}$</td>
                                    <td>
                                        <a href="MainController?action=Remove_Item&id=${cosmetic.productID}" class="btn btn-danger btn-sm" title="Remove">Remove</a>
                                        <button class="btn btn-warning btn-sm" type="submit" name="action" value="Update_Item" title="Edit">Edit Item</button>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                    <h3 class="text-right">Total: ${total}$</h3>
                </c:if>
            </c:if>
            <c:if test="${requestScope.CHECKOUT_MESSAGE != null}">
                <div class="alert alert-info mt-3">                  
                    <c:forEach var="checkoutMessage" items="${requestScope.CHECKOUT_MESSAGE}">
                        ${checkoutMessage}<br>
                    </c:forEach>
                </div>
            </c:if>
            <div class="text-center mt-4">
                <a href="shopping.jsp" class="btn btn-primary">Add more</a>
                <form action="MainController" method="POST" class="d-inline">
                    <input type="submit" name="action" value="Checkout" class="btn btn-success">
                </form>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
