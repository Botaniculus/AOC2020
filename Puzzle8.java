import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
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
		Set<Integer> executedLines = new HashSet<Integer>();
		int pc=0; // program counter
		executedLines.add(pc);
		
		boolean run = true;
		while(run)
		{
			int number = Integer.parseInt(input[pc].split(" ")[1].replace("+", "").replace("-",""));
			char sign = input[pc].split(" ")[1].charAt(0);
			String instruction = input[pc].split(" ")[0];
			switch(instruction)
			{
				case "acc":				
					accumulator+=((sign == '+') ? number : -number);
					pc++;
					break;
	
				case "jmp":
					pc+=((sign == '+') ? number : -number);
					break;
					
				case "nop":
					pc++;
					break;
			}

			if(!executedLines.add(pc)) // pc is cannot be added to set bcs set already contains it | otherwise add pc to set
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