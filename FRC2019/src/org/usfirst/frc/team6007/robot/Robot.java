/***********************************************************
* this is just the copy of 2018 
* Date: 1-11-2018
************************************************************/
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
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;

//import edu.wpi.first.wpilibj.VictorSP;


public class Robot extends TimedRobot {
	
	public Joystick driverStick;
	public Joystick xBox;
	public DifferentialDrive driveBase;
	private String gameData;
	private int startPos;
	public BoxGrabber boxGraber;
	public BoxLifter boxlifter;
	public RobotIO robotIO;
	public HatchDelivery hatchDelivery;
	public HatchIntake hatchIntake;
	public CargoDelivery cargoDelivery;
	public CargoIntake cargoIntake;
	boolean flag = true;
	boolean selectionIsJoyStick= true;
	private double speedModifierX;
	private double speedModifierY;
	private double xboxSpeedModifierX;
	private double xboxSpeedModifierY;

	
	
	
	public Robot(){
		/*Defines driverStick variable, can be used for extra driverSticks*/
		driverStick = new Joystick(0);
		xBox = new XboxController(1);
		boxGraber = new BoxGrabber();
		boxlifter = new BoxLifter();
		robotIO = new RobotIO(); 
		
		//ADD OPTIONS FOR AUTONOMOUS 
		startPos = 3;
		
		speedModifierX = 1.0;
		speedModifierY = -1.0;
		xboxSpeedModifierX = 0.1;
		xboxSpeedModifierY = 0.1;		
	
		/*COMMENT OUT IF SPARK MOTOR CONTROLLER IS USED*/
		Spark motor_frontLeft = new Spark(RobotMap.PWM_PinOut.FRONT_LEFT_MOTOR_ID);
		Spark motor_rearLeft = new Spark(RobotMap.PWM_PinOut.REAR_LEFT_MOTOR_ID);
		Spark motor_frontRight = new Spark(RobotMap.PWM_PinOut.FRONT_RIGHT_MOTOR_ID);
		Spark motor_rearRight = new Spark(RobotMap.PWM_PinOut.REAR_RIGHT_MOTOR_ID);

		/*COMMENT OUT IF VICTORSP MOTOR CONTROLLER IS USED*/
		//VictorSP motor_frontLeft = new VictorSP(RobotMap.PWM_PinOut.FRONT_LEFT_MOTOR_ID);
		//VictorSP motor_rearLeft = new VictorSP(RobotMap.PWM_PinOut.REAR_LEFT_MOTOR_ID);
		//VictorSP motor_frontRight = new VictorSP(RobotMap.PWM_PinOut.FRONT_RIGHT_MOTOR_ID);
		//VictorSP motor_rearRight = new VictorSP(RobotMap.PWM_PinOut.REAR_RIGHT_MOTOR_ID);

		SpeedControllerGroup motors_left = new SpeedControllerGroup(motor_frontLeft, motor_rearLeft);
		SpeedControllerGroup motors_right = new SpeedControllerGroup(motor_frontRight, motor_rearRight);

		driveBase = new DifferentialDrive(motors_left, motors_right);
		
		//This stops the robot if no input received SAFETY!!
		driveBase.setExpiration(0.1);

	}
	
