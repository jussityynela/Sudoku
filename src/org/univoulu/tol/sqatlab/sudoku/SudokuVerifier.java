package org.univoulu.tol.sqatlab.sudoku;

public class SudokuVerifier {

	public int verify(String candidateSolution) {
		// returns 1 if the candidate solution is correct
		if( candidateSolution.length() > 81 )
			throw new SudokuStringTooLongException();
		return 0;
	}
}
