

//license wtfpl 2.0

//by aenu 2019
//   email:202983447@qq.com

package aenu.eide.gradle_impl.plugin;
import aenu.eide.gradle_impl.IPlugin;
import aenu.gradle.G_Tree;
import java.io.File;
import aenu.gradle.G_Tree.Node;
import aenu.gradle.expr.StringLiteral;
import java.util.List;
import aenu.eide.gradle_impl.ToolChain;
import aenu.eide.gradle_impl.GradleProject;
import java.io.IOException;
import java.util.zip.ZipOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipFile;
import aenu.eide.util.IOUtils;
import android.content.Intent;
import android.net.Uri;
import aenu.eide.E_Application;

public final class com_android_application implements IPlugin{
    
    private int compileSdkVersion=21;
    private String buildToolsVersion="21.1.0";
    private String defaultConfig_applicationId=null;
    private int defaultConfig_minSdkVersion=14;
    private int defaultConfig_targetSdkVersion=21;
    private int defaultConfig_versionCode=1;
    private String defaultConfig_versionName="1.0";
    private boolean buildTypes_debug_minifyEnabled=false;
    private boolean buildTypes_debug_shrinkResources=false;
    private File[] buildTypes_debug_proguardFiles=null;
    private boolean buildTypes_release_minifyEnabled=false;
    private boolean buildTypes_release_shrinkResources=false;
    private File[] buildTypes_release_proguardFiles=null;
    private File externalNativeBuild_ndkBuild_path=null;
    private String ndk_moduleName=null;
    private String[] ndk_cppFlags=null;
    private String[] ndk_ldLibs=null;
    private String[] ndk_abiFilters=null;
    private String ndk_stl="c++_static";
    private float compileOptions_targetCompatibility=1.7f;
    private float compileOptions_sourceCompatibility=1.7f;
    
    private GradleProject g_project;
    
    public com_android_application(GradleProject g_project){
        this.g_project=g_project;
    }
    