	public void robotInit(){
		
		/****seting encoder parameters*********************check against standards b4 use**/
	/*	right_motor_encoder.setMaxPeriod(.1);
		right_motor_encoder.setMinRate(10);
		right_motor_encoder.setDistancePerPulse(5);
		right_motor_encoder.setSamplesToAverage(7);
		
		left_motor_encoder.setMaxPeriod(.1);
		left_motor_encoder.setMinRate(10);
		left_motor_encoder.setDistancePerPulse(5);
		left_motor_encoder.setSamplesToAverage(7);
		
		lifter_motor_encoder.setMaxPeriod(.1);
		lifter_motor_encoder.setMinRate(10);
		lifter_motor_encoder.setDistancePerPulse(5);
		lifter_motor_encoder.setSamplesToAverage(7);*/
		
		/****remove deadband from the speed controllers on driveBase*****Check effects b4 blind use*****/
	//	motor_frontLeft.enableDeadbandElimination(true);
		//motor_rearLeft.enableDeadbandElimination(true);
		//motor_frontRight.enableDeadbandElimination(true);
		//motor_rearRight.enableDeadbandElimination(true);
		
		
	/*********************************** DONT CHANGE THIS CODE!!!	*****************************************************
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
		
	}
	
	public void teleopPeriodic(){   //teleopPeriodic   operatorControl
		driveBase.setSafetyEnabled(true);

			  
		
		
		//Ensures robot only drives when under operator control 
		while(isOperatorControl() && isEnabled()) {//&&false){
			
			//Exponential Speed Controller
			//double speedSlider = driverStick.getRawAxis(3) + 2;
			
			// X-axis for turning , Y-axis for forward/back  
			
			

			//Sets speed to half when side button is held, for fine control
			if(driverStick.getRawButton(1)){
				speedModifierX = -driverStick.getRawAxis(3);
				speedModifierY = driverStick.getRawAxis(3);	

				//change = joystick - limitedJoystick;
				//if (change>limit) change = limit;
				//else (if change<-limit) change = -limit;
				//limitedJoystick += change;
				
				//limit is the amount of change you will allow every iteration
				//limitedJoystick is the rate-limited joystick value you use to control your motors.
				
			}
			
			
			
			

			if (xbox.getBumper(GenericHID.Hand kLeft)){
				
				hatchDelivery // grab
				
					//double intakePower = -0.7;
				//boxGraber.suckIn(intakePower);
			}
			
			if (xbox.getBumper(GenericHID.Hand kRight)){
				hatchDelivery //release
				
				//double outputPower = 1;
				//boxGraber.spitOut(outputPower);
			}
			
			if (xbox.getTriggerAxis(GenericHID.Hand kRight)&&xboxSpeedModifierX >= 1.0){
			xboxSpeedModifierX = xboxSpeedModifierX + 0.1;
			//accelorate
			}
			
			if (xbox.getTriggerAxis(GenericHID.Hand kLeft)&&xboxSpeedModifierX <= -1.0){
			xboxSpeedModifierX = xboxSpeedModifierX - 0.1;
			//brake
			}
			
			if (xbox.getJoystick( kLeft.getY()>0)){
			//turning
			driveBase.curvatureDrive(xboxSpeedModifierX, -1.0,true);
			}
			if (xbox.getJoystick( kLeft.getY()<0)){
			//turning
			driveBase.curvatureDrive(xboxSpeedModifierX, 1.0,true);
			}
			if (xbox.getJoystick(GenericHID.Hand kRight)){
			//arm movement
			}
			
			
			
			
				
			if (driverStick.getRawButton(3)){
				
			// stub left as example when setting buttons
				
			}
			
			
								
			}
			
			if (driverStick.getRawButton(6)){
				
				double lowerPower = 0.5;  //this value will need to be created from the PID data
				boxlifter.placeDown(lowerPower);
				
			}	
			
			//Sets the driving method
			//Use this one for z rotation
			driveBase.curvatureDrive(driverStick.getRawAxis(1)*speedModifierY, driverStick.getRawAxis(2)*speedModifierX, true);
			//Use this one for x rotation
			//driveBase.arcadeDrive(driverStick.getRawAxis(1)*speedModifierY, driverStick.getRawAxis(0)*speedModifierX, true);
			
			
			
		
		System.out.print("encoder Left:  "+RobotIO.getRight_motor_encoder().getDistance());
		System.out.println("encoder Right:  "+RobotIO.getLeft_motor_encoder().getDistance());
		System.out.println("encoder Lifter:  "+ RobotIO.getLifter_motor_encoder().getDistance());
	}
	public void disabledInit(){
		
		
	}
	public void teleopInit(){
		RobotIO.getLifter_motor_encoder().reset();
		RobotIO.getLeft_motor_encoder().reset();
		RobotIO.getRight_motor_encoder().reset();
		
	}
	public void disabledPeriodic(){
		
		
	}
	public void robotPeriodic(){
		
		
	}
	@SuppressWarnings("deprecation")
	public void testPeriodic(){
		
	LiveWindow.run();
		
	}
	
	
}
