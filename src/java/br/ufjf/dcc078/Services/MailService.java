package br.ufjf.dcc078.Services;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
    
    private static final String MAIL_ADDRESS = "tpaaesufjf@gmail.com";  
    
    private static final String MAIL_PASSWORD = "ufjf2018"; 
    
    private static MailService instance = new MailService();
    
    private MailService(){};
    
    public static MailService getInstance(){
        return instance;
    }
    
    private Properties getConfigurationProperties(){
        Properties props = new Properties();
        
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        
        return props;
    }
    
    private Session openSession(Properties props){
        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() 
                {
                   return new PasswordAuthentication(MAIL_ADDRESS, MAIL_PASSWORD);
                }
            });

        session.setDebug(true);  
        
        return session;
    }
    
    public void send(String recipient, String subject, String body){
        
        try { 
            
            Message mail = new MimeMessage(this.openSession(this.getConfigurationProperties()));
            
            mail.setFrom(new InternetAddress(MAIL_ADDRESS));

            Address[] toUser = InternetAddress 
                       .parse(recipient);  

            mail.setRecipients(Message.RecipientType.TO, toUser);
            mail.setSubject(subject);
            mail.setText(body);
            
            Transport.send(mail);

            System.out.println("E-mail enviado! \n Assunto: " + subject + "  \n Corpo da mensagem: " + body);
 
        } catch (MessagingException e) {
             throw new RuntimeException(e);
        }
    }
}
