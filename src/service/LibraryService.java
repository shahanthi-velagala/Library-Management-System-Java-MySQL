package service;
import java.sql.*;

import database.DBConnection;
import model.Book;
import model.Student;


public class LibraryService {

    // Add Book
    public void addBook(Book book) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO books(title,author,category,quantity) VALUES(?,?,?,?)");

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setInt(4, book.getQuantity());

            ps.executeUpdate();

            System.out.println("Book Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Books
    public void viewBooks() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM books");

            System.out.println("\n===== BOOK LIST =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("book_id") + " | "
                                + rs.getString("title") + " | "
                                + rs.getString("author") + " | "
                                + rs.getString("category") + " | "
                                + rs.getInt("quantity"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Search Book
    public void searchBook(String title) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            "SELECT * FROM books WHERE title LIKE ?");

            ps.setString(1, "%" + title + "%");

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            System.out.println("\n===== SEARCH RESULT =====");

            while (rs.next()) {

                found = true;

                System.out.println(
                        rs.getInt("book_id") + " | "
                                + rs.getString("title") + " | "
                                + rs.getString("author") + " | "
                                + rs.getString("category") + " | "
                                + rs.getInt("quantity"));
            }

            if (!found) {
                System.out.println("Book Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete Book
    public void deleteBook(int bookId) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            "DELETE FROM books WHERE book_id=?");

            ps.setInt(1, bookId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book Deleted Successfully!");
            } else {
                System.out.println("Book ID Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add Student
    public void addStudent(Student student) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            "INSERT INTO students(student_name,department,phone) VALUES(?,?,?)");

            ps.setString(1, student.getName());
            ps.setString(2, student.getDepartment());
            ps.setString(3, student.getPhone());

            ps.executeUpdate();

            System.out.println("Student Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Students
    public void viewStudents() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM students");

            System.out.println("\n===== STUDENT LIST =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("student_id") + " | "
                                + rs.getString("student_name") + " | "
                                + rs.getString("department") + " | "
                                + rs.getString("phone"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Issue Book
    public void issueBook(int studentId, int bookId) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement checkStudent =
                    con.prepareStatement(
                            "SELECT * FROM students WHERE student_id=?");

            checkStudent.setInt(1, studentId);

            ResultSet studentRs =
                    checkStudent.executeQuery();

            if(!studentRs.next()) {

                System.out.println(
                        "Student ID Not Found!");

                return;
            }

            PreparedStatement checkBook =
                    con.prepareStatement(
                            "SELECT quantity FROM books WHERE book_id=?");

            checkBook.setInt(1, bookId);

            ResultSet rs =
                    checkBook.executeQuery();

            if(rs.next()) {

                int quantity =
                        rs.getInt("quantity");

                if(quantity > 0) {

                    PreparedStatement issue =
                            con.prepareStatement(
                                    "INSERT INTO issued_books(student_id,book_id,issue_date) VALUES(?,?,CURDATE())");

                    issue.setInt(1, studentId);
                    issue.setInt(2, bookId);

                    issue.executeUpdate();

                    PreparedStatement update =
                            con.prepareStatement(
                                    "UPDATE books SET quantity=quantity-1 WHERE book_id=?");

                    update.setInt(1, bookId);

                    update.executeUpdate();

                    System.out.println(
                            "Book Issued Successfully!");

                } else {

                    System.out.println(
                            "Book Out Of Stock!");
                }

            } else {

                System.out.println(
                        "Book Not Found!");
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    // Return Book
    public void returnBook(int issueId) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement getBook =
                    con.prepareStatement(
                            "SELECT book_id FROM issued_books WHERE issue_id=? AND return_date IS NULL");

            getBook.setInt(1, issueId);

            ResultSet rs =
                    getBook.executeQuery();

            if(rs.next()) {

                int bookId =
                        rs.getInt("book_id");

                PreparedStatement updateReturn =
                        con.prepareStatement(
                                "UPDATE issued_books SET return_date=CURDATE() WHERE issue_id=?");

                updateReturn.setInt(1, issueId);

                updateReturn.executeUpdate();

                PreparedStatement updateQuantity =
                        con.prepareStatement(
                                "UPDATE books SET quantity=quantity+1 WHERE book_id=?");

                updateQuantity.setInt(1, bookId);

                updateQuantity.executeUpdate();

                System.out.println(
                        "Book Returned Successfully!");

            } else {

                System.out.println(
                        "Issue ID Not Found or Already Returned!");
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    // View Issued Books
    public void viewIssuedBooks() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM issued_books");

            System.out.println("\n===== ISSUED BOOKS =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("issue_id")
                                + " | Student ID: "
                                + rs.getInt("student_id")
                                + " | Book ID: "
                                + rs.getInt("book_id")
                                + " | Issue Date: "
                                + rs.getDate("issue_date")
                                + " | Return Date: "
                                + rs.getDate("return_date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}