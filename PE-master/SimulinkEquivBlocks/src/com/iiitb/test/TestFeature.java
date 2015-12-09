package com.iiitb.test;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.w3c.dom.Document;

import com.iiitb.cfg.Accfg;
import com.iiitb.utility.ParseXML;

public class TestFeature {
	static String path="E://9915windows//Desktop//";
	public static void main(String[] args) {
		// TODO Auto-generated method stub



		JFrame frame=new JFrame();
		frame.show();
		String selectS;
		int select;
		selectS = JOptionPane.showInputDialog(frame, "Enter integer value",
				0);
		select=Integer.parseInt(selectS);
		Document doc=null;
		try {
			switch (select){
			case 0:
				doc = ParseXML
				.initializeDocument("E://9915windows//Desktop//"+"zerocrossing.xml");	
				break;
			case 1:
				doc = ParseXML
				.initializeDocument("E://9915windows//Desktop//"+"gain_xml.xml");
				break;
			default:
				doc = ParseXML
				.initializeDocument("E://9915windows//Desktop//"+"sinTrigno_xml.xml");
				break;
			}
			frame.dispose();



			/*
			 * 
			 * The currSubSystemNode represents the current subsystem node. For
			 * the first call since we are at the top most level we pass null to
			 * currSubSystemNode
			 */

			Accfg mergedAccfg = ParseXML.parseDocument(doc, doc.getDocumentElement());

			System.out.println("\n\nThe final merged ACCFG is : \n\n"+mergedAccfg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
