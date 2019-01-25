package aenu.eide.PL;
import com.myopicmobile.textwarrior.common.ILanguage;
import com.myopicmobile.textwarrior.common.ILexer;

public class XmlLang implements ILanguage
{
    private XmlLexer lexer=new XmlLexer();

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
