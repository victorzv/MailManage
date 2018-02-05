package mygmail;

import javax.mail.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by KVP on 13.04.2017.
 */
public class ReadEmail {

    private String protocol = "imaps";
    private String host = "imap.gmail.com";
    private String user = ""; // the email which will use for get incomming commands
    private String password = ""; // your password

    private Session session;
    private Store store;
    private Folder folder;

    public ReadEmail(){
        init();
    }

    public ReadEmail(String protocol, String host, String user, String password){
        this.protocol = protocol;
        this.host = host;
        this.user = user;
        this.password = password;
        init();
    }

    public void logOut(){
        try {
            folder.close(false);
            store.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void init(){
        try{
            Properties props = System.getProperties();
            session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });
            store = session.getStore(protocol);
            store.connect(host, -1, user, password);
            folder = store.getDefaultFolder();
            if (folder == null){
                System.out.println("folder is null");
                return;
            }
            folder = folder.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public int getLastMessage(){
        try {
            return folder.getMessageCount();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Message getMessage(int n){
        try {
            return folder.getMessage(n);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Message[] getMessages(){
        try {
            return folder.getMessages();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
