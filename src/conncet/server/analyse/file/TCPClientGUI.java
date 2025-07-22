package conncet.server.analyse.file;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;

// TODO: should delete this file , will not be usage . 
public class TCPClientGUI extends JFrame {

    private JTextField inputField;
    private JTextArea responseArea;
    private JButton sendButton;
    private JButton clearButton; // New clear button

    public TCPClientGUI() {
        setTitle("AI Chat Client");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // GUI components
        inputField = new JTextField();
        sendButton = new JButton("Send");
        clearButton = new JButton("Clear"); // Initialize clear button
        responseArea = new JTextArea();
        responseArea.setEditable(false);
        responseArea.setLineWrap(true);

        // Top panel with input and send
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputField, BorderLayout.CENTER);
        topPanel.add(sendButton, BorderLayout.EAST);

        // Bottom panel with clear button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(clearButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(responseArea), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Send button action
        sendButton.addActionListener((ActionEvent e) -> sendMessage());

        // Enter key listener
        inputField.addActionListener((ActionEvent e) -> sendMessage());

        // Clear button action
        clearButton.addActionListener((ActionEvent e) -> responseArea.setText(""));
    }

    private void sendMessage() {
        String userInput = inputField.getText().trim();
        if (userInput.isEmpty()) {
            return;
        }

        try (Socket socket = new Socket("127.0.0.1", 5000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            writer.write(userInput);
            writer.newLine();
            writer.flush();

            String serverResponse = reader.readLine();

            responseArea.append("You: " + userInput + "\n");
            responseArea.append("AI: " + serverResponse + "\n\n");

            inputField.setText("");

        } catch (IOException ex) {
            responseArea.append("Connection error: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TCPClientGUI().setVisible(true);
        });
    }
}
