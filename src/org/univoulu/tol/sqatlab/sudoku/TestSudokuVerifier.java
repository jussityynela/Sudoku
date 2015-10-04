package org.univoulu.tol.sqatlab.sudoku;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestSudokuVerifier {

	private static final String correctSudokuString = "417369825632158947958724316825437169791586432346912758289643571573291684164875293";
	private static final String SudokuStringWithNegativeNumber = "417-36982563215894795872431682543716979158643234691275828964357157329168416487529";
	
	private static final String incorrectSudokuString = "123456789912345678891234567789123456678912345567891234456789123345678912234567891";
	private static final String stringHasRowsWithSameNumberButNotInColumnOrSubGrid = "123123123456456456789789789321321321654654654987987987231231231564564564897897897";
	private static final String stringHasColumnsWithSameNumberButNotInRowOrSubgrid= "147369258258147369369258147147369258258147369369258147147369258258147369369258147";
	private static final String stringHasSubGridWithSameNumberButNotInRowOrColumn= "123456789234567891345678912456789123567891234678912345789123456891234567912345678";
	
	private static final String stringHasSubGridWih123456789= "123123123456456456789789789123123123456456456789789789123123123456456456789789789";
	
	
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
	
	@Test(expected=SudokuStringAlphaException.class)
	public void testStringNonNumericException() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		sudokuVerifier.verify("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
		assertEquals(-3, sudokuVerifier.verify(stringHasRowsWithSameNumberButNotInColumnOrSubGrid) );
	}
	
	@Test
	public void testDigitCanAppearOnlyOnceInColumnNeg2() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();		
		//Assert
		assertEquals(-4, sudokuVerifier.verify(stringHasColumnsWithSameNumberButNotInRowOrSubgrid) );
	}
	
	@Test
	public void testDigitCanAppearOnlyOnceInSubGridNeg2() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();		
		//Assert
		assertEquals(-2, sudokuVerifier.verify(stringHasSubGridWithSameNumberButNotInRowOrColumn) );
	}
	
	@Test
	public void testSplitGridToSubGrids() {
		//arrange
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		
		List<String> expected = Arrays.asList("123234345", 
											  "456567678", 
											  "789891912", 
											  "456567678", 
											  "789891912", 
											  "123234345", 
											  "789891912", 
											  "123234345",
											  "456567678"
											  );
		//Assert
		assertEquals(expected, sudokuVerifier.splitStringToSubGrids(stringHasSubGridWithSameNumberButNotInRowOrColumn, 9) );
	}
	
	@Test
	public void testSplitToCharacterMatrix() {
		//arrange
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		List<String> sudokuCandidateRows = sudokuVerifier.splitEqually(stringHasSubGridWithSameNumberButNotInRowOrColumn, 9);
		char[][] expected = {"123456789".toCharArray(),
							 "234567891".toCharArray(),
							 "345678912".toCharArray(),
							 "456789123".toCharArray(),
							 "567891234".toCharArray(),
							 "678912345".toCharArray(),
							 "789123456".toCharArray(),
							 "891234567".toCharArray(),
							 "912345678".toCharArray(),
							  };

		//Assert
		assertArrayEquals(expected, sudokuVerifier.splitStringToCharMatrix(sudokuCandidateRows) );
	}
	
	@Test
	public void testSplitToCharacterMatrixHas123456789() {
		//arrange
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		List<String> sudokuCandidateRows = sudokuVerifier.splitEqually(stringHasSubGridWih123456789, 9);
		char[][] expected = {"123123123".toCharArray(),
							 "456456456".toCharArray(),
							 "789789789".toCharArray(),
							 "123123123".toCharArray(),
							 "456456456".toCharArray(),
							 "789789789".toCharArray(),
							 "123123123".toCharArray(),
							 "456456456".toCharArray(),
							 "789789789".toCharArray(),
							  };
		
		for(int x = 0; x <9; ++x){
			for(int y = 0; y <9; ++y){
				System.out.print(expected[x][y]);
			}
			System.out.println("");
		}
		System.out.println("");
		//Assert
		assertArrayEquals(expected, sudokuVerifier.splitStringToCharMatrix(sudokuCandidateRows) );
	}
}

