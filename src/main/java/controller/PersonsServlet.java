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
import domain.Person;
import domain.Role;

@WebServlet("/persons")
public class PersonsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ConnectionProperty prop;
    String select_all_person = "SELECT id, firstname, lastname, phone, email, roleid FROM persons";
    String select_all_role = "SELECT id, rolename FROM roles";
    ArrayList<Role> roles = new ArrayList<>();
    ArrayList<Person> persons = new ArrayList<>();
    String userPath;

    public PersonsServlet() throws FileNotFoundException, IOException {
        prop = new ConnectionProperty();
    }

    private Role findById(Long id, ArrayList<Role> roles) {
        if (roles != null) {
            for (Role r : roles) {
                if (r.getId().equals(id)) {
                    return r;
                }
            }
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        EmpConnBuilder builder = new EmpConnBuilder();

        try (Connection conn = builder.getConnection()) {
            // Загрузка всех должностей
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

            // Загрузка всех сотрудников
            stmt = conn.createStatement();
            rs = stmt.executeQuery(select_all_person);
            if (rs != null) {
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
                rs.close();
                request.setAttribute("persons", persons);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        userPath = request.getServletPath();
        if ("/persons".equals(userPath)) {
            request.getRequestDispatcher("/person.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}