    public int compileSdkVersion(){
        return compileSdkVersion;
    }
    public String buildToolsVersion(){
        return buildToolsVersion;
    }
    public String defaultConfig_applicationId(){
        return defaultConfig_applicationId;
    }
    public int defaultConfig_minSdkVersion(){
        return defaultConfig_minSdkVersion;
    }
    public int defaultConfig_targetSdkVersion(){
        return defaultConfig_targetSdkVersion;
    }
    public int defaultConfig_versionCode(){
        return defaultConfig_versionCode;
    }
    public String defaultConfig_versionName(){
        return defaultConfig_versionName;
    }
    public boolean buildTypes_debug_minifyEnabled(){
        return buildTypes_debug_minifyEnabled;
    }
    public boolean buildTypes_debug_shrinkResources(){
        return buildTypes_debug_shrinkResources;
    }
    public File[] buildTypes_debug_proguardFiles(){
        return buildTypes_debug_proguardFiles;
    }
    public boolean buildTypes_release_minifyEnabled(){
        return buildTypes_release_minifyEnabled;
    }
    public boolean buildTypes_release_shrinkResources(){
        return buildTypes_release_shrinkResources;
    }
    public File[] buildTypes_release_proguardFiles(){
        return buildTypes_release_proguardFiles;
    }
    public File externalNativeBuild_ndkBuild_path(){
        return externalNativeBuild_ndkBuild_path;
    }
    public String ndk_moduleName(){
        return ndk_moduleName;
    }
    public String[] ndk_cppFlags(){
        return ndk_cppFlags;
    }
    public String[] ndk_ldLibs(){
        return ndk_ldLibs;
    }
    public String[] ndk_abiFilters(){
        return ndk_abiFilters;
    }
    public String ndk_stl(){
        return ndk_stl;
    }
    public float compileOptions_targetCompatibility(){
        return compileOptions_targetCompatibility;
    }
    public float compileOptions_sourceCompatibility(){
        return compileOptions_sourceCompatibility;
    }
    @Override
    public void plugin_Visit(G_Tree tree){
        G_Tree.Node node;

        if((node=tree.getNode("android.compileSdkVersion"))!=null) 
            compileSdkVersion=(int)node.values().get(0).value();
        if((node=tree.getNode("android.buildToolsVersion"))!=null) 
            buildToolsVersion=(String)node.values().get(0).value();
        if((node=tree.getNode("android.defaultConfig.applicationId"))!=null) 
            defaultConfig_applicationId=(String)node.values().get(0).value();
        if((node=tree.getNode("android.defaultConfig.minSdkVersion"))!=null) 
            defaultConfig_minSdkVersion=(int)node.values().get(0).value();
        if((node=tree.getNode("android.defaultConfig.targetSdkVersion"))!=null) 
            defaultConfig_targetSdkVersion=(int)node.values().get(0).value();
        if((node=tree.getNode("android.defaultConfig.versionCode"))!=null) 
            defaultConfig_versionCode=(int)node.values().get(0).value();
        if((node=tree.getNode("android.defaultConfig.versionName"))!=null) 
            defaultConfig_versionName=(String)node.values().get(0).value();
        if((node=tree.getNode("android.buildTypes.debug.minifyEnabled"))!=null) 
            buildTypes_debug_minifyEnabled=(boolean)node.values().get(0).value();
        if((node=tree.getNode("android.buildTypes.debug.shrinkResources"))!=null) 
            buildTypes_debug_shrinkResources=(boolean)node.values().get(0).value();
        if((node=tree.getNode("android.buildTypes.debug.proguardFiles"))!=null) 
            buildTypes_debug_proguardFiles= handle_android_buildTypes_debug_proguardFiles(node);
        if((node=tree.getNode("android.buildTypes.release.minifyEnabled"))!=null) 
            buildTypes_release_minifyEnabled=(boolean)node.values().get(0).value();
        if((node=tree.getNode("android.buildTypes.release.shrinkResources"))!=null) 
            buildTypes_release_shrinkResources=(boolean)node.values().get(0).value();
        if((node=tree.getNode("android.buildTypes.release.proguardFiles"))!=null) 
            buildTypes_release_proguardFiles= handle_android_buildTypes_release_proguardFiles(node);
        if((node=tree.getNode("android.externalNativeBuild.ndkBuild.path"))!=null) 
            externalNativeBuild_ndkBuild_path= handle_android_externalNativeBuild_ndkBuild_path(node);
        if((node=tree.getNode("android.ndk.moduleName"))!=null) 
            ndk_moduleName=(String)node.values().get(0).value();
        if((node=tree.getNode("android.ndk.cppFlags"))!=null) 
            ndk_cppFlags=getStrings((List<StringLiteral>)node.values());
        if((node=tree.getNode("android.ndk.ldLibs"))!=null) 
            ndk_ldLibs=getStrings((List<StringLiteral>)node.values());
        if((node=tree.getNode("android.ndk.abiFilters"))!=null) 
            ndk_abiFilters=getStrings((List<StringLiteral>)node.values());
        if((node=tree.getNode("android.ndk.stl"))!=null) 
            ndk_stl=(String)node.values().get(0).value();
        if((node=tree.getNode("android.compileOptions.targetCompatibility"))!=null) 
            compileOptions_targetCompatibility=(float)node.values().get(0).value();
        if((node=tree.getNode("android.compileOptions.sourceCompatibility"))!=null) 
            compileOptions_sourceCompatibility=(float)node.values().get(0).value();
    }
    
