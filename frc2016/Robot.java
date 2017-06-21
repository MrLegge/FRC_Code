
package org.usfirst.frc.team6007.robot;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
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
	//RobotCamera camera;
	//OtherThread otherThread;
	//Thread otherThreadThread;
	
	//Change these depending on pin configuration on RoboRIO
	final int FRONT_LEFT_MOTOR_ID = 0;
	final int REAR_LEFT_MOTOR_ID = 1;
	final int FRONT_RIGHT_MOTOR_ID = 2;
	final int REAR_RIGHT_MOTOR_ID = 3;
	
	//CHANGE THIS OPTION FOR AUTONOMOUS
	final int POS_OPTION = 3; //change names as tasks arrive
	final int TOWER_APP = -1;  //change names as tasks arrive
	
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
		//camera = new RobotCamera();
		//otherThread = new OtherThread(this);
		
		//Defining motors so that they can be inverted
		VictorSP frontLeftMotor = new VictorSP(FRONT_LEFT_MOTOR_ID);
		VictorSP rearLeftMotor = new VictorSP(REAR_LEFT_MOTOR_ID);
		VictorSP frontRightMotor = new VictorSP(FRONT_RIGHT_MOTOR_ID);
		VictorSP rearRightMotor = new VictorSP(REAR_RIGHT_MOTOR_ID);
		
		//Use Talon to define another motor controller
		
		driveBase = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		driveBase.setInvertedMotor(MotorType.kRearLeft, true); // might be unneeded
		driveBase.setInvertedMotor(MotorType.kRearRight, true); // might be unneeded
		
		//This stops the robot if no input received SAFETY!!
		driveBase.setExpiration(0.1);
		
		//otherThreadThread = new Thread(otherThread);
		//otherThreadThread.start();
	}
	
	public void robotInit(){
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
		
	}
	
	public void autonomous(){
		//Disables the setExpiration to stop robot stopping
		driveBase.setSafetyEnabled(false);
		
		driveBase.setInvertedMotor(MotorType.kRearLeft, false);
		driveBase.setInvertedMotor(MotorType.kRearRight, false);
		
		
		
		switch(POS_OPTION) {
		case 1:
			//some stuff
			break;
			
		case 2:
			//example of what we do
			/*driveBase.drive(0.9, 0);
			Timer.delay(1.5);
			driveBase.drive(0, 0);
			Timer.delay(0.2);
			driveBase.drive(-0.5, 0);
			Timer.delay(0.3);
			driveBase.drive(0, 0);*/
			break;
			
		case 3:
			//some stuff
			break;
			
		case 4:
			//some stuff
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
		//	driveBase.drive(0.4, 0);
			//Timer.delay(1.0);
			
			//Turn right 60
		//	driveBase.drive(1, 1);
		//	Timer.delay(0.3);
			
			//Drive forward
			//driveBase.drive(0.4, 0);
		//	Timer.delay(2.4);
			
			//Turn left 90ish
		//	driveBase.drive(1, -1);
		//	Timer.delay(0.5);
			
			//Drive forward some more
		//	driveBase.drive(0.4, 0);
		//	Timer.delay(1.5);
			
			//Brakes
		//	driveBase.drive(-0.5, 0);
		//	Timer.delay(0.1);
		//driveBase.drive(0, 0);
			
	
			break;
			
		case 2:
			//some stuff
			break;
			
		case 3:
			//some stuff
			break;
			
		case 4:
			//some stuff
			break;
			
		case 5:
			//some stuff
			break;
			
		default:
			driveBase.drive(0.0, 0);
			Timer.delay(1.0);
			driveBase.drive(0, 0);
			break;

		
		}
		
		
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
			

			
		}
	}
	
	public void test(){
		
	}
	
	/*public RobotCamera getCamera(){
		return camera;
	}*/
	
}