import java.util.TreeSet;

class Employee implements Comparable<Employee> {
    private String id;
    private String name;
    private String department;
    private double salary;

    public Employee(String id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Dept: %s, Salary: $%.2f", 
            id, name, department, salary);
    }
}

public class EmployeeRecords {
    private TreeSet<Employee> employees = new TreeSet<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees in records");
            return;
        }
        System.out.println("Employees (sorted by name):");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    public static void main(String[] args) {
        EmployeeRecords records = new EmployeeRecords();
        records.addEmployee(new Employee("E003", "Charlie Brown", "HR", 55000));
        records.addEmployee(new Employee("E001", "Alice Johnson", "IT", 75000));
        records.addEmployee(new Employee("E002", "Bob Smith", "Finance", 65000));
        
        records.displayEmployees();
    }
}
