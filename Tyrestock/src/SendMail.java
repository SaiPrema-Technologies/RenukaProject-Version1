import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;

import java.io.IOException;
import java.util.Date;
public class SendMail {

    public SendMail() throws MessagingException {
     /*   String host = "smtp.mail.yahoo.com";
        String Password = "jaihanuman21";
        String from = "harsha.kanthi@yahoo.com";
        String toAddress = "ishwar.kanthi@yahoo.in";
        String filename = "C:/Users/kanth_000/Desktop/Building Wireless Sensor Networks.pdf";
        // Get system properties
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, null);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, toAddress);
        message.setSubject("JavaMail Attachment");
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Here's the file");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        messageBodyPart = new MimeBodyPart();
        FileDataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        try {
            Transport tr = session.getTransport("smtps");
            tr.connect(host, from, Password);
            tr.sendMessage(message, message.getAllRecipients());
            System.out.println("Mail Sent Successfully");
            tr.close();
        } catch (SendFailedException sfe) {
            System.out.println(sfe);
        }*/
    	
   /* 	String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "kcishwar54@gmail.com";
        final String password = "malatikanthi";
 
        // message info
        String mailTo = "ahsrahkanthi@gmail.com";
        String subject = "New email with attachments";
        String message = "I have some attachments for you.";
 
        // attachments
        String[] attachFiles = new String[2];
        attachFiles[0] = "C:/Users/kanth_000/Desktop/trial.docx";
        attachFiles[1] = "C:/Users/kanth_000/Desktop/images.jpg";
        //attachFiles[2] = "e:/Test/Video.mp4";
 
        try {
            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                subject, message, attachFiles);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }
    public static void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message, String[] attachFiles)
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
                
                protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
                    return new javax.mail.PasswordAuthentication(userName, password);
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
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }*/
    
    	String to="pripatil637@gmail.com";//change accordingly  
    	  final String user="techsaiprema@gmail.com";//change accordingly  
    	  final String password="techsaiprema123";//change accordingly  
    	   
    	  //1) get the session object     
    	  Properties properties = System.getProperties();  
    	  properties.setProperty("mail.smtp.host", "mail.javatpoint.com");  
    	  properties.put("mail.smtp.auth", "true");  
    	  
    	  Session session = Session.getDefaultInstance(properties,  
    	   new javax.mail.Authenticator() {  
    		  protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
                  return new javax.mail.PasswordAuthentication(user, password);
    	   //protected PasswordAuthentication getPasswordAuthentication() {  
    	  // return new PasswordAuthentication(user,password);  
    	   }  
    	  });  
    	     
    	  //2) compose message     
    	  try{  
    	    MimeMessage message = new MimeMessage(session);  
    	    message.setFrom(new InternetAddress(user));  
    	    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
    	    message.setSubject("Message Aleart");  
    	      
    	    //3) create MimeBodyPart object and set your message text     
    	    BodyPart messageBodyPart1 = new MimeBodyPart();  
    	    messageBodyPart1.setText("This is message body");  
    	      
    	    //4) create new MimeBodyPart object and set DataHandler object to this object      
    	    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
    	  
    	    String filename = "/Users/ashokmagadum/Desktop/untitled folder";//change accordingly  
    	    FileDataSource source = new FileDataSource(filename);  
    	    messageBodyPart2.setDataHandler(new DataHandler(source));  
    	    messageBodyPart2.setFileName(filename);  
    	     
    	     
    	    //5) create Multipart object and add MimeBodyPart objects to this object      
    	    Multipart multipart = new MimeMultipart();  
    	    multipart.addBodyPart(messageBodyPart1);  
    	    multipart.addBodyPart(messageBodyPart2);  
    	  
    	    //6) set the multiplart object to the message object  
    	    message.setContent(multipart );  
    	     
    	    //7) send message  
    	    Transport.send(message);  
    	   
    	   System.out.println("message sent....");  
    	   }catch (MessagingException ex) {ex.printStackTrace();}  
    	 }  
    
}