package com.hrd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hrd.crm.R;

@SuppressLint("SimpleDateFormat")
public class Utils {
	public static final String SIM_ABSENT = "Absent";
	public static final String SIM_READY = "Ready";
	public static final String SIM_UNKNOWN = "Unknown";
	public static int heightDifference = 0;

	private static SharedPreferences sharedPreferences = null;

	public static void openPref(Context context) {

		sharedPreferences = context.getSharedPreferences(Const.PREF_FILE,
				Context.MODE_PRIVATE);

	}

	public static String getValue(Context context, String key,
			String defaultValue) {
		Utils.openPref(context);
		String result = Utils.sharedPreferences.getString(key, defaultValue);
		Utils.sharedPreferences = null;
		return result;
	}

	public static void systemUpgrade(Context context) {
		DBHelper dbHelper = new DBHelper(context);
		int level = Integer.parseInt(Utils.getValue(context, "LEVEL", "0"));

		if (level == 0) {
			dbHelper.upgrade(level);

			// Create not confirmed order
			level++;

		}
		Utils.setValue(context, "LEVEL", level + "");

	}

	public static int getValue(Context context, String key, int defaultValue) {
		Utils.openPref(context);
		int result = Utils.sharedPreferences.getInt(key, defaultValue);
		Utils.sharedPreferences = null;
		return result;
	}

	public static void setValue(Context context, String key, String value) {
		Utils.openPref(context);
		Editor prefsPrivateEditor = Utils.sharedPreferences.edit();
		prefsPrivateEditor.putString(key, value);
		prefsPrivateEditor.commit();
		prefsPrivateEditor = null;
		Utils.sharedPreferences = null;
	}

	public static void setValue(Context context, String key, int value) {
		Utils.openPref(context);
		Editor prefsPrivateEditor = Utils.sharedPreferences.edit();
		prefsPrivateEditor.putInt(key, value);
		prefsPrivateEditor.commit();
		prefsPrivateEditor = null;
		Utils.sharedPreferences = null;
	}

	public static boolean validateEmail(String email) {

		if (email
				.matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
				&& email.length() > 0) {
			return true;
		}

		return false;
	}

	public static boolean isOnline(Context context) {

		try {
			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (cm != null) {
				return cm.getActiveNetworkInfo().isConnected();
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public static void showTost(Context caller, String title, String message,
			int Icone, int second) {
		if (caller instanceof Activity) {
			View layout = ((Activity) caller).getLayoutInflater().inflate(
					R.layout.cust_toast, null);
			ImageView image = (ImageView) layout.findViewById(R.id.imgIcone);
			image.setImageResource(Icone);
			TextView text = (TextView) layout.findViewById(R.id.txtFeildName);
			text.setText(title);
			TextView textMsg = (TextView) layout.findViewById(R.id.txtMessage);
			textMsg.setText(message);
			Toast toast = new Toast(caller.getApplicationContext());
			toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
			toast.setDuration(second * 1000);
			toast.setView(layout);
			toast.show();
		}

	}

	public static String millisToDate(long millis, String format) {

		return new SimpleDateFormat(format).format(new Date(millis));
	}

	public static long converDatetomillis(String dateString, String dateFormate) {
		long timeInMilliseconds = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
		try {
			Date mDate = sdf.parse(dateString);
			timeInMilliseconds = mDate.getTime();
			System.out.println("Date in milli :: " + timeInMilliseconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeInMilliseconds;
	}

	public static String getDateDifference(String fromDateStr,
			String fromDateFormate, String toDateStr, String toDateFormate) {

		String result = "0";
		System.out.println("===========FROM :::::::" + fromDateStr +"============FORMATE::::::::::::"+fromDateFormate);
		System.out.println("===========TO :::::::" + toDateStr +"============FORMATE::::::::::::"+toDateFormate);
		long diff = converDatetomillis(fromDateStr, fromDateFormate)
				- converDatetomillis(toDateStr, toDateFormate);
		long diffSeconds = (diff / 1000 % 60);
		long diffMinutes = (diff / (60 * 1000) % 60);
		long diffHours = (diff / (60 * 60 * 1000) % 24);
		long diffDays = (diff / (24 * 60 * 60 * 1000));

		if(diffSeconds > 0)
		{
			result = diffSeconds+" Seconds";
		}
		
		if(diffMinutes > 0)
		{
			result = diffMinutes+" Minutes";
		}
		if(diffHours > 0)
		{
			result = diffHours+" Hours";
		}
		
		if(diffDays > 0)
		{
			result = diffDays+" Days";
		}
		
		System.out.print(diffDays + " days, ");
		System.out.print(diffHours + " hours, ");
		System.out.print(diffMinutes + " minutes, ");
		System.out.print(diffSeconds + " seconds.");

		

		System.out.println("===========Reult:::::::" + result);
		return result;

	}

}
