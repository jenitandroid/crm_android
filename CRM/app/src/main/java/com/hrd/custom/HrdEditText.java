package com.hrd.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.hrd.crm.R;

public class HrdEditText extends EditText
{
  public HrdEditText(Context paramContext)
  {
    super(paramContext);
    init(null);
  }

  public HrdEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet);
  }

  public HrdEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
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

