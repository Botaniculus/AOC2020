import java.util.ArrayList;

class Puzzle16{
	
	public static void main(String[] args){
		String[] input = Reader.read("input16");
		System.out.println("Part 1: " + part1(input));
		//System.out.println("Part 2: " + part2(input));
		
	}
	public static class Rule{
		String name="";
		int[] a = new int[2];
		int[] b = new int[2];
		public Rule(String name, int[] a, int[] b){
			this.a=a.clone();
			this.b=b.clone();
			this.name=name;
		}
	}
	
	public static long part2(String[] input){
		ArrayList<Rule> rules = new ArrayList<Rule>();
		ArrayList<String[]> validTickets = new ArrayList<String[]>();
		String[] ourTicket = new String[0];
		int invalid=0;
		int stage=0;
		for(String line : input)
		{	
			if(line.trim().isEmpty()){
				stage++;
				continue;
			}
			
			if(stage==0){
				//--------------Rules-------------------------------
				String[] sLine = (line.split(": "))[1].split(" ");
				String name=(line.split(": "))[1];
				int[] a = new int[2];
				a[0] = Integer.parseInt(sLine[0].split("-")[0]);
				a[1] = Integer.parseInt(sLine[0].split("-")[1]);
				
				int[] b = new int[2];
				b[0] = Integer.parseInt(sLine[2].split("-")[0]);
				b[1] = Integer.parseInt(sLine[2].split("-")[1]);
				
				Rule rule = new Rule(name, a, b);
				rules.add(rule);
				
			} else if(stage==1){
				//--------------My ticket---------------------------------
				if(line.contains("your ticket:"))
					continue;
				ourTicket = line.split(",");
				
			} else if(stage==2){
				//--------------Tickets------------------
				if(line.contains("nearby tickets:"))
					continue;
				String[] values = line.split(",");
				boolean valid = false;
				for(String valueStr : values){
					int value = Integer.parseInt(valueStr);
					for(Rule rule : rules){
						if(value >= rule.a[0] && value <= rule.a[1] 
						|| value >= rule.b[0] && value <= rule.b[1]){	
							// value is valid
							valid=true;
							break;
						}	
					} if(!valid){
							// value isn't valid (doesn't follow any rules)
							invalid+=value;
							break;
					}
					
				}
				if(valid){
					validTickets.add(values);
				}
			}
		}
		
		return 0;
	}
	
	public static int part1(String[] input){
		ArrayList<Rule> rules = new ArrayList<Rule>();
		int invalid=0;
		int stage=0;
		for(String line : input)
		{	
			if(line.trim().isEmpty()){
				stage++;
				continue;
			}
			
			if(stage==0){
				//--------------Rules-------------------------------
				String[] sLine = (line.split(": "))[1].split(" ");
				String name=(line.split(": "))[1];
				int[] a = new int[2];
				a[0] = Integer.parseInt(sLine[0].split("-")[0]);
				a[1] = Integer.parseInt(sLine[0].split("-")[1]);
				
				int[] b = new int[2];
				b[0] = Integer.parseInt(sLine[2].split("-")[0]);
				b[1] = Integer.parseInt(sLine[2].split("-")[1]);
				
				Rule rule = new Rule(name, a, b);
				rules.add(rule);
				
			} else if(stage==1){
				//--------------My ticket---------------------------------
				if(line.contains("your ticket:"))
					continue;
				
			} else if(stage==2){
				//--------------Tickets------------------
				if(line.contains("nearby tickets:"))
					continue;
				String[] values = line.split(",");
				for(String valueStr : values){
					int value = Integer.parseInt(valueStr);
					for(Rule rule : rules){
						if(value >= rule.a[0] && value <= rule.a[1] 
						|| value >= rule.b[0] && value <= rule.b[1]){	
							// value is valid
							break;
							
						} else if(rule==rules.get(rules.size()-1)){
							// value isn't valid (doesn't follow any rules)
							invalid+=value;
						}
					}	
				}
			}
		}
		
		return invalid;
	}
}
