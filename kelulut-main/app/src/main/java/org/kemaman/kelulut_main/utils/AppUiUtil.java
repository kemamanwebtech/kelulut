package org.kemaman.kelulut_main.utils;

import android.content.Context;

/**
 * Class to hold App's utility functions
 *
 */

public class AppUiUtil {

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
