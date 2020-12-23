class Puzzle3 {
        final static int[] downArray = {1, 1, 1, 1, 2};
        final static int[] rightArray = {1, 3, 5, 7, 1};

        public static void main(String[] args) {
            String[] lines = Reader.read("input3");

            long multipliedCount = 1;
            
            for (int slope = 0; slope < downArray.length; slope++) {
                int index = 0;
                int count=0;
                for (int row = downArray[slope]; row < lines.length; row += downArray[slope]) {
                    index = (index + rightArray[slope]) % lines[row].length();
                    if (lines[row].charAt(index) == '#')
                        count++;
                }

                multipliedCount *= count;
                System.out.println("Count "+ (slope+1) + ": " + count);
            }
            System.out.println("Result: " + multipliedCount);


        }
}
