package com.marcorosner.views;

import com.marcorosner.R;
import com.marcorosner.database.DataAccess;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_activity);

	}

	public void addContact(View view){
		DataAccess db = new DataAccess(this);

		db.open();
		EditText name = (EditText) findViewById(R.id.edName);
		EditText email = (EditText) findViewById(R.id.edEmail);
		
		long id = db.insertContact(name.getText().toString(), email.getText().toString());

		Toast.makeText(this,R.string.msgAddSucess + "Id: "+id, Toast.LENGTH_LONG).show();
		
		db.close();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
