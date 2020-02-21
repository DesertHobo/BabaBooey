
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
/**
 * raises the hook to grab the pull up bar
 * 
 */
public class Elevator{
    //left motor controlling the elevator
    private CANSparkMax leftMotor;
    //right motor controlling elevator
    private CANSparkMax rightMotor;
    //piston locking the elevator in place
    private DoubleSolenoid lockingMechanism;

    /**
     * initializes the elevator subsystem
     * 
     * @param left - left motor
     * @param right - right motor
     * @param locking - piston
     */
    public Elevator(CANSparkMax left, CANSparkMax right, DoubleSolenoid locking){
        this.leftMotor = left;
        this.rightMotor = right;
        this.lockingMechanism = locking;
    }

    //extends the piston to lock the elevator
    public void lockElevator(){
        lockingMechanism.set(Value.kForward);
    }

    //retracts the piston to unlock the elevator
    public void unlockElevator(){
        lockingMechanism.set(Value.kReverse);
    }

    /**
     * sets the speed for the elevator motors
     * 
     * @param speed The speed is based on percent output (between -1 and 1)
     */
    public void setSpeed (double speed){
        leftMotor.set(speed);
        rightMotor.set(speed);
    }

}