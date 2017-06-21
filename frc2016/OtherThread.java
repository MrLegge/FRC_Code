package org.usfirst.frc.team6007.robot;

public class OtherThread implements Runnable{
	private Robot robot;
	private boolean stopThread;
	
	public OtherThread(Robot robot){
		this.robot = robot;
		stopThread = false;
	}
	
	public void stopThread(){
		stopThread = true;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("OtherThread has started");
		
		try{
			while(true){
				if(stopThread){
					throw new InterruptedException("Stopped");
				}
				
				if(robot.getCamera() != null){
					robot.getCamera().pushFrame();
				}
			}
		}
		catch(InterruptedException e){
			System.out.println("Other thread has been stopped!");
		}
	}
	
}
