package com.hrd.utils;

/**
 *  Helper class that maintain all the query functions relate to locations Table
 */
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.hrd.bean.Leadbean;
import com.hrd.bean.Quotebean;

public class ServiceBll {

	public Context context;

	public ServiceBll(Context context) {
		this.context = context;
	}

	// Select function for Location for Footer Menu options
	public Quotebean getRandomQuote() {
		Quotebean servBean = new Quotebean();
		DBHelper dbHelper = null;
		Cursor cursor = null;
		String sql = null;
		try {
			sql = "SELECT * FROM quotes ORDER BY RANDOM() LIMIT 1";
			dbHelper = new DBHelper(this.context);

			cursor = dbHelper.query(sql);
			if (cursor != null && cursor.getCount() > 0) {
				while (cursor.moveToNext()) {

					servBean.quoteText = cursor.getString(0);
					servBean.quoteAuthor = cursor.getString(1);
					servBean.quoteLines = cursor.getString(2);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbHelper = null;
			if (cursor != null && !cursor.isClosed())
				cursor.close();
		}

		// release
		dbHelper = null;
		cursor = null;
		sql = null;
		return servBean;
	}

	public ArrayList<Leadbean> getLeads() {
		ArrayList<Leadbean> leadList = new ArrayList<Leadbean>();
		Leadbean leadBean;
		DBHelper dbHelper = null;
		Cursor cursor = null;
		String sql = null;
		try {
			sql = "SELECT notes,follow_up,forwarded,activity_date,activity,company_phone,office_phone,mobile_phone,indutry,source,relationship,added_date,address,company_name,title,last_name,first_name,id FROM leads";
			dbHelper = new DBHelper(this.context);

			cursor = dbHelper.query(sql);
			if (cursor != null && cursor.getCount() > 0) {
				while (cursor.moveToNext()) {
					leadBean = new Leadbean();
					leadBean.notes = cursor.getString(0);
					leadBean.follow_up = cursor.getString(1);
					leadBean.forwarded = cursor.getString(2);
					leadBean.activity_date = cursor.getString(3);
					leadBean.activity = cursor.getString(4);
					leadBean.company_phone = cursor.getString(5);
					leadBean.office_phone = cursor.getString(6);
					leadBean.mobile_phone = cursor.getString(7);
					leadBean.indutry = cursor.getString(8);
					leadBean.source = cursor.getString(9);
					leadBean.relationship = cursor.getString(10);
					leadBean.added_date = cursor.getString(11);
					leadBean.address = cursor.getString(12);
					leadBean.company_name = cursor.getString(13);
					leadBean.title = cursor.getString(14);
					leadBean.last_name = cursor.getString(15);
					leadBean.first_name = cursor.getString(16);
					leadBean.leadId = cursor.getInt(17);
					leadList.add(leadBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbHelper = null;
			if (cursor != null && !cursor.isClosed())
				cursor.close();
		}

		// release
		dbHelper = null;
		cursor = null;
		sql = null;
		return leadList;
	}

	public void insertLead(Leadbean leadBean) {

		DBHelper dbHelper = null;
		String sql = null;
		try {

			sql = "INSERT INTO leads "
					+ " (notes,follow_up,forwarded,activity_date,activity,company_phone,office_phone,mobile_phone,indutry,source,relationship,added_date,address,company_name,title,last_name,first_name)"
					+ " VALUES ('"
					+ leadBean.notes
					+ "','"
					+ leadBean.follow_up
					+ "','"
					+ leadBean.forwarded
					+ "','"
					+ leadBean.activity_date
					+ "','"
					+ leadBean.activity
					+ "','"
					+ leadBean.company_phone
					+ "','"
					+ leadBean.office_phone
					+ "','"
					+ leadBean.mobile_phone
					+ "','"
					+ leadBean.indutry
					+ "','"
					+ leadBean.source
					+ "','"
					+ leadBean.relationship
					+ "','"
					+ leadBean.added_date
					+ "','"
					+ leadBean.address
					+ "','"
					+ leadBean.company_name
					+ "','"
					+ leadBean.title
					+ "','"
					+ leadBean.last_name
					+ "','"
					+ leadBean.first_name
					+ "')";

			System.out.println("=============SQl::::::::::::" + sql);
			dbHelper = new DBHelper(this.context);

			dbHelper.execute(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbHelper = null;

		}

		// release
		dbHelper = null;
		sql = null;

	}
	
	public void updateLead(Leadbean leadBean) {

		DBHelper dbHelper = null;
		String sql = null;
		try {
			sql = "Update leads set notes = '" + leadBean.notes
					+ "', follow_up = '" + leadBean.follow_up
					+ "', forwarded = '" + leadBean.forwarded
					+ "', activity_date = '" + leadBean.activity_date
					+ "', activity = '" + leadBean.activity
					+ "', company_phone = '" + leadBean.company_phone
					+ "', office_phone = '" + leadBean.office_phone
					+ "', mobile_phone = '" + leadBean.mobile_phone
					+ "', indutry = '" + leadBean.indutry
					+ "', source = '" + leadBean.source
					+ "', relationship = '" + leadBean.relationship
					+ "', added_date = '" + leadBean.added_date
					+ "', address = '" + leadBean.address
					+ "', company_name = '" + leadBean.company_name
					+ "', title = '" + leadBean.title
					+ "', last_name = '" + leadBean.last_name
					+ "', first_name = '" + leadBean.first_name
					+ "' where id = "
					+ leadBean.leadId;

			dbHelper = new DBHelper(this.context);

			dbHelper.execute(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbHelper = null;

		}

		// release
		dbHelper = null;
		sql = null;

	}
}
