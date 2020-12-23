import java.io.*;
class Puzzle2{
	
	public static void main(String[] args){
		String[] input = Reader.read("input2");
		int validPasswords = 0;
		for(int i=0; i<input.length; i++){
				String[] spaceSplitted=input[i].split(" ");
				String[] num = spaceSplitted[0].split("-");
				
				//convert String array to integer array
				int[] countInt = new int[num.length];
				for(int f=0; f<num.length; f++){
					countInt[f]=Integer.parseInt(num[f]);
				}
				
				String character = spaceSplitted[1].replace(":", "");
				String password = spaceSplitted[2];
				
				int calculatedCount=0;			
				if(String.valueOf(password.charAt(countInt[0]-1)).equals(character)
				|| (String.valueOf(password.charAt(countInt[1]-1)).equals(character)))
					calculatedCount++;
				//if(String.valueOf(password.charAt(countInt[1]-1)).equals(character))
					//calculatedCount++;
				
				if(calculatedCount==1)
					validPasswords++;
				
				System.out.println(input[i] + " - " + calculatedCount + " " + validPasswords);
		}
		System.out.println(validPasswords);
	}
	
}

