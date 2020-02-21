
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;

//storage for balls before being fed into the shooter
public class Loader {

    //motor for vertical belt
    private SpeedController loaderVertical;
    //motor for horizontal belt
    private SpeedController loaderHorizontal;

    /**
     * initializes the loader subsystem
     * 
     * @param vertical
     * @param horizontal
     */
    public Loader(SpeedController vertical, SpeedController horizontal) {
        this.loaderVertical = (WPI_TalonSRX) vertical;
        this.loaderHorizontal = (WPI_TalonSRX) horizontal;
    }

    /**
     * sets speed for both belts
     * 
     * @param speed
     */
    public void setSpeed(double speed) {
        loaderVertical.set(speed);
        loaderHorizontal.set(speed);
    }
}