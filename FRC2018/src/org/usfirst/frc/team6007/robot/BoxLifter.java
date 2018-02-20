/* this is the code that brings the external libries in for our use  */
package org.usfirst.frc.team6007.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive; 
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/* this is the declaraion of the class everything must be within these curly braces  */
public class BoxLifter extends PIDSubsystem{
	
	/* sets asside some space to make these objects will be declared later  */
	
	DifferentialDrive lifterBase;

	
	/* select correct controller for robot configuration  */
	private VictorSP topLifterMotor;
	private VictorSP bottomLifterMotor;
	// private Spark topLifterMotor;
	// private Spark bottomLifterMotor;

	
	

	/* this is the constructor that 'builds' the object when called  */
	public BoxLifter(){

		topLifterMotor = new VictorSP(RobotMap.RIGHT_TOP_LIFTER_MOTOR_ID);
		bottomLifterMotor = new VictorSP(RobotMap.RIGHT_BOTTOM_LIFTER_MOTOR_ID);
		//topLifterMotor = new Spark(RIGHT_TOP_LIFTER_MOTOR_ID);
		//bottomLifterMotor = new Spark(RIGHT_BOTTOM_LIFTER_MOTOR_ID);
		
		bottomLifterMotor.setInverted(true);

		lifterBase = new DifferentialDrive(topLifterMotor, bottomLifterMotor);

		lifterBase.setExpiration(0.1);
	
}
	
	/* function to pull the power cube into the holder  */
	public void liftUp(double liftPower){

		lifterBase.arcadeDrive(liftPower, 0);

	}
	
	/* function to eject the power cube from the holder  */
	public void placeDown(double lowerPower){

		lifterBase.arcadeDrive(lowerPower, 0);	
	
	}
		
}






