package com.hrd.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.hrd.crm.R;

public class HrdButton extends Button
{
  public HrdButton(Context paramContext)
  {
    super(paramContext);
    init(null);
  }

  public HrdButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet);
  }

  public HrdButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet);
  }

  private void init(AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet != null)
    {
      TypedArray localTypedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.HrdTextView);
      String str = localTypedArray.getString(0);
      if (str != null)
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), str));
      localTypedArray.recycle();
    }
  }
}

