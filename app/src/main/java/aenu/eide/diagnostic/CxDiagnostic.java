package aenu.eide.diagnostic;
import java.io.File;
import java.util.List;

public class CxDiagnostic implements IDiagnostic{

    public File __File;
    public String[] __Flags;
    
    public CxDiagnostic(File file,String... flags){
        this.__File=file;
        this.__Flags=flags;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof CxUnfDiagnostic)
            return ((CxUnfDiagnostic)o).__File.equals(__File);
        else if(o instanceof CxDiagnostic)
            return ((CxDiagnostic)o).__File.equals(__File);
        return false;
    }
    
    @Override
    public int hashCode(){
        return __File.hashCode();
    }
    
	@Override
	public String toString(){
		return CxDiagnostic.class+__File.toString();
	}
    
    @Override
    public void diag(DiagnosticCallback callback){
        CXDiagnosticJni.diag_from_file(__File.getAbsolutePath(),__Flags,callback);
    }

}
