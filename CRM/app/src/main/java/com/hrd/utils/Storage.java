package com.hrd.utils;

/**
 * This Class is used for common functions
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

public class Storage {
	/*
	 * Copy files
	 */
	public static void copy(InputStream in, String dest) {
		System.out.println("Copying DB to :: " + dest);
		FileOutputStream out = null;
		int ch;

		try {
			out = new FileOutputStream(dest);

			while ((ch = in.read()) != -1) {
				out.write(ch);
			}

			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
			}

			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
			}
		}

		in = null;
		out = null;
	}
	
	/*
	 * Copy database file From Asset to Db
	 */
	public static void copyFromAssetToDB(Context context) {
	try {
		Storage.copy(context.getResources().getAssets()
				.open("crm.sqlite"),
				"/data/data/com.hrd.crm/databases/"
						+ Const.DB_NAME);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
}