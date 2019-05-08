

//license wtfpl 2.0

//by aenu 2018,2019
//   email:202983447@qq.com

package aenu.eide.gradle_impl.plugin;

import aenu.eide.E_Application;
import aenu.eide.gradle_impl.GradleProject;
import aenu.eide.gradle_impl.IPlugin;
import aenu.eide.gradle_impl.ToolChain;
import aenu.gradle.G_Tree;
import aenu.gradle.expr.Expression;

import java.io.File;
import java.io.IOException;
import java.util.List;

import aenu.eide.gradle_impl.ToolChainHelper;

public class com_android_library implements IPlugin {

    private int compileSdkVersion = 21;
    private String buildToolsVersion = "21.1.0";
    private String defaultConfig_applicationId = null;
    private int defaultConfig_minSdkVersion = 14;
    private int defaultConfig_targetSdkVersion = 21;
    private int defaultConfig_versionCode = 1;
    private String defaultConfig_versionName = "1.0";
    private boolean buildTypes_debug_minifyEnabled = false;
    private boolean buildTypes_debug_shrinkResources = false;
    private File[] buildTypes_debug_proguardFiles = null;
    private boolean buildTypes_release_minifyEnabled = false;
    private boolean buildTypes_release_shrinkResources = false;
    private File[] buildTypes_release_proguardFiles = null;
    private File externalNativeBuild_ndkBuild_path = null;
    private String ndk_moduleName = null;
    private String[] ndk_cppFlags = null;
    private String[] ndk_ldLibs = null;
    private String[] ndk_abiFilters = null;
    private String ndk_stl = "c++_static";
    private float compileOptions_targetCompatibility = 1.7f;
    private float compileOptions_sourceCompatibility = 1.7f;

    private GradleProject g_project;

    public com_android_library(GradleProject g_project) {
        this.g_project = g_project;
    }

    public int compileSdkVersion() {
        return compileSdkVersion;
    }

    public String buildToolsVersion() {
        return buildToolsVersion;
    }

    public String defaultConfig_applicationId() {
        return defaultConfig_applicationId;
    }

    public int defaultConfig_minSdkVersion() {
        return defaultConfig_minSdkVersion;
    }

    public int defaultConfig_targetSdkVersion() {
        return defaultConfig_targetSdkVersion;
    }

    public int defaultConfig_versionCode() {
        return defaultConfig_versionCode;
    }

    public String defaultConfig_versionName() {
        return defaultConfig_versionName;
    }

    public boolean buildTypes_debug_minifyEnabled() {
        return buildTypes_debug_minifyEnabled;
    }

    public boolean buildTypes_debug_shrinkResources() {
        return buildTypes_debug_shrinkResources;
    }

    public File[] buildTypes_debug_proguardFiles() {
        return buildTypes_debug_proguardFiles;
    }

    public boolean buildTypes_release_minifyEnabled() {
        return buildTypes_release_minifyEnabled;
    }

    public boolean buildTypes_release_shrinkResources() {
        return buildTypes_release_shrinkResources;
    }

    public File[] buildTypes_release_proguardFiles() {
        return buildTypes_release_proguardFiles;
    }

    public File externalNativeBuild_ndkBuild_path() {
        return externalNativeBuild_ndkBuild_path;
    }

    public String ndk_moduleName() {
        return ndk_moduleName;
    }

    public String[] ndk_cppFlags() {
        return ndk_cppFlags;
    }

    public String[] ndk_ldLibs() {
        return ndk_ldLibs;
    }

    public String[] ndk_abiFilters() {
        return ndk_abiFilters;
    }

    public String ndk_stl() {
        return ndk_stl;
    }

    public float compileOptions_targetCompatibility() {
        return compileOptions_targetCompatibility;
    }

    public float compileOptions_sourceCompatibility() {
        return compileOptions_sourceCompatibility;
    }

