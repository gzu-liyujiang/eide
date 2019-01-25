
//license wtfpl 2.0

//by aenu 2018/10/22
//   email:202983447@qq.com

package aenu.eide;
import android.app.Activity;
import android.os.Bundle;
import aenu.eide.view.TermView;
import android.util.DisplayMetrics;
import java.io.IOException;
import android.content.Intent;
import android.view.KeyEvent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import dalvik.system.DexClassLoader;
import java.io.File;

public class E_TermActivity extends Activity{
    
    /*public static final String ACTION_TERM_EXEC="eide.intent.action.TERM_EXEC";
    public static final String EXTRA_COMMAND="extra.command";*/
    
    public static final String ACTION_TERM_EXEC="eide.intent.action.TERM_EXEC";
    public static final String ACTION_JAVA_TERM_EXEC="eide.intent.action.JAVA_TERM_EXEC";
    public static final String EXTRA_BIN="extra.bin";
    public static final String EXTRA_ARGS="extra.args";
    public static final String EXTRA_JAVA_MAIN_CLASS="extra.java_main_class";
    
    
    private TermView term_view;
    private Intent intent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term);
        term_view=(TermView) findViewById(R.id.term);
        
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        term_view.setDensity(dm);
        
        try{
            /*
            final String action=getAction();  
            String command=null;
            if(action!=null&&action.equals(ACTION_TERM_EXEC))
                command=getIntent().getStringExtra(EXTRA_COMMAND);
                
            term_view.attachSession(new E_TermSession(command,false));*/
            String action=getAction();
            if(action!=null&&action.equals(ACTION_JAVA_TERM_EXEC)){
                DexClassLoader DCL=getDCL(getIntent().getStringExtra(EXTRA_BIN));
                Class<?> main_class=DCL.loadClass(getIntent().getStringExtra(EXTRA_JAVA_MAIN_CLASS));
                String[] args=getIntent().getStringArrayExtra(EXTRA_ARGS);

                term_view.attachSession(new E_JavaTermSession(DCL,main_class,args));
            }
            else if(action!=null&&action.equals(ACTION_TERM_EXEC)){
                E_TermSession TS=  new E_TermSession(getIntent().getStringExtra(EXTRA_BIN),false);
                
                term_view.attachSession(TS);

            }
            else{
                term_view.attachSession(new E_TermSession(null,false));
            }
            
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        intent=new Intent(this,E_TermService.class);
        
        startService(intent);
    }
    
    private String getAction(){
        Intent I=getIntent();
        if(I==null) return null;
        return I.getAction();
    }

    private DexClassLoader getDCL(String jar_path){

        File dex_OD=new File(E_Application.getTmpDir(),Long.toString(System.currentTimeMillis()));
        dex_OD.mkdir();
        return new DexClassLoader(jar_path,dex_OD.getAbsolutePath(),null,getClassLoader());
    }
    
    @Override
    protected void onDestroy(){
        stopService(intent);
        super.onDestroy();
    }
    

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            final DialogInterface.OnClickListener l=new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){
                    switch(which){
                        case DialogInterface.BUTTON_NEGATIVE:
                            finish();
                            break;
                        case DialogInterface.BUTTON_POSITIVE:
                            startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME));
                            break;
                        
                    }
                }            
            };
            
            AlertDialog.Builder b=new AlertDialog.Builder(this);
            b.setMessage("退出或后台运行");
            b.setNegativeButton("退出",l);
            b.setPositiveButton("后台",l);
            b.show();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
