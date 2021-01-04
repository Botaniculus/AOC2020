import java.io.*;
class Puzzle2{

	public static void main(String[] args){
		String[] input = Reader.read("input2");
		int[] result = solve(input);
		System.out.println("Part 1: " + result[0]);
		System.out.println("Part 2: " + result[1]);
	}

	public static int[] solve(String[] input){
		int validPasswords1 = 0;
		int validPasswords2 = 0;
		for(int i=0; i<input.length; i++){
				String[] spaceSplitted=input[i].split(" ");

				int min = Integer.parseInt(spaceSplitted[0].split("-")[0]);
				int max = Integer.parseInt(spaceSplitted[0].split("-")[1]);

				char character = spaceSplitted[1].charAt(0);
				String password = spaceSplitted[2];

				// part1
				if(countChars(password, character) >= min && countChars(password, character) <= max)
					validPasswords1++;

				// part2
				if((password.charAt(min-1) == character || password.charAt(max-1) == character) && password.charAt(max-1) != password.charAt(min-1))
					validPasswords2++;

		}
		int[] toReturn = {validPasswords1, validPasswords2};
		return toReturn;

	}
	
	public static int countChars(String s, char character){
		int count = 0;
		for(char c : s.toCharArray()){
			if(c == character)
				count++;
		}
		return count;
	}

}
