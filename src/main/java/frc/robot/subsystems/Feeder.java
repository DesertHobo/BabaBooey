
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Feeder{

    private WPI_TalonSRX feederMotor;

    public Feeder(TalonSRX feeder) {
        this.feederMotor = (WPI_TalonSRX) feeder;
    }
    
    public void setSpeed (double speed){
        feederMotor.set(speed);
    }

}