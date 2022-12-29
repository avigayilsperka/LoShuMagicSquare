/*Avigayil Marcus
 * Program 6
 * 03/23/2001
 */
import java.util.Scanner;

public class Program6 {

	public static void main (String args[]) {
		
		Scanner input = new Scanner(System.in);
		
		//collect square size and create
		System.out.println("Enter the size of the square you would like to calculate:");
		int size = input.nextInt();
		int[][] square = new int[size][size];
		
		square= collectData(input,square);
		
		printSquare(square);
		
		//create array of row totals
		int[]ArrayRowTotals = new int[size];
		
		for (int i=0; i <size; i++) {
			//calculate row by passing row and returning row total
			ArrayRowTotals[i] = calculateRow(square, i);
		}
		
		//array of column totals
		int[]ArrayColTotals = new int[size];
		for (int i=0; i<size; i++) {
			ArrayColTotals[i]= calculateCol(square,i);
		}
			
		//calculate diagnol
		int[]diagnols = calculateDiagnols(square);
		
		printInfo(ArrayRowTotals, ArrayColTotals,diagnols,size);
		
		input.close();
	}
	
	public static int[][] collectData(Scanner input, int[][]square){
		
		//possible inputs
		int[]listNum= {1,2,3,4,5,6,7,8,9};
		int num;
		
		//loop to fill square
		for (int i=0; i < square.length; i++) {
			System.out.println("Enter the data for row "+(i+1)+ ":");
			for (int j=0; j < square[i].length; j++) {
				boolean found=false;
				do {
				
					System.out.println("Enter the value of column "+ (j+1));
					num = input.nextInt();
				
					//search for number in list of possible inputs. If found, remove.
					for (int x=0; x<9; x++) {
						if(listNum[x]==num) {
							listNum[x]=0;
							found = true;	
						}		
					}
					//number already used
					if (found==false) {
						System.out.println("That number was already entered or is not from 1-9. Please pick a different number.");
					}
				// if already used, repeat
				}while(!found);
			
				square [i][j]= num;
			}
		}
		return square;
						
	}
	public static void printSquare(int[][]square) {
		//loop through and print
		for (int i=0; i<square.length; i++) {
			System.out.println();
			for(int j=0; j<square.length;j++) {
				System.out.print(square[i][j]+"\t");
			}
			//for spacing
			System.out.println();
		}
		System.out.println();
	
	}
	
	public static int calculateRow (int[][]square, int i) {
		
		//initialize to total to first element in row
		int total = square[i][0];
		//calculate and return row
		for (int j=1; j<square.length;j++) {
			total += square [i][j];
		}
		return total;
	}
	
	public static int calculateCol(int[][]square, int i) {
		
		//initialize to first in column and calculate remaining column 
		int total = square[0][i];
		for (int j=1; j<square.length;j++) {
			total += square[j][i];
		}
		return total;
	}
	
	public static int[] calculateDiagnols(int[][]square) {
		//set to first item in array
		int total = square[0][0];
		for (int i=1; i<square.length; i++) {
			total += square[i][i];
		}
		
		//calculate second diagnol
		int total2 = square[square.length-1][0],j=1;
		
		for (int i=square.length-2; i>=0; i--) {
			total2 += square[i][j++];
		}
		//return array of diagnol totals
		int[]diagnols = new int[2];
		diagnols[0]= total;
		diagnols[1]= total2;
		
		return diagnols;
	}
	public static void printInfo(int[]row, int[]col, int[]diagnol, int size) {
		
		//print row totals
		for (int x =0; x<size;x++) {
			System.out.println("Total of row "+(x+1)+": "+row[x]);	
		}
		
		//print col totals
		for (int x =0; x<size;x++) {
			System.out.println("Total of column "+(x+1)+": "+col[x]);
		}
		
		//print diagnol totals
		for (int x =0; x<2;x++) {
			System.out.println("Total of diagnol "+(x+1)+": "+diagnol[x]);
		}
		
		//check if totals are equal
		int rowTotal=row[0], colTotal = col[0];
		boolean square=true;
		
		if (diagnol[0]!=diagnol[1])
			square = false;
		
		for (int x=1; x<size; x++) {
			if(rowTotal!=row[x]||colTotal!= col[x])
				square =false; 
		}
		
		if(square) {
			System.out.print("You have entered a valid Lo Shu Magic Square");
			}
		else 
			System.out.println("Your square is not valid.");
		}	
	}	


