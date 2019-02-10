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

public class CargoDelivery extends Kevin{

	private RobotIO robotIO;
	private VictorSP topHatchMotor;
	private VictorSP bottomHatchMotor;
	private DifferentialDrive hatchBase;
	private int cargoTargetLower, cargoTargetUpper;
	public double liftPower;
	public HatchDelivery hatchDelivery;
	public long cargoPotentiometer;

	public CargoDelivery(){




	}

	public void liftToCargoPosition(int target){
		liftToPosition(target);
		}

	
  }
