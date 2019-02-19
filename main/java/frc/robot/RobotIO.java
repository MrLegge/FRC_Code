/***********************************************************
* Date: 1-11-2018
* Changed for 2019
************************************************************/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;


public class RobotIO{
  
	private AHRS ahrs;
	public static AnalogInput potentiometer;
	private DigitalInput homeHatchSwitch;
	private DigitalInput lowerHatchSwitch;
	private static Encoder hatch_motor_encoder;
	
	public RobotIO(){
		/*********sets the switches**********/	
		try{
			homeHatchSwitch = new DigitalInput(RobotMap.DIO_PinOut.HOME_HATCH_SWITCH);
			lowerHatchSwitch = new DigitalInput(RobotMap.DIO_PinOut.LOWER_HATCH_SWITCH);
		}
		catch(RuntimeException ex ){
			DriverStation.reportError("Error instantiating switches  " + ex.getMessage(), true);
		}

		/*********stes the potentiometer**********/	
		try{
			potentiometer = new AnalogInput(RobotMap.Analog_PinOut.HATCH_POTENTIOMETER);
		}
		catch (RuntimeException ex ){
			DriverStation.reportError("Error instantiating potentiometer  " + ex.getMessage(), true);
		}
		
		/*********sets the encoder for the arm**********/	
		try{
			hatch_motor_encoder = new Encoder(RobotMap.DIO_PinOut.HATCH_MOTOR_ENCODER_A_CHANNEL,RobotMap.DIO_PinOut.HATCH_MOTOR_ENCODER_B_CHANNEL);
		}
		catch (RuntimeException ex ){
			DriverStation.reportError("Error instantiating encoder  " + ex.getMessage(), true);
		}

		/*********sets the ahrs thing**********/
		try{
			setAhrs(new AHRS(SPI.Port.kMXP));
		}
		catch (RuntimeException ex ){
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}

		
		
		//homeHatchSwitchAtFl= DigitakInput(RobotMap.HOME_HATCH_SWITCH_AT_FLOOR);
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

		/*************************************************************************************************************
		
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
	  
		/***************************************************************************************************************
		* these are the base functions to bring the gyro data in *
		AnalogGyro exampleAnalogGyro = new AnalogGyro(0);
		void	calibrate() Calibrate the gyro by running for a number of samples and computing the center value.
		int	getCenter() Return the gyro center value set during calibration to use as a future preset.
		double	getAngle() Return the actual angle in degrees that the robot is currently facing.
		void	initGyro() Initialize the gyro.
		void	reset() Reset the gyro.
	  ****************************************************************************************************************/
	 
	}

	/*********gets the ahrs thing**********/
	public AHRS getAhrs() {
		return ahrs;
	}

	/*********does something else to the ahrs thing**********/
	public void setAhrs(AHRS ahrs) {
		this.ahrs = ahrs;
	}

	/*********returns the potentiometer value**********/
	public static double getCurrentLiftDistance(){
		return potentiometer.getAverageVoltage();
	}

	/*********returns the encoder value**********/
	public static Encoder gethatch_motor_encoder() {
		return hatch_motor_encoder;
	}

	/*********sets the encoder up, needs tweaking**********/
	public static void setUpEncoder() {
		hatch_motor_encoder.reset();
		hatch_motor_encoder.setMaxPeriod(.1);
		hatch_motor_encoder.setMinRate(10);
		
		hatch_motor_encoder.setSamplesToAverage(10);
	}	

	//public boolean hatchSwitchAtLower() {
	//	return lowerHatchSwitch.get();
	//}
	
	/*public boolean hatchSwitchAtHome() {
		return homeHatchSwitch.get();
	
	}*/




	

}
