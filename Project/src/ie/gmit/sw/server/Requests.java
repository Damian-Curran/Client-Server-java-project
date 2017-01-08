package ie.gmit.sw.server;

import java.io.*;
import java.net.*;

import ie.gmit.sw.logger.PrintFile;

public class Requests {
	/**  Instantiates an instance of the SimpleServer class and initilaizes it.
	 * @throws ClassNotFoundException 
	  */
	  public static void main(String[] args) throws ClassNotFoundException{
		  Requests simpleserver = new Requests();
	      simpleserver.init();
	  }
	 
	  /**  Sets up a ServerSocket and listens on port 8189.
	 * @throws ClassNotFoundException 
	  */
	  public void init() throws ClassNotFoundException{
	    ServerSocket serversocket = null;
	    Socket socket = null;
	    try{
	      //establish a server socket monitoring port 8189 
	      //port 8189 is not used by any services
	      serversocket = new ServerSocket(7777);
	      System.out.println("Listening at 127.0.0.1 on port 7777");
	 
	      //wait indefinitely until a client connects to the socket
	      socket = serversocket.accept();
	 
	 
	      //establish an printwriter using the output streamof the socket object
	      //and set auto flush on    
	      
	      InputStream in = socket.getInputStream();
	      ObjectInputStream oin = new ObjectInputStream(in);
	      
	      String username = (String) oin.readObject();
	      String host =  (String) oin.readObject();
	      String port =  (String) oin.readObject();
	      String choice =  (String) oin.readObject();
	      
	      /*System.out.println("Received from Client: " + username);
	      System.out.println("Received from Client: " + host);
	      System.out.println("Received from Client: " + port);
	      System.out.println("Received from Client: " + choice);*/
	      
	      logger(host,choice);
	      
	      if(choice == "2")
	      {
	    	  final File folder = new File("/User/Public/Files");
	    	  listFilesForFolder(folder);
	      }
	      
	      //System.out.println("Closing connection.");
	      //bufferedreader.close();
	      //inputstreamreader.close();
	      //printwriter.close();
	      //socket.close();
	    }catch(UnknownHostException unhe){
	      System.out.println("UnknownHostException: " + unhe.getMessage());
	    }catch(InterruptedIOException intioe){
	      System.out.println("Timeout while attempting to establish socket connection.");
	    }catch(IOException ioe){
	      System.out.println("IOException: " + ioe.getMessage());
	    }finally{
	      try{
	        socket.close();
	        serversocket.close();
	      }catch(IOException ioe){
	        System.out.println("IOException: " + ioe.getMessage());
	      }
	    }
	  }
	  
	  public static void logger(String host, String choice) throws FileNotFoundException
	  {
		  PrintFile.log(host,choice);
	  }
	  
	  public void listFilesForFolder(final File folder) {
		    for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isDirectory()) {
		            listFilesForFolder(fileEntry);
		        } else {
		            System.out.println(fileEntry.getName());
		        }
		    }
		}
	} 