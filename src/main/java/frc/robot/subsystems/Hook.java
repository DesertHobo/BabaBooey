
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

public class Hook{

    private CANSparkMax hookMotor;

    public Hook(CANSparkMax hook) {
        this.hookMotor = hook;
    }

    public void setSpeed (double speed){
        hookMotor.set(speed);
    }

}