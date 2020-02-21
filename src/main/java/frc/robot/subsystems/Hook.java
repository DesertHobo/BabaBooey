
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

public class Hook{

    private CANSparkMax hookMotor;

    /**
     * 
     * @param hook - motor for hook
     */
    public Hook(CANSparkMax hook) {
        this.hookMotor = hook;
    }
    /**
     * 
     * @param speed - motor speed
     */
    public void setSpeed (double speed){
        hookMotor.set(speed);
    }

}