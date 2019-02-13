

//license wtfpl 2.0

//by aenu 2019
//   email:202983447@qq.com

package aenu.eide.gradle_impl.plugin;
import aenu.eide.E_Application;
import aenu.eide.gradle_impl.GradleProject;
import aenu.eide.gradle_impl.ToolChain;
import aenu.eide.util.IOUtils;
import android.content.Intent;
import android.net.Uri;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import aenu.eide.gradle_impl.ToolChainHelper;
import java.io.FileFilter;

public class com_android_application extends com_android_library{
    
    public com_android_application(GradleProject g_project){
        super(g_project);
    }
    
    @Override
    public Runnable plugin_Task(final GradleProject gp){
        return new Runnable(){
            @Override
            public void run(){

                try{
                    ToolChain tc=gp.tool_chain;
					final File p_dir=gp.getProjectDir();
					
                    {//run aapt
                        File r_dir=new File(p_dir,"build/gen");
                        File resDir=new File(p_dir,"src/main/res");
                        File android_jar=new File(E_Application.getAppPrivateDir(),"android.jar");
                        File AndroidManifest_xml=new File(p_dir,"src/main/AndroidManifest.xml");
						
						r_dir.mkdirs();
						
						final String gen_r_args=ToolChainHelper.generate_aapt_gen_r_args(r_dir,new File[]{resDir},android_jar,AndroidManifest_xml,null);
                        if(resDir.exists())
                            if(!tc.run_aapt(gen_r_args))
                                throw new IOException("AAPT!!!!!");

                        File assetsDir=new File(p_dir,"src/main/assets");
                        File out_file=new File(p_dir,"build/bin/resources.zip");

						out_file.getParentFile().mkdirs();
						
						final String pkg_args=ToolChainHelper.generate_aapt_pkg_args(new File[]{resDir},new File[]{assetsDir},android_jar,AndroidManifest_xml,null,out_file);

                        if(!tc.run_aapt(pkg_args))
                            throw new IOException("kk AAPT!!...");
					}

                    {//run ecj

                        File android_jar=new File(E_Application.getAppPrivateDir(),"android.jar");
                        File class_out_dir=new File(p_dir,"build/bin/class");
                        File src_dir=new File(p_dir,"src/main/java");
                        File r_dir=new File(p_dir,"build/gen");

						class_out_dir.mkdirs();
						//r_dir.mkdirs();

						String args;

                        if(r_dir.exists())
							args=ToolChainHelper.generate_ecj_args(android_jar,class_out_dir,src_dir,r_dir);
                        else
						    args=ToolChainHelper.generate_ecj_args(android_jar,class_out_dir,src_dir);

                        if(!tc.run_ecj(args))
                            throw new IOException("ECJ!!!!");
                    }

                    {//run dx
                        File classes_dir=new File(p_dir,"build/bin/class");
                        File jars_dir=new File(p_dir,"libs");
                        File jars_dex_dir=new File(p_dir,"build/bin/jar_dex");
                        File classes_dex=new File(p_dir,"build/bin/classes.dex");

						jars_dex_dir.mkdirs();
						classes_dex.getParentFile().mkdirs();
						
						File[] jars=null;
						
						if(jars_dir!=null)
							jars = jars_dir.listFiles(new FileFilter(){
									@Override
									public boolean accept(File p1){
										if(p1.isDirectory()) return false;
										return p1.getName().endsWith(".jar");
									}
								});
								
						if(jars!=null&&jars.length!=0){
							for(File jar:jars){
								final File out_dex=new File(jars_dex_dir,jar.getName().replace(".jar",".dex"));			
								final String dx_jar_args=ToolChainHelper.generate_dx_jar_args(jar,out_dex);
								
								if(!tc.run_dx(dx_jar_args))
									throw new IOException("dx jars!!!!");							
							}
						}
						
						final String dx_classes_args=ToolChainHelper.generate_dx_classes_args(classes_dir,classes_dex);
						
						if(!tc.run_dx(dx_classes_args))
                            throw new IOException("dx classes!!!!");
                    }
                    
                    {//build native lib
                        if(externalNativeBuild_ndkBuild_path()!=null){
                            File ndk_build =new File(E_Application.getNdkDir(),"ndk-build");
                            File pDir=externalNativeBuild_ndkBuild_path().getParentFile().getParentFile();

                            ProcessBuilder pBuilder=new ProcessBuilder();
                            pBuilder.environment().put("PATH",E_Application.getBinDir().getAbsolutePath());
                            pBuilder.environment().put("TEMPDIR",E_Application.getTmpDir().getAbsolutePath());//clang编译需要
 
                            Process proc= pBuilder.command(ndk_build.getAbsolutePath(),"-C",pDir.getAbsolutePath())
                                    .start();
							if(proc.waitFor()!=0)
								throw new RuntimeException(IOUtils.stream_read(proc.getErrorStream()));
                        }
                    }

                    {//create apk
                        ByteArrayOutputStream apk_buf=new ByteArrayOutputStream();
                        ZipOutputStream apk_strm=new ZipOutputStream(apk_buf);    

                        File assetsDir=new File(p_dir,"src/main/assets");
                        ZipFile aapt_resources=new ZipFile(new File(p_dir,"build/bin/resources.zip"));
                        File classes_dex=new File(p_dir,"build/bin/classes.dex");
                        File output_apk=new File(p_dir,"build/bin/o.apk");
                        File libs_dir=new File(p_dir,"src/main/libs");
                        
                        if(assetsDir.exists()){
                            IOUtils.zip_compressD(assetsDir,apk_strm,"assets");
                        }
                  
                        if(libs_dir.exists()){
                            IOUtils.zip_compressD(libs_dir,apk_strm,"lib");
                        }

                        IOUtils.zip_compressF(classes_dex,apk_strm,classes_dex.getName());
                        IOUtils.zip_compressZ2(aapt_resources,apk_strm);

                        aapt_resources.close();
                        apk_strm.close();

                        IOUtils.file_write(output_apk.getAbsolutePath(),apk_buf.toByteArray());
                    }

                    {//sign apk
                        File input_apk=new File(p_dir,"build/bin/o.apk");
                        File output_apk=new File(p_dir,"build/bin/o_sign.apk");
                        File ks=new File(E_Application.getAppPrivateDir(),"test.ks");//FIXME
                        String pass="android";//FIXME
						
						final String args=ToolChainHelper.generate_apksigner_args(input_apk,output_apk,ks,pass);
						
                        if(!tc.run_apksigner(args))
                            throw new IOException("签名失败!!!!");        
                    }

                    {//install apk

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        File apk=new File(p_dir,"build/bin/o_sign.apk");
                        intent.setDataAndType(Uri.fromFile(apk),"application/vnd.android.package-archive");       
                        gp.context.startActivity(intent);
                    }

                }catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
