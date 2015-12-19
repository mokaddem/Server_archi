import java.io.*;
import java.net.*;
import java.util.Random;
import java.lang.*;

class TCPClient
{

	public static void main(String argv[]) throws Exception
	{
		Socket socket = new Socket("192.168.0.19", 6789);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
	
		
		//double tab[][] = create_tab((Integer.parseInt(argv[0])));

		String filename= "total_time.txt";
		FileWriter fw = new FileWriter(filename,true); //the true will append the new data
//int iter = 5;
int k=0; double difference_time_total=0;
		for(int iter=1; iter<1000; iter=iter+50){ k++;
			double tab[][] = create_tab((int) (800*Math.random())+1); //random difficulty
			//print(tab);

			double lambda = 500;
			double prob = Math.random();
			double ExpoNumber = (-1/lambda) * Math.log(1-prob);
			Thread.sleep((int) ExpoNumber);
			long start_time_total = System.nanoTime();
			outputStream.writeObject(tab);
			System.out.println("Sending matrix");

			double receivedTab[][] = (double[][]) inputStream.readObject();
			System.out.println("Received matrix");
			long end_time_total = System.nanoTime();
			difference_time_total += (end_time_total - start_time_total)/1e6;
//			System.out.println("iter="+iter+"\ttotal time="+ difference_time_total);
//			System.out.println("iter="+iter+"\ttotal time="+ difference_time_total);
			fw.write(String.valueOf(difference_time_total)+"\n");
			fw.flush();
		}
		//print(receivedTab);
		System.out.println("Avg time="+ difference_time_total/k);
		fw.close();
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

