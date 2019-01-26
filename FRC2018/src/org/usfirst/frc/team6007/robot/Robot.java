package org.usfirst.frc.team6007.robot;

import edu.wpi.first.wpilibj.TimedRobot;
/*
//camera stuff
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc; 
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
*/
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^



public class Robot extends TimedRobot {
	
	//public DifferentialDrive driveBase;
	private String gameData;
	private int startPos;
	public static BoxGrabber boxGrabber;
	public static BoxLifter boxLifter;
	public static RobotIO robotIO;
	public static RobotDrive robotDrive;
	boolean flag = true;
	
	
	
	public Robot(){
		/*Defines driverStick variable, can be used for extra driverSticks*/
		boxGrabber = new BoxGrabber();
		boxLifter = new BoxLifter();
		robotIO = new RobotIO(); 
		robotDrive = new RobotDrive();
		//ADD OPTIONS FOR AUTONOMOUS 
		startPos = 2;
				
	
		/*COMMENT OUT IF SPARK MOTOR CONTROLLER IS USED
		Spark motor_frontLeft = new Spark(RobotMap.FRONT_LEFT_MOTOR_ID);
		Spark motor_rearLeft = new Spark(RobotMap.REAR_LEFT_MOTOR_ID);
		Spark motor_frontRight = new Spark(RobotMap.FRONT_RIGHT_MOTOR_ID);
		Spark motor_rearRight = new Spark(RobotMap.REAR_RIGHT_MOTOR_ID);

		/*COMMENT OUT IF VICTORSP MOTOR CONTROLLER IS USED
		//VictorSP motor_frontLeft = new VictorSP(RobotMap.PWM_PinOut.FRONT_LEFT_MOTOR_ID);
		//VictorSP motor_rearLeft = new VictorSP(RobotMap.PWM_PinOut.REAR_LEFT_MOTOR_ID);
		//VictorSP motor_frontRight = new VictorSP(RobotMap.PWM_PinOut.FRONT_RIGHT_MOTOR_ID);
		//VictorSP motor_rearRight = new VictorSP(RobotMap.PWM_PinOut.REAR_RIGHT_MOTOR_ID);

		SpeedControllerGroup motors_left = new SpeedControllerGroup(motor_frontLeft, motor_rearLeft);
		SpeedControllerGroup motors_right = new SpeedControllerGroup(motor_frontRight, motor_rearRight);

		//driveBase = new DifferentialDrive(motors_left, motors_right);
		
		//This stops the robot if no input received SAFETY!!
		//driveBase.setExpiration(0.1);*/

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
	public void autonomousInit(){
		robotIO.getLeft_motor_encoder().reset();
		robotIO.getRight_motor_encoder().reset();
		
	}
		
	public void autonomousPeriodic(){
		//Disables the setExpiration to stop robot stopping
		robotDrive.driveBase.setSafetyEnabled(false);
		
		/*driveBase.setInvertedMotor(MotorType.kFrontRight, true);
		driveBase.setInvertedMotor(MotorType.kRearRight, true);
		driveBase.setInvertedMotor(MotorType.kFrontLeft, true);
		driveBase.setInvertedMotor(MotorType.kRearLeft, true);*/
		
		
		/*driveBase.setInvertedMotor(MotorType.kFrontRight, true);
		driveBase.setInvertedMotor(MotorType.kRearRight, true);
		driveBase.setInvertedMotor(MotorType.kFrontLeft, true);
		driveBase.setInvertedMotor(MotorType.kRearLeft, true);*/
		
		
		
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		//gameData = "LLL";
	    boxLifter.liftUp(0.5);
		if(gameData.charAt(0) == 'L' && flag)
		{
		  switch(startPos){
			  case 0:                       //position 0 (leftleft) going to left side
				                            //drives straight forward to side of switch, turns and drops cube
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.5);	
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(0.5);
				  robotDrive.driveBase.tankDrive(-0.7, -0.7);
				  Timer.delay(1.5);
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(0.5);
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.5);	
				  robotDrive.driveBase.tankDrive(0, 0);
				  Timer.delay(0.1);
				  robotDrive.driveBase.arcadeDrive(-0.65, 0.8);
				  Timer.delay(0.7);	
				  robotDrive.driveBase.tankDrive(0, 0);
				  Timer.delay(0.1);
				  boxGrabber.spitOut(-0.7);				  
				  Timer.delay(1.5);

				  flag = false;
			  break;
			  case 1:                       //position 1 (left) going to left side
				                            //straight forward to infront of switch, drops cube
				  							//very good
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.5);				  
				  robotDrive.driveBase.tankDrive(-0.7, -0.7);
				  Timer.delay(1);
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.5);				  
				  boxGrabber.spitOut(-0.7);
				  Timer.delay(1.5);
				  
