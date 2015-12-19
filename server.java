import java.io.*;
import java.net.*;
import java.lang.*;

class TCPServer
{
	static int EXPONENT = 10;
	public static void main(String argv[]) throws Exception
	{
		
		ServerSocket socket = new ServerSocket(6789, 1);
		
		String filename = "computation_time.txt";
		FileWriter fw = new FileWriter(filename,true);
	
		Socket connectionSocket = socket.accept();
		System.out.println("-> Connected to client");
		ObjectOutputStream outputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
		ObjectInputStream inputStream = new ObjectInputStream(connectionSocket.getInputStream());

		while(true)
		{					
			double tab[][] = (double[][]) inputStream.readObject();
			System.out.println("Matrix retreived");
			long start_time_computation = System.nanoTime();
			double computedTab[][] = computationFunction(tab);
			System.out.println("Matrix computed");
			long end_time_computation = System.nanoTime();
			double difference_time_computation = (end_time_computation - start_time_computation)/1e6;
			System.out.println("computation time="+difference_time_computation);
			fw.write(String.valueOf(difference_time_computation)+"\n");
			fw.flush();
			//print(computedTab);
			System.out.println("Sending new matrix");
			outputStream.writeObject(computedTab);  
			System.out.println("-------------------------------");
		}
	}

	public static double[][] computationFunction(double array[][]){
		return expn(array, EXPONENT);
	}


	public static double [][] expn(double[][] matrix, int exponent){
		double[][] result = matrix;
		double[][] a = matrix;
		for (int n = 1; n < exponent; ++ n)
			result = mult(result, a);
		return result;
	}
	public static double [][] mult(double[][] A, double[][] B){
		int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;
        double[][] C = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;		
	}

	public static void print(double array[][]){
		for(int i=0; i<array.length; i++){
			for(int j=0; j<array.length; j++){  
				System.out.print(array[i][j]+" ");
			}
			System.out.println("");
		}
    }
}
