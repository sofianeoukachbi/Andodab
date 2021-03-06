package fr.upem.m2.android.andodab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.upem.m2.android.andodab.DAO.BddOperations;
import fr.upem.m2.android.andodab.beans.Bdd_bean;
import fr.upem.m2.android.andodab.beans.Objet_bean;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
//import android.view.View.OnClickListener;
import android.widget.Toast;

@SuppressLint("NewApi") public class EditObjectActivity extends Activity {
	private List<Bdd_bean> list_BD;
	private List<Objet_bean> list_object;
	private Spinner sp_db;
	private Spinner sp_object;
	private Bdd_bean selected_bd;
	private Objet_bean selected_ob;
	private Button btn_delete;
	private Button btn_modif;
	private BddOperations bddo;
	int idB;
	int idOb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_attribute);
		bddo = new BddOperations(this);
		initView();
	
		initEventView();
	}
	
	/**
	 * recupere les objets du layout
	 */
	public void initView() {
		sp_db = (Spinner) findViewById(R.id.id_spinner_base);
		btn_delete = (Button) findViewById(R.id.id_btn_delete_ob);
		btn_modif = (Button) findViewById(R.id.id_btn_modif_ob);
		sp_object = (Spinner) findViewById(R.id.id_spinner_object);
		/**
		 * recupere la liste des Bd
		 */
		List<Bdd_bean> lis = bddo.getListBdd();
		list_BD = new ArrayList<Bdd_bean>();
		for (Bdd_bean b : lis) {
			list_BD.add(b);
		Toast.makeText(EditObjectActivity.this, b.getBdd_name(), Toast.LENGTH_SHORT).show();
		}

		ArrayAdapter<Bdd_bean> dbNameAdapter = new ArrayAdapter<Bdd_bean>(EditObjectActivity.this,android.R.layout.simple_spinner_item,list_BD);
		dbNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_db.setAdapter(dbNameAdapter);		    
		sp_db.setSelection(sp_db.getSelectedItemPosition(), false);
	}
	
	/**
	 * initier les vues
	 */
	public void initEventView() {
		sp_db.setOnItemSelectedListener(bd_selected);
		sp_object.setOnItemSelectedListener(ob_selected);
		btn_delete.setOnClickListener( btn_delete_click);
		btn_modif.setOnClickListener( btn_modif_click);
	}
	
	/**
	 * lorsqu'on clique sur suprimer
	 */
	private android.view.View.OnClickListener btn_delete_click = new View.OnClickListener() {

		
		@Override
		public void onClick(View v) {
			int id=0;
		showDialog(id);

		}

		
	};
	/**
	 * lorsqu'on clique sur modifier
	 */
private android.view.View.OnClickListener btn_modif_click = new View.OnClickListener() {

		
		@Override
		public void onClick(View v) {
			/**
			 * aller � l'activit� EditValueObjectActivity
			 */
			Intent intent = new Intent(getApplicationContext(),	EditValueObjectActivity.class);
			intent.putExtra("db_id", idB);
			startActivity(intent);

		}

		
	};

	
	
	/**
	 * lorsque la base de donn�e est selection�e
	 */
	private OnItemSelectedListener bd_selected = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			selected_bd = list_BD.get(position);
			 idB = selected_bd.getBdd_id();
			List<Objet_bean> listO = bddo.getListeObjetBDD(idB);
			list_object = new ArrayList<Objet_bean>();
			Log.v("listO", list_object.size()+"");
			for(Objet_bean o:listO )
			{
				list_object.add(o);
				Log.v("listO",o.toString());
			}

			ArrayAdapter<Objet_bean> adapter = new ArrayAdapter<Objet_bean>(getApplicationContext(),android.R.layout.simple_spinner_item,list_object);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			sp_object.setAdapter(adapter);	
			sp_object.setBackgroundColor(Color.BLACK);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private OnItemSelectedListener ob_selected = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			selected_ob = list_object.get(position);
			 idOb = selected_ob.getObjet_id();
			
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	};
	

	/**
	 * creer le dialogue de suppression d'objets
	 */
	  @Override
	  public Dialog onCreateDialog (int id) {
		  Dialog dialog;
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			 
				builder.setMessage("Voulez-vous supprimer l'objets ?");
				builder.setCancelable(false);
				builder.setTitle("Suppression");
				builder.setIcon(getResources().getDrawable(R.drawable.choix));
				builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Objet_bean ob =(Objet_bean) sp_object.getSelectedItem();
						Toast.makeText(EditObjectActivity.this,ob.getObjet_nom()+"", Toast.LENGTH_SHORT).show();
						bddo = new BddOperations(EditObjectActivity.this);
						bddo.deleteObjet(idOb);
						
						
						
					}
				});
				
			 
				builder.setNegativeButton("NON",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			 
				dialog = builder.create();
				return dialog;
				
	  }
	  
//	  @Override
//	  public void onPrepareDialog (int id, Dialog box) {
//	  
//	      box.setTitle("suppression de !"+id);
//	     //On ne s'int�resse pas au cas o� l'identifiant vaut 1, puisque cette bo�te affiche le m�me texte � chaque lancement
//	  }
		
	
						
		
	
		


		

}
