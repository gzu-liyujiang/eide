package aenu.eide.view;
import com.myopicmobile.textwarrior.android.FreeScrollingTextField;
import android.content.Context;
import android.util.AttributeSet;
import aenu.eide.PL.XmlLang;
import android.graphics.Typeface;
import com.myopicmobile.textwarrior.android.YoyoNavigationMethod;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import java.io.IOException;
import aenu.eide.util.IOUtils;
import com.myopicmobile.textwarrior.common.Document;
import com.myopicmobile.textwarrior.common.DocumentProvider;
import java.io.File;

public class XmlEditor extends CodeEditor {

    public XmlEditor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLanguage(new XmlLang());
    }

    public XmlEditor(Context context,int style) {
        super(context,style);
        setLanguage(new XmlLang());   
    }
}
