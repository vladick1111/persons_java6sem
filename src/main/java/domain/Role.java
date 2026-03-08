package domain;

public class Role {
    private Long id;
    private String nameRole;

    // Конструктор по умолчанию
    public Role() {}

    // Конструктор с двумя параметрами (нужен для сервлета)
    public Role(Long id, String nameRole) {
        this.id = id;
        this.nameRole = nameRole;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    // Для удобного вывода в консоль (необязательно)
    @Override
    public String toString() {
        return "Role {id=" + id + ", nameRole='" + nameRole + "'}";
    }
}