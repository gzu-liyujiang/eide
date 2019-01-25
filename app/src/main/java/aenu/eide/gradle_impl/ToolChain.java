
//license wtfpl 2.0

//by aenu 2019
//   email:202983447@qq.com

package aenu.eide.gradle_impl;
import java.io.File;
import android.content.res.Resources;
import java.io.InputStream;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileInputStream;
import aenu.eide.util.IOUtils;
import org.eclipse.jdt.core.compiler.batch.BatchCompiler;
import java.io.PrintWriter;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import dalvik.system.DexClassLoader;
import android.content.Context;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.PrintStream;
import java.io.OutputStream;
import aenu.eide.util.OSUtils;
import jackpal.androidterm.TermExec;
import aenu.eide.E_Application;
import android.os.ParcelFileDescriptor;

public final class ToolChain{
    
    public final File AAPT;
    public final DexClassLoader DX;
    public final DexClassLoader APKSIGNER;
    
    public ToolChain(Context context) throws IOException{     
        AAPT=new File(E_Application.getBinDir(),"aapt");
        DX=DCL(context,"dx.jar");
        APKSIGNER=DCL(context,"apksigner.jar");
    }
    
    private static final DexClassLoader DCL(Context context,String jarName){
        
        final String lib=jarName.substring(0,jarName.length()-4);//.jar
        final File jar=new File(E_Application.getDexDir(),jarName);
        final File jar_optimizedDirectory=new File(E_Application.getDexDir(),lib);
        if(!jar_optimizedDirectory.exists())
            jar_optimizedDirectory.mkdirs();
            
        return new DexClassLoader(jar.getAbsolutePath(),jar_optimizedDirectory.getAbsolutePath(),null,context.getClassLoader());
    }
    
    public boolean run_ecj(File android_jar,File class_out_dir,File... src_dirs){
        
        StringBuilder cmds=new StringBuilder();
        
        cmds.append("-bootclasspath");
        cmds.append(" ");
        cmds.append(android_jar.getAbsoluteFile());
        cmds.append(" ");
        cmds.append("-source");
        cmds.append(" ");
        cmds.append("1.7");
        cmds.append(" ");
        cmds.append("-target");
        cmds.append(" ");
        cmds.append("1.7");
        cmds.append(" ");
        cmds.append("-d");
        cmds.append(" ");
        cmds.append(class_out_dir.getAbsolutePath());
        cmds.append(" ");
        
        for(int i=0;i<src_dirs.length;i++){
            cmds.append(src_dirs[i].getAbsolutePath());
            if(i!=src_dirs.length-1)cmds.append(" ");
        }
        
        if(!class_out_dir.exists())
            class_out_dir.mkdirs();
        Log.i("eide",cmds.toString());
        
        ByteArrayOutputStream err=new ByteArrayOutputStream();
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        
        PrintWriter errPW=new PrintWriter(err);
        PrintWriter outPW=new PrintWriter(out);
        
        boolean r= BatchCompiler.compile(cmds.toString(),outPW,errPW,null);
        
        outPW.close();
        errPW.close();
        
        if(!r){       
            //Log.w("eide","err:"+err.toString());
            //Log.w("eide","out:"+out.toString());
            
            throw new RuntimeException(err.toString());
        }
        
        return true;
    }
    
    public boolean run_aapt_gen_r(File r_dir,File resDir,File android_jar,File AndroidManifest_xml) throws IOException, InterruptedException{
        StringBuilder cmds=new StringBuilder();
        cmds.append(AAPT.getAbsolutePath());
        cmds.append(" ");
        cmds.append("package");
        cmds.append(" ");
        cmds.append("-m");
        cmds.append(" ");
        cmds.append("-J");
        cmds.append(" ");
        cmds.append(r_dir.getAbsolutePath());
        cmds.append(" ");
        cmds.append("-S");
        cmds.append(" ");
        cmds.append(resDir.getAbsolutePath());
        cmds.append(" ");
        cmds.append("-I");
        cmds.append(" ");
        cmds.append(android_jar.getAbsolutePath());
        cmds.append(" ");
        cmds.append("-M");
        cmds.append(" ");
        cmds.append(AndroidManifest_xml.getAbsolutePath());
        
        //Log.i("eide",cmds.toString());
        if(!r_dir.exists())
            r_dir.mkdirs();
        
        Process p=Runtime.getRuntime().exec(cmds.toString());
        if(p.waitFor()!=0)
            throw new IOException(IOUtils.stream_read(p.getErrorStream()));
        return true;
    }
    
