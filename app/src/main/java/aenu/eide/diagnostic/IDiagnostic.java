package aenu.eide.diagnostic;
import java.util.List;
import java.io.File;

public interface IDiagnostic{
    //public File file();
    public void diag(DiagnosticCallback callback);
}
