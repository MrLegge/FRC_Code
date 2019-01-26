/* this is the code that brings the external libraries in for our use*/
package org.usfirst.frc.team6007.robot;

//import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.VictorSP;
public class RobotDrive extends Subsystem{
	
	public DifferentialDrive driveBase;
	//private Spark motor_frontLeft;
	//private Spark motor_rearLeft;
	//private Spark motor_frontRight;
	//private Spark motor_rearRight;
	private double currentDriveDistanceInMetres;
	private double upperLimit;
	private double lowerLimit;
	private double inputPower;
	private double metreCalc;
	private double turnSpeed;
	private boolean slowDownFlag;
	private boolean leftNavXFlag;
	private boolean rightNavXFlag;
	private VictorSP motor_frontLeft;
	private VictorSP motor_rearLeft;
	private VictorSP motor_frontRight;
	private VictorSP motor_rearRight;
	
	

	
	
	public RobotDrive(){
		
		/*COMMENT OUT IF SPARK MOTOR CONTROLLER IS USED*/
		/*motor_frontLeft = new Spark(RobotMap.FRONT_LEFT_MOTOR_ID);
		motor_rearLeft = new Spark(RobotMap.REAR_LEFT_MOTOR_ID);
		motor_frontRight = new Spark(RobotMap.FRONT_RIGHT_MOTOR_ID);
		motor_rearRight = new Spark(RobotMap.REAR_RIGHT_MOTOR_ID);*/

		/*COMMENT OUT IF VICTORSP MOTOR CONTROLLER IS USED*/
		motor_frontLeft = new VictorSP(RobotMap.FRONT_LEFT_MOTOR_ID);
		motor_rearLeft = new VictorSP(RobotMap.REAR_LEFT_MOTOR_ID);
		motor_frontRight = new VictorSP(RobotMap.FRONT_RIGHT_MOTOR_ID);
		motor_rearRight = new VictorSP(RobotMap.REAR_RIGHT_MOTOR_ID);

		SpeedControllerGroup motors_left = new SpeedControllerGroup(motor_frontLeft, motor_rearLeft);
		SpeedControllerGroup motors_right = new SpeedControllerGroup(motor_frontRight, motor_rearRight);

		driveBase = new DifferentialDrive(motors_left, motors_right);
		
		//This stops the robot if no input received SAFETY!!
		driveBase.setSafetyEnabled(true);
		driveBase.setExpiration(0.1);
		
		currentDriveDistanceInMetres = 0;
		lowerLimit = 0.4;
		upperLimit = 0.7;
		metreCalc = 440;
		slowDownFlag = false;
		leftNavXFlag = true;
		rightNavXFlag = true;
	}
	//for larger values
	public void driveForwardForMetres(double inputMetres){
		currentDriveDistanceInMetres = 0;
		inputPower = lowerLimit;
		slowDownFlag = false;
		if(inputMetres <= 0.5){
			//System.out.println(currentDriveDistanceInMetres);
			while(currentDriveDistanceInMetres < inputMetres){
				
				currentDriveDistanceInMetres = ((Math.abs(Robot.robotIO.getRight_motor_encoder().getDistance()) + Math.abs(Robot.robotIO.getLeft_motor_encoder().getDistance())) /2) / metreCalc;
				//System.out.println(currentDriveDistanceInMetres);
				//System.out.println("first while  "+currentDriveDistanceInMetres);
			    //System.out.print("encoder Left:  "+Robot.robotIO.getRight_motor_encoder().getDistance());
			    //System.out.println("encoder Right:  "+Robot.robotIO.getLeft_motor_encoder().getDistance());
				inputPower = 0.5;
				driveBase.arcadeDrive(inputPower, 0);
				if(currentDriveDistanceInMetres >= inputMetres){
					
					driveBase.arcadeDrive(0, 0);
					
				}
			}
			
		}
		else{
			//.println("in else    " + currentDriveDistanceInMetres);
			while (inputMetres > currentDriveDistanceInMetres+0.5) {
				currentDriveDistanceInMetres = ((Math.abs(Robot.robotIO.getRight_motor_encoder().getDistance()) + Math.abs(Robot.robotIO.getLeft_motor_encoder().getDistance())) /2) / metreCalc;
				slowDownFlag = true;
				
				if (inputPower < upperLimit) {
					//System.out.println("in if in < upper " + currentDriveDistanceInMetres);
					inputPower += 0.005;
					driveBase.arcadeDrive(inputPower, 0);
					
				} 

				else{
					
					driveBase.arcadeDrive(inputPower, 0);
				}
				driveBase.arcadeDrive(0, 0);
			}
			
			/*this will be to drive the robot the final 0.5 metres*/
			while(slowDownFlag && currentDriveDistanceInMetres < inputMetres){
				currentDriveDistanceInMetres = ((Math.abs(Robot.robotIO.getRight_motor_encoder().getDistance()) + Math.abs(Robot.robotIO.getLeft_motor_encoder().getDistance())) /2) / metreCalc;
				
				if(inputPower > lowerLimit){
					inputPower -= 0.01;
					driveBase.arcadeDrive(inputPower, 0);
										
				}
				
			}		
			driveBase.arcadeDrive(0, 0);		
		}

	}
	
