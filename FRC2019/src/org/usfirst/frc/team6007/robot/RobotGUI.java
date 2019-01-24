/******Edited on 21/01/2019, by Daniel Elliott******/
/****************************************************
*This things gets data from the dashboard and sends 
*data to the dashboard
****************************************************/
package frc.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotGUI{
//variable spot?
private String CaseFive;
private String Case0;
private String ThereIsNothingThere;
	public RobotGUI(){
	//the something goes here?
	
	}
		
	public String getData(){
		
		return SmartDashboard.getString(Case0, ThereIsNothingThere);
		
		//gets data from the dashboard, 
		//if there is nothing there it gives the defult data
			
	}
	
	public void sendData(){
		
		SmartDashboard.putNumber(CaseFive, 420);
		//sends data to the dashboard
			
	}
	
	public boolean switchController(){
		
		return SmartDashboard.putBoolean("DB/Button 0", false);
		//if return is true, the Xbox controller is used, if false joystick is used

	}
	

		
	
	
}	
