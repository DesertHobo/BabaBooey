
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Controls the storage for balls before being fed into the shooter
*/
public class Loader {

    /** The motor controller for vertical ball movement */
    private SpeedController loaderVertical;
    /** The motor controller for horizontal ball movement */
    private SpeedController loaderHorizontal;

    /**
     * Initializes the loader subsystem with vertical and horizontal motor controlelrs
     * @param vertical - the motor controller for the vertical belt
     * @param horizontal - the motor controller for the horizontal belt
     */
    public Loader(SpeedController vertical, SpeedController horizontal) {
        this.loaderVertical = vertical;
        this.loaderHorizontal = horizontal;
    }

    /**
     * Sets speed for both belts to be the same
     * @param speed - The speed based on percent output (between -1 and 1) to be set to both belts. 
     */
    public void setSpeed(double speed) {
        loaderVertical.set(speed);
        loaderHorizontal.set(speed);
    }
    
}