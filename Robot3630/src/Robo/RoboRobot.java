/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Robo;

/**
 *
 * @author paul
 */
public class RoboRobot {
    public RoboDrive drive = new RoboDrive();
    public RoboManipulator manipulator = new RoboManipulator();
    public RoboLineTracker lineTracker = new RoboLineTracker();
    public RoboAutopilot autoPilot = new RoboAutopilot();
    public RoboCopilot coPilot = new RoboCopilot();
    public RoboDriverStation driverStation = new RoboDriverStation();
    public RoboMiniBot miniBot = new RoboMiniBot();
    double simDt = 0.020;

    public void start(){
        drive.start();
        lineTracker.start();
        manipulator.start();
        // autoPilot.start();
    }
    public void handle(){
        drive.handle();
        lineTracker.handle();
        manipulator.simulate();
        manipulator.handle();
        autoPilot.handle();
        coPilot.handle();
    }
    //void simulate(){
        // simulation tick
        //drive.simulate();
        //lineTracker.simulate();
        //manipulator.simulate();
    }



