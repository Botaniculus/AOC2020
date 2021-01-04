import java.util.regex.*;
import java.util.ArrayList;
import java.util.Arrays;

class Puzzle4{
	public static boolean checkPid(String str){
		if (str == null) return false;
    String regex = "^([0-9]{9})$";
   	Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(str);

		return m.matches();
	}
	public static boolean testColor(String str){
		if (str == null) return false;
    String regex = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
		Pattern p = Pattern.compile(regex);
   	Matcher m = p.matcher(str);

		return m.matches();
  }

	public static void main(String[] args){
		String textString = Reader.readToString("input4");
		int validPassportsPart1=0;
		int validPassportsPart2=0;
		String[] passports = textString.split("\n\n");

		for(String passport : passports)
		{
			String[] pairs = passport.replace("\n", " ").split(" "); // [name:value, name1:value1, name2:value2, ...]
			int containedPairsCount=0;
			int validPairsCount=0;

			for(String pair : pairs){
				int[] check = check(pair);
				containedPairsCount += check[0];
				validPairsCount += check[1];

			}

			validPassportsPart1 += (containedPairsCount >= 7) ? 1 : 0;
			validPassportsPart2 += (validPairsCount >= 7) ? 1 : 0;

		}

		System.out.println("Part 1: " + validPassportsPart1);
		System.out.println("Part 2: " + validPassportsPart2);
	}

	public static int[] check(String pair){
		String title = pair.split(":")[0];
		String value = pair.split(":")[1];
		int part1=0;
		int count=0;
		if(title.equals("eyr")){
			count+=(Integer.parseInt(value)>=2020 && Integer.parseInt(value)<=2030) ? 1 : 0;
			part1++;
		} else if(title.equals("iyr")){
			count+=(Integer.parseInt(value)>=2010 && Integer.parseInt(value)<=2020) ? 1 : 0;
			part1++;
		} else if(title.equals("byr")){
			count+=(Integer.parseInt(value)>=1920 && Integer.parseInt(value)<=2002) ? 1 : 0;
			part1++;
		} else if(title.equals("hcl")){
			if(testColor(value) == true)
				count++;
			part1++;
		} else if(title.equals("ecl")){
			ArrayList<String> colors = new ArrayList<String>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
			if(colors.contains(value))
				count++;
			part1++;

		} else if(title.equals("hgt")){
			if(value.endsWith("m")){
				int number = Integer.parseInt(value.split("c")[0]);
				count+=(number>=150 && number<=193) ? 1 : 0;

			} else{
				int number = Integer.parseInt(value.split("i")[0]);
				count+=(number>=59 && number<=76) ? 1 : 0;
			}
			part1++;
		} else if(title.equals("pid")){
			if(checkPid(value) == true)
				count++;
			part1++;
		}

		return new int[]{part1, count};
	}
}
