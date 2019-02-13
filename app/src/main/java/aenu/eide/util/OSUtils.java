package aenu.eide.util;
import android.content.Context;
import android.app.ActivityManager;
import java.util.List;

public class OSUtils
{
    static{System.loadLibrary("os-utils");}
    public static native int fork();
    public static native void setenv(String k,String v,int r);
    public static native int chmod(String path,int mode);
    public static native int mkfifo(String path,int mode);
	
	/**
     * @return null may be returned if the specified process not found
     */
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }
}
