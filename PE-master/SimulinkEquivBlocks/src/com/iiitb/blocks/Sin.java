package com.iiitb.blocks;

import java.util.ArrayList;
import java.util.List;

import com.iiitb.cfg.Accfg;
import com.iiitb.utility.TypeParseXML;

import expression.ConcreteConstant;
import expression.ConcreteFloatConstant;
import expression.Expression;
import expression.Func_call;
import expression.Variable;

public class Sin extends Block {


	private Expression lhs;
	private Expression name;
	private List<Expression> args_list=new ArrayList<Expression>();
	private String input1;
	private float amplitude=1;
	private int frequency=0;
	private float STime=0;

	public Expression getLhs() {
		return lhs;
	}

	public void setLhs(Variable lhs) {
		this.lhs = lhs;
	}




	private Accfg accfg;

	public Accfg getAccfg() {
		return accfg;
	}

	public void setAccfg(Accfg accfg) {
		this.accfg = accfg;
	}

	@Override
	public Expression expression() {
		setExpressionSet(true);	
		
		
		
			try {
				Variable v=new Variable("SinWave", this);
				
				return(new Func_call(this, v , args_list,getOutput(),TypeParseXML.getReturnType(v) ));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	
	}

	@Override
	public ArrayList<Expression> getInput() {
		List<Expression> inputs = new ArrayList<Expression>();
		inputs.add(getLhs());
		return (ArrayList<Expression>)inputs;
	}






	public String getInput1() {
		return input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}
	@Override
	public void setInput(String input,String port) {


		setInput1(input);
		try {
			lhs = new Variable(input, this);
			if (lhs != null){
				setInputSetFlag(true);
			//System.out.println(getGain()+"sssssssssssssssssssssssssssssssssssssssssss");
			/*args_list.add(new ConcreteFloatConstant(getAmplitude(), this, new Variable("Amplitude", this)));
			args_list.add(new ConcreteConstant(getFrequency(), this, new Variable("Frequency", this)));*/
				args_list.add(new ConcreteFloatConstant(getSTime(), this, new Variable("STime", this)));
			args_list.add(lhs);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("Input1 is set");

	}

	public Sin(String blockName) {
		// TODO Auto-generated constructor stub

		super(blockName);
		Accfg accfgObj = new Accfg();
		List<Expression> outputTemp = new ArrayList<Expression>();
		outputTemp.add(getOutput());
		accfgObj.setOutput(outputTemp);
		setAccfg(accfgObj);

	}

	public Expression getName() {
		return name;
	}

	public void setName(Expression name) {
		this.name = name;
	}

	public float getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(float amplitude) {
		this.amplitude = amplitude;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public float getSTime() {
		return STime;
	}

	public void setSTime(float f) {
		STime = f;
	}

	
}

