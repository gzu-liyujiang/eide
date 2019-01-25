package aenu.eide.PL;
import com.myopicmobile.textwarrior.common.ILexer;
import com.myopicmobile.textwarrior.common.DocumentProvider;
import com.myopicmobile.textwarrior.common.Pair;
import java.util.List;
import java.util.ArrayList;

public class XmlLexer implements ILexer
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

        
        private String source;
        private ILexer.LexCallback callback;
        private final Pair zero=new Pair(0,NORMAL);
        private final Scanner scanner=new Scanner();
        
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
            try{          
                Scanner.Token result=new Scanner.Token();
                scanner.setSource(source);
                while(!scanner.atEnd()){
                    switch(scanner.getNextToken(result)){
                        case Scanner.TokenName_identifier:
                        tokens.add(new Pair(result.start_position,NORMAL));                     
                        break;
                        case Scanner.TokenName_tag:
                        case Scanner.TokenName_magic:
                        tokens.add(new Pair(result.start_position, KEYWORD));                
                        break;
                        case Scanner.TokenName_string:                  
                        tokens.add(new Pair(result.start_position, NUMBER));                                
                        break;
                        default:
                        tokens.add(new Pair(result.start_position, OPERATOR));                                
                        break;
                    }          

                }
            }catch (Exception e) {}      
            
            return tokens;
        }
    }
    
    static class Scanner{
        
        static class Token{
            int start_position,end_position;
        }
        
        public final static int TokenName_identifier= 0;
        public final static int TokenName_tag=1;  
        public final static int TokenName_equal=2;// =
        public final static int TokenName_colon=3;// :
        public final static int TokenName_langle=4;// <
        public final static int TokenName_rangle=5;// >
        public final static int TokenName_div=6;// /
        public final static int TokenName_doubt=7;// ?
        public final static int TokenName_string=8;
        public final static int TokenName_common=9;
        public final static int TokenName_magic=10;
        
        public final static int TokenName_EOF=999;   
        
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

        public int getNextToken(Token result){

            switch(source[currentPosition]){       
                case '=':     
                result.start_position=currentPosition;
                result.end_position=++currentPosition;                                 
                return TokenName_equal;
                case ':': 
                result.start_position=currentPosition;
                result.end_position=++currentPosition;                                 
                return TokenName_colon;
                case '<': 
                    
                if( (source[currentPosition+1]=='!')
                  &&(source[currentPosition+2]=='-')
                  &&(source[currentPosition+3]=='-') ){
                    result.start_position=currentPosition;          
                    currentPosition+=3;
                    loop:{
                      while(source[++currentPosition]!='>');
                      if(source[currentPosition-1]==source[currentPosition-2]
                        &&source[currentPosition-1]=='-')
                            result.end_position=++currentPosition;
                      else break loop;
                    }                   
                    return TokenName_common;              
                } 
                
                if(source[currentPosition+1]=='?'){
                    result.start_position=currentPosition;                          
                    while(source[++currentPosition]!='>');
                    result.end_position=++currentPosition;
                    return TokenName_magic;  
                }
                
                result.start_position=currentPosition;
                result.end_position=++currentPosition;                                 
                return TokenName_langle;
                case '>': 
                result.start_position=currentPosition;
                result.end_position=++currentPosition;                                 
                return TokenName_rangle;
                case '/': 
                result.start_position=currentPosition;
                result.end_position=++currentPosition;                                 
                return TokenName_div;              
                case '"':
                result.start_position=currentPosition;
                while(source[++currentPosition]!='"')
                    ;
                result.end_position=++currentPosition;  
                return TokenName_string;
                
                default:
                
                boolean tag=false;
                
                if(source[currentPosition-1]=='<')
                    tag=true;
                if(source[currentPosition-2]=='<'&&source[currentPosition-1]=='/')
                    tag=true;
                
                while(source[currentPosition]==' '||source[currentPosition]=='\n')
                    if(currentPosition<endPosition)
                        ++currentPosition;
                    else
                        return TokenName_EOF;        

                switch(source[currentPosition]){
                    case'=':case':':
                    case'<':case'>':
                    case'/':case'"':
                    return getNextToken(result);
                }

                result.start_position=currentPosition;     

                for(;;){
                    if(currentPosition>=endPosition) break;
                    if(source[currentPosition]=='=') break;
                    if(source[currentPosition]==':') break;
                    if(source[currentPosition]=='<') break;
                    if(source[currentPosition]=='>') break;
                    if(source[currentPosition]=='/') break;
                    if(source[currentPosition]=='"') break;
                    if(source[currentPosition]==' ') break;
                    if(source[currentPosition]=='\n') break;

                    currentPosition++;

                }        
                result.end_position=currentPosition;
                
                return tag?TokenName_tag: TokenName_identifier;
            }      
        }
    }
}
