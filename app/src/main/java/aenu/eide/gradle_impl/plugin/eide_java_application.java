

//license wtfpl 2.0

//by aenu 2019
//   email:202983447@qq.com

package aenu.eide.gradle_impl.plugin;
import java.io.File;
import aenu.gradle.G_Tree;
import aenu.gradle.G_Tree.Node;
import aenu.eide.gradle_impl.IPlugin;
import aenu.eide.gradle_impl.GradleProject;
import aenu.eide.gradle_impl.ToolChain;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipOutputStream;
import aenu.eide.util.IOUtils;
import android.content.Intent;
import aenu.eide.E_TermActivity;
import aenu.eide.E_Application;
import java.io.FileOutputStream;
import aenu.eide.util.OSUtils;

public final class eide_java_application implements IPlugin{
    private String mainClassName="example.Main";
    private String[] runArgs=null;
    private String jni_scriptType="Android.mk";
    private File jni_scriptPath=null;
    
    public String mainClassName(){
        return mainClassName;
    }
    public String[] runArgs(){
        return runArgs;
    }
    public String jni_scriptType(){
        return jni_scriptType;
    }
    public File jni_scriptPath(){
        return jni_scriptPath;
    }
    @Override
    public void plugin_Visit(G_Tree tree){
        G_Tree.Node node;

        if((node=tree.getNode("eide-java.mainClassName"))!=null) 
            mainClassName=(String)node.values().get(0).value();
        if((node=tree.getNode("eide-java.runArgs"))!=null) 
            runArgs=(String[])node.values().get(0).value();
        if((node=tree.getNode("eide-java.jni.scriptType"))!=null) 
            jni_scriptType=(String)node.values().get(0).value();
        if((node=tree.getNode("eide-java.jni.scriptPath"))!=null) 
            jni_scriptPath= handle_eide_java_jni_scriptPath(node);
    }

    @Override
    public Runnable plugin_Task(final GradleProject gp){
        return new Runnable(){
            @Override
            public void run(){
                
                ToolChain tc=gp.tool_chain;
                File build_gradle=gp.build_gradle;
                
                if(mainClassName==null)
                    throw new RuntimeException("unspecified main class!!");
                    
                try{
                    {//run ecj

                        File android_jar=new File("/sdcard/.aide/android.jar");//FIXME
                        File class_out_dir=new File(build_gradle.getParentFile(),"build/bin/class");
                        File src_dir=new File(build_gradle.getParentFile(),"src/main/java");

                        if(!tc.run_ecj(android_jar,class_out_dir,src_dir))
                            throw new RuntimeException("run ecj failed!");
                    }

                    {//run dx
                        File classes_dir=new File(build_gradle.getParentFile(),"build/bin/class");
                        File jar_dir=new File(build_gradle.getParentFile(),"libs");
                        File jar_dex_dir=new File(build_gradle.getParentFile(),"build/bin/jar_dex");
                        File classes_dex=new File(build_gradle.getParentFile(),"build/bin/classes.dex");

                        if(jar_dir.exists())
                            if(!tc.run_dx_jars(jar_dir,jar_dex_dir))
                                throw new RuntimeException("dx jars!!!!");
                        if(!tc.run_dx_classes(classes_dir,classes_dex))
                            throw new IOException("dx classes!!!!");
                    }
                    
                    {//create jar(dex)
                        ByteArrayOutputStream jar_buf=new ByteArrayOutputStream();
                        ZipOutputStream jar_strm=new ZipOutputStream(jar_buf);    

                        File resourcesDir=new File(build_gradle.getParentFile(),"src/main/resources");
                        File classes_dex=new File(build_gradle.getParentFile(),"build/bin/classes.dex");
                        File output_jar=new File(build_gradle.getParentFile(),"build/bin/o.jar");

                        if(resourcesDir.exists()){
                            IOUtils.zip_compressD(resourcesDir,jar_strm,null);
                        }

                        IOUtils.zip_compressF(classes_dex,jar_strm,classes_dex.getName());
                                            
                        jar_strm.close();
                        IOUtils.file_write(output_jar.getAbsolutePath(),jar_buf.toByteArray());                      
                    }
                    
                    {//run jar
                        Intent intent = new Intent(E_TermActivity.ACTION_JAVA_TERM_EXEC);
                        File jar=new File(build_gradle.getParentFile(),"build/bin/o.jar");
                        intent.putExtra(E_TermActivity.EXTRA_BIN,jar.getAbsolutePath());
                        intent.putExtra(E_TermActivity.EXTRA_JAVA_MAIN_CLASS,mainClassName);
                        gp.context.startActivity(intent);    
                    }
                    
                    /*
                    final File sh=new File(E_Application.getTmpDir(),"dex_r.sh");                
                            
                    {//create shell script
                    
                        final File output_jar=new File(build_gradle.getParentFile(),"build/bin/o.jar");
                        
                        FileOutputStream w_sh=new FileOutputStream(sh);
                        
                        w_sh.write(("#!/system/bin/sh\n").getBytes());
                        w_sh.write(("export ANDROID_DATA=/data/data/aenu.eide/eide-tmp/data\n").getBytes());
                        w_sh.write(("mkdir -p $ANDROID_DATA/dalvik-cache\n").getBytes());
                        w_sh.write(("exec /system/bin/dalvikvm -Djava.io.tmpdir=/data/data/aenu.eide/eide-tmp "
                                +"-classpath "+mainClassName+' '+output_jar+'\n').getBytes());
                        w_sh.close();
                        OSUtils.chmod(sh.getAbsolutePath(),0700);
                    }
                    
                    {//run jar
                        Intent intent = new Intent(E_TermActivity.ACTION_TERM_EXEC);
                        intent.putExtra(E_TermActivity.EXTRA_COMMAND,sh.getAbsolutePath());
                        gp.context.startActivity(intent);    
                    }*/
                    
                }catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        };
    }
    
    private File handle_eide_java_jni_scriptPath(G_Tree.Node node){
        return null;
    }
}
