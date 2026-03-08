<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <meta charset="UTF-8">
    <title>Сотрудники</title>
</head>
<body>
    <jsp:include page="jspf/header.jsp" />
    <div id="main">
        <aside class="leftAside">
            <h3>Список сотрудников</h3>
            <table>
                <thead>
                    <tr>
                        <th>Код</th>
                        <th>Фамилия</th>
                        <th>Имя</th>
                        <th>Должность</th>
                        <th>Телефон</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="person" items="${persons}">
                        <tr>
                            <td>${person.id}</td>
                            <td>${person.lastName}</td>
                            <td>${person.firstName}</td>
                            <td>${person.role.nameRole}</td>
                            <td>${person.phone}</td>
                            <td>${person.email}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </aside>

        <section>
            <article>
                <h3>Данные по сотруднику</h3>
                <div class="text-article">
                    <form method="POST" action="">
                        <p>
                            <label for="firstName">Имя</label>
                            <input type="text" name="firstName" />
                        </p>
                        <p>
                            <label for="lastName">Фамилия</label>
                            <input type="text" name="lastName" />
                        </p>
                        <p>
                            <label for="phone">Телефон</label>
                            <input type="text" name="phone" />
                        </p>
                        <p>
                            <label for="email">Email</label>
                            <input type="email" name="email" />
                        </p>
                        <p>
                            <label for="role">Должность</label>
                            <select name="role">
                                <option disabled selected>Выберите должность</option>
                                <c:forEach var="role" items="${roles}">
                                    <option value="${role.id}">${role.nameRole}</option>
                                </c:forEach>
                            </select>
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