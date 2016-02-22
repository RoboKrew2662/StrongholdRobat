package org.usfirst.frc.team2662.robot;

public class autoCommands {

	Robot r;
	
	public void obstacle1(){
		
	}
	
	public void turnLeft(int time, double speed){
		time = 5;
		int count = time*1000;
		
		try{
			while(count > 0){
				count = count-1000;
				r.frontLeft.set(-(speed));
				r.backLeft.set(-(speed));
				r.frontRight.set(r.rightCorrection * speed);
				r.backRight.set(r.rightCorrection * speed);
				System.out.println(count);
				Thread.sleep(1000);
			} 
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	public void turnRight(int time, double speed){
		
		time = 5;
		int count = time*1000;
		
		try{
			while(count > 0){				
				count = count-1000;
				r.frontLeft.set((speed));
				r.backLeft.set((speed));
				r.frontRight.set(r.rightCorrection * -speed);
				r.backRight.set(r.rightCorrection * -speed);
				System.out.println(count/1000);
				Thread.sleep(1000);
			} 
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	public void moveForward(int time, double speed){
		
		time = 5;
		int count = time*1000;
		
		try{
			while(count > 0){				
				count = count-1000;
				r.frontRight.set(-(speed)*r.rightCorrection);
				r.backRight.set(-(speed)*r.rightCorrection);
				r.frontLeft.set((speed));
				r.backLeft.set((speed));
				System.out.println(count/1000);
				Thread.sleep(1000);
			} 
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
			
	}
	
	public void moveBackward(int time, double speed){
		
		time = 5;
		int count = time*1000;
		
		try{
			while(count > 0){				
				count = count-1000;
				r.frontRight.set(r.rightCorrection * speed);
				r.backRight.set(r.rightCorrection * speed);
				r.frontLeft.set(-(speed));
				r.backLeft.set(-(speed));
				System.out.println(count/1000);
				Thread.sleep(1000);
			} 
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}
	
	public void stop(int time){
		time = 5;
		int count = time*1000;
		
		try{
			while(count > 0){				
				count = count-1000;
				System.out.println(count/1000);
				Thread.sleep(1000);
			} 
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
}
