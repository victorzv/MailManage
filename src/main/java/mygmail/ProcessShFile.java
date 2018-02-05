package mygmail;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessShFile {

    public static String SH_FILE = "";
    public static String SH_FILE_DIR = "";

    private List<String> body = new ArrayList<>();

    public ProcessShFile(List<String> body) {
        this.body = body;
    }

    public ProcessShFile(MyMessage message){
        this.body = message.getBody();
    }

    public boolean createFileSh(){
        boolean flagStart = false;
        File filecheck = new File(SH_FILE);
        if (filecheck.exists()){
            filecheck.delete();
            System.out.println("tmp.sh was deleted");
        }
        try {
            File file = new File(SH_FILE);
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write("#!/bin/sh");
            writer.newLine();
            for(String line : body){
                if (flagStart && !line.equals("END")){
                    writer.write(line);
                    writer.newLine();
                }
                if (line.equals("BEGIN")){
                    flagStart = true;
                }
                if (line.equals("END")){
                    writer.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (flagStart){
            return true;
        }
        return false;
    }
}
