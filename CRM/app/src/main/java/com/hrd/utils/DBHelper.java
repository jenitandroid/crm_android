package com.hrd.utils;

/**
 * This Class is For SqliteDatabase Activities
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public Context context;

	public DBHelper(Context context) {
		super(context, Const.DB_NAME, null, 33);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	// This method is used from the bll packege classes.(specially used for the
	// Insert,Update,delete statement.)
	public void execute(String statment) {
		SQLiteDatabase db = this.getWritableDatabase();
		try {

			db.execSQL(statment);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
			db = null;
		}
	}

	// This method is used from the bll packege classes.(specially used for the
	// Select statement.)
	public Cursor query(String statment) {
		Cursor cur = null;
		SQLiteDatabase db = this.getReadableDatabase();
		try {
			cur = db.rawQuery(statment, null);
			cur.moveToPosition(-1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			db.close();
			db = null;
		}

		return cur;
	}

	// This method is used to remove special character from the string.
	public static String getDBStr(String str) {

		str = str != null ? str.replaceAll("'", "''") : null;
		str = str != null ? str.replaceAll("&#039;", "''") : null;
		str = str != null ? str.replaceAll("&amp;", "&") : null;

		return str;

	}

	// This method is used to upgrade the database.
	public void upgrade(int level) {
		switch (level) {
		case 0:
			doUpdate1();
		case 1:
			// doUpdate2();
		case 2:
			// doUpdate3();
		case 3:
			// doUpdate4();
		}
	}
	// This method is only once when the App install the first time on device
	// and create database and tables.
	private void doUpdate1() {

		// Create Table
		this.execute("CREATE TABLE categoty ( "
				+ "category_id INTEGER PRIMARY KEY ," + "category_title TEXT)");
		
		Storage.copyFromAssetToDB(this.context);


	}
}
