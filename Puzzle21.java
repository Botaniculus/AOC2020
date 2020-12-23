import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
class Puzzle21{
	public static void main(String[] args){
		int count=0;
		String[] input = Reader.read("input21");
		HashMap<String, List<String>> allergenMap = new HashMap<String, List<String>>();
		List<List<String>> allIngredients = new ArrayList<>();
		Set<String> uniqueIngredients = new HashSet<>();
		
		for(String line : input){
			List<String> localIngredients = Arrays.asList(line.substring(0, line.indexOf("(")).split(" "));
			System.out.println("localIngredients: " + localIngredients);
			uniqueIngredients.addAll(localIngredients);
			allIngredients.add(localIngredients);
			
			String[] allergens = line.substring(line.indexOf("(")+1, line.length()-1).replace("contains ", "").split(", ");
			
			for(String s : allergens){
				if(allergenMap.containsKey(s)){
					List<String> possibleIngredients = allergenMap.get(s);
					System.out.println("possibleIngredients: " + possibleIngredients);
					
					for(int i=possibleIngredients.size()-1; i>=0; i--){
						if(!localIngredients.contains(possibleIngredients.get(i)))
							possibleIngredients.remove(i);
					}
				
				} else{
					allergenMap.put(s, localIngredients);
				}
			}
			
		}
		HashMap<String, String> finalAllergens = new HashMap<String, String>();
		while(allergenMap.size()>0){
			String toRemove = "";

			for(String a : allergenMap.keySet())
			{
				List<String> localIngredients = allergenMap.get(a);
				if(localIngredients.size() == 1)
				{
					String ing = localIngredients.get(0);
					finalAllergens.put(a, ing);
					toRemove = a;
					
					//remove ingredience from other allergens
					for(String a2 : allergenMap.keySet()){
						allergenMap.get(a2).remove(ing);
					}
					System.out.println("We're good");
					break;
				}
				
			}
			
		//	if(!toRemove.isEmpty())
				allergenMap.remove(toRemove);
		
		}
		List<String> hasNoAllergens = new ArrayList<>(uniqueIngredients);
		for(String a : finalAllergens.values())
		{
			hasNoAllergens.remove(a);
		}
		
		int total = 0;
		for(List<String> ingredients : allIngredients)
		{
			for(String ing : hasNoAllergens)
			{
				if(ingredients.contains(ing))
					total++;
			}
		}
		System.out.println(total);
		
	}
}