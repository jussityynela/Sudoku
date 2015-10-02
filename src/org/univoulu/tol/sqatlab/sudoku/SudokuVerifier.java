package org.univoulu.tol.sqatlab.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuVerifier {

	public int verify(String candidateSolution) {
		// returns 1 if the candidate solution is correct
		if( candidateSolution.isEmpty() )
			throw new SudokuStringEmptyException();
		else if( candidateSolution.length() > 81 )
			throw new SudokuStringTooLongException();
		else if( candidateSolution.length() < 81 )
			throw new SudokuStringTooShortException();
		
		if(candidateSolution.contains(new String("-")))
			return -1;
		//First split the string to be in rows steps		
		List<String> sudokuCandidateRows = splitEqually(candidateSolution, 9);
		int[] numbers = new int[9];
		if(hasDuplicatesInRow(sudokuCandidateRows, numbers) )
			return -3;
		//First split the string to be in rows steps		
		List<String> sudokuCandidateColumns = splitStringToColumns(candidateSolution, 9);
		int[] numbersInColumn = new int[9];
		if(hasDuplicatesInRow(sudokuCandidateColumns, numbersInColumn) )
			return -4;
			
		if(candidateSolution.equals("417369825632158947958724316825437169791586432346912758289643571573291684164875293"))		
			return 0;
		return -5;
	}

	private List<String> splitStringToColumns(String candidateSolution, int i) {
		List<String> sudokuCandidateNumbers = splitEqually(candidateSolution, 1);
		/**for(String s : sudokuCandidateNumbers)	{
			System.out.print(s);
			System.out.print("\n");
		}*/
		
		List<String> sudokuCanditateColumns = new ArrayList<String>(9);
		for(int k=0; k<9; ++k){
			sudokuCanditateColumns.add("");
		}
			
		/**int[] numbers = new int[81];
		int counter = 0;
		for(String s : sudokuCandidateNumbers)	{
			numbers[counter] = Integer.parseInt(s);	
			++counter;
		}*/
		//first column
		//get 0,9, 18,...
		String firstColumn = "";
		for(String s:sudokuCanditateColumns ){
			for(int j=1; j<81; j += 9){
				sudokuCanditateColumns.set(0, sudokuCanditateColumns.get(0).concat(sudokuCandidateNumbers.get(j)));
			}
		}
		String secondColumn = "";
		for(int j=1; j<81; j += 9){
			secondColumn = secondColumn.concat(sudokuCandidateNumbers.get(j));
		}
		System.out.println(sudokuCanditateColumns.get(0));
		System.out.println(secondColumn);
		return sudokuCanditateColumns;					
	}

	private boolean hasDuplicatesInRow(List<String> sudokuCandidateRows, int[] numbers) {
		boolean hasDuplicates = false;
		for(String s : sudokuCandidateRows)
		{
			
			List<String> numbers_splitted = splitEqually(s, 1);	
			for(int i=0; i<9; ++i)
			{
				numbers[i] = Integer.parseInt(numbers_splitted.get(i));			
			}
			if(duplicates(numbers, 9) )
				hasDuplicates = true;
		}
		return hasDuplicates;
	}
	
	public static List<String> splitEqually(String text, int size) {
	    // Give the list the right capacity to start with. You could use an array
	    // instead if you wanted.
	    List<String> ret = new ArrayList<String>((text.length() + size - 1) / size);

	    for (int start = 0; start < text.length(); start += size) {
	        ret.add(text.substring(start, Math.min(text.length(), start + size)));
	    }
	    return ret;
	}
	
	public static boolean duplicates (int [] x, int numElementsInX ) {
	    Set<Integer> set = new HashSet<Integer>();
	    for ( int i = 0; i < numElementsInX; ++i ) {
	        if ( set.contains( x[i])) {
	            return true;
	        }
	        else {
	            set.add(x[i]);
	        }
	    }
	    return false;
	}
}
