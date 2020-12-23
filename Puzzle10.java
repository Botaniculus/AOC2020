import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

class Puzzle10{
	private static HashMap<String, Long> cache;
	public static void main(String[] args){
		String[] input = Reader.read("input10");
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		for(int i=0; i<input.length; i++) {
			list.add(Integer.parseInt(input[i]));
		}
		
		list.add(Collections.max(list)+3);
		Collections.sort(list);
		
		System.out.println("Part 1: " + part1(list));
		
		cache = new HashMap<>();
		System.out.println("Part 2: " + getArrangements(list));
		
		
	}
	
	public static long getArrangements(List<Integer> inputs)
	{
		if(inputs.size() == 1)
			return 1;

		long arrangements = 0;
		int index = 1;
		int current = inputs.get(0);
		while(index < inputs.size() && (inputs.get(index) - current) < 4)
		{
			List<Integer> subList = inputs.subList(index, inputs.size()); // new sublist(inclusiveStartPoint, exclusiveEndPoint) of inputs
			String subListStr = Arrays.toString(subList.toArray(new Integer[0]));
			
			if(cache.containsKey(subListStr))
				arrangements += cache.get(subListStr);
			else{
				long subArrangements = getArrangements(subList);
				cache.put(subListStr, subArrangements);
				arrangements += subArrangements;
			}
			index++;
		}
		return arrangements;
	}
	public static int part1(ArrayList<Integer> list){
		int oneJoltDifferences = 0;
		int threeJoltsDifferences = 0;
		
		int oldJolt = 0;
		for(int index=0; index<list.size(); index++){
			if(list.get(index)-oldJolt == 1){
				oneJoltDifferences++;
			}
			else if(list.get(index)-oldJolt == 3){
				threeJoltsDifferences++;
			}
			oldJolt=list.get(index);
			
		}
	
	return oneJoltDifferences*threeJoltsDifferences;
	}
}