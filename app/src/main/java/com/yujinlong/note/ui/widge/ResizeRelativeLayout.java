package com.yujinlong.note.ui.widge;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @author Rockey
 * this template use File | Settings | File Templates.
 */
public class ResizeRelativeLayout extends RelativeLayout implements View.OnClickListener
{
    private OnSoftKeyboardListener onSoftKeyboardListener;
    private boolean isSoftKeyboardShown;

    public ResizeRelativeLayout(Context context)
    {
        super(context);
    }

    public ResizeRelativeLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        try
        {
            super.onLayout(changed, l, t, r, b);  
        }
        catch (Exception e)
        {
            Log.e("ResizLayout",e.getMessage());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (onSoftKeyboardListener != null)
        {
            final int newSpec = MeasureSpec.getSize(heightMeasureSpec);
            final int oldSpec = getMeasuredHeight();
            if (oldSpec > newSpec)
            {
                isSoftKeyboardShown = true;
                onSoftKeyboardListener.onShown();
            }
            else if (oldSpec < newSpec)
            {
            	isSoftKeyboardShown = false;
                onSoftKeyboardListener.onClosed();
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public final void setOnSoftKeyboardListener(final OnSoftKeyboardListener listener)
    {
        this.onSoftKeyboardListener = listener;
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() > 0)
        {
            v.getLayoutParams();
        }
    }

    public interface OnSoftKeyboardListener
    {
        public void onShown();
        public void onClosed();
    }

    public boolean isSoftKeyboardShown()
    {
        return isSoftKeyboardShown;
    }

    public interface ActionListener
    {
        void onLayoutChange();
    }

}
