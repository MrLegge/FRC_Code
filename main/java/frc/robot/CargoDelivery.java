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

	//private RobotIO robotIO;

	public CargoDelivery(){
		super();



	}

	public static void liftToCargoPosition(int target){
		Robot.kevin.liftToPosition(target);
		}

	
  }
