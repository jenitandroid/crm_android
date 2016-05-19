package com.hrd.utils;

import android.content.Context;
import android.os.Environment;

import com.hrd.crm.R;

public class Const {

	public static Context CONTEXT;

	public static final String PREF_FILE = "INVITE_PREF";

	public static String HTTP_POST = "httpPost";
	public static String HTTP_GET = "httpGet";
	
	public static String DB_NAME = "CRM_DB";

	
	public static String APP_HOME = Environment.getExternalStorageDirectory()
			.getPath() + "/DrView";
	public static String THUMBNAIL_DIR = APP_HOME + "/Thumbnail/";
	public static String IMAGE_DIR = APP_HOME + "/Images/";
	public static String DIR_LIST = APP_HOME + "/AllDocs/";
	public static String DIR_MEDICINE = APP_HOME + "/ImpDocs/";
	public static String DIR_LOG = APP_HOME + "/Log";
	public static String DIR_DATA = APP_HOME + "/data";
	public static String LOG_ZIP = APP_HOME + "/DrView.zip";
	public static String PREF_IS_FIRST = "PREF_IS_FIRST";
	public static String DIR_FONT = "android_asset";

	// Default Messages
	public static final String LOGIN_REQUIRED = "loginRequired";
	public static final String NO_AUTHORIZATION = "noAuthorization";
	public static final String NETWORK_ERROR = "networkError";
	public static final String API_ERROR = "apiError";
	public static final String SERVER_ERROR = "serverError";
	public static String API_ALERT = "Invite";
	public static String UNKNOWN_ERROR = "UNKNOWN_ERROR";
	
	
	// Image names
	public static final int ICON_INFO = R.drawable.info;
	public static final int ICON_WARNING = R.drawable.warning;
	public static final int ICON_ERROR = R.drawable.error;
	public static final int ICON_NETWORK_ERROR = R.drawable.networkerror;
	public static final int ICON_UNAUTHORIZED = R.drawable.unauthorized;
	public static final int ICON_TOOLS = R.drawable.tools;
	
	
	public static String PREF_FACEBOOK_TOKEN = "PREF_FACEBOOK_TOKEN";
	public static String PREF_TWEETER_TOKEN = "PREF_TWEETER_TOKEN";
	public static String PREF_GOOGLE_TOKEN = "PREF_GOOGLE_TOKEN";
	

}