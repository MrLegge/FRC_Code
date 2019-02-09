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
	private int targetLower, targetHigher, offset;
  public long kevinPotentiometer;
	public double liftPower;

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
	public void liftToPosition(double target){
    targetLower = target + offset;
    targetHigher = target - offset;
    kevinPotentiometer = robotIO.
    while(!robotIO.hatchSwitchAtHome()){
			hatchMotor.set(Power);
			//while so it runs until its false
		}
		
	}
	
	//Puts arm in delivery position
	public void deliveryPosition(double Power) {
	
		if (robotIO.getCurrentLiftDistance() < target - 20 ) {
			liftPower = 0.7;
		} else if (robotIO.getCurrentLiftDistance() > target + 20) {
			liftPower = 0.3;
		} else {
			liftPower = 0.5;
		}
	
	hatchMotor.set(liftPower);
	
}	

	//Retrieves the hatch from floor
	public void retriveHatchFromFloor (double DownPower) {
		while (!robotIO.hatchSwitchAtLower()){
			hatchMotor.set(DownPower);	
		}
		
	}
	
  }
