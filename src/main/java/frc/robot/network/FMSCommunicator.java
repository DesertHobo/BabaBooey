package frc.robot.network;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.constants.ColorConstants;

public class FMSCommunicator{

  public ColorConstants getFMSColor(){

    // String to read the gameData in to
    String gameData;
    // Reads the game data from the driver station
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0)
    {
      char gameChar = gameData.charAt(0);
      if()
      switch (gameData.charAt(0))
      {
        case ColorConstants.BLUE.ID:
          //Blue case code
          break;
        case 'G' :
          //Green case code
          break;
        case 'R' :
          //Red case code
          break;
        case 'Y' :
          //Yellow case code
          break;
        default :
          //This is corrupt data
          break;
      }
    } else {
      //Code for no data received yet
    }

  }

}