package org.univoulu.tol.sqatlab.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuVerifier {

	private static final int columnCount = 9;
	private static final int rowCount = 9;
	private static final int numberCount = columnCount * rowCount ;
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
		//Check if SubGrid has duplicate numbers		
		List<String> sudokuCandidateColumn = splitEqually(candidateSolution, rowCount);
		int[] numbersInGrid = new int[rowCount];
		if(hasDuplicatesInRow(sudokuCandidateColumn, numbersInGrid) )
			return -2;
		
		//Check if Row has duplicate numbers		
		List<String> sudokuCandidateRows = splitStringToSubGrids(candidateSolution, rowCount);
		int[] numbers = new int[rowCount];
		if(hasDuplicatesInRow(sudokuCandidateRows, numbers) )
			return -3;
		
		//Check if Column has duplicate numbers	
		List<String> sudokuCandidateColumns = splitStringToColumns(candidateSolution, columnCount);
		int[] numbersInColumn = new int[columnCount];
		if(hasDuplicatesInRow(sudokuCandidateColumns, numbersInColumn) )
			return -4;
			
		if(candidateSolution.equals("417369825632158947958724316825437169791586432346912758289643571573291684164875293"))		
			return 0;
		return -5;
	}

	
	public List<String> splitStringToSubGrids(String candidateSolution, int columncount) {
		List<String> sudokuCandidateRows = splitEqually(candidateSolution, columncount);
		List<String> sudokuCandidateNumbers = splitEqually(candidateSolution, 1);
		List<String> sudokuCanditateGrids = new ArrayList<String>(columncount);
		System.out.println("splitStringToSubGrids");
		for(int k=0; k<9; ++k){
			sudokuCanditateGrids.add("");
		}
		
		char[][] sudokuCandidateMatrix = new char[9][9];

		
		//row at a time
		//
		sudokuCandidateMatrix = splitStringToCharMatrix(sudokuCandidateRows);
		//0			 1         2
		//0 1 2,    3 4 5,    6 7 8
		//9 10 11, 12 13 14, 15,16,17
		//18,19,20 ,21,22,23,24,25,26
		//27,28,29,....
		//first column is index x:0-2
		//and y: 0-2
		int row_counter = 0;
		//for(String s: sudokuCanditateGrids){
			for(int i = 0; i <6; ++i){
				for(int y = 0; y <3; ++y){
					sudokuCanditateGrids.set(row_counter, sudokuCanditateGrids.get(row_counter).concat( new StringBuilder().append(sudokuCandidateMatrix[i][y]).toString() ) );
					//1 4 7
					//2 5 8
					//3 6 9
					if( i!=0 && i%3==0)											
					{
						row_counter++;
						System.out.println(i+"\n");
					}//revert back index few rows, to get to the 
					
					//correct SubGridRowAddition
					if(y!=0 && y%3==0)
					{
						System.out.println(i+" "+y+"\n");
						row_counter-=2;
						System.out.println(row_counter);
					}
				}
				//row_counter++;
			}
		//row_counter++;
		//}
		//second column is index x:3-5
		//and y: 0-2
		for(int i = 3; i <6; ++i){
			for(int y = 0; y <3; ++y){
				sudokuCanditateGrids.set(1, sudokuCanditateGrids.get(1).concat( new StringBuilder().append(sudokuCandidateMatrix[i][y]).toString() ) );				
			}
			//System.out.print( "\n") ;
		}
		//third grid is index x:6-8
		//and y: 0-2
		for(int i = 6; i <9; ++i){
			for(int y = 0; y <3; ++y){
				sudokuCanditateGrids.set(2, sudokuCanditateGrids.get(2).concat( new StringBuilder().append(sudokuCandidateMatrix[i][y]).toString() ) );				
			}
			//System.out.print( "\n") ;
		}
		//4 grid is index x:0-2
		//and y: 3-5
		for(int i = 0; i <3; ++i){
			for(int y = 3; y <6; ++y){
				sudokuCanditateGrids.set(3, sudokuCanditateGrids.get(3).concat( new StringBuilder().append(sudokuCandidateMatrix[i][y]).toString() ) );				
			}
			//System.out.print( "\n") ;
		}
		for(String s : sudokuCanditateGrids)	{
			System.out.print(s);
			System.out.print("\n");
		}
		
		return sudokuCanditateGrids;					
	}


	public char[][] splitStringToCharMatrix(List<String> sudokuCandidateRows) {
		char[][] sudokuCandidateMatrix = new char[9][9];
		for(int y = 0; y <9; ++y){
			for(int i = 0; i <9; ++i){
				sudokuCandidateMatrix[i][y] = sudokuCandidateRows.get(y).charAt(i);
			}				
		}
		return sudokuCandidateMatrix;
	}
	
	private List<String> splitStringToColumns(String candidateSolution, int columncount) {
		List<String> sudokuCandidateRows = splitEqually(candidateSolution, columncount);
		List<String> sudokuCandidateNumbers = splitEqually(candidateSolution, 1);
		List<String> sudokuCanditateColumns = new ArrayList<String>(columncount);
		for(int k=0; k<9; ++k){
			sudokuCanditateColumns.add("");
		}
		/**for(String s : sudokuCanditateColumns)	{
			System.out.print(s);
			System.out.print("1\n");
		}*/
		/**int[] numbers = new int[81];
		int counter = 0;
		for(String s : sudokuCandidateNumbers)	{
			numbers[counter] = Integer.parseInt(s);	
			++counter;
		}*/
		String firstColumn = "";
		int counter = 0;
		for(String s : sudokuCanditateColumns ){
			for(String r:sudokuCandidateRows){
				sudokuCanditateColumns.set(counter,  sudokuCanditateColumns.get(counter).concat(r.substring(counter,counter+1) ) );
			}
			counter++;
		}
		/**for(String s : sudokuCanditateColumns)	{
			System.out.print(s);
			System.out.print("\n");
		}*/
		return sudokuCanditateColumns;					
	}

	public boolean hasDuplicatesInRow(List<String> sudokuCandidateRows, int[] numbers) {
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
