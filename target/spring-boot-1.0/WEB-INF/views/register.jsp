<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng kí</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-7">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header">
                    <h3 class="text-center font-weight-light my-4">Create Account</h3>
                </div>
                <div class="card-body">
                    <form:form method="GET" action="/register"
                               modelAttribute="registerUser">
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <div class="form-floating mb-3 mb-md-0">
                                    <c:set var="errorFirstName">
                                        <form:errors path="firstName"
                                                     cssClass="invalid-feedback" />
                                    </c:set>
                                    <label for="inputFirstName">First name</label>
                                    <form:input
                                            class="form-control ${not empty errorFirstName ? 'is-invalid' : ''}"
                                            id="inputFirstName" type="text"
                                            placeholder="Enter your first name"
                                            path="firstName" />
                                    ${errorFirstName}
                                </div>

                            </div>
                            <div class="col-md-6">
                                <div class="form-floating">
                                    <c:set var="errorLarstName">
                                        <form:errors path="lastName"
                                                     cssClass="invalid-feedback" />
                                    </c:set>
                                    <label for="inputLastName">Last name</label>
                                    <form:input class="form-control ${not empty errorFirstName ? 'is-invalid' : ''}" id="inputLastName"
                                                type="text" placeholder="Enter your last name"
                                                path="lastName" />
                                    ${errorLarstName}
                                </div>
                            </div>
                        </div>
                        <div class="form-floating mb-3">
                            <c:set var="errorEmail">
                                <form:errors path="email"
                                             cssClass="invalid-feedback" />
                            </c:set>
                            <label for="inputEmail">Email address</label>
                            <form:input
                                    class="form-control ${not empty errorFirstName ? 'is-invalid' : ''}"
                                    id="inputEmail" type="email"
                                    placeholder="name@example.com" path="email" />
                            ${errorEmail}

                        </div>
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <div class="form-floating mb-3 mb-md-0">
                                    <c:set var="errorPassword">
                                        <form:errors path="passWord"
                                                     cssClass="invalid-feedback" />
                                    </c:set>
                                    <label for="inputPassword">Password</label>
                                    <form:input
                                            class="form-control ${not empty errorFirstName ? 'is-invalid' : ''}"
                                            id="inputPassword" type="password"
                                            placeholder="Create a password"
                                            path="passWord"  />
                                    ${errorPassword}

                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-floating mb-3 mb-md-0">
                                    <c:set var="errorConfirmPassword">
                                        <form:errors path="confirmPassword"
                                                     cssClass="invalid-feedback" />
                                    </c:set>
                                    <label for="inputPasswordConfirm">Confirm
                                        Password</label>
                                    <form:input
                                            class="form-control ${not empty errorFirstName ? 'is-invalid' : ''}"
                                            id="inputPasswordConfirm" type="password"
                                            placeholder="Confirm password"
                                            path="confirmPassword" />
                                        ${errorConfirmPassword}

                                </div>
                            </div>
                        </div>
                        <div class="mt-4 mb-0">
                            <div class="d-grid"><button id="btnCreateAccount"
                                                        class="btn btn-primary">Create Account</button>
                            </div>
                        </div>
                    </form:form>
                </div>
                <div class="card-footer text-center py-3">
                    <div class="small"><a href="/login">Have an account? Go to
                        login</a></div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
