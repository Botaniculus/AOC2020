import java.util.regex.*;

class Puzzle4{
	public static boolean checkPid(String str){
		if (str == null) return false;
		// Regex to check valid 9-digit number.
       	String regex = "^([0-9]{9})$";
       	Pattern p = Pattern.compile(regex);
       	Matcher m = p.matcher(str);
       	return m.matches();
	}
	public static boolean testColor(String str){
		if (str == null) return false;
		// Regex to check valid hexadecimal color code.
       	String regex = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
		Pattern p = Pattern.compile(regex);
       	Matcher m = p.matcher(str);
		return m.matches();
    }
	public static void main(String[] args){
		String textString = readString();
		int finalCount=0;
		String[] passports = textString.split("\n\n");
		
		//each passport
		for(int passport=0; passport<passports.length; passport++){
			String[] data = passports[passport].replace("\n", " ").split(" ");
			int count=0;
			
			
			//each data
			for(int dataIndex=0; dataIndex<data.length; dataIndex++){
				String[] finalData = data[dataIndex].split(":");
				String title=finalData[0];
				String value=finalData[1];
			
				count += check(title, value);
			}
			
			if(count>=7)
				finalCount++;
				
		}
		System.out.println("Final count: " + finalCount);
		
	}
	public static int checkNumber(String value, int minimum, int maximum){
		int year = Integer.parseInt(value);
		if(year>=minimum && year<=maximum)
			return 1;
		else
			return 0;
	}
	public static int check(String title, String value){
		int count=0;
		if(title.equals("eyr")){
			count+=checkNumber(value, 2020, 2030);
			
		} else if(title.equals("iyr")){
			count+=checkNumber(value, 2010, 2020);
			
		} else if(title.equals("byr")){
			count+=checkNumber(value, 1920, 2002);
			
		} else if(title.equals("hcl")){
			if(testColor(value) == true)
				count++;
		} else if(title.equals("ecl")){
			String[] array = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
			for(int col = 0; col<array.length; col++){
				if(value.equals(array[col])){
					count++;
					break;
				}
			}
		} else if(title.equals("hgt")){
			if(value.endsWith("m")){
				String[] array = value.split("c");
				count+=checkNumber(array[0], 150, 193);
			
			} else{
				String[] array = value.split("i");
				count+=checkNumber(array[0], 59, 76);	
			}
		} else if(title.equals("pid")){
			if(checkPid(value) == true)
				count++;
		}
		return count;
	}
	public static String readString(){
		String[] firstArray = Reader.read("input4");
		
		String output="";
		for(int s=0; s<firstArray.length; s++){
			output+=firstArray[s] + "\n";	
		}
		return output;
	}
}
