

//by aenu 2018 11 29

//license wtfpl2.0

package aenu.eide.PL;
import com.myopicmobile.textwarrior.common.ILanguage;
import com.myopicmobile.textwarrior.common.ILexer;

public class GradleLanguage implements ILanguage
{
    private GradleLexer lexer=new GradleLexer();
    
    @Override
    public ILexer getLexer(){
        return lexer;
    }

    @Override
    public boolean isSentenceTerminator(char c){
        return (c=='.');
    }

    @Override
    public boolean isWhitespace(char c){
        return (
        c==' '||
        c=='\n'||
        c=='\t'||
        c=='\r'||
        c=='\f'||
        c==EOF);
    }
}
