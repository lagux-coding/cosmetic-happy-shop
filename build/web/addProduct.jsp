<%-- 
    Document   : addProduct
    Created on : Jul 4, 2024, 5:50:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Cosmetic</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 50%;
                margin: 50px auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            h1 {
                text-align: center;
                color: #333;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            .form-group input[type="text"],
            .form-group input[type="number"] {
                width: calc(100% - 10px);
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 14px;
            }
            .form-group button {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 14px;
            }
            .form-group button:hover {
                background-color: #45a049;
            }         
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Add New Product</h1>
            <form action="MainController" method="POST">
                <div class="form-group">
                    <label for="name">Product Name:</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="price">Price:</label>
                    <input type="number" id="price" name="price" required min="0">
                </div>
                <div class="form-group">
                    <label for="quantity">Quantity:</label>
                    <input type="number" id="quantity" name="quantity" required min="0">
                </div>
                <c:if test="${requestScope.MESSAGE != null}">
                    <div class="message">
                        ${requestScope.MESSAGE}
                    </div>
                </c:if>
                <div class="form-group">
                    <button type="submit" name="action" value="Add_Product">Add New Product</button>
                </div>
            </form>
            <form action="MainController" method="POST" class="form-group">
                <button type="submit" name="action" value="Product_Page">Back to Product Management</button>
            </form>
        </div>
    </body>
</html>
