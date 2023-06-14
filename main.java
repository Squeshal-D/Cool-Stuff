import java.util.ArrayList;
import java.util.Scanner;

public class main {
	static String input;
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("Please input a string.");
		input = s.nextLine();                                            // the string we are compressing
		
		ArrayList<String> table = new ArrayList<String>();               // list of patterns found
		
		for (int len = input.length()/2; len > 1; len--)                   // start at the greatest length
		{
			ArrayList<String> searchForThesePatterns = new ArrayList<String>(); // the list of possible patterns of current length
			for (int start = 0; start + len <= input.length(); start++)
			{
				searchForThesePatterns.add(input.substring(start, start + len));// fill list with all substrings of current length
			}
			//System.out.println("Possible patterns:");
			//printList(searchForThesePatterns);
			for (int i = 0; i < searchForThesePatterns.size(); i++)
			{
				for (int j = i + len; j < searchForThesePatterns.size(); j++)   // j = i + len so that identified patterns aren't part of each other
				{
					//System.out.println("Comparing " + searchForThesePatterns.get(i) + " and " + searchForThesePatterns.get(j));
					if (searchForThesePatterns.get(i).equals(searchForThesePatterns.get(j))) // match found
					{
						table.add(searchForThesePatterns.get(i));                                    // remember pattern
						compressString(searchForThesePatterns.get(i), table.size() - 1);             // abbreviate pattern
						removePatterns(searchForThesePatterns, searchForThesePatterns.get(i), len);  // remove other invalidated patterns
						//printList(searchForThesePatterns);
						//System.out.println(input);
					}
				}
			}
		}
		
		// Print out the results
		System.out.println("Table:");
		for (int i = 0; i < table.size(); i++)
		{
			System.out.println(i + ": " + table.get(i));
		}
		System.out.println("Compressed String:\n" + input);
	}
	
	// does what it sounds like
	private static void printList(ArrayList<String> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i));
		}
	}
	
	// function removes a pattern that has been found and patterns that contained letters from it
	private static void removePatterns(ArrayList<String> patterns, String pattern, int length)
	{
		for (int i = 0; i < patterns.size(); i++)
		{
			if (patterns.get(i).equals(pattern))
			{
				for (int j = i; j < i + length && j < patterns.size(); j++)
				{
					patterns.remove(i);
				}
			}
		}
	}
	
	// function removes a pattern from the string and replaces it with the index of the pattern table
	private static void compressString(String pattern, int index)
	{
		for (int start = 0; start + pattern.length() <= input.length(); start++)
		{
			if (input.substring(start, start+pattern.length()).equals(pattern))
			{
				input = input.substring(0, start) + index + input.substring(start+pattern.length());
			}
		}
	}

}
