package org.usfirst.frc.team3630.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class Manipulator {

	Joystick leftStick;
	Joystick rightStick;
	
	
	double buttonIncrement;
	
	Talon motorLeft;
	Talon motorRight;
	Talon loader;
	
	int motorLeftChannel;
	int motorRightChannel;
	int loaderChannel;
	double motorLeftStrength;
	double motorRightStrength;
	double loaderStrength;
	
	public Manipulator(int channel4, int channel5, int channel6){

	motorLeftChannel = channel4;
	motorRightChannel = channel5;
	loaderChannel = channel6;

}
	public void manipulatorInit(){
		
		motorLeft = new Talon(motorLeftChannel);
		motorRight = new Talon(motorRightChannel);
		loader = new Talon(loaderChannel);
	}
	
	public void spinLeftMotor(){
	
		if (Math.abs(motorLeftStrength) <= (1-buttonIncrement)) {
			motorLeftStrength += buttonIncrement;
			
		}
		else {
			motorLeftStrength = 1.0;
		}
		motorLeft.set (motorLeftStrength);
	}
	
	public void spinRightMotor(){
		
		if (Math.abs(motorRightStrength) <= (1-buttonIncrement)) {
			motorRightStrength -= buttonIncrement;
		}
		else {
			motorRightStrength = 1.0;
		}
		motorRight.set (motorRightStrength);
		
	}
	
	public void stopMotors() {
		motorRight.set(0);
		motorLeft.set(0);
		motorLeftStrength = 0;
		motorRightStrength = 0;
	}
	
	public void loadBall() {
		if (loaderStrength == 0) {
			loader.set (-1.0);
			loaderStrength = -1;
		}
		else {
			loader.set (0);
			loaderStrength = 0;
		}
	}
	
	public void manipulatorPeriodic() {
		if (rightStick.getRawButton(4)) {
			spinLeftMotor();
		}
		if (rightStick.getRawButton(5)) {
			spinRightMotor();
		}
		if (rightStick.getRawButton(3)) {
			spinLeftMotor();
			spinRightMotor();
		}
		if (rightStick.getRawButton(1)) {
			loadBall();
		}
		if (rightStick.getRawButton(2)) {
			stopMotors();
		}
	}
	}
