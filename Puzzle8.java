import java.util.ArrayList;
public class Puzzle8{
	public static void main(String[] args){
		String[] input = Reader.read("input8");
		Result res = runProgram(input);
		System.out.println("Part 1: " + res.acc);
		
		Result res2 = part2(input);
		System.out.println("Part 2: " + res2.acc);
	}
	public static Result part2(String[] input){
	int index = 0;
		boolean run = true;
		String old = "";
		Result res2;
		
		//if it is corrupted (infinite loop)
		if(runProgram(input).infinite)
		{
			// find instruction nop(/jmp) and replace it with jmp(/nop)
			do
			{
				boolean found = false;
				while(!found && index < input.length)
				{
					String op = input[index];
					String[] opAndArgs = op.split(" ");
					if(opAndArgs[0].equals("nop"))
					{
						found = true;
						old = input[index];
						input[index] = old.replace("nop", "jmp");
					
					} else if(opAndArgs[0].equals("jmp"))
					{
						found = true;
						old = input[index];
						input[index] = old.replace("jmp", "nop");
					} else
						index++;
				}
				res2 = runProgram(input);
				if(!res2.infinite)
				{
					run = false; // stop if done
					System.out.println("The source of the infinite loop was on line " + (index+1) +" : " +old+"\nSolution: " + old + " => " + input[index]);
				}
				else
				{
					input[index] = old; // insert old value because it didn't work
				}
				index++;
			} while(run && index < input.length);
		
		//if it is not corrupted (has no infinite loop)
		} else
			res2 = runProgram(input);
		
		return res2;
	}

	

	public static Result runProgram(String[] input){
	int accumulator=0;
		Result toReturn = new Result();
		
		ArrayList<Integer> executedLines = new ArrayList<Integer>();
		int pc=0;
		
		boolean run = true;
		while(run)
		{
			executedLines.add(pc);
			
			int number = Integer.parseInt(input[pc].split(" ")[1].replace("+", "").replace("-",""));
			char sign = input[pc].split(" ")[1].charAt(0);
			String instruction = input[pc].split(" ")[0];
			switch(instruction)
			{
				case "acc":				
					if(sign == '+')
						accumulator+=number;
					else
						accumulator-=number;
					pc++;
					break;
	
				case "jmp":
					if(sign == '+')
						pc+=number;
					else
						pc-=number;	
					break;
					
				case "nop":
					pc++;
					break;
			}

			if(executedLines.contains(pc))
			{
				run = false; // stop looping
				toReturn.infinite = true; // it is corrupted (infinite)
				toReturn.acc = accumulator;
			}
			else if(pc >= input.length)
			{
				run = false; // stop looping
				toReturn.infinite = false; // it isn't corrupted
				toReturn.acc = accumulator;
			}
		}	
		return toReturn;
	}
	
	private static class Result
	{
		boolean infinite;
		int acc;
	}
}