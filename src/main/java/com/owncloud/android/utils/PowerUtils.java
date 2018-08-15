package com.owncloud.android.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;

public class PowerUtils {

    /**
     * Checks if device is in power save mode. For older devices that do not support this API, returns false.
     * Includes check to see if app is excempted from battery optimisations.
     * @return true if it is, false otherwise.
     */
    // TODO: This probably needs to increase to Build.VERSION_CODES.M - not sure what the consequences are
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static boolean isPowerSaveMode(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

            // Check if the app is excepted from battery optimisations. If not supported, assume false.
            // PowerManager.isIgnoringBatteryOptimizations needs SDK 23 or higher
            boolean isIgnoringBatteryOptimizations = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                isIgnoringBatteryOptimizations = powerManager.isIgnoringBatteryOptimizations( (String) context.getPackageName() );
            }

            return powerManager != null && powerManager.isPowerSaveMode() && ! isIgnoringBatteryOptimizations;
        }

        // For older versions, we just say that device is not in power save mode
        return false;
    }
}
