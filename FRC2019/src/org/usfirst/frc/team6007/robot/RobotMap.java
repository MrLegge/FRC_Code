/***********************************************************
* this is just the copy of 2018 
* Date: 1-11-2018
************************************************************/

package frc.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into 
 * to a variable name. This provides flexibility changing wiring, makes checking 
 * the wiring easier and significantly reduces the number of magic numbers 
 * floating around. 
 */ 

	public class RobotMap{
		
		final static double HATCH_DELIVERY_UPPER_lIMIT = ?;
		final static double HATCH_DELIVERY_LOWER_lIMIT = ?;
	/*********************************
	*  Change these depending on pin configuration on RoboRIO *** 
	*********************************/
	public class PWM_PinOut {
		final static int REAR_LEFT_MOTOR_ID = 0;
		final static int FRONT_LEFT_MOTOR_ID = 1;
		final static int FRONT_RIGHT_MOTOR_ID = 2;
		final static int REAR_RIGHT_MOTOR_ID = 3;
		
	//2019 game
		final static int TOP_HATCH_MOTOR_ID = 4;
		final static int BOTTOM_HATCH_MOTOR_ID = 5;



	}
	public class DIO_PinOut{
		final static int HOME_HATCH_SWITCH = 0;
		final static int LOWER_HATCH_SWITCH = 1;
		final static int HOME_CARGO_SWITCH = 2;
		final static int LOWER_CARGO_SWITCH = 3;
		final static int HATCH_MOTOR_ENCODER_A_CHANNEL = 4;   
		final static int HATCH_MOTOR_ENCODER_B_CHANNEL = 5; 
	}	
	public class Analog_PinOut{
		//final static int PNEUMATIC = 0;
	}
	public class MyJoystick{
		final static int JOYSTICK_PORT = 0;
		final static int XBOXCONTROLLER_PORT = 1;
	}
}	