	public void robotTurn(boolean left) {
		turnSpeed = 0.7;
		
		if(left) {
			turnSpeed = turnSpeed * -1;
		}
		driveBase.arcadeDrive(0.7, turnSpeed);
		
	}
	
	public void turnToDegrees(double turnDegrees, boolean leftIsTrue) {
		//System.out.println(Robot.robotIO.getAhrs().getYaw());
		Robot.robotIO.getAhrs().zeroYaw();
		double currentDirection = Robot.robotIO.getAhrs().getYaw();
		if(leftIsTrue) {
			while(currentDirection > -turnDegrees && leftNavXFlag) {
				currentDirection = Robot.robotIO.getAhrs().getYaw();
				robotTurn(true);
				if(currentDirection > 5) {
					leftNavXFlag = false;	
				}
				
			}
			driveBase.arcadeDrive(0, 0);
			//System.out.println(currentDirection);
			if(leftNavXFlag) {
				//System.out.println("going right way");
			}
			else {
				//System.out.println("going wrong way");	
			}
			
		}
		else {
			while(currentDirection < turnDegrees && rightNavXFlag) {
				currentDirection = Robot.robotIO.getAhrs().getYaw();
				robotTurn(false);
				if(currentDirection< -5) {
					rightNavXFlag = false;
				}
			}
			driveBase.arcadeDrive(0, 0);
			//System.out.println(currentDirection);
			if(rightNavXFlag) {
				//System.out.println("going right way");
			}
			else {
				//System.out.println("going wrong way");
			}
		}
			
		}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void speedUp() {                //for travel over 2secs
		 driveBase.arcadeDrive(0.6, 0);
		  Timer.delay(0.5);                                        //730mm
		 driveBase.arcadeDrive(0.65, 0);
		  Timer.delay(0.5);
		 driveBase.arcadeDrive(0.7, 0);
		  Timer.delay(0.5);                                      
		 driveBase.arcadeDrive(0.75, 0);                         //2100mm     
		  Timer.delay(0.5);
	}
	
	public void driveForMetres(double inputMetres) {
	double timeDelay = inputMetres/1.5;                    //1.5m/sec 
	Timer.delay(timeDelay);
	}
	
	public void slowDown() {                                       
		 driveBase.arcadeDrive(0.7, 0);
		 Timer.delay(0.5);   
		 driveBase.arcadeDrive(0.65, 0);
		 Timer.delay(0.5);
		 driveBase.arcadeDrive(0.6, 0);
		 Timer.delay(0.5); 
		 driveBase.arcadeDrive(0, 0);           //added in a stop
	}
	
	
  /*  public  DriveStraight() {                            //parallels, driving and adjusting heading at once
    	addParallel(driveForMetres(-));                  //add parallel work? change drive function
    	addParallel(alignHeading);                       
    }*/
	
	/*public void speedUp() {                //for travelling shorter distances, try
		 driveBase.arcadeDrive(0.6, 0);
		  Timer.delay(0.25);                                       
		 driveBase.arcadeDrive(0.65, 0);
		  Timer.delay(0.25);
		 driveBase.arcadeDrive(0.7, 0);
		  Timer.delay(0.25);                                      
		 driveBase.arcadeDrive(0.75, 0);                              
		  Timer.delay(0.25);
	}
	
	public void driveForMetres(double inputMetres) {
	double timeDelay = inputMetres/1.5;                    //1.5m/sec 
	Timer.delay(timeDelay);
	}
	
	public void slowDown() {                                       
		 driveBase.arcadeDrive(0.7, 0);
		 Timer.delay(0.25);   
		 driveBase.arcadeDrive(0.65, 0);
		 Timer.delay(0.25);
		 driveBase.arcadeDrive(0.6, 0);
		 Timer.delay(0.25); 
	}
	 * 
	 * }
	 */
	

	
	
}
