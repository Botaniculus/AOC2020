import java.util.Collections;
import java.util.HashMap;
class Puzzle13{
	public static void main(String[] args){
		String[] input = Reader.read("input13");
		System.out.println(part1(input));
		part2(input);
		
	}
	public static int part1(String[] input){
		int timestamp = Integer.parseInt(input[0]);
		
		String[] idsArray = input[1].split(",");
		HashMap<Integer, Integer> ids = new HashMap<Integer, Integer>();
		for(int i=0; i<idsArray.length; i++){
			if(!idsArray[i].equals("x"))
				ids.put(Integer.parseInt(idsArray[i]), Integer.parseInt(idsArray[i]));
		}
		for(int id : ids.keySet()){
			while(ids.get(id)<timestamp){
				ids.put(id, ids.get(id)+id);	
			}
		}
		int first = Collections.min(ids.values());
		
		int minutes = first-timestamp;
		int idAnswer=0;
		for(int id : ids.keySet()){
			if(ids.get(id) == first){
				idAnswer=id;
				break;
			}
		}
		
		return minutes*idAnswer;
	}
	public static long part2(String[] input){
		long timestamp=1;
		String[] idsArray = input[1].replace("x", "0").split(",");
		while(true){
			
			for(int i=0; i<idsArray.length; i++){
				if(Integer.parseInt(idsArray[i])!=0 && timestamp%Integer.parseInt(idsArray[i])==0){
					System.out.println(timestamp + " % " + idsArray[i]);
				}
			
			}
			
			timestamp++;
		}
		//return timestamp;
	}

}