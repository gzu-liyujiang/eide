
//license wtfpl 2.0

//by aenu 2019
//   email:202983447@qq.com

package aenu.eide.diagnostic;
import java.util.Map;
import java.io.File;
import java.util.List;
import java.util.HashMap;

public class ProjectDiagnostic
{
    public final Map<File,List<DiagnosticInfo>> errors=new HashMap<>();
    public final Map<File,List<DiagnosticInfo>> warnings=new HashMap<>();
}
