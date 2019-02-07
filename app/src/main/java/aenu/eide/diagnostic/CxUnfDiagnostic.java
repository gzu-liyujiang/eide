package aenu.eide.diagnostic;
import java.io.File;

public class CxUnfDiagnostic implements IDiagnostic{
    
    public File __File;
    public String[] __Flags;
    public String __Source;

    public CxUnfDiagnostic(File file,String source,String... flags){
        this.__File=file;
        this.__Flags=flags;
        this.__Source=source;
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
        CXDiagnosticJni.diag_from_source(__File.getAbsolutePath(),__Source,__Flags,callback);
    }
}
