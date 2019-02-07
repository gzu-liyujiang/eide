

//license wtfpl 2.0

//by aenu 2019
//   email:202983447@qq.com

package aenu.eide.gradle_impl.plugin;
import aenu.eide.gradle_impl.IPlugin;
import java.io.File;
import aenu.gradle.G_Tree;
import aenu.eide.gradle_impl.GradleProject;
import aenu.gradle.G_Tree.Node;
import java.io.IOException;
import aenu.eide.E_Application;
import android.content.Intent;
import aenu.eide.E_TermActivity;
import aenu.eide.util.IOUtils;
import aenu.eide.util.OSUtils;
import java.io.FileOutputStream;
import android.util.Log;
import jackpal.androidterm.TermExec;
import android.os.ParcelFileDescriptor;
import java.io.*;

public final class eide_c_application implements IPlugin{
    private String scriptType="Android.mk";
    private File scriptPath=null;
    final GradleProject GProject;
    
    public eide_c_application(GradleProject gp){
        GProject=gp;
    }
    
    public String scriptType(){
        return scriptType;
    }
    public File scriptPath(){
        return scriptPath;
    }
    @Override
    public void plugin_Visit(G_Tree tree){
        G_Tree.Node node;

        if((node=tree.getNode("eide-c.scriptType"))!=null) 
            scriptType=(String)node.values().get(0).value();
        if((node=tree.getNode("eide-c.scriptPath"))!=null) 
            scriptPath= handle_eide_c_scriptPath(node);
    }
    
    @Override
    public Runnable plugin_Task(final GradleProject gp){
        return new Runnable(){
            @Override
            public void run(){
                if(scriptPath!=null){
                    try{
                        
                        String errMsg;
                        
                        {// exec ndk-build
                            File ndk_build =new File(E_Application.getNdkDir(),"ndk-build");
                            File pDir=scriptPath.getParentFile().getParentFile();

                            ProcessBuilder pBuilder=new ProcessBuilder();
                            pBuilder.environment().put("PATH",E_Application.getBinDir().getAbsolutePath());
                            pBuilder.environment().put("APP_PIE_REQUIRED","true");//安卓版本 >= 16
                            pBuilder.environment().put("TEMPDIR",E_Application.getTmpDir().getAbsolutePath());//clang编译需要
                        
                            Process ppp=pBuilder.command(ndk_build.getAbsolutePath(),"-C",pDir.getAbsolutePath())
                                    .start();
                            ppp.waitFor();
                            
                            errMsg=IOUtils.stream_read(ppp.getErrorStream());
                        }         
            
                        File exec_bin = null;
                        
                        {// copy bin
                            File pDir=scriptPath.getParentFile().getParentFile();                       
                            File[] files=new File(pDir,"libs/armeabi-v7a").listFiles();

                            if(files!=null&&files.length!=0){
                                File out_bin=files[0];
                                    exec_bin=new File(E_Application.getTmpDir(),out_bin.getName());
                                    IOUtils.file_copy(exec_bin,out_bin);
                                    OSUtils.chmod(exec_bin.getAbsolutePath(),0700);                               
                                
                            }
                            
                            if(exec_bin==null) throw new RuntimeException(errMsg);
                        }
                        
                        {// exec bin
                            Intent intent = new Intent(E_TermActivity.ACTION_TERM_EXEC);
                            intent.putExtra(E_TermActivity.EXTRA_BIN,exec_bin.getAbsolutePath());
                            gp.context.startActivity(intent); 
                        }
                    }catch (Exception e) {
                        Log.e("eide",e.toString());
                        throw new RuntimeException(e);
                    }
                }
            }
        };
    }
    
    private File handle_eide_c_scriptPath(G_Tree.Node node){
        return new File(GProject.build_gradle.getParentFile(),(String)node.values().get(0).value());
    }
}
