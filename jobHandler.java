import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.lang.*;


class ClientHandleThread extends Thread 
{ 
        Socket myClientSocket;

        public ClientHandleThread() 
        { 
            super(); 
        } 

        ClientHandleThread(Socket s) 
        { 
            myClientSocket = s; 

        } 

        public void run() 
        {            
            // Obtain the input stream and the output stream for the socket 
            // A good practice is to encapsulate them with a BufferedReader 
            // and a PrintWriter as shown below. 
            ObjectOutputStream outputStream = null;
	    ObjectInputStream inputStream = null;

            try 
            {                                
		double tab[][] = create_tab((int) (75*Math.random())+1);
		//print(tab);

		double lambda = 500;
		double prob = Math.random();
		double ExpoNumber = (-1/lambda) * Math.log(1-prob);
//		System.out.println(ExpoNumber);
//		Thread.sleep((int) ExpoNumber);
//		long start_time_total = System.nanoTime();
		outputStream.writeObject(tab);
//		System.out.println("Sending matrix");

		double receivedTab[][] = (double[][]) inputStream.readObject();
//		1	System.out.println("Received matrix");
//		long end_time_total = System.nanoTime();
//			difference_time_total += (end_time_total - start_time_total)/1e6;
//			System.out.println("iter="+iter+"\ttotal time="+ difference_time_total);
//			System.out.println("iter="+iter+"\ttotal time="+ difference_time_total);
//			fw.write(String.valueOf(difference_time_total)+"\n");
//			fw.flush();
//		fw.close();
//		socket.close();
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
	public static double[][] create_tab(int size){
		double tab[][] = new double[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				tab[i][j] = (double) ((int) (Math.random()*size));		
			}
		}
		return tab;
	}

}
