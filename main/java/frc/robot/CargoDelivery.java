/*******************************************************
* Robotics Hatch Delivery by everyone except Jordan Thorne date 23/01/19	
*******************************************************/
/***********************************
*Arm that retrives and delivers disk
***********************************/
package frc.robot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class CargoDelivery extends Kevin{
	private static VictorSP cargoDeliveryMotor;

	public CargoDelivery(RobotIO rio, VictorSP tkm, VictorSP bkm, DifferentialDrive kb, int off){
		//super(new RobotIO(), new VictorSP(RobotMap.PWM_PinOut.TOP_HATCH_MOTOR_ID), new VictorSP(RobotMap.PWM_PinOut.BOTTOM_HATCH_MOTOR_ID), new DifferentialDrive(topKevinMotor, bottomKevinMotor), 10);
		super(rio, tkm, bkm, kb, off);
		cargoDeliveryMotor = new VictorSP(5);


	}
/*
	public static void liftToCargoPosition(int target){
		Robot.kevin.liftToPosition(target);
		}
*/

	public static void shootCargo(){
		cargoDeliveryMotor.set(1.0); //shhots out ball
	}
	public static void succCargo(){
		cargoDeliveryMotor.set(-1.0); //succ in ball
	}	

  }
