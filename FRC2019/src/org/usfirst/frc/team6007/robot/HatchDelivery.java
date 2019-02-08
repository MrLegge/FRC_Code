/*******************************************************
* Robotics Hatch Delivery by 
* Jordan Thorne 
* Dan Elliot 
* start date 23/01/19
*******************************************************/
/***********************************
*Arm that retrives and delivers disk
***********************************/
package frc.robot;
import edu.wpi.first.wpilibj.VictorSP;

public class HatchDelivery{

	//private static RobotIO hatchtLifterPotentiometer;
	private RobotIO robotIO;
	private VictorSP hatchMotor;
	private int target;
	public double liftPower;

	public HatchDelivery(){
		
		robotIO = new RobotIO();
		hatchMotor = new VictorSP(RobotMap.PWM_PinOut.TOP_HATCH_MOTOR_ID);
   
	}

	//Puts disk in home position
	public void hatchLifterToHomePosition(double Power){
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
