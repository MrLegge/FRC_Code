
package org.usfirst.frc.team6007.robot;

import edu.wpi.first.wpilibj.TimedRobot;

//camera stuff
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc; 

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

//New Imports
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DriverStation;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

//sensor imports
import edu.wpi.first.wpilibj.Encoder;


public class Robot extends TimedRobot {
	
	Joystick driverStick;
	DifferentialDrive driveBase;
	String gameData;
	int startPos;
	BoxGrabber boxGrab;
	BoxLifter boxlift;
	Encoder right_motor_encoder;
	Encoder left_motor_encoder;
	Encoder lifter_motor_encoder;
	

	
	//ADD OPTIONS FOR AUTONOMOUS 
	startPos = 0;
	
	public Robot(){
		/*Defines driverStick variable, can be used for extra driverSticks*/
		driverStick = new Joystick(0);
		boxGrab = new BoxGrabber();
		boxlift = new BoxLifter();
		
		right_motor_encoder = new Encoder(RobotMap.RIGHT_MOTOR_ENCODER_A_CHANNEL, RobotMap.RIGHT_MOTOR_ENCODER_B_CHANNEL, true, Encoder.EncodingType.k2X);
		left_motor_encoder = new Encoder(RobotMap.LEFT_MOTOR_ENCODER_A_CHANNEL, RobotMap.LEFT_MOTOR_ENCODER_B_CHANNEL, false, Encoder.EncodingType.k2X);
		lifter_motor_encoder = new Encoder(RobotMap.LIFTER_MOTOR_ENCODER_A_CHANNEL, RobotMap.LIFTER_MOTOR_ENCODER_B_CHANNEL, false, Encoder.EncodingType.k2X);
		
		
	
		/*COMMENT OUT IF SPARK MOTOR CONTROLLER IS USED*/
		//Spark motor_frontLeft = new Spark(FRONT_LEFT_MOTOR_ID);
		//Spark motor_rearLeft = new Spark(REAR_LEFT_MOTOR_ID);
		//Spark motor_frontRight = new Spark(FRONT_RIGHT_MOTOR_ID);
		//Spark motor_rearRight = new Spark(REAR_RIGHT_MOTOR_ID);

		/*COMMENT OUT IF VICTORSP MOTOR CONTROLLER IS USED*/
		VictorSP motor_frontLeft = new VictorSP(RobotMap.FRONT_LEFT_MOTOR_ID);
		VictorSP motor_rearLeft = new VictorSP(RobotMap.REAR_LEFT_MOTOR_ID);
		VictorSP motor_frontRight = new VictorSP(RobotMap.FRONT_RIGHT_MOTOR_ID);
		VictorSP motor_rearRight = new VictorSP(RobotMap.REAR_RIGHT_MOTOR_ID);

		SpeedControllerGroup motors_left = new SpeedControllerGroup(motor_frontLeft, motor_rearLeft);
		SpeedControllerGroup motors_right = new SpeedControllerGroup(motor_frontRight, motor_rearRight);

		driveBase = new DifferentialDrive(motors_left, motors_right);
		
		//This stops the robot if no input received SAFETY!!
		driveBase.setExpiration(0.1);

	}
	
	public void robotInit(){

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

		
	public void autonomousPeriodic(){
		//Disables the setExpiration to stop robot stopping
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
				  //driveBase.drive(0.0,0);
				// Timer.delay(1.0);
				//  driveBase.drive(0, 0);
				  break;
			}
		}
		driveBase.setSafetyEnabled(true);
	}
	
	public void teleopPeriodic(){   //teleopPeriodic   operatorControl
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
				speedModifierX = -driverStick.getRawAxis(3);
				speedModifierY = -driverStick.getRawAxis(3);			
			}


			if (driverStick.getRawButton(3)){
				
				double intakePower = -0.7;  //this value will need to be created from the PID data
				boxGrab.suckIn(intakePower);
				
			}
			
			if (driverStick.getRawButton(4)){
				
				double outputPower = 1;  //this value will need to be created from the PID data
				boxGrab.spitOut(outputPower);
				
			}
			
			if (driverStick.getRawButton(2)){
				
				boxGrab.shuffle();
				
			}
			
			if (driverStick.getRawButton(5)){
				
				double liftPower = -0.8;  //this value will need to be created from the PID data
				boxlift.liftUp(liftPower);
								
			}
			
			if (driverStick.getRawButton(6)){
				
				double lowerPower = 0.5;  //this value will need to be created from the PID data
				boxlift.placeDown(lowerPower);
				
			}	
			
			//Sets the driving method
			//Use this one for z rotation
			driveBase.curvatureDrive(driverStick.getRawAxis(1)*speedModifierY, driverStick.getRawAxis(2)*speedModifierX, true);
			//Use this one for x rotation
			//driveBase.arcadeDrive(driverStick.getRawAxis(1)*speedModifierY, driverStick.getRawAxis(0)*speedModifierX, true);
			
			
			System.out.print("encoder Left:  ");
			System.out.println(right_motor_encoder.getRaw());
			System.out.print("encoder Right:  ");
			System.out.println(left_motor_encoder.getRaw());
			System.out.print("encoder Lifter:  ");
			System.out.println(lifter_motor_encoder.getRaw());

			
		}
	}
	public void disabledInit(){
		
		
	}
	public void teleopInit(){
		
		
	}
	public void disabledPeriodic(){
		
		
	}
	public void robotPeriodic(){
		
		
	}
	public void testPeriodic(){
		
	LiveWindow.run();
		
	}
	
	
}
