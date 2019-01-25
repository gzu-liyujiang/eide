
//license wtfpl 2.0

//by aenu 2019
//   email:202983447@qq.com


package aenu.eide.diagnostic;

public final class DiagnosticInfo{
    
    public static final int 
            TYPE_WARNING=0,
            TYPE_ERROR=1;
    
    public final int start_position;
    public final int end_position;
    public final int line;
    public final int column;
    public final String info;
    final int type;
    
    private DiagnosticInfo(int s,int e,int l,int c,String i,int t){
        start_position=s;
        end_position=e;
        line=l;
        column=c;
        info=i;
        type=t;
    }

    @Override
    public boolean equals(Object obj){
        if(((!((obj instanceof DiagnosticInfo)))))
            return false;
            
        DiagnosticInfo DI=(DiagnosticInfo)obj;
        return this.start_position==DI.start_position
                &&this.end_position==DI.end_position;
    }
    
    public static DiagnosticInfo newError(int startP,int endP,int line,int column,String info){
        return new DiagnosticInfo(startP,endP,line,column,info,TYPE_ERROR);
    }
    
    public static DiagnosticInfo newWarning(int startP,int endP,int line,int column,String info){
        return new DiagnosticInfo(startP,endP,line,column,info,TYPE_WARNING);
    }
}
