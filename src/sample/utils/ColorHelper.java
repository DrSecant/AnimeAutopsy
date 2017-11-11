package sample.utils;

import javafx.scene.paint.Color;

/**
 * Created by Flameborg the Bold on 9/19/2017.
 */
public class ColorHelper {

    /**
     * Takes in color information and determines whether it is a cool, warm, or neutral color
     * @param hue color value - ranges from 0 to 360
     * @param saturation color value - ranges from 0.0 to 1.0
     * @param brightness color value - ranges from 0.0 to 1.0
     * @return String color temperature
     */
    public static String colorTemp(double hue, double saturation, double brightness)
    {
        if(saturation <= .051 || brightness <= .31)
        {
            return "Neutral";
        }
        else
        {
            if(hue >= 66 && hue <= 294)
            {
                return "Cool";
            }
            else
            {
                return "Warm";
            }
        }
    }

    public static String toHexCode( Color color )
    {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }
}
