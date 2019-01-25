
//license wtfpl 2.0

//by aenu 2018/10/22
//   email:202983447@qq.com

package aenu.eide;

import android.app.*;
import android.os.*;

import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;
import android.content.res.Resources;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import aenu.eide.fragment.FileBrowser;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import aenu.eide.view.JavaEditor;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.view.Gravity;
import aenu.eide.fragment.Project;
import java.io.File;
import android.view.ViewGroup;
import aenu.eide.util.IOUtils;
import java.io.IOException;
import android.content.Intent;
import java.util.Map;
import com.myopicmobile.textwarrior.android.FreeScrollingTextField;
import java.util.HashMap;
import aenu.eide.view.CxxEditor;
import aenu.eide.view.GradleEditor;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.ImageButton;
import aenu.eide.gradle_impl.GradleProject;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.MalformedURLException;
import aenu.eide.PL.CxxLanguage;
import aenu.eide.view.XmlEditor;
import aenu.eide.diagnostic.ProjectDiagnostic;
import aenu.eide.diagnostic.CXProjectDiagnostic;
import aenu.eide.diagnostic.DiagnosticCallback;
import aenu.eide.diagnostic.DiagnosticInfo;
import java.util.Set;
import aenu.eide.view.CodeEditor;
import android.support.v4.widget.DrawerLayout;

public class E_MainActivity extends Activity implements RequestListener,DiagnosticCallback
{
    public final static int REQUEST_OPEN_FILE=0;
    public final static int REQUEST_OPEN_PROJECT=1;
   
    private final int ID_project=0xaa123456;
    
    private Fragment file_browser;
    private Project project_tree;
    private GradleProject mGradleProject;
    private ProjectDiagnostic mProjectDiagnostic;
    
    private final Map<Class,CodeEditor> EDITOR=new HashMap<>();
    private final List<Fragment> backStack=new ArrayList<>();
    
    private final View.OnClickListener click_listener=new View.OnClickListener(){
        @Override
        public void onClick(View v){
            on_click(v.getId());
        }     
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        if(getWindowManager().getDefaultDisplay().getWidth()
        <getWindowManager().getDefaultDisplay().getHeight())
            initVerticalFragmentContainer();
        else
            initHorizonalFragmentContainer();

        initAndHideFragment();
        initEditor();
        initActionBar();
        
        if(!E_Application.getAppVersion().equals(
          E_Application.getConfigVersion(this))){
            try{
                  E_Installer.install_toolchain(this);
                  E_Installer.install_template_and_key(this);
                  E_Installer.install_ndk(this);
                  E_Application.updateConfigVersion(this);
            }
            catch (Exception e) {
                E_Application.clearConfigVersion(this);
                throw new RuntimeException(e);
            }
        }
    }
    
    private void initVerticalFragmentContainer(){
        Display display=getWindowManager().getDefaultDisplay();
        findViewById(R.id.file_browser).setPadding((int)(display.getWidth()*0.4f),10,10,10);
        findViewById(R.id.project_tree).setPadding(10,10,(int)(display.getWidth()*0.4f),10);
    }

    private void initHorizonalFragmentContainer(){
        Display display=getWindowManager().getDefaultDisplay();
        findViewById(R.id.file_browser).setPadding((int)(display.getWidth()*0.6f),10,10,10);   
        findViewById(R.id.project_tree).setPadding(10,10,(int)(display.getWidth()*0.6f),10);
    }

    private void initAndHideFragment(){
        file_browser=new FileBrowser(this);
        getFragmentManager().beginTransaction().add(R.id.file_browser,file_browser).detach(file_browser).commit();
        project_tree=new Project(this);
        getFragmentManager().beginTransaction().add(R.id.project_tree,project_tree).detach(project_tree).commit();     
    }
    
