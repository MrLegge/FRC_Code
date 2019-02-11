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

public class HatchDelivery extends Kevin{

	private RobotIO robotIO;
	private DifferentialDrive kevinBase;
	public double liftPower;
	
	public long hatchPotentiometer;

	public HatchDelivery(){


	}

	

	//Retrieves the hatch from floor
/*	public void retriveHatchFromFloor (double DownPower) {
		while (!robotIO.hatchSwitchAtLower()){
			kevinBase.arcadeDrive(DownPower, 0);	
		}
	}*/

	public void liftToHatchPosition(int target){
		liftToPosition(target);
		}
	
  }
