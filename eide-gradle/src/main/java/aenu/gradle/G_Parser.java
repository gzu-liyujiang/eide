

//by aenu 2018 12 11

//license wtfpl2.0

package aenu.gradle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import aenu.gradle.expr.Expression;
import aenu.gradle.expr.IntegerLiteral;
import aenu.gradle.expr.FloatLiteral;
import aenu.gradle.expr.StringLiteral;
import aenu.gradle.expr.BooleanLiteral;
import aenu.gradle.expr.FunctionInvoke;

public class G_Parser
{
    private final G_Scanner scanner=new G_Scanner();   
    private static final G_Parser parser=new G_Parser();
    
    public static G_Tree Parse(String input) throws G_SyntaxException, G_ParseException{
        parser.reset();
        parser.source=input;
        parser.result=new G_Tree();
        return parser.parse();
    }
    
    private final List<G_Token> tokensO=new ArrayList<>();
    private final List<Integer> tokens=new ArrayList<>();
    private G_Tree result;
    private String source;
    
    private G_Parser(){
    }
    
    private void reset(){
        tokensO.clear();
        tokens.clear();
    }
    
    private String getTokenSource(G_Token token){
        return source.substring(token.start_position,token.end_position);
    }
    
    private String getSource(int start_position,int end_position){
        return source.substring(start_position,end_position);
    }
    
    public G_Tree parse() throws G_SyntaxException, G_ParseException{
       
        scanner.setSource(source);
        
        while(!scanner.atEnd()){
            G_Token result=new G_Token();
            int t=scanner.getNextToken(result);
            if(t==G_Token.TokenName_EOF)
                break;
            tokensO.add(result);
            tokens.add(t);
        }
        /*
        for(int i=0;i<tokensO.size();i++){
            G_Token t=tokensO.get(i);
            System.out.println(i+" "+getTokenSource(t));
        }*/
        
        int size=tokens.size();
        int index=0;
        
        for(;;){
            if(index>=size)
                break;
            int token1=tokens.get(index);
            
            switch(token1){
                case G_Token.TokenName_identifier:                 
                    index=parseIdentifier(index,null);
                    break;
                case G_Token.TokenName_task:
                case G_Token.TokenName_def://skip
                    index=parseDef(index);
                    break;
                default: throw new G_ParseException();
            }
        } 
        return result;
    }
    
    private int parseDef(int index) throws G_ParseException{
        
        int token3=tokens.get(index+2);
        int deep=0;
        
        int nextT;
        
        switch(token3){
            case G_Token.TokenName_lparen:
                for(;;){
                    nextT=tokens.get(++index);
                    if(nextT==G_Token.TokenName_lbrace)
                        ++deep;
                    else if(nextT==G_Token.TokenName_rbrace)
                        if(--deep==0) return ++index;
                    
                }              
            default: throw new G_ParseException();
        }
    }
    
    private int parseTask(int index) throws G_ParseException{

        int token3=tokens.get(index+2);
        int deep=0;

        int nextT;

        switch(token3){
            case G_Token.TokenName_lparen:
            for(;;){
                nextT=tokens.get(++index);
                if(nextT==G_Token.TokenName_lbrace)
                    ++deep;
                else if(nextT==G_Token.TokenName_rbrace)
                    if(--deep==0) return ++index;

            }              
            default: throw new G_ParseException();
        }
    }
    
    private int parseIdentifier(int index,G_Tree.Node node) throws G_ParseException{
       
        String t1Name=getTokenSource(tokensO.get(index));
        G_Tree.Node original=node;
        if(node==null){
            node=result.getNode(t1Name);
            if(node==null)
                result.addNode(node=new G_Tree.Node(t1Name));
        }
        else{
            G_Tree.Node childN=node.getChlidNode(t1Name);
            if(childN==null)
                node.addChildNode(childN=new G_Tree.Node(t1Name));         
            node=childN; 
        }
        
        int offset=1;
        /*
        // identifier . identifier . identifier ...
        while((tokens.get(index+offset))==G_Token.TokenName_dot){
            G_Tree.Node childN=node.getChlidNode(t1Name);
            if(childN==null)
                node.addChildNode(childN=new G_Tree.Node(t1Name));         
            node=childN; 
            offset+=2;
        }*/
        
        
        int tokenN=tokens.get(index+offset);
        
        switch(tokenN){
            //identifier {
            case G_Token.TokenName_lbrace:// {
                return parseBlock(index+offset,node);    
            //identifier (
            case G_Token.TokenName_lparen:// (
                return parseFuncInvoke(index,null);
            //identifier =
            case G_Token.TokenName_equal:
                return parseArgs(index+offset+1,node);
            case G_Token.TokenName_identifier:
                
                //identifier identifier :
                if(tokens.get(index+offset+1)==G_Token.TokenName_colon){ //:
                    String t2Name=getTokenSource(tokensO.get(index+1));
                    G_Tree.Node t2Node=node.getChlidNode(t2Name);
                    if(t2Node==null)
                        node.addChildNode(t2Node=new G_Tree.Node(t2Name));         
                    return parseArgs(index+offset+2,t2Node);
                }
                //identifier identifier (
                if(tokens.get(index+offset+1)==G_Token.TokenName_lparen){
                    
                    return parseArgs(index+offset,node);
                }
                else{
                     return parseArgs(index+offset+1,node);
                }
            default:return parseArgs(index+offset,node);
                
        }
    }
    
    private int parseArgs(int index,G_Tree.Node node) throws G_ParseException{
        int tt=tokens.get(index);
        String tc=getTokenSource(tokensO.get(index));
         switch(tt){
            case G_Token.TokenName_identifier:
                
                
                //FIXME 不完整
                
                switch(tokens.get(index+1)){
                    case G_Token.TokenName_lparen://(
                    case G_Token.TokenName_dot:// .
                        break;
                    default :throw new G_ParseException();
                }
                
                index=parseFuncInvoke(index,node)-1;
                break;
            case G_Token.TokenName_integerLiteral:
                node.values.add(new IntegerLiteral(tc));
                break;
            case G_Token.TokenName_floatLiteral:
                node.values.add(new FloatLiteral(tc));
                break;
            case G_Token.TokenName_stringLiteral:
                node.values.add(new StringLiteral(tc));
                break;
            case G_Token.TokenName_false:
            case G_Token.TokenName_true:
                node.values.add(new BooleanLiteral(tc));
                break;
            default: throw new G_ParseException();
                
        }
        if(tokens.get(++index)==G_Token.TokenName_comma)
            return parseArgs(++index,node);
            
        return index;
    }
    
    //{...}
    private int parseBlock(int index,G_Tree.Node block) throws G_ParseException{
        
        //empty block
        if(tokens.get(++index)==G_Token.TokenName_rbrace)
            return ++index;
        
        for(;;){        
            
            index=parseIdentifier(index,block);
            if(tokens.get(index)==G_Token.TokenName_rbrace)
             return ++index;          
        }
    }
    
    //
    private int parseFuncInvoke(int index,G_Tree.Node parent){
           int base=index;
           
        int deep=0;
        
        for(;;){
            int t=tokens.get(++index);
            switch(t){
                case G_Token.TokenName_lparen://(
                    ++deep;
                    break;
                case G_Token.TokenName_rparen://)
                    --deep;
                    if(deep==0){
                      if(parent!=null)
                        parent.values.add(new FunctionInvoke(
                          getSource(tokensO.get(base).start_position,tokensO.get(index).end_position)
                        ));
                        return ++index;
                    }
                    break;
            }
        }
    }
}
