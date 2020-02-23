
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.constants.Constants;

/**
 * Subsystem for collecting the balls from the ground and feeding into the loader
 */
public class Intake{

    /** Motor controller for the feeder motor */
    private SpeedController feederMotor;

    /**
    * Initializes feeder subsystem
    *
    * @param feeder - motor for feeder
    */   
    public Intake(SpeedController feeder) {
        this.feederMotor = feeder;
    }

    /**
     * Sets motor speed for feeder
     * @param speed - The speed based on percent output (between -1 and 1)
     */
    public void setSpeed (double speed){
        feederMotor.set(speed);
    }
    
    /**
     * Sets the feeder to intake at a constant pre-determined speed
     */
    public void intake(){
        setSpeed(Constants.INTAKE_FORWARD_SPEED);
    }
 
    /**
     * Sets the feeder to outtake at a constant pre-determined speed
     */
    public void reverseIntake(){
        setSpeed(Constants.INTAKE_REVERSE_SPEED);
    }

    /**
     * Sets the feeder to stop
     */
    public void stopIntake(){
        setSpeed(Constants.INTAKE_STOP_MOTOR);
    }

}