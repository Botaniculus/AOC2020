import java.util.ArrayList;
class Puzzle20{
	
	public static class TileData
	{
		public int id;
		public String topEdge = "";
		public String botEdge = "";
		public String rightEdge = "";
		public String leftEdge = "";
	}
	
	public static void main(String[] args){
		String[] input = Reader.read("input20");
		
		ArrayList<TileData> tiles = new ArrayList<TileData>();
		TileData tileData = null;
		String last = "";
		
		
		for(String line : input)
		{
			if(line.isEmpty()){
				tileData.botEdge = last;
				last = "";
				tiles.add(tileData);
				
			}
			else if(line.contains("Tile")){
				line =  line.replace("Tile ", "").replace(":", "");
				tileData = new TileData();
				tileData.id = Integer.parseInt(line);
			
			} else{
				if(last.isEmpty())
					tileData.topEdge = line;	
				last = line;
				
				tileData.leftEdge+=line.substring(0, 1);
				tileData.rightEdge+=line.substring(line.length()-1, line.length());
			}
		
		}
		tileData.botEdge = last;
		tiles.add(tileData);
		
		long answer = 1;
		
		for(int i = 0; i < tiles.size(); i++)
		{
			TileData td1 = tiles.get(i);

			boolean top = false;
			boolean left = false;
			boolean bottom = false;
			boolean right = false;
			for(int j = 0; j < tiles.size(); j++)
			{
				if(i == j)
					continue;

				TileData td2 = tiles.get(j);

				if(stringsMatch(td1.topEdge, td2.botEdge) || stringsMatch(td1.topEdge, td2.topEdge) || stringsMatch(td1.topEdge, td2.leftEdge) || stringsMatch(td1.topEdge, td2.rightEdge))
				{
					top = true;
				}
				if(stringsMatch(td1.botEdge, td2.botEdge) || stringsMatch(td1.botEdge, td2.topEdge) || stringsMatch(td1.botEdge, td2.leftEdge) || stringsMatch(td1.botEdge, td2.rightEdge))
				{
					bottom = true;
				}
				if(stringsMatch(td1.leftEdge, td2.botEdge) || stringsMatch(td1.leftEdge, td2.topEdge) || stringsMatch(td1.leftEdge, td2.leftEdge) || stringsMatch(td1.leftEdge, td2.rightEdge))
				{
					left = true;
				}
				if(stringsMatch(td1.rightEdge, td2.botEdge) || stringsMatch(td1.rightEdge, td2.topEdge) || stringsMatch(td1.rightEdge, td2.leftEdge) || stringsMatch(td1.rightEdge, td2.rightEdge))
				{
					right = true;
				}
			}

			int count = (top ? 0 : 1) + (bottom ? 0 : 1) + (left ? 0 : 1) + (right ? 0 : 1);
			System.out.println(td1.id + ": " + count);
			if(count == 2)
			{
				answer *= td1.id;
				
			}
		}

		
		System.out.println(answer);

	
	}
	public static boolean stringsMatch(String s1, String s2)
	{
		return s1.equals(s2) || s1.equals(reverseString(s2));
	}

	public static String reverseString(String s)
	{
		StringBuilder toReturn = new StringBuilder();
		for(int i = s.length() - 1; i >= 0; i--)
		{
			char b = s.charAt(i);
			toReturn.append(b);
		}
		return toReturn.toString();
	}
	
}