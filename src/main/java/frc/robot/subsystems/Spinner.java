package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.constants.ColorConstants;
import frc.robot.constants.Constants;
import frc.robot.constants.WiringConstants;

/**
 * spins the control panel
 */
public class Spinner {

    // Initalize motor for moving spinner along with the color sensor classes
    private SpeedController SpinnerMotor;
    private ColorSensorV3 ColorSensor;
    private ColorMatch ColorMatcher;

    //Initalize the DoubleSolenoid
    private DoubleSolenoid motorArm;

    //Variable to store the latest value recorded by the color sensor
    private ColorConstants detectedColor;

    /**
     * initializes spinner subsystem
     * 
     * @param spinner - motor for spinner
     */
    public Spinner(SpeedController SpinnerMotor) {
        this.SpinnerMotor = SpinnerMotor;
        //Retrives the color constants and adds them to the color matcher
        ColorMatcher.addColorMatch(ColorConstants.BLUE.getColor());;
        ColorMatcher.addColorMatch(ColorConstants.GREEN.getColor());
        ColorMatcher.addColorMatch(ColorConstants.RED.getColor());
        ColorMatcher.addColorMatch(ColorConstants.YELLOW.getColor());

        motorArm = new DoubleSolenoid(WiringConstants.CONTROL_PANEL_A, WiringConstants.CONTROL_PANEL_B);

    }

  
    public ColorConstants detectColor(){

        //updates the detected color to the most recent value recorded by the color sensor
        Color rawColor = ColorSensor.getColor();

        // Attempts to match the rawColor to the target colors
        ColorMatchResult match = ColorMatcher.matchClosestColor(rawColor);

        /*
         This takes the most recent color value read by the color sensor that was stored in detectedColor and compares 
         that color value to the four added colors added into the color matcher. Whichever added color value (red, blue, green, yellow) 
         is closest to the one read in the latest run of the color sensor, the matcher will associate that latest value with that added 
         color.
        */ 
        /*
         Takes the color matchers value and assgins the colorString the value from red, blue, green, or yellow
         */
        if (match.color == ColorConstants.BLUE.getColor()) {
            this.detectedColor = ColorConstants.BLUE;
        } else if (match.color ==  ColorConstants.RED.getColor()) {
            this.detectedColor = ColorConstants.RED;
        } else if (match.color ==  ColorConstants.GREEN.getColor()) {
            this.detectedColor = ColorConstants.GREEN;
        } else if (match.color == ColorConstants.YELLOW.getColor()) {
            this.detectedColor = ColorConstants.YELLOW;
        } else {
            this.detectedColor = ColorConstants.UNKNOWN;
        }
        
        // Returns the detected color
        return this.detectedColor;

    }

      /**
     * Sets the speed for the spinner motor
     * 
     * @param speed - Speeds are between -1 and 1. These values are measured by percent output.
     */
    public void setSpeed(double speed) {
       SpinnerMotor.set(speed);
    }

    /**
     * Moves the piston on the motor arm to push the arm down
     */
    public void openArm(){
        motorArm.set(Constants.CONTROL_PANEL_OPEN_ARM);
    }

    /**
     * Moves the piston on the motor arm to pull the arm up
     */
    public void closeArm(){
        motorArm.set(Constants.CONTROL_PANEL_CLOSE_ARM);
    }

    

}