// package Codes;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.sql.*;

// public class RegistrationSystem extends JFrame implements ActionListener {
//     private JLabel nameLabel, emailLabel, phoneLabel, resultLabel;
//     private JTextField nameField, emailField, phoneField;
//     private JButton submitButton, clearButton;

//     // Database credentials
//     private String dbURL = "jdbc:mysql://localhost:3306/";
//     private String user = "root";
//     private String password = "T1ger@2025";
//     private Connection conn;

//     public RegistrationSystem() {
//         setTitle("Registration System");
//         setSize(450, 300);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLocationRelativeTo(null);

//         // Panel setup
//         JPanel panel = new JPanel(new GridBagLayout());
//         panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
//                 "Enter Your Details", 0, 0, new Font("Arial", Font.BOLD, 14), Color.BLUE));
//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.insets = new Insets(8, 10, 8, 10);
//         gbc.fill = GridBagConstraints.HORIZONTAL;

//         // Labels and fields
//         nameLabel = new JLabel("Name:");
//         emailLabel = new JLabel("Email:");
//         phoneLabel = new JLabel("Phone:");

//         nameField = new JTextField();
//         emailField = new JTextField();
//         phoneField = new JTextField();
//         nameField.setToolTipText("Enter letters only");
//         emailField.setToolTipText("example@example.com");
//         phoneField.setToolTipText("10-digit number");

//         // Buttons
//         submitButton = new JButton("Submit");
//         styleButton(submitButton, new Color(255, 204, 0), Color.BLACK);
//         clearButton = new JButton("Clear");
//         styleButton(clearButton, Color.LIGHT_GRAY, Color.BLACK);

//         submitButton.addActionListener(this);
//         clearButton.addActionListener(this);

//         resultLabel = new JLabel("", JLabel.CENTER);
//         resultLabel.setFont(new Font("Arial", Font.BOLD, 14));

//         // Layout components
//         gbc.gridx = 0; gbc.gridy = 0; panel.add(nameLabel, gbc);
//         gbc.gridx = 1; panel.add(nameField, gbc);
//         gbc.gridx = 0; gbc.gridy = 1; panel.add(emailLabel, gbc);
//         gbc.gridx = 1; panel.add(emailField, gbc);
//         gbc.gridx = 0; gbc.gridy = 2; panel.add(phoneLabel, gbc);
//         gbc.gridx = 1; panel.add(phoneField, gbc);
//         gbc.gridx = 0; gbc.gridy = 3; panel.add(submitButton, gbc);
//         gbc.gridx = 1; panel.add(clearButton, gbc);

//         setLayout(new BorderLayout());
//         add(panel, BorderLayout.CENTER);
//         add(resultLabel, BorderLayout.SOUTH);

//         // Database initialization
//         initDatabase();

//         setVisible(true);
//     }

//     private void styleButton(JButton button, Color bg, Color fg) {
//         button.setBackground(bg);
//         button.setForeground(fg);
//         button.setFont(new Font("Arial", Font.BOLD, 16));
//         button.setFocusPainted(false);
//         button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
//         // Hover effect
//         button.addMouseListener(new java.awt.event.MouseAdapter() {
//             public void mouseEntered(java.awt.event.MouseEvent evt) {
//                 button.setBackground(button.getBackground().darker());
//             }
//             public void mouseExited(java.awt.event.MouseEvent evt) {
//                 button.setBackground(bg);
//             }
//         });
//     }

//     private void initDatabase() {
//         try {
//             Class.forName("com.mysql.cj.jdbc.Driver");
//             conn = DriverManager.getConnection(dbURL, user, password);

//             Statement stmt = conn.createStatement();
//             stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS registration_db");
//             stmt.executeUpdate("USE registration_db");
//             stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
//                     "id INT AUTO_INCREMENT PRIMARY KEY," +
//                     "name VARCHAR(50)," +
//                     "email VARCHAR(50) UNIQUE," +
//                     "phone VARCHAR(15))");

//         } catch (Exception ex) {
//             JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
//             ex.printStackTrace();
//         }
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
//         if (e.getSource() == clearButton) {
//             clearFields();
//             resultLabel.setText("");
//             return;
//         }

//         String name = nameField.getText().trim();
//         String email = emailField.getText().trim();
//         String phone = phoneField.getText().trim();

//         if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
//             showMessage("Please fill all fields!", Color.RED);
//         } else if (!isValidEmail(email)) {
//             showMessage("Invalid email!", Color.RED);
//         } else if (!isValidPhone(phone)) {
//             showMessage("Invalid phone!", Color.RED);
//         } else {
//             if (insertUser(name, email, phone)) {
//                 showMessage("Registration Successful!", new Color(0, 128, 0));
//                 openWelcomeWindow(name);
//                 clearFields();
//             } else {
//                 showMessage("Email already exists!", Color.RED);
//             }
//         }
//     }

//     private boolean isValidEmail(String email) {
//         int at = email.indexOf('@');
//         return at > 0 && at < email.length() - 1;
//     }

//     private boolean isValidPhone(String phone) {
//         if (phone.length() != 10) return false;
//         for (char c : phone.toCharArray()) if (!Character.isDigit(c)) return false;
//         return true;
//     }

//     private boolean insertUser(String name, String email, String phone) {
//         try {
//             String query = "INSERT INTO users (name, email, phone) VALUES (?, ?, ?)";
//             PreparedStatement ps = conn.prepareStatement(query);
//             ps.setString(1, name);
//             ps.setString(2, email);
//             ps.setString(3, phone);
//             ps.executeUpdate();
//             return true;
//         } catch (SQLException ex) {
//             return false;
//         }
//     }

