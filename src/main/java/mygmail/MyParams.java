package mygmail;

import java.util.Date;

/**
 * Created by KVP on 04.04.2017.
 */
public class MyParams {
    private String email;
    private String command;
    private Date emailDate;

    @Override
    public String toString() {
        return "MyParams{" +
                "email='" + email + '\'' +
                ", command='" + command + '\'' +
                ", emailDate=" + emailDate +
                '}';
    }

    public MyParams() {
        command = "-1";
    }

    public Date getEmailDate() {
        return emailDate;
    }

    public void setEmailDate(Date emailDate) {
        this.emailDate = emailDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
