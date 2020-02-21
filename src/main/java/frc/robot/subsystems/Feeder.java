
package frc.robot.subsystems;
import edu.wpi.first.wpilibj.SpeedController;

//picks up balls from the ground and feeds them into the loader
public class Feeder{

    //motor for the feeder
    private SpeedController feederMotor;

    /**
    * initializes feeder subsystem
    *
    * @param feeder - motor for feeder
    */   
    public Feeder(SpeedController feeder) {
        this.feederMotor = feeder;
    }
    /**
     * sets motor speed for feeder
     * 
     * @param speed - The speed is based on percent output (between -1 and 1)
     */
    public void setSpeed (double speed){
        feederMotor.set(speed);
    }

}