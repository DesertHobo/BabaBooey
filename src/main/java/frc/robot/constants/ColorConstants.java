package frc.robot.constants;

import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatch;

/**
 * An enumeration representing the possible colors
 */
public enum ColorConstants{

    /**
     * Initiates the colors with RGB values
     */
    BLUE ('B', 0.298096, 0.468262, 0.234131),
    GREEN ('G', 0.290527, 0.566895, 0.133545),
    RED ('R', 0.685059, 0.287842, 0.027832),
    YELLOW ('Y', 0.482, 0.465, 0.051),
    UNKNOWN ('\0', 0, 0, 0);

    /**
     * The color instance representative of this constant color
     */
    private Color color;

    /**
     * The ID chosen by the FMS for this color
     */
    private char ID;

    /**
     * Initializes the color with the given RGB values
     * @param name - the char chosen for this color in the FMS documentation
     * @param red - the value for red (between 0 and 1)
     * @param green - the value for green (between 0 and 1)
     * @param blue - the value for blue (between 0 and 1)
     */
    private ColorConstants (char name, double red, double green, double blue){
        this.color = ColorMatch.makeColor (red, green, blue);
        this.ID = name;
    }

    /**
     * Retrieves the color 
     * @return - the color as a instance of Color
     */
    public Color getColor(){
        return this.color;
    }
    
    /**
     * Returns the ID chosen by the FMS for this color
     * @return - the ID chosen by the FMS for this color
     */
    public char getID(){
        return this.ID;
    }

}