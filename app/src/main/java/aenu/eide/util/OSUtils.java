package aenu.eide.util;

public class OSUtils
{
    static{System.loadLibrary("os-utils");}
    public static native int fork();
    public static native void setenv(String k,String v,int r);
    public static native int chmod(String path,int mode);
    public static native int mkfifo(String path,int mode);
}
