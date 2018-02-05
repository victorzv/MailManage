package mygmail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by KVP on 14.04.2017.
 */
public class CommandExecutor {
    private String command;
    private String pwd;

    public CommandExecutor(String command, String pwd){
        this.command = command;
        this.pwd = pwd;
    }

    private static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte buffer[] = new byte[8192];
        while (true)
        {
            int read = inputStream.read(buffer);
            if (read == -1)
            {
                break;
            }
            baos.write(buffer, 0, read);
        }
        return baos.toByteArray();
    }

    public int doCommand(MyOutput output){
        try {
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", command);
            pb.directory(new File(pwd));
            Process process = pb.start();

            String errorMessage = new String(toByteArray(process.getErrorStream()));
            String outputMessage = new String(toByteArray(process.getInputStream()));

            int exitValue  = 0;

            try {
                exitValue = process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("exitValue = " + exitValue);

            output.setError(errorMessage);
            output.setOutput(outputMessage);
            return exitValue;

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int doCommand(String command, String pwd, MyOutput output){
        this.command = command;
        this.pwd = pwd;
        return doCommand(output);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
