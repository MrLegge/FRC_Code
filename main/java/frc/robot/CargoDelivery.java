/*******************************************************
* Robotics Hatch Delivery by Jordan Thorne date 23/01/19	
*******************************************************/
/***********************************
*Arm that retrives and delivers disk
***********************************/
package frc.robot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;

public class CargoDelivery extends Kevin{
	public static VictorSP cargoDeliveryMotor;

	public CargoDelivery(RobotIO rio, VictorSP tkm, VictorSP bkm, SpeedControllerGroup kms, int off, PIDController kc){
		//super(new RobotIO(), new VictorSP(RobotMap.PWM_PinOut.TOP_HATCH_MOTOR_ID), new VictorSP(RobotMap.PWM_PinOut.BOTTOM_HATCH_MOTOR_ID), new DifferentialDrive(topKevinMotor, bottomKevinMotor), 10);
		super(rio, tkm, bkm, kms, off, kc);
		cargoDeliveryMotor = new VictorSP(RobotMap.PWM_PinOut.CARGO_SHOOTER_MOTOR_ID);

	}

	public static void liftToCargoPosition(int target){
		liftToPosition(target);
		}

	public static void shootCargo(){
		cargoDeliveryMotor.set(1.0); //shhots out ball
	}
	public static void succCargo(){
		cargoDeliveryMotor.set(-1.0); //succ in ball
	}	

  }
