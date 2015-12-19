import java.io.*;
import java.net.*;
import java.util.Random;

class Matrix
{
	int size;
	int[][] matrix;

    public Matrix(int[][] matrix){
    	this.size = matrix.length;
    	this.matrix = matrix;
    }
    
    public int compareTo(Matrix otherMatrix) {
		// Return > 0 if current matrix is smaller.
		return otherMatrix.size - this.size;
		
	}	
}

