package com.iiitb.factory;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NodeList;

import com.iiitb.blocks.Abs;
import com.iiitb.blocks.Block;
import com.iiitb.blocks.Constant;
import com.iiitb.blocks.Delay;
import com.iiitb.blocks.Divide;
import com.iiitb.blocks.Gain;
import com.iiitb.blocks.InPort;
import com.iiitb.blocks.LogicalOperator;
import com.iiitb.blocks.MinMax;
import com.iiitb.blocks.Mux;
import com.iiitb.blocks.RelationalOperator;
import com.iiitb.blocks.Saturation;
import com.iiitb.blocks.Sin;
import com.iiitb.blocks.Sqrt;
import com.iiitb.blocks.Subsystem;
import com.iiitb.blocks.Sum;
import com.iiitb.blocks.Switch;
import com.iiitb.cfg.Accfg;
import com.iiitb.constant.Constants;
import com.iiitb.utility.BlockFactoryUtility;
import com.iiitb.utility.ParseXML;

import expression.SinExpression;

public class BlockFactory {

	/**
	 * @param blockName
	 *            - Based on blockName instance of a particular class is created
	 * @param attributes
	 *            - Passed as a parameter to utility method
	 * @return - Object with necessary attributes (e.g accfg) set
	 */
	public static Block generateBlock(String blockName, NodeList attributes) {




		Block block = null;
		if (blockName.startsWith(Constants.CONST)) {


			block = new Constant(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);

			List<String> attrFetchList = new ArrayList<String>(); 
			attrFetchList.add(Constants.VALUE);


			BlockFactoryUtility.setBlockAttributes(attrFetchList, attributes,
					block);

		}

		if (blockName.startsWith(Constants.SUM)) {
			block = new Sum(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);

			List<String> attrFetchList = new ArrayList<String>(); 
			attrFetchList.add(Constants.INPUT);

			BlockFactoryUtility.setBlockAttributes(attrFetchList, attributes,
					block);

		}
		if (blockName.startsWith(Constants.SQRT)) {
			block = new Sqrt(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);

			List<String> attrFetchList = new ArrayList<String>(); 
			attrFetchList.add(Constants.INPUT);

			BlockFactoryUtility.setBlockAttributes(attrFetchList, attributes,
					block);

		}

		if (blockName.startsWith(Constants.GAIN)) {
			block = new Gain(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);		
			List<String> attrFetchList = new ArrayList<String>(); 
			attrFetchList.add(Constants.GAIN);
			BlockFactoryUtility.setBlockAttributes(attrFetchList, attributes,
					block);

		}


		if (blockName.startsWith(Constants.DELAY)) {


			block = new Delay(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);
			List<String> attrFetchList = new ArrayList<String>(); 
			attrFetchList.add(Constants.IC);
			attrFetchList.add(Constants.DELAY_LENGTH);

			BlockFactoryUtility.setBlockAttributes(attrFetchList, attributes,
					block);

		}

		if (blockName.startsWith(Constants.SWITCH)) {



			block = new Switch(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);

			List<String> attrFetchList = new ArrayList<String>(); 
			attrFetchList.add(Constants.CRITERIA);
			attrFetchList.add(Constants.THRESHOLD);

			BlockFactoryUtility.setBlockAttributes(attrFetchList, attributes,
					block);

		}


		if (blockName.startsWith(Constants.DIVIDE)) {
			block = new Divide(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);

			List<String> attrFetchList = new ArrayList<String>(); 
			attrFetchList.add(Constants.INPUT);

			BlockFactoryUtility.setBlockAttributes(attrFetchList, attributes,
					block);

		}

		if (blockName.startsWith(Constants.RELATIONAL)) {
			block = new RelationalOperator(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);

			List<String> attrFetchList = new ArrayList<String>(); 
			attrFetchList.add(Constants.OPERATOR);

			BlockFactoryUtility.setBlockAttributes(attrFetchList, attributes,
					block);

		}

		if (blockName.startsWith(Constants.LOGICAL)) {
			block = new LogicalOperator(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);

			List<String> attrFetchList = new ArrayList<String>(); 
			attrFetchList.add(Constants.OPERATOR);

			BlockFactoryUtility.setBlockAttributes(attrFetchList, attributes,
					block);

		}


		if (blockName.startsWith(Constants.MINMAX)) {
			block = new MinMax(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);

			List<String> attrFetchList = new ArrayList<String>(); 
			attrFetchList.add(Constants.FUNCTION);
			attrFetchList.add(Constants.INPUT);

			BlockFactoryUtility.setBlockAttributes(attrFetchList, attributes,
					block);

		}

		if (blockName.startsWith(Constants.INPORT)) {
			block = new InPort(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);

			List<String> attrFetchList = new ArrayList<String>(); 
			attrFetchList.add(Constants.PORT);


			BlockFactoryUtility.setBlockAttributes(attrFetchList, attributes,
					block);

		}

		if (blockName.startsWith(Constants.SAT)) {



			block = new Saturation(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);

			List<String> attrFetchList = new ArrayList<String>(); 
			attrFetchList.add("UpperLimit");
			attrFetchList.add("LowerLimit");

			BlockFactoryUtility.setBlockAttributes(attrFetchList, attributes,
					block);

		}

		if (blockName.startsWith(Constants.SIN)) {



			block = new Sin(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);

			List<String> attrFetchList = new ArrayList<String>(); 
			/*attrFetchList.add(Constants.AMPLITUDE);
			attrFetchList.add(Constants.FREQUENCY);*/
			attrFetchList.add("SampleTime");
			
			BlockFactoryUtility.setBlockAttributes(attrFetchList, attributes,
					block);

		}
		
		if (blockName.startsWith(Constants.MUX)) {



			block = new Mux(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);

			List<String> attrFetchList = new ArrayList<String>(); 
			attrFetchList.add("Inputs");
			

			BlockFactoryUtility.setBlockAttributes(attrFetchList, attributes,
					block);

		}
		
		if (blockName.startsWith(Constants.ABS)) {



			block = new Abs(blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);


		}

		return block;

	}


	/** Method caters to Subsystem block. The instance of Accfg to be passed is computed as part of
	 * underlying subsystem 
	 **/

	public static Block generateBlock(String blockName, Accfg accfg) {
		Block block = null;
		if (blockName.startsWith(Constants.SUB_SYS)) {

			block = new Subsystem(accfg, blockName.split("_", 2)[1]+"_SubSystem_"+ParseXML.countSubSystem);

		}
		return block;
	}

}
