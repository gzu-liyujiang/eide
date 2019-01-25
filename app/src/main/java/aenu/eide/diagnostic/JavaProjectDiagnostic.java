

//license wtfpl 2.0

//by aenu 2019
//   email:202983447@qq.com

package aenu.eide.diagnostic;
import aenu.eide.gradle_impl.GradleProject;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import android.util.Log;
import org.eclipse.jdt.internal.compiler.batch.CompilationUnit;

public final class JavaProjectDiagnostic extends ProjectDiagnostic
{
    private final GradleProject project;
    private final DiagnosticCallback callback;

    public JavaProjectDiagnostic(GradleProject project,DiagnosticCallback cb){
        this.project=project;
        this.callback=cb;
        //start();
    }

    /*
    private void start(){     

        new Thread(){
            
            void x(List<File> files){
                CompilationUnit units[]=new CompilationUnit[files.size()];
                [i] = new CompilationUnit(null, fileName, encoding, this.destinationPaths[i],
                shouldIgnoreOptionalProblems(this.ignoreOptionalProblemsFromFolders, fileName.toCharArray()));
                
            }
            
            void diag(){
                try{
                    ICompilationUnit uint=new CompilationUnit(source.toCharArray(),"","utf-8");
                    CompilationResult result=new CompilationResult(uint,0,1,100);
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
                                warnings.add(new Pair(p.getSourceStart(),p.getSourceEnd()));
                                warningTexts.add(p.getMessage());
                            }
                            else if(p.isError()){
                                errors.add(new Pair(p.getSourceStart(),p.getSourceEnd()));
                                errorTexts.add(p.getMessage());
                            }                    
                        }

                }
                catch(Exception e){}
                finally{
                    callback.onWarning(warnings,warningTexts);
                    callback.onError(errors,errorTexts);
                }
            }
            
            public void run(){
                final File dir=new File(project.build_gradle.getParentFile(),
                "src/main/jni");

                final List<File> files=loadJavaFiles(dir);

                callback.onDiagStart();

  
                for(File file:files){
                    Log.i("eide",file.toString());
                    
                }

                callback.onDiagDone();
            }
        }.start();

    }*/

    private List<File> loadJavaFiles(File dir){
        final List<File> list=new ArrayList<>();
        final File[] files=dir.listFiles();

        if(files!=null)
            for(File file:files){
                if(file.isFile()){
                    String file_name=file.getName();
                    if(file_name.endsWith(".java"))
                        list.add(file);
                }
                else{
                    list.addAll(loadJavaFiles(file));
                }
            }

        return list;
    }

    public void updateDiagstic(File file,String unsaved_source){
        errors.remove(file);
        warnings.remove(file);

    }
}
