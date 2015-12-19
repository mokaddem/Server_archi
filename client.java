import java.io.*;
import java.net.*;
import java.util.Random;
import java.lang.*;

class TCPClient
{

	public static void main(String argv[]) throws Exception
	{		
		//double tab[][] = create_tab((Integer.parseInt(argv[0])));

		String filename= "total_time.txt";
		FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		//int iter = 5;
		int k=0; 
		//double difference_time_total=0;
		
		for(int iter=1; iter<500; iter=iter+1){ k++;
			
			double lambda = 500;
			double prob = Math.random();
			double ExpoNumber = (-1/lambda) * Math.log(1-prob);
			Thread.sleep((int) ExpoNumber*1000);

			Socket socket = new Socket("192.168.0.7", 6789);
			ClientHandleThread clieThread = new ClientHandleThread(socket);
            clieThread.start(); 
			System.out.println("Thread n°"+iter);

/*			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());			
	
//			double tab[][] = create_tab((int) (800*Math.random())+1); //random difficulty
			double tab[][] = create_tab((int) (75*Math.random())+1);
			//print(tab);

			double lambda = 500;
			double prob = Math.random();
			double ExpoNumber = (-1/lambda) * Math.log(1-prob);
			System.out.println(ExpoNumber);
//			Thread.sleep((int) ExpoNumber);
			long start_time_total = System.nanoTime();
			outputStream.writeObject(tab);
//			System.out.println("Sending matrix");

			double receivedTab[][] = (double[][]) inputStream.readObject();
//			System.out.println("Received matrix");
			long end_time_total = System.nanoTime();
//			difference_time_total += (end_time_total - start_time_total)/1e6;
//			System.out.println("iter="+iter+"\ttotal time="+ difference_time_total);
//			System.out.println("iter="+iter+"\ttotal time="+ difference_time_total);
//			fw.write(String.valueOf(difference_time_total)+"\n");
//			fw.flush();
			fw.close();
			socket.close();
*/		}
		//print(receivedTab);
//		System.out.println("Avg time="+ difference_time_total/k);
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

