package aenu.eide.diagnostic;
import java.util.List;
import java.io.File;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.jdt.internal.compiler.batch.CompilationUnit;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import aenu.eide.PL.JavaAutoCompletePanel;
import org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration;
import org.eclipse.jdt.internal.compiler.util.HashtableOfObject;
import java.util.ArrayList;
import org.eclipse.jdt.core.compiler.CategorizedProblem;

public class JavaDiagnostic implements IDiagnostic{
   
    private List<File> list_java_files(File dir){
        List<File> result=new ArrayList<>();
        File[] files= dir.listFiles();
        if(files!=null)
            for(File f:files)
               if(f.isDirectory())
                   result.addAll(list_java_files(f));
               else if(f.getName().endsWith(".java"))
                   result.add(f);
               else
                   continue;
        return result;                
    }
    
    public CompilationUnit[] generate_compilation_units(List<File> files) {
        int fileCount = files.size();
        CompilationUnit[] units = new CompilationUnit[fileCount];      

        for (int i = 0; i < fileCount; i++) {         
            String fileName=files.get(i).getAbsolutePath();
            units[i] = new CompilationUnit(null, fileName, "utf-8", null);
        }
        return units;
    }
    
    @Override
    public void diag(DiagnosticCallback callback){
    
        try{
            if(comp_utins==null)
                return;
            final int total=comp_utins.length;
            for(int i=0;i<total;i++){
                ICompilationUnit uint=comp_utins[i];
                CompilationResult result=new CompilationResult(uint,i,total,400);
                CompilationUnitDeclaration decl=JavaAutoCompletePanel._parser.parse(uint,result);       
                JavaAutoCompletePanel._parser.getMethodBodies(decl);
                if(decl.scope!=null)
                    decl.scope.faultInTypes();
                //if(decl.scope!=null)
                //    decl.scope.verifyMethods();

                decl.resolve();
                decl.analyseCode();
                decl.generateCode();

                if(decl.scope != null)
                    decl.scope.storeDependencyInfo();

                decl.finalizeProblems();

                CategorizedProblem[] problems=result.getAllProblems();

                if(problems!=null)
                    for(CategorizedProblem p:problems){
                        if(p.isWarning()){
                            callback.onNewWarning(DiagnosticMessage.newMessage(new String(uint.getFileName()),
                                p.getSourceStart(),p.getSourceEnd(),p.getSourceLineNumber(),-1,
                                    p.getMessage()));
                        }
                        else if(p.isError()){
                            callback.onNewError(DiagnosticMessage.newMessage(new String(uint.getFileName()),
                            p.getSourceStart(),p.getSourceEnd(),p.getSourceLineNumber(),-1,
                            p.getMessage()));
                        }                    
                    }
            }
        }
        catch(Exception e){}
        
    }

    private CompilationUnit[] comp_utins;
    
    public JavaDiagnostic(File dir,List<File> jars){
        comp_utins=generate_compilation_units(list_java_files(dir));
    }
}
