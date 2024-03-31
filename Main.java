import java.util.*;

class Student {
    String name;
    int rollNo;
    int presentDays;
    
    Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
        this.presentDays = 0; // Initially, no present days
    }
    
    void markAttendance(boolean isPresent) {
        if (isPresent) {
            presentDays++;
            System.out.println(name + " is marked present.");
        } else {
            System.out.println(name + " is marked absent.");
        }
    }
    
    String getName() {
        return name;
    }
    
    int getRollNo() {
        return rollNo;
    }
    
    int getPresentDays() {
        return presentDays;
    }
}

class AttendanceManagementSystem {
    Map<Integer, Student> students;
    
    AttendanceManagementSystem() {
        students = new HashMap<>();
    }
    
    void addStudent(int rollNo, String name) {
        if (students.containsKey(rollNo)) {
            System.out.println("Student with roll number " + rollNo + " already exists.");
        } else {
            students.put(rollNo, new Student(name, rollNo));
            System.out.println("Student " + name + " with roll number " + rollNo + " added successfully.");
        }
    }
    
    void markStudentAttendance(int rollNo, boolean isPresent) {
        Student student = students.get(rollNo);
        if (student != null) {
            student.markAttendance(isPresent);
        } else {
            System.out.println("Student with roll number " + rollNo + " not found.");
        }
    }
    
    void displayAttendance() {
        System.out.println("Attendance Report:");
        for (Student student : students.values()) {
            System.out.println("Name: " + student.getName() +
                               ", Roll No: " + student.getRollNo() +
                               ", Present Days: " + student.getPresentDays());
        }
    }
    
    Student getStudent(int rollNo) {
        return students.get(rollNo);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AttendanceManagementSystem ams = new AttendanceManagementSystem();
        
        int choice;
        do {
            System.out.println("\n1. Register Student");
            System.out.println("2. Student: Mark Attendance");
            System.out.println("3. Admin: View Attendance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter roll number: ");
                    int rollNo = scanner.nextInt();
                    System.out.print("Enter name: ");
                    scanner.nextLine(); // Consume newline character
                    String name = scanner.nextLine();
                    ams.addStudent(rollNo, name);
                    break;
                case 2:
                    System.out.print("Enter roll number: ");
                    int studentRollNo = scanner.nextInt();
                    Student student = ams.getStudent(studentRollNo);
                    if (student != null) {
                        System.out.print("Mark Your Attendance (Present/Absent): ");
                        boolean isPresent = scanner.nextBoolean();
                        student.markAttendance(isPresent);
                    } else {
                        System.out.println("Student with roll number " + studentRollNo + " not found.");
                    }
                    break;
                case 3:
                    ams.displayAttendance();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        } while (choice != 4);
        
        scanner.close();
    }
}
