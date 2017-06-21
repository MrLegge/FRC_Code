package org.usfirst.frc.team6007.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.*;

import edu.wpi.first.wpilibj.CameraServer;

public class RobotCamera {
	private int cameraId;
	private Image frame;
	
	public RobotCamera(){
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		cameraId = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(cameraId);
	}
	
	public void pushFrame(){
		NIVision.IMAQdxGrab(cameraId, frame, 1);
		CameraServer.getInstance().setImage(frame);
	}
}
