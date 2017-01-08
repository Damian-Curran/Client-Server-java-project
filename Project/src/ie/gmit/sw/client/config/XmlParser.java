package ie.gmit.sw.client.config;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class XmlParser {
	
	public String username;
	public String host;
	public String port;
    
    public XmlParser()
    {
    	
    }
    
    public XmlParser(String username,String host,String port)
    {
    	this.username = username;
    	this.host = host;
    	this.port = port;
    }
	
	public static XmlParser parser(){
		
		XmlParser var = new XmlParser();
		
	    try {

	    File fXmlFile = new File("/Users/Public/client.xml");
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(fXmlFile);
	    
	    /*if(fXmlFile.exists()){
			  System.out.println("File existed");
		  }else{
			  System.out.println("File not found!");
		  }*/
	    doc.getDocumentElement().normalize();

	    //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	    NodeList nList = doc.getElementsByTagName("client-config");

	    //System.out.println("----------------------------");

	    for (int temp = 0; temp < nList.getLength(); temp++) {

	        Node nNode = nList.item(temp);

	        //System.out.println("\nCurrent Element :" + nNode.getNodeName());

	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	            Element eElement = (Element) nNode;
	            
	            /*System.out.println("Staff id : " + eElement.getAttribute("username"));
	            System.out.println("First Name : " + eElement.getElementsByTagName("server-host").item(0).getTextContent());
	            System.out.println("Last Name : " + eElement.getElementsByTagName("server-port").item(0).getTextContent());*/
	            
	            var.username = eElement.getAttribute("username");
	            var.host = eElement.getElementsByTagName("server-host").item(0).getTextContent();
	            var.port = eElement.getElementsByTagName("server-port").item(0).getTextContent();
	        }
	    }
	    } catch (Exception e) {
	    e.printStackTrace();
	    }
		return var;
	  }
	}
