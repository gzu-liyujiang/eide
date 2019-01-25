package aenu.eide.view;
import com.myopicmobile.textwarrior.android.FreeScrollingTextField;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Typeface;
import com.myopicmobile.textwarrior.android.YoyoNavigationMethod;
import java.io.File;
import java.io.IOException;
import com.myopicmobile.textwarrior.common.Document;
import com.myopicmobile.textwarrior.common.DocumentProvider;
import aenu.eide.util.IOUtils;

public class CodeEditor extends FreeScrollingTextField
{
    private String path;
    
    public CodeEditor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setShowLineNumbers(true);
        setWordWrap(false);
        setTabSpaces(4);
        setAutoIndentWidth(4);  
        setTypeface(Typeface.MONOSPACE);
        setNavigationMethod((new YoyoNavigationMethod(this)));
    }

    public CodeEditor(Context context,int style) {
        super(context,style);
        setShowLineNumbers(true);
        setWordWrap(false);
        setTabSpaces(4);
        setAutoIndentWidth(4);   
        setTypeface(Typeface.MONOSPACE);
        setNavigationMethod((new YoyoNavigationMethod(this)));
    }
    
    public final void setText(CharSequence text) {
        Document document = new Document(this);
        document.setWordWrap(false);
        document.setText(text);
        setDocumentProvider(new DocumentProvider(document));
    }
    
    public final void redo() {

        int redo = createDocumentProvider().redo();
        if (redo >= 0) {
            setEdited(true);
            respan();
            selectText(false);
            moveCaret(redo);
            invalidate();
        }
    }
    
    public final void undo() {

        int undo = createDocumentProvider().undo();
        if (undo >= 0) {
            setEdited(true);
            respan();
            selectText(false);
            moveCaret(undo);
            invalidate();
        }
    }
    
    public void read(File file,Object... v) throws IOException {
        this.path = file.getAbsolutePath();    
        String text=new String(IOUtils.file_read(path));
        setText(text);
    }

    public void save() throws IOException{
        if(this.path!=null)
            IOUtils.file_write(this.path,_hDoc.toString().getBytes());
    }
}
