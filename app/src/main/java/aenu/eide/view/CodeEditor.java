package aenu.eide.view;
import com.myopicmobile.textwarrior.android.FreeScrollingTextField;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Typeface;
import com.myopicmobile.textwarrior.android.YoyoNavigationMethod;
import java.io.File;
import java.io.IOException;
import com.myopicmobile.textwarrior.common.Document;
import com.myopicmobile.textwarrior.common.DocumentProvider;
import aenu.eide.util.IOUtils;
import com.myopicmobile.textwarrior.android.ICodeDiag;
import java.util.List;
import com.myopicmobile.textwarrior.android.ICodeDiag.DiagInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import com.myopicmobile.textwarrior.common.ILanguage;
import com.myopicmobile.textwarrior.common.Pair;

import com.myopicmobile.textwarrior.common.ColorScheme.Colorable;
import java.util.BitSet;

public class CodeEditor extends FreeScrollingTextField
{
    private String path;
    private List<ICodeDiag.DiagInfo> errors;
    private List<ICodeDiag.DiagInfo> warnings;
    
    public CodeEditor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setShowLineNumbers(true);
        setWordWrap(false);
        setTabSpaces(4);
        setAutoIndentWidth(4);  
        setTypeface(Typeface.MONOSPACE);
        setNavigationMethod((new YoyoNavigationMethod(this)));
    }

    public CodeEditor(Context context,int style) {
        super(context,style);
        setShowLineNumbers(true);
        setWordWrap(false);
        setTabSpaces(4);
        setAutoIndentWidth(4);   
        setTypeface(Typeface.MONOSPACE);
        setNavigationMethod((new YoyoNavigationMethod(this)));
    }
    
    public final void setText(CharSequence text) {
        Document document = new Document(this);
        document.setWordWrap(false);
        document.setText(text);
        setDocumentProvider(new DocumentProvider(document));
    }
    
    public final void redo() {

        int redo = createDocumentProvider().redo();
        if (redo >= 0) {
            setEdited(true);
            respan();
            selectText(false);
            moveCaret(redo);
            invalidate();
        }
    }
    
    public final void undo() {

        int undo = createDocumentProvider().undo();
        if (undo >= 0) {
            setEdited(true);
            respan();
            selectText(false);
            moveCaret(undo);
            invalidate();
        }
    }
    
    public void read(File file,Object... v) throws IOException {
        this.path = file.getAbsolutePath();    
        String text=new String(IOUtils.file_read(path));
        setText(text);
    }

    public void save() throws IOException{
        if(this.path!=null)
            IOUtils.file_write(this.path,_hDoc.toString().getBytes());
    }
    
    /*
    
    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save(); 

        //translate clipping region to create padding around edges
        canvas.clipRect(getScrollX() + getPaddingLeft(),
        getScrollY() + getPaddingTop(),
        getScrollX() + getWidth() - getPaddingRight(),
        getScrollY() + getHeight() - getPaddingBottom());
        canvas.translate(getPaddingLeft(), getPaddingTop());
        realDraw(canvas);
        canvas.restore();

        _navMethod.onTextDrawComplete(canvas);
    }

    /**
     * 绘制
     * @param canvas
     */
