import java.util.ArrayList;
import java.util.Collections;
public class Puzzle9{
		public static void main(String[] args){
			String[] input = Reader.read("input9");
			long part1Result = part1(input, 25);
			System.out.println("Part 1: " + part1Result);
			System.out.println("Part 2: " + part2(input, part1Result));
			
		}
		public static long part2(String[] input, long notSummingNumber){
			int[] indexes = new int[2];
			
			for(int line=0; line<input.length; line++)
			{
				indexes[0] = line;
				long sum = 0;
				
				for(int innerLine=line; innerLine<input.length; innerLine++)
				{
					sum+=Long.parseLong(input[innerLine]);
					if(sum == notSummingNumber){
						indexes[1] = (innerLine); break;
					}
					else if(sum>notSummingNumber) break;
				}
				if(indexes[1]!=0) break;
			}
			
			ArrayList<Long> nums = new ArrayList<Long>();
			for(int i=indexes[0]; i<=indexes[1]; i++){
				nums.add(Long.parseLong(input[i]));
			}
			return (Collections.min(nums) + Collections.max(nums));
		}
		
		public static long part1(String[] input, int count){
			long[] numbers = new long[count];
			long notSummingNumber=0;
			
			for(int line=0; line<input.length; line++)
			{
				for(int index = 0; index<count; index++){
					numbers[index] = Long.parseLong(input[line+index]);	
				}
				
				long searchedNumber = Long.parseLong(input[line + count]);
				
				boolean successfulyAddedUp = false;
				for(int a=0; a<numbers.length; a++){
					for(int b=0; b<numbers.length; b++){
						if(numbers[a] + numbers[b] == searchedNumber && numbers[a] != numbers[b]){
							successfulyAddedUp=true;
							break;
						} else if(a==b && a==numbers.length-1){
							notSummingNumber=searchedNumber;
							successfulyAddedUp=false;
							break;
						}
					}
					if(successfulyAddedUp || notSummingNumber!=0)
						break;
				}
				if(notSummingNumber!=0)
					break;
				
			}
			return notSummingNumber;
		}
}