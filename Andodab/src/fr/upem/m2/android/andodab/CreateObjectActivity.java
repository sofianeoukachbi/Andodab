package fr.upem.m2.android.andodab;

import java.util.ArrayList;
import java.util.List;

import fr.upem.m2.android.andodab.DAO.BddOperations;
import fr.upem.m2.android.andodab.beans.Bdd_bean;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import fr.upem.m2.android.andodab.beans.Bdd_bean;

public class CreateObjectActivity extends Activity {
	
	private ArrayList<String> listObjets;
	private List<Bdd_bean> dbList;
	private Spinner dbNameSpinner;
	private Spinner parentList;
	private ListView attributList;
	private Button annulerCreation,validerCreation,ajouterAttribut;
	private ArrayList<String> attribItemList;
	private ArrayAdapter<String> listAdapter;
	private String selectedDBname;
	private int selectedDBid;
	private int selectedAttrib=-1;
	private BddOperations db;
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_object);
		
		db = new BddOperations(this);
		
		dbNameSpinner = (Spinner) findViewById(R.id.dbName);
		attributList =  (ListView) findViewById(R.id.attributList);
		parentList = (Spinner) findViewById(R.id.parentList);
		annulerCreation = (Button) findViewById(R.id.cancelBtn);
		validerCreation = (Button) findViewById(R.id.validerBtn);
		ajouterAttribut = (Button) findViewById(R.id.addAtributBtn);
						
		
		//Appel methode qui retourne le nom des base de donné existant
		 
		dbList = db.getListBdd();
	 
		ArrayAdapter<Bdd_bean> dbNameAdapter = new ArrayAdapter<Bdd_bean>(CreateObjectActivity.this,android.R.layout.simple_spinner_item,dbList);
		dbNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dbNameSpinner.setAdapter(dbNameAdapter);		    
		dbNameSpinner.setSelection(dbNameSpinner.getSelectedItemPosition(), false);
		dbNameSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				selectedDBname = dbList.get(position).getBdd_name();
				selectedDBid = 	dbList.get(position).getBdd_id();			
				//Appel methode qui retourne les objets de la base selectionné
				//....
				//liste retourné
				listObjets = new ArrayList<String>();
				listObjets.add("Aucun");
				listObjets.add("objet 1");
				listObjets.add("objet 2");
				listObjets.add("objet 3");
				
				//parentList.removeAllViews();
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,listObjets);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				parentList.setAdapter(adapter);			
	
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}

		});
		
		
		parentList.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Log.v("dddd", "parent choisit");
				Log.v("dddd", listObjets.get(position));
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}

		});
		
		
		
		attribItemList = new ArrayList<String>();
		  listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,attribItemList);
		attributList.setAdapter(listAdapter);
		
		
		
		attributList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selectedAttrib = position;
				
			}
		});	
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_object, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void doCancelNewObject(View v){
		Log.v("sefse", "sdssrgs");
	}
	
	public void doCreateObject(View v){		
		Log.v("sefse", "sdssrgs");
	}
	
	public void doAddAttribut(View v){
		
		Intent intent  = new Intent(getApplicationContext(),AddAtribut.class);
		Log.v("class", selectedDBname);
		Log.v("class", ""+ selectedDBid);
		intent.putExtra("db", selectedDBname);
		intent.putExtra("db_id", selectedDBid);
		startActivityForResult(intent, 0);		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == Activity.RESULT_OK) {
	        Log.v("infoo","atrribut="+data.getExtras().getString("Nom")+"---"+data.getExtras().getString("Value"));
	        attribItemList.add(data.getExtras().getString("Nom")+" : "+data.getExtras().getString("Value"));
	        listAdapter.notifyDataSetChanged();
	      
	      }
	}
	 
	
	public void removeAttrib(View v){
		if(selectedAttrib != -1){
			attribItemList.remove(selectedAttrib);
	        listAdapter.notifyDataSetChanged();	      
	        selectedAttrib = -1;
		}		
		
	}	
	
}
