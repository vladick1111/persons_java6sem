package domain;

/**
 * Класс данных о должностях
 */
public class Role {
    
    // Идентификатор должности
    private Long id;
    
    // Наименование должности
    private String nameRole;
    
    // Оклад (добавлено по твоему варианту)
    private Double salary;
    
    // Конструктор без параметров
    public Role() {
    }
    
    // Конструктор с названием должности
    public Role(String nameRole) {
        this.nameRole = nameRole;
    }
    
    // Конструктор с названием и окладом
    public Role(String nameRole, Double salary) {
        this.nameRole = nameRole;
        this.salary = salary;
    }
    
    // Полный конструктор
    public Role(Long id, String nameRole, Double salary) {
        this.id = id;
        this.nameRole = nameRole;
        this.salary = salary;
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
    
    public Double getSalary() {
        return salary;
    }
    
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    
    // Переопределение метода toString()
    @Override
    public String toString() {
        return "Role {" + 
               "Id = " + id + 
               ", NameRole = '" + nameRole + "'" + 
               ", Salary = " + salary + 
               "}";
    }
}