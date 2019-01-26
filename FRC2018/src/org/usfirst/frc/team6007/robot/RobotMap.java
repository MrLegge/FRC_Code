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
/*********************PWM_Pinout****************************************/
		final static int REAR_LEFT_MOTOR_ID = 0;
		final static int FRONT_LEFT_MOTOR_ID = 1;
		final static int FRONT_RIGHT_MOTOR_ID = 2;
		final static int REAR_RIGHT_MOTOR_ID = 3;	
		
		final static int RIGHT_TOP_LIFTER_MOTOR_ID = 4;
		final static int RIGHT_BOTTOM_LIFTER_MOTOR_ID = 5;
		
		final static int LEFT_GRABBER_MOTOR_ID = 7;
		final static int RIGHT_GRABBER_MOTOR_ID = 6;

/***********************DIO_PinOut************************************/
		final static int RIGHT_MOTOR_ENCODER_A_CHANNEL = 0;
		final static int RIGHT_MOTOR_ENCODER_B_CHANNEL = 1;
		
		final static int LEFT_MOTOR_ENCODER_A_CHANNEL = 2;
		final static int LEFT_MOTOR_ENCODER_B_CHANNEL = 3;
		
		final static int LIFTER_MOTOR_ENCODER_A_CHANNEL = 4;
		final static int LIFTER_MOTOR_ENCODER_B_CHANNEL = 5;
	
/**************************Analog_PinOut******************************/
		final static int ROBOT_LIFTER_GYRO = 0;
	
/***************************JoyStick control *******************/
		final static int JOYSTICK_PORT = 0; 
		final static int JOYSTICK_AXIS = 3;
		final static int JOYSTICK_TRIGGER_NUMBER = 1;
		final static int SHUFFLE_BUTTON = 2;
		final static int SUCK_IN_BUTTON = 7;
		final static int SWITCH_HEIGHT_BUTTON = 8;
		final static int SPIT_OUT_BUTTON = 11;

}	
