
package Codes;


import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExamSystem {
    // Admin credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "BforBall@2025";
    
    // In-memory data storage
    private static Map<String, User> users = new HashMap<>();
    private static Map<Long, Test> tests = new HashMap<>();
    private static List<TestResult> testResults = new ArrayList<>();
    private static List<ViolationRecord> violations = new ArrayList<>();
    
    // Session tracking
    private static String currentUsername = null;
    private static String currentRole = null;
    private static String currentPRN = null;
    
    // Violation tracking for current test
    private static int currentTestViolations = 0;
    private static boolean testTerminated = false;
    private static List<ViolationRecord> currentViolations = new ArrayList<>();
    private static final int MAX_VIOLATIONS = 3;
    
    // Test tracking
    private static Map<Integer, String> correctAnswers = new HashMap<>();
    private static Long currentTestId = null;
    private static Integer testDuration = null;
    
    // ID counters
    private static long userIdCounter = 1;
    private static long testIdCounter = 1;
    private static long resultIdCounter = 1;
    
    public static void main(String[] args) {
        initializeSampleData();
        showMainMenu();
    }
    
    private static void initializeSampleData() {
        // Add some sample users
        users.put("student1", new User(userIdCounter++, "student1", "password", "student1@example.com", "1234567890", "John Doe"));
        users.put("student2", new User(userIdCounter++, "student2", "password", "student2@example.com", "0987654321", "Jane Smith"));
        
        // Add some sample tests
        List<Question> questions1 = Arrays.asList(
            new Question("Which keyword is used to inherit a class in Java?",
                Arrays.asList("extends", "implements", "inherits", "super"), "extends"),
            new Question("What is the default value of boolean variable in Java?",
                Arrays.asList("true", "false", "0", "null"), "false"),
            new Question("Which method is the entry point of Java program?",
                Arrays.asList("main", "start", "run", "init"), "main")
        );
        
        List<Question> questions2 = Arrays.asList(
            new Question("What is the size of int in Java?",
                Arrays.asList("16-bit", "32-bit", "64-bit", "128-bit"), "32-bit"),
            new Question("Which collection implements Queue interface?",
                Arrays.asList("ArrayList", "LinkedList", "PriorityQueue", "HashSet"), "PriorityQueue"),
            new Question("Which keyword is used for exception handling?",
                Arrays.asList("try", "attempt", "error", "catch"), "try")
        );
        
        tests.put(testIdCounter, new Test(testIdCounter++, "Java Basics", "Java", 30, questions1, true));
        tests.put(testIdCounter, new Test(testIdCounter++, "Java Advanced", "Java", 45, questions2, true));
        
        // Add some sample results
        testResults.add(new TestResult(resultIdCounter++, "student1", "1234567890", 1L, 2, 3, 0, 
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), false));
        testResults.add(new TestResult(resultIdCounter++, "student2", "0987654321", 2L, 3, 3, 1, 
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), false));
    }
    
    private static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n===== Online Exam Proctoring System =====");
            System.out.println("1. Student Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");
            
            int choice = getIntInput(scanner, 1, 3);
            
            switch (choice) {
                case 1:
                    studentLogin(scanner);
                    break;
                case 2:
                    adminLogin(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using the Exam System. Goodbye!");
                    return;
            }
        }
    }
    
    private static void studentLogin(Scanner scanner) {
        System.out.println("\n----- Student Login -----");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("PRN: ");
        String prn = scanner.nextLine();
        
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password) && user.getPrn().equals(prn)) {
            currentUsername = username;
            currentRole = "student";
            currentPRN = prn;
            System.out.println("Login successful! Welcome, " + user.getFullName());
            showStudentDashboard(scanner);
        } else {
            System.out.println("Invalid login credentials!");
        }
    }
    
    private static void adminLogin(Scanner scanner) {
        System.out.println("\n----- Admin Login -----");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            currentUsername = username;
            currentRole = "admin";
            System.out.println("Admin login successful!");
            showAdminDashboard(scanner);
        } else {
            System.out.println("Invalid admin credentials!");
        }
    }
    
    private static void showStudentDashboard(Scanner scanner) {
        while (true) {
            System.out.println("\n----- Student Dashboard -----");
            System.out.println("1. View Available Tests");
            System.out.println("2. View Previous Results");
            System.out.println("3. Take a Test");
            System.out.println("4. Logout");
            System.out.print("Please choose an option: ");
            
            int choice = getIntInput(scanner, 1, 4);
            
            switch (choice) {
                case 1:
                    viewAvailableTests(scanner);
                    break;
                case 2:
                    viewPreviousResults(scanner);
                    break;
                case 3:
                    takeTest(scanner);
                    break;
                case 4:
                    logout();
                    return;
            }
        }
    }
    
    private static void viewAvailableTests(Scanner scanner) {
        System.out.println("\n----- Available Tests -----");
        for (Test test : tests.values()) {
            if (test.isActive()) {
                System.out.println("ID: " + test.getId() + " | Name: " + test.getTestName() + 
                                 " | Subject: " + test.getSubject() + 
                                 " | Duration: " + test.getDurationMinutes() + " minutes");
            }
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void viewPreviousResults(Scanner scanner) {
        System.out.println("\n----- Your Previous Results -----");
        boolean found = false;
        
        for (TestResult result : testResults) {
            if (result.getPrn().equals(currentPRN)) {
                found = true;
                Test test = tests.get(result.getTestId());
                System.out.println("Test: " + test.getTestName() + 
                                 " | Score: " + result.getScore() + "/" + result.getTotal() +
                                 " | Violations: " + result.getViolations() +
                                 " | Date: " + result.getSubmittedAt() +
                                 (result.isTerminated() ? " (Terminated)" : ""));
            }
        }
        
        if (!found) {
            System.out.println("No results found.");
        }
        
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void takeTest(Scanner scanner) {
        System.out.println("\n----- Take a Test -----");
        System.out.print("Enter Test ID: ");
        long testId = getLongInput(scanner);
        
        Test test = tests.get(testId);
        if (test == null || !test.isActive()) {
            System.out.println("Test not found or inactive!");
            return;
        }
        
        // Reset violation tracking
        currentTestViolations = 0;
        testTerminated = false;
        currentViolations.clear();
        correctAnswers.clear();
        currentTestId = testId;
        testDuration = test.getDurationMinutes();
        
        // Shuffle questions and options
        List<Question> questions = new ArrayList<>(test.getQuestions());
        Collections.shuffle(questions, new Random());
        for (Question q : questions) {
            Collections.shuffle(q.getOptions(), new Random());
        }
        
        // Store correct answers
        for (int i = 0; i < questions.size(); i++) {
            correctAnswers.put(i, questions.get(i).getAnswer());
        }
        
        System.out.println("\nStarting test: " + test.getTestName());
        System.out.println("Duration: " + test.getDurationMinutes() + " minutes");
        System.out.println("Number of questions: " + questions.size());
        System.out.println("Press Enter to begin...");
        scanner.nextLine();
        
        // Display and answer questions
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + q.getQuestion());
            
            List<String> options = q.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }
            
            System.out.print("Your answer (1-" + options.size() + "): ");
            int answerIndex = getIntInput(scanner, 1, options.size()) - 1;
            String chosenAnswer = options.get(answerIndex);
            
            if (chosenAnswer.equals(q.getAnswer())) {
                score++;
            }
            
            // Simulate random violations (for demonstration)
            if (Math.random() < 0.2 && currentTestViolations < MAX_VIOLATIONS) {
                recordViolation("Multiple faces detected");
            }
            
            if (testTerminated) {
                System.out.println("Test terminated due to violations!");
                break;
            }
        }
        
        // Save result
        TestResult result = new TestResult(
            resultIdCounter++, 
            currentUsername, 
            currentPRN, 
            testId, 
            score, 
            questions.size(), 
            currentTestViolations,
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            testTerminated
        );
        testResults.add(result);
        
        // Show result
        System.out.println("\n----- Test Results -----");
        System.out.println("Score: " + score + "/" + questions.size());
        System.out.println("Violations: " + currentTestViolations);
        if (testTerminated) {
            System.out.println("Test was terminated due to excessive violations!");
        }
        
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void showAdminDashboard(Scanner scanner) {
        while (true) {
            System.out.println("\n----- Admin Dashboard -----");
            System.out.println("1. User Management");
            System.out.println("2. Test Management");
            System.out.println("3. View Results");
            System.out.println("4. View Statistics");
            System.out.println("5. Logout");
            System.out.print("Please choose an option: ");
            
            int choice = getIntInput(scanner, 1, 5);
            
            switch (choice) {
                case 1:
                    manageUsers(scanner);
                    break;
                case 2:
                    manageTests(scanner);
                    break;
                case 3:
                    viewResults(scanner);
                    break;
                case 4:
                    viewStatistics(scanner);
                    break;
                case 5:
                    logout();
                    return;
            }
        }
    }
    
    private static void manageUsers(Scanner scanner) {
        while (true) {
            System.out.println("\n----- User Management -----");
            System.out.println("1. View All Users");
            System.out.println("2. Create User");
            System.out.println("3. Delete User");
            System.out.println("4. Back to Admin Dashboard");
            System.out.print("Please choose an option: ");
            
            int choice = getIntInput(scanner, 1, 4);
            
            switch (choice) {
                case 1:
                    viewAllUsers(scanner);
                    break;
                case 2:
                    createUser(scanner);
                    break;
                case 3:
                    deleteUser(scanner);
                    break;
                case 4:
                    return;
            }
        }
    }
    
    private static void viewAllUsers(Scanner scanner) {
        System.out.println("\n----- All Users -----");
        for (User user : users.values()) {
            System.out.println("PRN: " + user.getPrn() + 
                             " | Username: " + user.getUsername() +
                             " | Email: " + user.getEmail() +
                             " | Full Name: " + user.getFullName());
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void createUser(Scanner scanner) {
        System.out.println("\n----- Create User -----");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("PRN (10 digits): ");
        String prn = scanner.nextLine();
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();
        
        if (!prn.matches("\\d{10}")) {
            System.out.println("PRN must be exactly 10 digits!");
            return;
        }
        
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
            return;
        }
        
        // Check if PRN already exists
        for (User user : users.values()) {
            if (user.getPrn().equals(prn)) {
                System.out.println("PRN already exists!");
                return;
            }
        }
        
        users.put(username, new User(userIdCounter++, username, password, email, prn, fullName));
        System.out.println("User created successfully!");
    }
    
    private static void deleteUser(Scanner scanner) {
        System.out.println("\n----- Delete User -----");
        System.out.print("Enter PRN of user to delete: ");
        String prn = scanner.nextLine();
        
        User userToDelete = null;
        for (User user : users.values()) {
            if (user.getPrn().equals(prn)) {
                userToDelete = user;
                break;
            }
        }
        
        if (userToDelete != null) {
            users.remove(userToDelete.getUsername());
            
            // Remove user's results
            testResults.removeIf(result -> result.getPrn().equals(prn));
            
            // Remove user's violations
            violations.removeIf(violation -> violation.getPrn().equals(prn));
            
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User not found!");
        }
    }
    
    private static void manageTests(Scanner scanner) {
        while (true) {
            System.out.println("\n----- Test Management -----");
            System.out.println("1. View All Tests");
            System.out.println("2. Create Test");
            System.out.println("3. Toggle Test Status");
            System.out.println("4. Back to Admin Dashboard");
            System.out.print("Please choose an option: ");
            
            int choice = getIntInput(scanner, 1, 4);
            
            switch (choice) {
                case 1:
                    viewAllTests(scanner);
                    break;
                case 2:
                    createTest(scanner);
                    break;
                case 3:
                    toggleTestStatus(scanner);
                    break;
                case 4:
                    return;
            }
        }
    }
    
    private static void viewAllTests(Scanner scanner) {
        System.out.println("\n----- All Tests -----");
        for (Test test : tests.values()) {
            System.out.println("ID: " + test.getId() + 
                             " | Name: " + test.getTestName() +
                             " | Subject: " + test.getSubject() +
                             " | Duration: " + test.getDurationMinutes() + " minutes" +
                             " | Status: " + (test.isActive() ? "Active" : "Inactive") +
                             " | Questions: " + test.getQuestions().size());
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void createTest(Scanner scanner) {
        System.out.println("\n----- Create Test -----");
        System.out.print("Test Name: ");
        String testName = scanner.nextLine();
        System.out.print("Subject: ");
        String subject = scanner.nextLine();
        System.out.print("Duration (minutes): ");
        int duration = getIntInput(scanner, 1, 300);
        
        List<Question> questions = new ArrayList<>();
        System.out.println("Now let's add questions. Type 'done' when finished.");
        
        while (true) {
            System.out.print("Question: ");
            String questionText = scanner.nextLine();
            
            if ("done".equalsIgnoreCase(questionText) && !questions.isEmpty()) {
                break;
            }
            
            if (questionText.isEmpty()) {
                System.out.println("Question cannot be empty!");
                continue;
            }
            
            List<String> options = new ArrayList<>();
            for (int i = 1; i <= 4; i++) {
                System.out.print("Option " + i + ": ");
                String option = scanner.nextLine();
                if (option.isEmpty()) {
                    System.out.println("Option cannot be empty!");
                    i--;
                    continue;
                }
                options.add(option);
            }
            
            System.out.print("Correct answer (1-4): ");
            int correctIndex = getIntInput(scanner, 1, 4) - 1;
            String correctAnswer = options.get(correctIndex);
            
            questions.add(new Question(questionText, options, correctAnswer));
            System.out.println("Question added! Type 'done' to finish or continue adding questions.");
        }
        
        tests.put(testIdCounter, new Test(testIdCounter++, testName, subject, duration, questions, true));
        System.out.println("Test created successfully with " + questions.size() + " questions!");
    }
    
    private static void toggleTestStatus(Scanner scanner) {
        System.out.println("\n----- Toggle Test Status -----");
        System.out.print("Enter Test ID: ");
        long testId = getLongInput(scanner);
        
        Test test = tests.get(testId);
        if (test != null) {
            test.setActive(!test.isActive());
            System.out.println("Test " + (test.isActive() ? "activated" : "deactivated") + " successfully!");
        } else {
            System.out.println("Test not found!");
        }
    }
    
    private static void viewResults(Scanner scanner) {
        System.out.println("\n----- View Results -----");
        System.out.print("Enter PRN to filter (or press Enter for all): ");
        String filterPrn = scanner.nextLine();
        
        System.out.println("\n----- Test Results -----");
        boolean found = false;
        
        for (TestResult result : testResults) {
            if (filterPrn.isEmpty() || result.getPrn().equals(filterPrn)) {
                found = true;
                Test test = tests.get(result.getTestId());
                User user = null;
                for (User u : users.values()) {
                    if (u.getPrn().equals(result.getPrn())) {
                        user = u;
                        break;
                    }
                }
                
                System.out.println("ID: " + result.getId() +
                                 " | Student: " + (user != null ? user.getFullName() : result.getUsername()) +
                                 " | Test: " + (test != null ? test.getTestName() : result.getTestId()) +
                                 " | Score: " + result.getScore() + "/" + result.getTotal() +
                                 " | Violations: " + result.getViolations() +
                                 " | Date: " + result.getSubmittedAt() +
                                 (result.isTerminated() ? " (Terminated)" : ""));
            }
        }
        
        if (!found) {
            System.out.println("No results found.");
        }
        
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void viewStatistics(Scanner scanner) {
        System.out.println("\n----- System Statistics -----");
        System.out.println("Total Users: " + users.size());
        System.out.println("Total Tests: " + tests.size());
        System.out.println("Total Results: " + testResults.size());
        
        // Calculate average score
        if (!testResults.isEmpty()) {
            double totalScore = 0;
            double totalPossible = 0;
            for (TestResult result : testResults) {
                totalScore += result.getScore();
                totalPossible += result.getTotal();
            }
            double avgScore = (totalScore / totalPossible) * 100;
            System.out.println("Average Score: " + String.format("%.2f", avgScore) + "%");
        }
        
        // Count violations
        int totalViolations = 0;
        for (TestResult result : testResults) {
            totalViolations += result.getViolations();
        }
        System.out.println("Total Violations: " + totalViolations);
        
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void logout() {
        currentUsername = null;
        currentRole = null;
        currentPRN = null;
        System.out.println("Logged out successfully.");
    }
    
    private static void recordViolation(String type) {
        currentTestViolations++;
        ViolationRecord violation = new ViolationRecord();
        violation.setType(type);
        violation.setTimestamp(System.currentTimeMillis() / 1000.0);
        violation.setReadableTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        violation.setUsername(currentUsername);
        violation.setPrn(currentPRN);
        
        currentViolations.add(violation);
        violations.add(violation);
        
        System.out.println("VIOLATION: " + type + " (Total: " + currentTestViolations + "/" + MAX_VIOLATIONS + ")");
        
        if (currentTestViolations >= MAX_VIOLATIONS) {
            testTerminated = true;
            System.out.println("MAXIMUM VIOLATIONS REACHED! TEST TERMINATED!");
        }
    }
    
    // Utility methods for input validation
    private static int getIntInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    
    private static long getLongInput(Scanner scanner) {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}

// Data classes
class User {
    private long id;
    private String username;
    private String password;
    private String email;
    private String prn;
    private String fullName;
    
    public User(long id, String username, String password, String email, String prn, String fullName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.prn = prn;
        this.fullName = fullName;
    }
    
    // Getters and setters
    public long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getPrn() { return prn; }
    public String getFullName() { return fullName; }
}

class Test {
    private long id;
    private String testName;
    private String subject;
    private int durationMinutes;
    private List<Question> questions;
    private boolean active;
    
    public Test(long id, String testName, String subject, int durationMinutes, List<Question> questions, boolean active) {
        this.id = id;
        this.testName = testName;
        this.subject = subject;
        this.durationMinutes = durationMinutes;
        this.questions = questions;
        this.active = active;
    }
    
    // Getters and setters
    public long getId() { return id; }
    public String getTestName() { return testName; }
    public String getSubject() { return subject; }
    public int getDurationMinutes() { return durationMinutes; }
    public List<Question> getQuestions() { return questions; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

class Question {
    private String question;
    private List<String> options;
    private String answer;
    
    public Question(String question, List<String> options, String answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }
    
    // Getters
    public String getQuestion() { return question; }
    public List<String> getOptions() { return options; }
    public String getAnswer() { return answer; }
}

class TestResult {
    private long id;
    private String username;
    private String prn;
    private long testId;
    private int score;
    private int total;
    private int violations;
    private String submittedAt;
    private boolean terminated;
    
    public TestResult(long id, String username, String prn, long testId, int score, int total, int violations, String submittedAt, boolean terminated) {
        this.id = id;
        this.username = username;
        this.prn = prn;
        this.testId = testId;
        this.score = score;
        this.total = total;
        this.violations = violations;
        this.submittedAt = submittedAt;
        this.terminated = terminated;
    }
    
    // Getters
    public long getId() { return id; }
    public String getUsername() { return username; }
    public String getPrn() { return prn; }
    public long getTestId() { return testId; }
    public int getScore() { return score; }
    public int getTotal() { return total; }
    public int getViolations() { return violations; }
    public String getSubmittedAt() { return submittedAt; }
    public boolean isTerminated() { return terminated; }
}

class ViolationRecord {
    private String type;
    private double timestamp;
    private String readableTime;
    private String username;
    private String prn;
    
    // Getters and setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getTimestamp() { return timestamp; }
    public void setTimestamp(double timestamp) { this.timestamp = timestamp; }
    public String getReadableTime() { return readableTime; }
    public void setReadableTime(String readableTime) { this.readableTime = readableTime; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPrn() { return prn; }
    public void setPrn(String prn) { this.prn = prn; }
}