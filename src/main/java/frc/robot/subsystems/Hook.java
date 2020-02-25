
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import frc.robot.constants.Constants;

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

        // Restore the motor controller to factory defaults
        this.hookMotor.restoreFactoryDefaults();

        // Update the smart power limit on the hook motor controller
        this.hookMotor.setSmartCurrentLimit(Constants.HOOK_POWER_LIMIT);
    }

    /**
     * Moves the hook motor left at a predetermined speed
     */
    public void moveLeft (){
        hookMotor.set(Constants.HOOK_LEFT_SPEED);
    }

    /**
     * Moves the hook motor right at a predetermined speed
     */
    public void moveRight (){
        hookMotor.set(Constants.HOOK_RIGHT_SPEED);
    }

    /**
     * Stops the hook
     */
    public void stop(){
        hookMotor.set(0);
    }

    /**
     * Returns the previously set speed for the hook
     * @return
     */
    public double getHookSpeed(){
        return hookMotor.get();
    }

        /**
     * Sets whether or not brake mode is enabled
     * @param enabled
     */
    public void setBrakeMode (boolean enabled){

        this.hookMotor.setIdleMode(enabled? IdleMode.kBrake : IdleMode.kCoast);

    }

}