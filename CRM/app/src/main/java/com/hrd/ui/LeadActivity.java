package com.hrd.ui;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.hrd.adapter.LeadAdapter;
import com.hrd.bean.Leadbean;
import com.hrd.crm.R;
import com.hrd.custom.Header;
import com.hrd.utils.ServiceBll;

public class LeadActivity extends Activity implements View.OnClickListener {

	private Header header;
	private ListView lvLeads;
	private LeadAdapter leadAdapter;
	private ArrayList<Leadbean> leadList;
	private ServiceBll serviceBLL;
	private TextView txtAddLead;
	private EditText edtSearch;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.acitivity_lead);
		header = (Header) findViewById(R.id.header);
		header.ivMenu.setImageResource(R.drawable.ic_back);
		header.txtHeaderTitle.setText(getResources().getString(R.string.leads));


		//init listview for the lead
		lvLeads = (ListView) findViewById(R.id.lvLeads);
		leadList = new ArrayList<Leadbean>();

		//set adpater for the lead
		leadAdapter = new LeadAdapter(LeadActivity.this, leadList);
		lvLeads.setAdapter(leadAdapter);

		//init search
		this.edtSearch = (EditText) findViewById(R.id.edtSearch);
		this.edtSearch.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable paramAnonymousEditable) {
				String str = LeadActivity.this.edtSearch.getText().toString()
						.toLowerCase(Locale.getDefault());

				leadAdapter.filter(str);

			}

			public void beforeTextChanged(
					CharSequence paramAnonymousCharSequence,
					int paramAnonymousInt1, int paramAnonymousInt2,
					int paramAnonymousInt3) {
			}

			public void onTextChanged(CharSequence paramAnonymousCharSequence,
					int paramAnonymousInt1, int paramAnonymousInt2,
					int paramAnonymousInt3) {
			}
		});


		//Add lead text view
		txtAddLead = (TextView) findViewById(R.id.txtAddLead);

		//Add click listener for the new lead
		txtAddLead.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		serviceBLL = new ServiceBll(LeadActivity.this);
		leadAdapter.refill(serviceBLL.getLeads());
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txtAddLead:
			startActivity(new Intent(LeadActivity.this, AddLeadActivity.class));
			break;
		}
	}
}
