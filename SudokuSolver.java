import java.awt.PointerInfo;

public class SudokuSolver {
		
	int [][] matrix;
	int x; //rows
	int y; //columns
	
	public SudokuSolver(int x, int y, int[][] matrix) {
		this.matrix = matrix;
		this.x = x;
		this.y = y;
	}
	
	public boolean solveMatrix(int i, int j) { //i = row, j = col and num is what we want to place
			
		if(i == 9) {
			return true;
		}
		
		if(matrix[i][j] == 0) {
			boolean[] valid = getValidNumbers(i, j);
			for(int temp = 1; temp < 10; ++temp) 
			{
				if(valid[temp-1])
					continue;
				matrix[i][j] = temp;
				int[] vals = update(i,j);
				if(solveMatrix(vals[0],vals[1])) {
					return true;
				}
			}
			matrix[i][j] = 0;
			return false;
		}
		else {
			int[] vals = update(i,j);
			return solveMatrix(vals[0],vals[1]);
		}
	}
	
	public boolean[] getValidNumbers(int row, int col)
	{
		//Defaults to false
		boolean[] valid = new boolean[9];
		
		//Set all values to true for row search
		for (int j = 0; j < 9; ++j)
		{
			if(matrix[row][j] != 0)
				valid[matrix[row][j]-1] = true;
		}
		
		//Set all values to true for col search
		for (int i = 0; i < 9; ++i)
		{
			if(matrix[i][col] != 0)
				valid[matrix[i][col]-1] = true;
		}
		
		//set all values to true for block search
		int baseRow = row/3 *3;
		int baseCol = col/3 *3;
		//printBlock(row,col);
		for(int i = 0 ; i < 3 ; ++i) {
			for(int j = 0 ; j < 3 ; ++j) {
				if(matrix[baseRow+i][baseCol+j] != 0) {
					valid[matrix[baseRow+i][baseCol+j]-1] = true;
				}
			}
		}
		
		return valid;
	}
	
	
	public int[] update(int i, int j) 
	{
		if(j == 8) {
			j=0;
			++i;
		}
		else {
			++j;
		}
		int[] vals = new int[2];
		vals[0] = i;
		vals[1] = j;
		return vals;
	}
	
	public boolean gridComplete() { //check the entirety of the grid
		for(int i = 0 ; i < 9 ; ++i) {
			for(int j = 0 ; j < 9 ; ++j) {
				if(matrix[i][j] == 0) { //if there are any zeros grid not complete
					return false;
				}
			}
		}
		return true; //no zeroes so it is complete
	}
	
	public boolean validMove(int row, int col, int num) { // find if the int choice is valid
		if(validHorizontal(row, col, num)) { //valid in hor
			if(validVertical(row, col, num)) { // valid in ver
				if(validBlock(row, col, num)) { // valid in block
					if(num == 10) {
						return false;
					}
					return true; //all are valid therefore valid move
				}
			}
		}
		return false; //not valid in at least one of the three above
	}
	
	public boolean validHorizontal(int row, int col, int num) {
		for(int j = 0 ; j < 9 ; ++j) {
			if(matrix[row][j] == num) { //check to see if there is a num in my row that has the same value I want
				return false;
			}
		}
		return true;
	}
	
	public boolean validVertical(int row, int col, int num) {
		for(int i = 0 ; i < 9 ; ++i) {
			if(matrix[i][col] == num) { //check to see if there is a num in my column with the value I want
				return false;
			}
		}
		return true;
	}
	
	public boolean validBlock(int row, int col, int num) { //check if there is a num in my column with a value I want
		int baseRow = row/3 *3;
		int baseCol = col/3 *3;
		//printBlock(row,col);
		for(int i = 0 ; i < 3 ; ++i) {
			for(int j = 0 ; j < 3 ; ++j) {
				if(num == matrix[baseRow+i][baseCol+j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void printMatrix() { //simple printing
		for(int i = 0 ; i < 9 ; ++i) {
			for(int j = 0 ; j < 9 ; ++j) {
				System.out.print(matrix[i][j]);
				//if(j != 8) {
					System.out.print(" ");
				//}
			}
			if(i != 8) {
				System.out.println();
			}
		}
	}
	
	public void printBlock(int row, int col) {
		int baseRow = row/3 *3;
		int baseCol = col/3 *3;
		for(int i = 0 ; i < 3 ; ++i) {
			for(int j = 0 ; j < 3 ; ++j) {
				System.out.print(matrix[baseRow+i][baseCol+j]);
			}
			System.out.println();
		}
	}
}
