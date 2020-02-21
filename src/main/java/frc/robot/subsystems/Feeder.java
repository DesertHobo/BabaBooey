
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Feeder{
    
    //motor for the feeder
    private WPI_TalonSRX feederMotor;

    /**
    * initializes feeder subsystem
    *
    * @param feeder - motor for feeder
    */   
    public Feeder(TalonSRX feeder) {
        this.feederMotor = (WPI_TalonSRX) feeder;
    }
    /**
     * sets motor speed for feeder
     * 
     * @param speed - motor speed
     */
    public void setSpeed (double speed){
        feederMotor.set(speed);
    }

}