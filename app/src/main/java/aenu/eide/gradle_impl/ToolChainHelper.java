package aenu.eide.gradle_impl;
import java.io.File;
import java.io.IOException;

public final class ToolChainHelper
{
	public static String generate_ecj_args(File android_jar,File class_out_dir,File... src_dirs){

        final StringBuilder cmds=new StringBuilder();

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
		
		return cmds.toString();
	}
	
	public static String generate_aapt_gen_r_args(File r_dir,File[] resDirs,File android_jar,File AndroidManifest_xml,File[] extra_pkg){
		final StringBuilder cmds=new StringBuilder();
		cmds.append("package");
		cmds.append(" ");
		cmds.append("--auto-add-overlay");
		cmds.append(" ");
		cmds.append("-m");
		cmds.append(" ");
		cmds.append("-J");
		cmds.append(" ");
		cmds.append(r_dir.getAbsolutePath());
		cmds.append(" ");
		
		for(File resdir:resDirs){
			cmds.append("-S");
			cmds.append(" ");
			cmds.append(resdir.getAbsolutePath());
			cmds.append(" ");
		}
		
		cmds.append("-I");
		cmds.append(" ");
		cmds.append(android_jar.getAbsolutePath());
		cmds.append(" ");
		cmds.append("-M");
		cmds.append(" ");
		cmds.append(AndroidManifest_xml.getAbsolutePath());
		
		if(extra_pkg!=null&&extra_pkg.length!=0){
			cmds.append(" ");
			cmds.append("--extra-packages");
			cmds.append(" ");
			for(int i=0;i<extra_pkg.length;i++){
				cmds.append(extra_pkg[i].getAbsolutePath());
				if(i!=extra_pkg.length-1)cmds.append(':');
			}
		}
			
		
		return cmds.toString();
	}
	
	public static String generate_aapt_pkg_args(File[] resDirs,File[] assetsDirs,File android_jar,File AndroidManifest_xml,File[] extra_pkg,File outFile){
		final StringBuilder cmds=new StringBuilder();
		cmds.append("package");
		cmds.append(" ");
		cmds.append("--auto-add-overlay");
		cmds.append(" ");
		cmds.append("-f");
        cmds.append(" ");

		for(File resdir:resDirs){
			cmds.append("-S");
			cmds.append(" ");
			cmds.append(resdir.getAbsolutePath());
			cmds.append(" ");
		}
		
		if(assetsDirs==null||assetsDirs.length==0){
			//cmds.append("--ignore-assets");
			//cmds.append(" ");
		}
		else 
			for(File assetsdir:assetsDirs){
				cmds.append("-A");
				cmds.append(" ");
				cmds.append(assetsdir.getAbsolutePath());
				cmds.append(" ");
		    }
		

		cmds.append("-I");
		cmds.append(" ");
		cmds.append(android_jar.getAbsolutePath());
		cmds.append(" ");
		cmds.append("-M");
		cmds.append(" ");
		cmds.append(AndroidManifest_xml.getAbsolutePath());
		cmds.append(" ");
		if(extra_pkg!=null&&extra_pkg.length!=0){
			
			cmds.append("--extra-packages");
			cmds.append(" ");
			for(int i=0;i<extra_pkg.length;i++){
				cmds.append(extra_pkg[i].getAbsolutePath());
				if(i!=extra_pkg.length-1)cmds.append(':');
			}
			cmds.append(" ");
		}
		
		cmds.append("-F");
        cmds.append(" ");
        cmds.append(outFile.getAbsolutePath());
        

		return cmds.toString();
	}
	
	public static String generate_dx_jar_args(File jar,File out_dex){
		final StringBuilder cmds=new StringBuilder();
		cmds.append("--dex");
		cmds.append(" ");
		cmds.append("--output="+out_dex.getAbsolutePath());
		cmds.append(" ");
		cmds.append(jar.getAbsolutePath());
		return cmds.toString();
	}
	
	public static String generate_dx_classes_args(File classes_dir,File out_dex){
		final StringBuilder cmds=new StringBuilder();
		cmds.append("--dex");
		cmds.append(" ");
		cmds.append("--output="+out_dex.getAbsolutePath());
		cmds.append(" ");
		cmds.append(classes_dir.getAbsolutePath());
		return cmds.toString();
	}
	
	public static String generate_apksigner_args(File inApk,File outApk,File ks,String pass){
		final StringBuilder cmds=new StringBuilder();
		cmds.append("sign");
		cmds.append(" ");
		cmds.append("--ks");
		cmds.append(" ");
		cmds.append(ks.getAbsolutePath());
		cmds.append(" ");
		cmds.append("--ks-pass");
		cmds.append(" ");
		cmds.append("pass:"+pass);
		cmds.append(" ");
		cmds.append("--out");
		cmds.append(" ");	
		cmds.append(outApk.getAbsolutePath());
		cmds.append(" ");
		cmds.append(inApk.getAbsolutePath());	
		return cmds.toString();
	}
}
