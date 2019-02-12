
//license wtfpl 2.0

//by aenu 2018/10/22
//   email:202983447@qq.com

package aenu.eide;

import aenu.eide.PL.CxxLanguage;
import aenu.eide.diagnostic.DiagnosticCallback;
import aenu.eide.diagnostic.DiagnosticMessage;
import aenu.eide.gradle_impl.GradleProject;
import aenu.eide.view.CodeEditor;
import aenu.eide.view.CxxEditor;
import aenu.eide.view.GradleEditor;
import aenu.eide.view.JavaEditor;
import aenu.eide.view.ProjectPage;
import aenu.eide.view.ProjectView;
import aenu.eide.view.XmlEditor;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.myopicmobile.textwarrior.android.ICodeDiag;
import aenu.eide.diagnostic.JavaDiagnostic;
import aenu.eide.diagnostic.CxDiagnostic;
import android.app.*;
import android.content.*;
import android.net.*;

public class E_MainActivity extends AppCompatActivity implements RequestListener
{

    public static final int REQUEST_OPEN_PROJECT=0x3584;
    public static final int REQUEST_OPEN_FILE=0x3585;
    
    public final int event_click_project_icon=0xaa123456;
    public final int event_open_project=0xaa123457;
    public final int event_open_file=0xaa123458;
    
    private GradleProject mGradleProject;
    private E_DiagnosticServer diagnostic_server;
    private ProjectPage project_view;
    
    private final Map<Class,CodeEditor> EDITOR=new HashMap<>();
    private DrawerLayout drawer;
    private ViewPager pager;
    
    private final E_DiagnosticServer.Callback diag_cb=new E_DiagnosticServer.Callback(){
        @Override
        public void onChanged(){
            runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        CodeEditor editor=getCurrentEditor();
						ICodeDiag diag=editor!=null?editor.getCodeDiag():null;
						
						project_view.setWarnings(diagnostic_server.getAllWarnings());
						project_view.setErrors(diagnostic_server.getAllErrors());
						
                        if(diag!=null){
							
							List<DiagnosticMessage> warnings= diagnostic_server.getWarnings(editor.getPath());
							List<DiagnosticMessage> errors= diagnostic_server.getErrors(editor.getPath());
							
                            diag.warnings=convert_info(warnings);
                            diag.errors=convert_info(errors);     
							
							editor.invalidate();
                        }
						
                    }
                });
        }
