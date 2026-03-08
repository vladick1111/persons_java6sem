package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.ConnectionProperty;
import dao.EmpConnBuilder;
import domain.Role;

@WebServlet("/roles")
public class RoleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ConnectionProperty prop;
    String select_all_role = "SELECT id, rolename FROM roles";
    ArrayList<Role> roles = new ArrayList<Role>();
    String userPath;

    public RoleServlet() throws FileNotFoundException, IOException {
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        EmpConnBuilder builder = new EmpConnBuilder();

        try (Connection conn = builder.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(select_all_role);
            if (rs != null) {
                roles.clear();
                while (rs.next()) {
                    roles.add(new Role(rs.getLong("id"), rs.getString("rolename")));
                }
                rs.close();
                request.setAttribute("roles", roles);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        userPath = request.getServletPath();
        if ("/roles".equals(userPath)) {
            request.getRequestDispatcher("/role.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}