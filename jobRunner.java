import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.lang.*;


class ClientServiceThread extends Thread 
{ 
        Socket myClientSocket;
	double tab[][];

        public ClientServiceThread() 
        { 
            super(); 
        } 

        ClientServiceThread(Socket s, double tab[][]) 
        { 
            myClientSocket = s; 
	    this.tab = tab;
        } 

        public void run() 
        {            
            // Obtain the input stream and the output stream for the socket 
            // A good practice is to encapsulate them with a BufferedReader 
            // and a PrintWriter as shown below. 
            ObjectOutputStream outputStream = null;
	    ObjectInputStream inputStream = null;

            // Print out details of this connection 
            System.out.println("Accepted Client Address - " + myClientSocket.getInetAddress().getHostName()); 

            try 
            {                                
				outputStream = new ObjectOutputStream(myClientSocket.getOutputStream());
				inputStream = new ObjectInputStream(myClientSocket.getInputStream());

			//	double tab[][] = (double[][]) inputStream.readObject();
			//	System.out.println("Matrix retreived");
			//	long start_time_computation = System.nanoTime();
				double computedTab[][] = computationFunction(tab);
			//	System.out.println("Matrix computed");
			//	long end_time_computation = System.nanoTime();
			//	double difference_time_computation = (end_time_computation - start_time_computation)/1e6;
			//	System.out.println("computation time="+difference_time_computation);
			//	fw.write(String.valueOf(difference_time_computation)+"\n");
			//	fw.flush();
				//print(computedTab);
				System.out.println("Sending new matrix for client - " + myClientSocket.getInetAddress().getHostName());
				outputStream.writeObject(computedTab);  
			//	System.out.println("-------------------------------");
            } 
            catch(Exception e) 
            { 
                e.printStackTrace(); 
            } 
            finally 
            { 
                // Clean up 
                try 
                {                    
                    inputStream.close(); 
                    outputStream.close(); 
                    myClientSocket.close(); 
                } 
                catch(IOException ioe) 
                { 
                    ioe.printStackTrace(); 
                } 
            } 
        } 


	public static double[][] computationFunction(double array[][]){
		int EXPONENT = 10;
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


}
