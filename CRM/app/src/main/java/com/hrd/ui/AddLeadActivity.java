package com.hrd.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.hrd.bean.Leadbean;
import com.hrd.crm.R;
import com.hrd.custom.Header;
import com.hrd.utils.ServiceBll;
import com.hrd.utils.Utils;

public class AddLeadActivity extends Activity implements View.OnClickListener {

	private Header header;
	private EditText edtFirstName;
	private EditText edtLastName;
	private TextView txtLastContact;
	private TextView txtLastActivity;
	private TextView txtRelationShip;
	private EditText edtTitle;
	private EditText edtCompany;
	private EditText edtAddress;
	private TextView txtMap;
	private EditText edtDeskPhone;
	private EditText edtMobile;
	private EditText edtOfficeNo;
	private EditText edtEmail;
	private EditText edtIndustry;
	private EditText edtSource;
	private EditText edtAddNote;
	private TextView txtSave;
	private TextView txtFollowOn;
	private ServiceBll serviceBll;
	private Leadbean leadBean;
	private boolean isFromUpdate = false;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.acitivity_add_lead);
		header = (Header) findViewById(R.id.header);
		header.ivMenu.setImageResource(R.drawable.ic_back);
		header.txtHeaderTitle.setText(getResources().getString(
				R.string.Addleads));

		edtFirstName = (EditText) findViewById(R.id.edtFirstName);
		edtLastName = (EditText) findViewById(R.id.edtLastName);
		txtLastContact = (TextView) findViewById(R.id.txtLastContact);
		txtLastActivity = (TextView) findViewById(R.id.txtLastActivity);
		txtRelationShip = (TextView) findViewById(R.id.txtRelationShip);
		edtTitle = (EditText) findViewById(R.id.edtTitle);
		edtCompany = (EditText) findViewById(R.id.edtCompany);
		edtAddress = (EditText) findViewById(R.id.edtAddress);
		txtMap = (TextView) findViewById(R.id.txtMap);
		edtDeskPhone = (EditText) findViewById(R.id.edtDeskPhone);
		edtMobile = (EditText) findViewById(R.id.edtMobile);
		edtOfficeNo = (EditText) findViewById(R.id.edtOfficeNo);
		edtEmail = (EditText) findViewById(R.id.edtEmail);
		edtIndustry = (EditText) findViewById(R.id.edtIndustry);
		edtSource = (EditText) findViewById(R.id.edtSource);
		edtAddNote = (EditText) findViewById(R.id.edtAddNote);
		txtSave = (TextView) findViewById(R.id.txtSave);
		txtFollowOn = (TextView) findViewById(R.id.txtFollowOn);
		serviceBll = new ServiceBll(AddLeadActivity.this);

		leadBean = (Leadbean) getIntent().getSerializableExtra("Leadbean");
		if (leadBean != null) {

			header.txtHeaderTitle.setText(getResources().getString(
					R.string.ViewLeads));

			isFromUpdate = true;
			edtFirstName.setText(leadBean.first_name);
			edtLastName.setText(leadBean.last_name);
			txtLastContact.setText(Utils.getDateDifference(Utils.millisToDate(
					System.currentTimeMillis(), "dd-MM-yyyy HH:mm"),
					"dd-MM-yyyy HH:mm", leadBean.activity_date,
					"dd-MM-yyyy HH:mm")
					+ " Since last contact");
			edtTitle.setText(leadBean.title);
			edtCompany.setText(leadBean.company_name);
			edtAddress.setText(leadBean.address);
			edtDeskPhone.setText(leadBean.company_phone);
			edtMobile.setText(leadBean.mobile_phone);
			edtOfficeNo.setText(leadBean.office_phone);
			edtEmail.setText(leadBean.email);
			edtIndustry.setText(leadBean.indutry);
			edtSource.setText(leadBean.source);
			edtAddNote.setText(leadBean.notes);
			txtRelationShip.setText(leadBean.relationship);
			txtLastActivity.setText(leadBean.activity);
			txtSave.setText(getResources().getString(R.string.update));
		}

		txtRelationShip.setText("Referel");
		txtSave.setOnClickListener(this);
		txtMap.setOnClickListener(this);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txtSave:
			String result = validate();
			if (result == null) {

				if (isFromUpdate) {
					leadBean.first_name = edtFirstName.getText().toString()
							.trim();
					leadBean.last_name = edtLastName.getText().toString()
							.trim();
					leadBean.activity = txtLastActivity.getText().toString()
							.trim();
					leadBean.relationship = txtRelationShip.getText()
							.toString().trim();
					leadBean.title = edtTitle.getText().toString().trim();
					leadBean.company_name = edtCompany.getText().toString()
							.trim();
					leadBean.address = edtAddress.getText().toString().trim();
					leadBean.company_phone = edtDeskPhone.getText().toString()
							.trim();
					leadBean.office_phone = edtOfficeNo.getText().toString()
							.trim();
					leadBean.mobile_phone = edtMobile.getText().toString()
							.trim();
					leadBean.indutry = edtIndustry.getText().toString().trim();
					leadBean.source = edtSource.getText().toString().trim();
					leadBean.notes = edtAddNote.getText().toString().trim();
					leadBean.activity_date = Utils.millisToDate(
							System.currentTimeMillis(), "dd-MM-yyyy HH:mm");

					serviceBll.updateLead(leadBean);
				} else {
					leadBean = new Leadbean();
					leadBean.first_name = edtFirstName.getText().toString()
							.trim();
					leadBean.last_name = edtLastName.getText().toString()
							.trim();
					leadBean.activity = txtLastActivity.getText().toString()
							.trim();
					leadBean.relationship = txtRelationShip.getText()
							.toString().trim();
					leadBean.title = edtTitle.getText().toString().trim();
					leadBean.company_name = edtCompany.getText().toString()
							.trim();
					leadBean.address = edtAddress.getText().toString().trim();
					leadBean.company_phone = edtDeskPhone.getText().toString()
							.trim();
					leadBean.office_phone = edtOfficeNo.getText().toString()
							.trim();
					leadBean.mobile_phone = edtMobile.getText().toString()
							.trim();
					leadBean.indutry = edtIndustry.getText().toString().trim();
					leadBean.source = edtSource.getText().toString().trim();
					leadBean.notes = edtAddNote.getText().toString().trim();
					leadBean.activity_date = Utils.millisToDate(
							System.currentTimeMillis(), "dd-MM-yyyy HH:mm");

					serviceBll.insertLead(leadBean);
				}

				finish();
			} else {
				Utils.showTost(AddLeadActivity.this, "Alert", result,
						R.drawable.warning, 10000);
			}
			break;
		case R.id.txtMap:
			try {
				if (leadBean != null && leadBean.address != null
						&& !leadBean.address.equals("")) {
					Intent intent = new Intent(
							android.content.Intent.ACTION_VIEW,
							Uri.parse("google.navigation:q=" + leadBean.address));
					startActivity(intent);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	private String validate() {
		String result = null;

		if (edtFirstName.getText().toString().trim() == null
				|| edtFirstName.getText().toString().trim().equals("")) {
			result = "Please enter first name";
		} else if (edtLastName.getText().toString().trim() == null
				|| edtLastName.getText().toString().trim().equals("")) {
			result = "Please enter last name";
		} else if (txtRelationShip.getText().toString().trim() == null
				|| txtRelationShip.getText().toString().trim().equals("")) {
			result = "Please select relation ship";
		} else if (edtTitle.getText().toString().trim() == null
				|| edtTitle.getText().toString().trim().equals("")) {
			result = "Please enter title";
		} else if (edtCompany.getText().toString().trim() == null
				|| edtCompany.getText().toString().trim().equals("")) {
			result = "Please enter company name";
		} else if (edtAddress.getText().toString().trim() == null
				|| edtAddress.getText().toString().trim().equals("")) {
			result = "Please enter address";
		} else if (edtOfficeNo.getText().toString().trim() == null
				|| edtOfficeNo.getText().toString().trim().equals("")) {
			result = "Please enter office number";
		} else if (edtEmail.getText().toString().trim() == null
				|| edtEmail.getText().toString().trim().equals("")) {
			result = "Please enter email address";
		} else if (!Utils.validateEmail(edtEmail.getText().toString().trim())) {
			result = "Please enter valid email address";
		}

		else if (edtIndustry.getText().toString().trim() == null
				|| edtIndustry.getText().toString().trim().equals("")) {
			result = "Please enter industry";
		} else if (edtSource.getText().toString().trim() == null
				|| edtSource.getText().toString().trim().equals("")) {
			result = "Please enter source";
		} else if (edtAddNote.getText().toString().trim() == null
				|| edtAddNote.getText().toString().trim().equals("")) {
			result = "Please enter notes";
		}

		return result;
	}
}
