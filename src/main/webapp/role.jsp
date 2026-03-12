<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <meta charset="UTF-8">
    <title>Должности</title>
</head>
<body>
    <jsp:include page="jspf/header.jsp" />
    <div id="main">
        <aside class="leftAside">
            <h3>Список должностей</h3>
            <table class="table table-sm table-bordered">
                <thead>
                    <tr>
                        <th>Код</th>
                        <th>Должность</th>
                        <th>Редактировать</th>
                        <th>Удалить</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="role" items="${roles}">
                        <tr>
                            <td>${role.id}</td>
                            <td>${role.nameRole}</td>
                            <td>
                                <a href='<c:url value="/editrole?id=${role.id}" />' class="btn btn-outline-primary btn-sm">
                                    <img src="images/icon-edit.png" alt="ред" width="20" height="20">
                                </a>
                            </td>
                            <td>
                                <a href='<c:url value="/deleterole?id=${role.id}" />' class="btn btn-outline-danger btn-sm">
                                    <img src="images/icon-delete.png" alt="уд" width="20" height="20">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </aside>

        <section>
            <article>
                <h3>Добавить должность</h3>
                <div class="text-article">
                    <form method="POST" action="">
                        <div class="mb-3 row">
                            <label for="namerole" class="col-sm-3 col-form-label">Должность</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="namerole" />
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </form>
                </div>
            </article>
        </section>
    </div>
    <jsp:include page="jspf/footer.jsp" />
</body>
</html>