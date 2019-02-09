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

public class CargoDelivery{

	private RobotIO robotIO;
	private VictorSP topHatchMotor;
	private VictorSP bottomHatchMotor;
	private DifferentialDrive hatchBase;
	private int cargoTargetLower, cargoTargetUpper;
	public double liftPower;
	public HatchDelivery hatchDelivery;
	public long cargoPotentiometer;

	public CargoDelivery(){

		//robotIO = new RobotIO();		
		cargoTargetLower = 130;
		cargoTargetUpper = 140;

		hatchDelivery = new HatchDelivery();

	}

	
	//Puts arm in delivery position
	public void cargoDeliveryPosition(double Power) {
	
		if (hatchDelivery.hatchPotentiometer  > cargoTargetLower) {
			liftPower = 0.4;
		} else if (hatchDelivery.hatchPotentiometer < cargoTargetUpper) {
			liftPower = -0.5;
		} else {
			liftPower = -0.475;
		}	
		hatchBase.arcadeDrive(liftPower, 0);
	
}	


	
  }
