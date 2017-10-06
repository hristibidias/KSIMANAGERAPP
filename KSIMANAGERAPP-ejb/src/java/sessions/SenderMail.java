/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Hristi
 */
@Stateless
@LocalBean
public class SenderMail {
    public void sendEmail(String fromEmail, String username, String password, String toEmail, String subject, String message){
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.user", fromEmail);
            props.put("mail.smtp.password", password);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            
            
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(true);
            
            Message mailMessage = new MimeMessage(mailSession);
            mailMessage.setFrom(new InternetAddress(fromEmail));
            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mailMessage.setText(message);
            mailMessage.setSubject(subject);
            
            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com",fromEmail,password);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            transport.close();
            System.out.println("MESSAGE ENVOYE !");
        } catch (Exception ex) {
            Logger.getLogger(sessions.MailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
