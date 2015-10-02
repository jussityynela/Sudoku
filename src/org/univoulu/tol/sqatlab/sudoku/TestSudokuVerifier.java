package org.univoulu.tol.sqatlab.sudoku;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSudokuVerifier {

	private static final String correctSudokuString = "417369825632158947958724316825437169791586432346912758289643571573291684164875293";
	private static final String SudokuStringWithNegativeNumber = "417-36982563215894795872431682543716979158643234691275828964357157329168416487529";
	
	private static final String incorrectSudokuString = "123456789912345678891234567789123456678912345567891234456789123345678912234567891";
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
	
	@Test
	public void testCorrectSudokuString() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		
		//Assert
		assertEquals(0, sudokuVerifier.verify(correctSudokuString) );
	}
	
	@Test
	public void testInCorrectSudokuString() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		
		//Assert
		assertNotEquals(0, sudokuVerifier.verify(incorrectSudokuString) );
	}
	
	@Test
	public void testAllStringArePositiveRuleNegativeOne() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		
		//Assert
		assertEquals(-1, sudokuVerifier.verify(SudokuStringWithNegativeNumber) );
	}

	@Test
	public void testDigitCanAppearOnlyOnceInRow() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();		
		//Assert
		assertEquals(-3, sudokuVerifier.verify(incorrectSudokuString) );
	}
}
