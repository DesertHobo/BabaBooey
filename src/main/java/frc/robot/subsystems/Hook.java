
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

/**
 * Subsystem for horizontal movement on the climbing bar
 */
public class Hook{

    /** Motor controller for moving horizontally on the bar */
    private CANSparkMax hookMotor;

    /**
     * Initializes hook subsystem
     * 
     * @param hook - motor controller for hook horizontal movement
     */
    public Hook(CANSparkMax hook) {
        this.hookMotor = hook;
    }
    /**
     * Sets motor speed for hook (to achieve horizontal movement)
     * @param speed - The speed based on percent output (between -1 and 1)
     */
    public void setSpeed (double speed){
        hookMotor.set(speed);
    }

}