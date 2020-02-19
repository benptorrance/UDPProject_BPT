/**
 * @author Ben Torrance
 */
import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 
import java.util.Scanner; 

public class UDPClient
{ 
	public static void main(String args[]) throws IOException 
	{ 
		Scanner sc = new Scanner(System.in); 

		//Create the socket object for carrying the data. 
		DatagramSocket ds = new DatagramSocket(); 
		
		//Takes in the arguments for the ip and port number.
		InetAddress ip = InetAddress.getByName(args[0]); 
		int port = Integer.parseInt(args[1]);
		
		//Creates the Bytes for the buffer and the receiver.
		byte buf[] = null;
		byte receive[] = new byte[1024];

		//Loop while user doesn't enter "<EXIT>" 
		while (true) 
		{ 
			System.out.print("Enter a command: ");
			String inp = sc.nextLine(); 
			
			if (inp.equals("<REQUESTQUOTE>")){
				//Convert the String input into the byte array. 
				buf = inp.getBytes(); 

				//Create the datagramPacket for sending the data. 
				DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, port); 

				//Invoke the send call to actually send the data. 
				ds.send(DpSend); 
				
				//Creates the DatagramPacket for receiving the quote from the server
				DatagramPacket DpReceive = new DatagramPacket(receive, receive.length);
				ds.receive(DpReceive);
				
				//Prints out the quote from the server
				System.out.println(data(receive).toString());
				
				//Clears the receiving byte
				receive = new byte[1024];
			}
			// break the loop if user enters "<EXIT>"
			if (inp.equals("<EXIT>")){ 
				//Convert the String input into the byte array. 
				buf = inp.getBytes(); 

				//Create the datagramPacket for sending the data. 
				DatagramPacket DpSend = 
					new DatagramPacket(buf, buf.length, ip, port); 

				//Step 3 : invoke the send call to actually send the data. 
				ds.send(DpSend); 
				System.out.println("Client Closed.");
				break; 
			}
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
