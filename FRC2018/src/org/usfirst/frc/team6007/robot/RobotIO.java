package org.usfirst.frc.team6007.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;


public class RobotIO{
  
	private AHRS ahrs;
	public Joystick driverStick;
	private static AnalogGyro robotLifterGyro;
	private Encoder right_motor_encoder, left_motor_encoder, lifter_motor_encoder;
 


	public RobotIO(){
	
		/************************************************************************************************************
		*these are the functions to get data from the navX board*
		AHRS ahrs = new AHRS(SPI.Port.kMXP); 
		double	getAngle()	Returns the total accumulated yaw angle (Z Axis, in degrees) reported by the sensor.
		float	getCompassHeading()	Returns the current tilt-compensated compass heading value (in degrees, from 0 to 360) reported by the sensor.
		double	pidGet() Returns the current yaw value reported by the sensor.
		float	getRawGyroX() Returns the current raw (unprocessed) X-axis gyro rotation rate (in degrees/sec).
		float	getRawGyroY() Returns the current raw (unprocessed) Y-axis gyro rotation rate (in degrees/sec).
		float	getRawGyroZ() Returns the current raw (unprocessed) Z-axis gyro rotation rate (in degrees/sec).
		float	getRawMagX() Returns the current raw (unprocessed) X-axis magnetometer reading (in uTesla).
		float	getRawMagY() Returns the current raw (unprocessed) Y-axis magnetometer reading (in uTesla).
		float	getRawMagZ() Returns the current raw (unprocessed) Z-axis magnetometer reading (in uTesla).
		void	reset() Reset the Yaw gyro.
		void	setPIDSourceType(PIDSourceType type) 
		void	zeroYaw() Sets the user-specified yaw offset to the current yaw value reported by the sensor.	
		
		*************************************************************************************************************/
		try{
			setAhrs(new AHRS(SPI.Port.kMXP));
			}
		catch (RuntimeException ex ){
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
			}
		/*************************************************************************************************************
		* these are the functions to get data from the encoders *
		int count = sampleEncoder.get();
		double distance = sampleEncoder.getRaw();
		double distance = sampleEncoder.getDistance();
		double period = sampleEncoder.getPeriod();
		double rate = sampleEncoder.getRate();
		boolean direction = sampleEncoder.getDirection();
		boolean stopped = sampleEncoder.getStopped();
		sampleEncoder.reset();
		
		double	pidGet() Implement the PIDSource interface.
		void	setPIDSourceType(PIDSourceType pidSource) Set which parameter of the encoder you are using as a process control variable.
	  
		Count - The current count. May be reset by calling reset().
		Raw Count - The count without compensation for decoding scale factor.
		Distance - The current distance reading from the counter. This is the count multiplied by the Distance Per Count scale factor.
		Period - The current period of the counter in seconds. If the counter is stopped this value may return 0. This is deprecated, it is recommended to use rate instead.
		Rate - The current rate of the counter in units/sec. It is calculated using the DistancePerPulse divided by the period. If the counter is stopped this value may return Inf or NaN, depending on language.
		Direction - The direction of the last value change (true for Up, false for Down)
		Stopped - If the counter is currently stopped (period has exceeded Max Period)
		****************************************************************************************************************/
	  try {
		  right_motor_encoder = new Encoder(RobotMap.DIO_PinOut.RIGHT_MOTOR_ENCODER_A_CHANNEL, RobotMap.DIO_PinOut.RIGHT_MOTOR_ENCODER_B_CHANNEL, true, Encoder.EncodingType.k4X);
		  }
	  catch (RuntimeException ex ){
		  DriverStation.reportError("Error instantiating the right encoder:  " + ex.getMessage(), true);
		  }
	  try {
		  left_motor_encoder = new Encoder(RobotMap.DIO_PinOut.LEFT_MOTOR_ENCODER_A_CHANNEL, RobotMap.DIO_PinOut.LEFT_MOTOR_ENCODER_B_CHANNEL, false, Encoder.EncodingType.k4X);
		  }
	  catch (RuntimeException ex ){
		  DriverStation.reportError("Error instantiating the left encoder:  " + ex.getMessage(), true);
		  }
	  try {
		  lifter_motor_encoder = new Encoder(RobotMap.DIO_PinOut.LIFTER_MOTOR_ENCODER_A_CHANNEL, RobotMap.DIO_PinOut.LIFTER_MOTOR_ENCODER_B_CHANNEL, false, Encoder.EncodingType.k4X);
		  }
	  catch (RuntimeException ex ){
		  DriverStation.reportError("Error instantiating the lifter encoder:  " + ex.getMessage(), true);
		  }
	  
		/***************************************************************************************************************
		* these are the base functions to bring the gyro data in *
		AnalogGyro exampleAnalogGyro = new AnalogGyro(0);
		void	calibrate() Calibrate the gyro by running for a number of samples and computing the center value.
		int	getCenter() Return the gyro center value set during calibration to use as a future preset.
		double	getAngle() Return the actual angle in degrees that the robot is currently facing.
		void	initGyro() Initialize the gyro.
		void	reset() Reset the gyro.
	  ****************************************************************************************************************/
	  try {
		  robotLifterGyro = new AnalogGyro(RobotMap.Analog_PinOut.ROBOT_LIFTER_GYRO);
		  robotLifterGyro.initGyro();
		  robotLifterGyro.calibrate();
		}catch (RuntimeException ex ){
		  DriverStation.reportError("Error instantiating the lifter gyro:  " + ex.getMessage(), true);
		}
	}

	// NavX related methods for data
	public AHRS getAhrs() {
		return ahrs;
	}

	public void setAhrs(AHRS ahrs) {
		this.ahrs = ahrs;
	}
	
	public float getRobotHeading(){
		System.out.print("angle via NavX: ");
		System.out.println(ahrs.getAngle());
		return ahrs.getAngle();	
	}
	
	public static double getRobotLifterGyroAngle() {
		return robotLifterGyro.getAngle();
	}

	public Encoder getLifter_motor_encoder() {
		return lifter_motor_encoder;
	}

	public Encoder getLeft_motor_encoder() {
		return left_motor_encoder;
	}

	public Encoder getRight_motor_encoder() {
		return right_motor_encoder;
	}
	
	public boolean driveStraight(double distance){
		
		private int currentLeftTurns = left_motor_encoder.get();
		private int currentRightTurns = right_motor_encoder.get();
		
		if(currentLeftTurns != currentRightTurns){
		
			if(currentLeftTurns > currentRightTurns){
			
				System.out.print("left turns is bigger than right  left: ");
				System.out.print(currentLeftTurns);
				System.out.print("   right: ");
				System.out.println(currentRightTurns);
			}
		
			else{
			
				System.out.println("");
				
			}
		}
		
		
		while(currentRightTurns < distance && currentLeftTurns < distance){
		
			}
		
		}

	}
