package aenu.eide.PL;
import com.myopicmobile.textwarrior.android.ICodeDiag;
import java.util.List;
import com.myopicmobile.textwarrior.android.ICodeDiag.DiagInfo;
import aenu.eide.diagnostic.CXProjectDiagnostic;
import java.io.File;
import aenu.eide.diagnostic.DiagnosticInfo;
import java.util.ArrayList;
import aenu.eide.diagnostic.ProjectDiagnostic;

public class CxxCodeDiag implements ICodeDiag{
    ProjectDiagnostic diag;
    File file;
    public CxxCodeDiag(ProjectDiagnostic diag,File file){
        this.diag=diag;
        this.file=file;
    }
    
    @Override
    public void diag(String code){
        // TODO: Implement this method
    }

    @Override
    public List<ICodeDiag.DiagInfo> getErrors(){
        List<DiagnosticInfo> errs_I= diag.errors.get(file);
        if(errs_I==null) return null;
        List<ICodeDiag.DiagInfo> errs_O=new ArrayList<>();
        for(DiagnosticInfo i:errs_I)
            errs_O.add(new ICodeDiag.DiagInfo(i.start_position,i.end_position,i.info));
        return errs_O;
    }

    @Override
    public List<ICodeDiag.DiagInfo> getWarnings(){
        List<DiagnosticInfo> wars_I= diag.warnings.get(file);
        if(wars_I==null) return null;
        List<ICodeDiag.DiagInfo> wars_O=new ArrayList<>();
        for(DiagnosticInfo i:wars_I)
            wars_O.add(new ICodeDiag.DiagInfo(i.start_position,i.end_position,i.info));
        return wars_O;
    }
}