    public boolean run_aapt_pack(File assets_dir,File resDir,File android_jar,File AndroidManifest_xml,File out_file) throws IOException, InterruptedException{   
        StringBuilder cmds=new StringBuilder();
        cmds.append(AAPT.getAbsolutePath());
        cmds.append(" ");
        cmds.append("package");
        cmds.append(" ");
        cmds.append("-f");
        cmds.append(" ");
        cmds.append("-A");
        cmds.append(" ");
        cmds.append(assets_dir.getAbsolutePath());
        cmds.append(" ");
        cmds.append("-S");
        cmds.append(" ");
        cmds.append(resDir.getAbsolutePath());
        cmds.append(" ");
        cmds.append("-I");
        cmds.append(" ");
        cmds.append(android_jar.getAbsolutePath());
        cmds.append(" ");
        cmds.append("-M");
        cmds.append(" ");
        cmds.append(AndroidManifest_xml.getAbsolutePath());
        cmds.append(" ");
        cmds.append("-F");
        cmds.append(" ");
        cmds.append(out_file.getAbsolutePath());
        
        if(!out_file.getParentFile().exists())
            out_file.getParentFile().mkdirs();
        
        Process p=Runtime.getRuntime().exec(cmds.toString());
        if(p.waitFor()!=0)
            throw new IOException(IOUtils.stream_read(p.getErrorStream()));
        return true;
    }
    
    public boolean run_dx_jars(File jars_dir,File out_dir) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, IOException{
        
        Class Main=DX.loadClass("____.com.android.dx.command.Main");
        Method main=Main.getMethod("main",String[].class);
        
        if(!out_dir.exists())
            out_dir.mkdirs();
        
        File[] jars=jars_dir.listFiles();
        
        for(File jar:jars){
            String dexName=jar.getName();
            dexName=dexName.substring(0,dexName.length()-4)+".dex";
            
            File out_dex=new File(out_dir,dexName);
            
            ParcelFileDescriptor[] pfds=ParcelFileDescriptor.createPipe();
           
            InputStream pipeIn= new
              ParcelFileDescriptor.AutoCloseInputStream(pfds[0]);
            OutputStream pipeOut= new
              ParcelFileDescriptor.AutoCloseOutputStream(pfds[1]);
            
            int pid;
            if((pid=OSUtils.fork())==0){
                
                pipeIn.close();

                System.setErr(new PrintStream(pipeOut));             
                
                main.invoke(Main,new Object[]{new String[]{
                  "--dex",
                  "--output="+out_dex.getAbsolutePath(),
                  jar.getAbsolutePath()
                }});
                
                System.exit(0);
            }
            else{              
                pipeOut.close();         

                if(TermExec.waitFor(pid)!=0)
                    throw new RuntimeException(IOUtils.stream_read(pipeIn));
            }
        }
        
        return true;
    }
    
    public boolean run_dx_classes(File classes_dir,File out_file) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, IOException{

        Class Main=DX.loadClass("____.com.android.dx.command.Main");
        Method main=Main.getMethod("main",String[].class);
       
        if(!out_file.getParentFile().exists())
            out_file.getParentFile().mkdirs();
        
        ParcelFileDescriptor[] pfds=ParcelFileDescriptor.createPipe();
        
        InputStream pipeIn= new
          ParcelFileDescriptor.AutoCloseInputStream(pfds[0]);
        OutputStream pipeOut= new
          ParcelFileDescriptor.AutoCloseOutputStream(pfds[1]);
        
        int pid;
        
        if((pid=OSUtils.fork())==0){
            
            pipeIn.close();
            
            System.setErr(new PrintStream(pipeOut));             
            
            main.invoke(Main,new Object[]{new String[]{
              "--dex",
              "--output="+out_file.getAbsolutePath(),
              classes_dir.getAbsolutePath()
            }});
            
            System.exit(0);
        }
        else{
            pipeOut.close();         

            if(TermExec.waitFor(pid)!=0)
                throw new RuntimeException(IOUtils.stream_read(pipeIn));
        }
        return true;
    }
    
    public boolean run_apksigner(File inApk,File outApk,File ks,String pass) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, RuntimeException, IOException{
        Class ApkSignerTool=APKSIGNER.loadClass("com.android.apksigner.ApkSignerTool");
        Method main=ApkSignerTool.getMethod("main",String[].class);
       
        if(!outApk.getParentFile().exists())
            outApk.getParentFile().mkdirs();

        ParcelFileDescriptor[] pfds=ParcelFileDescriptor.createPipe();
        
        InputStream pipeIn= new
          ParcelFileDescriptor.AutoCloseInputStream(pfds[0]);
        OutputStream pipeOut= new
          ParcelFileDescriptor.AutoCloseOutputStream(pfds[1]);
        
        int pid;

        if((pid=OSUtils.fork())==0){
            
            pipeIn.close();
            
            System.setErr(new PrintStream(pipeOut));             
            
            main.invoke(ApkSignerTool,new Object[]{new String[]{
              "sign",
              "--ks",ks.getAbsolutePath(),
              "--ks-pass","pass:"+pass,
              "--out", outApk.getAbsolutePath(),
              inApk.getAbsolutePath()
            }});

            System.exit(0);
        }
        else{
            
            pipeOut.close();         
                
            if(TermExec.waitFor(pid)!=0)
                throw new RuntimeException(IOUtils.stream_read(pipeIn));
        }
        return true;
    }
}
