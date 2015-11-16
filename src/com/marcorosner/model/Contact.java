package com.marcorosner.model;

import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

import com.marcorosner.database.DataAccess;

public class Contact {
	
	private long id;
	private String name;
	private String email;
	
	public Contact(long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public Contact() {
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void showContacts(Context context, ArrayAdapter<String> list){
		DataAccess db = new DataAccess(context);

		db.open();
		Cursor c = db.getAllContacts();

		if(!list.isEmpty()){
			list.clear();	
		}
		
		if (c.moveToFirst()){
			do{
			Contact contact = new Contact(c.getLong(c.getColumnIndex("_id")), // getLong(0), // id
											c.getString(1).toString(), // name
											c.getString(2).toString()); // e-mail

			String[] info = new String[]{Long.toString(contact.getId()), contact.getName(),contact.getEmail()};
			
			list.addAll(info);
			}while(c.moveToNext());
		}

		db.close();
	}
	
	
}
