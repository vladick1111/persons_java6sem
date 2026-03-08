<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <meta charset="UTF-8">
    <title>Должности</title>
</head>
<body>
    <jsp:include page="jspf/header.jsp" />
    <div id="main">
        <aside class="leftAside">
            <h3>Список должностей</h3>
            <table>
                <thead>
                    <tr>
                        <th>Код</th>
                        <th>Должность</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="role" items="${roles}">
                        <tr>
                            <td>${role.id}</td>
                            <td>${role.nameRole}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </aside>
        <section>
            <article>
                <h3>Наименование должности</h3>
                <div class="text-article">
                    <form method="POST" action="">
                        <p>
                            <label for="namerole">Должность</label>
                            <input type="text" name="namerole" />
                        </p>
                        <p>
                            <button type="submit">Добавить</button>
                        </p>
                    </form>
                </div>
            </article>
        </section>
    </div>
    <jsp:include page="jspf/footer.jsp" />
</body>
</html>