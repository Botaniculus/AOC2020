import java.util.*;
class Puzzle5{
		public static void main(String[] args){
			String[] input = Reader.read("input5");
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			for(int seat = 0; seat<input.length; seat++){
				input[seat] = input[seat].replace("B", "1").replace("F", "0").replace("R", "1").replace("L", "0");
				System.out.println(input[seat]);
				int row = Integer.parseInt(input[seat].substring(0, 7), 2);
				int column = Integer.parseInt(input[seat].substring(7, 10), 2);
				int id = row*8 + column;
				list.add(id);
			}
			Collections.sort(list);
			
			System.out.println("The highest seat ID: " + list.get(list.size() - 1));
			for(int i=0; i<list.size()-1; i++){
				if((list.get(i+1)-list.get(i)) == 2){
					System.out.println("The missing seat ID is between " + list.get(i) + " and " + list.get(i+1) + " = " + (list.get(i)+1));
				}
				
			}
		
		}
}
