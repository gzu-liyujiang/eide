package com.myopicmobile.textwarrior.android;

public interface IAutoComplete
{
    public void select(int pos);
    public void complete(CharSequence text,int pos);
    public void cancel();
}
