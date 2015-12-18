import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

class TCPClient
{
	public static void main(String argv[]) throws Exception
	{
  
            Socket socket = new Socket("localhost", 6789);
            OutputStream outputStream = socket.getOutputStream();
			InputStream inputStream = socket.getInputStream();

      try{
            BufferedImage imageToSend = ImageIO.read(new File("hundreds-of-pokemons.bmp"));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(imageToSend, "bmp", byteArrayOutputStream); 
            byte[] sizeIm = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();

            /* OUT */
            outputStream.write(sizeIm);
            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.flush();
            System.out.println("Sent " + imageToSend.getHeight() + "x" + imageToSend.getWidth() + ": " + System.currentTimeMillis());

            /* IN */
            byte[] sizeAr = new byte[4];
        	inputStream.read(sizeAr);
        	int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
	        byte[] imageAr = new byte[size];
    	    inputStream.read(imageAr);
        	BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
        	System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());

            ImageIO.write(image, "bmp", new File("modified_imageC.bmp"));
        
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e.toString());
        }

        socket.close();
    }
}

