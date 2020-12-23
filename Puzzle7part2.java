import java.util.ArrayList;
public class Puzzle7part2{
		public static void main(String[] args){
			String[] input = Reader.read("input7");
			
			
			int nextCount=1;
			String next="shiny gold";
			ArrayList<String> namesList = new ArrayList<String>();
			
			namesList.add("shiny gold");
			
			for(int names=0; names<namesList.size(); names++){
				for(int line=0; line<input.length; line++){
					String[] containsBags = ((input[line].split(" contain "))[1]).replace(".", "").replace("bags", "").replace("bag", "").trim().split(", ");
					String name = (input[line].split(" contain ")[0]).replace("bags", "").trim();
					
					if(name.equals(namesList.get(names))){
						//System.out.println(name + ": ");
						for(int i=0; i<containsBags.length; i++){
							
							try{
								int count = (Integer.parseInt(containsBags[i].replaceAll("[^0-9]", "")));
								String subBagName = containsBags[i].replaceAll("[^a-zA-Z ]", "").trim();
								//System.out.println("	" +count + "x "+ subBagName);
								for(int a=0; a<count; a++){
									namesList.add(subBagName);
								}
								
							} catch(NumberFormatException e){}
							
						}
					}
			}}
					
						
				
			System.out.println(namesList);
			
			System.out.println(namesList.size()-1);
			
		}
		
		
}