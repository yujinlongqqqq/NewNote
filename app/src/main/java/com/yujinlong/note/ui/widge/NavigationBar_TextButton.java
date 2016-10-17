package com.yujinlong.note.ui.widge;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.yujinlong.note.R;

public class NavigationBar_TextButton extends NavigationBar
{
    private LayoutInflater m_inflate;

    public NavigationBar_TextButton(Context context)
    {
        this(context, null);
    }

    public NavigationBar_TextButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void inflateContextView(Context context)
    {
        m_inflate = (LayoutInflater) LayoutInflater.from(context);
        m_inflate.inflate(R.layout.view_navigationbar_textbutton, this, true);
    }

}
