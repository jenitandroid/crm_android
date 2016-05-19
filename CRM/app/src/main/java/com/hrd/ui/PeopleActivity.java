package com.hrd.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.hrd.crm.R;

public class PeopleActivity extends Activity implements View.OnClickListener {

	private ImageView ivLeads;
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.acitivity_people);
		ivLeads = (ImageView) findViewById(R.id.ivLeads);
		
		ivLeads.setOnClickListener(this);
	}
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeads:
			startActivity(new Intent(PeopleActivity.this,LeadActivity.class));
			break;
		}
	}
}
