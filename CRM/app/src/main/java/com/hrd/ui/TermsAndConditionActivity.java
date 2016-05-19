package com.hrd.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hrd.crm.R;
import com.hrd.custom.BlurDrawable;
import com.hrd.custom.Header;
import com.hrd.utils.Const;
import com.hrd.utils.Utils;

@SuppressLint("SetJavaScriptEnabled")
public class TermsAndConditionActivity extends Activity {
	private Header header;
	private WebView tac_webview = null;
	private BlurDrawable blurView;
	private RelativeLayout rlMain;
	private LinearLayout llFooter;
	private CheckBox cbTerms;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.terms_and_condition_activity);

		cbTerms = (CheckBox) findViewById(R.id.cbTerms);
		rlMain = (RelativeLayout) findViewById(R.id.rlMain);
		llFooter = (LinearLayout) findViewById(R.id.llFooter);
		blurView = new BlurDrawable(rlMain, 10);

		tac_webview = (WebView) findViewById(R.id.tac_webview);
		header = (Header) findViewById(R.id.term_Header);
		header.setBackgroundDrawable(blurView);
		llFooter.setBackgroundDrawable(blurView);
		header.txtHeaderTitle.setText("Terms & Conditions");
		header.ivMenu.setVisibility(View.GONE);

		// Utils.copyFile(TermsAndConditionActivity.this,"HelveticaLTStd-Roman.otf");
		tac_webview.getSettings().setJavaScriptEnabled(true);
		tac_webview.getSettings().setDefaultFontSize(15);
		tac_webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		tac_webview.setBackgroundColor(0x00000000);
		tac_webview
				.loadDataWithBaseURL(
						null,
						getHtmlData(
								this,
								getString(R.string.terms_and_condition_text)),
						"text/html", "UTF-8", null);

		cbTerms.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					Utils.setValue(TermsAndConditionActivity.this,Const.PREF_IS_FIRST,1);
					startActivity(new Intent(TermsAndConditionActivity.this,LoginActivity.class));
					finish();
				}
			}
		});

	}

	private String getHtmlData(Context context, String data) {
		String head = "<head><style>@font-face {font-family: 'Roboto-Regular';src: url('file:///"
				+ Const.DIR_FONT
				+ "/Oswald-Regular.ttf');}body {font-family: 'Roboto-Regular'; color:#ffffff;}</style></head>";
		String htmlData = "<html>" + head
				+ "<body style=text-align:justify; word-break:hyphenate>"
				+ data + "</body></html>";
		return htmlData;
	}
}
