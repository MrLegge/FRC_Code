/******Edited on 21/01/2019, by Daniel Elliott******/
/****************************************************
*This things gets data from the dashboard and sends 
*data to the dashboard
****************************************************/
package org.usfirst.frc.team6007.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotGUI{
//variable spot?


	public RobotGUI(){
	//the something goes here?
	
	}
		
	public int getData(){
		
		return startPos = SmartDashboard.getString("DB/String 0", "There is nothing there");
		
		//gets data from the dashboard, 
		//if there is nothing there it gives the defult data
			
	}
	
	public int sendData(){
		
		SmartDashboardputNumber("DB/String 5", "420");
		//sends data to the dashboard
			
	}
	
	public bool switchController(){
		
		return SmartDashboard.putBoolean("DB/Button 0", false);
		//if return is true, the Xbox controller is used, if false joystick is used

	}
	

		
	
	
}	
