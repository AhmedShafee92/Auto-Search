package first.option.forsendcv;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.FlagTerm;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class EmailManagementSystem 
{

    private static final String EXCEL_FILE = "emailDatabase.xlsx";
    private static final String FORWARD_EMAIL = "user@example.com"; // The email to forward positive emails
    private static final long DATABASE_CLEARED_INTERVAL = TimeUnit.DAYS.toMillis(75);  // 2.5 months (approx. 75 days)
    private static final long EMAIL_INBOX_READING_INTERVAL = TimeUnit.DAYS.toMillis(1);  // (EVERYDAY days)

    private Map<String, String> emailDatabase = new HashMap<>();

    public static void main(String[] args) throws Exception 
    {
        EmailManagementSystem system = new EmailManagementSystem();
        system.loadEmailDatabase();
        system.processInbox();
        system.cleanupDatabase();
    }

    // Load the email database from Excel
    // TODO : not part of the management should delete this part of the code .  
    private void loadEmailDatabase() throws IOException 
    {
        FileInputStream fis = new FileInputStream(EXCEL_FILE);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip the header row
            String email = row.getCell(0).getStringCellValue();
            String status = row.getCell(1).getStringCellValue();
            emailDatabase.put(email, status);
        }
        fis.close();
    }

    // Process the inbox email's
    // This the core process for the management.
    // TODO : should fix the code that working in the best way. 
    // Maybe should update the inbox email every day in specific time. 
    private void processInbox() throws Exception 
    {
        // Connect to the mail server (Gmail in this example)
        String host = "imap.gmail.com";
        String username = "your-email@gmail.com"; // Your email
        String password = "your-email-password"; // Your email password (or app password)

        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imap");
        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", "993");

        Session emailSession = Session.getDefaultInstance(properties);
        Store store = emailSession.getStore("imap");
        store.connect(username, password);

        
        // Here we read every email after the system connecting to the email . 
        // This is the management of the main email which is belong to the company domain. 
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

        // Get new messages
        // We should read just the unread messages and then decided if to keep the 
        // email message or to delete the message from the inbox.
        
        // Get unread messages (FlagTerm indicates only unread emails)
        FlagTerm unreadFlag = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        Message[] messages = inbox.search(unreadFlag);

        // here we print the number of the email's that unread.  
       // System.out.println("Unread Emails: " + messages.length);
        
        
        
         messages = inbox.getMessages();
        for (Message message : messages) {
            String subject = message.getSubject();
            String content = message.getContent().toString();
            String sender = ((InternetAddress) message.getFrom()[0]).getAddress();

            if (isPositiveEmail(content)) 
            {
                // Forward positive email to another email
                forwardEmail(message);
                // Update Excel file to mark email as positive
                updateEmailDatabase(sender, "positive");
            } else {
                // Mark the email as negative and delete it
                updateEmailDatabase(sender, "negative");
                message.setFlag(Flags.Flag.DELETED, true); // Delete the email
            }
        }
        inbox.close(true);
        store.close();
    }

    // Classify email as positive or negative based on content
    private boolean isPositiveEmail(String content) {
        // Simple rule: if email contains "good", "love", or "excellent", it's positive
    	//TODO: Here we should check the content with AI model that can decide if positive or not (change the code )
        return content.contains("good") || content.contains("love") || content.contains("excellent");
    }

    // Forward the email to another address (for positive emails)
    private void forwardEmail(Message message) throws Exception 
    {
        Session session = Session.getDefaultInstance(System.getProperties());
        MimeMessage forwardMessage = new MimeMessage(session);
        forwardMessage.setSubject("FWD: " + message.getSubject());
        forwardMessage.setFrom(new InternetAddress("your-email@gmail.com"));
        forwardMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(FORWARD_EMAIL));
        forwardMessage.setContent(message.getContent(), "text/plain");
        Transport.send(forwardMessage);
        System.out.println("Forwarded positive email to: " + FORWARD_EMAIL);
    }

    // Update the status of the email in the Excel database
    private void updateEmailDatabase(String email, String status) throws IOException {
        emailDatabase.put(email, status);
        System.out.println("Updated database with email: " + email + " - " + status);
        saveEmailDatabase();
    }

    // Save the updated email database to Excel
    private void saveEmailDatabase() throws IOException 
	{
        FileOutputStream fos = new FileOutputStream(EXCEL_FILE);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Emails");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Email");
        headerRow.createCell(1).setCellValue("Status");

        // Add data rows
        int rowNum = 1;
        for (Map.Entry<String, String> entry : emailDatabase.entrySet()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue());
        }

        workbook.write(fos);
        fos.close();
    }

    // Cleanup the database after 2.5 months (75 days)
    private void cleanupDatabase() throws IOException {
        File excelFile = new File(EXCEL_FILE);
        long lastUpdatedTime = excelFile.lastModified();
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastUpdatedTime > DATABASE_CLEARED_INTERVAL) {
            System.out.println("Clearing database after 2.5 months...");
            emailDatabase.clear();
            saveEmailDatabase();  // Save the cleared database
        }
    }
}
