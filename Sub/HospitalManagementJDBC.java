import java.sql.*;

public class HospitalManagementJDBC {
    public static void main(String[] args) {
        String urlWithoutDB = "jdbc:mysql://localhost:3306/";
        String dbName = "hospitaldb";
        String user = "root";
        String password = "T1ger@2025";

        String[][] patients = {
            {"Ravi", "45", "Fever"},
            {"Sneha", "30", "Diabetes"},
            {"Amit", "25", "Cold"}
        };
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC Driver loaded.");

            try (Connection conn = DriverManager.getConnection(urlWithoutDB, use`r, password);
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
                System.out.println("Database ready.");
            }

            String urlWithDB = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false";
            try (Connection conn = DriverManager.getConnection(urlWithDB, user, password);
                 Statement stmt = conn.createStatement()) {

                System.out.println("Connected to database.");

                String createTable = "CREATE TABLE IF NOT EXISTS patients (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(50), " +
                        "age INT, " +
                        "disease VARCHAR(50))";
                stmt.executeUpdate(createTable);
                System.out.println("Table ready.");

                stmt.executeUpdate("DELETE FROM patients");

                String insertSQL = "INSERT INTO patients (name, age, disease) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    for (String[] p : patients) {
                        pstmt.setString(1, p[0]);
                        pstmt.setInt(2, Integer.parseInt(p[1]));
                        pstmt.setString(3, p[2]);
                        pstmt.executeUpdate();
                    }
                    System.out.println("Data inserted.");
                }

                try (ResultSet rs = stmt.executeQuery("SELECT * FROM patients")) {
                    System.out.println("Patient Records:");
                    while (rs.next()) {
                        System.out.printf("ID: %d, Name: %s, Age: %d, Disease: %s%n",
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("age"),
                                rs.getString("disease"));
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        System.out.println("Program finished.");
    }
}
