<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <meta charset="UTF-8">
    <title>Главная страница</title>
</head>
<body>
    <jsp:include page="/jspf/header.jsp" />
    <div id="main">
        <h2>Функции системы</h2>
        <nav>
            <ul>
                <li><a href="persons">Сотрудники</a></li>
                <li><a href="roles">Должности</a></li>
            </ul>
        </nav>
        <p>Используйте меню для перехода к нужному разделу.</p>
    </div>
    <jsp:include page="/jspf/footer.jsp" />
</body>
</html>