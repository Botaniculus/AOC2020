class Puzzle11{
	public static int getOccupied(int atRow, int atChar, String[] input){
		int occupied=0;

		try{
			occupied+=(input[atRow-1].charAt(atChar-1) == '#') ? 1 : 0;	
		} catch(java.lang.ArrayIndexOutOfBoundsException | java.lang.StringIndexOutOfBoundsException e){}
		try{
			occupied+=(input[atRow-1].charAt(atChar) == '#') ? 1 : 0;
		} catch(java.lang.ArrayIndexOutOfBoundsException | java.lang.StringIndexOutOfBoundsException e){}
		try{
			occupied+=(input[atRow-1].charAt(atChar+1) == '#') ? 1 : 0;
		} catch(java.lang.ArrayIndexOutOfBoundsException | java.lang.StringIndexOutOfBoundsException e){}
		
		try{
			occupied+=(input[atRow].charAt(atChar-1) == '#') ? 1 : 0;
		} catch(java.lang.StringIndexOutOfBoundsException e){}
		try{
			occupied+=(input[atRow].charAt(atChar+1) == '#') ? 1 : 0;
		} catch(java.lang.StringIndexOutOfBoundsException e){}
		
		try{
			occupied+=(input[atRow+1].charAt(atChar-1) == '#') ? 1 : 0;
		} catch(java.lang.ArrayIndexOutOfBoundsException | java.lang.StringIndexOutOfBoundsException e){}
		try{
			occupied+=(input[atRow+1].charAt(atChar) == '#') ? 1 : 0;
		} catch(java.lang.ArrayIndexOutOfBoundsException | java.lang.StringIndexOutOfBoundsException e){}
		try{
			occupied+=(input[atRow+1].charAt(atChar+1) == '#') ? 1 : 0;
		} catch(java.lang.ArrayIndexOutOfBoundsException | java.lang.StringIndexOutOfBoundsException e){}
		
		
		return occupied;
	}
	public static void main(String[] args){
		String[] input = Reader.read("input11");
		System.out.println("Part 1: " + part1(input));
		System.out.println("Part 2: " + part2(input));
		
	}
	public static int part1(String[] input)
	{
		String[] temp = new String[input.length];
		int count=0;
		int countOld=0;
		
		while(true){
			count=0;
			for(int line=0; line<input.length; line++){
				StringBuilder string = new StringBuilder(input[line]);
				
				for(int character = 0; character<input[line].length(); character++){	
					char thischar = input[line].charAt(character);
					int occupied = getOccupied(line, character, input);
					if(thischar=='L'){
						if(occupied==0)
							string.setCharAt(character, '#');
							
					} else if(thischar=='#'){	
						if(occupied>=4)
							string.setCharAt(character, 'L');
					}
					
				}
				temp[line]=string.toString();
				
				count+=countChar(temp[line], '#');
			
			}
			if(countOld==count){
				break;
			}
			
			countOld=count;
			input=temp.clone();
			
		}
		
		return count;
	}
	public static int part2(String[] input)
	{
		String[] temp = new String[input.length];
		int count=0;
		int countOld=0;
		
		while(true){
			count=0;
			System.out.println();
			for(int line=0; line<input.length; line++){
				StringBuilder string = new StringBuilder(input[line]);
				
				for(int character = 0; character<input[line].length(); character++){	
					char thischar = input[line].charAt(character);
					int occupied = getOccupied(line, character, input);
					if(thischar=='L'){
						if(occupied==0)
							string.setCharAt(character, '#');
							
					} else if(thischar=='#'){
						if(occupied>=5)
							string.setCharAt(character, 'L');
					}
					
				}
				temp[line]=string.toString();
				System.out.println(temp[line]);
				count+=countChar(temp[line], '#');
			
			}
			System.out.println("Count: " + count);
			if(countOld==count){
				break;
			}
			
			countOld=count;
			input=temp.clone();
			
		}
		
		return count;
	}
	public static int countChar(String str, char c)
	{
		int count = 0;
		for(int i=0; i < str.length(); i++){    
			if(str.charAt(i) == c)
				count++;
		}
		return count;
	}

}