<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <meta charset="UTF-8">
    <title>Редактирование сотрудника</title>
</head>
<body>
    <jsp:include page="jspf/header.jsp" />
    <div id="main">
        <aside class="leftAside">
            <h3>Список сотрудников</h3>
            <table class="table table-sm table-bordered">
                <thead>
                    <tr><th>Код</th><th>Фамилия</th><th>Имя</th><th>Должность</th><th>Телефон</th><th>Email</th></tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${persons}">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.lastName}</td>
                            <td>${p.firstName}</td>
                            <td>${p.role.nameRole}</td>
                            <td>${p.phone}</td>
                            <td>${p.email}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </aside>
        <section>
            <article>
                <h3>Редактирование сотрудника</h3>
                <div class="text-article">
                    <form method="POST" action="">
                        <input type="hidden" name="id" value="${personsEdit[0].id}" />
                        <div class="mb-3 row">
                            <label class="col-sm-3 col-form-label">Код</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" readonly value="${personsEdit[0].id}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label class="col-sm-3 col-form-label">Имя</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="firstName" value="${personsEdit[0].firstName}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label class="col-sm-3 col-form-label">Фамилия</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="lastName" value="${personsEdit[0].lastName}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label class="col-sm-3 col-form-label">Телефон</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="phone" value="${personsEdit[0].phone}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label class="col-sm-3 col-form-label">Email</label>
                            <div class="col-sm-6">
                                <input type="email" class="form-control" name="email" value="${personsEdit[0].email}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label class="col-sm-3 col-form-label">Должность</label>
                            <div class="col-sm-6">
                                <select name="role" class="form-control">
                                    <c:forEach var="role" items="${roles}">
                                        <option value="${role.id}" ${role.id == personsEdit[0].idRole ? 'selected' : ''}>${role.nameRole}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Редактировать</button>
                        <a href="persons" class="btn btn-secondary">Отмена</a>
                    </form>
                </div>
            </article>
        </section>
    </div>
    <jsp:include page="jspf/footer.jsp" />
</body>
</html>