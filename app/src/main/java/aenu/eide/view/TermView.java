package aenu.eide.view;
import jackpal.androidterm.emulatorview.EmulatorView;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.AttributeSet;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.view.inputmethod.InputMethodManager;
import android.view.KeyEvent;

public class TermView extends EmulatorView{
    
    private class TermViewGestureListener extends SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            doToggleSoftKeyboard();  
            return true;
        }

        private void doToggleSoftKeyboard() {
            InputMethodManager imm = (InputMethodManager)
                    getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        }
    }
        
    public TermView(final Context context,AttributeSet attrs){
        super(context,attrs);
        setExtGestureListener(new TermViewGestureListener());
    }
}
