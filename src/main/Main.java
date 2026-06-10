package main;
import java.util.Scanner;

import model.Book;
import model.Student;
import service.LibraryService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        if(!AdminLogin.login()) {

            System.out.println(
                    "Invalid Credentials!");

            return;
        }

        LibraryService service = new LibraryService();

        while (true) {

            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");

            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Add Student");
            System.out.println("6. View Students");
            System.out.println("7. Issue Book");
            System.out.println("8. Return Book");
            System.out.println("9. View Issued Books");
            System.out.println("10. Exit");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    sc.nextLine();

                    System.out.print("Book Title: ");
                    String title = sc.nextLine();

                    if(title.trim().isEmpty()) {

                        System.out.println(
                                "Book Title Cannot Be Empty!");

                        break;
                    }

                    System.out.print("Author: ");
                    String author = sc.nextLine();

                    System.out.print("Category: ");
                    String category = sc.nextLine();

                    
                    System.out.print("Quantity: ");
                    int quantity = sc.nextInt();

                    if(quantity <= 0) {

                        System.out.println(
                                "Quantity must be greater than 0");

                        break;
                    }

                    service.addBook(
                            new Book(
                                    0,
                                    title,
                                    author,
                                    category,
                                    quantity));

                    break;

                case 2:

                    service.viewBooks();
                    break;

                case 3:

                    sc.nextLine();

                    System.out.print("Enter Book Title: ");
                    service.searchBook(sc.nextLine());

                    break;

                case 4:

                    System.out.print("Enter Book ID: ");
                    service.deleteBook(sc.nextInt());

                    break;

                case 5:

                    sc.nextLine();

                    System.out.print("Student Name: ");
                    String name = sc.nextLine();

                    System.out.print("Department: ");
                    String dept = sc.nextLine();

                    System.out.print("Phone: ");
                    String phone = sc.nextLine();

                    if(!phone.matches("\\d{10}")) {

                        System.out.println(
                                "Phone number must contain 10 digits");

                        break;
                    }

                    service.addStudent(
                            new Student(
                                    0,
                                    name,
                                    dept,
                                    phone));

                    break;

                case 6:

                    service.viewStudents();
                    break;

                case 7:

                    System.out.print("Enter Student ID: ");
                    int studentId = sc.nextInt();

                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();

                    service.issueBook(studentId, bookId);

                    break;

                case 8:

                    System.out.print("Enter Issue ID: ");
                    int issueId = sc.nextInt();

                    service.returnBook(issueId);

                    break;

                case 9:

                    service.viewIssuedBooks();

                    break;

                case 10:

                    System.out.println("Thank You!");
                    System.exit(0);

                    break;

                default:

                    System.out.println("Invalid Choice!");
            }
        }
    }
}