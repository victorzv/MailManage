package mygmail;

/**
 * Created by KVP on 14.04.2017.
 */
public class MyOutput {
    private String output;
    private String error;

    public MyOutput(){}

    public MyOutput(String output, String error){
        this.output = output;
        this.error = error;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
