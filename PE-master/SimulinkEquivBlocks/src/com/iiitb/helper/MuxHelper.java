package com.iiitb.helper;

import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.blocks.Mux;
import com.iiitb.inter.IHelper;

public class MuxHelper implements IHelper {

	@Override
	public void setAttr(NodeList attributes, int iter, Block block,
			String attrToFetch) {
		if(attrToFetch.equalsIgnoreCase("Inputs"))
			((Mux)block).setInputLines(Integer.parseInt(attributes.item(iter).getTextContent()));

	}

}
