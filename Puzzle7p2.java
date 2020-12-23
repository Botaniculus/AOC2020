import java.util.ArrayList;
import java.util.HashMap;
public class Puzzle7p2{
	
		public static void main(String[] args){
			int totalCount=0;
			HashMap<String, String> hashmap = init();
			//System.out.println(hashmap.get("shiny gold".toUpperCase()));
			String oldOne = "";
			String newOne = "shiny gold".toUpperCase();
			for(int c = 0; c<hashmap.size(); c++){
				String neww = hashmap.get(newOne);
				String[] innerBags = neww.split(";");
				for(String i : innerBags){
					String name = i.split(":")[0];
					int count = Integer.parseInt(i.split(":")[1]);
					System.out.println(name + " " + count + "x");
				}
			}
		}
		public static HashMap<String, String> init(){
			String[] input = Reader.read("input7");
			HashMap<String, String> hashmap = new HashMap<String, String>();
			for(int line=0; line<input.length; line++)
			{
				String[] containsBags = ((input[line].split(" contain "))[1]).replace(".", "").replace("bags", "").replace("bag", "").trim().split(", ");
				String name = (input[line].split(" contain ")[0]).replace("bags", "").trim();
				String innerBags="";
				for(int i=0; i<containsBags.length; i++)
				{							
					try{
							int count = (Integer.parseInt(containsBags[i].replaceAll("[^0-9]", "")));
							String subBagName = containsBags[i].replaceAll("[^a-zA-Z ]", "").trim();
							innerBags+=subBagName+":"+count + ";";					
					} catch(NumberFormatException e){}
							
				}
				hashmap.put(name.toUpperCase(), innerBags);
			}
					
						
			
		
		return(hashmap);	
		}
		
}