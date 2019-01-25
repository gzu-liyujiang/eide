package com.myopicmobile.textwarrior.common;
import java.util.Map;
import java.util.List;

public interface ILanguage
{
    public final static char EOF = '\uFFFF';
    public final static char NULL_CHAR = '\u0000';
    public final static char NEWLINE = '\n';
    public final static char BACKSPACE = '\b';
    public final static char TAB = '\t';   
    
    public final static String GLYPH_NEWLINE = "\u21b5";
    public final static String GLYPH_SPACE = "\u00b7";
    public final static String GLYPH_TAB = "\u00bb";

    public boolean isSentenceTerminator(char c);

    public boolean isWhitespace(char c);

    public ILexer getLexer();
}
