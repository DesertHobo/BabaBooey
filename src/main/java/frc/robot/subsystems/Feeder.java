
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;

//picks up balls from the ground and feeds them into the loader
public class Feeder{

    //motor for the feeder
    private SpeedController feederMotor;

    /**
    * initializes feeder subsystem
    *
    * @param feeder - motor for feeder
    */   
    public Feeder(SpeedController feeder) {
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