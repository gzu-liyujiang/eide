

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
import android.content.res.*;

public final class E_Installer
{
    
    static void install(final Activity activity,final ProgressDialog p_dialog){
        new Thread(){
            public void run(){      
            
                try{
                    final AssetManager assetM=activity.getAssets();

                    {//install toolchain
                        activity.runOnUiThread(new Runnable(){
                                @Override
                                public void run(){
                                    p_dialog.setMessage("install toochain....");
                                }
                            });
                        install_toolchain(assetM);                  
                    }

                    {//download android.jar
                        activity.runOnUiThread(new Runnable(){
                                @Override
                                public void run(){
                                    p_dialog.setMessage("downlaod android.jar....");
                                }
                            });
                        download_android_jar(E_Application.getAndroidJarUrl());
                    }
                    
                    {//uncompression data
                        activity.runOnUiThread(new Runnable(){
                                @Override
                                public void run(){
                                    p_dialog.setMessage("uncompression data....");
                                }
                            });
                        uncompression_data(assetM);
                    }
                    
                    {//install ndk
                        activity.runOnUiThread(new Runnable(){
                                @Override
                                public void run(){
                                    p_dialog.setMessage("ndk install ...");
                                }
                            });
                        install_ndk(E_Application.getNdkUrl());
                    }            
                    
                    {//update version info
                        activity.runOnUiThread(new Runnable(){
                                @Override
                                public void run(){
                                    p_dialog.dismiss();
                                }
                            });
                        E_Application.updateConfigVersion(activity);                      
                    }
                }
                catch(Exception e){
                    E_Application.clearConfigVersion(activity);               
                    throw new RuntimeException(e);
                }            
            }
        }.start();
    }
    
    static private void install_toolchain(AssetManager am) throws IOException, InterruptedException{
               
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
            InputStream in=am.open(i[0]);
            FileOutputStream out=new FileOutputStream(i[1]);

            while((n=in.read(buf))!=-1)
                out.write(buf,0,n);

            out.close();
            in.close();         

            OSUtils.chmod(i[1],0755);
        }

        if(Runtime.getRuntime().exec(binDir+"/busybox --install -s "+binDir)
            .waitFor()!=0)
                    throw new IOException("工具链安装失败");      
    }
    
    static private void download_android_jar(URL url) throws IOException{   
        InputStream in=url.openStream();
        byte[] buf=new byte[4096*4];
        int n;

        File of=new File(E_Application.getAppPrivateDir(),"android.jar");

        FileOutputStream out=new FileOutputStream(of);

        while((n=in.read(buf))!=-1)
            out.write(buf,0,n);

        out.close();
        in.close();              
    }
    
    static private void uncompression_data(final AssetManager am) throws IOException{       
        
        final String list[][]={
        {"test.ks",E_Application.getAppPrivateDir()+"/test.ks"},
               };

        final byte[] buf=new byte[4096*4];
        int n;             

        for(String[] i:list){
            InputStream in=am.open(i[0]);
            FileOutputStream out=new FileOutputStream(i[1]);

            while((n=in.read(buf))!=-1)
                out.write(buf,0,n);

            out.close();
            in.close();         
        }
    }
    
    static private void install_ndk(final URL url) throws IOException, InterruptedException{
   
        InputStream in=url.openStream();
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

        final int ec= p.waitFor();

        of.delete();      
        
        if(ec!=0)
            throw new IOException("ndk 安装失败");
    }
}
