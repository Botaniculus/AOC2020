import java.util.ArrayList;
import java.util.HashMap;
class Puzzle15{
	public static void main(String[] args){
		String[] input = Reader.read("input15")[0].split(",");
		ArrayList<Integer> spoken = new ArrayList<Integer>();
		HashMap<Integer, Integer> lastSaidAt = new HashMap<Integer, Integer>();
		int last=-1;
		for(int index = 0; index<input.length; index++){
			int num = Integer.parseInt(input[index]);
			spoken.add(num);
			if(index == input.length-1)
				last = num;
			else
				lastSaidAt.put(num, spoken.size());
		}
			
			
		
		while(spoken.size()<2020){
			last = step(last, spoken, lastSaidAt);
		}
		
		System.out.println(last);
		
		while(spoken.size()<30000000){
			last = step(last, spoken, lastSaidAt);
		}
		System.out.println(last);
	}
	
	public static int step(int lastSpoken, ArrayList<Integer> spoken, HashMap<Integer, Integer> lastSaidAt)
	{
		int seenLastTemp = lastSaidAt.getOrDefault(lastSpoken, -1);
		lastSaidAt.put(lastSpoken, spoken.size());
		spoken.add(lastSpoken);
		return seenLastTemp == -1 ? 0 : ((spoken.size() - 1) - seenLastTemp);
	}
}