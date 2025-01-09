package com.library.dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class DataAccess {

    public static DefaultTableModel getLibrarain() {
        DefaultTableModel tableModel = new DefaultTableModel();
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement
                = connection.prepareStatement("SELECT * FROM librarain"); ResultSet resultSet = statement.executeQuery()) {
            // Add columns to the table model
            tableModel.addColumn("ID");
            tableModel.addColumn("Name");
            tableModel.addColumn("Passward");
            tableModel.addColumn("UserName");
            tableModel.addColumn("Address");
            tableModel.addColumn("City");
            tableModel.addColumn("Contact");
            // Iterate through the result set and add rows to the table model
            while (resultSet.next()) {
                Object[] row = new Object[13]; // Number of columns
                row[0] = resultSet.getObject(1); 
                row[1] = resultSet.getObject(2); 
                row[2] = resultSet.getObject(3); 
                row[3] = resultSet.getObject(4); 
                row[4] = resultSet.getObject(5); 
                row[5] = resultSet.getObject(6); 
                row[6] = resultSet.getObject(7); 
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
//            Notifications.getInstance().show(Notifications.Type.ERROR, "حدث خطأ ما !, حاول ثانية");
        }
        return tableModel;
    }
     
    public static DefaultTableModel getIssueBooks() {
        DefaultTableModel tableModel = new DefaultTableModel();
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement
                = connection.prepareStatement("SELECT * FROM issue_books"); ResultSet resultSet = statement.executeQuery()) {
            // Add columns to the table model
            tableModel.addColumn("IssueBook ID");
            tableModel.addColumn("Student ID");
            tableModel.addColumn("Student Name");
            tableModel.addColumn("Student Contact");
            // Iterate through the result set and add rows to the table model
            while (resultSet.next()) {
                Object[] row = new Object[4]; // Number of columns
                row[0] = resultSet.getObject(1); // IssueBook ID
                row[1] = resultSet.getObject(2); // Student ID
                row[2] = resultSet.getObject(3); // Student Name
                row[3] = resultSet.getObject(4); // Student Contact
                tableModel.addRow(row);
            }

            // Print the output
            System.out.println("Query Result:");
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    System.out.print(tableModel.getValueAt(i, j) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly
        }
        return tableModel;
    }
     
    public static DefaultTableModel getBooks() {
        DefaultTableModel tableModel = new DefaultTableModel();
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement
                = connection.prepareStatement("SELECT * FROM books"); ResultSet resultSet = statement.executeQuery()) {
            // Add columns to the table model
            tableModel.addColumn("Book CallNo");
            tableModel.addColumn("Book Name");
            tableModel.addColumn("Author");
            tableModel.addColumn("Publisher");
            tableModel.addColumn("Quantity");
            tableModel.addColumn("Issued");

            // Iterate through the result set and add rows to the table model
            while (resultSet.next()) {
                Object[] row = new Object[7]; // Number of columns
                row[0] = resultSet.getObject(1);
                row[1] = resultSet.getObject(2);
                row[2] = resultSet.getObject(3);
                row[3] = resultSet.getObject(4);
                row[4] = resultSet.getObject(5);
                row[5] = resultSet.getObject(6);
                tableModel.addRow(row);
                
            }
            // Print the output
            System.out.println("Books Table Result:");
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    System.out.print(tableModel.getValueAt(i, j) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly
        }
        return tableModel;
    }
  
    public static void addLibrarain(String usernameLibrarin, String pass, String name, String adress, String city, String contact) {
        String query = "INSERT INTO librarain (username, password, name, adress, city, contact_name) VALUES (?, ?, ?, ?, ?, ?)";

        // فحص الإدخالات
        if (usernameLibrarin == null || pass == null || name == null || adress == null || city == null || contact == null) {
            System.out.println("Error: One or more fields are null. Please provide valid data.");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            // إعداد المعلمات
            statement.setString(1, usernameLibrarin);
            statement.setString(2, pass);
            statement.setString(3, name);
            statement.setString(4, adress);
            statement.setString(5, city);
            statement.setString(6, contact);

            // تنفيذ الاستعلام
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Success: Librarian added successfully.");
            } else {
                System.out.println("Error: No rows inserted.");
            }
        } catch (SQLException e) {
            System.err.println("Error while adding librarian: " + e.getMessage());
        }
    }

    public static void deleteLibrarain(int librarainId) {
        if (librarainId <= 0) {
            System.out.println("Error: Invalid librarian ID.");
            return;
        }

        String query = "DELETE FROM librarain WHERE librarain_id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            // إعداد المعاملات
            statement.setInt(1, librarainId);

            // تنفيذ الاستعلام
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Success: Librarian with ID " + librarainId + " has been deleted.");
            } else {
                System.out.println("Error: No librarian found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error while deleting librarian: " + e.getMessage());
        }
    }

    public static void deleteBooks(int bookId) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "DELETE FROM books WHERE bookColl_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bookId);
            statement.executeUpdate();
            DatabaseConnection.closeConnection(connection);
            System.out.println("Done delete 1 book");
        } catch (SQLException e) {
//            Notifications.getInstance().show(Notifications.Type.ERROR, "حدث خطأ ما !, حاول ثانية");
        }
    }

    public static void ReturnBooks(int bookCollId, int studentId) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "DELETE FROM issue_books WHERE student_id =?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bookCollId);
            String query2 = "UPDATE books SET quantity = 'available' WHERE bookColl_id = ?";
            statement = connection.prepareStatement(query2);
            statement.setInt(1, bookCollId);

            statement.executeUpdate();
            System.out.println("تم تحديث حالة الكتاب بنجاح!");
            statement.executeUpdate();
            DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
