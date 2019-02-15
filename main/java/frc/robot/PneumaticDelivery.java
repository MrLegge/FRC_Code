/***********************************************************
Pneumatic file for delivering hatch from robot 
************************************************************/

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Solenoid;

public class PneumaticDelivery{
  //i dont know if anything extra goes here
  private Solenoid pushingPneumatic;
  private Solenoid pullingPneumatic;
  
  public PneumaticDelivery(){

	pushingPneumatic = new Solenoid(0); //pushes the solenoids out and is the left controller
	pullingPneumatic = new Solenoid(1); //pulls the solenoids in and is the right controller

}

	public void pushAndPull(double delay){//pushes it out and pulls it in 

		pushingPneumatic.set(true);
		Timer.delay(delay);			//fires out
		pushingPneumatic.set(false);
		
		pullingPneumatic.set(true);	
		Timer.delay(delay);			//pulls in
		pullingPneumatic.set(false);	
	}

}
