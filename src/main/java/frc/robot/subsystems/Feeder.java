
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.constants.Constants;

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
    /**
     * sets motor speed for intake
     * 
     */
    public void intake(){
        setSpeed(Constants.INTAKE_SPEED);
    }
    /**
     * runs the motor in the reverse direction incase where the ball might get jammed.
     * 
     */
    public void reverseIntake(){
        setSpeed(Constants.REVERSE_INTAKE_SPEED);
    }
    /**
     * stops the intake motor
     * 
     */
    public void stopIntake(double speed){
        setSpeed(Constants.STOP_MOTOR   );
    }

}