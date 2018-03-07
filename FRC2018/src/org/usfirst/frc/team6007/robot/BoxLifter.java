/* this is the code that brings the external libraries in for our use*/
package org.usfirst.frc.team6007.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive; 
//import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

// this is the declaraion of the class everything must be within these curly braces
public class BoxGrabber extends Subsystem{	
	// sets asside some space to make these objects will be declared later	
	DifferentialDrive grabberBase;

	/* motor controllers*/
	//private VictorSP leftGrabberMotor;
	//private VictorSP rightGrabberMotor;
	private Spark leftGrabberMotor;
	private Spark rightGrabberMotor;

	
	
	
	/* this is the constructor that 'builds' the object when called*/
	public BoxGrabber(){	
	/* this is where the motorcontrollers above get created but using the 'new' keyword and including the needed parametres*/
	//leftGrabberMotor = new VictorSP(RobotMap.PWM_PinOut.LEFT_GRABBER_MOTOR_ID);
	//rightGrabberMotor = new VictorSP(RobotMap.PWM_PinOut.RIGHT_GRABBER_MOTOR_ID);
	leftGrabberMotor = new Spark(RobotMap.PWM_PinOut.LEFT_GRABBER_MOTOR_ID);
	rightGrabberMotor = new Spark(RobotMap.PWM_PinOut.RIGHT_GRABBER_MOTOR_ID);		
	
	grabberBase = new DifferentialDrive(leftGrabberMotor, rightGrabberMotor);	
	}
	
	/* function to pull the power cube into the holder*/
	public void suckIn(double intakePower){
		grabberBase.arcadeDrive(intakePower, 0);
	}
	
	/* function to eject the power cube from the holder*/
	public void spitOut(double outputPower){
		grabberBase.arcadeDrive(outputPower, 0);
	}
	
	/*this will shuffle the cube around by sucking in and out to align it properly*/
	public void shuffle(){
		
			grabberBase.arcadeDrive(1, 0);
			Timer.delay(0.2);
			grabberBase.arcadeDrive(-1, 0);
			Timer.delay(0.2);
			grabberBase.arcadeDrive(1, 0);
			Timer.delay(0.2);
			grabberBase.arcadeDrive(-1, 0);
			Timer.delay(0.5);
			grabberBase.arcadeDrive(0, 0);
			
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
		
}
