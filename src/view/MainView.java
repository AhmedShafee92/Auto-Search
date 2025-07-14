package view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel 
{
    public JTextField linkedInEmail = new JTextField();
    public JPasswordField linkedInPassword = new JPasswordField();
    public JTextField emailUser = new JTextField();
    public JPasswordField passwordUser = new JPasswordField();

    public JButton startSearchingButton = new JButton("Start Searching");
    public JButton analysePersonalDataButton = new JButton("Analyzing Data");
    public JButton attachCVButton = new JButton("Attach File");
    public JButton viewPersonalDataButton = new JButton("Personal Data");

    public MainView() 
    {
        setLayout(null);
        setPreferredSize(new Dimension(727, 467));

        JLabel lblLinkedInEmail = new JLabel("LinkedIn_Email");
        lblLinkedInEmail.setBounds(203, 217, 94, 30);
        add(lblLinkedInEmail);
        linkedInEmail.setBounds(365, 101, 160, 30);
        add(linkedInEmail);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(205, 154, 114, 37);
        add(lblPassword);
        linkedInPassword.setBounds(365, 158, 160, 30);
        add(linkedInPassword);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(219, 97, 100, 30);
        add(lblEmail);
        emailUser.setBounds(365, 219, 160, 28);
        add(emailUser);

        passwordUser.setBounds(365, 264, 160, 30);
        add(passwordUser);

        JLabel lblLinkedinPassword = new JLabel("LinkedIn_Password");
        lblLinkedinPassword.setBounds(203, 257, 94, 34);
        add(lblLinkedinPassword);

        JLabel uploadCV = new JLabel("Upload Your CV");
        uploadCV.setBounds(49, 301, 100, 37);
        add(uploadCV);

        attachCVButton.setBounds(172, 301, 94, 37);
        add(attachCVButton);

        startSearchingButton.setBounds(437, 348, 146, 58);
        add(startSearchingButton);

        analysePersonalDataButton.setBounds(234, 348, 146, 58);
        add(analysePersonalDataButton);

        viewPersonalDataButton.setBounds(48, 353, 114, 49);
        add(viewPersonalDataButton);
    }
}
