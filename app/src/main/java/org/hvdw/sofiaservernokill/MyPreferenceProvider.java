package org.hvdw.sofiaservernokill;

import com.crossbowffs.remotepreferences.RemotePreferenceProvider;


public class MyPreferenceProvider extends RemotePreferenceProvider {
    public MyPreferenceProvider() {
        super("org.hvdw.sofiaservernokill.preferences", new String[] {"preferences"});
    }
}