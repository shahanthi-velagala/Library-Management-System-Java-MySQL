package model;
public class Student {

    private int id;
    private String name;
    private String department;
    private String phone;

    public Student(int id, String name,
                   String department, String phone) {

        this.id = id;
        this.name = name;
        this.department = department;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhone() {
        return phone;
    }
}