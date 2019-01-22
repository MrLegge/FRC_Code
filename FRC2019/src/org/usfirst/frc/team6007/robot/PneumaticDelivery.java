package org.usfirst.frc.team6007.robot;

//i couldnt find the library because im a chungus
import edu.wpi.first.wpilibj.Timer;

public class PneumaticDelivery extends Subsystem{
  //i dont know if anything extra goes here
  private Solenoid leftPneumatic;
  private Solenoid middlePneumatic;
  private Solenoid rightPneumatic;
  private Compressor compressor;
  
  public PneumaticDelivery(){
	//does that thing and get the ports from robotMAP
    Solenoid leftPneumatic = new Solenoid(LEFT_SOLENOID_ID);
    Solenoid middlePneumatic = new Solenoid(MIDDLE_SOLENOID_ID);
    Solenoid rightPneumatic = new Solenoid(RIGHT_SOLENOID_ID);
    Compressor compressor = new Compressor(COMPRESSOR_ID);
  }

	public void pushOut(){//just pushes it out
		leftPneumatic(true);
		middlePneumatic(true);
		rightPneumatic(true);   
	} 

	public void pullIn(){// just pulls it in
		leftPneumatic(false);
		middlePneumatic(false);
		rightPneumatic(false);
	}

	public void pushAndPull(double delay){//pushes it out and pulls it in 
		leftPneumatic(true);			  //after a chosen amount of time
		middlePneumatic(true);
		rightPneumatic(true); 
		Timer.delay(delay);
		leftPneumatic(false);
		middlePneumatic(false);
		rightPneumatic(false);		
	}

	public void turnCompressorOn(double timeOn){
		compressor.setClosedLoopControl(true);
		Timer.delay(timeOn);
		compressor.setClosedLoopControl(false);
	}
}
