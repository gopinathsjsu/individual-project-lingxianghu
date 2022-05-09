import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

public class getError {
    private ArrayList<String> returnError;
    public getError(ArrayList<String> arrayList) {
        returnError = arrayList;
    }
    public void process(){
        try{
            FileWriter w = new FileWriter("Errors.txt");
            w.write("Please correct quantities.\n");
            for(String str : returnError) {
                String[] ary = str.split("~");
                w.write(ary[0] + ", " + ary[1] + "\n");
            }
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
