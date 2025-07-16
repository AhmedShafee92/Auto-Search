package conncet.server.analyse.file;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;

public class TCPClientGUI extends JFrame {

    private JTextField inputField;
    private JTextArea responseArea;
    private JButton sendButton;

    public TCPClientGUI() {
        setTitle("AI Chat Client");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // GUI components
        inputField = new JTextField();
        sendButton = new JButton("Send");
        responseArea = new JTextArea();
        responseArea.setEditable(false);
        responseArea.setLineWrap(true);

        // Layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(responseArea), BorderLayout.CENTER);

        // Send button action
        sendButton.addActionListener((ActionEvent e) -> sendMessage());

        // Enter key listener
        inputField.addActionListener((ActionEvent e) -> sendMessage());
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