    private void initEditor(){
        EDITOR.put(JavaEditor.class,new JavaEditor(this,R.style.FreeScrollingTextField_Light));
        EDITOR.put(CxxEditor.class,new CxxEditor(this,R.style.FreeScrollingTextField_Light));  
        EDITOR.put(GradleEditor.class,new GradleEditor(this,R.style.FreeScrollingTextField_Light));  
        EDITOR.put(XmlEditor.class,new XmlEditor(this,R.style.FreeScrollingTextField_Light));         
    }
    
    private void initActionBar(){
        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setDisplayShowCustomEnabled(true);

        final ImageView project=new ImageView(this);
        project.setId(ID_project);
        project.setImageResource(R.drawable.project_open);
        project.setOnClickListener(click_listener);
        project.setTag(R.drawable.project_open);
        getActionBar().setCustomView(project,new ActionBar.LayoutParams(Gravity.LEFT));
    }
    
    private void addFragmentOnUI(Fragment f){
        getFragmentManager().beginTransaction().attach(f).commit();
        backStack.add(f);
    }

    private void removeFragmentOnUI(Fragment f){
        getFragmentManager().beginTransaction().detach(f).commit();
        backStack.remove(f);
    }
    
    private void swapProjectIcon(){
        ImageView projectV=(ImageView)getActionBar().getCustomView();
        int projectNewI=((int)projectV.getTag())^R.drawable.project_open^R.drawable.project_hide;
        projectV.setImageResource(projectNewI);
        projectV.setTag(projectNewI);                     
    }
    
