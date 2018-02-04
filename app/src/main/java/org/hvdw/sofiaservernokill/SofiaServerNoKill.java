package org.hvdw.sofiaservernokill;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.util.Log;

import com.crossbowffs.remotepreferences.RemotePreferences;


import de.robv.android.xposed.*;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class SofiaServerNoKill implements IXposedHookLoadPackage {
	public static final String TAG = "SofiaServerNoKill";

	private SharedPreferences pref;
	private LoadPackageParam lpparam;

	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
		XposedBridge.log("Loaded app: " + lpparam.packageName);
		if (!lpparam.packageName.equals("com.syu.ms"))
			return;

		Context context = ContextUtils.getSystemContext();
		if (null == context) {
			Log.w(TAG,"null context");
		XposedBridge.log(TAG + " null context");
				return;
		}

		if (android.os.Build.VERSION.SDK_INT>23){
			// we need content provider to access preferences because of strict permissions on Nougat.
			pref = new RemotePreferences(context, "org.hvdw.sofiaservernokill", "preferences");
		}
		else {
			pref = new XSharedPreferences(SofiaServerNoKill.class.getPackage().getName(), "preferences");
		}

		findAndHookMethod("app.ToolkitApp", lpparam.classLoader, "killAppWhenSleep", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				XposedBridge.log("Loaded app: " + lpparam.packageName + "skipping method killAppWhenSleep");
				Log.d(TAG, "skipping method killAppWhenSleep");
				param.setResult(null);
			}
		});

/*		findAndHookMethod("app.ToolkitApp", lpparam.classLoader, "killAllAppButSome", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				XposedBridge.log("Loaded app: " + lpparam.packageName + "skipping method killAllAppButSome");
				Log.d(TAG, "skipping method killAllAppButSome");
				param.setResult(null);
			}
		});
*/
	}


/*************************** Preferences stuff **********************/
	public String getPrefString(String key, String defaultvalue){
		String res = pref.getString(key,defaultvalue);
		if (res.equals("")) {
			return defaultvalue;
		}
			return res;
		}


}
