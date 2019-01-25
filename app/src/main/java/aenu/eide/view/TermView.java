package aenu.eide.view;
import jackpal.androidterm.emulatorview.EmulatorView;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.AttributeSet;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.view.inputmethod.InputMethodManager;

public class TermView extends EmulatorView{
    
    public TermView(final Context context,AttributeSet attrs){
        super(context,attrs);
        setExtGestureListener(new GestureDetector.OnGestureListener(){
            final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            
            @Override
            public boolean onDown(MotionEvent e){
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e){
            }

            @Override
            public boolean onSingleTapUp(MotionEvent p1){
                //if(!imm.is)
                    imm.showSoftInput(TermView.this,1);            
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent p1, MotionEvent p2, float p3, float p4){
                return false;
            }

            @Override
            public void onLongPress(MotionEvent p1){
            }

            @Override
            public boolean onFling(MotionEvent p1, MotionEvent p2, float p3, float p4){
                return false;
            }        
        });
        /*term_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if(!imm.isActive())
                    imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);            
            }         
        });*/
    }
}