//            Notifications.getInstance().show(Notifications.Type.ERROR, "حدث خطأ ما !, حاول ثانية");
        }
    }

    public static void addBooks(String CallNo, String Name, String Author, String adress, String publisher) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = """
                           INSERT INTO books VALUES (
                               ?,
                               ?,
                               ?,
                               ?,
                               ?,
                               ?
                           )""";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, CallNo);
            statement.setString(2, Name);
            statement.setString(3, Author);
            statement.setString(4, adress);
            statement.setString(5, publisher);
            statement.setString(6, "available");

            statement.executeUpdate();
            DatabaseConnection.closeConnection(connection);
//            Notifications.getInstance().show(Notifications.Type.SUCCESS, "تم اضافة التقرير بنجاح!");
        } catch (SQLException e) {
//            Notifications.getInstance().show(Notifications.Type.ERROR, "حدث خطأ ما!,تأكد من كل البيانات المدخلة ");

        }
    }
    
    public static void addIssueBooks(int bookCallNo, int studentId, String studentName, String studentContact) {
    if (bookCallNo <= 0 || studentId <= 0 || studentName == null || studentName.isEmpty() || studentContact == null || studentContact.isEmpty()) {
        System.out.println("Error: Invalid input values.");
        return;
    }

    String insertQuery = "INSERT INTO issue_books (book_id, student_id, student_name, student_contact) VALUES (?, ?, ?, ?)";
    String updateQuery = "UPDATE books SET quantity = 'Not Available' WHERE bookColl_id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
         PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {

        // Insert issue book record
        insertStatement.setInt(1, bookCallNo);
        insertStatement.setInt(2, studentId);
        insertStatement.setString(3, studentName);
        insertStatement.setString(4, studentContact);
        int rowsInserted = insertStatement.executeUpdate();

        if (rowsInserted > 0) {
            // Update the quantity of the book
            updateStatement.setInt(1, bookCallNo);
            int rowsUpdated = updateStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Success: Book issued and quantity updated.");
            } else {
                System.out.println("Error: Book quantity is zero or insufficient.");
            }
        } else {
            System.out.println("Error: Failed to insert issue record.");
        }

    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
        e.printStackTrace();
    }
}

    public static boolean authenticateAdminUser(String username, String password) {
        boolean isAuthenticated = false;
        String query = "SELECT COUNT(*) FROM admin_users WHERE username = ? AND password = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            // إعداد المعلمات
            statement.setString(1, username);
            statement.setString(2, password); // ملاحظة: لا يُنصح بحفظ كلمات المرور كنصوص واضحة!

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    isAuthenticated = true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error authenticating admin user: " + e.getMessage());
        }
        return isAuthenticated;
    }

    public static boolean authenticateLibrarainUser(String username, String password) {
        boolean isAuthenticated = false;
        String query = "SELECT COUNT(*) FROM librarain WHERE username = ? AND password = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            // إعداد المعلمات
            statement.setString(1, username);
            statement.setString(2, password); // ملاحظة: لا يُنصح بحفظ كلمات المرور كنصوص واضحة!

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    isAuthenticated = true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error authenticating admin user: " + e.getMessage());
        }
        return isAuthenticated;
    }

}
