
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

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
     * @param speed - motor speed
     */
    public void setSpeed (double speed){
        hookMotor.set(speed);
    }

}