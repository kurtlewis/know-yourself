package com.kurtjlewis.knowyourself.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.ColorInt;



/**
 * Created by kurt on 2/10/18.
 * @author Kurt Lewis
 * Enum class that defines the different emotions for the Feeling object
 */

public enum Emotion {
    HAPPY, ANGRY, SAD, ANXIOUS, STRESSED, EXCITED, DEPRESSED;


    /**
     * Returns the color representation of an emotiondefined in this enum
     * @author Kurt Lewis
     * @date 2018.02.10
     * @return gets int representing a color for given emotion
     */
    public @ColorInt int getColorRepresentation() {
        int color = Color.BLACK;
        switch (this) {
            case HAPPY: color = Color.YELLOW;
                break;
            case ANGRY: color = Color.RED;
                break;
            case SAD: color = Color.BLUE;
                break;
            case ANXIOUS: color = Color.GREEN;
                break;
            case STRESSED: color = Color.rgb(255, 165, 0);
                break;
            case EXCITED: color  = Color.rgb(155, 193, 255);
                break;
            case DEPRESSED: color = Color.rgb(172, 20, 183);
                break;
            default:
                throw new RuntimeException("Unhandled enum value.");
        }
        return color;
    }

    /**
     * Returns the string representation
     * @author Kurt Lewis
     * @date 2018.02.10
     * @param context - context of view displaying the enum value
     * @return returns localized string representing the enum value
     */
    public String getLocalizedString(Context context) {
        Resources res = context.getResources();
        return res.getString(res.getIdentifier(this.name(), "string", context.getPackageName()));
    }


}
