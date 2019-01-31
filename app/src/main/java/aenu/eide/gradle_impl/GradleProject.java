
//license wtfpl 2.0

//by aenu 2019
//   email:202983447@qq.com

package aenu.eide.gradle_impl;
import aenu.gradle.G_Parser;
import aenu.gradle.G_Tree;
import java.io.File;
import aenu.eide.util.IOUtils;
import android.content.Context;
import aenu.gradle.G_ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import aenu.gradle.expr.StringLiteral;
import aenu.gradle.expr.Expression;
import aenu.eide.gradle_impl.plugin.com_android_application;
import java.util.ArrayList;
import aenu.gradle.expr.FunctionInvoke;
import java.io.IOException;
import aenu.gradle.G_SyntaxException;
import android.util.Log;
import java.util.Set;
import aenu.gradle.expr.Array;
//import aenu.eide.gradle_impl.plugin.__default;
import java.util.Collection;
import aenu.eide.gradle_impl.plugin.eide_java_application;
import aenu.eide.gradle_impl.plugin.eide_c_application;

public final class GradleProject{

    public static final GradleProject open(Context context,File build_gradle){
        try{
            return new GradleProject(context,build_gradle);
        }
        catch(Exception e){
            return null;
        }
    }
    
    public final File build_gradle;
    public final ToolChain tool_chain;
    public final Context context;
    
    private final Map<String,IPlugin> plugins=new HashMap<>();
    private List<IDependencie> dependencies=new ArrayList<>();
    
    GradleProject(Context context,File build_gradle) throws IOException{
        this.context=context;
        this.build_gradle=build_gradle;
        this.tool_chain=new ToolChain(context);
    }
    
    public File getJavaDir(){
        return new File(build_gradle.getParentFile(),"src/main/java");
    }
    
    private void analyze_gradle() throws Exception{
        
        G_Tree tree=G_Parser.Parse(IOUtils.file_read2(build_gradle));
        
        G_Tree.Node node;
        
        if((node=tree.getNode("apply.plugin"))!=null)
            if(node.values().size()!=0)
                load_plugins((List<StringLiteral>)node.values());
        
        for(IPlugin p:plugins.values())
            p.plugin_Visit(tree);
        /*if((node=tree.getNode("dependencies.compile"))!=null)
            if(node.values().size()!=0)
                load_dependencies(node.values());*/
    }
    
    private void load_plugins(List<StringLiteral> names){
        for(StringLiteral name:names){
            if(name.value().equals("com.android.application"))
                plugins.put(name.value(),new com_android_application());
            if(name.value().equals("eide-java-application"))
                plugins.put(name.value(),new eide_java_application());
            if(name.value().equals("eide-c-application"))
                plugins.put(name.value(),new eide_c_application(this));
            
        }
    }
    
    /*
    private void load_dependencies(List<Expression> dependencies){
        for(Expression e:dependencies){
            if(e instanceof StringLiteral){
                //maven 
                continue;
            }
            
            if(e instanceof FunctionInvoke){
                
                FunctionInvoke funcI=(FunctionInvoke) e;
                
                String func_name=funcI.getFuncName();
                
                if(func_name.equals("fileTree")){
                    StringLiteral dir=(StringLiteral)funcI.getArg("dir");
                    Array include=(Array)funcI.getArg("include");
                    File lib_dir=new File(build_gradle.getParentFile(),dir.value());
                    List<StringLiteral> include_libs=(List<StringLiteral>)include.value();
                    
                    {//check include_libs
                        for(StringLiteral lib:include_libs){
                            boolean lib_err=true;
                            if(lib.value().endsWith(".jar"))
                                lib_err=false;
                            if(lib.value().endsWith(".aar"))
                                lib_err=false;
                            if(lib_err)
                                throw new RuntimeException("只能包含.jar或.aar后缀的文件");
                        }
                    }            
                    
                    if(lib_dir.exists()&&lib_dir.isDirectory()){
                        dependencie_load_lib(lib_dir,include_libs);
                    }
                }
                else if(func_name.equals("project")){
                    String project_name=((StringLiteral)funcI.getArgs().get(0)).value().substring(1);
                    File project_dir=new File(build_gradle.getParentFile().getParentFile(),project_name);
                    this.dependencies.add(GradleProject.open(context,new File(project_dir,"build.gradle")));
                }
            }
        }
    }
    
    private void dependencie_load_lib(File dir,List<StringLiteral> include_libs){
        File[] files=dir.listFiles();
        
        for(File f:files){
            if(f.isDirectory()){
                dependencie_load_lib(f,include_libs);
            }
            else{
                String name=f.getName();
                for(StringLiteral lib:include_libs){
                    if(name.matches(lib.value()))
                        dependencies.add(
                          name.endsWith(".jar")
                          ?new JarPackage(f)
                          :new AarPackage(f)
                        );
                }
            }
        }
    }*/
    
    public void build(Context context) throws Exception
    {
        analyze_gradle();
        
        //if(plugins.size()==0)
        //    plugins.put("",new __default());
            
        Collection<IPlugin> plugins=this.plugins.values();
        
        for(IPlugin l:plugins){
         
            Runnable task=l.plugin_Task(this);
            task.run();
        }
    }
}
