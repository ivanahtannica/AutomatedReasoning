package pl.util;

public class Utils {
	
	public static int[][] generateTruthTable(int numOfSymbols) {
		int rows = (int) Math.pow(2,numOfSymbols);
		int[][] table = new int[rows][numOfSymbols];
		
        for (int i=0; i<rows; i++) {
            for (int j=numOfSymbols-1; j>=0; j--) {
                table[i][j] = ((i/(int) Math.pow(2, j))%2);
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
        
		return table;
	}
}
