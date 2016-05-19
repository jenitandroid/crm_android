package com.hrd.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hrd.crm.R;

public class Footer extends RelativeLayout
{
  private Context context;
  public LinearLayout llListTab;
  public LinearLayout llMediTab;
  public LinearLayout llListNewMed;

  public Footer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    View localView = LayoutInflater.from(paramContext).inflate(R.layout.footer, this);
    this.llListTab = ((LinearLayout)localView.findViewById(R.id.llListTab));
    this.llMediTab = ((LinearLayout)localView.findViewById(R.id.llMediTab));
    this.llListNewMed = ((LinearLayout)localView.findViewById(R.id.llListNewMed));
  }
}

