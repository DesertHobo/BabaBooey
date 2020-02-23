
package frc.robot.subsystems;

import frc.robot.constants.Constants;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Elevator subsystem for climbing in the end game
 */
public class Elevator{

    /** Left motor controller for the left elevator motor */
    private CANSparkMax leftMotor;
    /** Right motor controller for the right elevator motor */
    private CANSparkMax rightMotor;
    /** Controls the piston that locks the elevator in place */
    private DoubleSolenoid lockingMechanism;

    /**
     * Initializes the elevator subsystem
     * @param left - left motor
     * @param right - right motor
     * @param locking - piston that locks the elevator
     */
    public Elevator(CANSparkMax left, CANSparkMax right, DoubleSolenoid locking){

        this.leftMotor = left;
        this.rightMotor = right;
        this.lockingMechanism = locking;

        // Restore the factory defaults on the Spark Maxes
        this.leftMotor.restoreFactoryDefaults();
        this.rightMotor.restoreFactoryDefaults();

        // Set one of the sides to inverted because of the mechanical mounting
        this.leftMotor.setInverted(false);
        this.rightMotor.setInverted(true);

        // Set the current limits on the spark maxes
        this.leftMotor.setSmartCurrentLimit(Constants.ELEVATOR_POWER_LIMIT);
        this.rightMotor.setSmartCurrentLimit(Constants.ELEVATOR_POWER_LIMIT);

    }

    /**
     * Extends the piston to lock the elevator
     */
    public void lockElevator(){
        lockingMechanism.set(Constants.ELEVATOR_LOCK);
    }

    /**
     * Retracts the piston to unlock the elevator
     */
    public void unlockElevator(){
        lockingMechanism.set(Constants.ELEVATOR_UNLOCK);
    }

    /**
     * Returns whether the elevator is mechanically locked by the piston
     * @return
     */
    public boolean isLocked(){
        return lockingMechanism.get() == Constants.ELEVATOR_UNLOCK;
    }

    /**
     * Toggles the elevator lock state
     */
    public void toggleElevatorLock(){
        // Sets the locking mechanism to the opposite of what it currently is
        lockingMechanism.set((lockingMechanism.get() == Constants.ELEVATOR_UNLOCK) ? Constants.ELEVATOR_LOCK : Constants.ELEVATOR_UNLOCK);
    }

    /**
     * Sets the speed for the elevator motors
     * 
     * @param speed The speed is based on percent output (between -1 and 1)
     */
    public void setSpeed (double speed){
        leftMotor.set(speed);
        rightMotor.set(speed);
    }

    /**
     * Returns the previously set speed
     * @return
     */
    public double getSpeed(){
        return leftMotor.get();
    }

}