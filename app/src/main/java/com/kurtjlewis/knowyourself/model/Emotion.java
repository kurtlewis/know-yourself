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
    ANGRY, STRESSED, HAPPY, ANXIOUS, SAD,  EXCITED, DEPRESSED;


    /**
     * Returns the color representation of an emotiondefined in this enum
     * @author Kurt Lewis
     * @date 2018.02.10
     * @return gets int representing a color for given emotion
     */
    public @ColorInt int getColorRepresentation() {
        int color = Color.BLACK;
        switch (this) {
            case HAPPY: color = Color.rgb(255, 255, 77);
                break;
            case ANGRY: color = Color.rgb(216,31,42);
                break;
            case SAD: color = Color.rgb(51, 171, 249);
                break;
            case ANXIOUS: color = Color.rgb(144,245,0);
                break;
            case STRESSED: color = Color.rgb(255, 165, 0);
                break;
            case EXCITED: color  = Color.rgb(174, 234, 255);
                break;
            case DEPRESSED: color = Color.rgb(150, 0, 205);
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
