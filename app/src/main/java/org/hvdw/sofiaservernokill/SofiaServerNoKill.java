package org.hvdw.sofiaservernokill;

import android.util.Log;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class SofiaServerNoKill implements IXposedHookLoadPackage {
	public static final String TAG = "SofiaServerNoKill";
	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
		XposedBridge.log("Loaded app: " + lpparam.packageName);
		if (!lpparam.packageName.equals("com.syu.ms"))
			return;

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
}
