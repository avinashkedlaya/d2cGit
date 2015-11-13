package com.iiitb.utility;

import expression.Type;
import expression.Variable;



import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class TypeParseXML {
	static String path="E://9915windows//Desktop//";

	public static String getReturnType(Variable v)
	{
		try{
			File fXmlFile = new File(path+"LibraryFunc.xml");
			
			String typeName=null;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Func");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);


				Element eElement = (Element) nNode;
				if(v.getName().equals(eElement.getAttribute("name")))
				{
					typeName=eElement.getElementsByTagName("ReturnType").item(0).getTextContent();
					if(!Type.hasType(typeName))
					{
						Type.addType(typeName);
					}
					
				}
				



			}
			return typeName; 
		}
		catch(Exception e)
		{

			e.printStackTrace();
		}


		return null;
	}

}
