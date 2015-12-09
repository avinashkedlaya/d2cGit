package com.iiitb.helper;

import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.blocks.Sin;
import com.iiitb.constant.Constants;
import com.iiitb.inter.IHelper;

public class SinHelper implements IHelper {

	@Override
	public void setAttr(NodeList attributes, int iter, Block block,
			String attrToFetch) {
		if(attrToFetch.equalsIgnoreCase(Constants.FREQUENCY))
			((Sin)block).setFrequency(Integer.parseInt(attributes.item(iter).getTextContent()));

		if(attrToFetch.equalsIgnoreCase(Constants.AMPLITUDE))
			((Sin)block).setAmplitude(Float.parseFloat(attributes.item(iter).getTextContent()));	
		
		if(attrToFetch.equalsIgnoreCase("SampleTime"))
			((Sin)block).setSTime(Float.parseFloat(attributes.item(iter).getTextContent()));
		

	}

}
