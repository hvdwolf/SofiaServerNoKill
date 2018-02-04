package org.hvdw.sofiaservernokill;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.File;

public class Utils {

    /**
     * workaround for android N and later - preference files have to be in MODE_PRIVATE
     * so we need to override the permissions after each save so that Xposed part can read them.
     *
     * @param ctxt
     */
    @SuppressLint("SetWorldReadable")
    public static void fixPreferencePermission(Context ctxt){
        File prefsDir = new File(ctxt.getApplicationInfo().dataDir, "shared_prefs");
        File prefsFile = new File(prefsDir, "preferences.xml");
        if (prefsFile.exists()) {
            prefsFile.setReadable(true, false);
        }

    }
}