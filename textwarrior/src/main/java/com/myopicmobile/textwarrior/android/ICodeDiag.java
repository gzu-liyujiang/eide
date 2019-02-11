package com.myopicmobile.textwarrior.android;
import java.util.List;

public interface ICodeDiag{
    
    public static final class DiagInfo{
        public final int start_position;
        public final int end_position;
        public final String message;
        
        public DiagInfo(
        final int start_position,
        final int end_position,
        final String message){
            this.start_position=start_position;
            this.end_position=end_position;
            this.message=message;
        }
    }
    
    public void diag(String code);  
	public List<ICodeDiag.DiagInfo> errors;
	public List<ICodeDiag.DiagInfo> warnings;
}