/*
    private void realDraw(Canvas canvas){
        int beginPaintRow = getBeginPaintRow(canvas);
        int currentIndex = _hDoc.getLineOffset(beginPaintRow);

        if(currentIndex < 0){
            return;
        }
        int currRowNum = getBeginPaintRow(canvas);
        int currLineNum=isWordWrap() ? _hDoc.findLineNumber(currentIndex)+1 : currRowNum+1;
        int lastLineNum=0;
        int leftOffset;
        if(_showLineNumbers) {
            leftOffset = (int) _brushLine.measureText(" "+_hDoc.getRowCount() );
        }
        else
        {
            leftOffset=0;
        }
        //----------------------------------------------
        // set up span coloring settings
        //----------------------------------------------
        int spanIndex = 0;
        //得到一个词法分析的结果
        List<Pair> spans = _hDoc.getSpans();
   
        Pair nextSpan = (Pair) spans.get(spanIndex++);
        Pair currSpan;
        do{
            currSpan = nextSpan;
            if(spanIndex < spans.size()){
                nextSpan = (Pair) spans.get(spanIndex++);
            }
            else{
                nextSpan = null;
            }
        }
        while(nextSpan != null &&
        nextSpan.getFirst() <= currentIndex);

        int spanColor = _colorScheme.getTokenColor(currSpan.getSecond());
        _brush.setColor(spanColor);

        //----------------------------------------------
        // set up graphics settings
        //----------------------------------------------
        int paintX;
        int paintY = getPaintBaseline(beginPaintRow);
        int endY = getPaintBaseline(getEndPaintRow(canvas));

        //----------------------------------------------
        // start painting!
        //----------------------------------------------
        canvas.drawColor(_colorScheme.getColor(Colorable.BACKGROUND));
        int rowCount=_hDoc.getRowCount();
        if(_showLineNumbers){
            _brushLine.setColor(_colorScheme.getColor(Colorable.NON_PRINTING_GLYPH));
            _brushLine.setStrokeWidth(2.0f);
            _brushLine.setFakeBoldText(true);
            canvas.drawLine(leftOffset-_spaceWidth/2,getScrollY(),leftOffset-_spaceWidth/2,getScrollY()+getHeight(),_brushLine);
        }
        _hDoc.seekChar(currentIndex);//从currentIndex开始迭代
        paintX = leftOffset;


        final int rowH=rowHeight();
        int lineY=(beginPaintRow+1)*rowH;

        boolean line_draw_hint=false;

        List<ICodeDiag.DiagInfo> errs=null;
        List<ICodeDiag.DiagInfo> wars=null;
        BitSet errSet=null;
        BitSet warSet=null;
        BitSet errSkip=null;
        BitSet warSkip=null;


        {
            errs=errors;
            wars=warnings;
            final int err_count=errs==null?0:errs.size();
            final int war_count=wars==null?0:wars.size();

            if(err_count!=0){
                errSet=new BitSet(err_count);
                errSkip=new BitSet(err_count);           
            }

            if(war_count!=0){
                warSet=new BitSet(war_count);
                warSkip=new BitSet(war_count);  
            }

        }

        while (paintY <= endY && _hDoc.hasNext()){

            if (currRowNum > rowCount) {
                break;
            }

            if(_showLineNumbers && currLineNum!=lastLineNum)
            {
                lastLineNum=currLineNum;
                String num=String.valueOf(currLineNum);
                drawLineNum(canvas, num, 0, paintY);
            }

            // check if formatting changes are needed
            if(reachedNextSpan(currentIndex, nextSpan)){
                currSpan = nextSpan;
                spanColor = _colorScheme.getTokenColor(currSpan.getSecond());
                _brush.setColor(spanColor);

                if(spanIndex < spans.size()){
                    nextSpan = (Pair) spans.get(spanIndex++);
                }
                else{
                    nextSpan = null;
                }
            }
            if (currentIndex == _caretPosition){
                drawCaret(canvas, paintX, paintY);
            }
            else if(currentIndex+1==_caretPosition){
                _caretSpan=currSpan;
            }
            char c = _hDoc.next();
            int lineX=paintX;

            if (_fieldController.inSelectionRange(currentIndex)){
                paintX += drawSelectedText(canvas, c, paintX, paintY);
            }
            else{
                paintX += drawChar(canvas, c, paintX, paintY);
            }

            int lineX2=paintX;

            {
                if(errs!=null)
                    for(int i=0;i<errs.size();i++){

                        if(errSkip.get(i)) continue;
                        ICodeDiag.DiagInfo p=errs.get(i);
                        if(p.start_position<=currentIndex&&p.end_position>=currentIndex){
                            draw_line(canvas,lineX,lineX2,lineY,Color.RED);
                            if(!errSet.get(i)){
                                draw_small_text(canvas,lineX,lineY+24,p.message);
                                errSet.set(i);
                            }

                            if(p.end_position==currentIndex)
                                errSkip.set(i);

                            line_draw_hint=true;
                            break;
                        }
                    }
            }

            {
                if(wars!=null)
                    for(int i=0;i<wars.size();i++){
                        if(warSkip.get(i)) continue;

                        ICodeDiag.DiagInfo p=wars.get(i);
                        if(p.start_position<=currentIndex&&p.end_position>=currentIndex){
                            draw_line(canvas,lineX,lineX2,lineY,Color.RED);
                            if(!warSet.get(i)){
                                draw_small_text(canvas,lineX,lineY+24,p.message);
                                warSet.set(i);
                            }

                            if(p.end_position==currentIndex)
                                warSkip.set(i); 
                            line_draw_hint=true;

                            break;
                        }
                    }
            }      

            ++currentIndex;
            if (c == ILanguage.NEWLINE){
                paintY += rowH;
                lineY += rowH;

                if(line_draw_hint){
                    paintY+=24;
                    lineY+=24;
                    line_draw_hint=false;
                }
                if (paintX >= _xExtent){
                    _xExtent = paintX;
                }
                paintX = leftOffset;
                currLineNum++;
            }
        } // end while

        doOptionHighlightRow(canvas);
    }
    
    private final Paint _lineP=new Paint();{
        _lineP.setStrokeWidth(4);
    }

    private void draw_line(Canvas canvas,float x,float x_end,float Y,int color){
        _lineP.setColor(color);
        canvas.drawLine(x,Y,x_end,Y,_lineP);
    }

    private final Paint _smallTextP=new Paint();{
        _smallTextP.setTextSize(24);
        _smallTextP.setAntiAlias(true);
        _smallTextP.setColor(Color.RED);
    }

    private void draw_small_text(Canvas canvas,float x,float y,String text){
        canvas.drawText(text,x,y,_smallTextP);
    }*/
    
    public void setErrors(List<ICodeDiag.DiagInfo> errs){
        errors=errs;
    }

    public void setWarnings(List<ICodeDiag.DiagInfo> wars){
        warnings=wars;
    }
}
