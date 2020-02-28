package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.Constants;
import frc.robot.subsystems.Loader;
import frc.robot.subsystems.Shooter;

public class RunShooterAndLoader extends CommandBase{
    private final Shooter m_Shooter;
    private final Loader m_Loader;
    private long startTime;
    private long timeMillis;
    
    
    public RunShooterAndLoader(final Shooter m_Shooter, final Loader m_Loader) {
        this.m_Shooter = m_Shooter;
        this.m_Loader = m_Loader;

    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
        m_Shooter.ShooterOn();
        
    }

    /**
     * Get the time past
     * Enable loader if 3 sec has passed.
     */
    @Override
    public void execute() {
        timeMillis = System.currentTimeMillis() - startTime;
        if(timeMillis >= 3000)
            m_Loader.setSpeed(Constants.LOADER_SPEED);
    }

    /**
     * Sets everything back to default
     */
    @Override
    public void end(final boolean interrupted) {
        m_Shooter.ShooterOff();
        m_Loader.setSpeed(0);
        startTime = 0;
        timeMillis = 0;
    }
  
    /**
     * Ends the command after 10 seconds
     */
    @Override
    public boolean isFinished() {
      return (timeMillis >= 10000);
    }
    

}