import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
class Puzzle22{
	private static ArrayList<Integer> deck1;
	private static ArrayList<Integer> deck2;

	public static void initDecks(String[] input){
		deck1 = new ArrayList<Integer>();
		deck2 = new ArrayList<Integer>();
		int stage=0;
		for(String s : input)
		{
			if(s.isEmpty()){
				stage++;
				continue;
			}
			if(s.contains("Player")) continue;
			if(stage==0) deck1.add(Integer.parseInt(s));
			else if(stage==1) deck2.add(Integer.parseInt(s));
		}
	}
	public static int part1(){
		List<Integer> won = new ArrayList<Integer>();
		
		while(won.size()==0){
			int top1 = deck1.get(0);
			int top2 = deck2.get(0);
			if(top1>top2){
				deck1.add(top1);
				deck1.add(top2);
				
			} else if(top2>top1){
				deck2.add(top2);
				deck2.add(top1);
				
			}
			deck1.remove(0);
			deck2.remove(0);
			if(deck1.size()==0) won=deck2;	
			else if(deck2.size()==0) won=deck1;	
		}
		
		return result(won);
	}
	public static int result(List<Integer> won){
		int result = 0;
		for(int i=won.size()-1; i>=0; i--)
			result+=won.get(i)*(won.size()-i);	
				
		return result;
	}
	public static void main(String[] args){
		String input[] = Reader.read("input22");
		initDecks(input);
		System.out.println("Part 1: " + part1());
		initDecks(input);
		System.out.println("Part 2: " + part2(deck1, deck2).result);
	}
	
	public static Win part2(List<Integer> cards1, List<Integer> cards2){
		List<ArrayList<Integer>> history1 = new ArrayList<ArrayList<Integer>>();
		List<ArrayList<Integer>> history2 = new ArrayList<ArrayList<Integer>>();

		Win win = new Win();		
		while(win.playerNumber==0){
			int top1 = cards1.get(0);
			int top2 = cards2.get(0);
			
			if(history1.contains(cards1) && history2.contains(cards2)){
				win.playerNumber=1;
				win.result=result(cards1);
				return win;
			}

			history1.add(new ArrayList<Integer>(cards1));
			history2.add(new ArrayList<Integer>(cards2));


			if(cards1.size()-1 >= top1 && cards2.size()-1 >= top2){
				Win w = part2(new ArrayList<Integer>(cards1.subList(1, top1+1)), new ArrayList<Integer>(cards2.subList(1, top2+1)));
				
				if(w.playerNumber == 1){
					cards1.add(top1);
					cards1.add(top2);
				} else if(w.playerNumber==2){
					cards2.add(top2);
					cards2.add(top1);
				}
	
			} else{
				if(top1>top2){
					cards1.add(top1);
					cards1.add(top2);
				} else if(top2>top1){
					cards2.add(top2);
					cards2.add(top1);
				}
			}
			
			cards1.remove(0); 
			cards2.remove(0);
			
			if(cards1.size()==0){win.playerNumber=2;}
			else if(cards2.size()==0){win.playerNumber=1;}

		}

		win.result=(win.playerNumber==1) ? result(cards1) : result(cards2);
		return win;
		
	}
	public static class Win{
		public int playerNumber = 0; //1 or 2
		public long result=0;
	}
}