import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {

	public static void main(String[] args) throws Exception {
		
		//readCSVMatrices(); //check bottom to see what this is for please 
		
		 Scanner in = new Scanner(System.in);
		
		int x = 9;
		int y = 9;
		
		int[][] matrix = new int[x][y];
		String line;
		for(int i = 0 ; i < x ; ++i) {
			line = in.nextLine();
			String[] nums = line.split(" ");
			for(int j = 0 ; j < y ; ++j) {
				matrix[i][j] = Integer.parseInt(nums[j]);
			}
		}
		in.close();
		//printMatrix(x, y, matrix);
		SudokuSolver solve = new SudokuSolver(0,0, matrix); //0,0 just acts as the starting place to solve from
		ArrayList<Integer> banned = new ArrayList<Integer>();
		banned.clear();
		solve.solveMatrix();
		printMatrix(x, y, solve.matrix);
		
		
	}
	
	public static void printMatrix(int x, int y, int[][] matrix) { //simple print function - can be improved for readability with spaces
		for(int i = 0; i < x ; ++i) {
			for(int j = 0 ; j < y ; ++j) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	public  static void readCSVMatrices() throws Exception {
		int num = 10;
		Scanner in = new Scanner(new File("sudoku.txt")); // this is causing an error due to project root. It isnt really that
		//important now but it will be for checking against a couple solvable sudokus. If someone wants to sort it so that we can use
		//the million sudokus as test cases so when we submit we know the algorithm definitively works this can be done later
		//basically emulates a marker
		SudokuSolver solve;
		in.nextLine();
		for(int i = 1 ; i < num ; ++i) {
			String line = in.nextLine();
			String[] components = line.split(","); //csv structure of original text = question,answer
			int[][] question = generateMatrix(components[0]); //get question component
			int[][] answer = generateMatrix(components[1]); // get answer (what we know is the valid answer by def
			solve = new SudokuSolver(0,0,question); //use our alg to solve
			question = solve.matrix; // get the matrix (unnecessary but chose to do like this)
			if(question != answer) { //if they aren't the same then clearly we failed
				System.out.println("failed at : " + num);
			}
		}
		in.close();
	}
	
	public static int[][] generateMatrix(String comp){ //just used to parse the text file string into a valid matrix
		int[][] matrix = new int[9][9];
		int count = 0;
		for(int i = 0 ; i < 9 ; ++i) {
			for(int j = 0 ; j < 9 ; ++j) {
				matrix[i][j] = Integer.parseInt(comp.charAt(count)+"");
				++j;
			}
		}
		return matrix;
	}

}
