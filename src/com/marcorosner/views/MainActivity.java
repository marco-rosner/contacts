package com.marcorosner.views;


import com.marcorosner.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	
	public void addContact(View view){
		
		startActivity(new Intent(this, AddActivity.class));

	}
	
	public void updateContact(View view){
		
		startActivity(new Intent(this, UpdateActivity.class));

	}
	
	public void deleteContact(View view){
		
		startActivity(new Intent(this, DeleteActivity.class));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
