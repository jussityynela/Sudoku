package org.univoulu.tol.sqatlab.sudoku;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSudokuVerifier {

	private static final String correctSudokuString = "417369825632158947958724316825437169791586432346912758289643571573291684164875293";
	private static final String SudokuStringWithNegativeNumber = "417-36982563215894795872431682543716979158643234691275828964357157329168416487529";
	
	private static final String incorrectSudokuString = "123456789912345678891234567789123456678912345567891234456789123345678912234567891";
	private static final String stringHasRowsWithSameNumberButNotInColumn = "111111111222222222333333333444444444555555555666666666777777777888888888999999999";
	private static final String stringHasColumnsWithSameNumberButNotInRow= "123456789123456789123456789123456789123456789123456789123456789123456789123456789";
	private static final String stringHasSubGridWithSameNumberButNotInRow= "123456789234567891345678912456789123567891234678912345789123456891234567912345678";
	
	
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
	public void testDigitCanAppearOnlyOnceInRowNeg3() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();		
		//Assert
		assertEquals(-3, sudokuVerifier.verify(stringHasRowsWithSameNumberButNotInColumn) );
	}
	
	@Test
	public void testDigitCanAppearOnlyOnceInColumnNeg2() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();		
		//Assert
		assertEquals(-4, sudokuVerifier.verify(stringHasColumnsWithSameNumberButNotInRow) );
	}
	
	@Test
	public void testDigitCanAppearOnlyOnceInSubGridNeg2() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();		
		//Assert
		assertEquals(-2, sudokuVerifier.verify(stringHasSubGridWithSameNumberButNotInRow) );
	}
	
	@Test
	public void testSplitGridToSubGrids() {
		//arrange
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		List<String> expected = Arrays.asList("123234345", "345456567", "789891912", "456567678", "7898919123", "123234345", "789891912", "123234345", "456567678");
		//Assert
		assertEquals(-2, sudokuVerifier.splitStringToSubGrids(stringHasSubGridWithSameNumberButNotInRow, 9) );
	}
}
