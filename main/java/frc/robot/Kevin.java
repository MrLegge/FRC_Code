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
import edu.wpi.first.wpilibj.DriverStation;

public class Kevin{
	private static RobotIO robotIO;
	public static VictorSP topKevinMotor;
	public static VictorSP bottomKevinMotor;
	private static DifferentialDrive kevinBase;
	private static int targetHigher, targetLower, offset;
 	public static double kevinPotentiometer;
	public static double liftPower;
	private static boolean homePosition, floorPostition;

	public Kevin(){
	robotIO = new RobotIO();
	topKevinMotor = new VictorSP(RobotMap.PWM_PinOut.TOP_HATCH_MOTOR_ID);
	bottomKevinMotor = new VictorSP(RobotMap.PWM_PinOut.BOTTOM_HATCH_MOTOR_ID); 

	bottomKevinMotor.setInverted(true);

	kevinBase = new DifferentialDrive(topKevinMotor, bottomKevinMotor);	

	kevinBase.setExpiration(0.1);
	kevinBase.setSafetyEnabled(true);
	offset = 10;
	}
	Kevin(RobotIO rio, VictorSP tkm, VictorSP bkm, DifferentialDrive kb, int off){
		this.robotIO = rio;
		this.topKevinMotor = tkm;
		this.bottomKevinMotor = bkm; 
	
		bottomKevinMotor.setInverted(true);
	
		this.kevinBase = kb;
		kevinBase.setExpiration(0.1);
		kevinBase.setSafetyEnabled(true);
		this.offset = off;
	}

	//Puts disk in home position
	public static void liftToPosition(int target){
		targetLower = target + offset;
		targetHigher = target - offset;
		try{
		kevinPotentiometer =  Math.round(robotIO.getCurrentLiftDistance()*10000);
		System.out.println(kevinPotentiometer);

	}
	catch(RuntimeException re){
		DriverStation.reportError("Error instantiating potentiometer  " + re.getMessage(), true);
	}
		if (kevinPotentiometer  > targetLower) {
			liftPower = 0.4;
			System.out.println("moving up");

		} else if (kevinPotentiometer < targetHigher) {
			liftPower = -0.6;
			System.out.println("moving down");
		} else {
			liftPower = 0.46;
			System.out.println("holding steady");
		}	
		//kevinBase.arcadeDrive(liftPower, 0);
	}

	public void manualKevinControl(double powerIn){
		kevinBase.arcadeDrive(powerIn, 0);
	}


	
  }
