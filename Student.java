package com.example.studentapp.model;

/** MODEL: plain data object representing one row of the STUDENT table. */
public class Student {
    private int id;
    private String name;
    private String email;
    private String course;

    public Student() { }

    public Student(String name, String email, String course) {
        this.name = name;
        this.email = email;
        this.course = course;
    }

    public Student(int id, String name, String email, String course) {
        this(name, email, course);
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    @Override
    public String toString() {
        return String.format("%-4d %-20s %-28s %s", id, name, email, course);
    }
}
