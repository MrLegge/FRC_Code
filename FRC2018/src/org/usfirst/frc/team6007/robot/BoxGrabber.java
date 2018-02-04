// this is the code that brings the external libries in for our use
package org.usfirst.frc.team6007.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive 

// this is the declaraion of the class everything must be within these curly braces
public class BoxGrabber{
	
	// sets asside some space to make these objects will be declared later
	Joystick driverStick;

	DifferentialDrive grabberBase;



	// motor controller

	private Spark leftGrabberMotor;
	private Spark rightGrabberMotor;
	
	// these are the constants that need to be used later the numeral relates to the port that the motor controller is pluged in  
	final int LEFT_GRABBER_MOTOR_ID = 2;
	final int RIGHT_GRABBER_MOTOR_ID = 3;
	
	// these are the variables that we will use latter
	private double MotorSpeed = 0.5;
	

	// this is the constructor that 'builds' the object when called
	public BoxGrabber(){
	

	
	leftGrabberMotor = new Spark(LEFT_GRABBER_MOTOR_ID);
	rightGrabberMotor = new Spark(RIGHT_GRABBER_MOTOR_ID);
		
	driverStick = new Joystick(0);
	
	grabberBase = new DifferentialDrive(leftGrabberMotor, rightGrabberMotor);
	
																															
	//comment out one inversion
	grabberBase.setInvertedMotor(MotorType.kFrontRight, true);
	grabberBase.setInvertedMotor(MotorType.kFrontLeft, true);

		// this is where the objects above get created but using the 'new' keyword and including the needed parametres  
		driverStick = new Joystick(0);

		leftGrabberMotor = new Spark(LEFT_GRABBER_MOTOR_ID);
		rightGrabberMotor = new Spark(RIGHT_GRABBER_MOTOR_ID);
																													
		//comment out one inversion
		//grabberBase.setInvertedMotor(MotorType.kFrontRight, true);
		grabberBase.setInvertedMotor(MotorType.kFrontLeft, true);

	
	}
	
	// function to pull the power cube into the holder
	public void suckIn(){


		grabberBase.arcadeDrive(1, 0);
		

		Timer.delay(1);

	}
	
	// function to eject the power cube from the holder
	public void spitOut(){

		grabberBase.arcaeDrive(-1, 0);

	
	}
	
	//this will shuffle the cube around by sucking in and out to align it properly
	public void shuffle(){

		
		grabberBase.drive(1, 0);
		Timer.delay(0.2);
		grabberBase.drive(-1, 0);
		Timer.delay(0.2);
		grabberBase.drive(1, 0);
		Timer.delay(0.2);
		grabberBase.drive(-1, 0);
		Timer.delay(0.5);
		grabberBase.drive(0, 0);
	}

		
}
