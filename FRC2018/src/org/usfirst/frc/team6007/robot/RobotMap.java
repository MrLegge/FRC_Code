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
	public class PWM_PinOut {
		public static final int REAR_LEFT_MOTOR_ID = 0;                //motor pins are correct as of 3/3
		public static final int FRONT_LEFT_MOTOR_ID = 1;
		public static final int FRONT_RIGHT_MOTOR_ID = 2;
		public static final int REAR_RIGHT_MOTOR_ID = 3;	
		
		public static final int RIGHT_TOP_LIFTER_MOTOR_ID = 4;
		public static final int RIGHT_BOTTOM_LIFTER_MOTOR_ID = 5;
		
		public static final int LEFT_GRABBER_MOTOR_ID = 6;
		public static final int RIGHT_GRABBER_MOTOR_ID = 7;
	}
	public class DIO_PinOut{                                                 //encoder pins are correct as of 3/3
		public static final int RIGHT_MOTOR_ENCODER_A_CHANNEL = 0;
		public static final int RIGHT_MOTOR_ENCODER_B_CHANNEL = 1;
		
		public static final int LEFT_MOTOR_ENCODER_A_CHANNEL = 2;
		public static final int LEFT_MOTOR_ENCODER_B_CHANNEL = 3;
		
		public static final int LIFTER_MOTOR_ENCODER_A_CHANNEL = 4;
		public static final int LIFTER_MOTOR_ENCODER_B_CHANNEL = 5;
	}	
	public class Analog_PinOut{                                               //gyro not yet put in
		public static final int ROBOT_LIFTER_GYRO = 0;
	}
		
	/****************************************************************************
	* Constants for use in the calculations
	*****************************************************************************/	
	public class Robot_Constants{
		public static final double CIRCUMFERENCE_OF_WHEEL = 0.4787787204070844895417068516118;//in metres 
		public static final double MOTOR_TURNS_PER_TURN_OF_WHEEL = 0;//needs to be found for encoder distance
		public static final double DISTANCE_PER_PULSE = CIRCUMFERENCE / (3 * 512);
	}
	public class PID {
	   	 public static final double kP = 0.001;
	   	 public static final double kI = 0.0;
	  	 public static final double kD = 0.0;
	}
}	
