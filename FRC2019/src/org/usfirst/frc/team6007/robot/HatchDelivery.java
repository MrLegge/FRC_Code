/*******************************************************
* Robotics Hatch Delivery by Jordan Thorne date 22/01/19	
*******************************************************/
package org.usfirst.frc.team6007.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive; 
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;


public class HatchDelivery{
  
	DifferentialDrive hatchBase;
	private static RobotIO hatchtLifterPotentiometer;
	private RobotIO robotIO;
	private Spark leftHatchMotor;
	private Spark rightHatchMotor;
	
	public HatchDelivery(){
    
		leftHatchMotor = new Spark(RobotMap.PWM_PinOut.LEFT_HATCH_MOTOR_ID);
		rightHatchMotor = new Spark(RobotMap.PWM_PinOut.RIGHT_HATCH_MOTOR_ID);
    
		HatchBase = new DifferentialDrive(leftHatchMotor, rightHatchMotor);
	
	}


	
	//Puts disk in home position
	public void hatchLifterToHomePosition(double Power){
		while(!robotIO.hatchSwitchAtHome){
			HatchBase.arcadeDrive(Power, 0);
			//while so it runs until its false
		}
		
	}
	
	//Puts arm in delivery position
	public void deliveryPosition(double power) {
		HatchBase.arcadeDrive (power, 0) ;
	
	}
		
	//Retrieve from floor
	public void retriveHatchFromFloor(double DownPower){
		while (!robotIO.hatchSwitchAtLower){
			HatchBase.arcadeDrive(DownPower, 0);	
		}
		
		
	}
	
	
  }
