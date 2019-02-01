package aenu.eide.view;
import aenu.eide.E_MainActivity;
import aenu.eide.R;
import aenu.eide.RequestListener;
import aenu.eide.diagnostic.DiagnosticMessage;
import aenu.eide.util.IOUtils;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Adapter;
import java.util.Collection;
import aenu.eide.E_FileActivity;

public class ProjectPage extends PagerAdapter implements TreeNode.TreeNodeClickListener{

    private final Map<String,View> views=new HashMap<>();
    private final Context context;
    private final File project_dir;
    private final RequestListener listener;
    private final Map<File,TreeNode> node_map=new HashMap<>();
    private final TreeNode root_node=TreeNode.root();
    private final AdapterView.OnItemClickListener MSG_CLICK=new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4){
            final File file=((DiagnosticMessage)p1.getItemAtPosition(p3)).file;
            if(listener!=null)
                listener.onRequest(E_MainActivity.REQUEST_OPEN_FILE,file);
        }    
    };
    private AndroidTreeView tree_view;
    
    public ProjectPage(Context context,File projectDir,RequestListener l){
        this.context=context;
        this.project_dir=projectDir;
        this.listener=l;
        initialize();
    }

    private final void initialize(){

        views.put("错误",newListView("无错误"));
        views.put("警告",newListView("无警告"));
        views.put("项目",
        project_dir==null?newTextView("未打开项目"):newProjectView());
    }

    private final TextView newTextView(String text){
        final TextView v=new TextView(context);
        v.setGravity(Gravity.CENTER);
        v.setText(text);
        v.setBackgroundColor(-1);
        return v;
    }

    private final View newListView(String empty_message){
        final FrameLayout v=new FrameLayout(context);
        final ListView l=new ListView(context);
        final TextView e=newTextView(empty_message);
        v.setBackgroundColor(-1);
        v.addView(e);
        v.addView(l);
        l.setId(android.R.id.list);
        l.setEmptyView(e);
        l.setOnItemClickListener(MSG_CLICK);
        return v;
    }
     
    private final View newProjectView(){
        
        final LinearLayout v=new LinearLayout(context);
        v.setOrientation(LinearLayout.VERTICAL);
        v.setBackgroundColor(-1);
         
        
        tree_view =new AndroidTreeView(context,root_node);
        tree_view.setDefaultViewHolder(ProjectTreeHolder.class);
        tree_view.setDefaultNodeClickListener(this);
        
        tree_updateNode();
        
        final TextView av=new TextView(context);
        av.setText("新建文件");
        av.setGravity(Gravity.CENTER);
        av.setBackgroundColor(-1^0xff00);
        av.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View p1){
                addNewFileDialog().show();
            }
        });
        
        final View tv=tree_view.getView();
        
        v.addView(av);
        v.addView(tv);
        
        return v;
    }
    
    private Dialog addNewFileDialog(){
        final AlertDialog.Builder b=new AlertDialog.Builder(context);
        final View v=LayoutInflater.from(context).inflate(R.layout.add_new_file,null);
        final Spinner s=(Spinner) v.findViewById(R.id.new_file_types);
        final EditText e=(EditText) v.findViewById(R.id.new_file_name);
        final String[] types=context.getResources().getStringArray(R.array.new_file_types);
        final String[] types_hint=context.getResources().getStringArray(R.array.new_file_types_hint);
        
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4){
                e.setHint(types_hint[p3]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> p1){
                //
            }          
        });
        
        b.setTitle("新文件");
        b.setView(v);
        b.setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface p1, int p2){
                String input=e.getText().toString();
                if(input.isEmpty()){
                    Toast.makeText(context,"文件名不能为空！",1).show();
                    return;
                }
                
                String type=types[s.getSelectedItemPosition()];
                File file=null;
                if(type.equals("java_class"))
                    file=createJavaClass(input);
                else if(type.equals("cx_file"))
                    file=createNewFile("src/main/jni/"+input);
                else if(type.equals("file"))
                    file=createNewFile(input);
                else if(type.equals("dir"))
                    file=createNewDir(input);
                
                tree_updateNode();
                
                tree_view.collapseNode(root_node);
                tree_view.expandNode(root_node);
                
                p1.dismiss();
            }   
        });     
        b.setNegativeButton(android.R.string.cancel,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface p1, int p2){
                p1.dismiss();
            }          
        });
        
        return b.create();
    }
    
    private File createJavaClass(String class_name){
        String path=class_name.replace('.','/')+".java";
        String code="";
        {//generate code
            int p=class_name.lastIndexOf('.');
            if(p!=-1)
                code+="package "+class_name.substring(0,p)+";\n\n";
            code+="public class ";
            if(p!=-1)
                code+=class_name.substring(p+1)+"{\n\n}";
            else
                code+=class_name+"{\n\n}";
        }
        
        File file=new File(project_dir,"src/main/java/"+path);
        if(file.exists()){
            Toast.makeText(context,"类以存在！",1).show();
            return null;
        }
        file.getParentFile().mkdirs();
        try{
            IOUtils.file_write(file.getAbsolutePath(),code.getBytes());
        }catch (IOException e) {
            Toast.makeText(context,e.toString(),1).show();           
        }
        finally{
            return file;
        }
    }
    
    private File createNewFile(String childPath){
        File file=new File(project_dir,childPath);
        if(file.exists()){
            Toast.makeText(context,"文件以存在！",1).show();
            return null;
        }
        try{
            file.getParentFile().mkdirs();
            file.createNewFile();                  
        }catch (IOException e) {
            Toast.makeText(context,e.toString(),1).show();                    
        }
        finally{
            return file;
        }
    }
    
    private File createNewDir(String childPath){
        File file=new File(project_dir,childPath);
        if(file.exists()){
            Toast.makeText(context,"目录以存在！",1).show();
            return null;
        }
        
        try{
            if(!file.mkdirs())
                throw new IOException("创建目录失败！>> "+file.getAbsolutePath());
        }catch (IOException e) {
            Toast.makeText(context,e.toString(),1).show();                    
        }
        finally{
            return file;
        }
    }
    
    
    private final void tree_addChildNode(File dir,TreeNode parent){
        File[] files=dir.listFiles();
        if(files==null)
            return;
        for(File f:files){         
            if(node_map.get(f)!=null)
                continue;
                
            TreeNode node=new TreeNode(f);
            parent.addChild(node);
            node_map.put(f,node);
            
            if(f.isDirectory())
                tree_addChildNode(f,node);

        }
    }
    
    private final void tree_updateNode(){
        tree_addChildNode(project_dir,root_node);
    }
    

    /*
    private List<String> to_string_list(Map<File,List<DiagnosticMessage>> map){
        final ArrayList<String> list=new ArrayList<>();
        if(map==null) return list;
        final Set<Map.Entry<File,List<DiagnosticMessage>>> entries=map.entrySet();
        if(entries==null)return list;
        for(Map.Entry<File,List<DiagnosticMessage>> e:entries){
            String fn=e.getKey().getAbsolutePath();
            List<DiagnosticMessage> infos=e.getValue();
            for(DiagnosticMessage info:infos){
                list.add(fn+":"+info.line+":"+info.column+":"+info.text);               
            }
        }
        return list;
    }*/
    
    private List<DiagnosticMessage> list_all_diag_msg(Map<File,List<DiagnosticMessage>> map){
        final ArrayList<DiagnosticMessage> list=new ArrayList<>();
        if(map==null) return list;
        
        Collection<List<DiagnosticMessage>> values=map.values();
        if(values==null) return list;
        
        for(List<DiagnosticMessage> v:values)
            list.addAll(v);
        
        return list;
    }
    
    private ArrayAdapter<DiagnosticMessage> new_diag_msg_adapter(Map<File,List<DiagnosticMessage>> list){
        final ArrayAdapter<DiagnosticMessage> adapter=new ArrayAdapter<DiagnosticMessage>(context,-1){
            public View getView(int position,View convertView,ViewGroup parent) {
                TextView v=convertView!=null?(TextView)convertView:new TextView(context);
                DiagnosticMessage msg=getItem(position);
                v.setTextColor(Color.RED);
                String text="";{
                    text+=msg.file.getAbsolutePath();
                    text+=":"+msg.line;
                    if(msg.column!=-1)
                        text+=":"+msg.column;
                    text+=msg.text;
                }
                v.setText(text);
                return v;
            }          
        };
        adapter.addAll(list_all_diag_msg(list));    
        return adapter;
    }

    public void setErrors(Map<File,List<DiagnosticMessage>> errs){
        final ArrayAdapter<DiagnosticMessage> adapter=new_diag_msg_adapter(errs);
        
        final ListView v=(ListView)views.get("错误").findViewById(android.R.id.list);
        v.setAdapter(adapter);
    }

    public void setWarnings(Map<File,List<DiagnosticMessage>> wars){
        final ArrayAdapter<DiagnosticMessage> adapter=new_diag_msg_adapter(wars); 

        final ListView v=(ListView)views.get("警告").findViewById(android.R.id.list);
        v.setAdapter(adapter);
    }

    @Override
    public void onClick(TreeNode node,Object value){
        File f=(File) value;
        if(f.isFile())
            if(listener!=null)
                listener.onRequest(E_MainActivity.REQUEST_OPEN_FILE,f);
    }

    @Override
    public int getCount(){
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View v, Object o){
        return v==views.get(o);
    }

    @Override
    public void destroyItem(ViewGroup container, int position,Object object) {
        final View v=views.get(object);
        container.removeView(v);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Map.Entry<String,View> e=new ArrayList<Map.Entry<String,View>>(views.entrySet()).get(position);    
        container.addView(e.getValue());
        return e.getKey();
    }

    @Override
    public CharSequence getPageTitle(int positon){
        final String v= new ArrayList<String>(views.keySet()).get(positon);
        return v;
    }

    private static class ProjectTreeHolder extends TreeNode.BaseNodeViewHolder{

        private View node_view;

        @Override
        public View createNodeView(TreeNode node,Object value){
            
            File f=(File) value;
            
            ImageView icon=(ImageView)node_view.findViewById(android.R.id.icon);
            TextView text=(TextView)node_view.findViewById(android.R.id.text1);
            TextView hint=(TextView)node_view.findViewById(android.R.id.text2);

            icon.setImageResource(f.isDirectory()?R.drawable.ic_folder:R.drawable.ic_file);
            text.setText(f.getName());
            hint.setText(E_FileActivity.FileAdapter.getTimeStr(f.lastModified()));
            if(!f.isDirectory())
                hint.setText(hint.getText()+" "+E_FileActivity.FileAdapter.getSizeStr(f.length()));

            node_view.setPadding(getLeftPadding(mNode,2),2,2,2);
            return node_view;
        }

        private int getLeftPadding(TreeNode node,int padding){
            if(node.isRoot())
                return padding;
            return getLeftPadding(node.getParent(),padding+24);
        }

        public ProjectTreeHolder(Context context){
            super(context);
            node_view=LayoutInflater.from(context).inflate(R.layout.file_item,null);  
            node_view.setId(com.unnamed.b.atv.R.id.tree_items);

        }
    }
}
