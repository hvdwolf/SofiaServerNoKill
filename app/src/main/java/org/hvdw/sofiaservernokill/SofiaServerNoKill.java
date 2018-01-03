package org.hvdw.sofiaservernokill;

import android.util.Log;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class SofiaServerNoKill implements IXposedHookLoadPackage {
	public static final String TAG = "SofiaServerNoKill";
	public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

		if (!lpparam.packageName.equals("com.syu.ms")) return;

		findAndHookMethod("com.syu.ms.app.ToolkitApp", lpparam.classLoader, "killAllAppButSome", new XC_MethodHook() {
			//@Override
			protected void replaceHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				Log.d(TAG, "skipping method killAllAppButSome");
				param.setResult(null);
			}
		});
	}
}
