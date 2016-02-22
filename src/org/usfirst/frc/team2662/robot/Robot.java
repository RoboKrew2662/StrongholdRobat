
package org.usfirst.frc.team2662.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team2662.robot.commands.ExampleCommand;
import org.usfirst.frc.team2662.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;

    Command autonomousCommand;
    SendableChooser chooser;
    
    //Motor Controller
    public Victor frontRight = new Victor(2);
    public Victor frontLeft = new Victor(0);
    public Victor backRight = new Victor(3);
    public Victor backLeft = new Victor(1);
    
    //Arm Controller
    public Victor armController = new Victor(4);
    
    //Driver Controls
    //Get Controls 
    Joystick driver = new Joystick(0);
    Button slow = new JoystickButton(driver, 1 );
    Button up = new JoystickButton(driver, 3 );
    Button down = new JoystickButton(driver, 2 );
    Button forward = new JoystickButton(driver, 6);
    Button backward = new JoystickButton(driver, 7);
    	
    //Define Variables 
   	double xAxis;  //These are for the drive function
   	double yAxis;
   	double zAxis;
   	
   	public double rightCorrection = .95;
   	
   	boolean slowSpeed;
   	
   	boolean armUp = false;
   	boolean armDown = false; 
    	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	//jhfgjhgf obst
        
        
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        //Nurrr da currrrd        
        
        //Drive Function
        zAxis = (-driver.getZ() + 1)/2;
                                           // <---  Dank CopyPasta #1
        if(slow.get()){
        	yAxis = (driver.getY())*zAxis;
	        xAxis = -(driver.getX())*zAxis;
	        slowSpeed = false;
        }else{
                                           // <---  Dank CopyPasta #2
	        yAxis = (driver.getY());
	        xAxis = -(driver.getX());
	        slowSpeed = false;
        }
        
        frontRight.set((yAxis - xAxis)*rightCorrection);
        backRight.set((yAxis - xAxis));
        frontLeft.set(-(yAxis + xAxis)*rightCorrection);
        backLeft.set(-(yAxis + xAxis));
        
        System.out.println("Slow: " + slowSpeed);
        
        		//Move Forwaards and Backwards
        if(backward.get()){
        	frontRight.set(rightCorrection * zAxis);
            backRight.set(rightCorrection * zAxis);
            frontLeft.set(-(1 * zAxis));
            backLeft.set(-(1 * zAxis));
        }
         
        if(forward.get()){
        	frontRight.set(-(1 * zAxis)*rightCorrection);
            backRight.set(-(1 * zAxis)*rightCorrection);
            frontLeft.set((1 * zAxis));
            backLeft.set((1 * zAxis));
        }
        
        //Arm Contrololololol Function
        if(up.get()) {
        	armController.set(1);
        	armUp = true;
        	armDown = false;
        }else if(down.get()){	
            armController.set(-1);
            armDown = true;	
            armUp = false;
        }else{
        	armController.set(0);
        }
              
        System.out.println(armController);
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
