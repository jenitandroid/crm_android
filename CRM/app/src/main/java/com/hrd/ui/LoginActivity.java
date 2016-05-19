package com.hrd.ui;

import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthError;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.hrd.crm.R;
import com.hrd.utils.Const;
import com.hrd.utils.Utils;

public class LoginActivity extends Activity implements View.OnClickListener,DialogListener {
	private SocialAuthAdapter socialAdapter;
	private TextView icLoginBtn;
	private ImageView icLoginFacebook;
	private ImageView icLoginTwitter;
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.acitivity_login);
		
		icLoginBtn = (TextView) findViewById(R.id.icLoginBtn);
		icLoginTwitter = (ImageView) findViewById(R.id.icLoginTwitter);
		icLoginFacebook = (ImageView) findViewById(R.id.icLoginFacebook);
		
		socialAdapter = new SocialAuthAdapter(this);
		socialAdapter.getCurrentProvider();
		
		icLoginBtn.setOnClickListener(this);
		icLoginFacebook.setOnClickListener(this);
		icLoginTwitter.setOnClickListener(this);
		
	}

	public void onClick(View paramView) {
		switch (paramView.getId()) {
		case R.id.icLoginBtn:
			startActivity(new Intent(this, BeginActivity.class));
			break;
		case R.id.icLoginFacebook:
			if (Utils.isOnline(LoginActivity.this)) {
				socialAdapter.authorize(LoginActivity.this,
						SocialAuthAdapter.Provider.FACEBOOK);
			} else {

				Utils.showTost(LoginActivity.this,
						getResources().getString(R.string.alert),
						getResources().getString(R.string.api_no_internet_msg),
						R.drawable.info, 10);
			}
			break;
		case R.id.icLoginTwitter:
			if (Utils.isOnline(LoginActivity.this)) {
				socialAdapter.authorize(LoginActivity.this,
						SocialAuthAdapter.Provider.TWITTER);
			} else {

				Utils.showTost(LoginActivity.this,
						getResources().getString(R.string.alert),
						getResources().getString(R.string.api_no_internet_msg),
						R.drawable.info, 10);
			}
			break;
		}
	}

	@Override
	public void onBack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComplete(Bundle arg0) {


		if (socialAdapter.getCurrentProvider().getProviderId()
				.equals(org.brickred.socialauth.util.Constants.FACEBOOK)) {
			Utils.setValue(LoginActivity.this, Const.PREF_FACEBOOK_TOKEN,socialAdapter.getCurrentProvider().getAccessGrant().getKey());
			
		} else if (socialAdapter.getCurrentProvider().getProviderId()
				.equals(org.brickred.socialauth.util.Constants.TWITTER)) {
			Utils.setValue(LoginActivity.this, Const.PREF_TWEETER_TOKEN,
					socialAdapter.getCurrentProvider().getAccessGrant()
							.getKey());

			
		}

	
		
	}

	@Override
	public void onError(SocialAuthError arg0) {
		// TODO Auto-generated method stub
		
	}
}
