/************************************************************
* Robotics Hatch Delivery by Jordan Thorne date 21/01/19	
************************************************************/
package org.usfirst.frc.team6007.robot;


public class HatchDelivery{
  
	private static RobotIO hatchtLifterPotentiometer;
  	private static ReadSwitch homePosition;
  	private static ReadSwitch floorPosition;

	public HatchDelivery(){
    
		leftHatchMotor = new Spark(RobotMap.PWM_PinOut.LEFT_HATCH_MOTOR_ID);
		rightHatchMotor = new Spark(RobotMap.PWM_PinOut.RIGHT_HATCH_MOTOR_ID);
    
		HatchBase = new DifferentialDrive(leftHatchMotor, rightHatchMotor);
	
	}
	
	//Picks up Disk
	public void LiftUp(double Power){
		HatchBase.arcadeDrive(Power, 0);
	}
	
	//Puts down Disk
	public void PutDown(double DownPower){
		HatchBase.arcadeDrive(DownPower, 0);
	}
	
	
  }
