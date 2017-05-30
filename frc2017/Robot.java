
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
	RobotDrive climbBase;
	RobotDrive shootBase;
	Talon agitatorMotor;
	

	//Change these depending on pin configuration on RoboRIO
	final int FRONT_LEFT_MOTOR_ID = 0;
	final int REAR_LEFT_MOTOR_ID = 1;
	final int FRONT_RIGHT_MOTOR_ID = 2;
	final int REAR_RIGHT_MOTOR_ID = 3;
	final int SHOOTING_MOTOR_LEFT_ID = 4;
	final int SHOOTING_MOTOR_RIGHT_ID = 5;
	final int CLIMBING_MOTOR_LEFT_ID = 6;
	final int CLIMBING_MOTOR_RIGHT_ID = 7;
	final int AGITATING_MOTOR_ID = 8;
	
	
	//CHANGE THIS OPTION FOR AUTONOMOUS
	final double LENGTH_OF_WAIT = 3;  //wait time at peg delivering cog
 	final int POSITION_OPTION = 5; //change names as tasks arrive
	final int GEAR_DELIVERY = 4;  //change names as tasks arrive
	final int SHOOT_FOR_GOAL = -1; //shoots for goal
    final int BALL_SHOOT_WAIT = 5; //for how long to wait to unload all the balls
	
	
	
	/* 
	 * 
	 * At 0.7 the robot cleared the moat and the rock wall.
	 * 
	 * 
	 */
	
	
	public Robot(){
		//Defines driverStick variable, can be used for extra driverSticks
		driverStick = new Joystick(0);
		
		//Defining motors so that they can be inverted
		VictorSP frontLeftMotor = new VictorSP(FRONT_LEFT_MOTOR_ID);
		VictorSP rearLeftMotor = new VictorSP(REAR_LEFT_MOTOR_ID);
		VictorSP frontRightMotor = new VictorSP(FRONT_RIGHT_MOTOR_ID);
		VictorSP rearRightMotor = new VictorSP(REAR_RIGHT_MOTOR_ID);
		Spark leftShootingMotor = new Spark(SHOOTING_MOTOR_LEFT_ID);
		Spark rightShootingMotor = new Spark(SHOOTING_MOTOR_RIGHT_ID);
		VictorSP leftClimbingMotor = new VictorSP(CLIMBING_MOTOR_LEFT_ID);
		VictorSP rightClimbingMotor = new VictorSP(CLIMBING_MOTOR_RIGHT_ID);
		agitatorMotor = new Talon(AGITATING_MOTOR_ID);
		
		//Use Talon to define another motor controller
		
		driveBase = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		shootBase = new RobotDrive(leftShootingMotor, rightShootingMotor);
		climbBase = new RobotDrive(leftClimbingMotor, rightClimbingMotor);
		
		//This stops the robot if no input received SAFETY!!
		driveBase.setExpiration(0.1);
		
		
	}
	
	public void robotInit(){
		
		driveBase.setInvertedMotor(MotorType.kFrontRight, true);
		driveBase.setInvertedMotor(MotorType.kRearRight, true);
		driveBase.setInvertedMotor(MotorType.kFrontLeft, true);
		driveBase.setInvertedMotor(MotorType.kRearLeft, true);

		
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
		climbBase.setSafetyEnabled(false);
		shootBase.setSafetyEnabled(false);
		
		driveBase.setInvertedMotor(MotorType.kFrontRight, true);
		driveBase.setInvertedMotor(MotorType.kRearRight, true);
		driveBase.setInvertedMotor(MotorType.kFrontLeft, true);
		driveBase.setInvertedMotor(MotorType.kRearLeft, true);
		
		
		
		// set LENGTH_OF_WAIT to fix time at airship
		switch(POSITION_OPTION) {
		case 1:
		//S.O.L    start left drive to line    
			driveBase.drive(0.4, 0); 
			Timer.delay(1.5);
			driveBase.drive(0, 0);
			Timer.delay(0.5);
			driveBase.drive(-0.5, 0);
			Timer.delay(0.1);
			
			break;
			
		case 2:
			//S.O.L    middle avoid left       
			driveBase.drive(0.9, 0);
			Timer.delay(0.5);
			driveBase.drive(0, 0);
			Timer.delay(0.2);
			driveBase.drive(0.9, -1);
			Timer.delay(0.3);
			driveBase.drive(0.9, 0);
			Timer.delay(0.5);
			driveBase.drive(0.9, 1);
			Timer.delay(0.3);
			driveBase.drive(0, 0);
			Timer.delay(0.2);
			
			break;
			
		case 3:
			//S.O.L   middle avoid right    
			driveBase.drive(0.9, 0);
			Timer.delay(0.5);
			driveBase.drive(0, 0);
			Timer.delay(0.2);
			driveBase.drive(0.9, 1);
			Timer.delay(0.3);
			driveBase.drive(0.9, 0);
			Timer.delay(0.5);
			driveBase.drive(0.9, -1);
			Timer.delay(0.3);
			driveBase.drive(0.9, 0);
			Timer.delay(0.5);
			driveBase.drive(0, 0);
			Timer.delay(0.2);
			
			break;
		
		case 4:
		//S.O.L  start right drive to line
			driveBase.drive(0.4, 0);
			Timer.delay(1.5);
			driveBase.drive(0, 0);
			Timer.delay(0.5);
			driveBase.drive(-0.5, 0);
			Timer.delay(0.1);
		
		break;
		
		case 5:
		
			break;
		
		default:
			driveBase.drive(0.0, 0);
			Timer.delay(1.0);
			driveBase.drive(0, 0);
			
			break;
			
		}
		
		switch(GEAR_DELIVERY){
		case 1:
			
			//straight forward wait back up a little 
			driveBase.drive(0.4, 0);
			Timer.delay(1.1);
			driveBase.drive(-0.5, 0);
			Timer.delay(0.1);
			driveBase.drive(0, 0); 
			Timer.delay(LENGTH_OF_WAIT);
			driveBase.drive(-0.4, 0);
			Timer.delay(1.2);
			driveBase.drive(0.5, 0);
			Timer.delay(0.1);
            
			break;
			
		case 2:
			
			//This is the gear  delivery centre exit right 65 points
			//This takes 7.5 seconds to finish			  
			driveBase.drive(0.4, 0);
			Timer.delay(1.2);
			driveBase.drive(-0.5, 0);
			Timer.delay(0.1);
			driveBase.drive(0.0, 0);
		    Timer.delay(LENGTH_OF_WAIT);
			driveBase.drive(-0.4, 0);
			Timer.delay(1.0);
			driveBase.drive(0.4, 0);
			Timer.delay(0.1);
			driveBase.drive(0.9, -1);
			Timer.delay(0.5);
			driveBase.drive(0.5, 0);
			Timer.delay(1.3);
			driveBase.drive(0.9, 1);
			Timer.delay(0.3);
			driveBase.drive(0.9, 0);
			Timer.delay(0.5);
			driveBase.drive(0.0, 0);
			Timer.delay(0.2);
			driveBase.drive(-0.5, 0);
			Timer.delay(0.1);
			
			break;
			
		case 3:
		
			//gear delivery centre exit left   check on day
		
			
			driveBase.drive(0.4, 0);
			Timer.delay(1.2);
			driveBase.drive(-0.5, 0);
			Timer.delay(0.1);
			driveBase.drive(0.0, 0);
		    Timer.delay(LENGTH_OF_WAIT);
			driveBase.drive(-0.4, 0);
			Timer.delay(1.0);
			driveBase.drive(0.4, 0);
			Timer.delay(0.1);
			driveBase.drive(0.9, 1);
			Timer.delay(0.5);
			driveBase.drive(0.5, 0);
			Timer.delay(1.3);
			driveBase.drive(0.9, -1);
			Timer.delay(0.3);
			driveBase.drive(0.9, 0);
			Timer.delay(0.5);
			driveBase.drive(0.0, 0);
			Timer.delay(0.2);
			driveBase.drive(-0.5, 0);
			Timer.delay(0.1);
			
			break;
			
		case 4:
		
			//gear delivery for left cross line  65 points   
			driveBase.drive(0.5, 0);
	        Timer.delay(1.1);
	        driveBase.drive(-0.5, 0);
	        Timer.delay(0.1);
	        driveBase.drive(0.9, -1);
	        Timer.delay(0.45);
	        driveBase.drive(0.5, 0);
	        Timer.delay(0.8);
	        driveBase.drive(0.0, 0);
	        Timer.delay(0.4);
	        driveBase.drive(-0.25, 0);
	        Timer.delay(0.1);
	         driveBase.drive(0.0, 0);
	        Timer.delay(LENGTH_OF_WAIT);
           driveBase.drive(-0.5, 0);
	        Timer.delay(1.5);
	         driveBase.drive(1, 1);
	        Timer.delay(0.4);
	        driveBase.drive(0.0, 0.0);
	        Timer.delay(0.3);
	        driveBase.drive(1.0, 0);
	        Timer.delay(5.0);
			
			break;
			
		case 5:
		  
			//gear delivery for right line go to gear delivery  65 points     
			driveBase.drive(0.5, 0);
	        Timer.delay(1.0);
	        driveBase.drive(-0.5, 0);
	        Timer.delay(0.1);
	        driveBase.drive(0.9, 1);
	        Timer.delay(0.4);
	        driveBase.drive(0.5, 0);
	        Timer.delay(0.7);
	        driveBase.drive(0.0, 0);
	        Timer.delay(0.4);
	        driveBase.drive(-0.25, 0);
	        Timer.delay(0.1);
	         driveBase.drive(0.0, 0);
	        Timer.delay(LENGTH_OF_WAIT);
           driveBase.drive(-0.5, 0);
	        Timer.delay(1.5);
	         driveBase.drive(1, -1);
	        Timer.delay(0.3);
	        driveBase.drive(0.0, 0.0);
	        Timer.delay(0.3);
	        driveBase.drive(1.0, 0);
	        Timer.delay(2.5);
	        driveBase.drive(0.0, 0);
	        Timer.delay(0.4);
	        driveBase.drive(-0.3, 0);
	        Timer.delay(0.4);
	      
			
			break;
			
		case 6:
		  
			//gear delivery for right line go to gear delivery  65 points
			driveBase.drive(0.5, 0);
	        Timer.delay(1.0);
	        driveBase.drive(-0.5, 0);
	        Timer.delay(0.1);
	        driveBase.drive(0.9, 1);
	        Timer.delay(0.4);
	        driveBase.drive(0.5, 0);
	        Timer.delay(0.7);
	        driveBase.drive(0.0, 0);
	        Timer.delay(0.4);
	        driveBase.drive(-0.25, 0);
	        Timer.delay(0.1);
	         driveBase.drive(0.0, 0);
	        Timer.delay(LENGTH_OF_WAIT);
           driveBase.drive(-0.5, 0);
	        Timer.delay(1.5);
	         driveBase.drive(1, -1);
	        Timer.delay(0.3);
	        driveBase.drive(0.0, 0.0);
	        Timer.delay(0.3);
	        driveBase.drive(1.0, 0);
	        Timer.delay(2.5);
	        driveBase.drive(0.0, 0);
	        Timer.delay(0.4);
	        driveBase.drive(-0.3, 0);
	        Timer.delay(0.4);
			
			break;
			
			
		    case 7:
		    //G.D    for right side stand on line 65 points
				driveBase.drive(0.5, 0);
		        Timer.delay(1.0);
		        driveBase.drive(-0.5, 0);
		        Timer.delay(0.1);
		        driveBase.drive(0.9, -1);
		        Timer.delay(0.4);
		        driveBase.drive(0.5, 0);
		        Timer.delay(0.7);
		        driveBase.drive(0.0, 0);
		        Timer.delay(0.4);
		        driveBase.drive(-0.25, 0);
		        Timer.delay(0.1);
		         driveBase.drive(0.0, 0);
		        Timer.delay(LENGTH_OF_WAIT);
	           driveBase.drive(-0.5, 0);
		        Timer.delay(1.5);
		         driveBase.drive(1, 1);
		        Timer.delay(0.3);
		        driveBase.drive(0.0, 0.0);
		        Timer.delay(0.3);
		        driveBase.drive(1.0, 0);
		        Timer.delay(2.5);
		        driveBase.drive(0.0, 0);
		        Timer.delay(0.4);
		        driveBase.drive(-0.3, 0);
		        Timer.delay(0.4);
				
			break;
			
		default:
			driveBase.drive(0.0, 0);
			Timer.delay(1.0);
			driveBase.drive(0, 0);
			break;

		
		}
		
	switch(SHOOT_FOR_GOAL){
	
     case 1:
	 //S.4.G    pos left, deliver gear, shoot left, high goal  
			driveBase.drive(0.5, 0);
	        Timer.delay(1.0);
	        driveBase.drive(-0.5, 0);
	        Timer.delay(0.1);
	        driveBase.drive(0.9, -1);
	        Timer.delay(0.4);
	        driveBase.drive(0.5, 0);
	        Timer.delay(0.7);
	        driveBase.drive(0.0, 0);
	        Timer.delay(0.4);
	        driveBase.drive(-0.25, 0);
	        Timer.delay(0.1);
	         driveBase.drive(0.0, 0);
	        Timer.delay(LENGTH_OF_WAIT);
           driveBase.drive(-0.5, 0);
	        Timer.delay(1.5);
	       shootBase.drive(-1.0, 0);
		   Timer.delay(BALL_SHOOT_WAIT);

		  
	   break;
	   
	 case 2:
     //S.4.G    pos right, deliver gear, shoot right, high goal
			driveBase.drive(0.5, 0);
	        Timer.delay(1.0);
	        driveBase.drive(-0.5, 0);
	        Timer.delay(0.1);
	        driveBase.drive(0.9, 1);
	        Timer.delay(0.4);
	        driveBase.drive(0.5, 0);
	        Timer.delay(0.7);
	        driveBase.drive(0.0, 0);
	        Timer.delay(0.4);
	        driveBase.drive(-0.25, 0);
	        Timer.delay(0.1);
	         driveBase.drive(0.0, 0);
	        Timer.delay(LENGTH_OF_WAIT);
           driveBase.drive(-0.5, 0);
	        Timer.delay(1.5);
	       shootBase.drive(-1.0, 0);
		   Timer.delay(BALL_SHOOT_WAIT);

	   
	   
	   break;

	 case 3:  
     //S.4.G    pos middle, deliver gear, shoot right, high goal
	   driveBase.drive(0.5, 0);
	   Timer.delay(0.6);
	   driveBase.drive(0.0, 0);
	   Timer.delay(0.3);
	   driveBase.drive(0.0, 0);
	   Timer.delay(LENGTH_OF_WAIT);
	   driveBase.drive(-0.5, 0);
	   Timer.delay(0.2);
	   driveBase.drive(0.6, 0.6);
	   Timer.delay(0.4);
	   driveBase.drive(0.8, 0);
	   Timer.delay(0.8);
	   driveBase.drive(0.6, 0.6);
	   Timer.delay(0.1);
	   shootBase.drive(-1, 0);
	   Timer.delay(BALL_SHOOT_WAIT);
	   driveBase.drive(-0.5, 0);
	   Timer.delay(0.3);
	   driveBase.drive(0.6, 0.6);
	   Timer.delay(0.35);
	   driveBase.drive(0.7, 0);
	   Timer.delay(0.7);
	   driveBase.drive(0.6, -0.6);
	   Timer.delay(0.3);
	   driveBase.drive(0.8, 0);
	   Timer.delay(5);	   
	   
	   
       break;

	 case 4:  
     //S.4.G    pos middle, deliver gear, shoot left, high goal
	   driveBase.drive(0.5, 0);
	   Timer.delay(0.6);
	   driveBase.drive(0.0, 0);
	   Timer.delay(0.3);
	   driveBase.drive(0.0, 0);
	   Timer.delay(LENGTH_OF_WAIT);
	   driveBase.drive(-0.5, 0);
	   Timer.delay(0.2);
	   driveBase.drive(0.6, -0.6);
	   Timer.delay(0.4);
	   driveBase.drive(0.8, 0);
	   Timer.delay(0.8);
	   driveBase.drive(0.6, -0.6);
	   Timer.delay(0.1);
	   shootBase.drive(-1, 0);
	   Timer.delay(BALL_SHOOT_WAIT);
	   driveBase.drive(-0.5, 0);
	   Timer.delay(0.3);
	   driveBase.drive(0.6, -0.6);
	   Timer.delay(0.35);
	   driveBase.drive(0.7, 0);
	   Timer.delay(0.7);
	   driveBase.drive(0.6, 0.6);
	   Timer.delay(0.3);
	   driveBase.drive(0.8, 0);
	   Timer.delay(5);	
	
	
       break; 


	   
		default:
			driveBase.drive(0.0, 0);
			Timer.delay(1.0);
			driveBase.drive(0, 0);
			break;
			
	}
		
		
		driveBase.setInvertedMotor(MotorType.kFrontRight, false);
		driveBase.setInvertedMotor(MotorType.kRearRight, false);
		driveBase.setInvertedMotor(MotorType.kFrontLeft, false);
		driveBase.setInvertedMotor(MotorType.kRearLeft, false);
		
		
		driveBase.setSafetyEnabled(true);
		climbBase.setSafetyEnabled(true);
		shootBase.setSafetyEnabled(true);
	}
	
	public void operatorControl(){
		driveBase.setSafetyEnabled(true);
		climbBase.setSafetyEnabled(true);
		shootBase.setSafetyEnabled(true);

		
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
			//hold trigger spins ball shooter  100% power
			if(driverStick.getRawButton(2)){
				shootBase.drive(-1, 0);
				agitatorMotor.set(1);
			} else {agitatorMotor.set(0);}

			
			//Hold button 3 to climb up 
			if(driverStick.getRawButton(3)){
				climbBase.drive(-1, 0);///minus when used for climb pos when ball toss
			}
		    
		    //Hold button 4 to climb down
		    if(driverStick.getRawButton(4)){
		    	climbBase.drive(1.0, 0);
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