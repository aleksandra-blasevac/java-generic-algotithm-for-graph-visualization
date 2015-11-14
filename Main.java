//Aleksanra Blasevac, 13158325, Games Development

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        //The name of the text file (in the same directory as this java file) containing an adjacency matrix.
        final String fileName = "AI2015.txt";
        final int numberOfOrderings = 100;        
        final int n = countLines(fileName);
        
        int[][] adjacency = new int[n][n];
        
        readInput(fileName, adjacency);
        print2DArray(adjacency);
        checkSymmetry(adjacency);
        
        System.out.println("\n\n");
            
        int[][] orderings = create2DOrderings(0, (n - 1), numberOfOrderings);
        print2DArray(orderings);        
    }
    
    //Counts how many lines there is in the specified file
    private static int countLines(String fileName) {
        int count = -1;
        try {
            LineNumberReader reader = new LineNumberReader(new FileReader(fileName));
            reader.skip(Long.MAX_VALUE);
            count = reader.getLineNumber() + 1;
            reader.close();
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
            System.exit(1);
        }
        
       return count; 
    }
    

    //Reads the adjacency matrix from the specified file into a 2D array.
    //If the file doesn't contain the matrix in the right format it prints an error message and terminates the program.
    private static void readInput(String fileName, int[][] array) {
        try {
            Scanner scanner = new Scanner(new FileReader(fileName));
            
            while (scanner.hasNextInt()) {               
                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < array[i].length; j++) {
                        array[i][j] = scanner.nextInt();
                    }
                }
            }
            scanner.close();
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
            System.exit(1);
        }
    }
    
    
    //Prints a 2D array of integers
    private static void print2DArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }  
        System.out.println();
    }
    
    
    //Checks if specified 2D array (matrix) is symmetric.
    //If the matrix is not symmetric it prints an error message and terminates the program.
    private static void checkSymmetry(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != array[j][i]) {
                    System.out.println("Matrix isn't symetric. (error in [" + i + "][" + j + "])\n");
                    System.exit(1);
                }
            }
        }
    }

    
    //Returns an array of integers starting at startNumber and ending at endNumber in random order (non of the integers is repeated).
    private static int[] randomiseOrder(int startNumber, int endNumber) {      
        ArrayList<Integer> numbersList = new ArrayList<Integer>();
        
        for (int i = startNumber; i <= endNumber; i++) {
            numbersList.add(i);
        }
        
        Collections.shuffle(numbersList);
        
        int[] randomNumbers = new int[numbersList.size()];
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = numbersList.get(i);
        }
        
        return randomNumbers;
    }
    
    
    //Returns a 2D array of randomised integers. None of the integers are repeated in any specific row, also non of the rows are duplicated.
    private static int[][] create2DOrderings(int startNumber, int endNumber, int n) {
        int[][] orderings = new int[n][endNumber - startNumber + 1];
        
        for (int i = 0; i < n; i++) {
            boolean inserted = false;
            
            while (!inserted) {
                int[] temp = randomiseOrder(startNumber, endNumber);
                boolean found = false;
                for (int j = 0; j < i && !found; j++) {
                    if (Arrays.equals(orderings[j], temp)) found = true;
                }
                if (!found) {
                    orderings[i] = temp;
                    inserted = true;
                }
            }                
        }        
        return orderings;
    }
    
}