package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.Constants;
import frc.robot.subsystems.Loader;

public class RunLoader extends CommandBase{
    private final Loader m_Loader;
    private long startTime;
    private long timeMillis;
    
    
    public RunLoader(Loader m_Loader){
        this.m_Loader = m_Loader;
        
        
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
        m_Loader.setSpeed(Constants.LOADER_SPEED);
    }

    @Override
    public void execute() {
        timeMillis = System.currentTimeMillis() - startTime;
    }

    @Override
    public void end(boolean interrupted) {
        m_Loader.setSpeed(0);
        startTime = 0;
        timeMillis = 0;
    }
  
    /**
     * Runs the Loader for 7 Seconds
     */
    @Override
    public boolean isFinished() {
      return (timeMillis >= 7000);
    }
    

}