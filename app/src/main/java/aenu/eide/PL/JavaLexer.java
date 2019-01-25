
//license wtfpl 2.0

//by aenu 2018/10/22
//   email:202983447@qq.com

package aenu.eide.PL;
import com.myopicmobile.textwarrior.common.ILexer;
import com.myopicmobile.textwarrior.common.DocumentProvider;
import com.myopicmobile.textwarrior.common.Document;
import com.myopicmobile.textwarrior.common.Pair;
import java.util.List;
import java.util.ArrayList;
import org.eclipse.jdt.internal.compiler.parser.Scanner;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.internal.compiler.parser.TerminalTokens;
import java.util.Collections;
import org.eclipse.jdt.internal.compiler.batch.CompilationUnit;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.core.compiler.CategorizedProblem;
import android.util.Log;
import android.graphics.Color;

public class JavaLexer implements ILexer
{

    private LexThread lexer_thread;
    
    @Override
    public void tokenize(DocumentProvider hDoc,ILexer.LexCallback cb){
        if(lexer_thread!=null){
            lexer_thread.interrupt();
            lexer_thread=null;
        }

        lexer_thread=new LexThread(this,hDoc,cb);
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

        private final static Scanner scanner=new Scanner(); 
        
        private DocumentProvider doc;
        private ILexer.LexCallback callback;
        private final Pair zero=new Pair(0,NORMAL);
        private JavaLexer java_lexer;
        private LexThread(JavaLexer jl,DocumentProvider dp,ILexer.LexCallback cb){
            doc=dp;
            callback=cb;
            java_lexer=jl;
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

            try{          
                scanner.setSource(doc.toString().toCharArray());
                while(!scanner.atEnd()){
                    switch(scanner.getNextToken()){
                        case Scanner.TokenNameIdentifier:
                        tokens.add(new Pair(scanner.startPosition,NAME));                     
                        break;
                        case Scanner.TokenNameabstract:
                        case Scanner.TokenNameassert:
                        case Scanner.TokenNameboolean:
                        case Scanner.TokenNamebreak:
                        case Scanner.TokenNamebyte:
                        case Scanner.TokenNamecase:
                        case Scanner.TokenNamecatch:
                        case Scanner.TokenNamechar:
                        case Scanner.TokenNameclass:
                        case Scanner.TokenNamecontinue:
                        case Scanner.TokenNameconst:
                        case Scanner.TokenNamedefault:
                        case Scanner.TokenNamedo:
                        case Scanner.TokenNamedouble:
                        case Scanner.TokenNameelse:
                        case Scanner.TokenNameenum:
                        case Scanner.TokenNameextends:
                        case Scanner.TokenNamefalse:
                        case Scanner.TokenNamefinal:
                        case Scanner.TokenNamefinally:
                        case Scanner.TokenNamefloat:
                        case Scanner.TokenNamefor:
                        case Scanner.TokenNamegoto:
                        case Scanner.TokenNameif:
                        case Scanner.TokenNameimplements:
                        case Scanner.TokenNameimport:
                        case Scanner.TokenNameinstanceof:
                        case Scanner.TokenNameint:
                        case Scanner.TokenNameinterface:
                        case Scanner.TokenNamelong:
                        case Scanner.TokenNamenative:
                        case Scanner.TokenNamenew:
                        case Scanner.TokenNamenull:
                        case Scanner.TokenNamepackage:
                        case Scanner.TokenNameprivate:
                        case Scanner.TokenNameprotected:
                        case Scanner.TokenNamepublic:
                        case Scanner.TokenNamereturn:
                        case Scanner.TokenNameshort:
                        case Scanner.TokenNamestatic:
                        case Scanner.TokenNamestrictfp:
                        case Scanner.TokenNamesuper:
                        case Scanner.TokenNameswitch:
                        case Scanner.TokenNamesynchronized:
                        case Scanner.TokenNamethis:
                        case Scanner.TokenNamethrow:
                        case Scanner.TokenNamethrows:
                        case Scanner.TokenNametransient:
                        case Scanner.TokenNametrue:
                        case Scanner.TokenNametry:
                        case Scanner.TokenNamevoid:
                        case Scanner.TokenNamevolatile:
                        case Scanner.TokenNamewhile:
                        tokens.add(new Pair(scanner.startPosition, KEYWORD));                
                        break;
                        case TerminalTokens.TokenNameIntegerLiteral:
                        case TerminalTokens.TokenNameLongLiteral:
                        case TerminalTokens.TokenNameFloatingPointLiteral:
                        case TerminalTokens.TokenNameDoubleLiteral:
                        case TerminalTokens.TokenNameCharacterLiteral:
                        case TerminalTokens.TokenNameStringLiteral:
                        tokens.add(new Pair(scanner.startPosition, NUMBER));                                
                        break;
                        default:
                        tokens.add(new Pair(scanner.startPosition, OPERATOR));                                
                        break;
                    }          
                    
                }
            }catch (InvalidInputException e) {}      

            return tokens;
        }
    }
}
