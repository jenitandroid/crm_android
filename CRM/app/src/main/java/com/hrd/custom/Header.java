package com.hrd.custom;

import com.hrd.crm.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Header extends RelativeLayout
{
  private Context context;
  public ImageView ivMenu;
  public TextView txtHeaderTitle;

  public Header(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    View localView = LayoutInflater.from(paramContext).inflate(R.layout.header, this);
    this.ivMenu = ((ImageView)localView.findViewById(R.id.ivMenu));
    this.txtHeaderTitle = ((TextView)localView.findViewById(R.id.txtHeaderTitle));
  }
}
