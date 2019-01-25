

//by aenu 2018 11 29

//license wtfpl2.0

package aenu.gradle;

public class G_Token
{
    public final static int TokenName_identifier= -1;
    
    public final static int TokenName_def=1;
    public final static int TokenName_false=2;
    public final static int TokenName_true=3;
    public final static int TokenName_return=4;
    public final static int TokenName_task=4;
    
    public final static int TokenName_equal=256;//=
    public final static int TokenName_colon=257;//:
    public final static int TokenName_lparen=258;//(
    public final static int TokenName_rparen=259;//)
    public final static int TokenName_lbrace=260;//{
    public final static int TokenName_rbrace=261;//}
    public final static int TokenName_lbracket=262;//[
    public final static int TokenName_rbracket=263;//]
    public final static int TokenName_comma=264;//,
    public final static int TokenName_dot=265;//.
    
    public final static int TokenName_comment=512;
    public final static int TokenName_integerLiteral=513;
    public final static int TokenName_floatLiteral=514;
    public final static int TokenName_stringLiteral=515;
    
    public final static int TokenName_EOF=999;
    
    public int start_position;
    public int end_position;
}