    @Override
    public void plugin_Visit(G_Tree tree) {
        G_Tree.Node node;

        if ((node = tree.getNode("android.compileSdkVersion")) != null)
            compileSdkVersion = Integer.parseInt(node.values().get(0).value().toString());
        if ((node = tree.getNode("android.buildToolsVersion")) != null)
            buildToolsVersion = (String) node.values().get(0).value();
        if ((node = tree.getNode("android.defaultConfig.applicationId")) != null)
            defaultConfig_applicationId = (String) node.values().get(0).value();
        if ((node = tree.getNode("android.defaultConfig.minSdkVersion")) != null)
            defaultConfig_minSdkVersion = Integer.parseInt(node.values().get(0).value().toString());
        if ((node = tree.getNode("android.defaultConfig.targetSdkVersion")) != null)
            defaultConfig_targetSdkVersion = Integer.parseInt(node.values().get(0).value().toString());
        if ((node = tree.getNode("android.defaultConfig.versionCode")) != null)
            defaultConfig_versionCode = Integer.parseInt(node.values().get(0).value().toString());
        if ((node = tree.getNode("android.defaultConfig.versionName")) != null)
            defaultConfig_versionName = node.values().get(0).value().toString();
        if ((node = tree.getNode("android.buildTypes.debug.minifyEnabled")) != null)
            buildTypes_debug_minifyEnabled = Boolean.parseBoolean(node.values().get(0).value().toString());
        if ((node = tree.getNode("android.buildTypes.debug.shrinkResources")) != null)
            buildTypes_debug_shrinkResources = Boolean.parseBoolean(node.values().get(0).value().toString());
        if ((node = tree.getNode("android.buildTypes.debug.proguardFiles")) != null)
            buildTypes_debug_proguardFiles = handle_android_buildTypes_debug_proguardFiles(node);
        if ((node = tree.getNode("android.buildTypes.release.minifyEnabled")) != null)
            buildTypes_release_minifyEnabled = Boolean.parseBoolean(node.values().get(0).value().toString());
        if ((node = tree.getNode("android.buildTypes.release.shrinkResources")) != null)
            buildTypes_release_shrinkResources = Boolean.parseBoolean(node.values().get(0).value().toString());
        if ((node = tree.getNode("android.buildTypes.release.proguardFiles")) != null)
            buildTypes_release_proguardFiles = handle_android_buildTypes_release_proguardFiles(node);
        if ((node = tree.getNode("android.externalNativeBuild.ndkBuild.path")) != null)
            externalNativeBuild_ndkBuild_path = handle_android_externalNativeBuild_ndkBuild_path(node);
        if ((node = tree.getNode("android.ndk.moduleName")) != null)
            ndk_moduleName = (String) node.values().get(0).value();
        if ((node = tree.getNode("android.ndk.cppFlags")) != null)
            ndk_cppFlags = getStrings(node.values());
        if ((node = tree.getNode("android.ndk.ldLibs")) != null)
            ndk_ldLibs = getStrings(node.values());
        if ((node = tree.getNode("android.ndk.abiFilters")) != null)
            ndk_abiFilters = getStrings(node.values());
        if ((node = tree.getNode("android.ndk.stl")) != null)
            ndk_stl = (String) node.values().get(0).value();
        if ((node = tree.getNode("android.compileOptions.targetCompatibility")) != null)
            compileOptions_targetCompatibility = Float.parseFloat(node.values().get(0).value().toString());
        if ((node = tree.getNode("android.compileOptions.sourceCompatibility")) != null)
            compileOptions_sourceCompatibility = Float.parseFloat(node.values().get(0).value().toString());
    }

    @Override
    public Runnable plugin_Task(final GradleProject gp) {
        return new Runnable() {
            @Override
            public void run() {

                try {
                    ToolChain tc = gp.tool_chain;
                    final File p_dir = gp.getProjectDir();

                    {//run aapt
                        File r_dir = new File(p_dir, "build/gen");
                        File resDir = new File(p_dir, "src/main/res");
                        File android_jar = new File(E_Application.getAppPrivateDir(), "android.jar");
                        File AndroidManifest_xml = new File(p_dir, "src/main/AndroidManifest.xml");

                        r_dir.mkdirs();

                        final String gen_r_args = ToolChainHelper.generate_aapt_gen_r_args(r_dir, new File[]{resDir}, android_jar, AndroidManifest_xml, null);
                        if (resDir.exists())
                            if (!tc.run_aapt(gen_r_args))
                                throw new IOException("AAPT!!!!!");

                        File assetsDir = new File(p_dir, "src/main/assets");
                        File out_file = new File(p_dir, "build/bin/resources.zip");

                        out_file.getParentFile().mkdirs();

                        final String pkg_args = ToolChainHelper.generate_aapt_pkg_args(new File[]{resDir}, new File[]{assetsDir}, android_jar, AndroidManifest_xml, null, out_file);


                        if (!tc.run_aapt(pkg_args))
                            throw new IOException("kk AAPT!!...");
                    }

                    {//run ecj

                        File android_jar = new File(E_Application.getAppPrivateDir(), "android.jar");
                        File class_out_dir = new File(p_dir, "build/bin/class");
                        File src_dir = new File(p_dir, "src/main/java");
                        File r_dir = new File(p_dir, "build/gen");

                        class_out_dir.mkdirs();

                        String args;

                        if (r_dir.exists())
                            args = ToolChainHelper.generate_ecj_args(android_jar, class_out_dir, src_dir, r_dir);
                        else
                            args = ToolChainHelper.generate_ecj_args(android_jar, class_out_dir, src_dir);

                        if (!tc.run_ecj(args))
                            throw new IOException("ECJ!!!!");
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private String[] getStrings(List<Expression> list) {
        int count = list.size();
        if (count == 0) return null;

        String[] r = new String[count];
        for (int i = 0; i < count; i++)
            r[i] = list.get(i).value().toString();
        return r;
    }

    private File handle_android_externalNativeBuild_ndkBuild_path(G_Tree.Node node) {
        return new File(g_project.getProjectDir(), (String) node.values().get(0).value());
    }

    private File[] handle_android_buildTypes_release_proguardFiles(G_Tree.Node node) {
        return null;
    }

    private File[] handle_android_buildTypes_debug_proguardFiles(G_Tree.Node node) {
        return null;
    }
}
