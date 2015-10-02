package org.univoulu.tol.sqatlab.sudoku;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSudokuVerifier {

	@Test(expected=SudokuStringTooLongException.class)
	public void testStringTooLongException() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		sudokuVerifier.verify("1234567891234567891234567891234567891234567891234567891234567891234567891234567890");
	}
	
	@Test(expected=SudokuStringTooShortException.class)
	public void testStringTooShortException() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		sudokuVerifier.verify("1");
	}
	
	@Test(expected=SudokuStringEmptyException.class)
	public void testStringEmptyException() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		sudokuVerifier.verify("");
	}
	
	@Test(expected=SudokuStringEmptyException.class)
	public void testCorrectSudokuString() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		sudokuVerifier.verify("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
	}

}
