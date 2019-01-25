package aenu.eide.view;
import com.myopicmobile.textwarrior.android.FreeScrollingTextField;
import aenu.eide.PL.GradleLanguage;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Typeface;
import com.myopicmobile.textwarrior.android.YoyoNavigationMethod;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import java.io.IOException;
import aenu.eide.util.IOUtils;
import com.myopicmobile.textwarrior.common.Document;
import com.myopicmobile.textwarrior.common.DocumentProvider;
import java.io.File;

public class GradleEditor extends CodeEditor {

    public GradleEditor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLanguage(new GradleLanguage());
    }

    public GradleEditor(Context context,int style) {
        super(context,style);
        setLanguage(new GradleLanguage());   
    }
}
