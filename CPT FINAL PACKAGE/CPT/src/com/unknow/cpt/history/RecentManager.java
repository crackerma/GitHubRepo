/**
 * Provide some basic databse operation on History table.
 */
package com.unknow.cpt.history;

import java.util.ArrayList;
import java.util.List;

import com.unknow.cpt.history.DBHelper;
import com.unknow.cpt.product.Product;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecentManager implements HistoryManager {
	private final Activity activity;
	private static final String[] COUNT_COLUMN = { "COUNT(1)" };
	private static final String[] COLUMNS = {
		DBHelper.RID_COL,
		DBHelper.RNAME_COL,
		};
	public RecentManager(Activity activity){
		this.activity = activity;
	}

	@Override
	public boolean hasItems() {
		SQLiteOpenHelper helper = new DBHelper(activity);
	    SQLiteDatabase db = null;
	    Cursor cursor = null;
	    try {
	        db = helper.getReadableDatabase();
	        cursor = db.query(DBHelper.RRCENT_TABLE, COUNT_COLUMN, null, null, null, null, null);
	        cursor.moveToFirst();
	        return cursor.getInt(0) > 0;
	      } finally {
	        close(cursor, db);
	      }
	}
	
	@Override
	public List<Object> getItems(){
	    SQLiteOpenHelper helper = new DBHelper(activity);
	    List<Object> items = new ArrayList<Object>();
	    SQLiteDatabase db = null;
	    Cursor cursor = null;
	    try{
	    	db = helper.getReadableDatabase();
	    	cursor = db.query(DBHelper.RRCENT_TABLE, COLUMNS, null, null, null, null, null);
	    	while(cursor.moveToNext()){
	    		String pId = cursor.getString(0);
	    		String pName = cursor.getString(1);
	    		items.add(new Product(pId,pName));
	    	}
	    }finally{
	    	close(cursor, db);
	    }
	    return items;
	}
	
	@Override
	public void deleteItem(String ID) {
		// TODO Auto-generated method stub
	    SQLiteOpenHelper helper = new DBHelper(activity);
	    SQLiteDatabase db = null;
	    try {
	      db = helper.getWritableDatabase();      
	      db.delete(DBHelper.RRCENT_TABLE, DBHelper.RID_COL + '=' + ID, null);
	    } finally {
	      close(null, db);
	    }
	}

	@Override
	public void addItem(Object o) {
		// TODO Auto-generated method stub
		Product p = (Product)o;
		ContentValues values = new ContentValues();
		values.put(DBHelper.RID_COL, p.getProductID());
		values.put(DBHelper.RNAME_COL, p.getProductName());
		SQLiteOpenHelper helper = new DBHelper(activity);
		SQLiteDatabase db = null;
	    try {
	        db = helper.getWritableDatabase();      
	        // Insert the new entry into the DB.
	        db.insert(DBHelper.RRCENT_TABLE, null, values);
	      } finally {
	        close(null, db);
	    }
	}
	private static void close(Cursor cursor, SQLiteDatabase database) {
		if (cursor != null) {
			cursor.close();
		}
		if (database != null) {
			database.close();
		}
	}

	@Override
	public Object getItem(String ID) {
		// TODO Auto-generated method stub
		SQLiteOpenHelper helper = new DBHelper(activity);
		Object item = null;
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try{
			db = helper.getReadableDatabase();
			cursor = db.query(DBHelper.RRCENT_TABLE, COLUMNS, DBHelper.RID_COL + '=' + ID, null, null, null, null);
			while(cursor.moveToNext()){
				String pId = cursor.getString(0);
				String pName = cursor.getString(1);
				item = new Product(pId,pName);
			}
		}finally{
			close(cursor, db);
		}
		return item;
	}

}
