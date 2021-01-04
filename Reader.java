import java.io.*;
import java.util.ArrayList;
class Reader{
    public static String[] read(String filename){
        ArrayList<String> lines = new ArrayList<String>();
        // omg this "try with resources" improves speed so much
        try(BufferedReader br = new BufferedReader(new FileReader("inputs/"+filename));)
        {
            String line;
            while((line = br.readLine())!=null)
                lines.add(line);

        } catch(IOException e){ }

        String[] data = lines.toArray(new String[]{});

        return data;
    }
    public static String readToString(String filename){
      String lines="";

      try(BufferedReader br = new BufferedReader(new FileReader("inputs/"+filename));)
      {
          String line;
          while((line = br.readLine())!=null)
              lines+=line+"\n";

      } catch(IOException e){ }
      
      return lines;
    }
}
