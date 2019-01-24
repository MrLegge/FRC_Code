/******Edited on 21/01/2019, by Daniel Elliott******/
/****************************************************
*This things gets data from the dashboard and sends 
*data to the dashboard
****************************************************/
package frc.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotGUI{
//variable spot?
private String caseFive;                //dummy names for errors
private String case0;
private String thereIsNothingThere;
	public RobotGUI(){
	//the something goes here?
	
	}
		
	public String getData(){
		
		return SmartDashboard.getString(case0, thereIsNothingThere);
		
		//gets data from the dashboard, 
		//if there is nothing there it gives the defult data
			
	}
	
	public void sendData(){
		
		SmartDashboard.putNumber(caseFive, 420);
		//sends data to the dashboard
			
	}
	
	public boolean switchController(){
		
		return SmartDashboard.putBoolean("DB/Button 0", false);
		//if return is true, the Xbox controller is used, if false joystick is used

	}
	

		
	
	
}	
