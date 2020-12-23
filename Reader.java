import java.io.*;
import java.util.ArrayList;
class Reader{
    public static String[] read(String filename){
        ArrayList<String> lines = new ArrayList<String>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("inputs/"+filename));
            String line;
            while((line = br.readLine())!=null)
                lines.add(line);
            
        } catch(IOException e){ }
        
        String[] data = lines.toArray(new String[]{});
        return data;
    }
}

