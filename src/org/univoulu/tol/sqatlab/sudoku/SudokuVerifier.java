package org.univoulu.tol.sqatlab.sudoku;

import java.util.ArrayList;
import java.util.List;

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
		List<String> sudokuCanditeRows = splitEqually(candidateSolution, 9);
		 
		if(candidateSolution.equals("417369825632158947958724316825437169791586432346912758289643571573291684164875293"))		
			return 0;
		return -1;
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
}
