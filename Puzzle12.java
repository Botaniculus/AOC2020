class Puzzle12{
	public static void main(String[] args){
		String[] input = Reader.read("input12");
		System.out.println("Part 1: " + part1(input));
		System.out.println("Part 2: " + part2(input));
	}
	
	public static int part2(String[] input){
		MovingObject ship = new MovingObject(0, 0, 0);
		MovingObject waypoint = new MovingObject(0, 10, 1);
		for(int lineIndex=0; lineIndex<input.length; lineIndex++){
			int count = Integer.parseInt(input[lineIndex].replaceAll("[a-zA-Z]", ""));
			char letter = input[lineIndex].charAt(0);
			int oldX=waypoint.x;
			int oldY=waypoint.y;
			switch(letter)
			{
				case 'N':
					waypoint.y+=count;
					break;
				case 'S':
					waypoint.y-=count;
					break;
				case 'E':
					waypoint.x+=count;
					break;
				case 'W':
					waypoint.x-=count;
					break;
				
				case 'L':
					if(count<0)
						count = 360-Math.abs(count);
					if(count==90){
						waypoint.x=(waypoint.y-(2*waypoint.y));
						waypoint.y=oldX;
					} else if(count==180){
						waypoint.x=(waypoint.x-(2*waypoint.x));
						waypoint.y=(waypoint.y-(2*waypoint.y));
					} else if(count==270){
						waypoint.x=waypoint.y;
						waypoint.y=(oldX-(2*oldX));
					}
					break;
				case 'R':
					if(count<0)
						count = 360-Math.abs(count);
					if(count==90){
						waypoint.x=waypoint.y;
						waypoint.y=(oldX-(2*oldX));
					} else if(count==180){
						waypoint.x=(waypoint.x-(2*waypoint.x));
						waypoint.y=(waypoint.y-(2*waypoint.y));
					} else if(count==270){
						waypoint.x=(waypoint.y-(2*waypoint.y));
						waypoint.y=oldX;
					}
					break;
					
				case 'F':
					for(int i=0; i<count; i++){
						ship.x += waypoint.x;
						ship.y += waypoint.y;
					}
					break;
			}
		}
		return (Math.abs(ship.x)+Math.abs(ship.y));
	}
	
	public static int part1(String[] input){
		MovingObject ship = new MovingObject(90, 0, 0);
		for(int lineIndex=0; lineIndex<input.length; lineIndex++){
			int count = Integer.parseInt(input[lineIndex].replaceAll("[a-zA-Z]", ""));
			char letter = input[lineIndex].charAt(0);
			switch(letter)
			{
				case 'N':
					ship.y+=count;
					break;
				case 'S':
					ship.y-=count;
					break;
				case 'E':
					ship.x+=count;
					break;
				case 'W':
					ship.x-=count;
					break;
				
				case 'L':
					ship.facing -= count;
					break;
				case 'R':
					ship.facing += count;
					break;
					
				case 'F':
					if(ship.facing==0)
						ship.y+=count;
					else if(ship.facing==90)
						ship.x+=count;
					else if(ship.facing==180)
						ship.y-=count;
					else if(ship.facing==270)
						ship.x-=count;
					break;
			}
			if(ship.facing<0)
				ship.facing = 360-Math.abs(ship.facing);
			if(ship.facing>=360)
				ship.facing=ship.facing-360;
		}
		
		return (Math.abs(ship.x)+Math.abs(ship.y));
	}
	public static class MovingObject{
		int facing = 0; //0 north, 90 east, 180 south, 270 west
		int x=0; //(+) east / (-) west
		int y=0; //(+) north / (-) south
		public MovingObject(int facing, int x, int y){
			this.facing=facing;
			this.x=x;
			this.y=y;
		}
		
	}
}