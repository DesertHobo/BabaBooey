
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Elevator{

    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;

    private DoubleSolenoid lockingMechanism;

    public Elevator(CANSparkMax left, CANSparkMax right, DoubleSolenoid locking){
        this.leftMotor = left;
        this.rightMotor = right;
        this.lockingMechanism = locking;
    }

    public void lockElevator(){
        lockingMechanism.set(Value.kForward);
    }

    public void unlockElevator(){
        lockingMechanism.set(Value.kReverse);
    }

    public void setSpeed (double speed){
        leftMotor.set(speed);
        rightMotor.set(speed);
    }

}