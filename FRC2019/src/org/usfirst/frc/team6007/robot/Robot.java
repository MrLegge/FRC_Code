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
import edu.wpi.first.cameraserver.CameraServer;

//New Imports
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;

//import edu.wpi.first.wpilibj.Spark;

public class Robot extends TimedRobot {

	
	public Joystick driverStick;
	public XboxController xBox;
	public DifferentialDrive driveBase;
	public RobotIO robotIO;
	public RobotGUI robotGUI;
	public HatchDelivery hatchDelivery;
	//public HatchIntake hatchIntake;
	public CargoDelivery cargoDelivery;
	//public CargoIntake cargoIntake;
	
	private boolean selectionIsJoyStick= true;
	private double speedModifierX;
	private double speedModifierY;
	private double xboxSpeedModifierX;
	private double xboxSpeedModifierY;
	private Thread m_visionThread;
	
	public Robot(){
		/*Defines driverStick variable, can be used for extra driverSticks*/
		driverStick = new Joystick(0);
		xBox = new XboxController(1);
		robotIO = new RobotIO(); 
		robotGUI = new RobotGUI();

		
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


    m_visionThread = new Thread(() -> {
      // Get the Axis camera from CameraServer
      AxisCamera camera
          = CameraServer.getInstance().addAxisCamera("axis-camera.local");
      // Set the resolution
      camera.setResolution(640, 480);

      // Get a CvSink. This will capture Mats from the camera
      CvSink cvSink = CameraServer.getInstance().getVideo();
      // Setup a CvSource. This will send images back to the Dashboard
      CvSource outputStream
          = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

      // Mats are very memory expensive. Lets reuse this Mat.
      Mat mat = new Mat();

      // This cannot be 'true'. The program will never exit if it is. This
      // lets the robot stop this thread when restarting robot code or
      // deploying.
      while (!Thread.interrupted()) {
        // Tell the CvSink to grab a frame from the camera and put it
        // in the source mat.  If there is an error notify the output.
        if (cvSink.grabFrame(mat) == 0) {
          // Send the output the error.
          outputStream.notifyError(cvSink.getError());
          // skip the rest of the current iteration
          continue;
        }
        // Put a rectangle on the image
        Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
            new Scalar(255, 255, 255), 5);
        // Give the output stream a new image to display
        outputStream.putFrame(mat);
      }
    });
    m_visionThread.setDaemon(true);
    m_visionThread.start();
  
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
			

			//Joystick or Xbox
			if(selectionIsJoyStick){
				axisY = driverStick.getRawAxis(1);
				axisX = driverStick.getRawAxis(2);
				//speedModifierX = -driverStick.getRawAxis(3);
				///speedModifierY = driverStick.getRawAxis(3);
				if(driverStick.getRawButton(?SHOoT HATCH on?)){
					hatchDelivery // shoot
				}
			else {
				axisX = xBox.getX(?leftstick?); //axisX gets value from left thumbstick 
				if(?lefttrigger?&&!?righttrigger?){ //if lefttrigger is pushed down and not righttrigger the lefttrigger doese its thing
					axisY = xBox.getY(?lefttrigger?); //takes value of the trigger
				}else if(?righttrigger?&&!?lefttrigger?){ //it the righttrigger is pusheed down and not lefttrigger the righttrigger does its thing
					axisY = xBox.getY(?righttrigger?); //takes value of the trigger 
				} else {
					axisY = 0;							//if both or no buttons pushed it brakes
				}
				//speedModifierX = ;
				//speedModifierY = ;
			if (xbox.getBumper(GenericHID.Hand kLeft)){				
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
				}
				
				
				//change = joystick - limitedJoystick;
				//if (change>limit) change = limit;
				//else (if change<-limit) change = -limit;
				//limitedJoystick += change;

				//limit is the amount of change you will allow every iteration
				//limitedJoystick is the rate-limited joystick value you use to control your motors.

			}
			if (driverStick.getRawButton(3)){
				
			// stub left as example when setting buttons
				
			}
			
			}
			
			if (driverStick.getRawButton(6)){
				
				double lowerPower = 0.5;  //this value will need to be created from the PID data
					hatchDelivery.retriveHatchFromFloor(lowerPower);
				
			}	
			
			//Sets the driving method
			driveBase.curvatureDrive(axisY*speedModifierY, axisX*speedModifierX, true);
			//Use this one for z rotation
			//driveBase.curvatureDrive(driverStick.getRawAxis(1)*speedModifierY, driverStick.getRawAxis(2)*speedModifierX, true);
			//Use this one for x rotation
			//driveBase.arcadeDrive(driverStick.getRawAxis(1)*speedModifierY, driverStick.getRawAxis(0)*speedModifierX, true);

	}
	
	public void disabledInit(){
	
	}
	
	public void teleopInit(){
	
		
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
