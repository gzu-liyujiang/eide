package aenu.eide.PL;
import android.widget.ListPopupWindow;
import com.myopicmobile.textwarrior.android.IAutoComplete;
import com.myopicmobile.textwarrior.android.FreeScrollingTextField;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.Bitmap;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.view.View;
import java.util.List;
import java.util.ArrayList;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ImageView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.graphics.Color;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;

import aenu.eide.R;

public abstract class AutoCompletePanel extends ListPopupWindow implements IAutoComplete{
   
    protected FreeScrollingTextField _textField;
    protected Context _context;
    protected AutoPanelAdapter _adapter;
    protected int _itemHeight;   
	protected final  int PADDING=15;
    
    public AutoCompletePanel(FreeScrollingTextField textField){
        super(textField.getContext());
        _textField=textField;
        _context = textField.getContext();
        initAutoCompletePanel();
    }
    
    private void initAutoCompletePanel() {
        setAnchorView(_textField);
        _adapter = new AutoPanelAdapter();
        setAdapter(_adapter);
        setContentWidth(ListPopupWindow.WRAP_CONTENT);
        
        TypedArray array = _context.getTheme().obtainStyledAttributes(new int[] {  
        android.R.attr.colorBackground, 
        android.R.attr.textColorPrimary, 
        }); 
        int backgroundColor = array.getColor(0, 0xFF00FF); 
        int textColor = array.getColor(1, 0xFF00FF); 
        array.recycle();
        GradientDrawable gd=new GradientDrawable();
        gd.setColor(backgroundColor);
        gd.setCornerRadius(4);
        gd.setStroke(1, textColor);
        setBackgroundDrawable(gd);
        setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
                // TODO: Implement this method
                select(p3);
            }
        });
	}
    
    public int getItemHeight() {
        if (_itemHeight != 0)
            return _itemHeight;
        View v =  LayoutInflater.from(_context).inflate(R.layout.auto_panel_item, null);
        v.measure(0, 0);
        return _itemHeight = v.getMeasuredHeight();
    }

    @Override
    public void cancel(){
        if(isShowing())
            dismiss();
    }
    
    static final class ListItem{

        public final Bitmap bitmap;
        public final String text;
        public final String hint;

        public ListItem(Bitmap bitmap, String text,String hint) {
            this.bitmap = bitmap;
            this.text = text;
            this.hint = hint;
        }       
    }

    class AutoPanelAdapter extends BaseAdapter{

        private final List<ListItem> items=new ArrayList<>();

        @Override
        public int getCount(){
            return items.size();
        }

        @Override
        public ListItem getItem(int p){
            return items.get(p);
        }

        @Override
        public long getItemId(int p1){
            return 0;
        }

        public void setItems(List<ListItem> items){
            this.items.clear();
            this.items.addAll(items);
            this.notifyDataSetChanged();
        }

        @Override
        public View getView(int p, View v, ViewGroup vg){

            View view= v==null
            ?LayoutInflater.from(_context).inflate(R.layout.auto_panel_item,null)
            :v;

            TextView text=(TextView)view.findViewById(R.id.auto_panel_text);
            ImageView image=(ImageView)view.findViewById(R.id.auto_panel_icon);
            ListItem item=getItem(p);

            SpannableString spannableString=null;
            ForegroundColorSpan foregroundColorSpan =null;

            if(item.hint!=null){
                spannableString=new SpannableString(item.text+'\n'+item.hint);   

                int pos=item.text.length();
                foregroundColorSpan=new ForegroundColorSpan(Color.BLACK);
                spannableString.setSpan(foregroundColorSpan, 0,pos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                foregroundColorSpan=new ForegroundColorSpan(Color.GRAY);
                RelativeSizeSpan span=new RelativeSizeSpan(0.6666f);
                spannableString.setSpan(foregroundColorSpan,pos,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
                spannableString.setSpan(span,pos,spannableString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            else{
                spannableString=new SpannableString(item.text);              

                foregroundColorSpan=new ForegroundColorSpan(Color.BLACK);
                spannableString.setSpan(foregroundColorSpan, 0,item.text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);             
            }              

            text.setText(spannableString);
            image.setImageBitmap(item.bitmap);

            return view;
        }
    }
}
