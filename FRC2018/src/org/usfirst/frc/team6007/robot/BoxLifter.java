
//imported relevent libraries
package org.usfirst.frc.team6007.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType; 




public class BoxLifter(){
 
  RobotDrive lifterBase;
 
 private Spark leftLifterMotor;
	private Spark rightLifterMotor;
	final int LEFT_LIFTER_MOTOR_ID = 2;
	final int RIGHT_LIFTER_MOTOR_ID = 3;
	private double MotorSpeed = 0.5;
  
  //constructor
  
  public BoxLifter(){
   
 leftLifterMotor = new Spark(LEFT_LIFTER_MOTOR_ID);
	rightLifterMotor = new Spark(RIGHT_LIFTER_MOTOR_ID);
   
  }
}

