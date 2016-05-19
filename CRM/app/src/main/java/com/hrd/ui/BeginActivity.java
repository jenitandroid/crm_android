package com.hrd.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.hrd.crm.R;
import com.hrd.utils.ServiceBll;

public class BeginActivity extends Activity implements View.OnClickListener {
	
	private ServiceBll dbBll;
	private TextView txtQuote;
	private TextView txtBegin;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.acitivity_begin);
		
		
		txtQuote = (TextView) findViewById(R.id.txtQuote);
		txtBegin = (TextView) findViewById(R.id.txtBegin);
		
		
		dbBll = new ServiceBll(BeginActivity.this);
		
		txtQuote.setText(dbBll.getRandomQuote().quoteText+ "\n - "+dbBll.getRandomQuote().quoteAuthor);
		
		txtBegin.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txtBegin:
			startActivity(new Intent(BeginActivity.this,HomeActivity.class));
			break;
		}
	}
}
