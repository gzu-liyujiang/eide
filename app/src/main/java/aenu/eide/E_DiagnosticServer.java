package aenu.eide;
import aenu.eide.diagnostic.DiagnosticCallback;
import java.io.File;
import java.util.List;
import aenu.eide.diagnostic.DiagnosticMessage;
import aenu.eide.diagnostic.IDiagnostic;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

public class E_DiagnosticServer{

    private final DiagnosticThread thread=new DiagnosticThread();
    private final List<DiagnosticCallback> callbacks=new ArrayList<>();
    
    public void start(){
        thread.start();
    }
    
    public void stop(){
        thread.interrupt();
    }
    
    public void addCallback(DiagnosticCallback CB){
        callbacks.add(CB);
    }
    
    public void removeCallback(DiagnosticCallback CB){
        callbacks.remove(CB);
    }
    
    public void attachDiagnostic(IDiagnostic diag){
        thread.add_diag(diag);
    }
    
    public void updateDiagnostic(IDiagnostic diag){
        thread.up_diag(diag);
    }
    
    public void removeDiagnostic(IDiagnostic diag){
        thread.remove_diag(diag);
    }
    
    public Map<File,List<DiagnosticMessage>> get_warnings(){
        return convert_map(thread.warnings);
    }
    
    public Map<File,List<DiagnosticMessage>> get_errors(){
        return convert_map(thread.errors);
    }
    
    private static Map<File,List<DiagnosticMessage>> convert_map(Map<IDiagnostic,List<DiagnosticMessage>> input){
        final Map<File,List<DiagnosticMessage>> map =new HashMap<>();
        final Collection<List<DiagnosticMessage>> values= input.values();
        
        for(List<DiagnosticMessage> i_list:values){
            for(DiagnosticMessage msg:i_list){
                List<DiagnosticMessage> o_list=map.get(msg.file);
                if(o_list==null){
                    o_list=new ArrayList<DiagnosticMessage>();
                    map.put(msg.file,o_list);
                }
                o_list.add(msg);
            }
        }
        
        return map;
    }
    
    private class DiagnosticThread extends Thread implements DiagnosticCallback{  
        
        private final List<IDiagnostic> undiag_stack=new ArrayList<>();
        private final Map<IDiagnostic,List<DiagnosticMessage>> warnings=new HashMap<>();
        private final Map<IDiagnostic,List<DiagnosticMessage>> errors=new HashMap<>();
        
        private IDiagnostic current_diag;
        
        @Override
        public void onNewError(DiagnosticMessage msg){
            List<DiagnosticMessage> message=errors.get(current_diag);
            if(message==null){
                message=new ArrayList<DiagnosticMessage>();
                errors.put(current_diag,message);
            }
            message.add(msg);
            for(DiagnosticCallback cb:callbacks){
                cb.onNewError(msg);
            }
        }

        @Override
        public void onNewWarning(DiagnosticMessage msg){
            List<DiagnosticMessage> message=warnings.get(current_diag);
            if(message==null){
                message=new ArrayList<DiagnosticMessage>();
                warnings.put(current_diag,message);
            }
            message.add(msg);
            for(DiagnosticCallback cb:callbacks){
                cb.onNewWarning(msg);
            }
        }

        public void add_diag(IDiagnostic diag){
            synchronized(DiagnosticThread.this){
                undiag_stack.add(diag);
            }
        }
        
        public void remove_diag(IDiagnostic diag){
            synchronized(DiagnosticThread.this){
                
                //如果未诊断
                {
                    int n=undiag_stack.indexOf(diag);
                    if(n!=-1){
                        undiag_stack.remove(n);
                        return;
                    }          
                }              
                
                warnings.remove(diag);
                errors.remove(diag);
            }
        }
        
        public void up_diag(IDiagnostic diag){
            synchronized(DiagnosticThread.this){
                
                for(IDiagnostic d:undiag_stack){
                    if(d.equals(diag)){
                        undiag_stack.remove(d);
                        undiag_stack.add(diag);
                        return;
                    }
                }           
                
                for(IDiagnostic d:errors.keySet()){
                    if(d.equals(diag)){
                        errors.remove(d);
                        return;
                    }
                }
                
                for(IDiagnostic d:warnings.keySet()){
                    if(d.equals(diag)){
                        errors.remove(d);
                        return;
                    }
                }
                
                undiag_stack.add(diag);
            }
        }
        
        public void run(){
            for(;;){
                synchronized(DiagnosticThread.this){
                    if(undiag_stack.isEmpty()){
                        try{Thread.sleep(200);}
                        catch(InterruptedException e){}
                        finally{continue;}
                    }                   
                        
                    final int index=undiag_stack.size()-1;
                    current_diag=undiag_stack.get(index);         
                    current_diag.diag(this);
                    
                    undiag_stack.remove(index);                               
                }
            }
        }
    }
}
