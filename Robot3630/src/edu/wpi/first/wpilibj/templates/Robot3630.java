/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import Robo.RoboTask;
import Robo.RoboRobot;
import Robo.RoboTrimmer;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO.EnhancedIOException;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot3630 extends IterativeRobot {
    // autonomus mode variables
    Timer autoTimer;
    int timeInSeconds;
    // the power profiles for the straight and forked robot path. They are
    // different to let the robot drive more slowly as the robot approaches
    // the fork on the forked line case.
    double lastDashDisplayTime = 0;

    private RoboRobot robot;
    private RoboTrimmer trimmer = new RoboTrimmer();


    public Robot3630() throws EnhancedIOException {
        // create the main robot instance
        robot = new RoboRobot();
        // give all tasks access to it (set the robot member variable)
        RoboTask.robot = robot;
        // give the trimmer access to the robot
        trimmer.robot = robot;

        autoTimer = new Timer();
        robot.drive.resetGyro();
    }

    public void robotInit() {
        SmartDashboard.init();
        SmartDashboard.log("Initializing...", "System State");
        robot.start();
    }

    public void SmartDashboardInt() {
        SmartDashboard.log(robot.lineTracker.left, "left sensor input");
        SmartDashboard.log(robot.lineTracker.center, "mid sensor input");
        SmartDashboard.log(robot.lineTracker.right, "right sensor input");
    }

    public void autonomousInit() {
        SmartDashboard.useBlockingIO(true);
        SmartDashboard.log("Auto", "System State");
        autoTimer.reset();
        autoTimer.start();
        robot.start();
        robot.autoPilot.start();
    }

    // NOTE: the documentation is misleading - this is called ONLY when there is some new sensor data
    // (if nothing changes this does NOT get called
    public void autonomousContinuous() {
        robot.handle();
        robot.autoPilot.handle();
    }

    /**
     * This function is called periodically during autonomous (approx. 20msec)
     */
    public void autonomousPeriodic() {
        //updateAutoDashboard();
    }



    private void updateAutoDashboard() {
        // update only if it hasn't been updated for at least 0.1 sec

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopInit() {
        robot.start();
        robot.miniBot.start();
        robot.coPilot.start();
    }

    public void teleopPeriodic() {
        // SmartDashboard.log((robot.lineTracker.left?"(X)":"( )") + (robot.lineTracker.center?"-(X)-":"-( )-") + (robot.lineTracker.right?"(X)":"( )"), "Line tracker:");
        // SmartDashboard.log(robot.drive.readGyro(), "Gyro:");
    }

    public void teleopContinuous() {
        // Read Joysticks and set drive direction
        robot.handle();
        robot.miniBot.handle();
        robot.coPilot.handle();
    }

    public void disabledInit() {
        robot.autoPilot.stop();
        robot.coPilot.stop();
    }

    public void disabledContinuous() { // robot is disabled, stop driving
    }

    public void disabledPeriodic() { // robot is disabled, stop driving
        // handle trimmer adjustments

        if(robot.driverStation.leftDriveStick.getRawButton(6)){
            trimmer.next();
            // wait for button release
            while(robot.driverStation.leftDriveStick.getRawButton(6));
        }
        if(robot.driverStation.leftDriveStick.getRawButton(7)){
            trimmer.previous();
            // wait for button release
            while(robot.driverStation.leftDriveStick.getRawButton(7));
        }
        if(robot.driverStation.leftDriveStick.getRawButton(11)){
            trimmer.change(+0.1);
            // wait for button release
            while(robot.driverStation.leftDriveStick.getRawButton(11));
        }
        if(robot.driverStation.leftDriveStick.getRawButton(10)){
            trimmer.change(-0.1);
            // wait for button release
            while(robot.driverStation.leftDriveStick.getRawButton(10));
        }
        if(robot.driverStation.leftDriveStick.getRawButton(9)){
            trimmer.change(+0.01);
            // wait for button release
            while(robot.driverStation.leftDriveStick.getRawButton(9));
        }
        if(robot.driverStation.leftDriveStick.getRawButton(8)){
            trimmer.change(-0.01);
            // wait for button release
            while(robot.driverStation.leftDriveStick.getRawButton(8));
        }


        SmartDashboard.log(trimmer.name(), "Trimmer variable:");
        SmartDashboard.log(trimmer.value(), "Trimmer value:");
        SmartDashboard.log(robot.drive.readGyro(), "Gyro:");

    }
}
