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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DeleteActivity extends Activity {

	private List<String> listContacts = new ArrayList<String>();
	private ArrayAdapter<String> listContainer = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_activity);

		ListView lstContacts = (ListView) findViewById(R.id.lstContact);
		listContainer = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, listContacts);
		lstContacts.setAdapter(listContainer);
		
		lstContacts.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View contact, int itemPos, long id){

				ListView lv = (ListView) findViewById(R.id.lstContact);
				
				EditText edId = (EditText) findViewById(R.id.edId);
				
				edId.setText(lv.getItemAtPosition(itemPos).toString());
			}
		});
		
		// Mostrar todos os contatos
		new Contact().showContacts(this, listContainer);

	}

	public void deleteContact(View view){
		DataAccess db = new DataAccess(this);

		db.open();

		EditText i = (EditText) findViewById(R.id.edId);

		long id = Long.parseLong(i.getText().toString());

		if(db.deleteContact(id)){
			Toast.makeText(this, R.string.msgDelSucess, Toast.LENGTH_LONG).show();
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
