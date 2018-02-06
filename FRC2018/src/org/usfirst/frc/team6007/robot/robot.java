
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
	int startPos;
	startPos = 0;           //change depending on our starting position in auto (0,1,2,3 cases, 1,2,3 positions left to right)
	
	

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
		Spark motor_frontLeft = new Spark(1);
		Spark motor_rearLeft = new Spark(0)
		SpeedControllerGroup motors_left = new SpeedControllerGroup(motor_frontLeft, motor_rearLeft);

		Spark motor_frontRight = new Spark(2)
		Spark motor_rearRight = new Spark(3)
		SpeedControllerGroup motors_right = new SpeedControllerGroup(motor_frontRight, motor_rearRight);

		DifferentialDrive motor_drive = new DifferentialDrive(motors_left, motors_right);

		
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
		  switch(startPos){
			  case 0:                       //position 1 (left) going to left side
				  //drive forward 
				  //turn right 90
				  //drive forward 
				  //drop cube
			  break;
			  case 1:                       //position 1 (left) going to left side
				  //drive forward
				  //drop cube
			  break;
			  case 2:                       //center position (2) going to left side
				  //drive forward
				  //turn 45
				  //drive forward
				  //drop cube
			  break;
			  case 3:                       //position 3 (right) going to left side
				 //drive forward
				 //turn left 90
				 //drive forward
				 //turn left 90
				 //drive forward
				 //drop cube
			  break;
			  default: 
			  break;
		  }
		} 
		else {
		  switch(startPos){
			  case 0:                       //position 3 (right) going to right side
				  //drive forward 
				  //turn right 90
				  //drive forward 
				  //drop cube
			  break;
			  case 1:                       //position 3 (right) going to right side
				  //drive forward
				  //drop cube
			  break;
			  case 2:                       //center position (2) going to right side
				  //drive forward
				  //turn 45
				  //drive forward
				  //drop cube
			  break;
			  case 3:                       //position 1 (left) going to right side
				 //drive forward
				 //turn left 90
				 //drive forward
				 //turn left 90
				 //drive forward
				 //drop cube
			  break;
			  default: 
			  break;
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
			
			double speedModifierX = -1.0; //changed to -ve to invert the twist turn
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
