

//license wtfpl 2.0

//by aenu 2019
//   email:202983447@qq.com

package aenu.eide;
import android.app.Activity;
import android.os.Bundle;
import android.net.Uri;
import android.app.ProgressDialog;
import java.io.InputStream;
import java.net.URL;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import aenu.eide.util.OSUtils;

public final class E_Installer
{
    
    static void install_toolchain(final Activity activity){

        final ProgressDialog pd = ProgressDialog.show(activity, null, "ToolChain install ...", true, false);

        final Handler handler=new Handler(){
            public void handleMessage(Message msg){
                pd.hide();
            }       
        };

        new Thread(){
            public void run(){         
            
                try{              
                    final String binDir=E_Application.getBinDir().getAbsolutePath();
                    final String dexDir=E_Application.getDexDir().getAbsolutePath();
                    
                    final String list[][]={
                      {"bin/arm/aapt",binDir+"/aapt"},
                      {"bin/arm/aidl",binDir+"/aidl"},
                      {"bin/arm/busybox",binDir+"/busybox"},
                      {"bin/dx.jar",dexDir+"/dx.jar"},
                      {"bin/apksigner.jar",dexDir+"/apksigner.jar"}
                    };
                    
                    final byte[] buf=new byte[4096*4];
                    int n;             
                    
                    for(String[] i:list){
                        InputStream in=activity.getAssets().open(i[0]);
                        FileOutputStream out=new FileOutputStream(i[1]);
                        
                        while((n=in.read(buf))!=-1)
                            out.write(buf,0,n);

                        out.close();
                        in.close();         
                        
                        OSUtils.chmod(i[1],0755);
                    }
                    
                    Runtime.getRuntime().exec(binDir+"/busybox --install -s "+binDir)
                        .waitFor();

                    boolean r;        
                    while(!(r=handler.sendEmptyMessage(0)));

                }catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }
    
    static void install_template_and_key(final Activity activity){

        final ProgressDialog pd = ProgressDialog.show(activity, null, "ToolChain install ...", true, false);

        final Handler handler=new Handler(){
            public void handleMessage(Message msg){
                pd.hide();
            }       
        };

        new Thread(){
            public void run(){         

                try{              
                    final String sdcard="/sdcard";
                    final String ProjectDir=E_Application.getProjectDir().getAbsolutePath();
                    final String binDir=E_Application.getBinDir().getAbsolutePath();
                    
                    final String list[][]={
                    {"test.ks",sdcard+"/test.ks"},
                    {"t.tar.xz",ProjectDir+"/t.tar.xz"},
                     };

                    final byte[] buf=new byte[4096*4];
                    int n;             

                    for(String[] i:list){
                        InputStream in=activity.getAssets().open(i[0]);
                        FileOutputStream out=new FileOutputStream(i[1]);

                        while((n=in.read(buf))!=-1)
                            out.write(buf,0,n);

                        out.close();
                        in.close();         

                        OSUtils.chmod(i[1],0755);
                    }

                    Runtime.getRuntime().exec(binDir+
                        "/busybox tar -xJf "+list[1][1]+" -C "
                        +E_Application.getProjectDir())
                    .waitFor();

                    boolean r;        
                    while(!(r=handler.sendEmptyMessage(0)));

                }catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }
    
    
    static void install_ndk(final Activity activity){
        
        final ProgressDialog pd = ProgressDialog.show(activity, null, "ndk install ...", true, false);
        
        final Handler handler=new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==0)
                    pd.hide();
                else
                    Toast.makeText(activity,"ndk install fail !",1).show();
            }       
        };
        
        new Thread(){
            public void run(){         
                try{                
                    InputStream in=activity.getAssets().open("n.tar.xz");
                    byte[] buf=new byte[4096*4];
                    int n;
                    
                    File of=new File(E_Application.getTmpDir(),"tmp.tar.xz");
                    FileOutputStream out=new FileOutputStream(of);
                    
                    while((n=in.read(buf))!=-1)
                        out.write(buf,0,n);
                    
                    out.close();
                    in.close();
                    
                    String cmd=E_Application.getBinDir().getAbsolutePath()
                      +"/busybox tar -xJf "
                      +of.getAbsolutePath()
                      +" -C "
                      +E_Application.getNdkDir().getAbsolutePath();
                    
                    Process p= Runtime.getRuntime().exec(cmd);
                    
                    int ec= p.waitFor();
                    
                    of.delete();
                    
                    boolean r;        
                    while(!(r=handler.sendEmptyMessage(ec)));
                    
                }catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }
}
