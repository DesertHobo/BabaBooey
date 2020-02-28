package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.constants.Constants;
import frc.robot.subsystems.*;

public class ThreeBallAuto extends SequentialCommandGroup{

    /**
     * Shoots three balls and moves off the line
     * 
     * @param m_Drive Drive subsystem
     * @param m_Loader Loader subsystem
     * @param m_Shooter Shooter subsystem
     */
    public ThreeBallAuto(Drive m_Drive, Loader m_Loader, Shooter m_Shooter){
        addCommands(
            new RunShooterAndLoader(m_Shooter,m_Loader),
            new DriveDistance(Constants.AUTO_DRIVE_DISTANCE, Constants.AUTO_DRIVE_SPEED, m_Drive)
        );
    }
}