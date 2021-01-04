import java.util.*;
class Puzzle5{
		public static void main(String[] args){
			String[] input = Reader.read("input5");
			List<Integer> list = new ArrayList<Integer>();

			for(String seat : input){
				seat = seat.replace("B", "1").replace("F", "0").replace("R", "1").replace("L", "0");

				int row = Integer.parseInt(seat.substring(0, 7), 2);
				int column = Integer.parseInt(seat.substring(7, 10), 2);
				int id = row*8 + column;
				list.add(id);
			}
			Collections.sort(list);

			System.out.println("The highest seat ID: " + list.get(list.size() - 1));

			for(int i=0; i<list.size()-1; i++){
				int a = list.get(i);
				int b = list.get(i+1);
				if((b-a) == 2){
					System.out.println("The missing seat ID is between " + a + " and " + b + " = " + (a+1));
					break;
				}

			}

		}
}
