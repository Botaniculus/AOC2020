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

	public static Result part2(String[] input) {
		String old = "";
		Result result = runProgram(input);

		//if it is corrupted (infinite loop)
		if(result.infinite)
		{

			for(int index = 0; index<input.length; index++)
			{

				for(int i = index; i<input.length; i++) // step through instructions at and succeeding index to find any nop or jmp to replace
				{
					String opCode = input[i].split(" ")[0];
					if(opCode.equals("nop")) {
						index = i;
						old = input[index];
						input[index] = old.replace("nop", "jmp");
						break;

					} else if(opCode.equals("jmp")) {
						index = i;
						old = input[index];
						input[index] = old.replace("jmp", "nop");
						break;

					}
				}

				result = runProgram(input); // try running it with replaced instruction
				if(!result.infinite) {
					System.out.println("The source of the infinite loop is on line " + (index+1) +": " +old);
					System.out.println("Solution: " + old + " => " + input[index]);
					break;
				}

				else
					input[index] = old; // insert old value because it didn't work

			}
		}

		return result;
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

	private static class Result{
		boolean infinite;
		int acc;
	}
}
