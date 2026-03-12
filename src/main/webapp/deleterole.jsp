<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <meta charset="UTF-8">
    <title>Удаление должности</title>
</head>
<body>
    <jsp:include page="jspf/header.jsp" />
    <div id="main">
        <aside class="leftAside">
            <h3>Список должностей</h3>
            <table class="table table-sm table-bordered">
                <thead>
                    <tr><th>Код</th><th>Должность</th></tr>
                </thead>
                <tbody>
                    <c:forEach var="role" items="${roles}">
                        <tr><td>${role.id}</td><td>${role.nameRole}</td></tr>
                    </c:forEach>
                </tbody>
            </table>
        </aside>
        <section>
            <article>
                <h3>Удаление должности</h3>
                <div class="text-article">
                    <form method="POST" action="">
                        <input type="hidden" name="id" value="${rolesDelete[0].id}" />
                        <div class="mb-3 row">
                            <label class="col-sm-3 col-form-label">Код</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" readonly value="${rolesDelete[0].id}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label class="col-sm-3 col-form-label">Должность</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" readonly value="${rolesDelete[0].nameRole}" />
                            </div>
                        </div>
                        <button type="submit" class="btn btn-danger">Удалить</button>
                        <a href="roles" class="btn btn-secondary">Отмена</a>
                    </form>
                </div>
            </article>
        </section>
    </div>
    <jsp:include page="jspf/footer.jsp" />
</body>
</html>