package com.iiitb.helper;

import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.blocks.Delay;
import com.iiitb.constant.Constants;
import com.iiitb.inter.IHelper;

public class SaturationHelper implements IHelper {

	@Override
	public void setAttr(NodeList attributes, int iter, Block block,String attrToFetch) {
		// TODO Auto-generated method stub
		String [] arr;
		if(attrToFetch.equalsIgnoreCase("LowerLimit")){
			arr=attributes.item(iter).getTextContent().replaceAll("\\[", "").replaceAll("\\]","").split(" ");
			(block).setLower(Float.parseFloat(arr[0]));
		}

		if(attrToFetch.equalsIgnoreCase("UpperLimit"))
		{
			arr=attributes.item(iter).getTextContent().replaceAll("\\[", "").replaceAll("\\]","").split(" ");
			(block).setUpper(Float.parseFloat(arr[0]));	
	
		}
	}
}

