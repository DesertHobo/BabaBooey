
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

//moves the entire robot left and right while on the pull up bar
public class Hook{

    //motor for moving hook left/right
    private CANSparkMax hookMotor;

    /**
     * initializes hook subsystem
     * 
     * @param hook - motor for hook
     */
    public Hook(CANSparkMax hook) {
        this.hookMotor = hook;
    }
    /**
     * sets motor speed for hook
     * 
     * @param speed - The speed based on percent output (between -1 and 1)
     */
    public void setSpeed (double speed){
        hookMotor.set(speed);
    }

}