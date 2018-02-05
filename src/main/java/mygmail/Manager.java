package mygmail;

import javax.mail.Message;
import java.io.File;
import java.util.Scanner;

import static mygmail.ProcessShFile.SH_FILE;
import static mygmail.ProcessShFile.SH_FILE_DIR;

public class Manager{

    private static boolean flagEnd = false;

    public static void run(){
        int numberLastMessage = -1;
        ReadEmail reader = null;

            try{
                LastMessage lastMessage = new LastMessage();
                numberLastMessage = lastMessage.getLastNumber();

                reader = new ReadEmail();

                int nlastMessage = reader.getLastMessage();
                System.out.print("Last read: " + numberLastMessage + " Last in email: " + nlastMessage + "\r");

                if (numberLastMessage == nlastMessage){
                    reader.logOut();
                    return;
                }

                lastMessage.setLastNumber(nlastMessage);
                lastMessage.saveLastNumber();

                MyMessage myMessage = new MyMessage(reader.getMessage(nlastMessage));
                if (myMessage.getEmail().equals("account.gmail@gmail.com") || myMessage.getEmail().equals("email@email.com")) { // check email which can send commands

                    ProcessShFile processShFile = new ProcessShFile(myMessage);

                    if (!processShFile.createFileSh()) {
                        System.out.println();
                        System.out.println("***********************************************************");
                        System.out.println("Not found commands for SH file in the message body.");
                        System.out.println("Message from: " + myMessage.getEmail());
                        System.out.println("Message subject: " + myMessage.getSubject());
                        System.out.println("Message body:");
                        myMessage.getBody().forEach(System.out::println);
                        System.out.println("***********************************************************");
                        System.out.println();
                        reader.logOut();
                        return;
                    }
                    CommandExecutor command = new CommandExecutor("chmod 777 " + SH_FILE, SH_FILE_DIR);
                    MyOutput myOutput = new MyOutput();
                    command.doCommand(myOutput);
                    System.out.println();
                    System.out.println("***********************************************************");
                    System.out.println("Begin create SH file from the message body.");
                    System.out.println("Message from: " + myMessage.getEmail());
                    System.out.println("Message subject: " + myMessage.getSubject());
                    System.out.println("Message body:");
                    myMessage.getBody().forEach(System.out::println);

                    System.out.println("Create sh file...");
                    System.out.println("myscript.sh created.");
                    System.out.println();

                    command = new CommandExecutor("./myscript.sh", SH_FILE_DIR);
                    myOutput = new MyOutput();
                    command.doCommand(myOutput);
                    if (myOutput.getOutput().length() > 0) {
                        System.out.println("output:");
                        System.out.println(myOutput.getOutput());
                    }
                    if (myOutput.getError().length() > 0) {
                        System.out.println("error:");
                        System.out.println(myOutput.getError());
                    }
                    System.out.println("***********************************************************");
                    System.out.println();

                    SendEmail.Send(myMessage.getEmail(), "RE: " + myMessage.getSubject(), myOutput.getOutput(), myOutput.getError());
                }//if (myMessage.getEmail().equals("epirogov@windowslive.com") || myMessage.getEmail().equals("kaplantigra@gmail.com"))

                reader.logOut();
                Thread.sleep(1000 * 60);

            }catch (Exception e){

                LastMessage lastMessage = new LastMessage();
                lastMessage.setLastNumber(numberLastMessage);

                if (reader != null) {
                    reader.logOut();
                }
            }
    }

    public static void main(String[] args) {
        File f = new File(System.getProperty("java.class.path"));
        File dir = f.getAbsoluteFile().getParentFile();
        String path = dir.toString();
        ProcessShFile.SH_FILE_DIR = path + "/";
        ProcessShFile.SH_FILE = ProcessShFile.SH_FILE_DIR + "myscript.sh";
        LastMessage.FILE_PATH = ProcessShFile.SH_FILE_DIR;

        while (true){
            try{
                run();
            }catch (Exception e){
                run();
            }
            continue;
        }
    }
}
