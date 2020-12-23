import java.lang.Character;
import java.util.Arrays;
class Puzzle18{
	public static void main(String[] args){
		String[] input = Reader.read("input18");
		long result=0;
		for(String s : input){
			s = s.replace("(", "( ").replace(")", " )");
			String[] arr = s.split(" ");
			result+=calculate(arr);
		}
		System.out.println("Part 1: " + result);
	}
	
	public static long calculate(String[] arr){
		long result = 0;
		for(int index = 0; index<arr.length; index++)
		{
			int skip=0;
			long num=0;
			if(arr[index].equals("("))
			{
				int endIndex=0;
				int closedParentheses=0;
				for(int i=index; i<arr.length; i++){
					if(arr[i].equals("("))
						closedParentheses++;
						
					else if(arr[i].equals(")")){
						if(closedParentheses==1){
							endIndex=i;
							break;
						}
						closedParentheses--;
					}
				}
				if(endIndex>index+1){
					num = calculate(Arrays.copyOfRange(arr, index+1, endIndex)); //without the first parenthese
					skip=endIndex;
				}
				
			} 
			
			if(num==0){
				try{
					num = Integer.parseInt(arr[index]);
					//System.out.println("Index: " + index + " Num: " + num);
				} catch(NumberFormatException e){continue;}
			}

			try{
				char sign = arr[index-1].toCharArray()[0];
				
				if(sign=='+')
					result+=num;
				else if(sign=='-')
					result-=num;
				else if(sign=='*')
					result*=num;
				else if(sign=='/')
					result/=num;
			} catch(java.lang.ArrayIndexOutOfBoundsException e){
				// num is the first in the array
				result+=num;
			}
			
			if(skip>0) index=skip;
		
		}

		return result;
	}
}
