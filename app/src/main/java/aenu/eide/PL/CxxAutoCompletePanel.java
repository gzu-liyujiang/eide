
//license wtfpl 2.0

//by aenu 2018/11/21
//   email:202983447@qq.com

package aenu.eide.PL;
import com.myopicmobile.textwarrior.android.IAutoComplete;
import com.myopicmobile.textwarrior.android.FreeScrollingTextField;
import java.util.ArrayList;
import java.util.List;
import android.graphics.Bitmap;
import android.util.Log;

public class CxxAutoCompletePanel extends AutoCompletePanel{

    private String source_file_path;
    private int mReplaceTextLength;
    private String[] CxFlags;
    
    static {
        System.loadLibrary("cxxcomplete");
    }
    
    private final List<ListItem> completes=new ArrayList<>();
    
    public CxxAutoCompletePanel(FreeScrollingTextField fstf,String source_file_path,String[] flags){
        super(fstf);
        this.source_file_path=source_file_path;
        this.CxFlags=flags;
    }
    
    @Override
    public void select(int pos){
        
        if(!isShowing())
            return;
        
        ListItem item=_adapter.getItem(pos);
        String text=item.text;
        
        _textField.replaceText(_textField.getCaretPosition() - mReplaceTextLength, mReplaceTextLength, text);

        dismiss();
    }

    @Override
    public void complete(CharSequence text, int pos){
        
        if(pos==0||text.length()==0)return;

        int curr=pos;
        for(;curr>=1;curr--){
            char c=text.charAt(curr-1);
            if((c==' ')
            ||(c=='.')
            ||(c=='\n')
            ||(c=='{')
            ||(c=='}')
            ||(c=='(')
            ||(c==')')
            ||(c=='+')
            ||(c=='-')
            ||(c=='*')
            ||(c=='/')
            ||(c=='=')
            ||(c=='|')
            ||(c=='&')
            ||(c=='^')
            ||(c=='?')
            ||(c==':')
            ||(c=='>')
            ||(c=='<')
            ||(c=='!')
            ||(c=='@')
            ||(c=='~')
            ||(c=='%')
            ||(c=='[')
            ||(c==']')
            ||(c==',')
            ||(c==';'))
                break;
        }

        mReplaceTextLength=pos-curr;
        
        if(mReplaceTextLength==0) {dismiss(); return;}
        
        completes.clear();
        native_complete(text.toString(),pos,curr,source_file_path,CxFlags);
        
        if(completes.size()!=0){
            _adapter.setItems(completes);
            
            setWidth(_textField.getWidth()-PADDING*2);
            setHorizontalOffset(PADDING);
            
            setHeight(completes.size()>1?getItemHeight()*2:getItemHeight());
            setVerticalOffset(_textField.getCaretY()-_textField.getScrollY()-_textField.getHeight());
            
            if(!isShowing())
              show();
        }
        else
            dismiss();
    }
    
    //native call
    private void nc_add_complete(Bitmap icon,String text,String hint){
        completes.add(new ListItem(icon,text,hint));
    }
    
    private native void native_complete(String source, int curPos,int replacePos,String source_file_path,String[] flags);
    
}
