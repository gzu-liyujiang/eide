
//license wtfpl 2.0

//by aenu 2018/10/22
//   email:202983447@qq.com

package aenu.eide.fragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import java.io.File;
import android.widget.TextView;
import com.unnamed.b.atv.view.AndroidTreeView;
import com.unnamed.b.atv.model.TreeNode;
import android.content.Context;
import aenu.eide.R;
import android.widget.ImageView;
import android.view.Gravity;
import aenu.eide.RequestListener;
import aenu.eide.E_MainActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerAdapter;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import android.widget.ListView;
import aenu.eide.diagnostic.DiagnosticInfo;
import android.widget.ArrayAdapter;
import java.util.Set;
import android.os.Handler;
import android.os.Message;
import android.widget.ListAdapter;
import android.graphics.Color;

public class Project extends Fragment 
{
    private File project_dir;
    private RequestListener listener;
    private ViewPager project_pager;
    private Map<File,List<DiagnosticInfo>> errors;
    private Map<File,List<DiagnosticInfo>> warnings;
    
    public Project(RequestListener l){
        listener=l;
    }
    
    public void setProjectDir(File dir){
        this.project_dir=dir;
    }
    
    public File getProjectDir(){
        return this.project_dir;
    }
    
    public void setErrors(Map<File,List<DiagnosticInfo>> errs){
        errors=errs;
    }
    
    public void setWarnings(Map<File,List<DiagnosticInfo>> wars){
        warnings=wars;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_project, container, false);
    }  
  
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        project_pager = (ViewPager)view.findViewById(R.id.project_pager);

        ((PagerTabStrip)(view.findViewById(R.id.project_tab)))
            .setTextSpacing(50);
            
        final ProjectAdapter adapter = new ProjectAdapter(getActivity(),project_dir,listener);
        
        if(errors!=null)
            adapter.setErrors(errors);
        if(warnings!=null)
            adapter.setWarnings(warnings);
        project_pager.setAdapter(adapter);
    }   
    
    
    private static class ProjectAdapter extends PagerAdapter implements TreeNode.TreeNodeClickListener{

        private final Map<String,View> views=new HashMap<>();
        private final Context context;
        private final File project_dir;
        private final RequestListener listener;
        
        private ProjectAdapter(Context context,File projectDir,RequestListener l){
            this.context=context;
            this.project_dir=projectDir;
            this.listener=l;
            init();
        }

        private void init(){
            
            views.put("错误",newListView());
            views.put("警告",newListView());
            views.put("项目",
                project_dir==null?newTextView("未打开项目"):newTreeView());
        }
        
        private TextView newTextView(String text){
            TextView v=new TextView(context);
            v.setGravity(Gravity.CENTER);
            v.setText(text);
            v.setBackgroundColor(-1);
            return v;
        }
        
        private ListView newListView(){
            ListView v=new ListView(context);
            v.setBackgroundColor(-1);
            return v;
        }
        
        private View newTreeView(){
            TreeNode root=TreeNode.root();
            tree_addChildNode(project_dir,root);
            AndroidTreeView treeView=new AndroidTreeView(context,root);
            treeView.setDefaultViewHolder(ProjectTreeHolder.class);
            treeView.setDefaultNodeClickListener(this);
            final View rv=treeView.getView();
            rv.setBackgroundColor(-1);
            return rv;
        }
        
        private void tree_addChildNode(File dir,TreeNode parent){
            File[] files=dir.listFiles();
            if(files==null)
                return;
            for(File f:files){
                TreeNode node=new TreeNode(f);
                parent.addChild(node);

                if(f.isDirectory())
                    tree_addChildNode(f,node);

            }
        }
        
        private List<String> to_string_list(Map<File,List<DiagnosticInfo>> map){
            final ArrayList<String> list=new ArrayList<>();
            if(map==null) return list;
            final Set<Map.Entry<File,List<DiagnosticInfo>>> entries=map.entrySet();
            if(entries==null)return list;
            for(Map.Entry<File,List<DiagnosticInfo>> e:entries){
                String fn=e.getKey().getAbsolutePath();
                List<DiagnosticInfo> infos=e.getValue();
                for(DiagnosticInfo info:infos){
                    list.add(fn+":"+info.line+":"+info.column+":"+info.info);               
                }
            }
            return list;
        }
        
        public void setErrors(Map<File,List<DiagnosticInfo>> errs){
            final ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,-1){
                public View getView(int position,View convertView,ViewGroup parent) {
                    TextView v=convertView!=null?(TextView)convertView:new TextView(context);
                    v.setTextColor(Color.RED);
                    v.setText(getItem(position));
                    return v;
                }          
            };
            adapter.addAll(to_string_list(errs));
            
            final ListView v=(ListView)views.get("错误");
            v.setAdapter(adapter);
        }

        public void setWarnings(Map<File,List<DiagnosticInfo>> wars){
            final ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,-1){
                public View getView(int position,View convertView,ViewGroup parent) {
                    TextView v=convertView!=null?(TextView)convertView:new TextView(context);
                    v.setTextColor(Color.YELLOW);
                    v.setText(getItem(position));
                    return v;
                }          
            };
            adapter.addAll(to_string_list(wars));
            
            final ListView v=(ListView)views.get("警告");
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
    }
    
    private static class ProjectTreeHolder extends TreeNode.BaseNodeViewHolder{

        private View node_view;
        
        @Override
        public View createNodeView(TreeNode node,Object value){
            return null;
        }

        @Override
        public View getNodeView(){
            File f=(File) mNode.getValue();
            ImageView icon=(ImageView)node_view.findViewById(android.R.id.icon);
            TextView text=(TextView)node_view.findViewById(android.R.id.text1);
            TextView hint=(TextView)node_view.findViewById(android.R.id.text2);
            
            icon.setImageResource(f.isDirectory()?R.drawable.ic_folder:R.drawable.ic_file);
            text.setText(f.getName());
            hint.setText(FileBrowser.FileAdapter.getTimeStr(f.lastModified()));
            if(!f.isDirectory())
                hint.setText(hint.getText()+" "+FileBrowser.FileAdapter.getSizeStr(f.length()));
      
            node_view.setPadding(getLeftPadding(mNode,2),2,2,2);
            return node_view;
        }

        private int getLeftPadding(TreeNode node,int padding){
            if(node.isRoot())
                return padding;
            return getLeftPadding(node.getParent(),padding+16);
        }
        
        public ProjectTreeHolder(Context context){
            super(context);
            node_view=LayoutInflater.from(context).inflate(R.layout.file_item,null);  
            node_view.setId(com.unnamed.b.atv.R.id.tree_items);
            
        }
    }
}
