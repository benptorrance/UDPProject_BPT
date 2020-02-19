/**
 * @author Ben Torrance
 */
import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 
import java.net.SocketException; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class UDPServer
{ 
    public static void main(String[] args) throws IOException, FileNotFoundException 
    { 
		//Prints out the timestamp of when the server was launched.
		String timeStart = new SimpleDateFormat("hh:mmaa").format(new Date());
		String dateStart = new SimpleDateFormat("MMMM dd, yyyy").format(new Date());
		System.out.println("Server Started at " + timeStart + " on " + dateStart);
		
        //Create a socket to listen at port 2040 and the bytes to hold data.
        DatagramSocket ds = new DatagramSocket(2040); 
        byte[] receive = new byte[1024];
		byte[] send = new byte[1024];
		
		//
		File file = new File("quote.csv");
		Scanner sc = new Scanner(file);
		ArrayList<String> quoteList = new ArrayList<String>();
		
		//Iterates through the Quotes file and adds the quotes to the ArrayList.
		int lineNumber = 1;
		while(sc.hasNextLine()){
            String line = sc.nextLine();
            quoteList.add(line);
            lineNumber++;
        }      
		
		//Create the DatagramPackets for sending and receiving data
        DatagramPacket DpReceive = null; 
		DatagramPacket DpSend = null;
        while (true) 
        { 
            
            //Create a DatgramPacket to receive the data. 
            DpReceive = new DatagramPacket(receive, receive.length); 
  
            //Revieve the data in byte buffer. 
            ds.receive(DpReceive); 
			
			String timeStamp = new SimpleDateFormat("MM/dd/yyyy hh:mmaa").format(new Date());
			
            System.out.println("Request Received from " + DpReceive.getAddress().getHostAddress() +": "+ DpReceive.getPort() + " " + timeStamp); 
			
            //Exit the server if the client sends "<EXIT>" 
            if (data(receive).toString().equals("<EXIT>")) 
            { 
                System.out.println("Client sent <EXIT>.....EXITING"); 
                break; 
            } 
  
			//Generate random number within 1 to 10 
            int rand = (int)(Math.random() * 20);
			
			//Uses the random number to select a quote from the ArrayList.
			String quote = quoteList.get(rand);
			send = quote.getBytes();
			
			//Creates a DatagramPacket for sending and sends the quote back to the client.
			InetAddress ip = InetAddress.getByName(DpReceive.getAddress().getHostAddress());
			DpSend = new DatagramPacket(send, send.length,ip,DpReceive.getPort());
			ds.send(DpSend);
			
            //Clear the buffer after every message. 
            receive = new byte[1024]; 
			send = new byte[1024];
        } 
    } 
  
    // A utility method to convert the byte array 
    // data into a string representation. 
    public static StringBuilder data(byte[] a) 
    { 
        if (a == null) 
            return null; 
        StringBuilder ret = new StringBuilder(); 
        int i = 0; 
        while (a[i] != 0) 
        { 
            ret.append((char) a[i]); 
            i++; 
        } 
        return ret; 
    } 
}
