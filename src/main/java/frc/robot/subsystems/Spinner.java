package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.constants.ColorConstants;
import frc.robot.constants.Constants;

/**
 * Subsystem for controlling the spinner for manipulating the control panel
 */
public class Spinner {

    /** The motor controller for spinning the control panel */
    private SpeedController spinnerMotor;
    /** The Rev ColorSensor v3 for detecting the current color under the system */
    private ColorSensorV3 colorSensor = new ColorSensorV3(Constants.I2C_PORT);
    /** The color matches matches the colors from the sensor to one of the color presets */
    private ColorMatch colorMatcher = new ColorMatch();

    /** The double solenoid for the piston that lowers the spinner */
    private DoubleSolenoid piston;

    /**
     * Initializes the control-panel spinner subsystem
     * @param SpinnerMotor - the motor controller for the spinner
     * @param piston - the piston for lowering the spinner
     */
    public Spinner(SpeedController spinnerMotor, DoubleSolenoid piston){

        // Stores the motor in the instance
        this.spinnerMotor = spinnerMotor;
        // Retrives the color constants from the enumeration and adds them to the color matcher
        colorMatcher.addColorMatch(ColorConstants.BLUE.getColor());;
        colorMatcher.addColorMatch(ColorConstants.GREEN.getColor());
        colorMatcher.addColorMatch(ColorConstants.RED.getColor());
        colorMatcher.addColorMatch(ColorConstants.YELLOW.getColor());
        // Stores the piston in the instance
        this.piston = piston;

    }

    /**
     * Detects the color from the color sensor
     * @return - the color detected by the color sensor as a ColorConstants
     */
    public ColorConstants detectColor(){

        //updates the detected color to the most recent value recorded by the color sensor
        Color rawColor = colorSensor.getColor();

        // Attempts to match the rawColor to the target colors
        ColorMatchResult match = colorMatcher.matchClosestColor(rawColor);

        /*
         This takes the most recent color value read by the color sensor that was stored in rawColor and compares 
         that color value to the four added colors added into the color matcher. Whichever added color value (red, blue, green, yellow) 
         is closest to the one read in the latest run of the color sensor, the matcher will return the cooresponding value.
        */ 
        if (match.color == ColorConstants.BLUE.getColor()) {
            return ColorConstants.BLUE;
        } else if (match.color ==  ColorConstants.RED.getColor()) {
            return ColorConstants.RED;
        } else if (match.color ==  ColorConstants.GREEN.getColor()) {
            return ColorConstants.GREEN;
        } else if (match.color == ColorConstants.YELLOW.getColor()) {
            return ColorConstants.YELLOW;
        }

        // Otherwise, an unknown color was detected
        return ColorConstants.UNKNOWN;

    }

      /**
     * Sets the speed for the spinner motor
     * 
     * @param speed - Speeds are between -1 and 1. These values are measured by percent output.
     */
    public void spinnerOn() {
        spinnerMotor.set(Constants.SPINNER_SPEED);
    }
    public void spinnerOff() {
        spinnerMotor.set(0);
    }

    /**
     * Moves the piston on the motor arm to push the arm down
     */
    public void openArm(){
        piston.set(Constants.CONTROL_PANEL_OPEN_ARM);
    }

    /**
     * Moves the piston on the motor arm to pull the arm up
     */
    public void closeArm(){
        piston.set(Constants.CONTROL_PANEL_CLOSE_ARM);
    }

    /**
     * Returns whether or not the arm of the spinner is extended
     * @return
     */
    public boolean isExtended(){
        return piston.get() == Constants.CONTROL_PANEL_OPEN_ARM;
    }

    /**
     * Toggles whether the arm is extended
     */
    public void toggleArmExtended(){
        // Sets the arm extension to the opposite of what it currently is
        piston.set((piston.get() == Constants.CONTROL_PANEL_OPEN_ARM) ? Constants.CONTROL_PANEL_CLOSE_ARM : Constants.CONTROL_PANEL_OPEN_ARM);
    }

}