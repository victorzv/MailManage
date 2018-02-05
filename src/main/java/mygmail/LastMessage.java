package mygmail;

import java.io.*;

/**
 * Created by KVP on 14.04.2017.
 */
public class LastMessage {

    public static String FILE_PATH = "";
    private File file = null;

    public void setLastNumber(int lastNumber) {
        this.lastNumber = lastNumber;
    }

    private int lastNumber = -1;
    boolean flag = false;

    public LastMessage(){
        init();
    }

    public void init(){
        file = new File(FILE_PATH + "lastIDLetter.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error create file");
            }
        }
    }

    public void saveLastNumber(){
        try {
            if(file.exists()){
                file.delete();
                init();
            }
            FileOutputStream out = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(String.valueOf(lastNumber));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getLastNumber(){
        try {
            InputStream in = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            flag = false;
            while ((line = reader.readLine()) != null){
                flag = true;
                lastNumber = Integer.parseInt(line);
                reader.close();
                break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (flag){
            return lastNumber;
        }

        return 0;
    }
}
