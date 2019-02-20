/*******************************************************
* Robotics Hatch Delivery by Jordan Thorne date 23/01/19	
*******************************************************/
/***********************************
*Arm that retrives and delivers disk
***********************************/
package frc.robot;
import edu.wpi.first.wpilibj.VictorSP;
import java.lang.Math;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class HatchDelivery extends Kevin{

	public HatchDelivery(RobotIO rio, VictorSP tkm, VictorSP bkm, SpeedControllerGroup kms, int off, PIDController kc){
		super(rio, tkm, bkm, kms, off, kc);
	}

	/*********talks to kevin to go to hatch position**********/
	public static void liftToHatchPosition(double target){
		//liftToPosition(target);
		}

	
  }
