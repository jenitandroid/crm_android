package com.hrd.adapter;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hrd.bean.Leadbean;
import com.hrd.crm.R;
import com.hrd.ui.AddLeadActivity;
import com.hrd.utils.Utils;

public class LeadAdapter extends BaseAdapter {
	public ArrayList<Leadbean> galleryArray;
	private ArrayList<Leadbean> arraylist;
	private Context mContext;

	public LeadAdapter(Context paramContext,
			ArrayList<Leadbean> paramArrayList) {
		this.mContext = paramContext;
		this.galleryArray = paramArrayList;
		this.arraylist = new ArrayList();
		this.arraylist.addAll(paramArrayList);

	}

	public int getCount() {
		return this.galleryArray.size();
	}

	public void setAllItems(ArrayList<Leadbean> paramArrayList) {
		if (paramArrayList != null && paramArrayList.size() > 0) {
			this.galleryArray.addAll(paramArrayList);
			this.arraylist.addAll(paramArrayList);
		}
	}

	public void removeAllItems() {
		this.galleryArray.removeAll(galleryArray);
		this.arraylist.removeAll(arraylist);
	}

	public void refill(ArrayList<Leadbean> paramArrayList) {
		this.galleryArray.clear();
		this.arraylist.clear();
		this.galleryArray.addAll(paramArrayList);
		this.arraylist.addAll(paramArrayList);
		notifyDataSetChanged();
	}

	public Object getItem(int paramInt) {
		return Integer.valueOf(paramInt);
	}

	public long getItemId(int paramInt) {
		return paramInt;
	}
	
	// Filter Class
		public void filter(String charText) {
			charText = charText.toLowerCase(Locale.getDefault());
			galleryArray.clear();
			if (charText.length() == 0) {
				galleryArray.addAll(arraylist);
			} else {
				for (Leadbean wp : arraylist) {
					if (wp.first_name.toLowerCase(Locale.getDefault()).contains(
							charText)
							|| wp.last_name.toLowerCase(Locale.getDefault())
									.contains(charText) || wp.company_name.toLowerCase(Locale.getDefault()).contains(
											charText)) {
						galleryArray.add(wp);
					}
				}
			}
			notifyDataSetChanged();
		}

	@SuppressWarnings("static-access")
	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
		LayoutInflater localLayoutInflater = (LayoutInflater) this.mContext
				.getSystemService("layout_inflater");
		Viewholder localViewholder = null;
		if (paramView == null) {
			paramView = localLayoutInflater.inflate(R.layout.row_lead,
					paramViewGroup, false);
			localViewholder = new Viewholder();
			localViewholder.cbLead = (CheckBox) paramView
					.findViewById(R.id.cbLead);
			localViewholder.txtLeadNamePost = (TextView) paramView
					.findViewById(R.id.txtLeadNamePost);
			localViewholder.txtLeadCompany = (TextView) paramView
					.findViewById(R.id.txtLeadCompany);
			localViewholder.txtLeadLastCntct = (TextView) paramView
					.findViewById(R.id.txtLeadLastCntct);
			localViewholder.llMain = (RelativeLayout) paramView
					.findViewById(R.id.llMain);
			
			paramView.setTag(localViewholder);

		} else {
			localViewholder = new Viewholder();
			localViewholder = (Viewholder) paramView.getTag();
		}


		
		localViewholder.txtLeadNamePost.setText(galleryArray.get(paramInt).first_name +" "+galleryArray.get(paramInt).last_name+","+galleryArray.get(paramInt).title);
		localViewholder.txtLeadCompany.setText(galleryArray.get(paramInt).company_name);
		System.out.println("==================LEAD DATE:::::::::::"+galleryArray.get(paramInt).activity_date);
		localViewholder.txtLeadLastCntct.setText(Utils.getDateDifference(Utils.millisToDate(System.currentTimeMillis(), "dd-MM-yyyy HH:mm"), "dd-MM-yyyy HH:mm", galleryArray.get(paramInt).activity_date, "dd-MM-yyyy HH:mm")+" Since last contact");
		localViewholder.llMain.setTag(galleryArray.get(paramInt));
		localViewholder.llMain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Leadbean Leadbean = (Leadbean) v.getTag();
				mContext.startActivity(new Intent(mContext,AddLeadActivity.class).putExtra("Leadbean", Leadbean));

			}
		});
		
		

		return paramView;

	}
	

	static class Viewholder {
		CheckBox cbLead; 
		TextView txtLeadNamePost;
		TextView txtLeadCompany;
		TextView txtLeadLastCntct;
		RelativeLayout llMain;
	}
}
