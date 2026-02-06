package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/PersonsServlet")
public class PersonsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<h2>Привет PersonsServlet</h2>");
            out.println("<p>Страница для работы с сотрудниками</p>");
            out.println("<ul>");
            out.println("<li>ФИО: Иванов Иван Иванович</li>");
            out.println("<li>Дата рождения: 01.01.1990</li>");
            out.println("<li>Телефон: +7(999)123-45-67</li>");
            out.println("<li>Адрес: г. Москва, ул. Ленина, 1</li>");
            out.println("</ul>");
            out.println("<a href='index.jsp'>На главную</a>");
        } finally {
            out.close();
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}