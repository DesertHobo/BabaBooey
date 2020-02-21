package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
/**
 * spins the control panel
 */
public class Spinner {

    // motor for moving spinner
    private SpeedController SpinnerMotor;

    /**
     * initializes spinner subsystem
     * 
     * @param spinner - motor for spinner
     */
    public Spinner(SpeedController spinner) {
        this.SpinnerMotor = spinner;
    }

    /**
     * sets the speed for the spinner motor
     * 
     * @param speed - speed between -1 and 1
     */
    public void setSpeed(double speed) {
       SpinnerMotor.set(speed);
    }

}