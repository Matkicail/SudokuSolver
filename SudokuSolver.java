import java.util.ArrayList;


public class SudokuSolver {
		
	int [][] matrix;
	int x; //rows
	int y; //columns
	
	public SudokuSolver(int x, int y, int[][] matrix) {
		this.matrix = matrix;
		this.x = x;
		this.y = y;
	}
	
	//problem here is that there (I assume and hence may be wrong) is a list of banned integers for a spot, since there are more valid integers 
	//The way I was going to initially solve this was with an additional class which just had ArrayLists of integers
	//if you failed a current entry then you look one move back, ban the integer currently there and try and solve for another integer
	//however if that fails you should move one back and repeat (ban and check for valid)
	//then when you go forward you should unban the banned integer for that place at current(i.e when moving forwards always clear banned ints
	//when moving backwards always add the int in that location to banned
	//never clear the last arraylist you were on which was where there was a banned int that caused the problem (or dont, this is where unsure)
	//unsure since there is a chance that the banned ints will cause a problem for any one of them and hence they are invalid numbers that could
	//be added if we went back.
	//this is fine since if we moved back too many times we'd get to a scenario where all numbers are banned and hence the previous number may 
	//be the issue and at this point we sort that position out, then move forward and clear as we go forward (reseting)
	
	public void solveMatrix() {
		while(gridComplete() == false) {
			System.out.println("--------------------");
			for(int i = 0 ; i < 9 ; ++i) {//runs rows
				for(int j = 0 ; j < 9 ; ++j) { //runs cols
					if(matrix[i][j] == 0) {
						boolean someValidMove = false; //assume no valid move
						for(int num = 1 ; num < 10 ; ++num) {
							//System.out.print(" " + num + " ");
							if(validMove(i,j,num)){
								matrix[i][j] = num;
								someValidMove = true; //we found a valid move
							}
						}
						if(someValidMove == false) {//clearly there was an issue and some move we made prior is an issue so we must go back
							//have not dealt with this case
						}
					}
				}
			}
			printMatrix(); //print after each each attempt			
		}
	}
	
	
	public boolean gridComplete() { //check the entirety of the grid
		for(int i = 0 ; i < 9 ; ++i) {
			for(int j = 0 ; j < 9 ; ++j) {
				if(matrix[i][j] == 0) { //if there are any zeros grid not complete
					//System.out.println("false");
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
		//use integer division to solve
		int one = row/3;
		int temp1;
		int temp2;
		for(int i = 0 ; i < 2 ; ++i) {//index rows
			for(int j = 0 ; j < 2 ; ++j) {//index cols
				temp1 = one*3 + i;
				temp2 = one*3 +j;
				if(matrix[temp1][temp2] == num) {
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
			}
			System.out.println();
		}
	}
}
