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
	
	/*********************PWM_PinOut****************************/ 
		public static final int REAR_LEFT_MOTOR_ID = 0;                //motor pins are correct as of 3/3
		public static final int FRONT_LEFT_MOTOR_ID = 1;
		public static final int FRONT_RIGHT_MOTOR_ID = 2;
		public static final int REAR_RIGHT_MOTOR_ID = 3;	
		
		public static final int RIGHT_TOP_LIFTER_MOTOR_ID = 4;
		public static final int RIGHT_BOTTOM_LIFTER_MOTOR_ID = 5;
		
		public static final int LEFT_GRABBER_MOTOR_ID = 6;
		public static final int RIGHT_GRABBER_MOTOR_ID = 7;
		
	/*******************DIO_PinOut*******************************/                                                 //encoder pins are correct as of 3/3
		public static final int RIGHT_MOTOR_ENCODER_A_CHANNEL = 0;
		public static final int RIGHT_MOTOR_ENCODER_B_CHANNEL = 1;
		
		public static final int LEFT_MOTOR_ENCODER_A_CHANNEL = 2;
		public static final int LEFT_MOTOR_ENCODER_B_CHANNEL = 3;
		
		public static final int LIFTER_MOTOR_ENCODER_A_CHANNEL = 4;
		public static final int LIFTER_MOTOR_ENCODER_B_CHANNEL = 5;
	
	/*************************Analog_PinOut************************/                                               //gyro not yet put in
		public static final int ROBOT_LIFTER_GYRO = 0;
	
		
	/****************************************************************************
	* Constants for use in the calculations
	*****************************************************************************/	

		public static final double CIRCUMFERENCE_OF_WHEEL = 0.4787787204070844895417068516118;//in metres 
		public static final double MOTOR_TURNS_PER_TURN_OF_WHEEL = 0;//needs to be found for encoder distance
		public static final double DISTANCE_PER_PULSE = CIRCUMFERENCE / (3 * 512);
	
	/************************************PID*************************************/
	   	 public static final double kP = 0.001;
	   	 public static final double kI = 0.0;
	  	 public static final double kD = 0.0;
	
	/**********************************************************************************
	* these are the motor control variables 
	***********************************************************************************/
		// drive base
		public static PWMSpeedController motor_frontLeft;
		public static PWMSpeedController motor_rearLeft;
		public static PWMSpeedController motor_frontRight;
		public static PWMSpeedController motor_rearRight;
		
		public static SpeedControllerGroup motors_left;
		public static SpeedControllerGroup motors_right;
		
		// lifter base
		private static PWMSpeedController topLifterMotor;
		private static PWMSpeedController bottomLifterMotor;
		
		//grabber base
		private PWMSpeedController leftGrabberMotor;
		private PWMSpeedController rightGrabberMotor;
		
	/***********************************************************************************
	* these is the drive bases fpr the different bases
	***********************************************************************************/	
		public static DifferentialDrive driveBase;
		public static DifferentialDrive lifterBase;
		public static DifferentialDrive grabberBase;
	
	public static void robotInit(){
		// drive base
		motor_frontLeft = new Spark(FRONT_LEFT_MOTOR_ID);
		motor_rearLeft = new Spark(REAR_LEFT_MOTOR_ID);
		motor_frontRight = new Spark(FRONT_RIGHT_MOTOR_ID);
		motor_rearRight = new Spark(REAR_RIGHT_MOTOR_ID);
		
		/*COMMENT OUT IF VICTORSP MOTOR CONTROLLER IS USED*/
		//motor_frontLeft = new VictorSP(FRONT_LEFT_MOTOR_ID);
		//motor_rearLeft = new VictorSP(REAR_LEFT_MOTOR_ID);
		//motor_frontRight = new VictorSP(FRONT_RIGHT_MOTOR_ID);
		//motor_rearRight = new VictorSP(REAR_RIGHT_MOTOR_ID);
		
		motors_left = new SpeedControllerGroup(motor_frontLeft, motor_rearLeft);
		motors_right = new SpeedControllerGroup(motor_frontRight, motor_rearRight);
		
		driveBase = new DifferentialDrive(motors_left, motors_right);
		
		// lifter base
		topLifterMotor = new Spark(RIGHT_TOP_LIFTER_MOTOR_ID);
		bottomLifterMotor = new Spark(RIGHT_BOTTOM_LIFTER_MOTOR_ID);	
		//topLifterMotor = new VictorSP(RIGHT_TOP_LIFTER_MOTOR_ID);
		//bottomLifterMotor = new VictorSP(RIGHT_BOTTOM_LIFTER_MOTOR_ID);
		
		bottomLifterMotor.setInverted(true);
		
		lifterBase = new DifferentialDrive(topLifterMotor, bottomLifterMotor);
		
		//grabber base
		leftGrabberMotor = new Spark(LEFT_GRABBER_MOTOR_ID);
		rightGrabberMotor = new Spark(RIGHT_GRABBER_MOTOR_ID);
		//leftGrabberMotor = new VictorSP(LEFT_GRABBER_MOTOR_ID);
		//rightGrabberMotor = new VictorSP(RIGHT_GRABBER_MOTOR_ID);
	
		grabberBase = new DifferentialDrive(leftGrabberMotor, rightGrabberMotor);
	}
}	
