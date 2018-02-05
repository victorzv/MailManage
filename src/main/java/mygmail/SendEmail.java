package mygmail;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public final class SendEmail {
    private SendEmail(){}

    public static void Send(String email, String subject, String outTrue, String outError){

        Properties props = System.getProperties();

        props.put("mail.smtp.starttls.enable", "true"); // added this line
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.user", "mail@email.com");
        props.put("mail.smtp.password", "password");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        try{

            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText("out:\r\n" + outTrue + "\r\nerrors:\r\n" + outError);
            msg.setSubject("RE: " + subject);
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            Transport.send(msg);
            System.out.println("send!");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
