
//license wtfpl 2.0

//by aenu 2018/10/22
//   email:202983447@qq.com

package aenu.eide.view;
import com.myopicmobile.textwarrior.android.FreeScrollingTextField;
import android.content.Context;
import android.util.AttributeSet;
//import com.myopicmobile.textwarrior.android.AutoCompletePanel;
//import com.myopicmobile.textwarrior.common.LanguageJava;
import android.graphics.Typeface;
import com.myopicmobile.textwarrior.android.YoyoNavigationMethod;
//import com.myopicmobile.textwarrior.common.LinearSearchStrategy;
import android.widget.Toast;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import java.io.IOException;
import com.myopicmobile.textwarrior.common.Document;
import com.myopicmobile.textwarrior.common.DocumentProvider;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import aenu.eide.util.IOUtils;
import com.myopicmobile.textwarrior.common.LanguageNonProg;
import aenu.eide.PL.JavaLanguage;
import java.io.File;
import aenu.eide.PL.JavaAutoCompletePanel;
import com.myopicmobile.textwarrior.common.ILanguage;

public class JavaEditor extends CodeEditor {

    public JavaEditor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLanguage(new JavaLanguage(new File("/sdcard/.aide/android.jar")));     
    }
    
    public JavaEditor(Context context,int style) {
        super(context,style);
        setLanguage(new JavaLanguage(new File("/sdcard/.aide/android.jar")));     
    }
}
