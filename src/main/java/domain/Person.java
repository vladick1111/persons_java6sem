package domain;

public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Long idRole;
    private Role role;

    public Person() {}

    public Person(Long id, String firstName, String lastName, 
                  String phone, String email, Long idRole, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.idRole = idRole;
        this.role = role;
    }

    // Геттеры
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public Long getIdRole() { return idRole; }
    public Role getRole() { return role; }

    // Сеттеры (если нужны)
    public void setId(Long id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setIdRole(Long idRole) { this.idRole = idRole; }
    public void setRole(Role role) { this.role = role; }
}