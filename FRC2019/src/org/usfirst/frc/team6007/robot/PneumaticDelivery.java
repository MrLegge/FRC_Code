package frc.robot;

//i couldnt find the library because im a chungus
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;

public class PneumaticDelivery{
  //i dont know if anything extra goes here
  private Solenoid leftPneumatic;
  private Solenoid middlePneumatic;
  private Solenoid rightPneumatic;
  private Compressor compressor;
  
  public PneumaticDelivery(){
	//does that thing and get the ports from robotMAP
    leftPneumatic = new Solenoid(1);
    middlePneumatic = new Solenoid(2);
    rightPneumatic = new Solenoid(3);
    compressor = new Compressor(4);
}

	public void pushOut(){//just pushes it out
		leftPneumatic.set(true);
		middlePneumatic.set(true);
		rightPneumatic.set(true);   
	} 

	public void pullIn(){// just pulls it in
		leftPneumatic.set(false);
		middlePneumatic.set(false);
		rightPneumatic.set(false);
	}

	public void pushAndPull(double delay){//pushes it out and pulls it in 
		leftPneumatic.set(true);			  //after a chosen amount of time
		middlePneumatic.set(true);
		rightPneumatic.set(true); 
		Timer.delay(delay);
		leftPneumatic.set(false);
		middlePneumatic.set(false);
		rightPneumatic.set(false);		
	}

	public void turnCompressorOn(double timeOn){
		compressor.setClosedLoopControl(true);
		Timer.delay(timeOn);
		compressor.setClosedLoopControl(false);
	}
}
