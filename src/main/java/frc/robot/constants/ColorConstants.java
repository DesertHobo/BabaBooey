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
    BLUE ('B', 0.143, 0.427, 0.429),
    GREEN ('G', 0.197, 0.561, 0.240),
    RED ('R', 0.561, 0.232, 0.114),
    YELLOW ('Y', 0.361, 0.524, 0.113),
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