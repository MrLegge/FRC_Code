
package org.usfirst.frc.team6007.robot;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
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
	Joystick operatorStick;
	RobotDrive driveBase;
	RobotCamera camera;
	RoboArm roboArm;
	OtherThread otherThread;
	Thread otherThreadThread;
	
	//Change these depending on pin configuration on RoboRIO
	final int LEFT_MOTOR_ID = 0;
	final int RIGHT_MOTOR_ID = 1;
	
	//CHANGE THIS OPTION FOR AUTONOMOUS
	final int POS_OPTION = 3;
	final int TOWER_APP = -1;
	
	/* 
	 * 
	 * At 0.7 the robot cleared the moat and the rock wall.
	 * 
	 * 
	 */
	
	
	public Robot(){
		//Defines driverStick variable, can be used for extra driverSticks
		driverStick = new Joystick(0);
		operatorStick = new Joystick(1);
		roboArm = new RoboArm();
		camera = new RobotCamera();
		otherThread = new OtherThread(this);
		
		//Defining motors so that they can be inverted
		VictorSP leftMotor = new VictorSP(LEFT_MOTOR_ID);
		VictorSP rightMotor = new VictorSP(RIGHT_MOTOR_ID);
		
		//Use Talon to define another motor controller
		
		driveBase = new RobotDrive(leftMotor, rightMotor);
		driveBase.setInvertedMotor(MotorType.kRearLeft, true);
		driveBase.setInvertedMotor(MotorType.kRearRight, true);
		
		//This stops the robot if no input received SAFETY!!
		driveBase.setExpiration(0.1);
		
		otherThreadThread = new Thread(otherThread);
		otherThreadThread.start();
	}
	
	public void robotInit(){
		
	}
	
	public void autonomous(){
		//Disables the setExpiration to stop robot stopping
		driveBase.setSafetyEnabled(false);
		
		driveBase.setInvertedMotor(MotorType.kRearLeft, false);
		driveBase.setInvertedMotor(MotorType.kRearRight, false);
		
		roboArm.armUpdateSpeed(1.0);
		
		switch(POS_OPTION) {
		case 1:
			//Low bar 
			// from 1.6 ---> 1.5
			driveBase.drive(0.4, 0);
			Timer.delay(1.5);
			
			//from 2.2 ---> 2.0
			driveBase.drive(0.3, 0);
			Timer.delay(2.0);
			
			driveBase.drive(-0.3, 0);
			Timer.delay(0.1);
			
			driveBase.drive(0, 0);
			break;
			
		case 2:
			//Moat
			driveBase.drive(0.9, 0);
			Timer.delay(1.5);
			driveBase.drive(0, 0);
			Timer.delay(0.2);
			driveBase.drive(-0.5, 0);
			Timer.delay(0.3);
			driveBase.drive(0, 0);
			break;
			
		case 3:
			//Rock wall
			driveBase.drive(0.9, 0);
			Timer.delay(1.7);
			driveBase.drive(-0.5, 0);
			Timer.delay(0.3);
			driveBase.drive(0, 0);
			break;
			
		case 4:
			//Rampart
			driveBase.drive(0.7, 0);
			Timer.delay(1.5);
			driveBase.drive(0.5, -0.5);
			Timer.delay(2.0);
			driveBase.drive(-0.5, 0);
			Timer.delay(0.1);
			driveBase.drive(0, 0);
			break;
			
		default:
			driveBase.drive(0.0, 0);
			Timer.delay(1.0);
			driveBase.drive(0, 0);
			break;
			
		}
		
		switch(TOWER_APP){
		
		case 1:
			//This is the throw test for the low bar.
			
			//Drive forward
			driveBase.drive(0.4, 0);
			Timer.delay(1.0);
			
			//Turn right 60
			driveBase.drive(1, 1);
			Timer.delay(0.3);
			
			//Drive forward
			driveBase.drive(0.4, 0);
			Timer.delay(2.4);
			
			//Turn left 90ish
			driveBase.drive(1, -1);
			Timer.delay(0.5);
			
			//Drive forward some more
			driveBase.drive(0.4, 0);
			Timer.delay(1.5);
			
			//Brakes
			driveBase.drive(-0.5, 0);
			Timer.delay(0.1);
			driveBase.drive(0, 0);
			
			//Toss the boulder
			roboArm.armToss(1);
			roboArm.armToss(-1);
			break;
			
		case 2:
			//This is the second left obstacle.
			
			//Drive forward
			driveBase.drive(0.4, 0);
			Timer.delay(1.0);
			
			//Turn right 60
			driveBase.drive(1, 1);
			Timer.delay(0.3);
			
			//Drive forward
			driveBase.drive(0.4, 0);
			Timer.delay(1.8);
			
			//Turn left 90ish
			driveBase.drive(1, -1);
			Timer.delay(0.5);
			
			//Drive forward some more
			driveBase.drive(0.4, 0);
			Timer.delay(1.5);
			
			//Brakes
			driveBase.drive(-0.5, 0);
			Timer.delay(0.1);
			driveBase.drive(0, 0);
			
			//Toss the boulder
			roboArm.armToss(1);
			break;
			
		case 3:
			//This is the middle obstacle.
			
			//Drive forward
			driveBase.drive(0.4, 0);
			Timer.delay(1.5);
						
			//Turn left 30ish
			driveBase.drive(1, -1);
			Timer.delay(0.2);
			
			//Drive forward some more
			driveBase.drive(0.4, 0);
			Timer.delay(2);
			
			//Brakes
			driveBase.drive(-0.5, 0);
			Timer.delay(0.1);
			driveBase.drive(0, 0);
			
			//Toss the boulder
			roboArm.armToss(1);
			break;
			
		case 4:
			roboArm.armToss(1);
			roboArm.armToss(-1);
			break;
			
		case 5:
			//best guess after low bar
			//Turn right 30ish
			driveBase.drive(1, 1);
			Timer.delay(0.3);
			
			//Drive forward some more
			//from 2.6 -----> 2.4
			driveBase.drive(0.4, 0);
			Timer.delay(2.4);
			
			//Turn left 30ish
			driveBase.drive(1, -1);
			Timer.delay(0.425);
			
			//Drive forward some more
			driveBase.drive(0.4, 0);
			Timer.delay(1.0);
			
			//Brakes
			driveBase.drive(-0.5, 0);
			Timer.delay(0.1);
			driveBase.drive(0, 0);
			
			roboArm.armToss(1);
			roboArm.armToss(-1);
			break;
			
		default:
			driveBase.drive(0.0, 0);
			Timer.delay(1.0);
			driveBase.drive(0, 0);
			break;

		
		}
		
		
		roboArm.armUpdateSpeed(0.5);
		
		driveBase.setInvertedMotor(MotorType.kRearLeft, true);
		driveBase.setInvertedMotor(MotorType.kRearRight, true);
		
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
			double speedModifierY = 1.0;
			
			//Sets speed to half when trigger is held, for fine control
			if(driverStick.getRawButton(1)){
				speedModifierX = 0.9;
				speedModifierY = 0.7;
			}
			
			//Sets the driving method
			//Use this one for z rotation
			driveBase.arcadeDrive(driverStick.getRawAxis(1)*speedModifierY, driverStick.getRawAxis(2)*speedModifierX, true);
			
			//Use this one for x rotation
			//driveBase.arcadeDrive(driverStick.getRawAxis(1)*speedModifierY, driverStick.getRawAxis(0)*speedModifierX, true);
			
			roboArm.update(operatorStick);
			
		}
	}
	
	public void test(){
		
	}
	
	public RobotCamera getCamera(){
		return camera;
	}
	
}