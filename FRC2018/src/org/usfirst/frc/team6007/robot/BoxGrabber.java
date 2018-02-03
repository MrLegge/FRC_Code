package org.usfirst.frc.team6007.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive 

public class BoxGrabber{
	
	Joystick driverStick;
	DifferentialDrive grabberBase;

	private Spark leftGrabberMotor;
	private Spark rightGrabberMotor;
	final int LEFT_GRABBER_MOTOR_ID = 2;
	final int RIGHT_GRABBER_MOTOR_ID = 3;
	private double MotorSpeed = 0.5;
	

	
	

	
	
	public BoxGrabber(){
	
	
	leftGrabberMotor = new Spark(LEFT_GRABBER_MOTOR_ID);
	rightGrabberMotor = new Spark(RIGHT_GRABBER_MOTOR_ID);
		
	driverStick = new Joystick(0);
	
	grabberBase = new DifferentialDrive(leftGrabberMotor, rightGrabberMotor);
	
																															
	//comment out one inversion
	grabberBase.setInvertedMotor(MotorType.kFrontRight, true);
	grabberBase.setInvertedMotor(MotorType.kFrontLeft, true);
	
	}
	

	
	
	
	
	public void suckIn(){

		grabberBase.arcadeDrive(1, 0);
		
	}
	
	public void spitOut(){

		grabberBase.arcaeDrive(-1, 0);
		
	}

		
		
}
