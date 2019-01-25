package com.myopicmobile.textwarrior.common;
import java.util.List;
import com.myopicmobile.textwarrior.common.ILexer.LexCallback;

public final class LanguageNonProg implements ILanguage
{ 
    private static LanguageNonProg l;

    public synchronized static LanguageNonProg getInstance(){
        return l!=null?l:(l=new LanguageNonProg());
    }
    
    /**
     * 点运算符
     * @param c
     * @return
     */
    public boolean isSentenceTerminator(char c)
    {
        return (c == '.');
    } 

    /**
     * 空白符
     * @param c
     * @return
     */
    public boolean isWhitespace(char c)
    {
        return (c == ' ' || c == '\n' || c == '\t' ||
        c == '\r' || c == '\f' || c == EOF);
	}
    
    
    private LanguageNonProg(){}


    private ILexer _lexer=new ILexer(){
      
        @Override
        public void tokenize(DocumentProvider hDoc,ILexer.LexCallback cb){
            // TODO: Implement this method
        }
        @Override
        public void cancelTokenize(){
            // TODO: Implement this method
        }             
    };
    
    @Override
    public ILexer getLexer(){
        return _lexer;
    }

}
