package com.marcorosner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataAccess {
	static final String KEY_ROWID = "_id";
	static final String KEY_NAME = "name";
	static final String KEY_EMAIL = "email";
	static final String TAG = "DataAccess";
	static final String DATABASE_NAME = "MyDB";
	static final String TABLECONTACTS = "contacts";
	static final int DATABASE_VERSION = 1;
	static final String TABLE_CREATE ="create table contacts (_id integer primary key autoincrement, "
			+ "name text not null, email text not null);";
	
	final Context context;
	DatabaseConnection conDB;
	SQLiteDatabase db;
	
	//DataAccess banco = new DataAccess(this);
	
	public DataAccess(Context ctx)
	{
		this.context = ctx;
		conDB = new DatabaseConnection(context);
	}
	
	private static class DatabaseConnection extends SQLiteOpenHelper
	{
		public DatabaseConnection(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db){
			try {
				db.execSQL(TABLE_CREATE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS contacts");
			onCreate(db);
		}
	}
	
	//---Abrir o BD---
	public DataAccess open() throws SQLException
	{
		db = conDB.getWritableDatabase();
		return this;
	}
	//---Fechar o BD---
	public void close()
	{
		conDB.close();
	}

	//	DataAccess banco = new DataAccess(this);
	//	banco.open();
	//	banco.insertContact("osvaldo","os@gmail.com");
	
	//---Inserir um contato---
	public long insertContact(String name, String email)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_EMAIL, email);
		return db.insert(TABLECONTACTS, null, initialValues);
	}
	
	//---Excluir um contato em particular---
	public boolean deleteContact(long rowId)
	{
		return db.delete(TABLECONTACTS, KEY_ROWID + "=" + rowId, null) > 0;
	}
	
	//---Carregar todos os contatos---
	public Cursor getAllContacts()
	{
		return db.query(TABLECONTACTS, new String[] {KEY_ROWID, KEY_NAME,
				KEY_EMAIL}, null, null, null, null, null);
	}
	
	//---Carregar um contato em particular---
	public Cursor getContact(long rowId) throws SQLException
	{
		Cursor mCursor =
				db.query(true, TABLECONTACTS, new String[] {KEY_ROWID,
						KEY_NAME, KEY_EMAIL}, KEY_ROWID + "=" + rowId, null,
						null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	//---Atualizar um contato---
	public boolean updateContact(long rowId, String name, String email)
	{
		ContentValues args = new ContentValues();
		args.put(KEY_NAME, name);
		args.put(KEY_EMAIL, email);
		return db.update(TABLECONTACTS, args, KEY_ROWID + "="+ rowId, null) > 0;
	}
}