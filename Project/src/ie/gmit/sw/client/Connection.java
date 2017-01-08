package ie.gmit.sw.client;

import java.io.*;
import java.net.*;
import ie.gmit.sw.client.config.XmlParser;

public class Connection {
	  String serverurl = "127.0.0.1";
	  int serverport = 7777;
	 
	  /**  Instantiates an instance of the SimpleClient class and initilaizes it.
	  */
	  public static void main(String[] args){
		  Connection connection = new Connection();
		  connection.init();
	  }
	 
	  /**  Connects to the SimpleServer on port 8189 and sends a few demo lines
	  *  to the server, and reads, displays the server reply,
	  *  then issues a Bye command signaling the server to quit.
	  */
	  public void init(){
	    Socket socket = null;    
	    try{  	
	      System.out.println("Connecting to " + serverurl + " on port " + serverport);
	      socket = new Socket(serverurl,serverport);
	      //Set socket timeout for 10000 milliseconds or 10 seconds just 
	      //in case the host can't be reached
	      socket.setSoTimeout(100000);
	      System.out.println("Connected.");     
	      
	      String choice = UI.x;
	      //System.out.println(choice);
	      
	      XmlParser var = new XmlParser();
          var = XmlParser.parser();
	      
	      OutputStream out = socket.getOutputStream();
	      ObjectOutputStream oout = new ObjectOutputStream(out);
	      oout.writeObject(var.username);
	      oout.writeObject(var.host);
	      oout.writeObject(var.port);
	      oout.writeObject(choice);
	      
	      //System.out.println("Closing connection.");
	      //bufferedreader.close();
	      //inputstreamreader.close();
	      //printwriter.close();
	      //oout.close();
	      //socket.close();
	      //System.exit(0);
	 
	    }catch(UnknownHostException unhe){
	      System.out.println("UnknownHostException: " + unhe.getMessage());
	    }catch(InterruptedIOException intioe){
	      System.out.println("Timeout while attempting to establish socket connection.");
	    }catch(IOException ioe){
	      System.out.println("IOException: " + ioe.getMessage());
	    }finally{
	      try{
	        socket.close();
	      }catch(IOException ioe){
	        System.out.println("IOException: " + ioe.getMessage());
	      }
	    }
	  }
	  
	  public static void objectReader( String serverurl, int serverport) throws UnknownHostException, IOException
	  {
		  Socket socket = null;  
		  socket = new Socket(serverurl,serverport);
	      //Set socket timeout for 10000 milliseconds or 10 seconds just 
	      //in case the host can't be reached
	      socket.setSoTimeout(100000);
	      System.out.println("Connected.");   
	      
	      String choice = UI.x;
	      //System.out.println(choice);
	      
	      XmlParser var = new XmlParser();
          var = XmlParser.parser();
	      
	      OutputStream out = socket.getOutputStream();
	      ObjectOutputStream oout = new ObjectOutputStream(out);
	      oout.writeObject(var.username);
	      oout.writeObject(var.host);
	      oout.writeObject(var.port);
	      oout.writeObject(choice);
	      
	      socket.close();
	  }
	}

