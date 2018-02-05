package mygmail;

import org.jsoup.Jsoup;

import javax.mail.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by KVP on 14.04.2017.
 */
public class MyMessage {

    private boolean textIsHtml = false;

    private String email;
    private String subject;
    private Date date;
    private int messageNumber;
    private List<String> body = new ArrayList<>();

    public MyMessage(Message m){
        try {
            Address[] addresses = m.getFrom();
            String fullEmail = addresses[0].toString();
            email = fullEmail.substring(fullEmail.indexOf("<") + 1, fullEmail.lastIndexOf(">"));
            date = m.getSentDate();
            subject = m.getSubject();
            messageNumber = m.getMessageNumber();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        String content = null;
        try {
            content = getText(m);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lines = Jsoup.parse(content).text().split("#");
        for(String line : lines){
            body.add(line.trim());
        }
    }

    public List<String> getBody() {
        return body;
    }
    public int getMessageNumber() {
        return messageNumber;
    }
    public String getEmail() {
        return email;
    }
    public String getSubject() {
        return subject;
    }
    public Date getDate() {
        return date;
    }

    private String getText(Part p) throws MessagingException, IOException {
        if (p.isMimeType("text/*")) {
            String s = (String)p.getContent();
            textIsHtml = p.isMimeType("text/html");
            return s;
        }

        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart)p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                    continue;
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart)p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }

        return null;
    }

}
