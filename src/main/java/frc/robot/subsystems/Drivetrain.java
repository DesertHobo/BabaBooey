package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain {

    private CANSparkMax leftMotor1;
    private CANSparkMax leftMotor2;
    private CANSparkMax leftMotor3;
    private CANSparkMax rightMotor1;
    private CANSparkMax rightMotor2;
    private CANSparkMax rightMotor3;
    private DifferentialDrive m_drive;
    /**
     * 
     * @param l1 -- L1 -L3 takes in the left motor controllers of the drivetrain
     * @param l2
     * @param l3
     * @param r1 -- R1 -R3 takes in the right motor controllers of the drivetrain
     * @param r2
     * @param r3
     */
    public Drivetrain(CANSparkMax l1, CANSparkMax l2, CANSparkMax l3 , 
    CANSparkMax r1 , CANSparkMax r2 , CANSparkMax r3) {
        this.leftMotor1 = l1;
        this.leftMotor2 = l2;
        this.leftMotor3 = l3;
        this.rightMotor1 = r1;
        this.rightMotor2 = r2;
        this.rightMotor3 = r2;

        leftMotor1.restoreFactoryDefaults();
        leftMotor2.restoreFactoryDefaults();
        leftMotor3.restoreFactoryDefaults();

        rightMotor1.restoreFactoryDefaults();
        rightMotor2.restoreFactoryDefaults();
        rightMotor3.restoreFactoryDefaults();

        //R1 is the master on the right side
        rightMotor2.follow(rightMotor1);
        rightMotor3.follow(rightMotor1);

        //L1 is the master on the left side
        leftMotor2.follow(rightMotor1);
        leftMotor3.follow(rightMotor1);

        m_drive = new DifferentialDrive(leftMotor1, rightMotor1);
    }
    public void arcadeDrive(XboxController pilot){
        m_drive.arcadeDrive(pilot.getY(), -pilot.getX(), true);
    }
}
