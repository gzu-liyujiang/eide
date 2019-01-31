package aenu.eide.diagnostic;

public final class CXDiagnosticJni
{
    static{
        System.loadLibrary("cx-diag");
    }
    public static native void diag_from_file(String file_path,String[] cmds,DiagnosticCallback cb);
    public static native void diag_from_source(String file_path,String source,String[] cmds,DiagnosticCallback cb);
}
