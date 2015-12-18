import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

class TCPServer
{
	public static void main(String argv[]) throws Exception
	{
		String clientSentence;
		String capitalizedSentence;
		ServerSocket socket = new ServerSocket(6789, 1);

		while(true)
		{		
			Socket connectionSocket = socket.accept();
			InputStream inputStream = connectionSocket.getInputStream();
			OutputStream outputStream = connectionSocket.getOutputStream();
			
			try{
				/* IN */			
				byte[] sizeAr = new byte[4];
		    	inputStream.read(sizeAr);
		    	int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
			    byte[] imageAr = new byte[size];
				inputStream.read(imageAr);
		    	BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
		    	System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
				ImageIO.write(image, "bmp", new File("modified_imageS.bmp"));


				/* OUT */
		        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		        ImageIO.write(image, "bmp", byteArrayOutputStream); 
		        byte[] sizeIm = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();

				System.out.println("Sent " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
		        outputStream.write(sizeIm);
		        outputStream.write(byteArrayOutputStream.toByteArray());
		        outputStream.flush();

			}
		    catch (IOException e)
		    {
		        System.out.println("Error: " + e.toString());
		    }
		}
	}

	public static BufferedImage computationFunction(BufferedImage clientSentence){
		return clientSentence;
	}
}