/*

        @Override
        public void onClearError(final DiagnosticMessage msg){
            runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        project_view.removeError(msg);
                    }
                });
        }

        @Override
        public void onClearWarning(final DiagnosticMessage msg){
            runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        project_view.removeWarning(msg);
                    }
                });  
        }

        @Override
        public void onNewError(final DiagnosticMessage msg){
            runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        project_view.addError(msg);
                    }
                });
        }

        @Override
        public void onNewWarning(final DiagnosticMessage msg){
            runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        project_view.addWarning(msg);
                    }
                });
        }*/
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
             
        this.drawer= (DrawerLayout) findViewById(R.id.drawer);
        this.pager=(ViewPager) findViewById(R.id.project_pager);
        this.pager.setAdapter(new ProjectPage(this,null,null));
        
        initEditor();
        initActionBar();
        
        if(!E_Application.getAppVersion().equals(
          E_Application.getConfigVersion(this)))      
                  E_Installer.install(this,
                    ProgressDialog.show(this, null, ">>>>", true, false));      
        
    }
  
    
    private void initEditor(){
        EDITOR.put(CodeEditor.class,new CodeEditor(this,R.style.FreeScrollingTextField_Light));
        EDITOR.put(JavaEditor.class,new JavaEditor(this,R.style.FreeScrollingTextField_Light));
        EDITOR.put(CxxEditor.class,new CxxEditor(this,R.style.FreeScrollingTextField_Light));  
        EDITOR.put(GradleEditor.class,new GradleEditor(this,R.style.FreeScrollingTextField_Light));  
        EDITOR.put(XmlEditor.class,new XmlEditor(this,R.style.FreeScrollingTextField_Light));         
    }
    
    private void initActionBar(){
        
        Toolbar tBar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tBar);
        
        tBar.setNavigationIcon(R.drawable.ic_p_show);
        tBar.setTag(R.drawable.ic_p_show);
        tBar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View p1){   
                on_event(event_click_project_icon);
            }
        });
        
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    
    private void swapProjectIcon(){
        Toolbar tBar= (Toolbar) findViewById(R.id.toolbar);
        int projectNewI=((int)tBar.getTag())^R.drawable.ic_p_show^R.drawable.ic_p_hide;
        
        tBar.setNavigationIcon(projectNewI);
        tBar.setTag(projectNewI);                     
    }
    
    
    public void on_event(int id,Object... data){
        switch(id){
            case event_click_project_icon:
                if(!drawer.isDrawerOpen(Gravity.LEFT))
                drawer.openDrawer(Gravity.LEFT);
                else
                    drawer.closeDrawer(Gravity.LEFT);
            
                swapProjectIcon();          
                break;
               case event_open_project:
                   mGradleProject=GradleProject.open(this,new File(data[0]+"/build.gradle"));
           project_view= new ProjectPage(this,new File((String)data[0]),this);
            
            pager.setAdapter(project_view);
            drawer.openDrawer(Gravity.LEFT);
           diag_stop();
           diag_start();
                   break;
            case R.id.menu_file_browser:  
            
              startActivityForResult(new Intent(this,E_FileActivity.class),REQUEST_OPEN_PROJECT);
            
            break;
            case R.id.menu_open_term:
                startActivity(new Intent(this,E_TermActivity.class));
                break;
                
            case R.id.menu_compile:
                if(mGradleProject!=null){
                    
                   codeSave();
                    
                   try{
                        mGradleProject.build(this);
                        Toast.makeText(this,"成功",1).show();                       
                    }catch (Exception e) {    
                        new AlertDialog.Builder(this)
                          .setTitle("编译失败")
                          .setMessage(e.toString())
                          .create()
                          .show();
                    }finally{
                      //  d.dismiss();
                    }
                }break;
            case R.id.menu_undo:
                codeUndo();
                break;
            case R.id.menu_redo:
                codeRedo();
                break;
			case R.id.menu_donate:
				
				{
					try{
						Intent intent=new Intent(Intent.ACTION_VIEW);
						intent.setData(E_Application.getAlipayDonateUri());
						startActivity(intent);
					}catch(Exception e){
						Toast.makeText(this,"需要安装支付宝 >> "+e,1).show();
					}
				}
				break;
            default: break;
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        if(mGradleProject==null)
            menu.findItem(R.id.menu_compile).setVisible(false);
        else
            menu.findItem(R.id.menu_compile).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        on_event(item.getItemId());
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==RESULT_OK){
            switch(requestCode){
                case REQUEST_OPEN_PROJECT:
                    on_event(event_open_project,data.getData().getPath());
                    break;
            }
        }
        
        super.onActivityResult(requestCode, resultCode, data);
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
    
    private void diag_start(){
        diagnostic_server=new E_DiagnosticServer();
        diagnostic_server.addCallback(diag_cb);
        diagnostic_server.start();
        
        diagnostic_server.attachDiagnostic(new JavaDiagnostic(mGradleProject.getJavaDir(),null));
        diag_add_cxdiag(diagnostic_server,mGradleProject.getJniDir());
    }
    
    private void diag_add_cxdiag(E_DiagnosticServer ser,File dir){
        
        if(!dir.exists()) return;
        
        File[] list=dir.listFiles();
        if(list==null) return;
        for(File file:list){
            if(file.isDirectory())
                diag_add_cxdiag(ser,file);
        else if(file.getName().endsWith(".cpp")
          ||file.getName().endsWith(".cxx")
          ||file.getName().endsWith(".cc"))
            ser.attachDiagnostic(new CxDiagnostic(file,CxxLanguage.defaultCppFlag()));
        else if(file.getName().endsWith(".c"))
            ser.attachDiagnostic(new CxDiagnostic(file,CxxLanguage.defaultCFlag()));
        else
            continue;
        }
    }
    
    private void diag_stop(){
        if(diagnostic_server!=null)
            diagnostic_server.stop();
    }
    
    private void codeSave(){  
        CodeEditor editor=getCurrentEditor();
        try{
            if(editor!=null) editor.save();
        }catch (IOException e) {}     
    }
    
    private void codeUndo(){
        CodeEditor editor=getCurrentEditor();
        if(editor!=null) editor.undo();     
    }
    
    private void codeRedo(){
        CodeEditor editor=getCurrentEditor();
        if(editor!=null) editor.redo();  
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
            return new Object[] {CxxLanguage.defaultCFlag(),diagnostic_server};    
 
        else if(file.getName().endsWith(".cpp")
        ||file.getName().endsWith(".cxx")
        ||file.getName().endsWith(".cc")
        ||file.getName().endsWith(".hpp")
        ||file.getName().endsWith(".hxx")
        ||file.getName().endsWith(".hh"))
            return new Object[] {CxxLanguage.defaultCFlag(),diagnostic_server};  

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
                        ICodeDiag diag=editor!=null?editor.getCodeDiag():null;
                        if(diag!=null){
                            diag.warnings=convert_info(diagnostic_server.getWarnings(editor.getPath()));
                            diag.errors=convert_info(diagnostic_server.getErrors(editor.getPath()));                 
                        }        
					}
                }catch(IOException e){
                }
            }break;
            }
        
        return true;
    }
    
    public List<ICodeDiag.DiagInfo> convert_info(List<DiagnosticMessage> list){
        final int s= list.size();
        List<ICodeDiag.DiagInfo> r=new ArrayList<>();
        for(int i=0;i<s;i++){
            DiagnosticMessage in=list.get(i);         
            ICodeDiag.DiagInfo out=new ICodeDiag.DiagInfo(in.start_position,in.end_position,in.text);
            r.add(i,out);
        }
        
        return r;
    }
}
