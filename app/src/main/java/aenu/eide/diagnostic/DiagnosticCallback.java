
//license wtfpl 2.0

//by aenu 2019
//   email:202983447@qq.com

package aenu.eide.diagnostic;
import java.io.File;

public interface DiagnosticCallback{
    public void onNewError(DiagnosticMessage msg);
    public void onNewWarning(DiagnosticMessage msg);
}
