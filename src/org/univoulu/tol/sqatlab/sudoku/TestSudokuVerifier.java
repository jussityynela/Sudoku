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

}