				  flag = false;
				
			  break;
			  case 2:                       //center position (2) going to left side
				                            //goes across to left side, behind other robots, to beside switch then turns to drop cube
				                            //add in delay if other robots need to time to move oout of our path!!!
				  //small forward to allow turn
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.3);
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(0.3);
				  //90 turn
				  robotDrive.driveBase.arcadeDrive(-0.7, -0.8);
				  Timer.delay(0.6);	
				  //drive across
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.3);	
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(0.5);
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.3);
				  //turn 90
				  robotDrive.driveBase.arcadeDrive(-0.7, 0.8);
				  Timer.delay(0.6);
				  //drive forward
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(0.8);
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.8);
				  robotDrive.driveBase.tankDrive(0, 0);
				  Timer.delay(0.1);
				  //drop cube
				  boxGrabber.spitOut(-0.7);				  
				  Timer.delay(1.5);
				  
				  flag = false;
				  
			  break;
			  case 3:                       //position 3 (right) going to left side
				                            //drives straight forward, stops in front of switch over line
	/*			  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(2);				  
				  robotDrive.driveBase.tankDrive(-0.7, -0.7);
				  Timer.delay(1.5);
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(1.2);	
		*/
				  
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.5);				  
				  robotDrive.driveBase.tankDrive(-0.7, -0.7);
				  Timer.delay(1);
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.5);				  
				  
				  //test for turn to degrees:
				  //robotDrive.turnToDegrees(90, true);             //turns left
				  //robotDrive.turnToDegrees(90, false);            //turns right
				  
				  //test for drive to metres:
				  //robotDrive.speedUp;
				  //robotDrive.driveForMetres(1.5//parallels, driving and adjusting heading at once);
				  //robotDrive.slowDown;
				  
				  //robotDrive.speedUp();                                  //travelling for 2100mm
				  //robotDrive.driveForMetres(0.9);
				 // robotDrive.driveBase.arcadeDrive(0.8, 0);
				  //Timer.delay(2);                                      //4980mm for 3 secs 3630mm for 2 sec 2400mm for 1 sec
				  //robotDrive.slowDown();
				  //robotDrive.driveBase.arcadeDrive(0, 0);      
				
				 flag = false;
			  break;
			  case 4:                         //position 4 (rightright) going to left side
				                              //drive forward over line, stops next to switch
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(1);	
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(1);
				  robotDrive.driveBase.tankDrive(-0.7, -0.7);
				  Timer.delay(1.5);
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(0.7);
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(1.2);	
				  if(gameData.charAt(1) == 'R' && flag) {             //trying to spit cube into scale
					  robotDrive.driveBase.arcadeDrive(0.7, -0.6);
					  Timer.delay(0.15);
					  boxGrabber.spitOut(-1);				  
					  Timer.delay(1.5);
				  }
				  
				  //drives forward past switch, turns left, drives to left switch, turns and drops cube, second opt.:
				  /*robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(1);	
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(1);
				  robotDrive.driveBase.tankDrive(-0.7, -0.7);
				  Timer.delay(2.5);                                  //change to go far enough in testing
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(0.7);
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(1.2);	
				  robotDrive.driveBase.arcadeDrive(-0.7, -0.8);
				  Timer.delay(1.7);                                  //change to turn 90
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(1);	
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(1);
				  robotDrive.driveBase.tankDrive(-0.7, -0.7);
				  Timer.delay(2.5);                                  //change to go far enough in testing
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(0.7);
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(1.2);	
				  robotDrive.driveBase.arcadeDrive(-0.7, -0.8);
				  Timer.delay(1.7);                                  //change to turn 90
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.5);	
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(1);                                     //drive forward slightly
				  boxGrabber.spitOut(-0.5);				  
				  Timer.delay(1.5);                                   //cube go
				  */
				  //end of second option
				  
				  flag = false;
				  break;
			  default: 
			  break;
		  }
		} 
		else if(gameData.charAt(0) == 'R' && flag){
		  switch(startPos){
			  case 0:                       //position 0 (leftleft) going to right side
                                            //drive forward over line, stops next to switch
		  robotDrive.driveBase.tankDrive(-0.5, -0.5);
		  Timer.delay(1);	
		  robotDrive.driveBase.tankDrive(-0.6, -0.6);
		  Timer.delay(1);
		  robotDrive.driveBase.tankDrive(-0.7, -0.7);
		  Timer.delay(1.5);
		  robotDrive.driveBase.tankDrive(-0.6, -0.6);
		  Timer.delay(0.7);
		  robotDrive.driveBase.tankDrive(-0.5, -0.5);
		  Timer.delay(1.2);	
		  if(gameData.charAt(1) == 'L' && flag) {              //trying to spit cube into scale
			  robotDrive.driveBase.arcadeDrive(0.7, 0.6);
			  Timer.delay(0.15);
			  boxGrabber.spitOut(-1);				  
			  Timer.delay(1.5);
		  }
				                           
				  //robotDrive.turnToDegrees(-90, true);
				
				  flag = false;
				  break;
			  case 1:                       //position 1 (left) going to right side
				                            //drives straight forward, stops in front of switch
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(2);				  
				  robotDrive.driveBase.tankDrive(-0.7, -0.7);
				  Timer.delay(1.5);
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(1.2);	
				  
				  flag = false;
				  
				  break;
			  case 2:                       //center position (2) going to right side
				                            //goes across to right side, behind other robots, to beside switch then turns to drop cube
                                            //add in delay if other robots need to time to move oout of our path!!!
				  
				//small forward to allow turn
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.3);
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(0.3);
				  //90 turn
				  robotDrive.driveBase.arcadeDrive(-0.7, 0.8);
				  Timer.delay(0.6);	
				  //drive across
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.3);	
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(0.5);
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.3);
				  //turn 90
				  robotDrive.driveBase.arcadeDrive(-0.7, -0.8);
				  Timer.delay(0.6);
				  //drive forward
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(0.8);
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.8);
				  robotDrive.driveBase.tankDrive(0, 0);
				  Timer.delay(0.1);
				  //drop cube
				  boxGrabber.spitOut(-0.7);				  
				  Timer.delay(1.5);

				  
				  flag = false;
				  
				  break;
			  case 3:                       //position 3 (right) going to right side
				                            //drive straight forward and drop cube
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.5);				  
				  robotDrive.driveBase.tankDrive(-0.7, -0.7);
				  Timer.delay(1);
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.5);				  
				  boxGrabber.spitOut(-0.7);
				  Timer.delay(1.5);
				
				  
				  flag = false;

				 break;
			  case 4:                         //position 4 (rightright) going to right side
				                              //drives straight forward to side of switch, turns and drops cube
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.5);	
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(0.5);
				  robotDrive.driveBase.tankDrive(-0.7, -0.7);
				  Timer.delay(1.5);
				  robotDrive.driveBase.tankDrive(-0.6, -0.6);
				  Timer.delay(0.5);
				  robotDrive.driveBase.tankDrive(-0.5, -0.5);
				  Timer.delay(0.5);	
				  robotDrive.driveBase.tankDrive(0, 0);
				  Timer.delay(0.1);
				  robotDrive.driveBase.arcadeDrive(-0.65, -0.8);
				  Timer.delay(0.7);	
				  robotDrive.driveBase.tankDrive(0, 0);
				  Timer.delay(0.1);
				  boxGrabber.spitOut(-0.7);				  
				  Timer.delay(1.5);
				  
				  flag = false;
				  
				  break;
			  
			  default: 
				  //driveBase.drive(0.0,0);
				// Timer.delay(1.0);
				//  driveBase.drive(0, 0);
				  break;
			}
		}
		robotDrive.driveBase.setSafetyEnabled(true);
	}

	public void teleopPeriodic(){   //teleopPeriodic   operatorControl
		robotDrive.driveBase.setSafetyEnabled(true);
			  
		
		
		//Ensures robot only drives when under operator control 
		while(isOperatorControl() && isEnabled()) {//&&false){
			
			//Exponential Speed Controller
			//double speedSlider = driverStick.getRawAxis(3) + 2;
			
			// X-axis for turning , Y-axis for forward/back  
			
			double speedModifierX = 1.0; //changed to -ve to invert the twist turn
			double speedModifierY = -1.0;
			boxLifter.setCurrentLiftDistance(robotIO.getLifter_motor_encoder().getDistance());
			
			
			
			//Sets speed to half when side button is held, for fine control
			if(robotIO.getDriverStick().getRawButton(RobotMap.JOYSTICK_TRIGGER_NUMBER)){
				speedModifierX = -robotIO.getDriverStick().getRawAxis(RobotMap.JOYSTICK_AXIS);
				speedModifierY = robotIO.getDriverStick().getRawAxis(RobotMap.JOYSTICK_AXIS);	

				//change = joystick - limitedJoystick;
				//if (change>limit) change = limit;
				//else (if change<-limit) change = -limit;
				//limitedJoystick += change;
				
				//limit is the amount of change you will allow every iteration
				//limitedJoystick is the rate-limited joystick value you use to control your motors.
				
			}


			if (robotIO.getDriverStick().getRawButton(RobotMap.SUCK_IN_BUTTON)){
				
				double intakePower = 1;  //
				boxLifter.placeDown(0.4);
				boxGrabber.suckIn(intakePower);
				robotIO.getLifter_motor_encoder().reset();
				
			} 
			
			if (robotIO.getDriverStick().getRawButton(RobotMap.SPIT_OUT_BUTTON)){
				
				double outputPower = -1;  //this value will need to be created from the PID data
				boxGrabber.spitOut(outputPower);
				
			}
			
			if (robotIO.getDriverStick().getRawButton(RobotMap.SHUFFLE_BUTTON)){
				
				boxGrabber.shuffle();
				
			}
			
			if (robotIO.getDriverStick().getRawButton(RobotMap.SWITCH_HEIGHT_BUTTON)){
				//robotIO.getLifter_motor_encoder().reset();
				double target =250;
				if(boxLifter.getCurrentLiftDistance() < target - 20 ) {
					boxLifter.setLiftPower(0.7);
					} else if (boxLifter.getCurrentLiftDistance() > target + 20) {
						
						boxLifter.setLiftPower(0.3);
					} else {
						
						boxLifter.setLiftPower(BoxLifter.getHoldPower());
					}
								
				boxLifter.liftUp(BoxLifter.getLiftPower());

			}
	   		if (robotIO.getDriverStick().getRawButtonReleased(RobotMap.SWITCH_HEIGHT_BUTTON)) {
				/*if(boxLifter.getCurrentLiftDistance() > 10) {
				boxLifter.placeDown(0.5);				
				BoxLifter.softDown();
				Timer.delay(2);
				}*/
	   			
	   			boxLifter.softDown();
	   			
	   			
		
			} 
			if (robotIO.getDriverStick().getRawButton(6)){
				
				double lowerPower = 0.5;  //this value will need to be created from the PID data
				boxLifter.placeDown(lowerPower);
				//BoxLifter.softDown();
				//Timer.delay(2);
			}
			
			if (robotIO.getDriverStick().getRawButton(10)){
				//robotDrive.turnToDegrees(10);
			}
			//Sets the driving method
			//Use this one for z rotation
			robotDrive.driveBase.curvatureDrive(robotIO.getDriverStick().getRawAxis(1)*speedModifierY, robotIO.getDriverStick().getRawAxis(2)*speedModifierX, true);
			//Use this one for x rotation
			//driveBase.arcadeDrive(driverStick.getRawAxis(1)*speedModifierY, driverStick.getRawAxis(0)*speedModifierX, true);

			
		}
		
		/*System.out.print("encoder Left:  "+robotIO.getRight_motor_encoder().getDistance());
		System.out.println("encoder Right:  "+robotIO.getLeft_motor_encoder().getDistance());
		.println("encoder Lifter:  "+ robotIO.getLifter_motor_encoder().getDistance());*/
	}
	public void disabledInit(){
		
		
	}
	public void teleopInit(){
		robotIO.getLifter_motor_encoder().reset();
		robotIO.getLeft_motor_encoder().reset();
		robotIO.getRight_motor_encoder().reset();
		robotIO.getAhrs().zeroYaw();
		
	}
	public void disabledPeriodic(){
		
		
	}
	public void robotPeriodic(){
		
		
	}
	@SuppressWarnings("deprecation")
	public void testPeriodic(){
		
	LiveWindow.run();
		
	}
	public static RobotIO getLocalRobotIO() {
		
		return robotIO;
	}
	public static BoxLifter getlocalBoxLifter() {
		return boxLifter;
	}
	
}
