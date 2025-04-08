package sms;
//import java.io.*;
import java.util.*;
public class StudentManagementApp {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentManagementSystem sms = new StudentManagementSystem();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    sms.displayStudents();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine();
        
        sms.addStudent(new Student(name, rollNumber, grade));
        System.out.println("Student added successfully!");
    }

    private static void removeStudent() {
        System.out.print("Enter Roll Number to Remove: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();
        
        sms.removeStudent(rollNumber);
        System.out.println("Student removed successfully!");
    }

    private static void searchStudent() {
        System.out.print("Enter Roll Number to Search: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();
        
        Student student = sms.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Student Found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }
}
