/*******************************************************
* Robotics Hatch Delivery by Jordan Thorne date 23/01/19	
*******************************************************/
/***********************************
*Arm that retrives and delivers disk
***********************************/
package frc.robot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import java.lang.Math;

public class HatchDelivery{

	private RobotIO robotIO;
	private VictorSP topHatchMotor;
	private VictorSP bottomHatchMotor;
	private DifferentialDrive hatchBase;
	private int hatchTargetLower, hatchTargetUpper, cargoTargetLower, cargoTargetUpper;
	public double liftPower;
	
	public long hatchPotentiometer;

	public HatchDelivery(){

		//robotIO = new RobotIO();
		hatchTargetLower = 110;//both guesses
		hatchTargetUpper = 90;
		
		cargoTargetLower = 140;
		cargoTargetUpper = 120;


		topHatchMotor = new VictorSP(RobotMap.PWM_PinOut.TOP_HATCH_MOTOR_ID);
		bottomHatchMotor = new VictorSP(RobotMap.PWM_PinOut.BOTTOM_HATCH_MOTOR_ID);
		bottomHatchMotor.setInverted(true);
		hatchBase = new DifferentialDrive(topHatchMotor, bottomHatchMotor);

		hatchBase.setExpiration(0.1);
		hatchBase.setSafetyEnabled(true);

	}

	//Puts disk in home position
	public void hatchLifterToHomePosition(double Power){
		while(!robotIO.hatchSwitchAtHome()){
			hatchBase.arcadeDrive(Power, 0);
			//while so it runs until its false
		}
		
	}
	
	//Puts arm in delivery position
	public void hatchDeliveryPosition(double Power) {
	
		if (hatchPotentiometer > hatchTargetLower) {
			liftPower = 0.4;
		} else if (hatchPotentiometer < hatchTargetUpper) {
			liftPower = -0.5;
		} else {
			liftPower = -0.475;
		}
	
		hatchBase.arcadeDrive(liftPower, 0);
	//daniel is a bit short
}
public void cargoDeliveryPosition(double Power) {
	
	if (hatchPotentiometer  > cargoTargetLower) {
		liftPower = 0.4;
	} else if (hatchPotentiometer < cargoTargetUpper) {
		liftPower = -0.5;
	} else {
		liftPower = -0.475;
	}	
	hatchBase.arcadeDrive(liftPower, 0);
// daniel needs a haircut
}	

	//Retrieves the hatch from floor
	public void retriveHatchFromFloor (double DownPower) {
		while (!robotIO.hatchSwitchAtLower()){
			hatchBase.arcadeDrive(DownPower, 0);	
		}
		// daniel needs to stop playing games on the drive computer
	}

	public void liftHatchArmUp(){	
		hatchBase.arcadeDrive(0.4, 0.0);
	}
	public void putHatchArmDown(){	
		hatchBase.arcadeDrive(-0.475, 0.0);
	}
	
  }
