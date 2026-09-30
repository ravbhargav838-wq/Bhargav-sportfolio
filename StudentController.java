package com.example.studentapp.controller;

import com.example.studentapp.dao.StudentDAO;
import com.example.studentapp.model.Student;
import com.example.studentapp.view.StudentView;

import java.sql.SQLException;
import java.util.Optional;

/** CONTROLLER: receives user choices from the View, calls the DAO (Model), sends results back to the View. */
public class StudentController {

    private final StudentDAO dao;
    private final StudentView view;

    public StudentController(StudentDAO dao, StudentView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean running = true;
        while (running) {
            view.showMenu();
            int choice = view.readChoice();
            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> view.showStudents(dao.findAll());
                    case 3 -> findStudent();
                    case 4 -> updateStudent();
                    case 5 -> deleteStudent();
                    case 0 -> {
                        view.showMessage("Goodbye!");
                        running = false;
                    }
                    default -> view.showMessage("Invalid option. Try again.");
                }
            } catch (SQLException e) {
                view.showMessage("Database error: " + e.getMessage());
            }
        }
    }

    private void addStudent() throws SQLException {
        Student s = view.readStudentDetails();
        dao.add(s);
        view.showMessage("Student added with ID " + s.getId());
    }

    private void findStudent() throws SQLException {
        int id = view.readInt("Enter student ID: ");
        Optional<Student> found = dao.findById(id);
        if (found.isPresent()) {
            view.showStudent(found.get());
        } else {
            view.showMessage("No student with ID " + id);
        }
    }

    private void updateStudent() throws SQLException {
        int id = view.readInt("Enter ID of student to update: ");
        if (dao.findById(id).isEmpty()) {
            view.showMessage("No student with ID " + id);
            return;
        }
        Student s = view.readStudentDetails();
        s.setId(id);
        view.showMessage(dao.update(s) ? "Student updated." : "Update failed.");
    }

    private void deleteStudent() throws SQLException {
        int id = view.readInt("Enter ID of student to delete: ");
        view.showMessage(dao.delete(id) ? "Student deleted." : "No student with ID " + id);
    }
}
