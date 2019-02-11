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
import android.util.*;

public class E_DiagnosticServer{

	public static interface Callback{
		public void onChanged();
	}
    private final DiagnosticThread thread=new DiagnosticThread();
    private final List<Callback> callbacks=new ArrayList<>();
    
    public void start(){
        thread.start();
    }
    
    public void stop(){
        thread.interrupt();
    }
    
    public void addCallback(Callback CB){
        callbacks.add(CB);
    }
    
    public void removeCallback(Callback CB){
        callbacks.remove(CB);
    }
    
    public void attachDiagnostic(IDiagnostic diag){
        thread.add_diag(diag);
    }
    
    public void updateDiagnostic(IDiagnostic diag){
        thread.remove_diag(diag);
        thread.add_diag(diag);
    }
    
    public void removeDiagnostic(IDiagnostic diag){
        thread.remove_diag(diag);
    }
    
	public List<DiagnosticMessage> getAllWarnings(){
        return thread.getAllWarnings();
    }

    public List<DiagnosticMessage> getAllErrors(){
        return thread.getAllErrors();
    }
	
    public List<DiagnosticMessage> getWarnings(File file){
        return thread.getWarnings(file);
    }
    
    public List<DiagnosticMessage> getErrors(File file){
        return thread.getErrors(file);
    }
    
    /*
    
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
    }*/
    
    private class DiagnosticThread extends Thread implements DiagnosticCallback
    {
        
        private final List<IDiagnostic> undiag_stack=new ArrayList<>();
        private final Map<IDiagnostic,List<DiagnosticMessage>> warnings=new HashMap<>();
        private final Map<IDiagnostic,List<DiagnosticMessage>> errors=new HashMap<>();
        
        private IDiagnostic current_diag;
        
        private void printMsg(DiagnosticMessage msg){
            Log.v("eide",msg.toString());
        }
        
        @Override
        public void onNewError(DiagnosticMessage msg){
            
            printMsg(msg);
            
            List<DiagnosticMessage> message=errors.get(current_diag);
            if(message==null){
                message=new ArrayList<DiagnosticMessage>();
                errors.put(current_diag,message);
            }
        
            message.add(msg);
        }

        @Override
        public void onNewWarning(DiagnosticMessage msg){
            
            printMsg(msg);
            List<DiagnosticMessage> message=warnings.get(current_diag);
            if(message==null){
                message=new ArrayList<DiagnosticMessage>();
            
                warnings.put(current_diag,message);
            }
        
            message.add(msg);
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
                
                List<DiagnosticMessage> w_messages=warnings.get(diag);
                List<DiagnosticMessage> e_messages=errors.get(diag);
                
                warnings.remove(diag);
                errors.remove(diag);
                
                /*if(w_messages!=null)
                    for(DiagnosticMessage dm:w_messages)
                        onClearWarning(dm);
                    
                if(e_messages!=null)
                    for(DiagnosticMessage dm:e_messages)
					
                        onClearError(dm);*/
                
            }
        }
		
		public List<DiagnosticMessage> getAllErrors(){
            List<DiagnosticMessage> list=new ArrayList<>();
            synchronized(DiagnosticThread.this){
                Collection<List<DiagnosticMessage>> dml=errors.values();

                for(List<DiagnosticMessage> l:dml)
                    list.addAll(l);


            }
            return list;
        }

        public List<DiagnosticMessage> getAllWarnings(){
            List<DiagnosticMessage> list=new ArrayList<>();
            synchronized(DiagnosticThread.this){
                Collection<List<DiagnosticMessage>> dml=warnings.values();

                for(List<DiagnosticMessage> l:dml)
                    list.addAll(l);
				


            }
            return list;
        }
        
        public List<DiagnosticMessage> getErrors(File f){
            List<DiagnosticMessage> list=new ArrayList<>();
            synchronized(DiagnosticThread.this){
                Collection<List<DiagnosticMessage>> dml=errors.values();
                
                for(List<DiagnosticMessage> l:dml)
                    for(DiagnosticMessage i:l)
                        if(f.equals(i.file))
                            list.add(i);
                    
                
            }
            return list;
        }
        
        public List<DiagnosticMessage> getWarnings(File f){
            List<DiagnosticMessage> list=new ArrayList<>();
            synchronized(DiagnosticThread.this){
                Collection<List<DiagnosticMessage>> dml=warnings.values();

                for(List<DiagnosticMessage> l:dml)
                    for(DiagnosticMessage i:l)
                        if(f.equals(i.file))
                            list.add(i);


            }
            return list;
        }
        
        public void run(){
            
           
            for(;;){
                
                try{
                    if (undiag_stack.isEmpty()){
                        Thread.sleep(200);
                        continue;
                    }
                }
                catch (InterruptedException e){
                    return;
                }

               synchronized(DiagnosticThread.this) {
                                                
                    final int index=undiag_stack.size()-1;
                    current_diag=undiag_stack.get(index);         
                    current_diag.diag(this);
                    undiag_stack.remove(index);                   
                }
                
				for(Callback cb:callbacks){
					cb.onChanged();
				}
				
            }
        }
    }
}
