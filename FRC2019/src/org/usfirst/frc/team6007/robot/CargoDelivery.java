/***********************************************************
* 
* 
************************************************************/

package org.usfirst.frc.team6007.robot;


public class CargoDelivery{
  
	private static RobotIO ballLifterPotentiometer;
  	private static RobotIO robotIO;

	public CargolDelivery(){
  		robotIO = new RobotIO();
    	}
	
	public void cargoIntake(){
		while(robotIO.LowerCargoSwitch.get()){
		
		}
	}
	
  	public void cargoLoad(){
		while(robotIO.ballPotentiometer.get >= ?value? && robotIO.ballPotentiometer.get <= ?value?){
		}
	}
	
	public void loaderToHome(){
		while(robotIO.homeCargoSwitch.get()){
		
		}
	}
  }