    private void on_click(int id){
        switch(id){
            case ID_project:
                if(project_tree.isDetached())
                    addFragmentOnUI(project_tree);
                else
                    removeFragmentOnUI(project_tree);
                swapProjectIcon();          
                break;
            case R.id.menu_file_browser:  
            
                if(file_browser.isDetached())
                    addFragmentOnUI(file_browser);
                else
                    removeFragmentOnUI(file_browser);
                break;
            case R.id.menu_open_term:
                startActivity(new Intent(this,E_TermActivity.class));
                break;
            case R.id.menu_compile:
                if(mGradleProject!=null){
                    
                    {
                        CodeEditor editor=getCurrentEditor();
                        try{
                            if(editor!=null) editor.save();
                        }catch (IOException e) {}
                    }
                    
                   // ProgressDialog d=ProgressDialog.show(this,null,"编译中...",true,false);
                    try{
                        mGradleProject.build(this);
                        Toast.makeText(this,"成功",1).show();                       
                    }catch (Exception e) {
                        ByteArrayOutputStream b=new ByteArrayOutputStream();
                        PrintStream p=new PrintStream(b);
                        e.printStackTrace(p);
                        p.close();
                        new AlertDialog.Builder(this)
                          .setTitle("编译失败")
                          .setMessage(b.toString())
                          .create()
                          .show();
                    }finally{
                      //  d.dismiss();
                    }
                }
                     
            default: break;
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        on_click(item.getItemId());
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(backStack.size()!=0){
            Fragment f=backStack.get(backStack.size()-1);
            removeFragmentOnUI(f);
            if(f instanceof Project)
                swapProjectIcon();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    private CodeEditor getCurrentEditor(){
        ViewGroup code_edit_container=(ViewGroup)findViewById(R.id.code_edit_container);   
        if(code_edit_container.getChildCount()!=0)
            return (CodeEditor) code_edit_container.getChildAt(0);   
        return null;
    }
    
    private void removeCurrentEditor(){
        ViewGroup code_edit_container=(ViewGroup)findViewById(R.id.code_edit_container);   
        if(code_edit_container.getChildCount()!=0)
            code_edit_container.removeViewAt(0);    
    }
    
    private void setCurrentEditor(CodeEditor editor){
        ViewGroup code_edit_container=(ViewGroup)findViewById(R.id.code_edit_container);   
        if(code_edit_container.getChildCount()!=0)
            code_edit_container.removeViewAt(0);           
        code_edit_container.addView(editor);       
    }
    
    private CodeEditor getFileEditor(File file){
        if(file.getName().endsWith(".java"))                                             
            return EDITOR.get(JavaEditor.class);                                    
        else if(file.getName().endsWith(".c")
        ||file.getName().endsWith(".h")
        ||file.getName().endsWith(".cpp")
        ||file.getName().endsWith(".cxx")
        ||file.getName().endsWith(".cc")
        ||file.getName().endsWith(".hpp")
        ||file.getName().endsWith(".hxx")
        ||file.getName().endsWith(".hh")
        )
            return EDITOR.get(CxxEditor.class);
        else if(file.getName().endsWith(".gradle"))
            return EDITOR.get(GradleEditor.class);
        else if(file.getName().endsWith(".xml"))
            return EDITOR.get(XmlEditor.class);
        return null;
    }
    
    private Object[] getFileEditorArgs(File file){
        if(file.getName().endsWith(".c")
        ||file.getName().endsWith(".h"))
            return new Object[] {CxxLanguage.defaultCFlag(),mProjectDiagnostic};    
 
        else if(file.getName().endsWith(".cpp")
        ||file.getName().endsWith(".cxx")
        ||file.getName().endsWith(".cc")
        ||file.getName().endsWith(".hpp")
        ||file.getName().endsWith(".hxx")
        ||file.getName().endsWith(".hh"))
            return new Object[] {CxxLanguage.defaultCFlag(),mProjectDiagnostic};  

        return null;
    }
    
    @Override
    public boolean onRequest(int requestCode,Object data){
        switch(requestCode){
            
            case REQUEST_OPEN_FILE:{      
               
                try{
                    CodeEditor editor=getCurrentEditor();
                    if(editor!=null)editor.save();
                }
                catch(IOException e){
                    Toast.makeText(this,"文件保存失败!",1);
                    throw new RuntimeException(e);
                }
                
                try{              
                    CodeEditor editor=getFileEditor((File)data);
                    Object[] args=getFileEditorArgs((File)data);
                    if(editor!=null){
                        setCurrentEditor(editor);
                        editor.read((File)data,args);
                    }
                }catch(IOException e){
                }
            }break;
            
            case REQUEST_OPEN_PROJECT:{
                
                removeFragmentOnUI(file_browser);
                
                project_tree.setProjectDir((File)data);         
                mGradleProject=GradleProject.open(this,new File((File)data,"build.gradle"));
                mProjectDiagnostic=new CXProjectDiagnostic(mGradleProject,this);
                
                removeFragmentOnUI(project_tree);
                addFragmentOnUI(project_tree);
                
            }break;
                
        }
        return true;
    }
    
    @Override
    public void onDiagStart(){
        
    }

    private List<String> to_string_list(Map<File,List<DiagnosticInfo>> map){
        final ArrayList<String> list=new ArrayList<>();
        final Set<Map.Entry<File,List<DiagnosticInfo>>> entries=map.entrySet();
        if(entries==null)return list;
        for(Map.Entry<File,List<DiagnosticInfo>> e:entries){
            String fn=e.getKey().getAbsolutePath();
            List<DiagnosticInfo> infos=e.getValue();
            for(DiagnosticInfo info:infos){
                list.add(fn+":"+info.line+":"+info.column+":"+info.info);               
            }
        }
        return list;
    }
    
    @Override
    public void onDiagDone(){
        Log.i("eide","onDiagDone");
        printErrors(to_string_list(mProjectDiagnostic.errors));
        printWarnings(to_string_list(mProjectDiagnostic.warnings));
        
        project_tree.setErrors(mProjectDiagnostic.errors);
        project_tree.setWarnings(mProjectDiagnostic.warnings);
        
        boolean show_project_tree=!project_tree.isDetached();
        if(show_project_tree){
            removeFragmentOnUI(project_tree);
            addFragmentOnUI(project_tree);
        }
    }
    
    private void printErrors(List<String> list){
        for(String s:list)
        Log.i("eide","E:"+s);
    }
    private void printWarnings(List<String> list){
        for(String s:list)
            Log.i("eide","W:"+s);
    }
}