//     private void showMessage(String message, Color color) {
//         resultLabel.setText(message);
//         resultLabel.setForeground(color);
//     }

//     private void clearFields() {
//         nameField.setText("");
//         emailField.setText("");
//         phoneField.setText("");
//     }

//     private void openWelcomeWindow(String name) {
//         JFrame welcome = new JFrame("Welcome");
//         welcome.setSize(400, 250);
//         welcome.setLocationRelativeTo(null);
//         welcome.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//         JPanel panel = new JPanel() {
//             @Override
//             protected void paintComponent(Graphics g) {
//                 super.paintComponent(g);
//                 Graphics2D g2 = (Graphics2D) g;
//                 GradientPaint gp = new GradientPaint(0, 0, Color.CYAN, 0, getHeight(), Color.MAGENTA);
//                 g2.setPaint(gp);
//                 g2.fillRect(0, 0, getWidth(), getHeight());

//                 g.setColor(Color.WHITE);
//                 g.setFont(new Font("Arial", Font.BOLD, 24));
//                 g.drawString("Welcome, " + name + "!", 50, 100);

//                 g.setColor(Color.YELLOW);
//                 g.fillOval(50, 120, 50, 50);
//                 g.setColor(Color.ORANGE);
//                 g.fillRect(120, 120, 60, 60);
//                 g.setColor(Color.RED);
//                 g.drawLine(200, 120, 300, 180);
//             }
//         };

//         welcome.add(panel);
//         welcome.setVisible(true);
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(RegistrationSystem::new);
//     }
// }


package Codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationSystem extends JFrame implements ActionListener {
    private JLabel nameLabel, emailLabel, phoneLabel, resultLabel;
    private JTextField nameField, emailField, phoneField;
    private JButton submitButton, clearButton;

    private String dbURL = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String password = "T1ger@2025";
    private Connection conn;

    public RegistrationSystem() {
        setTitle("🌟 Registration System 🌟");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 239, 213));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.ORANGE, 3),
                "📝 Enter Your Details 📝", 0, 0, new Font("Comic Sans MS", Font.BOLD, 16), Color.MAGENTA));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameLabel = new JLabel("👤 Name:");
        emailLabel = new JLabel("📧 Email:");
        phoneLabel = new JLabel("📱 Phone:");

        nameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        emailLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        phoneLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        nameField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();

        submitButton = new JButton("🚀 Submit");
        styleButton(submitButton, new Color(255, 140, 0), Color.WHITE);
        clearButton = new JButton("🧹 Clear");
        styleButton(clearButton, new Color(100, 149, 237), Color.WHITE);

        submitButton.addActionListener(this);
        clearButton.addActionListener(this);

        resultLabel = new JLabel("", JLabel.CENTER);
        resultLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        gbc.gridx = 0; gbc.gridy = 0; panel.add(nameLabel, gbc);
        gbc.gridx = 1; panel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(emailLabel, gbc);
        gbc.gridx = 1; panel.add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(phoneLabel, gbc);
        gbc.gridx = 1; panel.add(phoneField, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(submitButton, gbc);
        gbc.gridx = 1; panel.add(clearButton, gbc);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        initDatabase();
        setVisible(true);
    }

    private void styleButton(JButton button, Color bg, Color fg) {
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { button.setBackground(bg.brighter()); }
            public void mouseExited(MouseEvent evt) { button.setBackground(bg); }
        });
    }

    private void initDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, user, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS registration_db");
            stmt.executeUpdate("USE registration_db");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50)," +
                    "email VARCHAR(50) UNIQUE," +
                    "phone VARCHAR(15))");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            clearFields();
            resultLabel.setText("");
            return;
        }

        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            showMessage("⚠️ Please fill all fields!", Color.RED);
        } else if (!isValidEmail(email)) {
            showMessage("❌ Invalid email!", Color.RED);
        } else if (!isValidPhone(phone)) {
            showMessage("❌ Invalid phone!", Color.RED);
        } else {
            if (insertUser(name, email, phone)) {
                showMessage("🎉 Registration Successful!", new Color(0, 128, 0));
                openWelcomeWindow(name);
                clearFields();
            } else {
                showMessage("📧 Email already exists!", Color.RED);
            }
        }
    }

    private boolean isValidEmail(String email) {
        int at = email.indexOf('@');
        return at > 0 && at < email.length() - 1;
    }

    private boolean isValidPhone(String phone) {
        if (phone.length() != 10) return false;
        for (char c : phone.toCharArray()) if (!Character.isDigit(c)) return false;
        return true;
    }

    private boolean insertUser(String name, String email, String phone) {
        try {
            String query = "INSERT INTO users (name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) { return false; }
    }

    private void showMessage(String message, Color color) {
        resultLabel.setText(message);
        resultLabel.setForeground(color);
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }

    private void openWelcomeWindow(String name) {
        JFrame welcome = new JFrame("🌈 Welcome! 🌈");
        welcome.setSize(450, 300);
        welcome.setLocationRelativeTo(null);
        welcome.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, Color.PINK, getWidth(), getHeight(), Color.ORANGE, true);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(Color.WHITE);
                g.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
                g.drawString("✨ Welcome, " + name + "! ✨", 50, 100);

                g.setColor(Color.YELLOW);
                g.fillOval(50, 150, 50, 50);
                g.setColor(Color.GREEN);
                g.fillRect(120, 150, 70, 50);
                g.setColor(Color.RED);
                g.drawLine(200, 150, 300, 220);
            }
        };

        welcome.add(panel);
        welcome.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationSystem::new);
    }
}
