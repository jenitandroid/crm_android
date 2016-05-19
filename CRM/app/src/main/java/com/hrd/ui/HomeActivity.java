package com.hrd.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.hrd.crm.R;

public class HomeActivity extends Activity implements View.OnClickListener {

	private ImageView ivPeople;
	private ImageView ivTodo;
	private ImageView ivCalender;
	private ImageView ivSettings;
	private ImageView ivComunity;
	private ImageView ivReports;
	private ImageView ivEmail;
	private ImageView ivBackup;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.acitivity_home);
		ivPeople = (ImageView) findViewById(R.id.ivPeople);
		ivTodo= (ImageView) findViewById(R.id.ivTodo);
		ivCalender= (ImageView) findViewById(R.id.ivCalender);
		ivSettings= (ImageView) findViewById(R.id.ivSettings);
		ivComunity= (ImageView) findViewById(R.id.ivComunity);
		ivReports= (ImageView) findViewById(R.id.ivReports);
		ivEmail= (ImageView) findViewById(R.id.ivEmail);
		ivBackup= (ImageView) findViewById(R.id.ivBackup);

		ivPeople.setOnClickListener(this);
		ivTodo.setOnClickListener(this);
		ivBackup.setOnClickListener(this);
		ivCalender.setOnClickListener(this);
		ivSettings.setOnClickListener(this);
		ivComunity.setOnClickListener(this);
		ivReports.setOnClickListener(this);
		ivEmail.setOnClickListener(this);

	}

	public void onClick(View paramView) {
		switch (paramView.getId()) {
		case R.id.ivPeople:
			startActivity(new Intent(HomeActivity.this, PeopleActivity.class));
			break;

		}
	}
}
