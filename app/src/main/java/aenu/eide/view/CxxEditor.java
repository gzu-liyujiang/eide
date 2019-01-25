
//license wtfpl 2.0

//by  aenu
//  ----
//    email:202983447@qq.com

package aenu.eide.view;
import com.myopicmobile.textwarrior.android.FreeScrollingTextField;
import android.content.Context;
import android.util.AttributeSet;import android.graphics.Typeface;
import com.myopicmobile.textwarrior.android.YoyoNavigationMethod;
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
import com.myopicmobile.textwarrior.common.ILanguage;
import aenu.eide.PL.CxxLanguage;
import aenu.eide.PL.CxxLexer;
import aenu.eide.PL.CxxAutoCompletePanel;
import android.util.Log;
import aenu.eide.diagnostic.CXProjectDiagnostic;
import aenu.eide.PL.CxxCodeDiag;
import aenu.eide.diagnostic.ProjectDiagnostic;


public class CxxEditor extends CodeEditor {

    public CxxEditor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CxxEditor(Context context,int style) {
        super(context,style);
    }

    public void read(File file,Object... v) throws IOException {

        final String path=file.getAbsolutePath();
        final String[] flags=(String[]) v[0];
        final ProjectDiagnostic diag=(ProjectDiagnostic) v[1];
        
        setLanguage(new CxxLanguage(new CxxLexer(path,flags)));
        setAutoComplete(new CxxAutoCompletePanel(this,path,flags));
        
        if(diag!=null)
            setCodeDiag(new CxxCodeDiag(diag,file));
            
        super.read(file,v);
    }
}
