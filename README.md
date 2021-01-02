# Advent Of Code 2020

# Day 8
Meaning of:

`if(!executedLines.add(pc))

{

	run = false; // stop looping
	
	toReturn.infinite = true; // it is corrupted (infinite)
	
	toReturn.acc = accumulator;
	
}`

I am using set to store indexes of executed lines. This if block tries to add pc to set executedLines. If it has has successfuly added pc to executedLines, it returns true. Otherwise (if executedLines already contains pc), it returns false.


