package fr.upem.m2.android.andodab;



import java.net.URL;
import java.util.List;

import fr.upem.m2.android.andodab.DAO.BddOperations;
import fr.upem.m2.android.andodab.beans.Bdd_bean;


import android.R.color;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button consulterBaseBtn;
	private Button creerBaseBtn;
	private Button creerObjetBtn;
	private Button editionObjetBtn;
	private Spinner listBdd;
	private BddOperations bddo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accueil);
		
	
		bddo = new BddOperations(this);
		initView();
		initEventView();
	}

//________________________________________________________________________________________

	/**
	 * cette m�thode nous permet d'initialiser nos vues (les boutton et autres)
	 */
	public void initView() {
		
		consulterBaseBtn = (Button) findViewById(R.id.bt_afficher);
		creerBaseBtn = (Button) findViewById(R.id.bt_creer_base);
		creerObjetBtn = (Button) findViewById(R.id.bt_ajout_objet);
		editionObjetBtn = (Button) findViewById(R.id.bt_edition);
		listBdd = (Spinner) findViewById(R.id.list_bdd);

	}
	
//________________________________________________________________________________________
	/*
	 * ititialisation de la boite de dialog nous permitant d'afficher les objet d'une base
	 */
	public void initListAfficheDialogBDD() {
		// instanciation du layout en tant que view
		LayoutInflater factory = LayoutInflater.from(this);
		final View alertDialogView = factory.inflate(R.layout.affiche_dialog_layout, null);

		// cr�ation de l'alret dialog
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
	
		
		//affichage de la vue que l'on a cr�er pour le AlertDialog
		adb.setView(alertDialogView);
		
		//On donne un titre � l'AlertDialog
        adb.setTitle("Choix de la BDD");
        
        adb.setIcon(getResources().getDrawable(R.drawable.choix));
        
        //On affecte un bouton "OK" � notre AlertDialog et on lui affecte un �v�nement
 
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
 
            		
          } });
        


 
        //On cr�e un bouton "Annuler" � notre AlertDialog et on lui affecte un �v�nement
        adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	
          } });
        AlertDialog alert = adb.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setBackgroundColor(getResources().getColor(R.color.no));
        Drawable imgn = getBaseContext().getResources().getDrawable( R.drawable.annuler );
        imgn.setBounds( 0, 0, 60, 60 );
        nbutton.setCompoundDrawables( imgn, null, null, null );
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setBackgroundColor(getResources().getColor(R.color.success));
//        pbutton.setBackgroundResource(resid)
        Drawable img = getBaseContext().getResources().getDrawable( R.drawable.ok );
        img.setBounds( 0, 0, 60, 60 );
        pbutton.setCompoundDrawables( img, null, null, null );
	}

//________________________________________________________________________________________
	/*
	 * ititialisation de la boite de dialog nous permetant de cr�er un nouvelle base de donn�e
	 */
	public void initCreerBddDialog(){
		
		LayoutInflater factory = LayoutInflater.from(this);
		final View alertDialogView = factory.inflate(R.layout.creer_bdd_dialog_layout, null);
		
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		
				adb.setView(alertDialogView);
				adb.setTitle("Cr�ation d'une base");        
				adb.setIcon(getResources().getDrawable(R.drawable.choix));
		        
//		action a faire en cliquant sur le bouton ok 
		        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		            	
		            	EditText ed = (EditText)findViewById(R.id.editText1);
		            	
		            	//bddo.CreateBdd("adembddtest");
		            	
		            	List<Bdd_bean> bddList = bddo.getListBdd();
		            	
		            	for (Bdd_bean b : bddList){
	            			Log.v("bdd", b.getBdd_name());
	            		}
		            	
		          } });

		 
//		action a faire en cas d'annulation 
		        adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		            
//		            	ne rien faire
		            	
		          } });
//		        adb.show();
		        AlertDialog alert = adb.create();
		        alert.show();
		        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
		        nbutton.setBackgroundColor(getResources().getColor(R.color.no));
		        Drawable imgn = getBaseContext().getResources().getDrawable( R.drawable.annuler );
		        imgn.setBounds( 0, 0, 60, 60 );
		        nbutton.setCompoundDrawables( imgn, null, null, null );
		        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
		        pbutton.setBackgroundColor(getResources().getColor(R.color.success));
//		        pbutton.setBackgroundResource(resid)
		        Drawable img = getBaseContext().getResources().getDrawable( R.drawable.ok );
		        img.setBounds( 0, 0, 60, 60 );
		        pbutton.setCompoundDrawables( img, null, null, null );
	}

//________________________________________________________________________________________
	/*
	 * cette m�thode nous permet d'initialiser les listener
	 */
	public void initEventView() {
		consulterBaseBtn.setOnClickListener(consulterBaseBtnListener);
		creerBaseBtn.setOnClickListener(creerBaseBtnListener);
		creerObjetBtn.setOnClickListener(creerObjetBtnListener);
		editionObjetBtn.setOnClickListener(editionObjetBtnListener);
	}

	
//________________________________________________________________________________________
	/*
	 * D�claration des listener pour chaque objet (bouton)
	 */
	
	private OnClickListener consulterBaseBtnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			initListAfficheDialogBDD();
		}
	};

	private OnClickListener creerBaseBtnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			initCreerBddDialog();
		}
	};

	private OnClickListener creerObjetBtnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getApplicationContext(),
					CreateObjectActivity.class);
			startActivity(intent);
		}
	};

	private OnClickListener editionObjetBtnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getApplicationContext(),
					EditObjectActivity.class);
			startActivity(intent);
		}
	};

}
