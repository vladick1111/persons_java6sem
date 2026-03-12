package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

@WebServlet("/editrole")
public class EditRoleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ConnectionProperty prop;
    String select_all_role = "SELECT id, rolename FROM roles";
    String select_role_byId = "SELECT id, rolename FROM roles WHERE id = ?";
    String edit_role = "UPDATE roles SET rolename = ? WHERE id = ?";
    ArrayList<Role> roles = new ArrayList<>();
    ArrayList<Role> editroles = new ArrayList<>();

    public EditRoleServlet() throws FileNotFoundException, IOException {
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        EmpConnBuilder builder = new EmpConnBuilder();
        try (Connection conn = builder.getConnection()) {
            String strId = request.getParameter("id");
            Long id = (strId != null) ? Long.parseLong(strId) : null;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(select_all_role);
            roles.clear();
            while (rs.next()) {
                roles.add(new Role(rs.getLong("id"), rs.getString("rolename")));
            }
            request.setAttribute("roles", roles);

            if (id != null) {
                try (PreparedStatement ps = conn.prepareStatement(select_role_byId)) {
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    editroles.clear();
                    if (rs.next()) {
                        editroles.add(new Role(rs.getLong("id"), rs.getString("rolename")));
                    }
                    request.setAttribute("rolesEdit", editroles);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/editrole.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("namerole");

        if (idStr != null && name != null) {
            EmpConnBuilder builder = new EmpConnBuilder();
            try (Connection conn = builder.getConnection();
                 PreparedStatement ps = conn.prepareStatement(edit_role)) {
                ps.setString(1, name);
                ps.setLong(2, Long.parseLong(idStr));
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("roles");
    }
}