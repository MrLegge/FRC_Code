
package org.usfirst.frc.team6007.robot;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc; 

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SampleRobot;

//New Import
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after 
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
	
	Joystick driverStick;
	RobotDrive driveBase;
	String gameData;
	
	

	/*********************************
	*  Change these depending on pin configuration on RoboRIO *** 
	*********************************/

	final int REAR_LEFT_MOTOR_ID = 0;
	final int FRONT_LEFT_MOTOR_ID = 1;
	final int FRONT_RIGHT_MOTOR_ID = 2;
	final int REAR_RIGHT_MOTOR_ID = 3;
	/*********************************
	*  Change these depending on time delay on RoboRIO *** 
	*********************************/
	
	//ADD OPTIONS FOR AUTONOMOUS 

	
	

	

	
	
	public Robot(){
		//Defines driverStick variable, can be used for extra driverSticks
		driverStick = new Joystick(0);
		/*
		//COMMENT OUT IF SPARK MOTOR CONTROLLER IS USED
		Spark m_frontLeft = new Spark(1);
		Spark m_rearLeft = new Spark(0)
		SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

		Spark m_frontRight = new Spark(2)
		Spark m_rearRight = new Spark(3)
		SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);

		DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
		*/
		
		 
		//COMMENT OUT IF VICTORSP MOTOR CONTROLLER IS USED
		Spark m_frontLeft = new Spark(1);
		Spark m_rearLeft = new Spark(0)
		SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

		Spark m_frontRight = new Spark(2)
		Spark m_rearRight = new Spark(3)
		SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);

		DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

		
		//Use Talon to define another motor controller
		

		
		//This stops the robot if no input received SAFETY!!
		
		driveBase.setExpiration(0.1);
		
		
	}
	
	public void robotInit(){
		//NOT SURE YET CHECK MOTOR DIRECTIONS
		/*driveBase.setInvertedMotor(MotorType.kFrontRight, true);
		driveBase.setInvertedMotor(MotorType.kRearRight, true);
		driveBase.setInvertedMotor(MotorType.kFrontLeft, true);
		driveBase.setInvertedMotor(MotorType.kRearLeft, true);*/

/*********************************** DONT CHANGE THIS CODE!!!	*****************************************************/
		 new Thread(() -> {
                UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
                camera.setResolution(640, 480);
                 
                CvSink cvSink = CameraServer.getInstance().getVideo();
                CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
                
                Mat source = new Mat();
                Mat output = new Mat();
                
                while(!Thread.interrupted()) {
                    cvSink.grabFrame(source);
                    Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
                    outputStream.putFrame(output);
                }
            }).start();
/*************************************************CAN CHANGE BELOW THIS *************************************************/	
	}
	
		//Disables the setExpiration to stop robot stopping
	public void autonomous(){
		driveBase.setSafetyEnabled(false);
		
		/*driveBase.setInvertedMotor(MotorType.kFrontRight, true);
		driveBase.setInvertedMotor(MotorType.kRearRight, true);
		driveBase.setInvertedMotor(MotorType.kFrontLeft, true);
		driveBase.setInvertedMotor(MotorType.kRearLeft, true);*/
		
		
		/*driveBase.setInvertedMotor(MotorType.kFrontRight, true);
		driveBase.setInvertedMotor(MotorType.kRearRight, true);
		driveBase.setInvertedMotor(MotorType.kFrontLeft, true);
		driveBase.setInvertedMotor(MotorType.kRearLeft, true);*/
		
		
		
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L')
		{
			//Put left auto code here
		} else {
			//Put right auto code here
		}
		
		
		driveBase.setSafetyEnabled(true);
	}
	
	public void operatorControl(){
		driveBase.setSafetyEnabled(true);
	  	
		//Ensures robot only drives when under operator control 
		while(isOperatorControl() && isEnabled()){
			
			//Exponential Speed Controller
			//double speedSlider = driverStick.getRawAxis(3) + 2;
			
			// X-axis for turning , Y-axis for forward/back  
			
			double speedModifierX = 1.0;
			double speedModifierY = -1.0;

			//Sets speed to half when side button is held, for fine control
			if(driverStick.getRawButton(1)){
				speedModifierX = 0.7;
				speedModifierY = -0.7;			
			}


			
			//Sets the driving method
			//Use this one for z rotation
			driveBase.arcadeDrive(driverStick.getRawAxis(1)*speedModifierY, driverStick.getRawAxis(2)*speedModifierX, true);
			
			//Use this one for x rotation
			//driveBase.arcadeDrive(driverStick.getRawAxis(1)*speedModifierY, driverStick.getRawAxis(0)*speedModifierX, true);
			

			
		}
	}
	
	public void test(){
		
	} 
	
	/*public RobotCamera getCamera(){
		return camera;
	}*/
	
}
