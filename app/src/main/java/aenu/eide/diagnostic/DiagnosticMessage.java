
//license wtfpl 2.0

//by aenu 2019
//   email:202983447@qq.com


package aenu.eide.diagnostic;
import java.io.File;

public final class DiagnosticMessage{
    
    public final int start_position;
    public final int end_position;
    public final int line;
    public final int column;
    public final String text;
    public final File file;
    
    private DiagnosticMessage(File f,int s,int e,int l,int c,String t){
        file=f;
        start_position=s;
        end_position=e;
        line=l;
        column=c;
        text=t;     
    }

    @Override
    public boolean equals(Object obj){
        if(((!((obj instanceof DiagnosticMessage)))))
            return false;
            
        DiagnosticMessage DI=(DiagnosticMessage)obj;
        return this.start_position==DI.start_position
                &&this.end_position==DI.end_position;
    }
    
    public static DiagnosticMessage newMessage(String file_path,int startP,int endP,int line,int column,String info){
        return new DiagnosticMessage(new File(file_path),startP,endP,line,column,info);
    }
    
    public static DiagnosticMessage newMessage(File file,int startP,int endP,int line,int column,String info){
        return new DiagnosticMessage(file,startP,endP,line,column,info);
    }
}
