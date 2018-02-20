package org.usfirst.frc.team6007.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType; 


public class RobotIO(){
  
  AHRS ahrs;
 // Joystick driverStick;
  
  
}

public RobotIO(){
  try{
  ahrs = new AHRS(SPI.Port.kMXP); 
  }
  catch (RuntimeException ex ){
     DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
  }
}
