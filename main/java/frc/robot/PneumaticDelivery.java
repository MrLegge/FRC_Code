/***********************************************************
Pneumatic file for delivering hatch from robot 
************************************************************/

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Solenoid;

public class PneumaticDelivery{
  private Solenoid pushingPneumatic;
  private Solenoid pullingPneumatic;
  
	public PneumaticDelivery(){
	/***********sets the solenoids up**************/
	pushingPneumatic = new Solenoid(0); //pushes the solenoids out and is the left controller
	pullingPneumatic = new Solenoid(1); //pulls the solenoids in and is the right controller

	}

	/***********pushes the pneumatics out and pulls it in**************/
	public void pushAndPull(double delay){

		pushingPneumatic.set(true);
		Timer.delay(delay);			//fires out
		pushingPneumatic.set(false);
		
		pullingPneumatic.set(true);	
		Timer.delay(delay);			//pulls in
		pullingPneumatic.set(false);	
	}

}
