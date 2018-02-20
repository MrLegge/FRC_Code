package org.usfirst.frc.team6007.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into 
 * to a variable name. This provides flexibility changing wiring, makes checking 
 * the wiring easier and significantly reduces the number of magic numbers 
 * floating around. 
 */ 

	public class RobotMap{
		
	/*********************************
	*  Change these depending on pin configuration on RoboRIO *** 
	*********************************/
	/**** PWM PinOut ******/
	final int REAR_LEFT_MOTOR_ID = 0;
	final int FRONT_LEFT_MOTOR_ID = 1;
	final int FRONT_RIGHT_MOTOR_ID = 2;
	final int REAR_RIGHT_MOTOR_ID = 3;	
	final int RIGHT_TOP_LIFTER_MOTOR_ID = 4;
	final int RIGHT_BOTTOM_LIFTER_MOTOR_ID = 5;
	final int LEFT_GRABBER_MOTOR_ID = 6;
	final int RIGHT_GRABBER_MOTOR_ID = 7;
	
	/**** DIO PinOut ******/
	final int RIGHT_MOTOR_ENCODER_A_CHANNEL = 0;
	final int RIGHT_MOTOR_ENCODER_B_CHANNEL = 1;
	final int LEFT_MOTOR_ENCODER_A_CHANNEL = 2;
	final int LEFT_MOTOR_ENCODER_B_CHANNEL = 3;
	final int LIFTER_MOTOR_ENCODER_A_CHANNEL = 4;
	final int LIFTER_MOTOR_ENCODER_B_CHANNEL = 5;
		
		
}	