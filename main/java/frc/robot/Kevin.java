/*******************************************************
* Kevin is the parent class to run the mec arm  
* 
* start date 10/02/19
*******************************************************/
/***********************************
*Arm that retrives and delivers disk
***********************************/
package frc.robot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import java.lang.Math;

public class Kevin{

	private RobotIO robotIO;
	private VictorSP topMotor;
	private VictorSP bottomMotor;
	private DifferentialDrive kevinBase;
	private int targetHigher, targetLower, offset;
 	public double kevinPotentiometer;
	public double liftPower;
	private boolean homePosition, floorPostition;

	public Kevin(){
		
		//robotIO = new RobotIO();
	topMotor = new VictorSP(RobotMap.PWM_PinOut.TOP_HATCH_MOTOR_ID);
	bottomMotor = new VictorSP(RobotMap.PWM_PinOut.TOP_HATCH_MOTOR_ID); 
	kevinBase = new DifferentialDrive(topMotor, bottomMotor);
	bottomMotor.setInverted(true);
	kevinBase.setExpiration(0.1);
	kevinBase.setSafetyEnabled(true);
	offset = 10;
	}

	//Puts disk in home position
	public void liftToPosition(int target){
    targetLower = target + offset;
    targetHigher = target - offset;
	kevinPotentiometer = robotIO.getCurrentLiftDistance();
	
	if (kevinPotentiometer  > targetLower + 10) {
		liftPower = 0.4;
	} else if (kevinPotentiometer < targetHigher - 10) {
		liftPower = -0.5;
	} else {
		liftPower = -0.475;
	}	
	kevinBase.arcadeDrive(liftPower, 0);
		
	}
	public void liftArmUp(double powerIn){
		kevinBase.arcadeDrive(powerIn, 0);
	}
	public void putArmDown(double powerIn){
		kevinBase.arcadeDrive(-powerIn, 0);
	}

	
  }
