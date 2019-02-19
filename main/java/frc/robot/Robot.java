/***********************************************************
* this is just the copy of 2018 
* Date: 1-11-2018
* Changed for 2019
************************************************************/
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

//camera stuff
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

//New Imports
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import java.lang.Math;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID;

//import edu.wpi.first.wpilibj.Spark;

public class Robot extends TimedRobot {

	
	public Joystick driverStick;
	public XboxController xBox;
	public DifferentialDrive driveBase;
	public RobotIO robotIO;
	public RobotGUI robotGUI;
	public HatchDelivery hatchDelivery;
	public static CargoDelivery cargoDelivery;
	public static Kevin kevin;
	public static PneumaticDelivery pneumaticDelivery;
	public static Boolean holdPosition = false;

	private boolean selectionIsJoyStick = true;
	private double speedModifierX;
	private double speedModifierY;
	private Thread m_visionThread;
	private double axisX;
	private double axisY;
	private double rigthStickAxisY;
	
	public Robot(){
		/*Defines driverStick variable, can be used for extra driverSticks*/
		driverStick = new Joystick(0);
		xBox = new XboxController(0); 	
		kevin = new Kevin();
		pneumaticDelivery = new PneumaticDelivery();

		cargoDelivery = new CargoDelivery(kevin.robotIO, kevin.topKevinMotor, kevin.bottomKevinMotor, kevin.kevinMotors, 10, kevin.kevinController);
		hatchDelivery = new HatchDelivery(kevin.robotIO, kevin.topKevinMotor, kevin.bottomKevinMotor, kevin.kevinMotors, 10, kevin.kevinController);
		
		rigthStickAxisY = 0;
		speedModifierX = 1.0;
		speedModifierY = -1.0;
		
	
		/*COMMENT OUT IF SPARK MOTOR CONTROLLER IS USED*/
		//Spark motor_frontLeft = new Spark(RobotMap.PWM_PinOut.FRONT_LEFT_MOTOR_ID);
		//Spark motor_rearLeft = new Spark(RobotMap.PWM_PinOut.REAR_LEFT_MOTOR_ID);
		//Spark motor_frontRight = new Spark(RobotMap.PWM_PinOut.FRONT_RIGHT_MOTOR_ID);
		//Spark motor_rearRight = new Spark(RobotMap.PWM_PinOut.REAR_RIGHT_MOTOR_ID);

		/*COMMENT OUT IF VICTORSP MOTOR CONTROLLER IS USED*/
		VictorSP motor_frontLeft = new VictorSP(RobotMap.PWM_PinOut.FRONT_LEFT_MOTOR_ID);
		VictorSP motor_rearLeft = new VictorSP(RobotMap.PWM_PinOut.REAR_LEFT_MOTOR_ID);
		VictorSP motor_frontRight = new VictorSP(RobotMap.PWM_PinOut.FRONT_RIGHT_MOTOR_ID);
		VictorSP motor_rearRight = new VictorSP(RobotMap.PWM_PinOut.REAR_RIGHT_MOTOR_ID);

		SpeedControllerGroup motors_left = new SpeedControllerGroup(motor_frontLeft, motor_rearLeft);
		SpeedControllerGroup motors_right = new SpeedControllerGroup(motor_frontRight, motor_rearRight);

		driveBase = new DifferentialDrive(motors_left, motors_right);
		
		//This stops the robot if no input received SAFETY!!
		driveBase.setExpiration(0.1);

	}
	@Override
	public void robotInit(){
		
/*********************************** DONT CHANGE THIS CODE!!!	*****************************************************/
		new Thread(() -> {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(320, 240);
			camera.setFPS(24);
			 
			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 320, 240);
			
			Mat source = new Mat();
			Mat output = new Mat();
			
			while(!Thread.interrupted()) {
				cvSink.grabFrame(source);
				Imgproc.cvtColor(source, output, 8);
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
		while(isOperatorControl() && isEnabled()) {

			/*************DRIVING AND STEERING CODE*************/
			axisX = xBox.getX(GenericHID.Hand.kLeft); //axisX gets value from left thumbstick 
			if((xBox.getTriggerAxis(GenericHID.Hand.kLeft)>0) && !(xBox.getTriggerAxis(GenericHID.Hand.kRight)>0)){ //if lefttrigger is pushed down and not righttrigger the lefttrigger doese its thing
				
				axisY = xBox.getTriggerAxis(GenericHID.Hand.kLeft); //takes value of the trigger
			
			}else if((xBox.getTriggerAxis(GenericHID.Hand.kRight)>0) && !(xBox.getTriggerAxis(GenericHID.Hand.kLeft)>0)){ //it the righttrigger is pusheed down and not lefttrigger the righttrigger does its thing
					
				axisY = xBox.getTriggerAxis(GenericHID.Hand.kRight)*-1; //takes value of the trigger 
				
			} else {
				axisY = 0;	//if both or no buttons pushed it brakes
			}

			/*********gets the value from the right stick*********/	
			try{
				rigthStickAxisY = xBox.getY(GenericHID.Hand.kRight) * -1.0;
			}
			catch(RuntimeException ex ){
				DriverStation.reportError("Error instantiating y axis not reading  " + ex.getMessage(), true);
			
			}		

			/*********if the arm is not being used the power is set to 0**********/


			/*********manual control for the arm**********/


			/*********controls everything to do with the arm**********/	
			if(xBox.getAButton()){
				kevin.liftToPosition(180);
			}else if(xBox.getBButton()){
				kevin.liftToPosition(170);
			}else if(rigthStickAxisY > 0.1 || rigthStickAxisY < -0.1){
				kevin.manualKevinControl(rigthStickAxisY*0.75);
			}else{//((rigthStickAxisY < 0.05 && rigthStickAxisY > -0.05) && !(xBox.getAButton()))
				kevin.placeArmDown(300);
				System.out.println("ïs else");
			}

			/*********retrives the disk from the floor***********/


			/*********makes the cargo motor succ in and spit out cargo**********/			
			if(xBox.getXButton()){
				cargoDelivery.succCargo();
			}else if(xBox.getYButton()){
				cargoDelivery.shootCargo();
			}else{
				cargoDelivery.cargoDeliveryMotor.set(0.0);
			}

			/********* **********/	


			/*********fires the pneumatics**********/	
			if(xBox.getBumper(GenericHID.Hand.kRight)){
				pneumaticDelivery.pushAndPull(0.1);
			}	

			/*********hopefully makes the arm hold current position**********/	
			if(xBox.getBumperReleased(GenericHID.Hand.kLeft)){
				System.out.println(Math.round(robotIO.getCurrentLiftDistance() * 2000));
				//holdPosition = !holdPosition;
			}

			driveBase.arcadeDrive(axisY*speedModifierY, axisX*speedModifierX, true);
		}
	
			//change = joystick - limitedJoystick;
			//if (change>limit) change = limit;
			//else (if change<-limit) change = -limit;
			//limitedJoystick += change;
			//limit is the amount of change you will allow every iteration
			//limitedJoystick is the rate-limited joystick value you use to control your motors.
			//Sets the driving method
			
			
			//Use this one for z rotation
			//driveBase.curvatureDrive(driverStick.getRawAxis(1)*speedModifierY, driverStick.getRawAxis(2)*speedModifierX, true);
			//Use this one for x rotation
			//driveBase.arcadeDrive(driverStick.getRawAxis(1)*speedModifierY, driverStick.getRawAxis(0)*speedModifierX, true);
	}
		
	
	/*
	public void disabledInit(){
	
	}
	
	public void teleopInit(){
	
		
	}
	
	public void disabledPeriodic(){
		
		
	}
	public void robotPeriodic(){
		
		
	}*/
	//@SuppressWarnings("deprecation")
	//public void testPeriodic(){
		
	//LiveWindow.run();
		
	//}
	
}

