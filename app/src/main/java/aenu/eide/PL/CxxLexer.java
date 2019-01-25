
//license wtfpl 2.0

//by aenu 2018/11/19
//   email:202983447@qq.com

package aenu.eide.PL;
import com.myopicmobile.textwarrior.common.ILexer;
import com.myopicmobile.textwarrior.common.DocumentProvider;
import com.myopicmobile.textwarrior.common.Pair;
import java.util.List;
import java.util.ArrayList;
import android.util.Log;

public class CxxLexer implements ILexer{
    
    private LexThread lexer_thread;   
    private String  source_file_path;
    private String[]  cx_flags;
    
    static {
        System.loadLibrary("cxxlexer");
    }
    
    public CxxLexer(String sfp,String[] flags){
        source_file_path=sfp;
        cx_flags=flags;
    }
    
    @Override
    public void tokenize(DocumentProvider hDoc,ILexer.LexCallback cb){
        if(lexer_thread!=null){
            lexer_thread.interrupt();
            lexer_thread=null;
        }

        lexer_thread=new LexThread(this,hDoc.toString(),cb);
        lexer_thread.start();
    }

    @Override
    public void cancelTokenize(){
        if(lexer_thread!=null){
            lexer_thread.interrupt();
            lexer_thread=null;
        }
    }  

    static private class LexThread extends Thread{
        
        private ILexer.LexCallback callback;
        private final Pair zero=new Pair(0,NORMAL);
        private CxxLexer lexer;
        private String source;
        private List<Pair> tokens = new ArrayList<>();
        
        private LexThread(CxxLexer cl,String source,ILexer.LexCallback cb){
            this.source=source;
            callback=cb;
            lexer=cl;
        }

        @Override
        public void run(){
            try{
                native_tokenize(source,lexer.source_file_path,lexer.cx_flags);
            }
            finally{
                if(tokens.isEmpty())
                    tokens.add(zero);
                callback.lexDone(tokens);
            }
        }
        
        private native void native_tokenize(String source,String source_file_path,String[] flag);
        
        //native call
        private void nc_add_pair(int s,int t){
            tokens.add(new Pair(s,t));
        }
    }
}
