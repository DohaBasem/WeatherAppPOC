package com.example.dohabasem.robustaweatherapp.Utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Doha Basem on 9/16/2017.
 */

public class GlobalFunctions {

    public static boolean requestPermission(String permission,int requestCode, final Activity activity){
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>= Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {

                    //This is called if user has denied the permission before
                    //In this case I am just asking the permission again
                    ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
                    return false;
                } else {

                    ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
                    return false;
                }
            } else {
                return true;
            }
        }
        return true;
    }
}
