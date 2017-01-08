package ie.gmit.sw.logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;

public class PrintFile {
	public static void log(String ip, String access) throws FileNotFoundException
	{
		PrintWriter out = new PrintWriter("AccessLog.txt");
		String datetimestring = (Calendar.getInstance()).getTime().toString();
		String query = "";
		
		if(access.equals("1"))
		{
			query = "Connection ";
		}
		if(access.equals("2"))
		{
			query = "Listing ";
		}
		if(access.equals("3"))
		{
			query = "Download ";
		}
		if(access.equals("4"))
		{
			query = "Exit ";
		}
		
		out.println(query + "requested by " + ip + " at " + datetimestring + "\n");
		out.close();
	}
}
