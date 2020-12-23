import java.io.*;

class Puzzle1{
	public static void main(String[] args){
		String[] input = Reader.read("input1");
		int result=0;
		for(int i=0; i<input.length; i++){
			for(int j=0; j<input.length; j++){
				for(int k=0; k<input.length; k++){
					int x = Integer.parseInt(input[i]);
					int y = Integer.parseInt(input[j]);
					int z = Integer.parseInt(input[k]);
					if(x + y + z == 2020){
						result = x * y * z;
						break;
					}
					
				}
			}
		}
		System.out.println("\n" + result);
	}
}
