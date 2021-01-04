import java.util.Set;
import java.util.HashSet;

class Puzzle6{
	final static String alphabetString = "abcdefghijklmnopqrstuvwxyz";
		public static void main(String[] args){
			String[] groups = Reader.readToString("input6").split("\n\n");

			int everyone = 0;
			int anyone = 0;

			for(String group : groups){
				int peopleCount = group.split("\n").length;
				String groupLine = group.replace("\n", "");

				Set<Character> used = new HashSet<Character>();
				for(char character : alphabetString.toCharArray()){
					if(countChar(groupLine, character) == peopleCount) everyone++;
					if(groupLine.contains(String.valueOf(character))) used.add(character);
				}
				anyone+=used.size();
			}
			System.out.println("Anyone: " +anyone);
			System.out.println("Total matches: " +everyone);

		}


		public static int countChar(String str, char c) {
			int count = 0;
			for(int i=0; i < str.length(); i++){
				if(str.charAt(i) == c) count++;
			}
			return count;
		}

}
