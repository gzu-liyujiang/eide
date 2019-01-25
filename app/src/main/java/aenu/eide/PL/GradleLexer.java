
//by aenu 2018 11 29

//license wtfpl2.0

package aenu.eide.PL;
import com.myopicmobile.textwarrior.common.ILexer;
import com.myopicmobile.textwarrior.common.DocumentProvider;
import com.myopicmobile.textwarrior.common.ILexer.LexCallback;
import aenu.gradle.G_Scanner;
import com.myopicmobile.textwarrior.common.Pair;
import java.util.List;
import java.util.ArrayList;
import aenu.gradle.G_SyntaxException;
import aenu.gradle.G_Token;
import com.myopicmobile.textwarrior.common.Pair;

public class GradleLexer implements ILexer
{
    private LexThread lexer_thread;

    @Override
    public void tokenize(DocumentProvider hDoc,ILexer.LexCallback cb){
        if(lexer_thread!=null){
            lexer_thread.interrupt();
            lexer_thread=null;
        }

        lexer_thread=new LexThread(hDoc.toString(),cb);
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

        private final static G_Scanner scanner=new G_Scanner(); 

        private String source;
        private ILexer.LexCallback callback;
        private final Pair zero=new Pair(0,NORMAL);
        private LexThread(String source,ILexer.LexCallback cb){
            this.source=source;
            callback=cb;
        }

        @Override
        public void run(){
            List<Pair> r=tokenize();
            if(r.isEmpty())
                r.add(zero);
            callback.lexDone(r);
        }

        private List<Pair> tokenize(){

            List<Pair> tokens = new ArrayList<>();
            G_Token result=new G_Token();
            
            try{          
                scanner.setSource(source);
                while(!scanner.atEnd()){
                    switch(scanner.getNextToken(result)){
                        case G_Token.TokenName_identifier:
                        tokens.add(new Pair(result.start_position,NORMAL));                     
                        break;
                        case G_Token.TokenName_return:
                        case G_Token.TokenName_def:
                        tokens.add(new Pair(result.start_position, KEYWORD));                
                        break;
                        case G_Token.TokenName_integerLiteral:
                        case G_Token.TokenName_floatLiteral:
                        case G_Token.TokenName_stringLiteral: 
                        case G_Token.TokenName_false:
                        case G_Token.TokenName_true:                  
                        tokens.add(new Pair(result.start_position, NUMBER));                                
                        break;
                        default:
                        tokens.add(new Pair(result.start_position, OPERATOR));                                
                        break;
                    }          

                }
            }catch (G_SyntaxException e) {}      

            return tokens;
        }
    }
}
