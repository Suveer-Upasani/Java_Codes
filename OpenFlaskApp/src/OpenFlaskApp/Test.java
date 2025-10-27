package OpenFlaskApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    private static final String FLASK_API_URL = "http://localhost:5005/api";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
    
    private JFrame frame;
    private JTextArea logArea;
    private JLabel statusLabel;
    private boolean isRecording = false;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Test().createAndShowGUI());
    }
    
    private void createAndShowGUI() {
        // Create main frame
        frame = new JFrame("Face Mesh Tracking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        
        // Create control panel with improved layout
        JPanel controlPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setBackground(new Color(240, 240, 240));
        
        // Create buttons with better styling
        JButton openButton = createStyledButton("Open Web View", new Color(70, 130, 180));
        JButton startButton = createStyledButton("Start Recording", new Color(76, 175, 80));
        JButton stopButton = createStyledButton("Stop Recording", new Color(244, 67, 54));
        JButton saveButton = createStyledButton("Save Data", new Color(255, 193, 7));
        JButton downloadButton = createStyledButton("Download", new Color(156, 39, 176));
        
        // Add action listeners
        openButton.addActionListener(e -> openWebView());
        startButton.addActionListener(e -> sendCommand("start"));
        stopButton.addActionListener(e -> sendCommand("stop"));
        saveButton.addActionListener(e -> saveData());
        downloadButton.addActionListener(e -> downloadData());
        
        // Add buttons to panel
        controlPanel.add(openButton);
        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(saveButton);
        controlPanel.add(downloadButton);
        
        // Create status panel with better visibility
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBackground(new Color(240, 240, 240));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        statusLabel = new JLabel("Status: Not connected");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setForeground(Color.BLACK);
        statusPanel.add(statusLabel);
        
        // Create log area with better readability
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        logArea.setForeground(Color.BLACK);
        logArea.setBackground(new Color(255, 255, 255));
        logArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Activity Log"));
        
        // Add components to frame
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(statusPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);
        
        // Initial status check
        checkServerStatus();
        
        // Center the window
        frame.setLocationRelativeTo(null);
        
        // Make it visible
        frame.setVisible(true);
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return button;
    }
    
    private void log(String message) {
        String timestamp = DATE_FORMAT.format(new Date());
        SwingUtilities.invokeLater(() -> {
            logArea.append("[" + timestamp + "] " + message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }
    
    private void checkServerStatus() {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    String response = sendGetRequest(FLASK_API_URL + "/get_config");
                    log("Server connection successful");
                    statusLabel.setText("Status: Connected to server");
                    statusLabel.setForeground(new Color(0, 100, 0)); // Dark green for success
                } catch (Exception e) {
                    log("Server connection failed: " + e.getMessage());
                    statusLabel.setText("Status: Connection failed");
                    statusLabel.setForeground(Color.RED);
                }
                return null;
            }
        }.execute();
    }
    
    private void openWebView() {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    Desktop.getDesktop().browse(new URI("http://localhost:5005"));
                    log("Opened web interface in browser");
                } catch (Exception ex) {
                    log("Failed to open browser: " + ex.getMessage());
                }
                return null;
            }
        }.execute();
    }
    
    private void sendCommand(String command) {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    String response = sendPostRequest(
                        FLASK_API_URL + "/send_command", 
                        "{\"command\":\"" + command + "\"}"
                    );
                    
                    if (command.equals("start")) {
                        isRecording = true;
                        log("Recording started successfully");
                    } else if (command.equals("stop")) {
                        isRecording = false;
                        log("Recording stopped successfully");
                    }
                    
                    log("Server response: " + response);
                } catch (Exception ex) {
                    log("Error sending command: " + ex.getMessage());
                }
                return null;
            }
        }.execute();
    }
    
    private void saveData() {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    String response = sendGetRequest(FLASK_API_URL + "/save_json");
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.getString("status").equals("success")) {
                        log("Data saved successfully to: " + jsonResponse.getString("file_path"));
                    } else {
                        log("Failed to save data: " + jsonResponse.getString("message"));
                    }
                } catch (Exception ex) {
                    log("Error saving data: " + ex.getMessage());
                }
                return null;
            }
        }.execute();
    }
    
    private void downloadData() {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    // Create a file chooser
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Specify where to save the file");
                    
                    // Show save dialog
                    int userSelection = fileChooser.showSaveDialog(frame);
                    
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        
                        // Download the file
                        URL website = new URL(FLASK_API_URL + "/download_json");
                        try (InputStream in = website.openStream();
                             FileOutputStream out = new FileOutputStream(fileToSave)) {
                            byte[] buffer = new byte[4096];
                            int bytesRead;
                            while ((bytesRead = in.read(buffer)) != -1) {
                                out.write(buffer, 0, bytesRead);
                            }
                        }
                        
                        log("File downloaded successfully to: " + fileToSave.getAbsolutePath());
                    }
                } catch (Exception ex) {
                    log("Error downloading data: " + ex.getMessage());
                }
                return null;
            }
        }.execute();
    }
    
    private String sendGetRequest(String urlString) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }
    
    private String sendPostRequest(String urlString, String jsonInput) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }
    
    // Simple JSON parser for handling responses
    private static class JSONObject {
        private final String json;
        
        public JSONObject(String json) {
            this.json = json;
        }
        
        public String getString(String key) {
            String pattern = "\"" + key + "\":\"(.*?)\"";
            java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
            java.util.regex.Matcher m = r.matcher(json);
            if (m.find()) {
                return m.group(1);
            }
            pattern = "\"" + key + "\":(\\d+)"; // For numbers
            r = java.util.regex.Pattern.compile(pattern);
            m = r.matcher(json);
            if (m.find()) {
                return m.group(1);
            }
            return "";
        }
    }
}