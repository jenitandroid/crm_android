package com.hrd.ui;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.hrd.crm.R;
import com.hrd.utils.Const;
import com.hrd.utils.Utils;

public class SplashScreen extends Activity {
	private Handle handler;
	private Animation animationFadeIn;
	private Animation animationFadeOut;
	private Thread animator;
	private boolean isRunning = true;
	private TextView txtAppName;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(1);
		setContentView(R.layout.activity_splash);
		animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
		txtAppName = (TextView) findViewById(R.id.txtAppName);
		handler = new Handle(SplashScreen.this);

		animator = new Thread() {
			public void run() {
				try {
					Utils.systemUpgrade(SplashScreen.this);
					handler.sendMessage(handler.obtainMessage(1));
					sleep(2000);
					handler.sendMessage(handler.obtainMessage(2));
					sleep(2000);
					handler.sendMessage(handler.obtainMessage(3));
					sleep(2000);
					handler.sendMessage(handler.obtainMessage(4));

				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		};

		animator.start();

	}

	static class Handle extends Handler {
		private final WeakReference<SplashScreen> mSplash;

		Handle(SplashScreen splash) {
			// weakReference is used for avoiding memory leak
			mSplash = new WeakReference<SplashScreen>(splash);
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			SplashScreen splash = mSplash.get();
			if (msg.what == 1 && splash.isRunning == true) {
				splash.txtAppName.startAnimation(splash.animationFadeIn);
			} else if (msg.what == 2 && splash.isRunning == true) {
			} else if (msg.what == 3 && splash.isRunning == true) {
				splash.txtAppName.startAnimation(splash.animationFadeOut);
			} else if (msg.what == 4 && splash.isRunning == true) {
				splash.txtAppName.setVisibility(View.GONE);
				
				if(Utils.getValue(splash,Const.PREF_IS_FIRST,0)==0)
				{
					Intent i = new Intent(splash, TermsAndConditionActivity.class);
					splash.overridePendingTransition(0, 0);
					splash.startActivity(i);
					splash.finish();
				}
				else if(Utils.getValue(splash,Const.PREF_IS_FIRST,0)==1)
				{
					Intent i = new Intent(splash, LoginActivity.class);
					splash.overridePendingTransition(0, 0);
					splash.startActivity(i);
					splash.finish();
				}
				else if(Utils.getValue(splash,Const.PREF_IS_FIRST,0)==2)
				{
					Intent i = new Intent(splash, BeginActivity.class);
					splash.overridePendingTransition(0, 0);
					splash.startActivity(i);
					splash.finish();
				}
			}
			super.handleMessage(msg);

		}

	}
}
