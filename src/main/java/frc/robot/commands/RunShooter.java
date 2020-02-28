package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class RunShooter extends CommandBase{
    private final Shooter m_Shooter;
    private long startTime;
    private long timeMillis;
    
    
    public RunShooter(Shooter m_Shooter){
        this.m_Shooter = m_Shooter;
        
        
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
        m_Shooter.ShooterOn();
    }

    @Override
    public void execute() {
        timeMillis = System.currentTimeMillis() - startTime;
    }

    @Override
    public void end(boolean interrupted) {
        m_Shooter.ShooterOff();
        startTime = 0;
        timeMillis = 0;
    }
  
    /**
     * Runs the Loader for 7 Seconds
     */
    @Override
    public boolean isFinished() {
      return (timeMillis >= 10000);
    }
    

}