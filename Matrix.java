import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Comparator;

class Matrix implements Comparator<Matrix>
{
	int size;
	double[][] matrix;
	ClientServiceThread thread;

    public Matrix(double[][] matrix, ClientServiceThread thread){
    	this.size = matrix.length;
    	this.matrix = matrix;
	this.thread = thread;
    }
    
    public int compareTo(Matrix otherMatrix) {
	// Return > 0 if current matrix is smaller.
	return otherMatrix.size - this.size;
	
    }	
	
    public int compare(Matrix matrix, Matrix otherMatrix)
    {
        // Return > 0 if current matrix is smaller.
	return otherMatrix.size - this.size;
    }

}

