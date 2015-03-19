package fr.upem.m2.android.andodab;

import java.util.ArrayList;
import java.util.List;

import fr.upem.m2.android.andodab.DAO.BddOperations;
import fr.upem.m2.android.andodab.beans.Bdd_bean;
import fr.upem.m2.android.andodab.beans.Final_bean;
import fr.upem.m2.android.andodab.beans.Objet_bean;
import fr.upem.m2.android.andodab.beans.Primitif_bean;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import fr.upem.m2.android.andodab.beans.Bdd_bean;

public class CreateObjectActivity extends Activity {
	
	private ArrayList<Objet_bean> listObjets;
	private List<Bdd_bean> dbList;
	private Spinner dbNameSpinner;
	private Spinner parentList;
	private ListView attributList;
	private Button annulerCreation,validerCreation,ajouterAttribut;
	private ArrayList<AttributType> attribItemList;
	private ArrayAdapter<AttributType> listAdapter;
	private String selectedDBname;
	private int selectedDBid;
	private int selectedAttrib=-1;
	private BddOperations db;
	private EditText objectName ;
	private int selectedObjectIndex;
	 

	
	public class AttributType {
		private int type;
		private String valeur;
		private String nom;
		
		public AttributType(int type, String valeur, String nom) {
			
			this.type = type;
			this.valeur = valeur;
			this.nom = nom;
		}

		@Override
		public String toString() {
			return  nom + " : " + valeur ;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getValeur() {
			return valeur;
		}

		public void setValeur(String valeur) {
			this.valeur = valeur;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}
		
		
		
		
	}
	
	
	
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
		
		objectName = (EditText) findViewById(R.id.objectNameTxt);
						
		
		//Appel methode qui retourne le nom des base de donné existant
		 
		dbList = db.getListBdd();
	 
		ArrayAdapter<Bdd_bean> dbNameAdapter = new ArrayAdapter<Bdd_bean>(CreateObjectActivity.this,android.R.layout.simple_spinner_item,dbList);
		dbNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dbNameSpinner.setAdapter(dbNameAdapter);		    
		//dbNameSpinner.setSelection(dbNameSpinner.getSelectedItemPosition(), false);
		dbNameSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				selectedDBname = dbList.get(position).getBdd_name();
				selectedDBid = 	dbList.get(position).getBdd_id();			
				//Appel methode qui retourne les objets de la base selectionné
				//....
				//liste retourné
			//	listObjets = new ArrayList<Objet_bean>(db.getListObjetNonFinaux());
				listObjets = new ArrayList<Objet_bean>();
					
				listObjets.add(0,new Objet_bean("objet 1", null, null, 0));
				listObjets.add(0,new Objet_bean("objet 2", null, null, 0));
				listObjets.add(0,new Objet_bean("Aucun", 0, 0, 0));	
				 				
				//parentList.removeAllViews();
				ArrayAdapter<Objet_bean> adapter = new ArrayAdapter<Objet_bean>(getApplicationContext(),android.R.layout.simple_spinner_item,listObjets);
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
				
				selectedObjectIndex = position;
								
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}

		});
		
		
		
		attribItemList = new ArrayList<AttributType>();
		  listAdapter = new ArrayAdapter<AttributType>(this, android.R.layout.simple_list_item_1,attribItemList);
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
		Intent intent  = new Intent(getApplicationContext(),MainActivity.class);
	 
		startActivity(intent);	
	}
	
	public void doCreateObject(View v){		
		Objet_bean objet;
		
		
		if(listObjets.get(selectedObjectIndex).getObjet_nom()!= "Aucun")
		{
			
		 
		objet= new Objet_bean(objectName.getText().toString(),listObjets.get(selectedObjectIndex).getObjet_id(),null,selectedDBid);
		}
		else
		{
			objet= new Objet_bean(objectName.getText().toString(),null,null,selectedDBid);
		}
		 
		 
		int objectId = db.createObjet(objet);
		Log.v("idO", ""+objectId);
		
		for (AttributType attrib : attribItemList) {
			String valeur = attrib.getValeur();	
			
		switch (attrib.getType()) {	 
		// valeur final
		case 1:
			
					
			Final_bean valeurFinal = new Final_bean(null,attrib.getValeur(), null);
			int x =db.addFinalToObjet(objectId, attrib.getNom(), valeurFinal);
			Log.v("fin", ""+x);
			break;
			
		case 2:
			// Objet
			break;
			
case 3:
	//  primitif
	int idPrimitif ;
	if(valeur == "String")
	{
		idPrimitif = 1;
	}
	else
		if(valeur == "float"){
			idPrimitif = 2;	
		}
		else
		{
			idPrimitif = 3;
		}
	
	Primitif_bean primitif = new Primitif_bean(idPrimitif);
	db.addPrimitifToObjet(objectId, attrib.getNom(), primitif);
			
			break;
			
		}
			
		}
		
		
		objectName.setText("");
		attribItemList.clear();
		listAdapter.notifyDataSetChanged();
		Toast.makeText(getApplicationContext(), "Objet crée !", Toast.LENGTH_SHORT).show();
		
		
	}
	
	public void doAddAttribut(View v){
		
	if(selectedDBname.equals(null)){
		Toast.makeText(getApplicationContext(), "Il faut choisir une BD", Toast.LENGTH_LONG).show();
	}
	else
	{		
		Intent intent  = new Intent(getApplicationContext(),AddAtribut.class);
		Log.v("class", selectedDBname);
		Log.v("class", ""+ selectedDBid);
		intent.putExtra("db", selectedDBname);
		intent.putExtra("db_id", selectedDBid);
		startActivityForResult(intent, 0);	
		
		
	}
			
	 
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == Activity.RESULT_OK) {
	        Log.v("infoo","atrribut="+data.getExtras().getString("Nom")+"---"+data.getExtras().getString("Value"));
	        attribItemList.add(new AttributType(data.getExtras().getInt("Type"), data.getExtras().getString("Value"), data.getExtras().getString("Nom")));
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
