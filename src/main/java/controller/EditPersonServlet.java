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
import domain.Person;
import domain.Role;

@WebServlet("/editperson")
public class EditPersonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ConnectionProperty prop;
    String select_all_person = "SELECT id, firstname, lastname, phone, email, roleid FROM persons";
    String select_all_role = "SELECT id, rolename FROM roles";
    String select_person_byId = "SELECT id, firstname, lastname, phone, email, roleid FROM persons WHERE id = ?";
    String edit_person = "UPDATE persons SET firstname=?, lastname=?, phone=?, email=?, roleid=? WHERE id=?";
    ArrayList<Role> roles = new ArrayList<>();
    ArrayList<Person> persons = new ArrayList<>();
    ArrayList<Person> editpersons = new ArrayList<>();

    public EditPersonServlet() throws FileNotFoundException, IOException {
        prop = new ConnectionProperty();
    }

    private Role findById(Long id, ArrayList<Role> roles) {
        for (Role r : roles) {
            if (r.getId().equals(id)) return r;
        }
        return null;
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

            rs = stmt.executeQuery(select_all_person);
            persons.clear();
            while (rs.next()) {
                Long roleId = rs.getLong("roleid");
                persons.add(new Person(
                    rs.getLong("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    roleId,
                    findById(roleId, roles)
                ));
            }
            request.setAttribute("persons", persons);

            if (id != null) {
                try (PreparedStatement ps = conn.prepareStatement(select_person_byId)) {
                    ps.setLong(1, id);
                    rs = ps.executeQuery();
                    editpersons.clear();
                    if (rs.next()) {
                        editpersons.add(new Person(
                            rs.getLong("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getLong("roleid")
                        ));
                    }
                    request.setAttribute("personsEdit", editpersons);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/editperson.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String roleIdStr = request.getParameter("role");

        if (idStr != null && firstName != null && lastName != null && roleIdStr != null) {
            EmpConnBuilder builder = new EmpConnBuilder();
            try (Connection conn = builder.getConnection();
                 PreparedStatement ps = conn.prepareStatement(edit_person)) {
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, phone);
                ps.setString(4, email);
                ps.setLong(5, Long.parseLong(roleIdStr));
                ps.setLong(6, Long.parseLong(idStr));
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("persons");
    }
}