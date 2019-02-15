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
		//robotGUI = new RobotGUI();
		
		kevin = new Kevin();
		pneumaticDelivery = new PneumaticDelivery();
		cargoDelivery = new CargoDelivery(new RobotIO(), new VictorSP(RobotMap.PWM_PinOut.TOP_HATCH_MOTOR_ID), new VictorSP(RobotMap.PWM_PinOut.BOTTOM_HATCH_MOTOR_ID), new DifferentialDrive(kevin.topKevinMotor, kevin.bottomKevinMotor), 10);
		hatchDelivery = new HatchDelivery(new RobotIO(), new VictorSP(RobotMap.PWM_PinOut.TOP_HATCH_MOTOR_ID), new VictorSP(RobotMap.PWM_PinOut.BOTTOM_HATCH_MOTOR_ID), new DifferentialDrive(kevin.topKevinMotor, kevin.bottomKevinMotor), 10);
		
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
		
/*********************************** DONT CHANGE THIS CODE!!!	*****************************************************
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
		while(isOperatorControl() && isEnabled()) {//&&false){
			selectionIsJoyStick = false;

			//Exponential Speed Controller
			//double speedSlider = driverStick.getRawAxis(3) + 2;
			
			// X-axis for turning , Y-axis for forward/back  
			//Sets speed to half when side button is held, for fine control
			//if(driverStick.getRawButton(1)){
				//speedModifierX = -driverStick.getRawAxis(3);
				//speedModifierY = driverStick.getRawAxis(3);	

				//change = joystick - limitedJoystick;
				//if (change>limit) change = limit;
				//else (if change<-limit) change = -limit;
				//limitedJoystick += change;
				
				//limit is the amount of change you will allow every iteration
				//limitedJoystick is the rate-limited joystick value you use to control your motors.
				
			//}
			

			//Joystick or Xbox
			if(selectionIsJoyStick){
				axisY = driverStick.getRawAxis(1);
				axisX = driverStick.getRawAxis(2);
				//speedModifierX = -driverStick.getRawAxis(3);
				///speedModifierY = driverStick.getRawAxis(3);
				/*if(driverStick.getRawButton(?SHOoT HATCH on?)){
					hatchDelivery // shoot
				}*/

					
					
					if (driverStick.getRawButton(6)){
						
						double lowerPower = 0.5;  //this value will need to be created from the PID data
							//hatchDelivery.retriveHatchFromFloor(lowerPower);
						
					
					
					}
				}else {

				axisX = xBox.getX(GenericHID.Hand.kLeft); //axisX gets value from left thumbstick 
				if((xBox.getTriggerAxis(GenericHID.Hand.kLeft)>0) && !(xBox.getTriggerAxis(GenericHID.Hand.kRight)>0)){ //if lefttrigger is pushed down and not righttrigger the lefttrigger doese its thing
					
					axisY = xBox.getTriggerAxis(GenericHID.Hand.kLeft)*-1; //takes value of the trigger
				
				}else if((xBox.getTriggerAxis(GenericHID.Hand.kRight)>0) && !(xBox.getTriggerAxis(GenericHID.Hand.kLeft)>0)){ //it the righttrigger is pusheed down and not lefttrigger the righttrigger does its thing
					
					axisY = xBox.getTriggerAxis(GenericHID.Hand.kRight); //takes value of the trigger 
				
				} else {
					axisY = 0;							//if both or no buttons pushed it brakes
				}

/*******************JUST TESTING CODE***************************/
				try{
					rigthStickAxisY = xBox.getY(GenericHID.Hand.kRight)*0.75;
				}
				catch(RuntimeException ex ){
					DriverStation.reportError("Error instantiating y axis not reading  " + ex.getMessage(), true);
			
				}
				//System.out.println(rigthStickAxisY);				
				if(rigthStickAxisY > 0.0 || rigthStickAxisY < 0.0){
					kevin.manualKevinControl(rigthStickAxisY);
				}
				
				if(xBox.getYButton()){
					pneumaticDelivery.pushAndPull(0.07);
				}				
				if(xBox.getXButton()){
					//cargoDelivery.succCargo();
					
					//System.out.println(Math.round(RobotIO.getCurrentLiftDistance()*1000));
				}
				if(xBox.getAButton()){
					kevin.liftToPosition(170);
				}
				if(xBox.getBButton()){
					kevin.liftToPosition(110);
				}
/****************************************************************/
				//speedModifierX = ;
				//speedModifierY = ;
			
				/*if (xbox.getBumper(GenericHID.Hand kLeft)){				
					hatchDelivery // shoot					
				}
			if (xbox.getBumper(GenericHID.Hand kRight)){
					hatchDelivery //release	
				//double outputPower = 1;
					//boxGraber.spitOut(outputPower);
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
				}*/
				
				
				//change = joystick - limitedJoystick;
				//if (change>limit) change = limit;
				//else (if change<-limit) change = -limit;
				//limitedJoystick += change;

				//limit is the amount of change you will allow every iteration
				//limitedJoystick is the rate-limited joystick value you use to control your motors.

			}

			
			//Sets the driving method
			driveBase.arcadeDrive(axisY*speedModifierY, axisX*speedModifierX, true);
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
}
