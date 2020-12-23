class Puzzle6{
	final static String alphabetString = "abcdefghijklmnopqrstuvwxyz";
		public static void main(String[] args){
			String[] groups = readString().split("\n\n");
			
			int count = 0;
			for(int group = 0; group<groups.length; group++){
				int peopleCount = (groups[group].split("\n")).length;
				
				String groupLine = groups[group].replace("\n", "");
				int groupCount = 0;
				
				for(int character=0; character<alphabetString.length(); character++){			
					if(countChar(groupLine, alphabetString.charAt(character)) == peopleCount){
						count++;	
						groupCount++;
					}
				}
				System.out.println(peopleCount + " people"+ ": " + groupCount + " matches");
			}
			System.out.println("Total matches: " +count);
			
		}
		
		public static String readString(){
			String[] firstArray = Reader.read("input6");
			
			String output="";
			for(int s=0; s<firstArray.length; s++){
				output+=firstArray[s] + "\n";	
			}
			return output;
		}
		
		public static int countChar(String str, char c) {
			int count = 0;

			for(int i=0; i < str.length(); i++)
			{    if(str.charAt(i) == c)
					count++;
			}

			return count;
		}

}
