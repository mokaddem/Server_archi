import java.io.*;
import java.net.*;
import java.util.Random;

class TCPClient
{

	public static void main(String argv[]) throws Exception
	{
		Socket socket = new Socket("localhost", 6789);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
	
		
		double tab[][] = create_tab((Integer.parseInt(argv[0])));

		System.out.println("Sending matrix");
		//print(tab);
		outputStream.writeObject(tab);
		
		double receivedTab[][] = (double[][]) inputStream.readObject();
		System.out.println("Received matrix");
		//print(receivedTab);
		socket.close();
    }

    public static void print(double array[][]){
		for(int i=0; i<array.length; i++){
			for(int j=0; j<array.length; j++){  
				System.out.print(array[i][j]+" ");
			}
			System.out.println("");
		}
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

