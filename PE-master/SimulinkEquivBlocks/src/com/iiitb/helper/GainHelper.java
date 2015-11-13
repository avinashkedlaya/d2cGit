package com.iiitb.helper;

import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.inter.IHelper;

public class GainHelper implements IHelper {

	@Override
	public void setAttr(NodeList attributes, int iter, Block block,String attrToFetch) {
		// TODO Auto-generated method stub
		if(attributes.item(iter).getTextContent()!= null && attributes.item(iter).getTextContent() !="" )
			{
			block.setGain(Integer.parseInt(attributes.item(iter).getTextContent()));
			
			}
			else
				block.setValue("1");
	}
}
