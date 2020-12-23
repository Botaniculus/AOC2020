import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
class Puzzle19{
	private static HashMap<Integer, String> rules;
	public static void main(String[] args){
		String[] input = Reader.read("input19");
		int stage=0;
		rules = new HashMap<Integer, String>();
		int result=0;
		for(String s : input){
			if(s.trim().isEmpty()){
				stage++;
				continue;
			}
			
			if(stage==0)
			{
				int index = Integer.parseInt(s.split(": ")[0]);
				String rule = s.split(": ")[1];
				rules.put(index, rule);
				
			} 
			
			else if(stage==1)
			{
				if(matches(s, 0))
					result++;
			}
		}
		
	}
	public static boolean matches(String recieved, int ruleIndex){
		String rule = rules.get(ruleIndex);
		List<String> ruleNumbers;
		List<String> ruleNumbers2;
		if(rule.contains("\"") && recieved.charAt(0)==rule.replace("\"", "").charAt(0)){
			rule = rule.substring(1); 
			return true;
		} else{
			ruleNumbers = Arrays.asList(rule.split(" "));
			if(ruleNumbers.contains("|"))
				ruleNumbers2 = ruleNumbers.subList(ruleNumbers.indexOf("|"), ruleNumbers.size()-1);
			
			for(int i = 0; i<ruleNumbers.size(); i++){
				if(matches)
			}
		}
	}
}