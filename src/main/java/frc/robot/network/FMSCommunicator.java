package frc.robot.network;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.constants.ColorConstants;

public class FMSCommunicator{

  /**
   * Gets the color "as is" from the FMS
   */
  private ColorConstants getFMSColor(){

    // String to read the gameData in to
    String gameData;
    // Reads the game data from the driver station
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    // Check if there was game data
    if(gameData.length() > 0)
    {
      // Retrieve the only char at the string
      char gameChar = gameData.charAt(0);

      // Compares the char to see which color was chosen
      if(gameChar == ColorConstants.BLUE.getID()){
        return ColorConstants.BLUE;
      }
      if (gameChar == ColorConstants.GREEN.getID()){
        return ColorConstants.GREEN;
      }
      if (gameChar == ColorConstants.RED.getID()){
        return ColorConstants.RED;
      }
      if (gameChar == ColorConstants.YELLOW.getID()){
        return ColorConstants.YELLOW;
      }
    } 

    // If hasn't returned thus far, then must've been an unknown color
    return ColorConstants.UNKNOWN;

  }

  /**
   * Gets the target color from the FMS after transforming it to match the color we're seeking
   * @return
   */
  public ColorConstants getTargetColor(){

    // Retrieve the raw color from the FMS
    ColorConstants rawColor = getFMSColor();

    // If the color that the FMS wants is blue, then we're looking for red
    if (rawColor == ColorConstants.BLUE){
      return ColorConstants.RED;
    }

    // If the color that the FMS wants is green, then we're looking for yellow
    if (rawColor == ColorConstants.GREEN){
      return ColorConstants.YELLOW;
    }

    // If the color that the FMS wants is red, then we're looking for blue
    if (rawColor == ColorConstants.RED){
      return ColorConstants.BLUE;
    }

    // If the color that the FMS wants is yellow, then we're looking for green
    if (rawColor == ColorConstants.YELLOW){
      return ColorConstants.GREEN;
    }

    // If the color is unknown, the color will still be unkown
    return ColorConstants.UNKNOWN;

  }

}