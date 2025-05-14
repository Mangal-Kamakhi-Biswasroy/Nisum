import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String id;
    private String name;
    private double grade;

    public Student(String id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getGrade() { return grade; }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Grade: %.2f", id, name, grade);
    }
}

public class StudentManagement {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }

    public Student searchStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void main(String[] args) {
        StudentManagement system = new StudentManagement();
        system.addStudent(new Student("S001", "Alice Johnson", 85.5));
        system.addStudent(new Student("S002", "Bob Smith", 92.0));
        
        System.out.println("All Students:");
        system.displayAllStudents();
        
        System.out.println("\nSearching for S001:");
        Student found = system.searchStudent("S001");
        System.out.println(found != null ? found : "Student not found");
        
        system.removeStudent("S002");
        System.out.println("\nAfter removing S002:");
        system.displayAllStudents();
    }
}
