package Task1.com.company.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InsertManager implements AutoCloseable {
    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
    private static InsertManager instance;


    private InsertManager() {}
    public static InsertManager getInstance() {
        if (instance == null) {
            instance = new InsertManager();
        }
        return instance;
    }


    public String InsertAndTrim(){
        String message;
        try{
            message=bufferedReader.readLine();
            if(message==null || message.equals("")){}// need Exception!!!!!!!
        } catch (IOException e) {
            System.out.println("exception1");
            message="Error! It's an error of insert, please restart the application"; //need to go to the last step?!!!!
            return null;
        }

        message=message.trim();

        return message;
    }

    @Override
    public void close() throws Exception {
        bufferedReader.close();
    }
}
