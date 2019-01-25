
//by aenu 2018 11 29
//        2018 12 11

//license wtfpl2.0

package aenu.gradle;
import java.util.List;
import java.util.ArrayList;

public class G_Scanner
{
    private char[] source;
    private int currentPosition;
    private int endPosition;

    public void setSource(String source){
        this.source=source.toCharArray();
        this.currentPosition=0;
        endPosition=this.source.length-1;
    }

    public boolean atEnd(){
        return this.currentPosition>=this.endPosition;
    }

    public int getNextToken(G_Token result) throws G_SyntaxException{

        switch(source[currentPosition]){       
                case ',': 
                    result.start_position=currentPosition;
                    result.end_position=++currentPosition;                                 
                    return G_Token.TokenName_comma;
                case '=':     
                    result.start_position=currentPosition;
                    result.end_position=++currentPosition;                                 
                    return G_Token.TokenName_equal;
                case ':': 
                    result.start_position=currentPosition;
                    result.end_position=++currentPosition;                                 
                    return G_Token.TokenName_colon;
                case '(': 
                    result.start_position=currentPosition;
                    result.end_position=++currentPosition;                                 
                    return G_Token.TokenName_lparen;
                case ')': 
                    result.start_position=currentPosition;
                    result.end_position=++currentPosition;                                 
                    return G_Token.TokenName_rparen;
                case '{': 
                    result.start_position=currentPosition;
                    result.end_position=++currentPosition;                                 
                    return G_Token.TokenName_lbrace;
                case '}': 
                    result.start_position=currentPosition;
                    result.end_position=++currentPosition;                                 
                    return G_Token.TokenName_rbrace;
                case '[': 
                    result.start_position=currentPosition;
                    result.end_position=++currentPosition;                                 
                    return G_Token.TokenName_lbracket;
                case ']': 
                    result.start_position=currentPosition;
                    result.end_position=++currentPosition;                                 
                    return G_Token.TokenName_rbracket;
                case '.': 
                    result.start_position=currentPosition;
                    result.end_position=++currentPosition;                                 
                    return G_Token.TokenName_dot;                
                case '/'://注释
                    if(source[currentPosition+1]!='/')
                        throw new G_SyntaxException();

                    result.start_position=currentPosition;
                    while(source[++currentPosition]!='\n')
                        ;
                    result.end_position=++currentPosition;
                    return G_Token.TokenName_comment;
                case '"':
                    result.start_position=currentPosition;
                    while(source[++currentPosition]!='"')
                        ;
                    result.end_position=++currentPosition;  
                    return G_Token.TokenName_stringLiteral;
                case '\'':
                    result.start_position=currentPosition;
                    while(source[++currentPosition]!='\'')
                        ;
                    result.end_position=++currentPosition;  
                    return G_Token.TokenName_stringLiteral;
                default:
                
                    while(source[currentPosition]==' '||source[currentPosition]=='\n')
                        if(currentPosition<endPosition)
                            ++currentPosition;
                        else
                            return G_Token.TokenName_EOF;
                        
                    if(source[currentPosition]>='0'
                      &&source[currentPosition]<='9'){
                        result.start_position=currentPosition;            
                        while(source[++currentPosition]>='0'&&source[currentPosition]<='9')
                            ;
                        if(source[currentPosition]!='.'){
                            result.end_position=currentPosition; 
                            return G_Token.TokenName_integerLiteral;}
                        while(source[++currentPosition]>='0'&&source[currentPosition]<='9')
                            ;
                        result.end_position=currentPosition; 
                        return G_Token.TokenName_floatLiteral;
                    }
                    
                    switch(source[currentPosition]){
                       case'(':case')':
                       case'[':case']':
                       case'{':case'}':
                       case':':case',':
                       case'.':case'=':
                       case'\'':case'"':
                       case'/':
                           return getNextToken(result);
                    }
          
                    List<Character> chars=new ArrayList<>(); 
                    result.start_position=currentPosition;     
                    
                    for(;;){
                        if(currentPosition>=endPosition) break;
                        if(source[currentPosition]=='(') break;
                        if(source[currentPosition]==')') break;
                        if(source[currentPosition]=='{') break;
                        if(source[currentPosition]=='}') break;
                        if(source[currentPosition]=='[') break;
                        if(source[currentPosition]==']') break;
                        if(source[currentPosition]==':') break;
                        if(source[currentPosition]==',') break;
                        if(source[currentPosition]=='.') break;
                        if(source[currentPosition]=='=') break;
                        if(source[currentPosition]=='"') break;
                        if(source[currentPosition]=='\'') break;
                        if(source[currentPosition]=='/') break;
                        if(source[currentPosition]==' ') break;
                        if(source[currentPosition]=='\n') break;
                        
                        chars.add(source[currentPosition]);
                        currentPosition++;
                        
                    }        
                    result.end_position=currentPosition;
                    return getToken(chars);
          }      
    }

    private int getToken(List<Character> chars){

        final int size=chars.size();

        if( (size==3)
        && (chars.get(0)=='d')
        && (chars.get(1)=='e')
        && (chars.get(2)=='f'))
            return G_Token.TokenName_def;

        if( (size==5)
        && (chars.get(0)=='f')
        && (chars.get(1)=='a')
        && (chars.get(2)=='l')
        && (chars.get(3)=='s')
        && (chars.get(4)=='e'))
            return G_Token.TokenName_false;

        if( (size==4)
        && (chars.get(0)=='t')
        && (chars.get(1)=='r')
        && (chars.get(2)=='u')
        && (chars.get(3)=='e'))
            return G_Token.TokenName_true;

        if( (size==6)
        && (chars.get(0)=='r')
        && (chars.get(1)=='e')
        && (chars.get(2)=='t')
        && (chars.get(3)=='u')
        && (chars.get(4)=='r')
        && (chars.get(5)=='n'))
            return G_Token.TokenName_return;

        if( (size==4)
        && (chars.get(0)=='t')
        && (chars.get(1)=='a')
        && (chars.get(2)=='s')
        && (chars.get(3)=='k'))
            return G_Token.TokenName_task;

        return G_Token.TokenName_identifier;
    }
}
