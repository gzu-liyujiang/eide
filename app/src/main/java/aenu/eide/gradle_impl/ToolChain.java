
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
	
	private static final void RUN_JAR(DexClassLoader dcl,String mainClassName,String args) throws IOException, IllegalArgumentException, InvocationTargetException, IllegalAccessException, SecurityException, NoSuchMethodException, ClassNotFoundException 	{
		final Class Main=dcl.loadClass(mainClassName);
        final Method main=Main.getMethod("main",String[].class);
		
        final ParcelFileDescriptor[] pfds=ParcelFileDescriptor.createPipe();

		final InputStream pipeIn= new
			ParcelFileDescriptor.AutoCloseInputStream(pfds[0]);
		final OutputStream pipeOut= new
			ParcelFileDescriptor.AutoCloseOutputStream(pfds[1]);

		int pid;
		if((pid=OSUtils.fork())==0){
			
			pipeIn.close();
			System.setErr(new PrintStream(pipeOut));             
			main.invoke(Main,new Object[]{args.split(" ")});
			System.exit(0);
		}
		else{              
			pipeOut.close();   		
			
			if(TermExec.waitFor(pid)!=0)
				throw new RuntimeException(IOUtils.stream_read(pipeIn));		
		}		
	}
    
    public boolean run_ecj(String args){
        
        ByteArrayOutputStream err=new ByteArrayOutputStream();
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        
        PrintWriter errPW=new PrintWriter(err);
        PrintWriter outPW=new PrintWriter(out);
        
        boolean r= BatchCompiler.compile(args.toString(),outPW,errPW,null);
        
        outPW.close();
        errPW.close();
        
        if(!r){       
            throw new RuntimeException(err.toString());
        }
        
        return true;
    }
    
    public boolean run_aapt(String args) throws IOException, InterruptedException{
        
		final String command=AAPT.getAbsolutePath()+" "+args;
        Process p=Runtime.getRuntime().exec(command);
        if(p.waitFor()!=0)
            throw new IOException(IOUtils.stream_read(p.getErrorStream()));
        return true;
    }
	
	public boolean run_dx(String args) throws SecurityException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException, InvocationTargetException {	
		RUN_JAR(DX,"____.com.android.dx.command.Main",args);
		return true;
	}
    
    public boolean run_apksigner(String args) throws SecurityException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException, InvocationTargetException {
		RUN_JAR(APKSIGNER,"com.android.apksigner.ApkSignerTool",args);
		return true;
    }
}
