package ie.gmit.sw.client;

import java.io.IOException;
import java.util.Scanner;

import ie.gmit.sw.server.Requests;

public class UI {
	
	public static String x;
	public static int connected = 0;
	
	public static void menu()
	{
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("");
		System.out.println("");
		System.out.println("Connect to Server");
		System.out.println("Print File Listing");
		System.out.println("Download File");
		System.out.println("Quit");
		System.out.println();
		System.out.println("Type Option[1-4]>");
		int n = reader.nextInt(); // Scans the next token of the input as an int.
		System.out.println("\n");
		
		if(n == 1)
		{
			x = "1";
			connect();
		}
		else if(n == 2)
		{
			x = "2";
			fileList();
		}
		else if(n == 3)
		{
			x = "3";
			download();
		}
		else if(n == 4)
		{
			x = "4";
			exit();
		}
	}
	
	public static void connect()
	{
		connected++;
		
		if(connected == 2)
		{
			System.out.println("Already Connected to Server");
		}
		else
		{
			Thread thread = new Thread(new Runnable() {
			    @Override
			    public void run() {
			    	try {
						Requests.main(null);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			});
			thread.start();
			long endTimeMillis = System.currentTimeMillis() + 1000;
			while (thread.isAlive()) {
			    if (System.currentTimeMillis() > endTimeMillis) {
			        break;
			    }
			    try {
			        Thread.sleep(500);
			    }
			    catch (InterruptedException t) {}
			}
		 Connection.main(null);
		}
	 
	 	menu();
	}
	
	public static void fileList()
	{
		String serverurl = "127.0.0.1";
		int serverport = 7777;
		try {
			Connection.objectReader(serverurl,serverport);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 menu();
	}
	
	public static void download()
	{
		
		menu();
	}
	
	public static void exit()
	{
		System.exit(0);
	}
}
