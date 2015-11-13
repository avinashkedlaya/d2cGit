package com.iiitb.utility;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.blocks.Delay;
import com.iiitb.constant.Constants;
import com.iiitb.testing.TestPortBranchSubsystem;
import com.iiitb.utility.DestNode;

import expression.Expression;
import expression.Variable;

public class FetchInputFromLineSubsystem {
	public static Map<String, LinkedList<DestNode>> adjacencyList = new HashMap<String, LinkedList<DestNode>>();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void parseLineTest(ArrayList<Block> blockList,
			NodeList attributes) {
		
		String sourceNode = "";
		String destNode = "";
		List<DestNode> destNodeTemp = new LinkedList<DestNode>();
		String destPort = "";
		// Map to generate adjacency list representation of subsystem
		
		
		/* TEST : 1
		 * 
		 * blockList contains all the blocks in model as java objects with necessary attributes set
		 * 
		 * As per our model in case of parent subsystem , Constant,Constant1,Subsystem,Sum are the blocks involved
		 * 
		 * In case of child subsystem , In1,In2,Sum,Out1 are the blocks
		 * 
		 * attributes contain child nodes of <Line>
		 * 
		 * 
		 * */
		DestNode nodeIni = new DestNode();
		for (int iter = 0; iter < attributes.getLength(); iter++) {

			
			// Branch is applicable incase of parent subsystem
			
			
			// NOTE : Only branches of below type is parsed
			/*<Line>
			<P Name="SrcBlock">Constant1</P>

			<Branch>
  				<P Name="ZOrder">6</P>
  				<P Name="Points">[100, 0; 0, 41; 160, 0]</P>
  				<P Name="DstBlock">Sum</P>
  				<P Name="DstPort">2</P>
			</Branch>
			
			<Branch>
  				<P Name="ZOrder">5</P>
  				<P Name="Points">[0, -30]</P>
  				<P Name="DstBlock">Subsystem</P>
  				<P Name="DstPort">2</P>
			</Branch>	
  
  				</Line>
			
			
			*/
		/*	
			Branches of following type is not parsed 
			
			<Line>
			
				<P Name="SrcBlock">Constant1</P>
			<Branch>
				<P Name="ZOrder">6</P>
			  
			  <Branch>
				<P Name="ZOrder">6</P>
				<P Name="Points">[100, 0; 0, 41; 160, 0]</P>
				<P Name="DstBlock">Sum</P>
				<P Name="DstPort">2</P>
			  </Branch>
				
				<P Name="Points">[0, -30]</P>
  				<P Name="DstBlock">Subsystem</P>
  				<P Name="DstPort">2</P>
  				
  			</Branch>
  			
  			</Line>*/
				
			
			
			
			
			
			if (attributes.item(iter).getNodeName().equalsIgnoreCase("P")
					|| attributes.item(iter).getNodeName()
							.equalsIgnoreCase("Branch"))

			{

				// Branch
				/*
				 *  <Line>
				
				<P Name="SrcBlock">Constant1</P>

				<Branch>
	  				<P Name="ZOrder">6</P>
	  				<P Name="Points">[100, 0; 0, 41; 160, 0]</P>
	  				<P Name="DstBlock">Sum</P>
	  				<P Name="DstPort">2</P>
				</Branch>
				
				<Branch>
	  				<P Name="ZOrder">5</P>
	  				<P Name="Points">[0, -30]</P>
	  				<P Name="DstBlock">Subsystem</P>
	  				<P Name="DstPort">2</P>
				</Branch>	
      
      				</Line>
				 * 
				 * 
				 * 
				 * 
				 * TEST : Incase of source block Constant1 , destNodeTemp list should contain 2 nodes Sum ,Subsystem
				 * 
				 * */
				if (attributes.item(iter).getNodeName()
						.equalsIgnoreCase("Branch")) {

					DestNode node = new DestNode();
					NodeList branchChildren = attributes.item(iter)
							.getChildNodes();
					for (int brTemp = 0; brTemp < branchChildren.getLength(); brTemp++) {
						if (branchChildren.item(brTemp).getNodeName()
								.equalsIgnoreCase("P")) {

							NamedNodeMap temp = branchChildren.item(brTemp)
									.getAttributes();

							for (int tempIter = 0; tempIter < temp.getLength(); tempIter++) {

								// System.out.println("boolean value "+temp.item(tempIter).getNodeValue().equalsIgnoreCase("DstBlock"));
								if (temp.item(tempIter).getNodeValue()
										.equalsIgnoreCase("DstBlock")) {
									
									node.setName(branchChildren.item(brTemp)
											.getTextContent());
									destNodeTemp.add(node);
								}

								if (temp.item(tempIter).getNodeValue()
										.equalsIgnoreCase("DstPort")) {
								

									destNodeTemp
											.get(destNodeTemp.indexOf(node))
											.setPort(
													branchChildren.item(brTemp)
															.getTextContent());

								}

							}

						}
					}

				}

				else {

					// for a single <p>
					NamedNodeMap temp = attributes.item(iter).getAttributes();

					for (int tempIter = 0; tempIter < temp.getLength(); tempIter++) {

						
						if (temp.item(tempIter).getNodeValue()
								.equalsIgnoreCase("SrcBlock")) {

							sourceNode = attributes.item(iter).getTextContent();

						}

						if (temp.item(tempIter).getNodeValue()
								.equalsIgnoreCase("DstBlock")) {

							nodeIni.setName(attributes.item(iter)
									.getTextContent());
							destNodeTemp.add(nodeIni);
							

						}

						// Used for switch to identify port of input and
						// condition
						if (temp.item(tempIter).getNodeValue()
								.equalsIgnoreCase("DstPort")) {
							
							// Name and port is set
							destNodeTemp.get(destNodeTemp.indexOf(nodeIni))
									.setPort(
											attributes.item(iter)
													.getTextContent());

						}

					}

				}

			}

		}

	

		/* 
		 * 
		 * TEST:2 
		 * Parent subsystem : 
		 * If the source node is Incase of source block Constant1 , destNodeTemp list should contain 2 nodes Sum ,Subsystem
		 * 
		 * 
		 * */

		if(sourceNode.equalsIgnoreCase("Constant1"))
		{
			
			assertEquals(2,destNodeTemp.size());
			assertEquals("Sum",destNodeTemp.get(0).getName());
			assertEquals("Subsystem",destNodeTemp.get(1).getName());
		}
		
		
		

		
		
		if (sourceNode != "") {

			if (adjacencyList.get(sourceNode) != null) {

				adjacencyList.get(sourceNode).addAll(destNodeTemp);

			} else {
				LinkedList<DestNode> addList = new LinkedList<DestNode>();

				addList.addAll(destNodeTemp);

				adjacencyList.put(sourceNode, addList);

			}
			
			
			
			
			

			Iterator<Block> blockListIter = blockList.iterator();

			while (blockListIter.hasNext()) {

				Block blockObj = blockListIter.next();

				// CHECK THIS

				Iterator<DestNode> destNodeTempIter = destNodeTemp.iterator();
				while (destNodeTempIter.hasNext()) {

					DestNode tempDest = destNodeTempIter.next();
					destNode = tempDest.getName();

					destPort = tempDest.getPort();
					if (destNode.equalsIgnoreCase(((Variable) blockObj
							.getOutput()).getName())) {

						// input is not set for delay block as for delay block
						// input is based on delay length
						if (!destNode.startsWith(Constants.DELAY))
							blockObj.setInput(sourceNode, destPort);

						// System.out.println("Super ");
					}
				}
			
				
			
			}

		
			
			
			
		}

		// Necessary inputs are set

		// Clear the list
		destNodeTemp.clear();

		Iterator<Block> blockListIter = blockList.iterator();

		while (blockListIter.hasNext()) {

			Block blockObj = blockListIter.next();

			if (blockObj.isInputSetFlag()
					|| destNode.startsWith(Constants.DELAY)) {

				
				// Applicable only for delay block. For other blocks input is
				// set.
				// Flag to set input based on delay length.
				/*
				 * If delay length is 1 then input is directly the source node
				 */
				
				
				// Delay is not part of model under test
				
				
				boolean flag = false;
				if (((Variable) blockObj.getOutput()).getName().startsWith(
						Constants.DELAY)) {
					if (((Delay) blockObj).getDelayLength() > 1) {
						blockObj.setInput(
								((Variable) blockObj.getOutput()).getName()
										+ "_"
										+ "delay_"
										+ (((Delay) blockObj).getDelayLength() - 2),
								destPort);
					} else {
						blockObj.setInput(sourceNode, destPort);
						flag = true;
					}
				}

				
				
				
		
				
				List<Expression> input = new ArrayList<Expression>();
				input = blockObj.getInput();
				blockObj.getAccfg().setInput(input);
				List<Expression> expr = new ArrayList<Expression>();

				expr.add(blockObj.expression());
				
		

				/*
				 * For Delay block there shouldn't be FP. Also check for delay
				 * length and add additional delay blocks to delayLengthList
				 */
				
				
				// Delay is not part of model under test
				
				if (((Variable) blockObj.getOutput()).getName().startsWith(
						Constants.DELAY)) {
					List<Delay> delayLengthSet = new ArrayList<Delay>();
					Block delayObj;
					for (int i = 0; i < ((Delay) blockObj).getDelayLength() - 1; i++) {
						String name = ((Variable) blockObj.getOutput())
								.getName() + "_" + "delay_" + i;
						String tempInput;
						if (i == 0)
							tempInput = sourceNode;
						else
							tempInput = ((Variable) blockObj.getOutput())
									.getName() + "_" + "delay_" + (i - 1);

						delayObj = new Delay(name);
						delayObj.setInput(tempInput, destPort);

						List<Expression> inputTemp = new ArrayList<Expression>();
						inputTemp = delayObj.getInput();
						delayObj.getAccfg().setInput(inputTemp);
						List<Expression> exprTemp = new ArrayList<Expression>();
						exprTemp.add(delayObj.expression());
						delayObj.getAccfg().setDelay(exprTemp);

						delayLengthSet.add(0, (Delay) delayObj);

					}

					((Delay) blockObj).setDelayLengthList(delayLengthSet);
					blockObj.getAccfg().setDelay(expr);

					// Finally only sourceNode is input to delay block
					if (!flag) {
						blockObj.setInput(sourceNode, destPort);
						input = blockObj.getInput();

						blockObj.getAccfg().setInput(input);
					}
				}

				else {

					blockObj.getAccfg().setFp(expr);
				}

			}

		}
		
		
		
		
	}
	public static ArrayList<String> parseLineForPort(NodeList attributes) {
		// TODO Auto-generated method stub

		String sourceNode = "";

		boolean store = false;

		ArrayList<String> tempList = new ArrayList<String>();
		for (int iter = 0; iter < attributes.getLength(); iter++) {

			if (attributes.item(iter).getNodeName().equalsIgnoreCase("P")
					|| attributes.item(iter).getNodeName()
							.equalsIgnoreCase("Branch")) {

				if (attributes.item(iter).getNodeName()
						.equalsIgnoreCase("Branch")) {

					NodeList branchChildren = attributes.item(iter)
							.getChildNodes();
					for (int brTemp = 0; brTemp < branchChildren.getLength(); brTemp++) {
						if (branchChildren.item(brTemp).getNodeName()
								.equalsIgnoreCase("P")) {

							NamedNodeMap temp = branchChildren.item(brTemp)
									.getAttributes();

							for (int tempIter = 0; tempIter < temp.getLength(); tempIter++) {

								if (temp.item(tempIter).getNodeValue()
										.equalsIgnoreCase("DstBlock")) {

									if (branchChildren.item(brTemp)
											.getTextContent()
											.startsWith(Constants.SUB_SYS_CASE)) {

										store = true;

									}
								}

								if (temp.item(tempIter).getNodeValue()
										.equalsIgnoreCase("DstPort")) {

									if (store) {
										tempList.add(sourceNode);

										TestPortBranchSubsystem.portMap.put(branchChildren
												.item(brTemp).getTextContent(),
												sourceNode);
										/* We use portMap in ParseXML class because when InPort block is created ,
										   portMap in ParseXML is referred to setValue()
										*/
										ParseXML.portMap.put(branchChildren
												.item(brTemp).getTextContent(),
												sourceNode);
										store = false;
									}

								}

							}

						}
					}

				}

				else {

					NamedNodeMap temp = attributes.item(iter).getAttributes();

					for (int tempIter = 0; tempIter < temp.getLength(); tempIter++) {

						if (temp.item(tempIter).getNodeValue()
								.equalsIgnoreCase("SrcBlock")) {

							sourceNode = attributes.item(iter).getTextContent();

						}

						if (temp.item(tempIter).getNodeValue()
								.equalsIgnoreCase("DstBlock")) {

							if (attributes.item(iter).getTextContent()
									.startsWith(Constants.SUB_SYS_CASE)) {
								store = true;

							}
						}
						if (temp.item(tempIter).getNodeValue()
								.equalsIgnoreCase("DstPort")) {

							if (store) {
								tempList.add(sourceNode);
								TestPortBranchSubsystem.portMap.put(attributes.item(iter)
										.getTextContent(), sourceNode);
								
								/* We use portMap in ParseXML class instead of TestPortBranchSubsystem because when InPort block is created ,
								   portMap in ParseXML is referred to setValue()
								*/
								
								ParseXML.portMap.put(attributes.item(iter)
										.getTextContent(), sourceNode);
								store = false;
							}

						}

					}
				}
			}

			/*
			 * Test portMap - 
			 * In case of subsystem with port , the portMap must contain the block connected to the port.
			 * This is determined by using <Line> in XML
			 * 
			 * The blocks that have line connected to subsystem block are the one which will act as input to the port. (i.e) DstBlock should be Subsystem
			 *  [<P Name="DstBlock">Subsystem</P>]
			 * This can be either as part of branch or a direct connection
			 * 
			 * As per our model , Direct connection - 
			 *   
			 *   
			 *   <Line>
					<P Name="ZOrder">1</P>
					<P Name="SrcBlock">Constant</P>
					<P Name="SrcPort">1</P>
					<P Name="DstBlock">Subsystem</P>
					<P Name="DstPort">1</P>
      			</Line>
			 * 
			 * 
			 * As part of Branch -
			 * 
			 *  <Line>
					<P Name="ZOrder">3</P>
					<P Name="SrcBlock">Constant1</P>
					<P Name="SrcPort">1</P>
					<P Name="Points">[45, 0; 0, -50]</P>

					<Branch>
	  					<P Name="ZOrder">5</P>
	  					<P Name="Points">[0, -30]</P>
	  					<P Name="DstBlock">Subsystem</P>
	  					<P Name="DstPort">2</P>
					</Branch>
					
				</Line>
			 * 
			 * 
			 * */
			
			/* TEST - store is true or false based on DstBlock. If DstBlock starts with Subsystem store is true else store is false.
			 * 
			 * Considering -
			 * 
			 * 
			 *     <Line>
						<P Name="ZOrder">1</P>
						<P Name="SrcBlock">Constant</P>
						<P Name="SrcPort">1</P>
						<P Name="DstBlock">Subsystem</P>
						<P Name="DstPort">1</P>
      				</Line>
			 */
			if(TestPortBranchSubsystem.portMap.get("1")!=null)
			{
				assertEquals("Constant",TestPortBranchSubsystem.portMap.get("1"));
			}
			
		}
		return tempList;
	}
}
