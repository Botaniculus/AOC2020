import java.util.ArrayList;
public class Puzzle7{
		public static void main(String[] args){
			String[] input = Reader.read("input7");
			
			ArrayList<String> outerBagsList = new ArrayList<String>();
			outerBagsList.add("shiny gold");
			
			int oldSize=0;
			while(true){
				for(int line=0; line<input.length; line++){
					//System.out.println(input[line]);
					String[] innerBags = ((input[line].split("contain"))[1]).replace(".", "").replace("bags", "").replace("bag", "").replaceAll("\\d ","").split(", ");
					String outerBag = input[line].replace(" bags ", "").split("contain")[0];
					
					for(int innerBag=0; innerBag<innerBags.length; innerBag++){
						for(int n =0; n<outerBagsList.size(); n++){
							if(innerBags[innerBag].trim().equals(outerBagsList.get(n))){
								System.out.println(outerBag + ": " + innerBags[innerBag]);
								if(!outerBagsList.contains(outerBag))
									outerBagsList.add(outerBag);	
							}
						}
					}	
				}
				
				
				System.out.println(outerBagsList);
				System.out.println(outerBagsList.size()-1);
				if(outerBagsList.size()-1 == oldSize)
					System.exit(0);
				else
					oldSize = outerBagsList.size()-1;
				
			
			}
		}
		
		
}