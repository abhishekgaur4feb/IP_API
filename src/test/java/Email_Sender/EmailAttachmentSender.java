package Email_Sender;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


 
public class EmailAttachmentSender {
	
    public static void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        //if (attachFiles != null && attachFiles.length > 0) {}
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
 
    /**
     * Test sending e-mail with attachments
     * @return 
     */
    public void testmail() {
        // SMTP info
    	 String host = "smtp.gmail.com";
         String port = "587";
         String mailFrom = "abhishekgaur4feb@gmail.com";
         String password = "Sparkle7@";
  
         // message info
         String mailTo = "alerts@rarecarat.com";
         //String mailTo = "abhishekgaur054@gmail.com";
         //mail.To.Add(mailTo);
         String subject = "IP API Service Status";
         String message = "Hi all,"+"<p/>"+"	IP API Service is Down.Kindly Check the Reason Behind."+ "<p/>"+"Regards"+ "<p/>"+"Abhishek Gaur";
         
         
         // attachments
         //String[] attachFiles = new String[1];
        // attachFiles[0] = "log/testlog.log";
         //attachFiles[1] = "e:/Test/Music.mp3";
        // attachFiles[2] = "e:/Test/Video.mp4";
  
        try {
            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                subject, message);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }
}