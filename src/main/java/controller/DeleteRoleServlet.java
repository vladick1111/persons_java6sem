package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.ConnectionProperty;
import dao.EmpConnBuilder;
import domain.Role;

@WebServlet("/deleterole")
public class DeleteRoleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ConnectionProperty prop;
    String select_role_byId = "SELECT id, rolename FROM roles WHERE id = ?";
    String delete_role = "DELETE FROM roles WHERE id = ?";
    ArrayList<Role> deleteroles = new ArrayList<>();

    public DeleteRoleServlet() throws FileNotFoundException, IOException {
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String strId = request.getParameter("id");
        if (strId != null) {
            EmpConnBuilder builder = new EmpConnBuilder();
            try (Connection conn = builder.getConnection();
                 PreparedStatement ps = conn.prepareStatement(select_role_byId)) {
                ps.setLong(1, Long.parseLong(strId));
                ResultSet rs = ps.executeQuery();
                deleteroles.clear();
                if (rs.next()) {
                    deleteroles.add(new Role(rs.getLong("id"), rs.getString("rolename")));
                }
                request.setAttribute("rolesDelete", deleteroles);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/deleterole.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            EmpConnBuilder builder = new EmpConnBuilder();
            try (Connection conn = builder.getConnection();
                 PreparedStatement ps = conn.prepareStatement(delete_role)) {
                ps.setLong(1, Long.parseLong(idStr));
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("roles");
    }
}