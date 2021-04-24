import java.io.*;

//test if change
public class arrayReader
{	 
	static boolean isSafe(int[][] board,int row, int col, int num)
	{
		if(rowSafe(board, row, col, num) && colSafe(board, row, col, num) && squareSafe(board, row, col, num))
		{
			return true;
		}
		return false;
	}
	
	// method to check if number to be placed already exist in row
	static boolean rowSafe(int[][] board,int row, int col, int num)
	{
		for (int d = 0; d < board.length; d++)
		{			
			if (board[row][d] == num) 
			{
				return false;
			}
		}
		return true;
	}	
	
	// method to check if number to be placed already exist in column
	static boolean colSafe(int[][] board,int row, int col, int num)
	{
		for (int r = 0; r < board.length; r++)
		{	
			if (board[r][col] == num)
			{
				return false;
			}
		}
		return true;
	}
	// method to check if number to be placed already exist in square of 3x3 if 9x9 sudoku or 3x2 if 6x6 sudoku
	static boolean squareSafe(int[][] board,int row, int col, int num)
	{
		//row and column must always be equal to 
		row = (row/(board.length/3)*(board.length/3));
		col = (col/3)*3;
		for(int r=0; r<(board.length/3); r++)
		{
			for(int c=0; c<3; c++)
			{
				if(board[row+r][col+c] == num)
				{
					return false ;
				}
			}
		}
		return true;
	}
		// if there is no clash, it's safe
		
		public static boolean solveSudoku(int[][] board, int n)
		{
			int row = -1;
			int col = -1;
			boolean isEmpty = true;
			
			//traverse the array/puzzle for 0
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
				{
					if (board[i][j] == 0)
					{
						row = i;
						col = j;
						// We still have some remaining
						// missing values in Sudoku
						isEmpty = false;
						break;
					}
				}
				if (!isEmpty) 
				{
					break;
				}
			}
			
			// No empty space left
			if (isEmpty)
			{
				return true;
			}
			
			// Else for each-row backtrack
			else
			{
				for (int num = 1; num <= n; num++)
				{
					if (isSafe(board, row, col, num))
					{
						board[row][col] = num;
						if (solveSudoku(board, n))
						{
							// print(board, n);
							return true;
						}
						else
						{
							// replace it
							board[row][col] = 0;
						}
					}
				}
			}
			return false;
		}
		
		public static void print(
		int[][] board, int N)
		{
			// We got the answer, just print it
			for (int r = 0; r < N; r++)
			{
				for (int d = 0; d < N; d++)
				{
					System.out.print(board[r][d]);
					System.out.print(" ");
				}
				System.out.print("\n");
				
				if ((r + 1) % (int)Math.sqrt(N) == 0)
				{
					System.out.print("");
				}
			}
		}
	
	public static void main(String args[])
	{
		OurListQueue queue = new OurListQueue(); 
		//String input_file = args[0]; if using console to input file
		
		try
		{
			FileReader file_reader = new FileReader("input.txt"); //or input_file variable 
			BufferedReader br = new BufferedReader(file_reader);
			
			String str = "";
			int numofDimensions = 0;
			
			numofDimensions = Integer.parseInt(br.readLine()); //first line value will be the number of lines to check
			System.out.println("There are " + numofDimensions + " dimensions");
			int[][] A = new int [numofDimensions][numofDimensions];

			for(int i=0;i<numofDimensions;i++)
			{
				str = br.readLine();
				for (int j = 0; j < str.replace(" ","").length(); j++)//traversing the string
				{
					char x = str.replace(" ","").charAt(j);
					int a = Character.getNumericValue(x);
					queue.enqueue(a);
				}
			//	System.out.println();
			}		
			br.close();
			for(int r=0;r<numofDimensions;r++){
					for(int c=0;c<numofDimensions;c++){
						A[r][c]=queue.dequeue();
					}
			}
			System.out.println();
			
			if (solveSudoku(A, numofDimensions))
	        {
	            // print solution
	            print(A, numofDimensions);
	        }
	        else {
	            System.out.println("No solution");
	        }
			
		}catch(IOException e)
		{
			System.out.println("An exception occured");
		}
		
		
		
	}
}