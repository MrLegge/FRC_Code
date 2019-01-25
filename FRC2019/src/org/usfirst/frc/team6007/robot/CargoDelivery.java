/***********************************************************
* 
* 
************************************************************/

package org.usfirst.frc.team6007.robot;


public class CargoDelivery{
  
	public CargolDelivery(){
  		
    	}
	
	public void cargoIntake(){
		while(robotIO.cargoSwitchIntake()){
			//run motor to move to positon
		}
	}
	
  	public void cargoLoad(){
		while(robotIO.ballPotentiometer.get >= RobotMap.HATCH_DELIVERY_LOWER_lIMIT && robotIO.ballPotentiometer.get <= RobotMap.HATCH_DELIVERY_UPPER_lIMIT){
			//run motor to move to positon
		}
	}
	
	public void loaderToHome(){
		while(robotIO.homeCargoSwitch.get()){
			//run motor to move to positon
		}
	}
  }
