<%-- 
    Document   : createUser
    Created on : Jul 3, 2024, 8:00:17 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <title>Create User</title>
    </head>
    <body>
        <section class="bg-light py-3 py-md-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12 col-sm-10 col-md-8 col-lg-6 col-xl-5 col-xxl-4">
                        <div class="card border border-light-subtle rounded-3 shadow-sm">
                            <div class="card-body p-3 p-md-4 p-xl-5">
                                <h2 class="fs-6 fw-normal text-center text-secondary mb-4">Enter your details to register</h2>
                                <form action="MainController" method="POST">
                                    <div class="row gy-2 overflow-hidden">
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" name="userID" placeholder="User ID" required>
                                                <label for="userID" class="form-label">User ID</label>
                                            </div> 
                                            <div class="col-12" style="background-color: #f8d7da; color: #721c24;">
                                                ${requestScope.USER_ERROR.userIdError}
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" name="fullName"" placeholder="Full Name" required>
                                                <label for="fullName" class="form-label">Full Name</label>
                                            </div>
                                            <div class="col-12" style="background-color: #f8d7da; color: #721c24;">
                                                ${requestScope.USER_ERROR.fullNameError}
                                            </div>
                                        </div>
                                        <c:if test="${sessionScope.LOGIN_USER == null}">
                                            <div class="col-12">
                                                <div class="form-floating mb-3">
                                                    <input type="email" class="form-control" name="email" placeholder="name@example.com" required>
                                                    <label for="email" class="form-label">Email</label>
                                                </div>
                                                <div class="col-12" style="background-color: #f8d7da; color: #721c24;">
                                                    ${requestScope.USER_ERROR.emailError}
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" name="roleID" value="US" readonly="">
                                                <label for="roleID" class="form-label">Role ID</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="password" class="form-control" name="password" id="password" value="" placeholder="Password" required>
                                                <label for="password" class="form-label">Password</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="password" class="form-control" name="confirm" id="password" value="" placeholder="Confirm Password" required>
                                                <label for="password" class="form-label">Confirm Password</label>
                                            </div>
                                            <div class="col-12" style="background-color: #f8d7da; color: #721c24;">
                                                ${requestScope.USER_ERROR.confirmError}
                                            </div>
                                        </div>
                                            <div class="col-12" style="background-color: #f8d7da; color: #721c24;">
                                                ${requestScope.USER_ERROR.error}
                                            </div>
                                        <div class="col-12">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" value="" name="iAgree" id="iAgree" required>
                                                <label class="form-check-label text-secondary" for="iAgree">
                                                    I agree to the <a href="#!" class="link-primary text-decoration-none">terms and conditions</a>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="d-grid my-3">
                                                <button class="btn btn-primary btn-lg" type="submit" name="action" value="Create">Sign up</button>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <p class="m-0 text-secondary text-center">Already have an account? <a href="login.jsp" class="link-primary text-decoration-none">Sign in</a></p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
