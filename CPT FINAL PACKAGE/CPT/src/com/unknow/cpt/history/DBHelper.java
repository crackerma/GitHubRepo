package com.unknow.cpt.history;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

/**
 * DBHelper: Create two table in local sqlite database, history table and favourite table.
 * @author Mali
 */
final class DBHelper extends SQLiteOpenHelper {

  private static final int DB_VERSION = 5;
  private static final String DB_NAME = "cpt.db";
  static final String RRCENT_TABLE = "history";
  static final String RID_COL = "id";
  static final String RNAME_COL = "name";
  static final String FAVOURITE_TABLE = "favourite";
  static final String FID_COL = "id";
  static final String FNAME_COL = "name";

  DBHelper(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
	    sqLiteDatabase.execSQL(
	            "CREATE TABLE " + RRCENT_TABLE + " (" +
	            RID_COL + " TEXT PRIMARY KEY, " +
	            RNAME_COL + " TEXT);");
	    sqLiteDatabase.execSQL(
	            "CREATE TABLE " + FAVOURITE_TABLE + " (" +
	            FID_COL + " TEXT PRIMARY KEY, " +
	            FNAME_COL + " TEXT);");
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RRCENT_TABLE);
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FAVOURITE_TABLE);
    onCreate(sqLiteDatabase);
  }

}
