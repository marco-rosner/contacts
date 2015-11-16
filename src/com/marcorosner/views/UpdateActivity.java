package com.marcorosner.views;

import java.util.ArrayList;
import java.util.List;

import com.marcorosner.R;
import com.marcorosner.database.DataAccess;
import com.marcorosner.model.Contact;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class UpdateActivity extends Activity {
	
	private List<String> listContatcs = new ArrayList<String>();
	private ArrayAdapter<String> listContainer = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_activity);

		ListView lstContacts = (ListView) findViewById(R.id.lstContact);
		listContainer = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, listContatcs);
		lstContacts.setAdapter(listContainer);
		
		lstContacts.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View contact, int itemPos, long id){

				ListView lv = (ListView) findViewById(R.id.lstContact);
				
				EditText edId = (EditText) findViewById(R.id.edId);
				EditText name = (EditText) findViewById(R.id.edName);
				EditText email = (EditText) findViewById(R.id.edEmail);
				
				edId.setText(lv.getItemAtPosition(itemPos).toString());
				name.setText(lv.getItemAtPosition(itemPos+1).toString());
				email.setText(lv.getItemAtPosition(itemPos+2).toString());

			}
		});
		
		// Mostrar todos os contatos
		new Contact().showContacts(this, listContainer);

	}

	public void updateContact(View view){
		DataAccess db = new DataAccess(this);

		db.open();

		EditText i = (EditText) findViewById(R.id.edId);
		EditText name = (EditText) findViewById(R.id.edName);
		EditText email = (EditText) findViewById(R.id.edEmail);

		long id = Long.parseLong(i.getText().toString());

		if(db.updateContact(id, name.getText().toString(), email.getText().toString())){
			Toast.makeText(this, R.string.msgUpSucess, Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(this, R.string.msgFail, Toast.LENGTH_LONG).show();
		}

		// Mostrar todos os contatos
		new Contact().showContacts(this, listContainer);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
