import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.*;

public class PartialHTTP1Server {

	//public static void main (String[]args) {}

	//initialize socket and input stream
	private Socket          socket   = null;
	private ServerSocket    server   = null;
	private DataInputStream in       =  null;

	// constructor with port
	public PartialHTTP1Server(int port){
		// starts server and waits for a connection
		try{
			server = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

			//Accept blocking call
			socket = server.accept();

			System.out.println("Client accepted");

			// takes input from the client socket
			in = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));

			String line = "";

			// reads message from client until "Over" is sent
			while (!line.equals("\n\n")){
				try{
					line = in.readUTF();
					System.out.println(line);

				}catch(IOException i){
					System.out.println(i);
				}
			}

			System.out.println("Closing connection");

			// close connection
			socket.close();
			in.close();
		}catch(IOException i){
			System.out.println(i);
		}
	}
	
	public static void myGETRequest(String resource) throws IllegalArgumentException{
		//Invalid URL
		
		//Invalid file path
		
		//Invalid format
		
		//Check Date?
		throw new IllegalArgumentException();
	}
	
	public static void myPOSTRequest(String resource) throws IllegalArgumentException{
		throw new IllegalArgumentException();
	}
	
	public static void myHEADRequest(String resource) throws IllegalArgumentException{
		throw new IllegalArgumentException();
	}
	

	public static void main(String args[]) {
		PartialHTTP1Server server = new PartialHTTP1Server(Integer.parseInt(args[0]));
		
		String threadStr = "";
		
		boolean isCommand=false, isResource=false, isHTTPVersion=false;
		
		List<String> tokens = new ArrayList<>();
	    StringTokenizer tokenizer = new StringTokenizer(threadStr, " ");
	    while (tokenizer.hasMoreElements()) {
	        tokens.add(tokenizer.nextToken());
	    }
	    
	    String command = "", resource = "", httpVersion ="";
	   
	    if(tokenizer.hasMoreElements()) {
	    	command = (String)tokenizer.nextElement().toString();
	    	isCommand = true;
	    }
	    if(tokenizer.hasMoreElements()) {
	    	resource = (String)tokenizer.nextElement().toString();
	    	isResource = true; 
	    }
	    if(tokenizer.hasMoreElements()) {
	    	httpVersion = (String)tokenizer.nextElement().toString();
	    	isHTTPVersion = true;
	    }
		
			
		switch(command) {
			case "GET":
				System.out.println("GET command");
				myGETRequest(resource);
				break;
			case "POST":
				System.out.println("POST command");
				myPOSTRequest(resource);
				break;
			case "HEAD":
				System.out.println("HEAD command");
				myHEADRequest(resource);
				break;
			default:
				System.out.println("Unsupported command");
		}
	}

}
