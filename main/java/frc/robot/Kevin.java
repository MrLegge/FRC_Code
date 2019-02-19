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
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import java.lang.Math;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.*;

public class Kevin extends Subsystem{
	public static RobotIO robotIO;
	public static VictorSP topKevinMotor;
	public static VictorSP bottomKevinMotor;
	public static PIDController kevinController;
	public static SpeedControllerGroup kevinMotors;
	public static double targetHigherFast, targetLowerFast, targetLowerSlow, targetHigherSlow, offset, newKevinPotentiometer;
	public static double kevinPotentiometer = 0.0;
	public static double liftPower;
	private static double kevinError, homeError;
	private static boolean homePosition, floorPostition;
	private static int count = 0;
	public double kevinsPower;

	public Kevin() {
		robotIO = new RobotIO();

		topKevinMotor = new VictorSP(RobotMap.PWM_PinOut.TOP_HATCH_MOTOR_ID);
		bottomKevinMotor = new VictorSP(RobotMap.PWM_PinOut.BOTTOM_HATCH_MOTOR_ID);
		kevinMotors = new SpeedControllerGroup(topKevinMotor, bottomKevinMotor);

		offset = 10;
	}

	Kevin(RobotIO rio, VictorSP tkm, VictorSP bkm, SpeedControllerGroup kms, int off, PIDController kc) {
		robotIO = rio;

		topKevinMotor = tkm;
		bottomKevinMotor =bkm;
		kevinMotors = kms;

		offset = 10;
	}

	/***********puts disk in home position**************/
	public static void liftToPosition(double target) {
		/*******a thing to print some stuff, just ignore it********/
		/*if(count%50 == 0){
			//System.out.println("high power up");
			count++;
		}else{
			count++;
		}*/
		
		kevinPotentiometer = Math.round(robotIO.getCurrentLiftDistance() * 2000);
		/************some variables that arent needed anymore****************
		targetLowerSlow = target + offset;
		targetLowerFast = target + 100;
		targetHigherSlow = target - offset;
		targetHigherFast = target - 130;

		//this is the if/else way of doing it, not as great
		/***********Moving up********************
		if(kevinPotentiometer > targetLowerFast){
			liftPower = 0.7;    //high powered moving up  tick
		}

		else if(kevinPotentiometer < targetLowerFast && kevinPotentiometer > targetLowerSlow){
			liftPower = 0.3;   //low powered moving up
		}

		/***********Moving down******************
		else if(kevinPotentiometer < 100){
			liftPower = -0.4;
		}

		else if(kevinPotentiometer < targetHigherFast && kevinPotentiometer > 100){
			liftPower = -0.25;   //high powered moving down tick check power???
		}

		else if(kevinPotentiometer > targetHigherFast && kevinPotentiometer < targetHigherSlow){
			liftPower = -0.1;   //low powered moving down
		}

		/************Holding steady************
		else if(kevinPotentiometer < targetLowerSlow && kevinPotentiometer > targetHigherSlow){
			liftPower = 0.28;   //holding steady
		}
		*/
		
		//this is a simplier way of doing it, probably better 
		//it takes the current value and subtracts the target and sets that as another variable
		//it then multiplies that number by another and adds a base value to give motor power 
		kevinError = kevinPotentiometer - target;
		liftPower =  0.05 + (kevinError * 0.004);
		manualKevinControl(liftPower);
	}

	/************places the arm down into home position************/
	public void placeArmDown(double homeTarget){
		kevinPotentiometer = Math.round(robotIO.getCurrentLiftDistance() * 2000);
		homeError = kevinPotentiometer - homeTarget;
		liftPower =  0.4 + (homeError * 0.004);
		manualKevinControl(liftPower);
	}

	/***********unfinished code but will get disk from floor**************/
	public static void retriveDiskFromFloor(){
		/*if(kevinPotentiometer < 100){
			liftPower = -0.3;
		}else if(kevinPotentiometer > 99 && kevinPotentiometer < 150){

			
		}*/
	}

	/***********does all the controlling for the kevin arm**************/
	public static void manualKevinControl(double powerIn) {
		kevinMotors.set(powerIn);
	}

	@Override
	protected void initDefaultCommand() {
		// setDefaultCommand(new ArcadeDrive());
	}

}