    @Override
    public Runnable plugin_Task(final GradleProject gp){
        return new Runnable(){
            @Override
            public void run(){

                try{
                    ToolChain tc=gp.tool_chain;
                    File build_gradle=gp.build_gradle;

                    {//run aapt
                        File r_dir=new File(build_gradle.getParentFile(),"build/gen");
                        File resDir=new File(build_gradle.getParentFile(),"src/main/res");
                        File android_jar=new File(E_Application.getAppPrivateDir(),"android.jar");
                        File AndroidManifest_xml=new File(build_gradle.getParentFile(),"src/main/AndroidManifest.xml");
                        if(resDir.exists())
                            if(!tc.run_aapt_gen_r(r_dir,resDir,android_jar,AndroidManifest_xml))
                                throw new IOException("AAPT!!!!!");

                        File assetsDir=new File(build_gradle.getParentFile(),"src/main/assets");
                        File out_file=new File(build_gradle.getParentFile(),"build/bin/resources.zip");

                        if(!tc.run_aapt_pack(assetsDir,resDir,android_jar,AndroidManifest_xml,out_file))
                            throw new IOException("kk AAPT!!...");
                    }

                    {//run ecj

                        File android_jar=new File(E_Application.getAppPrivateDir(),"android.jar");
                        File class_out_dir=new File(build_gradle.getParentFile(),"build/bin/class");
                        File src_dir=new File(build_gradle.getParentFile(),"src/main/java");
                        File r_dir=new File(build_gradle.getParentFile(),"build/gen");

                        boolean r;

                        if(r_dir.exists())
                            r=tc.run_ecj(android_jar,class_out_dir,src_dir,r_dir);
                        else
                            r=tc.run_ecj(android_jar,class_out_dir,src_dir);
                        if(!r)
                            throw new IOException("ECJ!!!!");
                    }

                    {//run dx
                        File classes_dir=new File(build_gradle.getParentFile(),"build/bin/class");
                        File jar_dir=new File(build_gradle.getParentFile(),"libs");
                        File jar_dex_dir=new File(build_gradle.getParentFile(),"build/bin/jar_dex");
                        File classes_dex=new File(build_gradle.getParentFile(),"build/bin/classes.dex");

                        if(jar_dir.exists())
                            if(!tc.run_dx_jars(jar_dir,jar_dex_dir))
                                throw new IOException("dx jars!!!!");
                        if(!tc.run_dx_classes(classes_dir,classes_dex))
                            throw new IOException("dx classes!!!!");
                    }
                    
                    {//build native lib
                        if(externalNativeBuild_ndkBuild_path!=null){
                            File ndk_build =new File(E_Application.getNdkDir(),"ndk-build");
                            File pDir=externalNativeBuild_ndkBuild_path.getParentFile().getParentFile();

                            ProcessBuilder pBuilder=new ProcessBuilder();
                            pBuilder.environment().put("PATH",E_Application.getBinDir().getAbsolutePath());
                            pBuilder.environment().put("TEMPDIR",E_Application.getTmpDir().getAbsolutePath());//clang编译需要
 
                            pBuilder.command(ndk_build.getAbsolutePath(),"-C",pDir.getAbsolutePath())
                                    .start().waitFor();
                        }
                    }

                    {//create apk
                        ByteArrayOutputStream apk_buf=new ByteArrayOutputStream();
                        ZipOutputStream apk_strm=new ZipOutputStream(apk_buf);    

                        File assetsDir=new File(build_gradle.getParentFile(),"src/main/assets");
                        ZipFile aapt_resources=new ZipFile(new File(build_gradle.getParentFile(),"build/bin/resources.zip"));
                        File classes_dex=new File(build_gradle.getParentFile(),"build/bin/classes.dex");
                        File output_apk=new File(build_gradle.getParentFile(),"build/bin/o.apk");
                        File libs_dir=new File(build_gradle.getParentFile(),"src/main/libs");
                        
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
                        File input_apk=new File(build_gradle.getParentFile(),"build/bin/o.apk");
                        File output_apk=new File(build_gradle.getParentFile(),"build/bin/o_sign.apk");
                        File ks=new File(E_Application.getAppPrivateDir(),"test.ks");//FIXME
                        String pass="android";//FIXME
                        if(!tc.run_apksigner(input_apk,output_apk,ks,pass))
                            throw new IOException("签名失败!!!!");        
                    }

                    {//install apk

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        File apk=new File(build_gradle.getParentFile(),"build/bin/o_sign.apk");
                        intent.setDataAndType(Uri.fromFile(apk),"application/vnd.android.package-archive");       
                        gp.context.startActivity(intent);
                    }

                }catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        };
    }
    
    private String[] getStrings(List<StringLiteral> list){
        int count=list.size();
        if(count==0) return null;
        
        String[] r=new String[count];
        for(int i=0;i<count;i++)
            r[i]=list.get(i).value();
        return r;
    }

    private File handle_android_externalNativeBuild_ndkBuild_path(G_Tree.Node node){
        return new File(g_project.getProjectDir(),(String)node.values().get(0).value());
    }

    private File[] handle_android_buildTypes_release_proguardFiles(G_Tree.Node node){
        return null;
    }

    private File[] handle_android_buildTypes_debug_proguardFiles(G_Tree.Node node){
        return null;
    }
}
